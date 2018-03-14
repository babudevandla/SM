package com.sm.portal.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public abstract class GenricValidator extends ValidationUtils implements Validator{
	
	
	public  boolean valueCheck(int field,int maxLength){
		String str = Integer.valueOf(field).toString();
		if(str.length() == maxLength)
			return true;
		return false;
	}
	public  boolean valueCheck(String field,int minlength,int maxLength){
		if(field !=null && (field.trim().length() >=minlength && field.trim().length()<=maxLength))
			return true;
		else 
			return false;
	}
	public  boolean valueCheck(String field,int maxLength){
		return valueCheck(field, 1,maxLength);
	}
	public  boolean strictCheck(String field,int maxLength){
		if(field !=null && (field.trim().length() >0 && field.trim().length()==maxLength))
			return true;
		else 
			return false;
	}
	
	public  boolean checkLength(String field,int maxLength){
		if(field == null)
			return true;
		return (field.trim().length() < maxLength);
	}
	
	
	public  boolean isMailIdValid(String field,int maxLen){
		String field1=field.trim();
		if(!valueCheck(field1,maxLen))
	    	  return false;
		  //Pattern p = Pattern.compile(".+@.+\\.[a-zA-Z]+");
		  //Pattern p = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		  Pattern p = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
	      Matcher m = p.matcher(field1);
	      return m.matches();
	}
	public  boolean checkIntegers(String field,int maxLen){
		String field1=field.trim();
		if(!strictCheck(field1,maxLen))
			return false;
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(field1);
		boolean fp = m.matches();
	    return m.matches();
	}
	
	public  boolean checkIntegers(String field,int minlength,int maxLen){
		String field1=field.trim();
		if(!strictCheck(field1,minlength,maxLen))
			return false;
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(field1);
		boolean fp = m.matches();
	    return m.matches();
	}
	
	public  boolean strictCheck(String field,int minlength,int maxLength){
		if(field !=null && (field.trim().length() >=minlength && field.trim().length()<=maxLength))
			return true;
		else 
			return false;
	}
}
