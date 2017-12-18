package com.sm.portal.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sm.portal.model.SMPropertites;

@Repository
public class PropertyDaoImpl implements PropertyDao{

	private static final Logger logger = LoggerFactory.getLogger(PropertyDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public SMPropertites getSmPropertyByKey(String key) {
		if(logger.isTraceEnabled())logger.info("PropertyDaoImpl === getSmPropertyByKey == end");
		SMPropertites propertites=(SMPropertites) sessionFactory.openSession().createQuery("from SMPropertites where propkey=:propkey").setParameter("propkey", key).uniqueResult();
		
		if(logger.isTraceEnabled())logger.info("PropertyDaoImpl === getSmPropertyByKey == end");
		return propertites;
	}

}
