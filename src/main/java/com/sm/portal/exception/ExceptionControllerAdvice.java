package com.sm.portal.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ModelAndView exception(HttpServletRequest req,HttpServletResponse response,Exception ex) {
		
		ModelAndView mav = new ModelAndView("/error");
		ex.printStackTrace();
		mav.addObject("errorMsg", ex.getMessage());
	    mav.addObject("url", req.getRequestURL());
		mav.addObject("backUrl", req.getHeader("Referer")); 
		System.out.println("Error Message::"+ex.getLocalizedMessage());
		
		return mav;
	}
	
	
	 
}
