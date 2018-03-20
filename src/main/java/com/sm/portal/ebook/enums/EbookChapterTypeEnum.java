package com.sm.portal.ebook.enums;

import com.sm.portal.digilocker.model.DigiLockerEnum;

public enum EbookChapterTypeEnum {

	CHAPTER_NAME(1,"CHAPTER_NAME"),
	PAGE_CONTENT(2,"PAGE_CONTENT");
	
	private int	id;
	private String status;
	private EbookChapterTypeEnum(int id, String status)
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
	public String getContentType() {
		return status;
	}
	public void setContentType(String status) {
		this.status = status;
	}
	
	static EbookChapterTypeEnum[] values = values();

	public static EbookChapterTypeEnum getContentType(int id)
	{
		for (EbookChapterTypeEnum value : values)
		{
			if (value.getId() == id)
				return value;
		}
		return null;
	}
}
