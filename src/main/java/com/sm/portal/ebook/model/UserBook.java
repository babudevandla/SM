package com.sm.portal.ebook.model;

import java.util.Date;
import java.util.List;

public class UserBook {

	private Integer bookId;
	private String bookTitle;
	private Date createdDate;
	private String status;
	private String coverPage;
	private Integer bookSize;
	private Integer pageSize;
	private String createdBy;
	private String tagline;
	private List<BookRating> ratingList;
	
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCoverPage() {
		return coverPage;
	}
	public void setCoverPage(String coverPage) {
		this.coverPage = coverPage;
	}
	public Integer getBookSize() {
		return bookSize;
	}
	public void setBookSize(Integer bookSize) {
		this.bookSize = bookSize;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public List<BookRating> getRatingList() {
		return ratingList;
	}
	public void setRatingList(List<BookRating> ratingList) {
		this.ratingList = ratingList;
	}
	
	
}
