package org.trams.hello.bean;

import java.util.List;


public class PageCustom<T> {
	
	private List<T> list;
	private Integer totalPages;
	private Integer totalCount;
	private Integer current;
	private Integer size;
	private List<Object> other;
	public PageCustom(List<T> list, Integer totalCount, Integer page, Integer size) {
		super();
		this.list = list;
		this.totalCount = totalCount;
		this.current = page;
		this.size = size;
	}
	
	public PageCustom(List<T> list, Integer totalCount, Integer current, Integer size,
			List<Object> other) {
		super();
		this.list = list;
		this.totalCount = totalCount;
		this.current = current;
		this.size = size;
		this.other = other;
	}

	public List<Object> getOther() {
		return other;
	}

	public void setOther(List<Object> other) {
		this.other = other;
	}

	public Integer getTotalPages() {
		Double  page = Math.ceil((double)totalCount/size);
		return page.intValue();
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Integer getCurrent() {
		return current;
	}
	public void setCurrent(Integer current) {
		this.current = current;
	}
	
	
}
