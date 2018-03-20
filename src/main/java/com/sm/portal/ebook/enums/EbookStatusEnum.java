package com.sm.portal.ebook.enums;


public enum EbookStatusEnum {
	ACTIVE(1,"ACTIVE"),
	HIDDEN(2,"HIDDEN"),
	DELETED(3,"DELETED");
	
	private int	id;
	private String status;
	private EbookStatusEnum(int id, String status)
	{
		this.id = id;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	static EbookStatusEnum[] values = values();

	public static EbookStatusEnum getStatus(int id)
	{
		for (EbookStatusEnum value : values)
		{
			if (value.getId() == id)
				return value;
		}
		return null;
	}
}
