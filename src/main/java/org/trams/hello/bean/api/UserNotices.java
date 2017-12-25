package org.trams.hello.bean.api;

import java.util.Date;

public class UserNotices {
	private String title;
	private Date createDate;
	private String content;
	private Integer id;
	private Integer isShowIcon;
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsShowIcon() {
		return isShowIcon;
	}

	public void setIsShowIcon(Integer isShowIcon) {
		this.isShowIcon = isShowIcon;
	}

}
