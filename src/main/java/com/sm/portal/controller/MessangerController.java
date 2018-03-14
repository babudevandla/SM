package com.sm.portal.controller;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sm.portal.constants.URLCONSTANT;
import com.sm.portal.model.Users;
import com.sm.portal.service.UserService;

@RestController
@RequestMapping(URLCONSTANT.BASE_URL)
public class MessangerController {

	Logger logger=Logger.getLogger(MessangerController.class.getName());
	
	@Autowired
    private UserService userService;
	
	@GetMapping(value=URLCONSTANT.MESSANGER_HOME)
	public @ResponseBody ModelAndView messanger(Principal principal){
		logger.debug(" show user messanger ...");
		ModelAndView mvc = new ModelAndView("/customer/messanger");
		Users user =userService.findUserByUserName(principal.getName());
		mvc.addObject("user", user);
		mvc.addObject("msgActive", true);
		return mvc;
	}
}
