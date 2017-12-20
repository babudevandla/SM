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
public class GalleryController {

	Logger logger=Logger.getLogger(GalleryController.class.getName());
	
	@Autowired
    private UserService userService;
	
	@GetMapping(value=URLCONSTANT.GALLERY_HOME)
	public @ResponseBody ModelAndView gallery(Principal principal){
		logger.debug(" show user gallery ...");
		ModelAndView mvc = new ModelAndView("/customer/gallery");
		Users user =userService.findUserByUserName(principal.getName());
		mvc.addObject("user", user);
		return mvc;
	}
}
