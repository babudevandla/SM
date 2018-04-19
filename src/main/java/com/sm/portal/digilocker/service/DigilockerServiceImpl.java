package com.sm.portal.digilocker.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sm.portal.digilocker.model.DigiLockerEnum;
import com.sm.portal.digilocker.model.DigiLockerStatusEnum;
import com.sm.portal.digilocker.model.FilesInfo;
import com.sm.portal.digilocker.model.FolderInfo;
import com.sm.portal.digilocker.model.GalleryDetails;
import com.sm.portal.digilocker.mongo.dao.DigiLockerMongoDao;
import com.sm.portal.digilocker.utils.DigiLockeUtils;
import com.sm.portal.service.FileUploadServices;
import com.sm.portal.uniquekeys.UniqueKeyDaoImpl;
import com.sm.portal.uniquekeys.UniqueKeyEnum;

@Service
public class DigilockerServiceImpl implements DigilockerService{

	@Autowired
	DigiLockerMongoDao digiLockerMongoDao;
	
	@Autowired
	FileUploadServices fileUploadServices;
	
	@Autowired
	DigilockerService digilockerService;
	
	@Autowired
	UniqueKeyDaoImpl uniqueKeyDaoImpl;
	
	@Autowired
	DigiLockeUtils digiLockerUtils;
	
	
	@Override
	public List<FolderInfo> getDigiLockerHomeData(Long userId) {
		List<FolderInfo> folderList =digiLockerMongoDao.getFolderInfo(userId);
		return folderList;
	}//getDigiLockerHomeData() closing


	@Override
	public List<FolderInfo> getRootFoldersList(List<FolderInfo> allFolderList) {
		List<FolderInfo> rootFolders=allFolderList.stream().filter(folder->folder.parentId == 0).collect(Collectors.toList());
		return rootFolders;
	}//getRootFoldersList() closing


	@Override
	public FolderInfo getFolderInfo(List<FolderInfo> allFolderList, Integer folderId) {
		List<FolderInfo> rootFolders=allFolderList.stream().filter(folder->folder.folderId == folderId.intValue()).collect(Collectors.toList());
		FolderInfo rootFolderInfo =null;
		if(rootFolders!=null&& rootFolders.size()>0){
			rootFolderInfo=rootFolders.get(0);
		
			List<FolderInfo> childFolders=allFolderList.stream().filter(folder->folder.parentId == folderId.intValue()).collect(Collectors.toList());
			rootFolderInfo.setChildFolders(childFolders);
		}
		return rootFolderInfo;
	}//getFolderInfo() closing


	@Override
	public void storeNewFileOrFolderInfo(FolderInfo newFolderInfo, Integer folderId, Integer userId) {
		digiLockerMongoDao.storeNewFileOrFolderInfo(newFolderInfo,folderId, userId);
	}//storeFileInfoInDB() closing
	@Override
	public void storeFilesInGallery(FolderInfo newFolderInfo, Integer userId){
		digiLockerMongoDao.storeFilesInGallery(newFolderInfo, userId);
	}//storeFilesInGallery() closing
	
	@Override
	public void updateFileOrFolderSatus(String deleteInfo, String action, Integer folderId,Integer fileId,Integer userId) {
		digiLockerMongoDao.updateFileOrFolderSatus(deleteInfo,action,folderId, fileId,userId);
	}//storeFileInfoInDB() closing


	@Override
	public void showHiddenFoldersAndFiles(Integer fid, Integer userid) {
		digiLockerMongoDao.showHiddenFoldersAndFiles(fid, userid);
	}//showHiddenFoldersAndFiles() closing


	@Override
	public void storeFolderInfo(List<FolderInfo> folderlist, Integer userId) {
		digiLockerMongoDao.storeFolderInfo(folderlist,userId);	
	}


	@Override
	public List<GalleryDetails> getGallerContent(Integer userid, String filesType,String fileStatus) throws ParseException {

		List<GalleryDetails> galleryContent = digiLockerMongoDao.getGallerContent(userid, filesType,fileStatus);
		
		return galleryContent;
	}//getGallerContent() closing


	@Override
	public FolderInfo getGalleryDetails(Integer userId) {

		FolderInfo gallery = digiLockerMongoDao.getGalleryDetails(userId);
		
		return gallery;
	}//getGalleryDetails() closinig


	@Override
	public FolderInfo createNewFolder(Integer userid, String foldername, String currentFolderPath,
			List<FolderInfo> allFolderList) {

		Integer currentFolderId=null;
		int newFolderId=0;
		if(StringUtils.isNotBlank(currentFolderPath)){
			String strTemp1=currentFolderPath.substring(0, currentFolderPath.length()-1);
			currentFolderId = Integer.parseInt(strTemp1.substring(strTemp1.lastIndexOf('/')+1));
			
			fileUploadServices.createFolderName(currentFolderPath);
		}else{
			newFolderId=uniqueKeyDaoImpl.getUniqueKey(userid, UniqueKeyEnum.FOLDER_ID.toString(), 1);
			fileUploadServices.createFolderName("/"+userid+"/"+newFolderId+"/");
			//fileUploadServices.createFolderName("/"+currentFolderId+"/");
		}
		FolderInfo	currentFolderInfo =null;
		if(currentFolderId!=null)currentFolderInfo=digilockerService.getFolderInfo(allFolderList,currentFolderId);
		if(newFolderId==0)newFolderId=uniqueKeyDaoImpl.getUniqueKey(userid, UniqueKeyEnum.FOLDER_ID.toString(), 1);

		FolderInfo newFolder =new FolderInfo();
		newFolder.setfId(++newFolderId);
		newFolder.setfName(foldername);
		
		if(currentFolderId==null) newFolder.setParentId(0);
		else newFolder.setParentId(currentFolderId);
		
		if(currentFolderInfo!=null){
			newFolder.setFolderNamePath(currentFolderInfo.getFolderNamePath()+"/"+foldername);
			newFolder.setFolderPath(currentFolderInfo.getFolderPath()+newFolder.getfId()+"/");
		}else{
			newFolder.setFolderNamePath("home"+"/"+foldername);
			//newFolder.setFolderPath(currentFolderInfo.getFolderPath()+newFolder.getfId()+"/");
			newFolder.setFolderPath("/"+userid+"/"+newFolder.getfId()+"/");
		}
		newFolder.setFolderStatus(DigiLockerStatusEnum.ACTIVE.toString());
		newFolder.setChildFolders(null);
		newFolder.setLocalFilesInfo(null);
		newFolder.setOrigin(DigiLockerEnum.LOCKER.toString());
		digilockerService.storeNewFileOrFolderInfo(newFolder, new Integer(""+newFolder.getfId()), userid);
		
		return newFolder;
	}//createNewFolder() closing


