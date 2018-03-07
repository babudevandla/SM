package com.sm.portal.edairy.mongo.dao;

import com.sm.portal.edairy.model.DairyInfo;
import com.sm.portal.edairy.model.DairyPage;
import com.sm.portal.edairy.model.UserDairies;

public interface EdairyDao {

	public UserDairies gerUserDairies(int userId);
	public  DairyInfo getDairyInfo(int userId, int dairyId);
	public DairyInfo editPageContent(int userId, int dairyId, DairyPage dairyPage);
	public boolean savePageContent(int userId, int dairyId, DairyPage dairyPage);}
