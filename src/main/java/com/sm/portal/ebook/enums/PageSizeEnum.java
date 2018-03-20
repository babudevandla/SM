package com.sm.portal.ebook.enums;

public enum PageSizeEnum {

	Eighteen(18,"Eighteen"),
	Twenty(20,"Twenty"),
	TwentyFive(25,"TwentyFive"),
	Thirty(30,"Thirty"),
	Fourty(40,"Fourty");
	
	
	private int	pageSize;
	private String pageSizeName;
	static PageSizeEnum[] values = values();
	
	private PageSizeEnum(int pageSize, String pageSizeName)
	{
		this.pageSize = pageSize;
		this.pageSizeName = pageSizeName;
	}	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getPageSizeName() {
		return pageSizeName;
	}
	public void setPageSizeName(String pageSizeName) {
		this.pageSizeName = pageSizeName;
	}
	public static PageSizeEnum getBookSizes(int pageSize)
	{
		for (PageSizeEnum value : values)
		{
			if (value.getPageSize() == pageSize)
				return value;
		}
		return null;
	}
}
