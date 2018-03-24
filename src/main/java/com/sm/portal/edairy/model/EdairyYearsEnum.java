package com.sm.portal.edairy.model;

public enum EdairyYearsEnum {

	TwoThouSixteen(2016, "TwoThouSixteen"),
	TwoThouSeventeen(2017, "TwoThouSeventeen"),
	TwoThouEighteen(2018, "TwoThouEighteen"),
	TwoThouNinteen(2019, "TwoThouNinteen");
	
	int yearId;
	String yearname;
	static EdairyYearsEnum[] values = values();
	
	EdairyYearsEnum(int yearId, String yearName){
		this.yearId=yearId;
		this.yearname=yearName;
	}
	
	public int getYearId() {
		return yearId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public String getYearname() {
		return yearname;
	}
	public void setYearname(String yearname) {
		this.yearname = yearname;
	}
	public static EdairyYearsEnum getDairyYears(int yearId)
	{
		for (EdairyYearsEnum value : values)
		{
			if (value.getYearId() == yearId)
				return value;
		}
		return null;
	}
	
}
