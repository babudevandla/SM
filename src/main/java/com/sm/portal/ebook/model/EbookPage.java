package com.sm.portal.ebook.model;

import java.io.Serializable;

public class EbookPage implements Serializable{

	private Integer pageNo;
	private String chapterName;
	private String chaperType;
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public String getChaperType() {
		return chaperType;
	}
	public void setChaperType(String chaperType) {
		this.chaperType = chaperType;
	}
	
	
	
	
}//class closing
