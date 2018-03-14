package com.sm.portal.controller;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sm.portal.constants.URLCONSTANT;
import com.sm.portal.model.Users;
import com.sm.portal.model.UsersDto;
import com.sm.portal.service.UserService;
import com.sm.portal.validator.SettingsValidator;

@RestController
@RequestMapping(URLCONSTANT.BASE_URL)
public class SettingController {

Logger logger=Logger.getLogger(MessangerController.class.getName());
	
	@Autowired
    private UserService userService;
	
	@Autowired
	SettingsValidator settingsValidator;
	
	@GetMapping(value=URLCONSTANT.USER_SETTINGS)
	public @ResponseBody ModelAndView userSettings(@ModelAttribute("users") UsersDto users,
			@RequestParam(value="message", required=false) String message, Principal principal){
		logger.debug(" userSettings ...");
		ModelAndView mvc = new ModelAndView("/customer/settings");
		Users user =userService.findUserByUserName(principal.getName());
		mvc.addObject("userId", user.getUserId());
		mvc.addObject("message", message);
		mvc.addObject("settActive", true);
		return mvc;
	}
	
	
	@PostMapping(value=URLCONSTANT.USER_SETTINGS)
	public @ResponseBody ModelAndView updateUserPassword(@ModelAttribute("users") UsersDto users,BindingResult result,Principal principal){
		logger.debug(" updateUserPassword ...");
		ModelAndView mvc = new ModelAndView("/customer/settings");
		try{
			/*settingsValidator.validate(users, result);
			if (!result.hasErrors()) {
				userService.updatedUserPassword(users);
				mvc.addObject("message", "Password updated successfully!");
				mvc.setViewName("redirect:/sm/settings");
			}else{
				return mvc;
			}*/
			
			userService.updatedUserPassword(users);
			mvc.addObject("message", "Password updated successfully!");
			mvc.setViewName("redirect:/sm/settings");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return mvc;
	}
}
