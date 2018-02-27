package com.sm.portal.controller;

import java.net.URLEncoder;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sm.portal.constants.EmailSender;
import com.sm.portal.model.Users;
import com.sm.portal.model.UsersDto;
import com.sm.portal.service.UserService;

@Component
public class CommonController {

     public Users user = null;

    
	
	@Autowired protected ServletContext context;
	
	@Autowired
	public UserService userService;

	@Autowired 
	protected VelocityEngine velocityEngine;
	
	@Autowired 
	protected EmailSender emailSender;
	
	
	@ModelAttribute
	public void getLoggedUser(HttpSession session,Principal pricipal,Model model){
		if(pricipal!=null){
			user=userService.findUserByUserName(pricipal.getName());
			if(user!=null){
			}
			model.addAttribute("user", user);
		}
	}
	
	public static String SMSAuthetications(UsersDto users){
		
		//Send SMS API
		String smsUrl="https://control.msg91.com/api/sendhttp.php?";
		//Your authentication key
		String authkey = "103508AS9eQhVppQO58074482";
		//Sender ID,While using route4 sender id should be 6 characters long.
		String senderId = "ravirk";
		//define route
		String route="4";
		
		String sbPostData=PrepareAutheticationURL(users,smsUrl,authkey,route,senderId);
		smsUrl=sbPostData;
		return smsUrl;
	}
	
	@SuppressWarnings("deprecation")
	private static String PrepareAutheticationURL(UsersDto users,String smsUrl, String authkey, String route, String senderId) {

		//encoding message 
		String encoded_message=URLEncoder.encode(PrepareSMSMessage(users).toString());
		StringBuilder sbPostData=new StringBuilder(smsUrl);
		sbPostData.append("authkey="+authkey); 
		sbPostData.append("&mobiles="+users.getMobile_no());
		sbPostData.append("&message="+encoded_message);
		sbPostData.append("&route="+route);
		sbPostData.append("&sender="+senderId);

		//final string
		smsUrl = sbPostData.toString();
		return smsUrl.toString();
	}

	private static String PrepareSMSMessage(UsersDto users) {
		
		StringBuilder message=new StringBuilder();
		
		if(StringUtils.isNotEmpty(users.getDynamic_access_code())){
			message.append("Dear "+users.getFirstname()+" "+users.getLastname());
			message.append("  Your verification Dynamic Access Code is "+users.getDynamic_access_code());
		}
		
		return message.toString();
	}

	protected static String getDynamicCode() {
		String code = null;
		for(int i=0;i<1;i++){
			 code=String.valueOf( Math.round(Math.random()*1000000));
		}
		return code;
	}

	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	
	}
	
	public static Date convertStringtoDateIndDDMMyyyyhhmmss(String strDate){
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

		System.out.println("StrDate:"+strDate);
		Date date1 = null;
		try {
			// Converting the input String to Date
			date1 = df.parse(strDate);

		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		System.out.println("Date:"+date1);
		return date1;

	}

	public static Date stringtoDate(String strDate){

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("StrDate:"+strDate);
		Date date = null;
		try {
			// Converting the input String to Date
			date = df.parse(strDate);

		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		System.out.println("Date:"+date);
		return date;

	}
	
	
	public static Date stringtoSqlDate(String strDate){

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		System.out.println("StrDate:"+strDate);
		Date date = null;
		try {
			// Converting the input String to Date
			date = df.parse(strDate);

		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		System.out.println("Date:"+date);
		return date;

	}
	
	public static Date stringtoSqlDateForPropertiy(String strDate){

		DateFormat df = new SimpleDateFormat("MMM dd yyyy hh:mm:ss aa");

		System.out.println("StrDate:"+strDate);
		Date date = null;
		try {
			// Converting the input String to Date
			date = df.parse(strDate);

		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		System.out.println("Date:"+date);
		return date;

	}
	
	public static Date getDate(Integer size)
	{
		Date today = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, size);
		Date nextDate = cal.getTime();
		return nextDate;
	}
	
	public static Date getDateFromString(String dateStr) {
		  //String dateStr ="15-01-2016";
	     Date date1 = null;
	     Date convertedCurrentDate = null;
		  try {
		   date1 = new SimpleDateFormat("dd-MMM-yy").parse(dateStr);
		   System.out.println(dateStr+"\t"+date1); 
		  } catch (ParseException e) {
		   try{
		    date1 = new SimpleDateFormat("dd-MMM-yy").parse(dateStr);
		    System.out.println(dateStr+"\t"+date1); 
		   } catch (ParseException e1) {
		    try{
		     date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr);
		     System.out.println(dateStr+"\t"+date1); 
		    } catch (ParseException e2) {
		     
		    }
		   }
		  }  
		  
		   DateFormat destDf = new SimpleDateFormat("yyyy-MM-dd");
		      dateStr = destDf.format(date1);
		    //  System.out.println(dateStr);
		   
		      DateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		      try {
		    convertedCurrentDate = sdf.parse(dateStr);
		   } catch (ParseException e) {
		    e.printStackTrace();
		   }
		  return convertedCurrentDate;
		 }//getSourceDate() closing
	
	public static int parse(String str){
		Integer parsed = parseString(str);
		return parsed!=null?parsed.intValue():0;
	}
	
	public static Integer parseString(String str) {
		Integer value = null;
		if (str != null) {
			try {
				value = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				value = null;
			}
		}
		return value;
	}
}
