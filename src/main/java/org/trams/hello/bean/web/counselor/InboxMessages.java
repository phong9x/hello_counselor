package org.trams.hello.bean.web.counselor;

import java.util.Date;

public class InboxMessages {
	private Integer id;
	private String userName;
	private Integer userId;
	private String userEmail;
	private String content;
	private Date receivedDate;
	private Integer messageId;
	private Integer typeUser;

	public Integer getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(Integer typeUser) {
		this.typeUser = typeUser;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	@Override
	public String toString() {
		return "InboxMessages [id=" + id + ", userName=" + userName + ", userId=" + userId + ", userEmail=" + userEmail
				+ ", content=" + content + ", receivedDate=" + receivedDate + ", messageId=" + messageId + ", typeUser="
				+ typeUser + "]";
	}

}
