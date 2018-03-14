package com.sm.portal.edairy.model;

import java.io.Serializable;

public class EDairyPageDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer userId;
	private Integer dairyId;
	private Integer currentPageNo;
	private String pageContent;
	private Integer pageNo;
	private String content;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getDairyId() {
		return dairyId;
	}
	public void setDairyId(Integer dairyId) {
		this.dairyId = dairyId;
	}
	public Integer getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public String getPageContent() {
		return pageContent;
	}
	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
