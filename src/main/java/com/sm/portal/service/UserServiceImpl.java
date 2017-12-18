package com.sm.portal.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sm.portal.dao.UserDao;
import com.sm.portal.model.Role;
import com.sm.portal.model.Users;
import com.sm.portal.model.UsersDto;

@Service
@Transactional
public class UserServiceImpl  implements UserService {

	@Autowired
	private  UserDao userDao;
	
	
	@Override
	public Users findUserByUserName(String username) {
		return userDao.findUserByUserName(username);
	}

	@Override
	public Users getUserById(Integer userId) {
		return userDao.getUserById(userId);
	}
	
	
	@Override
	public Integer saveUser(UsersDto users) {
		Users info=new Users();
		info.setFirstname(users.getFirstname());
		info.setLastname(users.getLastname());
		info.setMobile_no(users.getMobile_no());
		//info.setDateofbirth(stringtoDate(users.getBirthday_year()+"-"+users.getBirthday_month()+"-"+users.getBirthday_date()));
		info.setEmail(users.getEmail());
		info.setPassword(users.getPassword());
		info.setUserName(users.getMobile_no());
		info.setCountry(users.getCountry());
		info.setState(users.getState());
		info.setCity(users.getCity());
		info.setPhoneNumber(users.getPhoneNumber());
		info.setZipCode(users.getZipCode());
		info.setCreatedDate(new Date());
		info.setGender(users.getGender());
		Integer day=Integer.valueOf(users.getBirthday_date());
		Integer year=Integer.valueOf(users.getBirthday_year());
		info.setDateofbirth(day+"-"+users.getBirthday_month()+"-"+year);
		info.setUserRole("ROLE_CUSTOMER");
		List<Role> roles=new ArrayList<>();
		Role role=new Role();
		role.setRoleName("ROLE_CUSTOMER");
		role.setRoleId(1);
		roles.add(role);
		info.setRoles(roles);
		return userDao.saveUser(info);
	}

	@Override
	public boolean isUserExist(UsersDto user) {
		return userDao.isUserExist(user);
	}
	
	@Override
	public Users getUserByUsernamePassword(UsersDto usersDto) {
		return userDao.getUserByUsernamePassword(usersDto);
	}
	
	@Override
	public void updateDynamicCode(String dynamicCode,UsersDto users) {
		userDao.updateDynamicCode(dynamicCode,users);		
	}

	
	@Override
	public Users checkDynamicAccessCode(UsersDto userDto) {
		return userDao.checkDynamicAccessCode(userDto);
	}

	@Override
	public void updateUserInfo(UsersDto userDto) {
		userDao.updateUserInfo(userDto);
	}


}
