package com.sm.portal.controller;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sm.portal.constants.URLCONSTANT;
import com.sm.portal.model.Users;
import com.sm.portal.service.UserService;

@RestController
@RequestMapping(URLCONSTANT.BASE_URL)
public class PersonalAssistantController {

	Logger logger=Logger.getLogger(PersonalAssistantController.class.getName());
	
	@Autowired
    private UserService userService;
	
	@GetMapping(value=URLCONSTANT.PERSONAL_LIST)
	public @ResponseBody ModelAndView personalJobList(Principal principal){
		logger.debug(" show personalJobList ...");
		ModelAndView mvc = new ModelAndView("/customer/personal_setting_list");
		Users user =userService.findUserByUserName(principal.getName());
		
		
		mvc.addObject("paActive", true);
		mvc.addObject("user", user);
		return mvc;
	}
	
	
	@GetMapping(value=URLCONSTANT.NEW_JOB_SETTING_CREATE)
	public @ResponseBody ModelAndView createNewJobSettings(Principal principal){
		logger.debug(" show createNewJobSettings ...");
		ModelAndView mvc = new ModelAndView("/customer/new_job_settings");
		Users user =userService.findUserByUserName(principal.getName());
		
		
		mvc.addObject("paActive", true);
		mvc.addObject("user", user);
		return mvc;
	}
	
}
