package com.sm.portal.digilocker.model;

public enum DigiLockerFileTypeEnum {

	VIDEO(1,"VIDEO"),
	AUDIO(2,"AUDIO"),
	IMAGE(3,"IMAGE"),
	DOCUMENT(4,"DOCUMENT"),
	UNKNOWN(5,"UNKNOWN");
	
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
