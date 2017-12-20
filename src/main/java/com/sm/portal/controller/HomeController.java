package com.sm.portal.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@GetMapping(value = "/")
	public  String homePage(Model model,Locale locale) {
		System.out.println("Starting page");
		return "home";
		
	}
	
	@GetMapping(value = "/aboutUs")
	public String aboutUs(Model model,Locale locale) {
		System.out.println("aboutUs page");
		return "aboutUs";
		
	}
	
	
	
}