	@Override
	public String uploadFiles(MultipartFile multipart, Integer userid, String folderPath, Integer folderId) {

		String fileName = multipart.getOriginalFilename();
		String filePath = folderPath+fileName.replaceAll(" ", "_");
		
		String fileURL=fileUploadServices.uploadWebDavServer(multipart,folderPath);
		String fileType =digiLockerUtils.getFileType(multipart);
		String fileExtension=fileName.substring(fileName.lastIndexOf(".")+1);
		int	fileUniqueKey=uniqueKeyDaoImpl.getUniqueKey(userid, UniqueKeyEnum.FILES_ID.toString(), 1);
		if(fileURL!=null){
			
			FilesInfo newFileInfo = new FilesInfo();
			newFileInfo.setFileId(++fileUniqueKey);
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
		
		return fileURL;
	}


	@Override
	public void storeFilesInGalleryFromDigiLocker(Integer userId, Integer folderId, MultipartFile[] multipartList) {

		FolderInfo gallery =digilockerService.getGalleryDetails(userId);
		if(gallery==null){
			gallery =this.checkAndCreateGalleryFolder(userId);
		}
		String fileURL=null;
		List<FilesInfo> newFileList = new ArrayList<>();
		FilesInfo filesInfo = null;
		int	fileUniqueKey=uniqueKeyDaoImpl.getUniqueKey(userId, UniqueKeyEnum.FILES_ID.toString(),multipartList.length);
		String fileExtention =null;
		String fileName =null;
		for (int i=0;i<multipartList.length;i++) {	
            if (!multipartList[i].isEmpty()) {
            	fileURL =fileUploadServices.uploadWebDavServer(multipartList[i], gallery.getFolderPath());
            	if(fileURL!=null){
	            	filesInfo =new FilesInfo();
	            	fileName= multipartList[i].getOriginalFilename();
	            	fileExtention=fileName.substring(fileName.lastIndexOf(".")+1);
	            	filesInfo.setFileId(++fileUniqueKey);
	            	filesInfo.setFileName(fileName);
	            	filesInfo.setDumy_filename(fileName.replaceAll(" ", "_"));
	            	filesInfo.setFilePath(gallery.getFolderPath()+fileName.replaceAll(" ", "_"));
	            	filesInfo.setFileStatus(DigiLockerStatusEnum.ACTIVE.toString());
	            	filesInfo.setCreateddate(new Date());
	            	filesInfo.setStatusAtGallery(DigiLockerStatusEnum.ACTIVE.toString());
	            	filesInfo.setFileType(digiLockerUtils.getFileType(multipartList[i]));
	            	filesInfo.setFileExtension(fileExtention);
	            	newFileList.add(filesInfo);
            	}//if closing
            }//if closing
		}//for closing
		if(newFileList.size()>0){
			gallery.setLocalFilesInfo(newFileList);
			digilockerService.storeNewFileOrFolderInfo(gallery, gallery.getFolderId(), userId);
		}
	}//storeFilesInGalleryFromDigiLocker() closing


	@SuppressWarnings("null")
	private synchronized FolderInfo checkAndCreateGalleryFolder(Integer userId) {
		
		FolderInfo gallery =digilockerService.getGalleryDetails(userId);
		if(gallery!=null){
			return gallery;
		}else{
			
			gallery =new FolderInfo();
			int newFolderId=uniqueKeyDaoImpl.getUniqueKey(userId, UniqueKeyEnum.FOLDER_ID.toString(), 1);
			fileUploadServices.createFolderName("/"+userId+"/"+newFolderId+"/");
			
			gallery.setfId(++newFolderId);
			String foldername ="Gallery";
			gallery.setfName(foldername);
			
			gallery.setParentId(0);
			gallery.setFolderNamePath("home"+"/"+foldername);
			gallery.setFolderPath("/"+userId+"/"+gallery.getfId()+"/");
			gallery.setFolderStatus(DigiLockerStatusEnum.ACTIVE.toString());
			gallery.setChildFolders(null);
			gallery.setLocalFilesInfo(null);
			gallery.setOrigin(DigiLockerEnum.GALLERY.toString());
			digilockerService.storeNewFileOrFolderInfo(gallery, new Integer(""+gallery.getfId()), userId);
			return gallery;
		}
	}//checkAndCreateGalleryFolder() closing

}//class closing
