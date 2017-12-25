package org.trams.hello.bean.api;

public class ReservationDate {
	private Long reservationDate;
	private Integer counselorScheduleSettingId;

	public Long getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Long reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Integer getCounselorScheduleSettingId() {
		return counselorScheduleSettingId;
	}

	public void setCounselorScheduleSettingId(Integer counselorScheduleSettingId) {
		this.counselorScheduleSettingId = counselorScheduleSettingId;
	}

}
