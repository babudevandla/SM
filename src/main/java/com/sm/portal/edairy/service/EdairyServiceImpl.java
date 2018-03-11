package com.sm.portal.edairy.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.portal.edairy.model.DairyInfo;
import com.sm.portal.edairy.model.DairyPage;
import com.sm.portal.edairy.model.EdairyActionEnum;
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
	public DairyInfo getDairyInfo(int userId, int dairyId, String actionBy, int defaultPageNo) {
		DairyInfo dairyInfo =EdairyDaoImpl.getDairyInfo(userId, dairyId);
		List<DairyPage> pagesList =null;
		DairyPage defaultPage = null;
		if(dairyInfo!=null)pagesList =dairyInfo.getPages();
		if(pagesList !=null && actionBy!=null){
			if(actionBy.equals(EdairyActionEnum.TODAYS_PAGE.toString())){
				defaultPage =pagesList.stream().filter(p->p.getDate().equals(new Date())).findFirst().orElse(new DairyPage());
			}else if(actionBy.equals(EdairyActionEnum.LAST_UPDATD_DATE.toString())){
				defaultPage =pagesList.stream().filter(p->p.getDate().equals(dairyInfo.getLastModifiedDate())).findFirst().orElse(new DairyPage());
			}else if(actionBy.equals(EdairyActionEnum.SELECTED_DATE.toString())){
				defaultPage =pagesList.stream().filter(p->p.getPageNo()==defaultPageNo).findFirst().orElse(new DairyPage());
			}else if(actionBy.equals(EdairyActionEnum.FAVORITE_PAGE.toString())){
				defaultPage =pagesList.stream().filter(p->p.getPageNo()==defaultPageNo).findFirst().orElse(new DairyPage());
			}else if(actionBy.equals(EdairyActionEnum.TITLE_PAGE.toString())){
				defaultPage =pagesList.stream().filter(p->p.getPageNo()==defaultPageNo).findFirst().orElse(new DairyPage());
			}else if(actionBy.equals(EdairyActionEnum.EDIT_PAGE.toString())){
				defaultPage =pagesList.stream().filter(p->p.getPageNo()==defaultPageNo).findFirst().orElse(new DairyPage());
			}else if(actionBy.equals(EdairyActionEnum.VIEW_PAGE.toString())){
				defaultPage =pagesList.stream().filter(p->p.getPageNo()==defaultPageNo).findFirst().orElse(new DairyPage());
			}
			dairyInfo.setDefaultPage(defaultPage);
		}//if closing
		return dairyInfo;
	}//getDairyInfo() closing

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