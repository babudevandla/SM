package com.sm.portal.constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SMSGateway {

	private static final Logger logger = LoggerFactory.getLogger(SMSGateway.class);
	
	@SuppressWarnings("unused")
	public static boolean SendDynamicAccessCode(String final_url) {

		boolean sms_status=true;
		//Prepare Url
		URLConnection myURLConnection=null;
		URL myURL=null;
		BufferedReader reader=null;
		
		try
		{
		    //prepare connection
		    myURL = new URL(final_url);
		    myURLConnection = myURL.openConnection();
		    myURLConnection.connect();
		    reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
		    //reading response 
		    String response;
		    while ((response = reader.readLine()) != null) 
		    //print response 
		    System.out.println(response);
		    logger.info(response);
		    //finally close connection
		    reader.close();
		} 
		catch (IOException e) 
		{ 
			logger.info("SMS Status:"+e.getLocalizedMessage());
			e.printStackTrace();
			sms_status=false;
		} 
		return sms_status;
	}

}
