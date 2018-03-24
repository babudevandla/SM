package com.sm.portal.edairy.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class DairyInfo implements Serializable{

	private Integer userId;
	private Integer year;
	private Integer dairyId;
	private String dairyName;
	private String coverImage;
	private Date lastModifiedDate;
	private List<DairyPage> pages;
	private DairyPage defaultPage;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
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
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public void setDairyId(Integer dairyId) {
		this.dairyId = dairyId;
	}
	
    	
	
}
