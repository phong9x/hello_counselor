package org.trams.hello.web.controller.counselor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.trams.hello.bean.Counselor;
import org.trams.hello.bean.CounselorScheduleSetting;
import org.trams.hello.bean.jpa.CounselingSessionEntity;
import org.trams.hello.bean.jpa.CounselorScheduleSettingEntity;
import org.trams.hello.business.service.CounselingSessionService;
import org.trams.hello.business.service.CounselorScheduleSettingService;
import org.trams.hello.business.service.CounselorService;
import org.trams.hello.web.common.ApplicationDefine;
import org.trams.hello.web.common.Login;
import org.trams.hello.web.common.utils.DataUtils;
import org.trams.hello.web.common.utils.JsonUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by bryanlee on 3/23/17.
 */
@Controller
@RequestMapping("/auth")
public class ScheduleController extends BaseController {

    @Autowired
    private CounselorScheduleSettingService counselorScheduleSettingService;
    @Autowired
    private CounselorService counselorService;
    @Autowired
    private CounselingSessionService counselingSessionService;

    @RequestMapping(value = "/schedule/list", method = RequestMethod.GET)
    public String index(
            HttpSession session,
            ModelMap map,
            @RequestParam(defaultValue = "0") int week
    ) throws JsonProcessingException {
        DateFormat formatFirstDayOfWeek = new SimpleDateFormat("yyyy년 MM월 dd일(E)", Locale.KOREA);
        DateFormat formatLastDayOfWeek  = new SimpleDateFormat("dd일(E)", Locale.KOREA);
        DateFormat simpleDateFormat     = new SimpleDateFormat("yyyy/MM/dd");

        int counselorId = Login.getLoggedInUser(session).getId();
        Counselor counselor = counselorService.findById(counselorId);
        StringBuilder sb = new StringBuilder();

        Calendar weekInstance = Calendar.getInstance();
        weekInstance.add(Calendar.WEEK_OF_MONTH, week);
        weekInstance.set(Calendar.DAY_OF_WEEK, 2);
        Date lowerLimit = weekInstance.getTime();
        sb.append(formatFirstDayOfWeek.format(lowerLimit));

        weekInstance.add(Calendar.WEEK_OF_MONTH, 1);
        weekInstance.set(Calendar.DAY_OF_WEEK, 1);
        Date upperLimit = weekInstance.getTime();
        sb.append(" ~ ").append(formatLastDayOfWeek.format(upperLimit));

        Map<String, Object> reservedParams = new HashMap<>();
        reservedParams.put("page"       , 1);
        reservedParams.put("size"       , Integer.MAX_VALUE);
        reservedParams.put("counselorId", counselorId);
        reservedParams.put("fromTime"   , DataUtils.parseStringFromDate(lowerLimit, DataUtils.DatePattern.YYYYMMDD.getPattern()));
        reservedParams.put("toTime"     , DataUtils.parseStringFromDate(upperLimit, DataUtils.DatePattern.YYYYMMDD.getPattern()));
        reservedParams.put("counselTypes", new Short[] {2});
        reservedParams.put("sorts"      , "startTime");
        reservedParams.put("direction"  , "ASC");
        reservedParams.put("statuses"   , new Short[] {
                ApplicationDefine.CounselingSessionStatus.NOT_COUNSELED.getCode(),
                ApplicationDefine.CounselingSessionStatus.COUNSELD.getCode(),
                ApplicationDefine.CounselingSessionStatus.COMPLETED.getCode()
        });

        Map<String, Object> params = new HashMap<>();
        params.put("page"               , 1);
        params.put("size"               , Integer.MAX_VALUE);
        params.put("counselorId"        , counselorId);
        params.put("reservationFrom"    , DataUtils.parseStringFromDate(lowerLimit, DataUtils.DatePattern.YYYYMMDD.getPattern()));
        params.put("reservationTo"      , DataUtils.parseStringFromDate(upperLimit, DataUtils.DatePattern.YYYYMMDD.getPattern()));

        Map<String, Map<String, Object>>    scheduleSettingMap      = new HashMap<>();
        Map<String, Set<Map<Float, String>>> reservedMap            = new HashMap<>();
        Map<Date, Map<String, Object>>      fullSchedule 	        = new LinkedHashMap<>();

        int hour, min;
        Page<CounselingSessionEntity> reservedSessions = counselingSessionService.filter(reservedParams);
        for (CounselingSessionEntity cse : reservedSessions.getContent()) {
            String key = simpleDateFormat.format(cse.getStartTime());

            // calculate start time
            Calendar instance = Calendar.getInstance();
            instance.setTime(cse.getStartTime());

            hour    = instance.get(Calendar.HOUR_OF_DAY);
            min     = (instance.get(Calendar.MINUTE) / 10) * 10;

            float reservedTimeConverted;
            float reservedEndTimeConverted;
            String reservedTime     = (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min);

            reservedTimeConverted   = min == 0 ? hour : hour + (float) min/100;

            // calculate end time
//            if (cse.getEndTime() != null) {
//            	instance.setTime(cse.getEndTime());
//            } else {
//                instance.add(Calendar.MINUTE, cse.getCounselingTimeType());
//            }
            
            instance.add(Calendar.MINUTE, cse.getCounselingTimeType());
            hour    = instance.get(Calendar.HOUR_OF_DAY);
            min     = (instance.get(Calendar.MINUTE) / 10) * 10;

            String reservedTimeEnd  = (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min);
            
            reservedEndTimeConverted = min == 0 ? hour : hour + (float) min/100;
            
            Map<Float, String> reservedSessionMap = new LinkedHashMap<>();
            reservedSessionMap.put(reservedTimeConverted    , reservedTime);
            reservedSessionMap.put(reservedEndTimeConverted , reservedTimeEnd);
            
            if (!reservedMap.containsKey(key)) {
                Set<Map<Float, String>> reservedTimes = new LinkedHashSet<>();
                reservedTimes.add(reservedSessionMap);
                reservedMap.put(key, reservedTimes);
            } else {
                reservedMap.get(key).add(reservedSessionMap);
            }
        }

        Page<CounselorScheduleSettingEntity> scheduleSettingEntityPage  = counselorScheduleSettingService.filter(params);

        if (!scheduleSettingEntityPage.getContent().isEmpty()) {
            Iterator<CounselorScheduleSettingEntity> iterator = scheduleSettingEntityPage.getContent().iterator();
            while (iterator.hasNext()) {
                CounselorScheduleSettingEntity schedule = iterator.next();
                String key = simpleDateFormat.format(schedule.getReservationDate());

                Map<String, Object> settingMap          = new HashMap<>();
                settingMap.put("reservationDate", schedule.getReservationDate());
                settingMap.put("onlineTime"     , schedule.getOnlineTime());
                settingMap.put("reservationTime", schedule.getReservationTime());
                settingMap.put("reservedTime"   , reservedMap.getOrDefault(key, null));

                scheduleSettingMap.put(key, settingMap);
            }
        }
        for (String i: reservedMap.keySet()) {
			if(scheduleSettingMap.get(i) == null){
				Map<String, Object> settingMap          = new HashMap<>();
				settingMap.put("reservationDate", DataUtils.getDate("yyyy/MM/dd", i));
                settingMap.put("onlineTime"     , "");
                settingMap.put("reservationTime", "");
                settingMap.put("reservedTime"   , reservedMap.getOrDefault(i, null));
				scheduleSettingMap.put(i, settingMap);
			}
		}

        for (int i = 2; i < 8; i++) {
            Calendar now = Calendar.getInstance();
            now.add(Calendar.WEEK_OF_MONTH, week);
            now.set(Calendar.DAY_OF_WEEK, i);
            fullSchedule.put(now.getTime(), scheduleSettingMap.getOrDefault(simpleDateFormat.format(now.getTime()), null));
        }

        // put sunday at last of the map
        Calendar now = Calendar.getInstance();
        now.add(Calendar.WEEK_OF_MONTH, week + 1);
        now.set(Calendar.DAY_OF_WEEK, 1);
        fullSchedule.put(now.getTime(), scheduleSettingMap.getOrDefault(simpleDateFormat.format(now.getTime()), null));

        map.put("week"              , week);
        map.put("counselor"         , counselor);
        map.put("currentWeek"       , sb.toString());
        map.put("fullSchedule"      , fullSchedule);
        map.put("fullScheduleJson"  , JsonUtils.toString(fullSchedule));
        return getView();
    }

