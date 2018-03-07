package com.sm.portal.edairy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.portal.edairy.model.DairyInfo;
import com.sm.portal.edairy.model.DairyPage;
import com.sm.portal.edairy.model.UserDairies;
import com.sm.portal.edairy.mongo.dao.EdairyDao;

@Service
public class EdairyServiceImpl implements EdairyService{

	@Autowired
	private EdairyDao EdairyDaoImpl;
	
	@Override
	public UserDairies gerUserDairies(int userId) {
		return EdairyDaoImpl.gerUserDairies(userId);
	}

	@Override
	public DairyInfo getDairyInfo(int userId, int dairyId) {
		return EdairyDaoImpl.getDairyInfo(userId, dairyId);
	}

	@Override
	public DairyInfo editPageContent(int userId, int dairyId, DairyPage dairyPage) {
		return EdairyDaoImpl.editPageContent(userId, dairyId, dairyPage);
	}

	@Override
	public DairyInfo savePageContent(int userId, int dairyId, DairyPage dairyPage) {

		boolean result =EdairyDaoImpl.savePageContent(userId, dairyId, dairyPage);
		//if(result)
			return EdairyDaoImpl.editPageContent(userId, dairyId, dairyPage);
	}

}
