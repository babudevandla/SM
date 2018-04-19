package com.sm.portal.ebook.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Ebook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer bookId;
	private Integer userId;
	private String bookTitle;
	private String coverImage;
	private Integer bookSize;
	private Integer pageSize;
	List<EbookPage> ebookPages;
	private EbookPage defaultPage;
	
	private MultipartFile coverImg;
	
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
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
	public List<EbookPage> getEbookPages() {
		return ebookPages;
	}
	public void setEbookPages(List<EbookPage> ebookPages) {
		this.ebookPages = ebookPages;
	}
	public EbookPage getDefaultPage() {
		return defaultPage;
	}
	public void setDefaultPage(EbookPage defaultPage) {
		this.defaultPage = defaultPage;
	}
	public MultipartFile getCoverImg() {
		return coverImg;
	}
	public void setCoverImg(MultipartFile coverImg) {
		this.coverImg = coverImg;
	}
	
	
	
	
	
}//class closing