    @RequestMapping(value = "/schedule", method = RequestMethod.PUT)
    public Object update(
            @RequestBody Map<String, Object> params,
            HttpSession session
    ) throws ParseException {
        int counselorId = Login.getLoggedInUser(session).getId();
        // update counselor
        Counselor counselor = counselorService.findById(counselorId);
        Short active = Short.parseShort(String.valueOf(params.get("status")));
        System.out.println("status:"+active);
        counselor.setStatus(active.equals((short) 1)
                ? ApplicationDefine.Counselor_Status.NormalDisplay.getCode()
                : ApplicationDefine.Counselor_Status.NormalNotDisplay.getCode());
        counselor.setCounselOnline(Short.parseShort(String.valueOf(params.get("counselOnline"))));
        counselor.setStatusPaymentCounselingUseVoucher(Integer.parseInt(String.valueOf(params.get("statusPaymentCounselingUseVoucher"))));
        counselor.setCounselingRestTime(Integer.parseInt(String.valueOf(params.get("counselingRestTime"))));
        counselor.setCounseling50Minutes(Short.parseShort(String.valueOf(params.get("counseling50Minutes"))));

        counselorService.update(counselor);

        // update scheduled settings
        List<Map<String, Object>> reservationsArray = (List<Map<String, Object>>) params.get("reservationsArray");
        List<Map<String, Object>> onlinesArray      = (List<Map<String, Object>>) params.get("onlinesArray");
        List<CounselorScheduleSetting> settings     = new ArrayList<>();

        DateFormat formatter    = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate        = new Date();

        if (reservationsArray != null && !reservationsArray.isEmpty()) {

            Iterator<Map<String, Object>> reservationIterator = reservationsArray.iterator();
            while (reservationIterator.hasNext()) {
                Map<String, Object> reservationMap = reservationIterator.next();

                String reservationKey   = reservationMap.keySet().toArray(new String[]{})[0];
                String reservationVal   = String.valueOf(reservationMap.get(reservationKey));

                Date reservationDate    = formatter.parse(reservationKey);

                CounselorScheduleSetting setting = new CounselorScheduleSetting();
                setting.setCounselorId(counselorId);
                setting.setReservationDate(reservationDate);
                setting.setReservationTime(reservationVal);

                Iterator<Map<String, Object>> onlineIterator = onlinesArray.iterator();
                while (onlineIterator.hasNext()) {
                    Map<String, Object> onlineMap = onlineIterator.next();
                    String onlineKey   = onlineMap.keySet().toArray(new String[]{})[0];

                    if (reservationKey.equalsIgnoreCase(onlineKey)) {
                        String onlineVal   = String.valueOf(onlineMap.get(onlineKey));

                        setting.setOnlineTime(onlineVal);
                        onlineIterator.remove();
                    }
                }

                settings.add(setting);
            }
        }

        if (!settings.isEmpty()) {

            for (CounselorScheduleSetting setting : settings) {

                // find existed one
                CounselorScheduleSetting existedSetting = counselorScheduleSettingService.findCounselorScheduleSettingByReservationDate(setting.getReservationDate(), counselorId);
                // existed
                if (existedSetting != null) {
                    existedSetting.setOnlineTime(setting.getOnlineTime().isEmpty() ? null : setting.getOnlineTime());
                    existedSetting.setReservationTime(setting.getReservationTime().isEmpty() ? null : setting.getReservationTime());
                    existedSetting.setUpdateDate(currentDate);

                    if (existedSetting.getReservationTime() == null) {
                        counselorScheduleSettingService.delete(existedSetting.getId());
                    } else {
                        counselorScheduleSettingService.update(existedSetting);
                    }
                }
                // new
                else {
                    if (setting.getReservationTime() != null && !setting.getReservationTime().isEmpty()) {
                        CounselorScheduleSetting scheduleSetting = new CounselorScheduleSetting();
                        scheduleSetting.setCounselorId(counselorId);
                        scheduleSetting.setOnlineTime(setting.getOnlineTime());
                        scheduleSetting.setReservationTime(setting.getReservationTime());
                        scheduleSetting.setReservationDate(setting.getReservationDate());
                        scheduleSetting.setCreateDate(currentDate);
                        scheduleSetting.setUpdateDate(currentDate);
                        counselorScheduleSettingService.create(scheduleSetting);
                    }
                }
            }
        }

        return SUCCESS();
    }

    @RequestMapping(value = "/schedule/repeat", method = RequestMethod.POST)
    public Object updateRepeat(
            @RequestParam Map<String, Object> params,
            HttpSession session
    ) throws ParseException {
        // validation
        if (params.get("upperLimit") == null) {
            return FAIL("upperLimit is missing");
        }
        if (params.get("daysOfWeek") == null) {
            return FAIL("daysOfWeek is missing");
        }
        if (params.get("currentWeek") == null) {
            return FAIL("currentWeek is missing");
        }

        params.put("counselorId", Login.getLoggedInUser(session).getId());
        counselorScheduleSettingService.repeatSchedule(params);

        return SUCCESS();
    }

    @RequestMapping(value = "/schedule/reset", method = RequestMethod.DELETE)
    public Object resetSchedule(
            HttpSession session
    ) throws ParseException {
        Integer counselorId = Login.getLoggedInUser(session).getId();
        counselorScheduleSettingService.resetSchedule(counselorId);

        return SUCCESS();
    }

}
