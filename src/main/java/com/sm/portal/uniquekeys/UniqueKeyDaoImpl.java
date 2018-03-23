package com.sm.portal.uniquekeys;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sm.portal.model.UniqueKey;


@Repository
public class UniqueKeyDaoImpl implements UniqueKeyDao{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Integer getUniqueKey(Integer userId, String uniqueProperty, Integer noOfIds) {

		UniqueKey uniqueKey = null;
		Session session=sessionFactory.openSession();;
		 Transaction tx = null;
		 tx = session.beginTransaction();
		/*uniqueKey=(UniqueKey) session.createQuery("from UniqueKey where userId=:userId and uniqueProperty=uniqueProperty")
				.setParameter("userId", userId)
				.setParameter("uniqueProperty",uniqueProperty)
				.uniqueResult();*/
		 Criteria cr =session.createCriteria(UniqueKey.class);
		 cr.add(Restrictions.eq("userId", userId));
		 cr.add(Restrictions.eq("uniqueProperty", uniqueProperty));
		 List<UniqueKey> uniqueKeys=cr.list();
		 if(uniqueKeys!=null && uniqueKeys.size()>0)uniqueKey= uniqueKeys.get(0);
		 int mainValue=0;
		if(uniqueKey==null){
			try{
				mainValue =1000;
				uniqueKey=new UniqueKey();
				uniqueKey.setUserId(userId);
				uniqueKey.setUniqueProperty(uniqueProperty);
				uniqueKey.setUniqueValue(mainValue+noOfIds);
				session.save(uniqueKey);
				
			}catch(Exception e){
				e.printStackTrace();
				}
		}else{
			try{
				//uniqueKey=new UniqueKey();
				uniqueKey.setUserId(userId);
				uniqueKey.setUniqueProperty(uniqueProperty);
				uniqueKey.setUniqueValue(uniqueKey.getUniqueValue()+noOfIds);
				session.saveOrUpdate(uniqueKey);
				
			}catch(Exception e){
				e.printStackTrace();
				}
		}
		tx.commit();
		uniqueKey.setUniqueValue(uniqueKey.getUniqueValue()-noOfIds);
		if(mainValue!=0) return mainValue;
		return uniqueKey.getUniqueValue();
	}//getUniqueKey() closings

	@Override
	public boolean updateUniqueKey(UniqueKey uniqueKey) {
		Session session=sessionFactory.openSession();;
		Transaction tx = null;
		 
		try{
			
			tx = session.beginTransaction();
			Criteria cr =session.createCriteria(UniqueKey.class);
			 cr.add(Restrictions.eq("userId", uniqueKey.getUserId()));
			 cr.add(Restrictions.eq("uniqueProperty", uniqueKey.getUniqueProperty()));
			 session.saveOrUpdate(uniqueKey);		
		}catch(Exception e){
			e.printStackTrace();
			}
		
		return true;
	}//updateUniqueKey() closing
	
	
}//class closing
