package org.trams.hello.web.bean.search;

public class SearchMember {
	
	SearchCommon common;
	
	String startDate;
	
	String endDate;
	
	boolean individual;
	
	boolean enterprise;
	
	Integer businessId;
	
	boolean man;
	
	boolean women;
	
	boolean ios;
	
	boolean android;
	
	boolean otherOs;
	
	boolean age10;
	
	boolean age20;
	
	boolean age30;

	boolean age40;
	
	boolean age50;
	
	boolean statusNormal;
	
	boolean statusBan;
	
	boolean statusDormat;
	
	boolean statusWithdrawl;
	
	String order;
	
	public SearchMember(){
		common = new SearchCommon();
		individual = false;
		enterprise =false;
		startDate = null;
		endDate = null;
		common.setKey("member");
		man =false;
		women = false;
		age10 =false;
		age20 = false;
		age30 = false;
		age40 = false;
		age50 = false;
		statusBan =false;
		statusDormat = false;
		statusNormal = false;
		statusWithdrawl = false;
		android = false;
		ios = false;
		otherOs = false;
		order = "createDate";
	}

	public SearchCommon getCommon() {
		return common;
	}

	public boolean isIndividual() {
		return individual;
	}

	public void setIndividual(boolean individual) {
		this.individual = individual;
	}

	public boolean isEnterprise() {
		return enterprise;
	}

	public void setEnterprise(boolean enterprise) {
		this.enterprise = enterprise;
	}

	public boolean isMan() {
		return man;
	}

	public void setMan(boolean man) {
		this.man = man;
	}

	public boolean isWomen() {
		return women;
	}

	public void setWomen(boolean women) {
		this.women = women;
	}

	public boolean isIos() {
		return ios;
	}

	public void setIos(boolean ios) {
		this.ios = ios;
	}

	public boolean isAndroid() {
		return android;
	}

	public void setAndroid(boolean android) {
		this.android = android;
	}

	public boolean isOtherOs() {
		return otherOs;
	}

	public void setOtherOs(boolean otherOs) {
		this.otherOs = otherOs;
	}

	public boolean isAge10() {
		return age10;
	}

	public void setAge10(boolean age10) {
		this.age10 = age10;
	}

	public boolean isAge20() {
		return age20;
	}

	public void setAge20(boolean age20) {
		this.age20 = age20;
	}

	public boolean isAge30() {
		return age30;
	}

	public void setAge30(boolean age30) {
		this.age30 = age30;
	}

	public boolean isAge40() {
		return age40;
	}

	public void setAge40(boolean age40) {
		this.age40 = age40;
	}

	public boolean isAge50() {
		return age50;
	}

	public void setAge50(boolean age50) {
		this.age50 = age50;
	}

	public boolean isStatusNormal() {
		return statusNormal;
	}

	public void setStatusNormal(boolean statusNormal) {
		this.statusNormal = statusNormal;
	}

	public boolean isStatusBan() {
		return statusBan;
	}

	public void setStatusBan(boolean statusBan) {
		this.statusBan = statusBan;
	}

	public boolean isStatusDormat() {
		return statusDormat;
	}

	public void setStatusDormat(boolean statusDormat) {
		this.statusDormat = statusDormat;
	}

	public boolean isStatusWithdrawl() {
		return statusWithdrawl;
	}

	public void setStatusWithdrawl(boolean statusWithdrawl) {
		this.statusWithdrawl = statusWithdrawl;
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




	public String getOrder() {
		return order;
	}




	public void setOrder(String order) {
		this.order = order;
	}




	public Integer getBusinessId() {
		return businessId;
	}




	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	

	
	
}
