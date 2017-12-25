package org.trams.hello.bean;

import java.util.Date;
import java.util.List;

public class FullSchedule {
private Date date;
private List<Float> reservationTime;
private Integer counselorNumber;
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}

public List<Float> getReservationTime() {
	return reservationTime;
}
public void setReservationTime(List<Float> reservationTime) {
	this.reservationTime = reservationTime;
}
public Integer getCounselorNumber() {
	return counselorNumber;
}
public void setCounselorNumber(Integer counselorNumber) {
	this.counselorNumber = counselorNumber;
}

}
