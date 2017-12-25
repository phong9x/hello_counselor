package org.trams.hello.web.bean.search;

public class SearchCommon {
	
	Integer page;
	
	String key;
	
	String keyWord;
	
	
	public SearchCommon(){
		page = 1;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
}
