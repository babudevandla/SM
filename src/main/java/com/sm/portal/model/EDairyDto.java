package com.sm.portal.model;

import java.util.Date;

public class EDairyDto {

	private Integer dairyId;
	private String dairyname;
	private Date dairyDate;
	private String dairy_content;
	private boolean status;
	private Integer userId;
	
	public Integer getDairyId() {
		return dairyId;
	}
	public void setDairyId(Integer dairyId) {
		this.dairyId = dairyId;
	}
	public String getDairyname() {
		return dairyname;
	}
	public void setDairyname(String dairyname) {
		this.dairyname = dairyname;
	}
	public Date getDairyDate() {
		return dairyDate;
	}
	public void setDairyDate(Date dairyDate) {
		this.dairyDate = dairyDate;
	}
	public String getDairy_content() {
		return dairy_content;
	}
	public void setDairy_content(String dairy_content) {
		this.dairy_content = dairy_content;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "EDairyDto [dairyId=" + dairyId + ", dairyname=" + dairyname + ", dairyDate=" + dairyDate
				+ ", dairy_content=" + dairy_content + ", status=" + status + "]";
	}
	
}
