package org.trams.hello.business.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by bryanlee on 4/21/17.
 */
public class PsyTestPaymentAddedEvent extends ApplicationEvent {

	private Integer memberId;
	private Integer counselorId;
	private Integer psyId;
	private String testSite;

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getCounselorId() {
		return counselorId;
	}

	public void setCounselorId(Integer counselorId) {
		this.counselorId = counselorId;
	}

	public Integer getPsyId() {
		return psyId;
	}

	public void setPsyId(Integer psyId) {
		this.psyId = psyId;
	}

	public String getTestSite() {
		return testSite;
	}

	public void setTestSite(String testSite) {
		this.testSite = testSite;
	}

	public PsyTestPaymentAddedEvent(Object source, Integer memberId, Integer counselorId, Integer psyId,
			String testSite) {
		super(source);
		this.memberId = memberId;
		this.counselorId = counselorId;
		this.psyId = psyId;
		this.testSite = testSite;
	}

}
