package com.sm.portal.digilocker.model;

public enum DigiLockerFileTypeEnum {

	VIDEO(1,"VIDEO"),
	AUDIO(2,"AUDIO"),
	IMAGE(3,"IMAGE"),
	PDF(4,"PDF"),
	EXCEL(5,"EXCEL"),
	DOCUMENT(6,"DOCUMENT"),
	TXT(6,"TXT"),
	UNKNOWN(7,"UNKNOWN");
	
	private int	id;
	private String status;
	private DigiLockerFileTypeEnum(int id, String status)
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
	
	static DigiLockerFileTypeEnum[] values = values();

	public static DigiLockerFileTypeEnum getStatus(int id)
	{
		for (DigiLockerFileTypeEnum value : values)
		{
			if (value.getId() == id)
				return value;
		}
		return null;
	}
}
