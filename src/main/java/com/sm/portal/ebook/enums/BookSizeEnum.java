package com.sm.portal.ebook.enums;


public enum BookSizeEnum {


	Fifty(50,"Fifty"),
	Hundred(100,"Hundred"),
	TwoHun(200,"TwoHun"),
	FourHun(400,"FourHun"),
	SixHun(600,"SixHun"),
	EightHun(800,"EightHun"),
	Thousand(1000,"Thousand");
	
	
	private int	bookSize;
	private String bookSizeName;
	static BookSizeEnum[] values = values();
	
	private BookSizeEnum(int bookSize, String bookSizeName)
	{
		this.bookSize = bookSize;
		this.bookSizeName = bookSizeName;
	}	
	
	public int getBookSize() {
		return bookSize;
	}
	public void setBookSize(int bookSize) {
		this.bookSize = bookSize;
	}
	public String getBookSizeName() {
		return bookSizeName;
	}
	public void setBookSizeName(String bookSizeName) {
		this.bookSizeName = bookSizeName;
	}
	public static BookSizeEnum getBookSizes(int bookSize)
	{
		for (BookSizeEnum value : values)
		{
			if (value.getBookSize() == bookSize)
				return value;
		}
		return null;
	}
}
