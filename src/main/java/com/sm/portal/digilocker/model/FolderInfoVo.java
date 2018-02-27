package com.sm.portal.digilocker.model;

import java.io.Serializable;
import java.util.List;

public class FolderInfoVo implements Serializable {

	public Integer userId;
	public List<FolderInfo> foldersList;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<FolderInfo> getFoldersList() {
		return foldersList;
	}
	public void setFoldersList(List<FolderInfo> foldersList) {
		this.foldersList = foldersList;
	}
	
	
	
}
