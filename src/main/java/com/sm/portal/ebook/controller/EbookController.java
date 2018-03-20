package com.sm.portal.ebook.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sm.portal.constants.URLCONSTANT;
import com.sm.portal.controller.EDairyController;
import com.sm.portal.digilocker.utils.DigiLockeUtils;
import com.sm.portal.ebook.enums.BookSizeEnum;
import com.sm.portal.ebook.enums.PageSizeEnum;
import com.sm.portal.ebook.model.Ebook;
import com.sm.portal.ebook.model.EbookPage;
import com.sm.portal.ebook.model.EbookPageBean;
import com.sm.portal.ebook.service.EbookServiceImpl;
import com.sm.portal.model.Users;
import com.sm.portal.service.FileUploadServices;
import com.sm.portal.service.UserService;


@RestController
@RequestMapping(URLCONSTANT.BASE_URL)
public class EbookController {

	Logger logger=Logger.getLogger(EDairyController.class.getName());
	@Autowired
	FileUploadServices fileUploadServices;
	
	@Autowired
    private EbookServiceImpl ebookServiceImple;
	
	@Autowired
    private UserService userService;
	
	@RequestMapping(value="/eBooklist", method=RequestMethod.GET)
	public ModelAndView getEbookList(@RequestParam(name="userId", required=false) Integer userId, Principal principal){
		ModelAndView mav = new ModelAndView("/ebook/ebook_home");
		Users user =userService.findUserByUserName(principal.getName());
		List<Ebook> ebookList = ebookServiceImple.getEbookList(userId);
		mav.addObject("EbookList", ebookList);
		if(user.getUserId()!=null)mav.addObject("userId",user.getUserId());
		else mav.addObject("userId",userId);
		return mav;
	}//getEbookList() closing
	
	@RequestMapping(value="/creatEbook", method=RequestMethod.GET)
	public ModelAndView creatEbook(@RequestParam Integer userId, @ModelAttribute("eBook") Ebook eBook){
		ModelAndView mav = new ModelAndView("/ebook/CreateNewBook");
		mav.addObject("userId",userId);
		mav.addObject("bookSizeEnumList", BookSizeEnum.values());
		mav.addObject("pageSizeEnumList", PageSizeEnum.values());
		return mav;
	}//getEbookList() closing
	
	@RequestMapping(value="/creatEbook", method=RequestMethod.POST)
	public ModelAndView creatEbookSubmit(@ModelAttribute Ebook eBook,BindingResult result, HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		DigiLockeUtils digiLockerUtils = new DigiLockeUtils();
		eBook.setBookId(digiLockerUtils.gerUniqueKey(request));
		ebookServiceImple.createEbook(eBook);
		mav.setViewName("redirect:/sm/eBooklist?userId="+eBook.getUserId());
		return mav;
	}//getEbookList() closing
	
	@RequestMapping(value="/updateEbookDetails", method=RequestMethod.POST)
	public ModelAndView updateEbookDetails(@ModelAttribute Ebook ebook){
		ModelAndView mav = new ModelAndView();
		ebookServiceImple.updateEbookDetails(ebook);
		return mav;
	}//getEbookList() closing
	
	@RequestMapping(value="/creatChapter", method=RequestMethod.POST)
	public ModelAndView creatChapter(@ModelAttribute EbookPageBean ebookPageBean){
		ModelAndView mav = new ModelAndView();
		ebookServiceImple.creatChapter(ebookPageBean);
		return mav;
		
	}//creatChapter() closing
	
	@RequestMapping(value="/updateChapter", method=RequestMethod.POST)
	public ModelAndView updateChapter(@ModelAttribute EbookPageBean ebookPageBean){
		ModelAndView mav = new ModelAndView();
		ebookServiceImple.updateChapter(ebookPageBean);
		return mav;
		
	}//creatChapter() closing
	
	@RequestMapping(value="/updateEbookPage", method=RequestMethod.POST)
	public ModelAndView updateEbookPage(@ModelAttribute EbookPageBean ebookPageBean){
		ModelAndView mav = new ModelAndView();
		ebookServiceImple.updateEbookPage(ebookPageBean);
		return mav;
		
	}//creatChapter() closing
}//class closing
