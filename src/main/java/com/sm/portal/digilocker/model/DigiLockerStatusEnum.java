package com.sm.portal.digilocker.model;


public enum DigiLockerStatusEnum {

	ACTIVE(1,"ACTIVE"),
	HIDDEN(2,"HIDDEN"),
	DELETED(3,"DELETED");
	
	private int	id;
	private String status;
	private DigiLockerStatusEnum(int id, String status)
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
	
	static DigiLockerStatusEnum[] values = values();

	public static DigiLockerStatusEnum getStatus(int id)
	{
		for (DigiLockerStatusEnum value : values)
		{
			if (value.getId() == id)
				return value;
		}
		return null;
	}
}
