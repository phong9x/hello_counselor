package org.trams.hello.bean.web.counselor;

import org.trams.hello.bean.jpa.CounselorEntity;

public class SuggestionCounselor {
	private String categories;
	private CounselorEntity counselorEntity;
	private String fullName;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public CounselorEntity getCounselorEntity() {
		return counselorEntity;
	}

	public void setCounselorEntity(CounselorEntity counselorEntity) {
		this.counselorEntity = counselorEntity;
	}

}
