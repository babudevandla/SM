package com.sm.portal.edairy.model;

import com.sm.portal.digilocker.model.DigiLockerStatusEnum;

public enum EdairyStatusEnum {

	ACTIVE(1,"ACTIVE"),
	HIDDEN(2,"HIDDEN"),
	DELETED(3,"DELETED");
	
	private int	id;
	private String status;
	private EdairyStatusEnum(int id, String status)
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
	
	static EdairyStatusEnum[] values = values();

	public static EdairyStatusEnum getStatus(int id)
	{
		for (EdairyStatusEnum value : values)
		{
			if (value.getId() == id)
				return value;
		}
		return null;
	}
}
