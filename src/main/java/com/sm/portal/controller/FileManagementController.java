package com.sm.portal.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sm.portal.constants.URLCONSTANT;
import com.sm.portal.constants.WebDavServerConstant;
import com.sm.portal.digilocker.model.DigiLockerAddressBar;
import com.sm.portal.digilocker.model.DigiLockerEnum;
import com.sm.portal.digilocker.model.DigiLockerStatusEnum;
import com.sm.portal.digilocker.model.FilesInfo;
import com.sm.portal.digilocker.model.FolderInfo;
import com.sm.portal.digilocker.model.GalleryDetails;
import com.sm.portal.digilocker.service.DigilockerService;
import com.sm.portal.digilocker.utils.DigiLockeUtils;
import com.sm.portal.ebook.model.EbookPageDto;
import com.sm.portal.edairy.model.EdairyActionEnum;
import com.sm.portal.model.Users;
import com.sm.portal.service.FileManagementService;
import com.sm.portal.service.FileUploadServices;
import com.sm.portal.service.PropertyService;
import com.sm.portal.service.UserService;

@RestController
@RequestMapping(URLCONSTANT.BASE_URL)
public class FileManagementController  extends CommonController{

	private static final Logger logger = LoggerFactory.getLogger(FileManagementController.class);
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public PropertyService propertyService;
	
	@Autowired
	FileManagementService managementService;
	
	@Autowired
	FileUploadServices fileUploadServices;
	
	@Autowired
	DigilockerService digilockerService;
	
	@Autowired
	DigiLockeUtils digiLockerUtils;
	
	@GetMapping(value=URLCONSTANT.FILE_MANAGEMENT_HOME)
	public ModelAndView getDigiLockerHomeData(@PathVariable Integer userId,@RequestParam(name="message",required=false) String message,
			Principal principal,HttpServletRequest request){
		logger.debug(" show fileManagement ...");
		
		ModelAndView mvc = new ModelAndView("/customer/file_management");
		try{
			//Users user=userService.getUserById(userId);
			List<FolderInfo>	allFolderList=digilockerService.getDigiLockerHomeData(new Long(userId));
			
			HttpSession httpSession=request.getSession(true);
			httpSession.setAttribute("allFoldersData", allFolderList);
			httpSession.setAttribute("userid", userId);
			List<FolderInfo>	rootFolderList=digilockerService.getRootFoldersList(allFolderList);
			mvc.addObject("digiLockerHomeData", rootFolderList);
			List<FolderInfo> galleryData=rootFolderList.stream().filter(folder -> folder.origin.equals("GALLERY")).collect(Collectors.toList());
			mvc.addObject("galleryData", galleryData);
			System.out.println(user);	
			mvc.addObject("WEBDAV_SERVER_URL", WebDavServerConstant.WEBDAV_SERVER_URL);
		}catch(Exception e){
			e.printStackTrace();
		}
		//mvc.addObject("currentFolderPath", "home");
		mvc.addObject("digiLockActive", true);
		return mvc;
	}//closing getDigiLockerHomeData() 
	
	
	
