package org.trams.hello.bean.api;

public class PointCouselor {
	private Float totalPoint;
	private Integer countPoint;

	public Float getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(Float totalPoint) {
		this.totalPoint = totalPoint;
	}

	public Integer getCountPoint() {
		return countPoint;
	}

	public void setCountPoint(Integer countPoint) {
		this.countPoint = countPoint;
	}

	@Override
	public String toString() {
		return "PointCouselor [totalPoint=" + totalPoint + ", countPoint=" + countPoint + "]";
	}

}
