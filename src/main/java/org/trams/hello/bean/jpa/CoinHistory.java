package org.trams.hello.bean.jpa;

import java.util.Date;

public class CoinHistory {
	private Integer id;
	private String memberName;
	private Date createDate;
	private Integer typeUse;
	private Integer point;
	private String adminName;
	private Integer memberId;
	private String reason;
	private Integer typePayment;
	
	
	
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Integer getTypePayment() {
		return typePayment;
	}
	public void setTypePayment(Integer typePayment) {
		this.typePayment = typePayment;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getTypeUse() {
		return typeUse;
	}
	public void setTypeUse(Integer typeUse) {
		this.typeUse = typeUse;
	}
	
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
}
