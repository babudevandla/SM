package com.sm.portal.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import com.sm.portal.constants.WebDavServerConstant;
import com.sm.portal.model.FileManagementVO;

@Service
public class FileUploadServices {

	
	static Sardine sardine = SardineFactory.begin();
	  private static final String FILE_PATTERN ="([^\\s]+(\\.(?i)(jpg|png|gif|bmp|pdf|xls))$)";
	public String uploadWebDavServer(MultipartFile multipartFile, String folderPath, HttpServletRequest request) {
		boolean status=false;
		String fileURL=null;
		String filename=multipartFile.getOriginalFilename().replaceAll(" ", "_");
		File file =new File(filename);
		System.out.println("file name::"+file.getAbsolutePath());
		InputStream fis;
		try {
			 byte[] bytes = multipartFile.getBytes();
			fis = new ByteArrayInputStream(bytes);
			String directoryPath=WebDavServerConstant.WEBDAV_SERVER_URL+folderPath;
			//unCreatedFolder(folderPath);
			if(!sardine.exists(directoryPath))
				sardine.createDirectory(directoryPath);
			
			sardine.put(directoryPath+"/"+filename, fis);
			//sardine.delete("http://104.155.27.172:8081/caweb_admin/images/actions/delete.png");
			System.out.println("file stored successfully!");
			//status=true;
			//fileURL=directoryPath+"/"+filename;
			fileURL=request.getContextPath()+WebDavServerConstant.MEDIA_URL+"?filePath="+folderPath+""+filename;
		
		} catch (Exception e) {	
			e.printStackTrace();
			fileURL=null;;
		}
		
		return fileURL;
	}

	
	public String uploadWebDavServer2(MultipartFile multipartFile, String folderPath) {
		boolean status=false;
		String fileURL=null;
		String filePathInWebDev=null;
		String filename=multipartFile.getOriginalFilename().replaceAll(" ", "_");
		File file =new File(filename);
		System.out.println("file name::"+file.getAbsolutePath());
		InputStream fis;
		try {
			 byte[] bytes = multipartFile.getBytes();
			fis = new ByteArrayInputStream(bytes);
			String directoryPath=WebDavServerConstant.WEBDAV_SERVER_URL+folderPath;
			//unCreatedFolder(folderPath);
			if(!sardine.exists(directoryPath))
				sardine.createDirectory(directoryPath);
			
			sardine.put(directoryPath+"/"+filename, fis);
			//sardine.delete("http://104.155.27.172:8081/caweb_admin/images/actions/delete.png");
			System.out.println("file stored successfully!");
			//status=true;
			//fileURL=directoryPath+"/"+filename;
			fileURL=WebDavServerConstant.MEDIA_URL+"?filePath="+folderPath+""+filename;
			filePathInWebDev=folderPath+""+filename;
		} catch (Exception e) {	
			e.printStackTrace();
			fileURL=null;;
		}
		
		return filePathInWebDev;
	}
	/*private void unCreatedFolder(String folderPath) {
		// TODO Auto-generated method stub
		
	}*/

	public List<FileManagementVO> listFilesAndFolders(Integer userId ) throws IOException {
		List<FileManagementVO> filesList= new ArrayList<>();
		 List<DavResource> res = sardine.list(WebDavServerConstant.WEBDAV_SERVER_URL+userId);
         for(DavResource dr : res){
        	 if(!dr.getName().equalsIgnoreCase(String.valueOf(userId))){
	        	 FileManagementVO managementVO=new FileManagementVO();
	             System.out.println(dr.getName()+"::"+dr.getDisplayName());
	             System.out.println(dr.getHref()+"::"+dr.getModified());
	             managementVO.setFoldername(dr.getDisplayName());
	             managementVO.setCreated_date(dr.getCreation());
	             managementVO.setUser_id(userId);
	             managementVO.setModifyDate(dr.getModified());
	             managementVO.setHref(String.valueOf(dr.getHref()));
	             String[] serverPath=WebDavServerConstant.WEBDAV_SERVER_URL.split("/");
	             System.out.println("serverPath::"+serverPath);
	             managementVO.setServerPath(serverPath[0]+"//"+serverPath[2]);
	             if(dr.getContentType().startsWith("httpd")){
	            	 managementVO.setFileType(true);
	             }else{
	            	 managementVO.setFileType(false);
	            	 managementVO.setContentLength(dr.getContentLength());
	             }
	             filesList.add(managementVO);
        	 }
         }
         
		return filesList;
	}

	public boolean deleteFileOrFolder(String filePath) {
		boolean flag;
		String directoryPath=WebDavServerConstant.WEBDAV_SERVER_URL+filePath;
		System.err.println(directoryPath);
		try {
			sardine.delete(directoryPath);
			flag=true;
			System.err.println("file deleted from given webdav localtion::"+directoryPath);
		} catch (IOException e) {
			flag=false;
			e.printStackTrace();
		}
		
		return flag;
	}

	public void createFolderName(String currentFolderPath) {
		String directoryPath=WebDavServerConstant.WEBDAV_SERVER_URL+currentFolderPath;
		System.err.println(directoryPath);
		try {
			if(!sardine.exists(directoryPath))
				sardine.createDirectory(directoryPath);
			
			System.err.println("create folder given webdav localtion::"+directoryPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream downloadFile(String filePath) {

		String directoryPath=WebDavServerConstant.WEBDAV_SERVER_URL+filePath;
		System.err.println(directoryPath);
		InputStream inputStream=null;
		try {
			inputStream =sardine.get(directoryPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return inputStream;
	}

	public void createDefaultUserIdFolder(Integer userId) throws IOException {
		String directoryPath=WebDavServerConstant.WEBDAV_SERVER_URL+"/"+userId;
		System.err.println(directoryPath);
		if(!sardine.exists(directoryPath)){
			sardine.createDirectory(directoryPath);
		}
	}

}
