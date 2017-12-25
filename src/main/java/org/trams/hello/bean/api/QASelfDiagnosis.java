package org.trams.hello.bean.api;

public class QASelfDiagnosis {
	private Integer questionId;
	private Integer selfDiagnosisAnswerId;
	private Float point;

	public Float getPoint() {
		return point;
	}

	public void setPoint(Float point) {
		this.point = point;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getSelfDiagnosisAnswerId() {
		return selfDiagnosisAnswerId;
	}

	public void setSelfDiagnosisAnswerId(Integer selfDiagnosisAnswerId) {
		this.selfDiagnosisAnswerId = selfDiagnosisAnswerId;
	}

}
