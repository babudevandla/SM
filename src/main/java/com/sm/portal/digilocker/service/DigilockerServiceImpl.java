package com.sm.portal.digilocker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.portal.digilocker.model.FilesInfo;
import com.sm.portal.digilocker.model.FolderInfo;
import com.sm.portal.digilocker.mongo.dao.DigiLockerMongoDao;

@Service
public class DigilockerServiceImpl implements DigilockerService{

	@Autowired
	DigiLockerMongoDao digiLockerMongoDao;
	
	
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
	public FolderInfo getGallerContent(Integer userid, String filesType) {

		FolderInfo galleryContent = null;
		galleryContent =digiLockerMongoDao.getGallerContent(userid, filesType);
		
		return galleryContent;
	}//getGallerContent() closing


	@Override
	public FolderInfo getGalleryDetails(Integer userId) {

		FolderInfo gallery = digiLockerMongoDao.getGalleryDetails(userId);
		
		return gallery;
	}//getGalleryDetails() closinig

}//class closing
