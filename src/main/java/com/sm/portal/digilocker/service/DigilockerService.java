package com.sm.portal.digilocker.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sm.portal.digilocker.model.FilesInfo;
import com.sm.portal.digilocker.model.FolderInfo;
import com.sm.portal.digilocker.model.GalleryDetails;

public interface DigilockerService {

	public List<FolderInfo> getDigiLockerHomeData(Long userId);

	public List<FolderInfo> getRootFoldersList(List<FolderInfo> allFolderList);

	public FolderInfo getFolderInfo(List<FolderInfo> allFolderList,Integer fid);

	public void storeNewFileOrFolderInfo(FolderInfo FolderInfo, Integer folderId, Integer userid);

	public void updateFileOrFolderSatus(String deleteInfo, String action, Integer folderId, Integer fileId, Integer userId);

	public void showHiddenFoldersAndFiles(Integer fid, Integer userid);

	public void storeFolderInfo(List<FolderInfo> folderlist, Integer userId);

	public List<GalleryDetails> getGallerContent(Integer userid, String filesType, String fileStatus) throws ParseException;

	public void storeFilesInGallery(FolderInfo newFolderInfo, Integer userId);

	public FolderInfo getGalleryDetails(Integer userId);

	public FolderInfo createNewFolder(Integer userid, String foldername, String currentFolderPath,
			List<FolderInfo> allFolderList);

	public String uploadFiles(MultipartFile multipart, Integer userid, String folderPath, Integer folderId);

	public void storeFilesInGalleryFromDigiLocker(Integer userId, Integer folderId, MultipartFile[] multipartList);

}
