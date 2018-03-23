package com.sm.portal.uniquekeys;

public enum UniqueKeyEnum {

	USER_ID(1,"USER_ID"),
	FOLDER_ID(2,"FOLDER_ID"),
	FILES_ID(3,"FILES_ID"),
	DAIRY_ID(4,"DAIRY_ID"),
	BOOK_ID(5,"BOOK_ID");
	
	int uniqueKeyId;
	String uniqueKeyProperty;
	
	UniqueKeyEnum(int id, String property){
		this.uniqueKeyId=id;
		this.uniqueKeyProperty=property;
	}
	public int getUniqueKeyId() {
		return uniqueKeyId;
	}
	public void setUniqueKeyId(int uniqueKeyId) {
		this.uniqueKeyId = uniqueKeyId;
	}
	public String getUniqueKeyProperty() {
		return uniqueKeyProperty;
	}
	public void setUniqueKeyProperty(String uniqueKeyProperty) {
		this.uniqueKeyProperty = uniqueKeyProperty;
	}
	
	
}
