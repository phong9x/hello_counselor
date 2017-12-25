package org.trams.hello.bean.jpa;

import java.util.Date;

public class ReservationEntity {
	private Integer id;
	private String reservationName ;
	private Integer reservationRole ;
	private String  bussiness;
	private Integer yearContract;
	private String  subBussiness;
	private String counselorName;
	private Date createDate;
	private Date startTime;
	private Date endTime;
	private String timeRemain;
	private Short status;
	private Short typeCoin;
	private Date entryCounselorTime;
	private Date entryUserTime;
	private Date exitCounselorTime;
	private Date exitUserTime;
	private Date startFactTime;
	private Date endFactTime;
	
	
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getStartFactTime() {
		return startFactTime;
	}
	public void setStartFactTime(Date startFactTime) {
		this.startFactTime = startFactTime;
	}
	public Date getEndFactTime() {
		return endFactTime;
	}
	public void setEndFactTime(Date endFactTime) {
		this.endFactTime = endFactTime;
	}
	public Date getEntryCounselorTime() {
		return entryCounselorTime;
	}
	public void setEntryCounselorTime(Date entryCounselorTime) {
		this.entryCounselorTime = entryCounselorTime;
	}
	public Date getEntryUserTime() {
		return entryUserTime;
	}
	public void setEntryUserTime(Date entryUserTime) {
		this.entryUserTime = entryUserTime;
	}
	public Date getExitCounselorTime() {
		return exitCounselorTime;
	}
	public void setExitCounselorTime(Date exitCounselorTime) {
		this.exitCounselorTime = exitCounselorTime;
	}
	public Date getExitUserTime() {
		return exitUserTime;
	}
	public void setExitUserTime(Date exitUserTime) {
		this.exitUserTime = exitUserTime;
	}
	public Short getTypeCoin() {
		return typeCoin;
	}
	public void setTypeCoin(Short typeCoin) {
		this.typeCoin = typeCoin;
	}
	public String getSubBussiness() {
		return subBussiness;
	}
	public void setSubBussiness(String subBussiness) {
		this.subBussiness = subBussiness;
	}
	public Integer getReservationRole() {
		return reservationRole;
	}
	public void setReservationRole(Integer reservationRole) {
		this.reservationRole = reservationRole;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getBussiness() {
		return bussiness;
	}
	public void setBussiness(String bussiness) {
		this.bussiness = bussiness;
	}
	public Integer getYearContract() {
		return yearContract;
	}
	public void setYearContract(Integer yearContract) {
		this.yearContract = yearContract;
	}
	public String getCounselorName() {
		return counselorName;
	}
	public void setCounselorName(String counselorName) {
		this.counselorName = counselorName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getTimeRemain() {
		return timeRemain;
	}
	public void setTimeRemain(String timeRemain) {
		this.timeRemain = timeRemain;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	
	
}
