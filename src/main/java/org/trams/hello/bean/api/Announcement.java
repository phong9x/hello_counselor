package org.trams.hello.bean.api;

import java.util.Date;

public class Announcement {
	private String title;
	private String content;
	private Integer isShowIcon;
	private Integer id;
	private Date createDate;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsShowIcon() {
		return isShowIcon;
	}

	public void setIsShowIcon(Integer isShowIcon) {
		this.isShowIcon = isShowIcon;
	}

}
