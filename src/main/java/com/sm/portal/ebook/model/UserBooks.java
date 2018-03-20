package com.sm.portal.ebook.model;

import java.util.List;

public class UserBooks {

	private Integer userId;
	private List<UserBook> books;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<UserBook> getBooks() {
		return books;
	}
	public void setBooks(List<UserBook> books) {
		this.books = books;
	}
	
	
	
}