	@GetMapping(value="/getfolderinfo/{fid}")
	public ModelAndView getFolderInfo(@PathVariable Integer fid,Principal principal,HttpServletRequest request){
		logger.debug(" show fileManagement ...");
		
		ModelAndView mvc = new ModelAndView("/customer/file_management");
		HttpSession httpSession=request.getSession(true);
		@SuppressWarnings("unchecked")
		List<FolderInfo> allFolderList =(List<FolderInfo>) httpSession.getAttribute("allFoldersData");
		try{
						
			FolderInfo	folderInfo=digilockerService.getFolderInfo(allFolderList,fid);
			List<DigiLockerAddressBar> addressBar = this.getAddressBar(folderInfo, allFolderList);
			mvc.addObject("folderInfo", folderInfo);
			mvc.addObject("currentFolderPath", folderInfo.getFolderPath());
			mvc.addObject("isInternalFolder", "Yes");
			mvc.addObject("addressBar",addressBar);
			
			
			//fileUploadServices.createFolderName(folderInfo.getFolderPath());
			mvc.addObject("digiLockActive", true);
			mvc.addObject("WEBDAV_SERVER_URL", WebDavServerConstant.WEBDAV_SERVER_URL);
			//System.out.println(user);			
		}catch(Exception e){e.printStackTrace();}
		
		return mvc;
	}//closing getDigiLockerHomeData() 
	
	
	@GetMapping(value="/downloadFile/{fid}")
	public void downloadFile(@PathVariable Integer fid,@RequestParam String filePath,Principal principal,HttpServletRequest request
			,HttpServletResponse response){
		
		/*//ModelAndView mvc = new ModelAndView("/customer/file_management");
		HttpSession httpSession=request.getSession(true);
		@SuppressWarnings("unchecked")
		List<FolderInfo> allFolderList =(List<FolderInfo>) httpSession.getAttribute("allFoldersData");
		  FolderInfo	folderInfo=digilockerService.getFolderInfo(allFolderList,fid);
			List<DigiLockerAddressBar> addressBar = this.getAddressBar(folderInfo, allFolderList);
			mvc.addObject("folderInfo", folderInfo);
			mvc.addObject("currentFolderPath", folderInfo.getFolderPath());
			mvc.addObject("isInternalFolder", "Yes");
			mvc.addObject("addressBar",addressBar);*/
			String fileName =filePath.substring(filePath.lastIndexOf("/"));
		try{
						
			InputStream	inputStream=fileUploadServices.downloadFile(filePath);
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			 /*BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
			 byte[] buffer = new byte[1024];
		      int bytesRead = 0;
		      while ((bytesRead = inputStream.read(buffer)) != -1) {
		        outStream.write(buffer, 0, bytesRead);
		      }
		      outStream.flush();
		      inputStream.close();*/
			FileCopyUtils.copy(inputStream, response.getOutputStream());
			//mvc.setViewName("redirect:/sm/getfolderinfo/"+fid);
			//System.out.println(user);			
		}catch(Exception e){e.printStackTrace();}
		
		//return mvc;
	}//closing getDigiLockerHomeData() 
	
	
	
	
	
	
	private List<DigiLockerAddressBar> getAddressBar(FolderInfo folderInfo, List<FolderInfo> allFolderList) {
		String currentFolderPath = folderInfo.getFolderPath();
		List<DigiLockerAddressBar> addressBar=new ArrayList<>();
		String[] folderIdArray=currentFolderPath.split("/");
		DigiLockerAddressBar addBar = null;
		for(String folderId:folderIdArray){
			for(FolderInfo folder:allFolderList){
				if(!folderId.equals("") &&new Long(""+folder.getfId()).intValue()==Long.parseLong(folderId)){
					addBar = new DigiLockerAddressBar();
					addBar.setFolderId(new Integer(folderId));
					addBar.setFolderName(folder.getfName());
					addressBar.add(addBar);
					break;
				}//if closing
			}//for closing
		}//for closing
		
		
		return addressBar;
	}//getAddressBar() closing



