package com.sm.portal.edairy.model;

import java.util.Date;

public class Dairy {

	private int dairyId;
	private String name;
	private Date createdDate;
	private String status;
	
	public int getDairyId() {
		return dairyId;
	}
	public void setDairyId(int dairyId) {
		this.dairyId = dairyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
	
}
