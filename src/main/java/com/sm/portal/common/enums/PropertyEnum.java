package com.sm.portal.common.enums;


public enum PropertyEnum {
	
	UPLOAD_USER_FILE_PATH("UPLOAD_USER_FILE_PATH",true,null);
	
	
	static PropertyEnum[] info = values();
	public static Boolean forName(String name) {		
        for (PropertyEnum value :info) {
            if (value.getDatabasekey().equals(name)) {
                return value.isMandatory();
            }
        }
        return false;
    }
	public static PropertyEnum getName(String name){
		for (PropertyEnum value :info) {
            if (value.getDatabasekey().equals(name)) {
            	return value;
            }
		} 
		return null;
	}

	private String databasekey = "";
	private boolean isMandatory= false;
	private String defaultvalue = "";
	
	private PropertyEnum(String databasekey,boolean isMandatory,String defaultvalue){
		this.databasekey = databasekey;
		this.defaultvalue = defaultvalue;
		this.isMandatory = isMandatory;
	}

	public String getDatabasekey() {
		return databasekey;
	}

	public void setDatabasekey(String databasekey) {
		this.databasekey = databasekey;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	
	
	
}
