package com.sm.portal.ebook.model;

import java.util.List;

public class UserBooks {

	private Integer userId;
	private List<BookVo> books;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<BookVo> getBooks() {
		return books;
	}
	public void setBooks(List<BookVo> books) {
		this.books = books;
	}
	
	
	
}
