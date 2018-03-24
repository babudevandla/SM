package com.sm.portal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.portal.edairy.model.DairyInfo;
import com.sm.portal.model.EDairyDto;
import com.sm.portal.model.Users;
import com.sm.portal.mongo.dao.EDairyMongoDao;
import com.sm.portal.uniquekeys.UniqueKeyDaoImpl;
import com.sm.portal.uniquekeys.UniqueKeyEnum;

@Service
public class EDairyServiceImpl implements EDairyService {

	@Autowired
	private EDairyMongoDao dairyMongoDao;
	
	@Autowired
	UniqueKeyDaoImpl uniqueKeyDaoImpl;
	
	@Override
	public void saveEDairyData(EDairyDto eDairyDto, Users user) {
		
		eDairyDto.setDairyDate(new Date());
		eDairyDto.setStatus(true);
		dairyMongoDao.saveEDairyData(eDairyDto,user);
	}

	@Override
	public List<EDairyDto> getEDairyList(Integer userId) {
		return dairyMongoDao.getEDairyList(userId);
	}

	@Override
	public EDairyDto getEDairyDataById(Integer dairyId) {
		return dairyMongoDao.getEDairyDataById(dairyId);
	}

	@Override
	public void updateEDairyData(EDairyDto eDairyDto, Users user) {
		dairyMongoDao.updateEDairyData(eDairyDto,user);
	}

	@Override
	public void saveEDairyData(DairyInfo dairyInfo) {
		dairyInfo.setDairyId(uniqueKeyDaoImpl.getUniqueKey(dairyInfo.getUserId(), UniqueKeyEnum.DAIRY_ID.toString(), 1));
		dairyMongoDao.creatEdairy(dairyInfo);
		
		
	}//saveEDairyData() closing

}
