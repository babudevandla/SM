package com.sm.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.portal.dao.PropertyDao;
import com.sm.portal.model.SMPropertites;

@Service
public class PropertyServiceImpl implements PropertyService{

	@Autowired
	public PropertyDao propertyDao;
	
	
	@Override
	public SMPropertites getSmPropertyByKey(String key) {
		return propertyDao.getSmPropertyByKey(key);
	}

}
