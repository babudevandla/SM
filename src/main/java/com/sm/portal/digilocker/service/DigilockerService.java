package com.sm.portal.digilocker.service;

import java.util.List;

import com.sm.portal.digilocker.model.FilesInfo;
import com.sm.portal.digilocker.model.FolderInfo;

public interface DigilockerService {

	public List<FolderInfo> getDigiLockerHomeData(Long userId);

	public List<FolderInfo> getRootFoldersList(List<FolderInfo> allFolderList);

	public FolderInfo getFolderInfo(List<FolderInfo> allFolderList,Integer fid);

	public void storeNewFileOrFolderInfo(FolderInfo FolderInfo, Integer folderId, Integer userid);

	public void updateFileOrFolderSatus(String deleteInfo, String action, Integer folderId, Integer fileId, Integer userId);

	public void showHiddenFoldersAndFiles(Integer fid, Integer userid);

	public void storeFolderInfo(FolderInfo newFolder, Integer integer, Integer userId);

	public FolderInfo getGallerContent(Integer userid, String filesType);

}
