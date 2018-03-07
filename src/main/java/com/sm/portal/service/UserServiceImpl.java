package com.sm.portal.service;


import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sm.portal.dao.UserDao;
import com.sm.portal.digilocker.model.DigiLockerStatusEnum;
import com.sm.portal.digilocker.model.FilesInfo;
import com.sm.portal.digilocker.model.FolderInfo;
import com.sm.portal.digilocker.service.DigilockerService;
import com.sm.portal.model.Role;
import com.sm.portal.model.Users;
import com.sm.portal.model.UsersDto;

@Service
@Transactional
public class UserServiceImpl  implements UserService {

	@Autowired
	private  UserDao userDao;
	
	
	@Autowired
	DigilockerService digilockerService;
	
	@Override
	public Users findUserByUserName(String username) {
		return userDao.findUserByUserName(username);
	}

	@Override
	public Users getUserById(Integer userId) {
		return userDao.getUserById(userId);
	}
	
	
	@Override
	public Integer saveUser(UsersDto users,HttpServletRequest request) {
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
		role.setRoleId(2);
		roles.add(role);
		info.setRoles(roles);
		
		Integer  userId=userDao.saveUser(info);
		
		
		FolderInfo newFolder =new FolderInfo();
		newFolder.setfId(0);
		newFolder.setfName("");
		newFolder.setParentId(0);
		newFolder.setFolderNamePath("home");
		newFolder.setFolderPath("/"+userId+"/"+newFolder.getfId()+"/");
		newFolder.setFolderStatus(DigiLockerStatusEnum.ACTIVE.toString());
		newFolder.setChildFolders(null);
		newFolder.setLocalFilesInfo(null);
		List<FilesInfo> fileList = new ArrayList<>();
		newFolder.setLocalFilesInfo(fileList);
		digilockerService.storeFolderInfo(newFolder, new Integer(""+newFolder.getfId()), userId);
		
		return userId;
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


	
	public Integer gerUniqueKey(HttpServletRequest request){
		int newValue=0;
		InputStream input = request.getServletContext().getResourceAsStream("/WEB-INF/uniquekey.properties");
		Properties properties = new Properties();
		try{
			properties.load(input);
			String uniqueKey=properties.getProperty("uniqueId");
			newValue= Integer.parseInt(uniqueKey)+1;
			properties.setProperty("uniqueId", ""+newValue);
			properties.store(new FileOutputStream(request.getServletContext().getRealPath("/WEB-INF/uniquekey.properties")),null);
		}catch(Exception e){
			
		}
		
		return newValue;
	}//closing
	
}
