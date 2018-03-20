package com.sm.portal.ebook.model;

public class EbookPageDto {

	private Integer userId;
	private Integer bookId;
	private Integer pageNo;
	private String chapterName;
	private String chaperType;
	private String content;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
