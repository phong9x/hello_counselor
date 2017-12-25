package org.trams.hello.web.dto;

public class SelfDiagnosis {
	private String createDate;
	private Float point;

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Float getPoint() {
		return point;
	}

	public void setPoint(Float point) {
		this.point = point;
	}

	public SelfDiagnosis(String createDate, Float point) {
		super();
		this.createDate = createDate;
		this.point = point;
	}

	public SelfDiagnosis() {
		super();
	}

}
