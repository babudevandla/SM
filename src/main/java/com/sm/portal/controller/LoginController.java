package com.sm.portal.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sm.portal.constants.URLCONSTANT;
import com.sm.portal.service.UserService;


@Controller
public class LoginController {
	
	Logger logger=Logger.getLogger(LoginController.class.getName());
	
	
	@Autowired
    private UserService userService;
	
	
	@RequestMapping(value=URLCONSTANT.LOGIN_PAGE,method=RequestMethod.GET)
	public ModelAndView showLoginForm(){
		System.out.println("login form");
		ModelAndView mv = new ModelAndView("/common/login");
		return mv;
	}
	
	
	
	@RequestMapping(value="/accessdenied", method=RequestMethod.GET)
	public ModelAndView showAccessDenied(){
		ModelAndView mv = new ModelAndView("/access-denied");
		mv.addObject("error", "true");
		return mv;
	}
	
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "/common/login";
 
	}
	
 	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session)
			throws Exception {
		if(session.isNew())
		{
			
		}
		session.invalidate();
		ModelAndView mav = new ModelAndView("redirect:/login");	
		return mav;
	}

}
