package com.sm.portal.service;

import com.sm.portal.model.Users;
import com.sm.portal.model.UsersDto;

public interface UserService {

	Users findUserByUserName(String username);
	Users getUserById(Integer userId);
	Users checkDynamicAccessCode(UsersDto userDto);
	void updateUserInfo(UsersDto userDto);
	void updateDynamicCode(String dynamicCode, UsersDto users);
	boolean isUserExist(UsersDto user);
	Integer saveUser(UsersDto usersDto);
	Users getUserByUsernamePassword(UsersDto usersDto);
}