package org.trams.hello.bean.api;

public class LatestSelfDiagnosisResult {
	private Integer questionaireId;
	private String contentResult;
	private String userName;
	private Short typeIcon;

	public Short getTypeIcon() {
		return typeIcon;
	}

	public void setTypeIcon(Short typeIcon) {
		this.typeIcon = typeIcon;
	}

	public Integer getQuestionaireId() {
		return questionaireId;
	}

	public void setQuestionaireId(Integer questionaireId) {
		this.questionaireId = questionaireId;
	}

	public String getContentResult() {
		return contentResult;
	}

	public void setContentResult(String contentResult) {
		this.contentResult = contentResult;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
