package org.trams.hello.bean.api;

import java.util.Date;

public class UserPsychologicalTest {
	private Integer userPsychologicalId;
	private String[] nameTest;
	private Integer totalTest;
	private Integer totalMoney;
	private Date effectPaymentPsychological;

	public Date getEffectPaymentPsychological() {
		return effectPaymentPsychological;
	}

	public void setEffectPaymentPsychological(Date effectPaymentPsychological) {
		this.effectPaymentPsychological = effectPaymentPsychological;
	}

	public Integer getUserPsychologicalId() {
		return userPsychologicalId;
	}

	public void setUserPsychologicalId(Integer userPsychologicalId) {
		this.userPsychologicalId = userPsychologicalId;
	}

	public String[] getNameTest() {
		return nameTest;
	}

	public void setNameTest(String[] nameTest) {
		this.nameTest = nameTest;
	}

	public Integer getTotalTest() {
		return totalTest;
	}

	public void setTotalTest(Integer totalTest) {
		this.totalTest = totalTest;
	}

	public Integer getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}

}
