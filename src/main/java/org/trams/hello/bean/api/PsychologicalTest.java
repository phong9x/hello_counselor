package org.trams.hello.bean.api;

import java.util.Date;

public class PsychologicalTest {
	private String nameCouselor;
	private Integer couselorId;
	private Date requestPsychological;
	private Integer id;
	private Date effectPaymentPsychological;
	private String testName;
	private Integer fee;
	private Integer totalTest;
	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getTotalTest() {
		return totalTest;
	}

	public void setTotalTest(Integer totalTest) {
		this.totalTest = totalTest;
	}

	public String getNameCouselor() {
		return nameCouselor;
	}

	public void setNameCouselor(String nameCouselor) {
		this.nameCouselor = nameCouselor;
	}

	public Integer getCouselorId() {
		return couselorId;
	}

	public void setCouselorId(Integer couselorId) {
		this.couselorId = couselorId;
	}

	public Date getRequestPsychological() {
		return requestPsychological;
	}

	public void setRequestPsychological(Date requestPsychological) {
		this.requestPsychological = requestPsychological;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getEffectPaymentPsychological() {
		return effectPaymentPsychological;
	}

	public void setEffectPaymentPsychological(Date effectPaymentPsychological) {
		this.effectPaymentPsychological = effectPaymentPsychological;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

}
