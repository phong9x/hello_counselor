package org.trams.hello.bean.api;

import java.util.Date;

public class HelloVoucherSummary {
	private Integer id;
	private Integer userId;
	private Integer voucherId;
	private Date fromDate;
	private Date toDate;
	private Short statusUse;
	private String voucherName;
	private Date createDate;
	private Date updateDate;
	private String contentVoucher;
	private Integer isBussinessVoucher;
	private Integer isHeartVoucher;
	private Date userVoucherDate;

	public Date getUserVoucherDate() {
		return userVoucherDate;
	}

	public void setUserVoucherDate(Date userVoucherDate) {
		this.userVoucherDate = userVoucherDate;
	}

	public String getVoucherName() {
		return voucherName;
	}

	public void setVoucherName(String voucherName) {
		this.voucherName = voucherName;
	}

	public Integer getIsHeartVoucher() {
		return isHeartVoucher;
	}

	public void setIsHeartVoucher(Integer isHeartVoucher) {
		this.isHeartVoucher = isHeartVoucher;
	}

	public Integer getIsBussinessVoucher() {
		return isBussinessVoucher;
	}

	public void setIsBussinessVoucher(Integer isBussinessVoucher) {
		this.isBussinessVoucher = isBussinessVoucher;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Short getStatusUse() {
		return statusUse;
	}

	public void setStatusUse(Short statusUse) {
		this.statusUse = statusUse;
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

	public String getContentVoucher() {
		return contentVoucher;
	}

	public void setContentVoucher(String contentVoucher) {
		this.contentVoucher = contentVoucher;
	}

}
