package org.trams.hello.bean.api;

import java.util.List;

public class CompletedCounselingListByYear {
	private Integer year;
	private List<MyReservation> completedCounselingList;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<MyReservation> getCompletedCounselingList() {
		return completedCounselingList;
	}

	public void setCompletedCounselingList(List<MyReservation> completedCounselingList) {
		this.completedCounselingList = completedCounselingList;
	}

}
