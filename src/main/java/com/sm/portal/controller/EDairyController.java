package com.sm.portal.controller;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sm.portal.constants.URLCONSTANT;
import com.sm.portal.model.EDairyDto;
import com.sm.portal.model.Users;
import com.sm.portal.service.EDairyService;
import com.sm.portal.service.UserService;

@RestController
@RequestMapping(URLCONSTANT.BASE_URL)
public class EDairyController {

	Logger logger=Logger.getLogger(EDairyController.class.getName());
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private EDairyService eDairyService;
	
	@RequestMapping(value="/e_dairy_list", method=RequestMethod.GET)
	public ModelAndView eDairyLists(Principal principal){
		logger.debug(" show user profile ...");
		ModelAndView mvc = new ModelAndView("/customer/e-dairy");
		Users user =userService.findUserByUserName(principal.getName());
		
		List<EDairyDto>  dairyList=eDairyService.getEDairyList(user.getUserId());
		mvc.addObject("user", user);
		mvc.addObject("dairyList", dairyList);
		return mvc;
	}
	
	
	@RequestMapping(value="/create_edairy", method=RequestMethod.GET)
	public ModelAndView createNewEDairy(@ModelAttribute("edairyDto") EDairyDto eDairyDto, Principal principal){
		logger.debug(" show user profile ...");
		ModelAndView mvc = new ModelAndView("/customer/create_edairy");
		mvc.addObject("edairyDto",eDairyDto);
		return mvc;
	}
	
	@RequestMapping(value="/submit_edairy", method=RequestMethod.POST)
	public ModelAndView saveNewEDairy(@ModelAttribute("edairyDto") EDairyDto eDairyDto, Principal principal){
		logger.debug(" show saveNewEDairy ...");
		ModelAndView mvc = new ModelAndView();
		Users user =userService.findUserByUserName(principal.getName());
		mvc.addObject("user", user);
		eDairyService.saveEDairyData(eDairyDto,user);
		mvc.setViewName("redirect:/sm/e_dairy_list");
		
		mvc.addObject("edairyDto",eDairyDto);
		return mvc;
	}


	@RequestMapping(value="/edit_edairy/{dairyId}", method=RequestMethod.GET)
	public ModelAndView editEDairy(@PathVariable("dairyId") Integer dairyId, Principal principal){
		logger.debug(" show edit edairy ...");
		ModelAndView mvc = new ModelAndView("/customer/edit_edairy");
		EDairyDto eDairyDto=eDairyService.getEDairyDataById(dairyId);
		mvc.addObject("edairyDto",eDairyDto);
		return mvc;
	}
	
	@RequestMapping(value="/edit_edairy", method=RequestMethod.POST)
	public ModelAndView saveEditEDairy(@ModelAttribute("edairyDto") EDairyDto eDairyDto, Principal principal){
		logger.debug(" show saveNewEDairy ...");
		ModelAndView mvc = new ModelAndView();
		Users user =userService.findUserByUserName(principal.getName());
		mvc.addObject("user", user);
		eDairyService.updateEDairyData(eDairyDto,user);
		mvc.setViewName("redirect:/sm/e_dairy_list");
		
		mvc.addObject("edairyDto",eDairyDto);
		return mvc;
	}
	
}
