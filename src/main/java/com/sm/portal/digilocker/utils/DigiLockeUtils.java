package com.sm.portal.digilocker.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import org.springframework.stereotype.Service;

//@Service
public class DigiLockeUtils {

	private static ResourceBundle rb = ResourceBundle.getBundle("uniquekey");
	
	static Properties prop = new Properties();
	static OutputStream output = null;
	static InputStream input = null;
	public static void main(String[] args) {

		
		try{
			input = new FileInputStream("uniquekey.properties");
			prop.load(input);
			gerUniqueId();

			// get the property value and print it out
			System.out.println(prop.getProperty("uniqueId"));
		}catch(Exception e){
			e.printStackTrace();	
		}
		
		/*String browser = rb.getString("uniqueId");
        System.out.println(browser);*/
		
	}
	
	private static Integer readCurrentValue(){
		
		//return  Integer.parseInt(rb.getString("uniqueId"));
		try {
			input = new FileInputStream("../../uniquekey.properties");
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return Integer.parseInt(prop.getProperty("uniqueId"));
	}
	public synchronized static Integer gerUniqueId(){
		
		
		Integer uniqueId = null;
		
		int currentValue=readCurrentValue();
		currentValue+=1;
		updateWithNewValue(currentValue);
		output=null;
		input=null;
		return currentValue;
	}

	private static void updateWithNewValue(int currentValue) {
		prop.setProperty("uniqueId", ""+currentValue);
		try {
			prop.store(new FileOutputStream("../../uniquekey.properties"), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
