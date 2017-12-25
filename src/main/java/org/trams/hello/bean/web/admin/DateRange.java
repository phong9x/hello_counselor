package org.trams.hello.bean.web.admin;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateRange {
	private Integer fromYear;
	private Integer fromMonth;
	private Integer fromDay;
	private Integer toYear;
	private Integer toMonth;
	private Integer toDay;
	
	public Integer getFromYear() {
		return fromYear;
	}
	public void setFromYear(Integer fromYear) {
		this.fromYear = fromYear;
	}
	public Integer getFromMonth() {
		return fromMonth;
	}
	public void setFromMonth(Integer fromMonth) {
		this.fromMonth = fromMonth;
	}
	public Integer getFromDay() {
		return fromDay;
	}
	public void setFromDay(Integer fromDay) {
		this.fromDay = fromDay;
	}
	public Integer getToYear() {
		return toYear;
	}
	public void setToYear(Integer toYear) {
		this.toYear = toYear;
	}
	public Integer getToMonth() {
		return toMonth;
	}
	public void setToMonth(Integer toMonth) {
		this.toMonth = toMonth;
	}
	public Integer getToDay() {
		return toDay;
	}
	public void setToDay(Integer toDay) {
		this.toDay = toDay;
	}
	
	public Date getFromDate(){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(fromYear, fromMonth - 1, fromDay);
		return calendar.getTime();
	}
	
	public Date getToDate(){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(toYear, toMonth - 1, toDay);
		return calendar.getTime();
	}
}