	@GetMapping(value=URLCONSTANT.FILE_MANAGEMENT_CREATE_FOLDER)
	public @ResponseBody ModelAndView createFolder(@RequestParam Integer userid,@RequestParam String foldername,
			Principal principal,@RequestParam String currentFolderPath,HttpServletRequest request){
		logger.debug(" show fileManagement ...");
		HttpSession httpSession=request.getSession(true);
		ModelAndView mvc = new ModelAndView();
		Integer currentFolderId=null;
		if(StringUtils.isNotBlank(currentFolderPath)){
			String strTemp1=currentFolderPath.substring(0, currentFolderPath.length()-1);
			currentFolderId = Integer.parseInt(strTemp1.substring(strTemp1.lastIndexOf('/')+1));
			
			fileUploadServices.createFolderName(currentFolderPath);
		}else{
			currentFolderId=0;
			fileUploadServices.createFolderName("/"+userid+"/"+currentFolderId+"/");
		}
		
		
		@SuppressWarnings("unchecked")
		List<FolderInfo> allFolderList =(List<FolderInfo>) httpSession.getAttribute("allFoldersData");
		FolderInfo	currentFolderInfo=digilockerService.getFolderInfo(allFolderList,currentFolderId);
		
		//currentFolderPath=currentFolderInfo.getFolderPath();
		
		
		FolderInfo newFolder =new FolderInfo();
		newFolder.setfId(gerUniqueKey(request));
		newFolder.setfName(foldername);
		newFolder.setParentId(currentFolderId);
		newFolder.setFolderNamePath(currentFolderInfo.getFolderNamePath()+"/"+foldername);
		newFolder.setFolderPath(currentFolderInfo.getFolderPath()+newFolder.getfId()+"/");
		newFolder.setFolderStatus(DigiLockerStatusEnum.ACTIVE.toString());
		newFolder.setChildFolders(null);
		newFolder.setLocalFilesInfo(null);
		newFolder.setOrigin(DigiLockerEnum.LOCKER.toString());
		digilockerService.storeNewFileOrFolderInfo(newFolder, new Integer(""+newFolder.getfId()), userid);
		
		List<FolderInfo>	getUpdatedFolderList=digilockerService.getDigiLockerHomeData(new Long(userid));
		httpSession.setAttribute("allFoldersData", getUpdatedFolderList);
		httpSession.setAttribute("userid", userid);
		mvc.addObject("digiLockActive", true);
		mvc.setViewName("redirect:/sm/getfolderinfo/"+currentFolderId);
		return mvc;
	}
	
	@PostMapping(value=URLCONSTANT.FILE_MANAGEMENT_UPLOAD_FILES)
	public ModelAndView uploadFiles(@RequestParam("fileName") MultipartFile multipart,@RequestParam Integer userid,@RequestParam String folderPath,
			@RequestParam Integer folderId,Principal principal,
			RedirectAttributes redirectAttributes, HttpServletRequest request){
		logger.debug(" show fileManagement ...");
		
		ModelAndView mvc = new ModelAndView();
		//Users user=userService.findUserByUserName(principal.getName());
		String fileName = multipart.getOriginalFilename();
		String filePath = folderPath+fileName.replaceAll(" ", "_");
		
		String fileURL=fileUploadServices.uploadWebDavServer(multipart,folderPath);
		String fileType =digiLockerUtils.getFileType(multipart);
		String fileExtension=fileName.substring(fileName.lastIndexOf(".")+1);;
		if(fileURL!=null){
			mvc.addObject("message","file uploaded successfully!");
			FilesInfo newFileInfo = new FilesInfo();
			newFileInfo.setFileId(gerUniqueKey(request));
			newFileInfo.setFileName(fileName);
			newFileInfo.setDumy_filename(fileName.replaceAll(" ", "_"));
			newFileInfo.setFilePath(filePath);
			newFileInfo.setFileStatus(DigiLockerStatusEnum.ACTIVE.toString());
			newFileInfo.setCreateddate(new Date());
			newFileInfo.setStatusAtGallery(DigiLockerStatusEnum.ACTIVE.toString());
			newFileInfo.setFileType(fileType);
			newFileInfo.setFileExtension(fileExtension);
			FolderInfo newFolder = new FolderInfo();
			
			List<FilesInfo> localFilesInfo = new ArrayList<>();
			localFilesInfo.add(newFileInfo);
			newFolder.setLocalFilesInfo(localFilesInfo);
			
			digilockerService.storeNewFileOrFolderInfo(newFolder, folderId, userid);
		}
		List<FolderInfo>	allFolderList=digilockerService.getDigiLockerHomeData(new Long(userid));
		
		HttpSession httpSession=request.getSession(true);
		httpSession.setAttribute("allFoldersData", allFolderList);
		httpSession.setAttribute("userid", userid);
		
		mvc.setViewName("redirect:/sm/getfolderinfo/"+folderId);
		System.out.println(user);
		return mvc;
	}
	
	

