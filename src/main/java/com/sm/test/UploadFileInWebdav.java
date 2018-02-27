package com.sm.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import com.sm.portal.constants.WebDavServerConstant;

public class UploadFileInWebdav {
	
	static Sardine sardine = SardineFactory.begin();
	public static void main(String[] args) throws IOException  {

		InputStream fis;
		try {
			fis = new FileInputStream(new File("D:\\app\\caweb_2016\\invoice\\88694930_1627286.pdf"));
			sardine.put(WebDavServerConstant.WEBDAV_SERVER_URL+"88694930_1627286.pdf", fis);
			System.out.println("file stored successfully!");
		} catch (Exception e) {	
			e.printStackTrace();
		}
		//getFileFromWebdav();
	}

	private static void getFileFromWebdav() {

		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {
			// read this file into InputStream
			inputStream = sardine.get(WebDavServerConstant.WEBDAV_SERVER_URL+"88694930_1627286.pdf");

			// write the inputStream to a FileOutputStream
			outputStream =new FileOutputStream(new File("D:/SM-Project_docs/temp/ouput/a.pdf"));
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

			System.out.println("Done!");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	    }
		
		
		
}
