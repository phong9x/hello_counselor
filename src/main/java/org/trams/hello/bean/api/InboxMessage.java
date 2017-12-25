package org.trams.hello.bean.api;

import java.util.Date;

public class InboxMessage {
	private String thumbnailUrlCounselor;
	private Integer counselorId;
	private String fullNameCounselor;
	private Date recieveDate;
	private String contentMessage;
	private Short statusMessage;
	private Integer id;
	private String emailCounselor;

	public String getEmailCounselor() {
		return emailCounselor;
	}

	public void setEmailCounselor(String emailCounselor) {
		this.emailCounselor = emailCounselor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getThumbnailUrlCounselor() {
		return thumbnailUrlCounselor;
	}

	public void setThumbnailUrlCounselor(String thumbnailUrlCounselor) {
		this.thumbnailUrlCounselor = thumbnailUrlCounselor;
	}

	public Integer getCounselorId() {
		return counselorId;
	}

	public void setCounselorId(Integer counselorId) {
		this.counselorId = counselorId;
	}

	public String getFullNameCounselor() {
		return fullNameCounselor;
	}

	public void setFullNameCounselor(String fullNameCounselor) {
		this.fullNameCounselor = fullNameCounselor;
	}

	public Date getRecieveDate() {
		return recieveDate;
	}

	public void setRecieveDate(Date recieveDate) {
		this.recieveDate = recieveDate;
	}

	public String getContentMessage() {
		return contentMessage;
	}

	public void setContentMessage(String contentMessage) {
		this.contentMessage = contentMessage;
	}

	public Short getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(Short statusMessage) {
		this.statusMessage = statusMessage;
	}

}