	private Integer getNewId() {
		// TODO Auto-generated method stub
		return null;
	}


	
//http://localhost:8092/SM_Portal/sm/deletefile?folderId=10004&userid=101&action=Delete&fileId=506
	@GetMapping(value=URLCONSTANT.DIGILOCKER_DELETE_OR_HIDE_FILE)
	public ModelAndView deleteOrHideFile(@RequestParam Integer folderId,@RequestParam Integer parentId,@RequestParam String deleteInfo,@RequestParam(required=false) Integer fileId,@RequestParam Integer userid, @RequestParam String action,
			Principal principal,RedirectAttributes redirectAttributes, HttpServletRequest request){
		logger.debug(" delete file or folders ...");
		ModelAndView mvc = new ModelAndView();
		if(deleteInfo.equals("File"))folderId=parentId;
		//SMPropertites propertites = propertyService.getSmPropertyByKey(PropertyConstant.UPLOAD_USER_FILE_PATH);
		//Users user=userService.findUserByUserName(principal.getName());
		//boolean deleteStatus;
		try {
			//deleteStatus = fileUploadServices.deleteFileOrFolder(filePath);
			//if(deleteStatus){
				digilockerService.updateFileOrFolderSatus(deleteInfo,action,folderId, fileId,userid);
				mvc.addObject("message","file deleted successfully!");
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.reseteFolderInfoInSession(request, userid);
		mvc.setViewName("redirect:/sm/getfolderinfo/"+parentId);
		mvc.addObject("digiLockActive", true);
		return mvc;
	}//deleteFile() closing
	
	
	private void reseteFolderInfoInSession(HttpServletRequest request, Integer userid) {

		List<FolderInfo>	allFolderList=digilockerService.getDigiLockerHomeData(new Long(userid));
		
		HttpSession httpSession=request.getSession(true);
		httpSession.setAttribute("allFoldersData", allFolderList);
		httpSession.setAttribute("userid", userid);
	}//reseteFolderInfoInSession() closing



	//http://localhost:8092/SM_Portal/sm/deletefolder?folderId=10004&userid=101&action=Delete
	@GetMapping(value=URLCONSTANT.DIGILOCKER_DELETE_OR_HIDE_FOLDER)
	public ModelAndView deleteOrHideFolder(@RequestParam Integer folderId,@RequestParam Integer userid,@RequestParam(name="filePath",required=false) String filePath, 
			@RequestParam String action,Principal principal,RedirectAttributes redirectAttributes, HttpServletRequest request){
		logger.debug(" delete file or folders ...");
		ModelAndView mvc = new ModelAndView();
		//SMPropertites propertites = propertyService.getSmPropertyByKey(PropertyConstant.UPLOAD_USER_FILE_PATH);
		Users user=userService.findUserByUserName(principal.getName());
		boolean deleteStatus;
		try {
			//deleteStatus = fileUploadServices.deleteFileOrFolder(filePath);
			//if(deleteStatus){
				digilockerService.updateFileOrFolderSatus("Folder",action,folderId, null,userid);
				mvc.addObject("message","file deleted successfully!");
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.reseteFolderInfoInSession(request, userid);
		mvc.addObject("digiLockActive", true);
		mvc.setViewName("redirect:/sm/getfolderinfo/"+folderId);
		
		return mvc;
	}//deleteFile() closing

	//http://localhost:8092/SM_Portal/sm/showHiddenFoldersAndFiles/10000?userid=101
	@GetMapping(value="/showHiddenFoldersAndFiles/{fid}")
	public ModelAndView showHiddenFoldersAndFiles(@PathVariable Integer fid,@RequestParam Integer userid,Principal principal,HttpServletRequest request){
		logger.debug(" show fileManagement ...");
		
		ModelAndView mvc = new ModelAndView();
		
		try{
						
			digilockerService.showHiddenFoldersAndFiles(fid, userid);
			mvc.addObject("isInternalFolder", "Yes");
			//System.out.println(user);			
		}catch(Exception e){e.printStackTrace();}
		
		this.reseteFolderInfoInSession(request, userid);
		mvc.setViewName("redirect:/sm/getfolderinfo/"+fid);
		return mvc;
	}//closing getDigiLockerHomeData() 
	
	@GetMapping(value="/openGallery/{fid}")
	public ModelAndView openGallery(@PathVariable Integer fid,@RequestParam Integer userid,Principal principal,HttpServletRequest request){
		ModelAndView mvc = new ModelAndView("/customer/gallery_content");
		
		try {
			List<GalleryDetails> gallerylist = digilockerService.getGallerContent(userid, null,null);
			mvc.addObject("galleryContent", gallerylist);
			mvc.addObject("fileType", "ALL");
			mvc.addObject("fid", fid);
			mvc.addObject("userid", userid);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		mvc.addObject("digiLockActive", true);
		return mvc;
	}//openGallery() closing
	
	
	@GetMapping(value="/getGallerContent")
	public ModelAndView getGallerContent(@RequestParam Integer userid,Principal principal,
			@RequestParam(name="filesType", required=false) String filesType,@RequestParam(name="fileStatus", required=false) String fileStatus
			,HttpServletRequest request){
		ModelAndView mvc = new ModelAndView("/customer/gallery_content");
		try {
			List<GalleryDetails> gallerylist = digilockerService.getGallerContent(userid, filesType,fileStatus);
			mvc.addObject("galleryContent", gallerylist);
			mvc.addObject("fileType", filesType);
			mvc.addObject("userid", userid);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		mvc.addObject("digiLockActive", true);
		return mvc;
	}//getGallerContent() closing
	
	
	@RequestMapping(value = "/storeFilesFromGallery", method = RequestMethod.POST)
	public ModelAndView  storeFilesFromGallery(@RequestParam("userId") Integer userId,
			 @RequestParam("folderId") Integer folderId,
			 @RequestParam("files") MultipartFile multipartList[],
			 HttpServletRequest request) {
		FolderInfo gallery =digilockerService.getGalleryDetails(userId);
		String fileURL=null;
		List<FilesInfo> newFileList = new ArrayList<>();
		FilesInfo filesInfo = null;
		for (int i=0;i<multipartList.length;i++) {	
            if (!multipartList[i].isEmpty()) {
            	fileURL =fileUploadServices.uploadWebDavServer(multipartList[i], gallery.getFolderPath());
            	if(fileURL!=null){
	            	filesInfo =new FilesInfo();
	            	String fileName = multipartList[i].getOriginalFilename();
	        		//String filePath = multipartList[i]+fileName.replaceAll(" ", "_");
	            	filesInfo.setFileId(digiLockerUtils.gerUniqueKey(request));
	            	filesInfo.setFileName(fileName);
	            	filesInfo.setDumy_filename(fileName.replaceAll(" ", "_"));
	            	//String filePath = gallery.getFolderPath()+fileName.replaceAll(" ", "_");
	            	filesInfo.setFilePath(gallery.getFolderPath()+fileName.replaceAll(" ", "_"));
	            	filesInfo.setFileStatus(DigiLockerStatusEnum.ACTIVE.toString());
	            	filesInfo.setCreateddate(new Date());
	            	filesInfo.setStatusAtGallery(DigiLockerStatusEnum.ACTIVE.toString());
	            	filesInfo.setFileType(digiLockerUtils.getFileType(multipartList[i]));
	            	newFileList.add(filesInfo);
            	}//if closing
            }//if closing
		}//for closing
		if(newFileList.size()>0){
			gallery.setLocalFilesInfo(newFileList);
			digilockerService.storeNewFileOrFolderInfo(gallery, gallery.getFolderId(), userId);
		}
		ModelAndView mvc= new ModelAndView();
		
		mvc.setViewName("redirect:/sm/getGallerContent?userid="+userId);
		
		return mvc;
	}//storeFilesInGalleryFromEbook() closing
	
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
	
	


	
}//class closing
