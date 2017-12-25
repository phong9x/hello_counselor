package org.trams.hello.bean.api;

import java.util.Date;

/**
 * @author RUBY
 *
 */
public class UserPsychologicalFileApi {
	private Integer id;
	private Integer userPsychologicalId;
	private String testName;
	private Short statusPayment;
	private String fileName;
	private String fileUrl;
	private Date createDate;
	private Date updateDate;
	private Integer psychologicalTestSettingId;

	public Integer getPsychologicalTestSettingId() {
		return psychologicalTestSettingId;
	}

	public void setPsychologicalTestSettingId(Integer psychologicalTestSettingId) {
		this.psychologicalTestSettingId = psychologicalTestSettingId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserPsychologicalId() {
		return userPsychologicalId;
	}

	public void setUserPsychologicalId(Integer userPsychologicalId) {
		this.userPsychologicalId = userPsychologicalId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Short getStatusPayment() {
		return statusPayment;
	}

	public void setStatusPayment(Short statusPayment) {
		this.statusPayment = statusPayment;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
