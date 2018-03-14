package com.sm.portal.dao;

import com.sm.portal.model.Users;
import com.sm.portal.model.UsersDto;

public interface UserDao {

	public Users findUserByUserName(String username);

	public Users getUserById(Integer userId);

	public Integer saveUser(Users info);

	public boolean isUserExist(UsersDto user);
	public Users getUserByUsernamePassword(UsersDto usersDto);
	public Users checkDynamicAccessCode(UsersDto userDto);

	public void updateUserInfo(UsersDto userDto);
	public void updateDynamicCode(String dynamicCode, UsersDto users);

	public void updatedUserPassword(UsersDto users);

	public boolean checkOldPasssword(String oldpassword, Integer userId);

}