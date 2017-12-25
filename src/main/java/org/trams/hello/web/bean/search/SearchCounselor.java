package org.trams.hello.web.bean.search;

import java.util.Date;

public class SearchCounselor {
	
	SearchCommon common;
	
	Integer counselorFreelacer;
	
	Integer counselorInCenter;
	
	Integer counselorCenterId;
	
	Integer[] counselField;
	
	Integer counselFieldAll;
	
	String textCounselField;
	
	Integer activedOn;
	
	Integer activedOff;
	
	Integer age10;
	
	Integer age20;
	
	Integer age30;
	
	Integer age40;
	
	Integer age50;
	
	Integer aimmedYes;
	
	Integer aimmedOff;
	
	Short[] statusActive;
	
	String orderBy;



	public SearchCounselor(){
		common = new SearchCommon();
		Date now = new Date();
		common.setKey("member");
		common.setPage(1);
		orderBy = "createDate";
	}



	
}
