package org.trams.hello.bean.web.counselor;

import java.util.Date;

public class PsychologicalTestCounselor {
	private Integer id;
	private Integer userId;
	private String userFullName;
	private Date requestPsychological;
	private Date effectPaymentPsychological;
	private Short statusPayment;
	private String testName;
	private String userPsychologicalTestIds;
	private String psychologicalTestIds;
	private Integer psychologicalTestCounselorId;
	private String email;
	private Date paymentPsychological;

	public Date getPaymentPsychological() {
		return paymentPsychological;
	}

	public void setPaymentPsychological(Date paymentPsychological) {
		this.paymentPsychological = paymentPsychological;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPsychologicalTestCounselorId() {
		return psychologicalTestCounselorId;
	}

	public void setPsychologicalTestCounselorId(Integer psychologicalTestCounselorId) {
		this.psychologicalTestCounselorId = psychologicalTestCounselorId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public Date getRequestPsychological() {
		return requestPsychological;
	}

	public void setRequestPsychological(Date requestPsychological) {
		this.requestPsychological = requestPsychological;
	}

	public Date getEffectPaymentPsychological() {
		return effectPaymentPsychological;
	}

	public void setEffectPaymentPsychological(Date effectPaymentPsychological) {
		this.effectPaymentPsychological = effectPaymentPsychological;
	}

	public Short getStatusPayment() {
		return statusPayment;
	}

	public void setStatusPayment(Short statusPayment) {
		this.statusPayment = statusPayment;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getPsychologicalTestIds() {
		return psychologicalTestIds;
	}

	public String getUserPsychologicalTestIds() {
		return userPsychologicalTestIds;
	}

	public void setUserPsychologicalTestIds(String userPsychologicalTestIds) {
		this.userPsychologicalTestIds = userPsychologicalTestIds;
	}

	public void setPsychologicalTestIds(String psychologicalTestIds) {
		this.psychologicalTestIds = psychologicalTestIds;
	}

	public PsychologicalTestCounselor() {
		super();
	}

	@Override
	public String toString() {
		return "PsychologicalTestCounselor [id=" + id + ", userId=" + userId + ", userFullName=" + userFullName
				+ ", requestPsychological=" + requestPsychological + ", effectPaymentPsychological="
				+ effectPaymentPsychological + ", statusPayment=" + statusPayment + ", testName=" + testName
				+ ", userPsychologicalTestIds=" + userPsychologicalTestIds + ", psychologicalTestIds="
				+ psychologicalTestIds + ", psychologicalTestCounselorId=" + psychologicalTestCounselorId + ", email="
				+ email + ", paymentPsychological=" + paymentPsychological + "]";
	}

}
