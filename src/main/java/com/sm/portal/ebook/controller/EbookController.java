package com.sm.portal.ebook.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sm.portal.constants.URLCONSTANT;
import com.sm.portal.controller.EDairyController;
import com.sm.portal.ebook.model.Ebook;
import com.sm.portal.ebook.model.EbookPage;
import com.sm.portal.ebook.model.EbookPageBean;
import com.sm.portal.ebook.service.EbookServiceImpl;
import com.sm.portal.service.FileUploadServices;

@RestController
@RequestMapping(URLCONSTANT.BASE_URL)
public class EbookController {

	Logger logger=Logger.getLogger(EDairyController.class.getName());
	@Autowired
	FileUploadServices fileUploadServices;
	
	@Autowired
    private EbookServiceImpl ebookServiceImple;
	
	@RequestMapping(value="/eBooklist", method=RequestMethod.GET)
	public ModelAndView getEbookList(@RequestParam Integer userId){
		ModelAndView mav = new ModelAndView();
		
		List<Ebook> ebookList = ebookServiceImple.getEbookList(userId);
		return mav;
	}//getEbookList() closing
	
	@RequestMapping(value="/creatEbook", method=RequestMethod.GET)
	public ModelAndView creatEbook(@RequestParam Integer userId){
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}//getEbookList() closing
	
	@RequestMapping(value="/creatEbook", method=RequestMethod.POST)
	public ModelAndView creatEbookSubmit(@ModelAttribute Ebook ebook){
		ModelAndView mav = new ModelAndView();
		ebookServiceImple.createEbook(ebook);
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
