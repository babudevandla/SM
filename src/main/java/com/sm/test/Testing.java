package com.sm.test;

public class Testing {

	public static void main(String[] args) {
		
		final String FILE_PATTERN ="([^\\s]+(\\.(?i)(jpg|png|gif|bmp|pdf|xls))$)";
		String file="2673_1618010.pdf";
		
		boolean type=FILE_PATTERN.endsWith(file);
	}
}
