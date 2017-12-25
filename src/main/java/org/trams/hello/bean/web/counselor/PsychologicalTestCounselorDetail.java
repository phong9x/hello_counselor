package org.trams.hello.bean.web.counselor;

public class PsychologicalTestCounselorDetail {
	private Integer userId;
	private String userFullName;
	private String testName;
	private Integer userPsychologicalTestId;
	private Integer psychologicalTestId;
	private Integer psychologicalTestCounselorId;
	private Integer statusResultTest;
	private Integer userPsychologicalFileId;
	private String fileName;
	private String fileUrl;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Integer getUserPsychologicalTestId() {
		return userPsychologicalTestId;
	}

	public void setUserPsychologicalTestId(Integer userPsychologicalTestId) {
		this.userPsychologicalTestId = userPsychologicalTestId;
	}

	public Integer getPsychologicalTestId() {
		return psychologicalTestId;
	}

	public void setPsychologicalTestId(Integer psychologicalTestId) {
		this.psychologicalTestId = psychologicalTestId;
	}

	public Integer getUserPsychologicalFileId() {
		return userPsychologicalFileId;
	}

	public void setUserPsychologicalFileId(Integer userPsychologicalFileId) {
		this.userPsychologicalFileId = userPsychologicalFileId;
	}

	public Integer getPsychologicalTestCounselorId() {
		return psychologicalTestCounselorId;
	}

	public void setPsychologicalTestCounselorId(Integer psychologicalTestCounselorId) {
		this.psychologicalTestCounselorId = psychologicalTestCounselorId;
	}

	@Override
	public String toString() {
		return "PsychologicalTestCounselorDetail [userId=" + userId + ", userFullName=" + userFullName + ", testName="
				+ testName + ", userPsychologicalTestId=" + userPsychologicalTestId + ", psychologicalTestId="
				+ psychologicalTestId + ", psychologicalTestCounselorId=" + psychologicalTestCounselorId
				+ ", statusResultTest=" + statusResultTest + ", userPsychologicalFileId=" + userPsychologicalFileId
				+ ", fileName=" + fileName + ", fileUrl=" + fileUrl + "]";
	}

	public Integer getStatusResultTest() {
		return statusResultTest;
	}

	public void setStatusResultTest(Integer statusResultTest) {
		this.statusResultTest = statusResultTest;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

}
