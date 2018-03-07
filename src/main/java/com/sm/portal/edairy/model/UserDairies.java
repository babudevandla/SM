package com.sm.portal.edairy.model;

import java.util.List;

public class UserDairies {

	private int userId;
	private List<Dairy> dairyList;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Dairy> getDairyList() {
		return dairyList;
	}
	public void setDairyList(List<Dairy> dairyList) {
		this.dairyList = dairyList;
	}
	
	
}
