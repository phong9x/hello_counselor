package org.trams.hello.bean.api;

public class ReservationTime {
	private Float fromDate;
	private Float toDate;

	public Float getFromDate() {
		return fromDate;
	}

	public void setFromDate(Float fromDate) {
		this.fromDate = fromDate;
	}

	public Float getToDate() {
		return toDate;
	}

	public void setToDate(Float toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "ReservationTime [fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}

}
