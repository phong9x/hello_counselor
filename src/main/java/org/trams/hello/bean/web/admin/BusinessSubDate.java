package org.trams.hello.bean.web.admin;

public class BusinessSubDate {
	private DateRange contractDate;
	private DateRange voucherDate;
	
	public DateRange getContractDate() {
		return contractDate;
	}
	public void setContractDate(DateRange contractDate) {
		this.contractDate = contractDate;
	}
	public DateRange getVoucherDate() {
		return voucherDate;
	}
	public void setVoucherDate(DateRange voucherDate) {
		this.voucherDate = voucherDate;
	}
}
