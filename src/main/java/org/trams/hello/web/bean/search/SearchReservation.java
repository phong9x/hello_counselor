package org.trams.hello.web.bean.search;

public class SearchReservation {
	
	SearchCommon common;
	
	String startDate;
	
	String endDate;
	
	boolean individual;
	
	boolean business;
	
	Integer businessId;
	
	Integer manageYear;
	
	Integer businessSubId;
	
	boolean android;
	
	boolean iOS;
	
	boolean voucher;
	
	boolean coin;
	
	boolean resvComplete;
	
	boolean resvCancel;

	String order;
	
	
	public SearchReservation(){
		common = new SearchCommon();
		individual = false;
		business = false;
		android = false;
		iOS = false;
		voucher = false;
		coin = false;
		resvComplete = false;
		resvCancel = false;
		businessId = 0;
		manageYear = 0;
		businessSubId = 0;
		common.setKey("member");
		order = "createDate";
	}
	
	
	public SearchCommon getCommon() {
		return common;
	}

	public void setCommon(SearchCommon common) {
		this.common = common;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isIndividual() {
		return individual;
	}

	public void setIndividual(boolean individual) {
		this.individual = individual;
	}

	public boolean isBusiness() {
		return business;
	}

	public void setBusiness(boolean business) {
		this.business = business;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}


	public Integer getManageYear() {
		return manageYear;
	}

	public void setManageYear(Integer manageYear) {
		this.manageYear = manageYear;
	}


	public Integer getBusinessSubId() {
		return businessSubId;
	}

	public void setBusinessSubId(Integer businessSubId) {
		this.businessSubId = businessSubId;
	}

	public boolean isAndroid() {
		return android;
	}

	public void setAndroid(boolean android) {
		this.android = android;
	}

	public boolean isiOS() {
		return iOS;
	}

	public void setiOS(boolean iOS) {
		this.iOS = iOS;
	}

	public boolean isVoucher() {
		return voucher;
	}

	public void setVoucher(boolean voucher) {
		this.voucher = voucher;
	}

	public boolean isCoin() {
		return coin;
	}

	public void setCoin(boolean coin) {
		this.coin = coin;
	}

	public boolean isResvComplete() {
		return resvComplete;
	}

	public void setResvComplete(boolean resvComplete) {
		this.resvComplete = resvComplete;
	}

	public boolean isResvCancel() {
		return resvCancel;
	}

	public void setResvCancel(boolean resvCancel) {
		this.resvCancel = resvCancel;
	}

	public String getOrder() {
		return order;
	}
	
	public void setOrder(String order) {
		this.order = order;
	}
	
	
}
