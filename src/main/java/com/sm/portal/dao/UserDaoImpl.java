package com.sm.portal.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sm.portal.model.Users;
import com.sm.portal.model.UsersDto;

@Repository
public class UserDaoImpl implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Users findUserByUserName(String username) {
		Users user=(Users) sessionFactory.getCurrentSession().createQuery("from Users where userName=:userName and enabled=true and dynamic_status=true ").setParameter("userName", username).uniqueResult();	    
		return user;
	}

	@Override
	public Users getUserById(Integer userId) {
		if(logger.isTraceEnabled())
			logger.info("UserDaoImpl === getUserById == end");
		
		Users user=(Users) sessionFactory.getCurrentSession().createQuery("from Users where userId=:userId").setParameter("userId", userId).uniqueResult();
		
		if(logger.isTraceEnabled())
			logger.info("UserDaoImpl === getUserById == end");
		
		return user;
	}

	@Override
	public Integer saveUser(Users info) {
		return (Integer) sessionFactory.getCurrentSession().save(info);
	}

	@Override
	public boolean isUserExist(UsersDto userex) {
		boolean userStatus=false;
		Users user=(Users) sessionFactory.getCurrentSession().createQuery("from Users where userName=:userName").setParameter("userName", userex.getUsername()).uniqueResult();
		if(user!=null){
			userStatus=true;
		}
		if(logger.isTraceEnabled())
			logger.info("UserDaoImpl === isUserExist == end");
		return userStatus;
	}
	
	@Override
	public Users getUserByUsernamePassword(UsersDto usersDto) {
		if(logger.isTraceEnabled())
			logger.info("UserDaoImpl === findUserByUserName == end");
		
		Users user=(Users) sessionFactory.getCurrentSession().createQuery("from Users where mobile_no=:mobile_no and password=:password ")
				.setParameter("mobile_no", usersDto.getMobile_no())
				.setParameter("password", usersDto.getPassword()).uniqueResult();
		
		if(logger.isTraceEnabled())
			logger.info("UserDaoImpl === findUserByUserName == end");
		return user;
	}
	/*@Override
	public Captcha getCaptcha() {
		return (Captcha)sessionFactory.getCurrentSession().createQuery("from Captcha order by rand()").setMaxResults(1).uniqueResult();
	}
	
	*/
	
	@Override
	public void updateDynamicCode(String dynamicCode,UsersDto users) {
		if(logger.isTraceEnabled())
			logger.info("UserDaoImpl === updateDynamicCode == end");
		
		sessionFactory.getCurrentSession().createQuery("update Users set dynamic_access_code=:dynamic_access_code where mobile_no=:mobile_no")
		.setParameter("dynamic_access_code", dynamicCode)
		.setParameter("mobile_no", users.getMobile_no()).executeUpdate();
		
		if(logger.isTraceEnabled())
			logger.info("UserDaoImpl === updateDynamicCode == end");
	}

	

	@Override
	public Users checkDynamicAccessCode(UsersDto userDto) {
		if(logger.isTraceEnabled())
			logger.info("UserDaoImpl === checkDynamicAccessCode == start ");
		
		Users user=(Users) sessionFactory.getCurrentSession().createQuery("from Users where userId=:userId and dynamic_access_code=:dynamic_access_code and dynamic_status=:dynamic_status")
				.setParameter("userId", userDto.getUserId())
				.setParameter("dynamic_access_code", userDto.getDynamic_access_code())
				.setParameter("dynamic_status", false).uniqueResult();	
		
		if(logger.isTraceEnabled())
			logger.info("UserDaoImpl === checkDynamicAccessCode == end");
		
		return user;
	}

	@Override
	public void updateUserInfo(UsersDto userDto) {
		if(logger.isTraceEnabled())
			logger.info("UserDaoImpl === updateUsers == start ");
		
		sessionFactory.getCurrentSession().createQuery("update Users set dynamic_status=:dynamic_status , enabled=:enabled where userId=:userId")
		.setParameter("dynamic_status", true)
		.setParameter("enabled", true)
		.setParameter("userId", userDto.getUserId()).executeUpdate();
		
		if(logger.isTraceEnabled())
			logger.info("UserDaoImpl === updateUsers == end ");
	}

	@Override
	public void updatedUserPassword(UsersDto users) {
		if(logger.isTraceEnabled())logger.info("UserDaoImpl === updatedUserPassword == end");
		
		sessionFactory.getCurrentSession().createQuery("update Users set password=:password where userId=:userId")
		.setParameter("password", users.getPassword())
		.setParameter("userId", users.getUserId()).executeUpdate();
		
		if(logger.isTraceEnabled())logger.info("UserDaoImpl === updatedUserPassword == end");
	}

	@Override
	public boolean checkOldPasssword(String oldpassword,Integer userId) {
		boolean userStatus=false;
		Users user=(Users) sessionFactory.getCurrentSession().createQuery("from Users where password=:password and userId=:userId")
				.setParameter("password", oldpassword)
				.setParameter("userId", userId)
				.uniqueResult();
		if(user!=null){
			userStatus=true;
		}
		if(logger.isTraceEnabled())
			logger.info("UserDaoImpl === isUserExist == end");
		return userStatus;
	}

	

	


}
