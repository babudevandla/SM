package com.sm.portal.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sm.portal.constants.URLCONSTANT;
import com.sm.portal.model.UserDetailsDto;
import com.sm.portal.model.Users;
import com.sm.portal.service.UserService;

@RestController
@RequestMapping(URLCONSTANT.BASE_URL)
public class UserProfileController {

	Logger logger=Logger.getLogger(UserProfileController.class.getName());
	
	@Autowired
    private UserService userService;
	
	@GetMapping(value="/profile")
	public @ResponseBody ModelAndView profilePage(Principal principal){
		logger.debug(" show user profile ...");
		ModelAndView mvc = new ModelAndView("/customer/profile");

		Users user=userService.findUserByUserName(principal.getName());
		/*RestTemplate restTemplate = new RestTemplate();
		String uri="/sm/v1.0/user-profile/"+principal.getName();
		
	    Users user =restTemplate.getForObject(uri, Users.class);*/
	    
		mvc.addObject("user", user);
		System.out.println(user);
		return mvc;
	}
	
	@GetMapping(value="/edit-profile/{userId}")
	public @ResponseBody ModelAndView editProfile(@PathVariable Integer userId, Principal principal){
		logger.debug(" edit user profile ...");
		ModelAndView mvc = new ModelAndView("/customer/edit-profile");
		Users user=userService.getUserById(userId);
	    
		mvc.addObject("user", user);
		System.out.println(user);
		return mvc;
	}
	
	
	@GetMapping(value=URLCONSTANT.SIGNUP_PAGE)
	public ModelAndView submitUserBasicInfo(@ModelAttribute UserDetailsDto detailsDto,HttpServletRequest request){
		if(logger.isTraceEnabled())
			logger.info("UserProfileController --- submitUserBasicInfo --start");
		ModelAndView mvc = new ModelAndView("/customer/edit-profile");
	    
		return mvc;
	}
	
}
