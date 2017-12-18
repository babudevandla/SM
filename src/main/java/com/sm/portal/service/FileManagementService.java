package com.sm.portal.service;

import java.security.Principal;
import java.util.List;

import com.sm.portal.model.FileManagementVO;

public interface FileManagementService {

	void uploadFolderOrFile(Integer userid, String foldername, Integer parentid, Principal principal);

	List<FileManagementVO> getDocumentsAndFoldesList(Integer userId);

}
