package com.sm.portal.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;

import com.sm.portal.constants.PropertyConstant;
import com.sm.portal.constants.URLCONSTANT;
import com.sm.portal.model.FileManagementVO;
import com.sm.portal.model.SMPropertites;
import com.sm.portal.model.Users;
import com.sm.portal.service.FileManagementService;
import com.sm.portal.service.PropertyService;
import com.sm.portal.service.UserService;

@RestController
@RequestMapping(URLCONSTANT.BASE_URL)
public class FileManagementController {

	private static final Logger logger = LoggerFactory.getLogger(FileManagementController.class);
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public PropertyService propertyService;
	
	@Autowired
	FileManagementService managementService;
	
	
	@GetMapping(value=URLCONSTANT.FILE_MANAGEMENT_HOME)
	public ModelAndView fileManagement(@PathVariable Integer userId, Principal principal){
		logger.debug(" show fileManagement ...");
		ModelAndView mvc = new ModelAndView("/customer/file_management");
		try{
			Users user=userService.getUserById(userId);
			
			List<FileManagementVO>	filesList=managementService.getDocumentsAndFoldesList(userId);
			
			SMPropertites propertites = propertyService.getSmPropertyByKey(PropertyConstant.UPLOAD_USER_FILE_PATH);
			String folderPath=propertites.getPropvalue() + File.separator+ userId;
				
			File folder=new File(folderPath);
			if(!folder.exists()){
				//creating folder if not exist
				folder.mkdir();
			}
			Map<String, Boolean> foldersFiles=listFilesForFolder(folder);
		    System.out.println("folders Size::"+foldersFiles.size());
		    
			mvc.addObject("user", user);
			mvc.addObject("userid", userId);
			mvc.addObject("foldersFiles", filesList);
			System.out.println(user);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return mvc;
	}
	
	

	@GetMapping(value=URLCONSTANT.FILE_MANAGEMENT_CREATE_FOLDER)
	public @ResponseBody ModelAndView createFolder(@RequestParam Integer userid,@RequestParam String foldername,
			@RequestParam Integer parentid, Principal principal){
		logger.debug(" show fileManagement ...");
		ModelAndView mvc = new ModelAndView("/customer/file_management");
		SMPropertites propertites = propertyService.getSmPropertyByKey(PropertyConstant.UPLOAD_USER_FILE_PATH);
		/*System.out.println("filePath:"+propertites.getPropvalue());
		StringBuffer folderPath=new StringBuffer(propertites.getPropvalue() + File.separator+ userid);
		String[] folder=foldername.split(":");
		if(folder.length>1){
			folderPath.append(File.separator).append(folder[0]);
			folderPath.append(File.separator).append(folder[1]);
		}else{
			folderPath.append(File.separator).append(folder[0]);
		}
		
		System.out.println("finalpath"+folderPath);
		File file = new File(folderPath.toString());
		if(!file.exists()){
			//creating folder if not exist
			file.mkdir();
		}*/
		
		managementService.uploadFolderOrFile(userid,foldername,parentid,principal);
		
		return mvc;
	}
	
	@PostMapping(value=URLCONSTANT.FILE_MANAGEMENT_UPLOAD_FILES)
	public ModelAndView uploadFiles(Principal principal){
		logger.debug(" show fileManagement ...");
		
		ModelAndView mvc = new ModelAndView("/customer/file_management");
		SMPropertites propertites = propertyService.getSmPropertyByKey(PropertyConstant.UPLOAD_USER_FILE_PATH);
		
		Users user=userService.findUserByUserName(principal.getName());
	    
		mvc.addObject("user", user);
		System.out.println(user);
		return mvc;
	}
	
	@GetMapping(value=URLCONSTANT.FILE_MANAGEMENT_OPEN_FOLDER)
	public  ModelAndView openFolder(@RequestParam Integer userid,@RequestParam String foldername, Principal principal){
		logger.debug(" show fileManagement ...");
		ModelAndView mvc = new ModelAndView("/customer/file_management");
		StringBuffer navigatePath=new StringBuffer();
		SMPropertites propertites = propertyService.getSmPropertyByKey(PropertyConstant.UPLOAD_USER_FILE_PATH);
		System.out.println("filePath:"+propertites.getPropvalue());
		StringBuffer folderPath=new StringBuffer(propertites.getPropvalue() + File.separator+ userid);
		
		String[] folderStr=foldername.split(":");
		if(folderStr.length>1){
			for(int i=0;i<folderStr.length;i++){
				folderPath.append(File.separator).append(folderStr[i]);
				navigatePath.append(folderStr[i]);
				if(folderStr.length>navigatePath.length())
					navigatePath.append(":");
			}
		}else{
			folderPath.append(File.separator).append(folderStr[0]);
			navigatePath.append(folderStr[0]);
		}
		System.out.println("finalpath"+folderPath);
		File folder=new File(folderPath.toString());
		if(!folder.exists()){
			//creating folder if not exist
			folder.mkdir();
		}
		Map<String, Boolean> foldersFiles=listFilesForFolder(folder);
		mvc.addObject("foldersFiles", foldersFiles);
	    mvc.addObject("navigatePath", navigatePath);
	    mvc.addObject("userid", userid);
		return mvc;
	}
	
	private Map<String, Boolean> listFilesForFolder(File folder) {
		Map<String, Boolean> map=new HashMap<>();
		if(StringUtils.isNotBlank(folder.getName())){
			for (final File fileEntry : folder.listFiles()) {
		        if (fileEntry.isDirectory()) {
		           // listFilesForFolder(fileEntry);
		            map.put(fileEntry.getName(),false);
		        } else {
		            System.out.println(fileEntry.getName());
		            map.put(fileEntry.getName(), true);
		        }
		    }
		}
		return map;
	}
	
	
	@RequestMapping(value = URLCONSTANT.FILE_MANAGEMENT_VIEW_FILE, method = RequestMethod.GET)
	public void downloadExcel(@RequestParam Integer userid,@RequestParam("filename") String filename,
			HttpServletRequest request, HttpServletResponse response,
			Locale locale) throws IOException {
		if(logger.isInfoEnabled()) logger.info("AdminReportsController ::: downloadExcel ::: Start");
		ModelAndView mav = new ModelAndView();
		try {
			SMPropertites propertites = propertyService.getSmPropertyByKey(PropertyConstant.UPLOAD_USER_FILE_PATH);
			System.out.println("filePath:"+propertites.getPropvalue());
			StringBuffer folderPath=new StringBuffer(propertites.getPropvalue() + File.separator+ userid);
			
			String targetFile =folderPath+filename;
			File myFile = new File(targetFile);
			
			// download excel file
	        response.setContentType("application/octet-stream");
			response.setContentLength((int) myFile.length());
			response.setHeader("Content-Disposition","inline; filename="+filename);
			FileCopyUtils.copy(new FileInputStream(myFile),response.getOutputStream());
		}catch(Exception e){
			logger.error("Unable to download excel file "+e.getLocalizedMessage());
			mav.addObject("MESSAGE","Unable to download orderdump excel file Reason is : "+(e.getMessage()));
		}
		if(logger.isInfoEnabled()) logger.info("AdminReportsController ::: downloadExcel ::: End");
	
	}
	
}
