package com.sm.portal.digilocker.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sm.portal.digilocker.model.DigiLockerFileTypeEnum;

@Component
public class DigiLockeUtils {

	private static ResourceBundle rb = ResourceBundle.getBundle("uniquekey");
	
	static Properties prop = new Properties();
	static OutputStream output = null;
	static InputStream input = null;

	
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
	
	public synchronized Integer gerUniqueKey(HttpServletRequest request){
		int newValue=0;
		
		Properties properties = new Properties();
		FileOutputStream fos =null;
		try(InputStream input = request.getServletContext().getResourceAsStream("/WEB-INF/uniquekey.properties");){
			properties.load(input);
			String uniqueKey=properties.getProperty("uniqueId");
			newValue= Integer.parseInt(uniqueKey)+1;
			properties.setProperty("uniqueId", ""+newValue);
			fos =new FileOutputStream(request.getServletContext().getRealPath("/WEB-INF/uniquekey.properties"));
			properties.store(fos,null);
		}catch(Exception e){
			
		}
		finally{
			try {fos.close();} catch (IOException e) {e.printStackTrace();}
			properties=null;
		}
		
		return newValue;
	}//closing
	
	public String getFileType(MultipartFile multipart) {
		List<String> imageList = new ArrayList<>();
		List<String> audioList = new ArrayList<>();
		List<String> videoList = new ArrayList<>();
		List<String> documentList = new ArrayList<>();
		imageList.add("jpg");
		imageList.add("jpeg");
		imageList.add("png");
		
		audioList.add("wav");
		audioList.add("mp3");
		
		videoList.add("avi");
		videoList.add("flv");
		videoList.add("wmv");
		videoList.add("mov");
		videoList.add("mp4");
		
		documentList.add("pdf");
		documentList.add("xml");
		documentList.add("xlsx");
		documentList.add("doc");
		documentList.add("docx");
		documentList.add("json");
		documentList.add("pptx");
		documentList.add("ppt");
		documentList.add("txt");
		documentList.add("log");
		
		String fileName = multipart.getOriginalFilename();
		String fileExtention = fileName.substring(fileName.lastIndexOf(".")+1);
		if(imageList.contains(fileExtention.toLowerCase()))
			return DigiLockerFileTypeEnum.IMAGE.toString();
		else if(audioList.contains(fileExtention.toLowerCase()))
			return DigiLockerFileTypeEnum.AUDIO.toString();
		else if(videoList.contains(fileExtention.toLowerCase()))
			return DigiLockerFileTypeEnum.VIDEO.toString();
		else if(documentList.contains(fileExtention.toLowerCase()))
			return DigiLockerFileTypeEnum.DOCUMENT.toString();
		else 
			return DigiLockerFileTypeEnum.UNKNOWN.toString();
		
	}//getFileType() closing
	
	public String fileExtension(MultipartFile multipart){
		String fileName = multipart.getOriginalFilename();
		if(fileName.endsWith(".jpg") || fileName.endsWith(".png") || 
				fileName.endsWith(".jpeg") ||  fileName.endsWith(".gif")){
			return "IMG";
		}else if(fileName.endsWith(".pdf") ){
			return "PDF";
		}else if(fileName.endsWith(".xls")|| fileName.endsWith(".xlsx")){
			return "XLS";
		}else if(fileName.endsWith(".txt") ){
			return "TXT";
		}else if(fileName.endsWith(".doc") || fileName.endsWith(".docx") ){
			return "DOC"; 
		}
		else
			return "UNKNOWN"; 
	}

	

}//class closing
