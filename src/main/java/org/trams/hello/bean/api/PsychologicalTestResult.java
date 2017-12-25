package org.trams.hello.bean.api;

import java.util.List;

public class PsychologicalTestResult {
	private Integer psychologicalTestSettingId;
	private String testName;
	private List<UserPsychologicalFileApi> userPsychologicalFiles;
	private Integer statusDisable;
	private List<Integer> userPsychologicalFileIds;

	public List<Integer> getUserPsychologicalFileIds() {
		return userPsychologicalFileIds;
	}

	public void setUserPsychologicalFileIds(List<Integer> userPsychologicalFileIds) {
		this.userPsychologicalFileIds = userPsychologicalFileIds;
	}

	public Integer getStatusDisable() {
		return statusDisable;
	}

	public void setStatusDisable(Integer statusDisable) {
		this.statusDisable = statusDisable;
	}

	public Integer getPsychologicalTestSettingId() {
		return psychologicalTestSettingId;
	}

	public void setPsychologicalTestSettingId(Integer psychologicalTestSettingId) {
		this.psychologicalTestSettingId = psychologicalTestSettingId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public List<UserPsychologicalFileApi> getUserPsychologicalFiles() {
		return userPsychologicalFiles;
	}

	public void setUserPsychologicalFiles(List<UserPsychologicalFileApi> userPsychologicalFiles) {
		this.userPsychologicalFiles = userPsychologicalFiles;
	}

}
