package com.sm.portal.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sm.portal.constants.URLCONSTANT;
import com.sm.portal.service.UserService;


@Controller
public class LoginController {
	
	Logger logger=Logger.getLogger(LoginController.class.getName());
	
	
	@Autowired
    private UserService userService;
	
	
	@GetMapping(value=URLCONSTANT.LOGIN_PAGE)
	public ModelAndView showLoginForm(@RequestParam(value="message",required=false) String message){
		System.out.println("login form");
		ModelAndView mvc = new ModelAndView("/common/login");
		mvc.addObject("message",message);
		return mvc;
	}
	
	
	
	@GetMapping(value="/accessdenied")
	public ModelAndView showAccessDenied(){
		ModelAndView mv = new ModelAndView("/access-denied");
		mv.addObject("error", "true");
		return mv;
	}
	
	
	@GetMapping(value="/loginfailed")
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "/common/login";
 
	}
	
	@GetMapping(value = "/logout")
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
