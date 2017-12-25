package org.trams.hello.bean.web.admin;

import java.util.Date;

public class ReservationRow {
	private Integer id;
	private String userName;
	private Integer userRoleId;
	private String  businessName;
	private Integer manageYear;
	private String  businessSubName;
	private String counselorName;
	private Date createDate;
	private Date startTime;
	private String remainTime;
	private Integer typeCoin;
	private Short status;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public Integer getManageYear() {
		return manageYear;
	}
	public void setManageYear(Integer manageYear) {
		this.manageYear = manageYear;
	}
	public String getBusinessSubName() {
		return businessSubName;
	}
	public void setBusinessSubName(String businessSubName) {
		this.businessSubName = businessSubName;
	}
	public String getCounselorName() {
		return counselorName;
	}
	public void setCounselorName(String counselorName) {
		this.counselorName = counselorName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getRemainTime() {
		return remainTime;
	}
	public void setRemainTime(String remainTime) {
		this.remainTime = remainTime;
	}
	public Integer getTypeCoin() {
		return typeCoin;
	}
	public void setTypeCoin(Integer typeCoin) {
		this.typeCoin = typeCoin;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}


}
