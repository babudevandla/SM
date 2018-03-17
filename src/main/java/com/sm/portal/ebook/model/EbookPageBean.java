package com.sm.portal.ebook.model;

public class EbookPageBean {

	private Integer userId;
	private Integer ebookId;
	private EbookPage ebookPage;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getEbookId() {
		return ebookId;
	}
	public void setEbookId(Integer ebookId) {
		this.ebookId = ebookId;
	}
	public EbookPage getEbookPage() {
		return ebookPage;
	}
	public void setEbookPage(EbookPage ebookPage) {
		this.ebookPage = ebookPage;
	}
	
	
}
