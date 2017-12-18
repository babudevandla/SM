package com.sm.portal.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.portal.model.FileManagementVO;
import com.sm.portal.mongo.dao.FileManagementMongoDao;

@Service
public class FileManagementServiceImpl implements FileManagementService{

	@Autowired
	FileManagementMongoDao managementMongoDao;
	
	@Override
	public void uploadFolderOrFile(Integer userid, String foldername,Integer parentid,Principal principal) {
		
		FileManagementVO filevo=new FileManagementVO();
		filevo.setUser_id(userid);
		filevo.setFoldername(foldername);
		filevo.setFolder_parent_id(parentid);
		filevo.setFile_status(true);
		filevo.setCreated_date(new Date());
		filevo.setUploaded_by(principal.getName());
		filevo.setFile_size("0 KB");
		
		managementMongoDao.createFolder(filevo);
	}

	@Override
	public List<FileManagementVO> getDocumentsAndFoldesList(Integer userId) {
		return managementMongoDao.getDocumentsAndFoldesList(userId);
	}

}
