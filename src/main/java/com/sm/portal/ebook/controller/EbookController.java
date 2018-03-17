package com.sm.portal.ebook.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sm.portal.constants.URLCONSTANT;
import com.sm.portal.controller.EDairyController;
import com.sm.portal.service.FileUploadServices;

@RestController
@RequestMapping(URLCONSTANT.BASE_URL)
public class EbookController {

	Logger logger=Logger.getLogger(EDairyController.class.getName());
	@Autowired
	FileUploadServices fileUploadServices;
	
	
	
}//class closing
