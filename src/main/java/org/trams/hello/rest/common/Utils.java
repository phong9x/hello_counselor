package org.trams.hello.rest.common;

import org.trams.hello.bean.Counselor;
import org.trams.hello.bean.CounselorScheduleSetting;
import org.trams.hello.bean.api.ReservationDate;
import org.trams.hello.bean.api.ReservationTime;
import org.trams.hello.business.service.CounselingSessionService;

import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {
	public static String convertListStartTimeToString(List<Integer> list){
		String rs = "";
		Float startTimeConvert = 0f;
		for (Integer integer : list) {
			startTimeConvert = ((float)integer /(float) 3600);
			startTimeConvert = (float) (Math.round(startTimeConvert*10.0)/10.0);
			rs = rs + Float.valueOf(startTimeConvert) + ";";
		}
		return rs;
	}
	
	public static Date getDate3MonthsAgo() {
		Calendar calendar = Calendar.getInstance();         
		calendar.add(Calendar.MONTH, -3);
//		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	
	public static float roundingFloat2DecimalPlaces(int total, int count) {
		float result = 0;
		result = (float)total/(float)count;
		result = (float) Math.round(result * 10) / 10;
		
		return result;
	}
	
	public static List<Date> getDaysByMonth(int year, int month, Date lastOfMonth, List<ReservationDate> listDate) {
		SimpleDateFormat sd = new SimpleDateFormat("dd");
		List<Date> list = new ArrayList<>();
		int DayLastOfMonth = Integer.valueOf(sd.format(lastOfMonth));
		List<Integer> integers = new ArrayList<>();
		for (ReservationDate elem : listDate) {
			integers.add(Integer.valueOf(sd.format(elem.getReservationDate())));
		}
		
		for (int i = 1; i <= DayLastOfMonth; i++) {
			if (!integers.contains(i)) {
				Calendar c = Calendar.getInstance();
				c.set(year, month -1, i, 0,0);
				list.add(c.getTime());
			}
		}
		
		return list;
	}
	
	public static Date getDate(int month, int year) {
		Calendar calendar = new GregorianCalendar(year, month, 0);
	    return calendar.getTime();
	}
	
	public static List<String> reservationTimes(CounselorScheduleSetting CounselorScheduleSetting, Counselor counselor, Date reservationDate, CounselingSessionService counselingSessionService, String reservedTime) {
		List<Float> reservationTimeFl = new ArrayList<>();
		String reservationTimeStr = CounselorScheduleSetting.getReservationTime();
		for (String str : reservationTimeStr.split(";")) {
			float fl = Float.valueOf(str);
			reservationTimeFl.add(fl);
		}
			
		List<ReservationTime> reservationTimes = new ArrayList<>();
		
		if (reservationTimeFl.size() > 0) {
			float iConvert_0 = reservationTimeFl.get(0);
			ReservationTime r = new ReservationTime();
			r.setFromDate(iConvert_0);
			r.setToDate(iConvert_0 + 0.5f);
			reservationTimes.add(r); 
			
			for (int i = 1; i < reservationTimeFl.size(); i++) {
				float iConvert_i = reservationTimeFl.get(i);
				if (iConvert_i == r.getToDate()) {
					r.setToDate(r.getToDate() + 0.5f);
				} else {
					ReservationTime r2 = new ReservationTime();
					r2.setFromDate(iConvert_i);
					r2.setToDate(iConvert_i + 0.5f);
					reservationTimes.add(r2);
					iConvert_0 = iConvert_i;
					r = r2;
				}
			}
		}
		
		
		/*calculate reservation_time from Database*/
		List<Float> reservedTimeFl = new ArrayList<>();
		String reservedTimeStr = reservedTime;
		if (reservedTimeStr.isEmpty() || (reservationTimeStr == null)) {
			System.out.println(" not reservedTimeFl");
		} else {
			for (String str1 : reservedTimeStr.split(";")) {
				float fl1 = Float.valueOf(str1);
				reservedTimeFl.add(fl1);
			}
			if(reservationTimes.size() > 1) {
				for (int i = reservationTimes.size()-1; i >= 0; i--) {
					ReservationTime re = reservationTimes.get(i);
					for (int j = 0; j < reservedTimeFl.size(); j++) {
						float fromDate_ = reservedTimeFl.get(j);
						float toDate_ = reservedTimeFl.get(j) + 0.5f;
						if (fromDate_ >= re.getFromDate() && fromDate_ <= re.getToDate()) {
							if (fromDate_ == re.getFromDate()) {
								re.setFromDate(toDate_);
								if(re.getToDate() > toDate_) {
									re.setToDate(re.getToDate());
								} else {
									reservationTimes.remove(i);
								}
							} else {
								float toDateFi = re.getToDate();
								if (re.getToDate() - re.getFromDate() < 0.5) {
									reservationTimes.remove(i);
								} else {
									re.setToDate(fromDate_);
								}
								
								if(toDate_ + 0.5f < toDateFi) {
									ReservationTime r3 = new ReservationTime();
									r3.setFromDate(toDate_);
									r3.setToDate(toDateFi);
									reservationTimes.add(r3);
								}
							}
						}
					}
				}
			}
		}
		
		/*calculate reservation_time(not have reserved_time)*/
		List<String> reTimes = new ArrayList<>();
		List<String> reTimesFinal = new ArrayList<>();
		for (int ib = 0; ib < reservationTimes.size(); ib++) {
			float totalTimeFl = reservationTimes.get(ib).getToDate() - reservationTimes.get(ib).getFromDate();
			int minuteConvert = (int) (totalTimeFl * 60);
			if ((minuteConvert == 30) && minuteConvert <= (30 + counselor.getCounselingRestTime())) {
				float fr = reservationTimes.get(ib).getFromDate();
				int firstIn =  (int)((fr-((int)fr)) * 60);
				String firstStr = ""+firstIn;
				if (firstIn < 10) {
					firstStr = "0" + firstIn;
				}
				int preIn = firstIn + 30;
				String preStr = ""+preIn;
				float fr2 = fr;
				if (preIn >= 60) {
					fr2 = (int)fr2 + 1;
					preIn = preIn - 60;
					if (preIn < 10) {
						preStr = "0" + preIn;
					}
				}
				if (fr < 10) {
					reTimes.add("0" + (int)fr + ":"+ firstStr + " ~ 0" + (int)fr2 + ":" + preStr);
				} else {
					reTimes.add("" + (int)fr + ":"+ firstStr + " ~ " + (int)fr2 + ":" + preStr);
				}
			} else {
				int lession = minuteConvert/(30 + counselor.getCounselingRestTime());
				float fr = reservationTimes.get(ib).getFromDate();
				int firstIn =  (int)((fr-((int)fr)) * 60);
				String firstStr = ""+firstIn;
				if (firstIn < 10) {
					firstStr = "0" + firstIn;
				}
				int preIn = firstIn + 30;
				String preStr = ""+preIn;
				float fr2 = fr;
				if (preIn >= 60) {
					fr2 = (int)fr2 + 1;
					preIn = preIn - 60;
					if (preIn < 10) {
						preStr = "0" + preIn;
					}
				}
				if (fr < 10) {
					reTimes.add("0" + (int)fr + ":"+ firstStr + " ~ 0" + (int)fr2 + ":" + preStr);
				} else {
					reTimes.add("" + (int)fr + ":"+ firstStr + " ~ " + (int)fr2 + ":" + preStr);
				}
				
				for (int ic = 2; ic <= lession; ic++) {
					int preIn2 = Integer.valueOf(preStr) + counselor.getCounselingRestTime();
					String preIn2Str = "" + preIn2;
					if (preIn2 < 10) {
						preIn2Str = "0" + preIn2;
					}
					
					float tr2 = fr2;
					float tr3 = 0f;  
					int preIn3 = 0;
					String preIn3Str = "" + preIn3;
					
					if (preIn2 >= 60) {
						tr2 = (int)tr2 + 1;
						preIn2 = preIn2 - 60;
						if (preIn2 < 10) {
							preIn2Str = "0" + preIn2;
						}
					}
					
					preIn3 = preIn2 + 30;
					preIn3Str = ""+preIn3;
					tr3 = (int)tr2;
					if (preIn3 >= 60) {
						tr3 = (int)tr3 + 1;
						preIn3 = preIn3 - 60;
						preIn3Str = "" + preIn3;
						if (preIn3 < 10) {
							preIn3Str = "0" + preIn3;
						}
					}
					
					preStr = preIn3Str;
					fr2 = tr3;
					
					if (tr2 < 10) {
						reTimes.add("0" + (int)tr2 + ":"+ preIn2Str + " ~ 0"+ (int)tr3 + ":" + preIn3Str);
					} else {
						reTimes.add("" + (int)tr2 + ":"+ preIn2Str + " ~ " + (int)tr3 + ":" + preIn3Str);
					}
				}
			}
			
		}
		
		/*if reservationDate is currentDate*/
		Date currentDate = new Date();
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sTime = new SimpleDateFormat("HH:mm");
		
		if(sp.format(reservationDate).equals(sp.format(currentDate))) {
			if(currentDate.getHours() <= 21) {
				currentDate.setHours(currentDate.getHours() + 2);
				for (String stt : reTimes) {
					String preTimeChr = stt.substring(0, 5);
					String currentTime = sTime.format(currentDate);
					long preTimeLong =Long.valueOf(preTimeChr.split(":")[0])*60 + Long.valueOf(preTimeChr.split(":")[1]);
					/*
					String endTimeChr = stt.substring(8);
					long endTimeLong =Long.valueOf(endTimeChr.split(":")[0])*60 + Long.valueOf(endTimeChr.split(":")[1]);
					*/
					long currentTimeLong =Long.valueOf(currentTime.split(":")[0])*60 + Long.valueOf(currentTime.split(":")[1]);
					if(currentTimeLong <= preTimeLong){
						reTimesFinal.add(stt);
					}
				}
			}
		} else {
			reTimesFinal = reTimes;
		}
		
		List<Long> list = counselingSessionService.listTimeByDate(counselor.getId(), reservationDate);
		
		
		for (int j = 0; j < reTimesFinal.size(); j++) {
			for (int i = 0; i < list.size(); i++) {
				String preTimeChr = reTimesFinal.get(j).substring(0, 5);
				long preTimeLong =Long.valueOf(preTimeChr.split(":")[0])*60 + Long.valueOf(preTimeChr.split(":")[1]);
				String endTimeChr = reTimesFinal.get(j).substring(8);
				long endTimeLong =Long.valueOf(endTimeChr.split(":")[0])*60 + Long.valueOf(endTimeChr.split(":")[1]);
				
				if (list.get(i) == preTimeLong) {
					reTimesFinal.remove(j);
				} else if ((list.get(i) > preTimeLong) && (list.get(i) <= endTimeLong)) {
					reTimesFinal.remove(j);
					if ((reTimesFinal.size() - 1) > j) {
						reTimesFinal.remove(j+1);
					}
				} else {
					
				}
			}
		}
		
		return reTimesFinal;
	}
}
