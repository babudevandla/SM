package com.sm.portal.edairy.model;

import java.util.Date;
import java.util.List;

public class DairyInfo {

	private int userId;
	private int dairyId;
	private String dairyName;
	private String coverImage;
	private Date lastModifiedDate;
	private List<DairyPage> pages;
	private DairyPage defaultPage;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getDairyId() {
		return dairyId;
	}
	public void setDairyId(int dairyId) {
		this.dairyId = dairyId;
	}
	public String getDairyName() {
		return dairyName;
	}
	public void setDairyName(String dairyName) {
		this.dairyName = dairyName;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public List<DairyPage> getPages() {
		return pages;
	}
	public void setPages(List<DairyPage> pages) {
		this.pages = pages;
	}
	public DairyPage getDefaultPage() {
		return defaultPage;
	}
	public void setDefaultPage(DairyPage defaultPage) {
		this.defaultPage = defaultPage;
	}
	
    	
	
}
