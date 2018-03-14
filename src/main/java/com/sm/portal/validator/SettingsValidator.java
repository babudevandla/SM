package com.sm.portal.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.sm.portal.model.UsersDto;
import com.sm.portal.service.UserService;

@Service
public class SettingsValidator  extends GenricValidator{
	
	@Autowired
    private UserService userService;
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UsersDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		UsersDto usersDto= (UsersDto) target;
		
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldpassword", "update.oldpassword.not.blank");
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "update.password.not.blank");
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmpassword", "update.confirmpassword.not.blank");
		 
		 if(!checkOldPasssword(usersDto.getOldpassword(),usersDto.getUserId())){
			 errors.rejectValue("oldpassword", "update.oldpassword.not.found");
		 }
		 if(!StringUtils.equals(usersDto.getPassword(), usersDto.getConfirmpassword())){
			 errors.rejectValue("confirmpassword", "update.confirmpassword.not.equal");
		 }
		 
	}

	private boolean checkOldPasssword(String oldpassword, Integer userId) {
		return userService.checkOldPasssword(oldpassword,userId);
	}
	
}