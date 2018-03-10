package com.sm.portal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.sm.portal.constants.URLCONSTANT;
import com.sm.portal.edairy.model.DairyInfo;
import com.sm.portal.edairy.model.DairyPage;
import com.sm.portal.edairy.model.UserDairies;
import com.sm.portal.edairy.service.EdairyServiceImpl;
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
	
	@Autowired
	EdairyServiceImpl edairyServiceImpl;
	
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
	
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody String uploadMultipleFileHandler(@RequestParam("pagecontent") String pagecontent, 
			 @RequestParam("files") MultipartFile files[]) {

		try {
            String filePath="c:/temp/kk/";
            StringBuffer result=new StringBuffer();
            byte[] bytes=null;
            result.append("Uploading of File(s) ");

            for (int i=0;i<files.length;i++) {	
                if (!files[i].isEmpty()) {
                    bytes = files[i].getBytes();
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath+files[i].getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();

                   result.append(files[i].getOriginalFilename() + " Ok. ") ;
                }
                else
                    result.append( files[i].getOriginalFilename() + " Failed. ");

        }
            return result.toString();

        } catch (Exception e) {
            return "Error Occured while uploading files." + " => " + e.getMessage();
        }

	} 
	
	@RequestMapping(value="/getUserDairiesList", method=RequestMethod.GET)
	public ModelAndView getUserDairiesList(Principal principal){
		logger.debug(" show user profile ...");
		ModelAndView mvc = new ModelAndView("/customer/user_dairies");
		Users user =this.getUserInfo(principal);
		
		UserDairies  userDairies=edairyServiceImpl.gerUserDairies(user.getUserId());
		mvc.addObject("user", user);
		mvc.addObject("userDairies", userDairies);
		return mvc;
	}//getUserDairiesList() closing
	
	@RequestMapping(value="/getDairyInfo/{userId}/{dairyId}", method=RequestMethod.GET)
	public ModelAndView getDairyInfo(Principal principal, @PathVariable("userId") Integer userId,
			@PathVariable("dairyId") Integer dairyId){
		logger.debug(" show user profile ...");
		
		Gson gson = new Gson();
		ModelAndView mvc = new ModelAndView("/customer/dairy_content");
		DairyInfo  dairyInfo=edairyServiceImpl.getDairyInfo(userId, dairyId);
		//mvc.addObject("showPageNo", 1);
		mvc.addObject("userId", userId);
		mvc.addObject("dairyInfo", dairyInfo);
		mvc.addObject("pages", dairyInfo.getPages());
		mvc.addObject("pagelist", gson.toJson(dairyInfo.getPages()));
		return mvc;
	}//getUserDairiesList() closing
	
	
	@RequestMapping(value="/editPageContent/{userId}/{dairyId}", method=RequestMethod.GET)
	public ModelAndView editPageContent(Principal principal, @PathVariable("userId") Integer userId,
			@PathVariable("dairyId") Integer dairyId, @RequestParam Integer pageNo,
			@RequestParam String  content){
		DairyPage page =new DairyPage();
		page.setPageNo(pageNo);
		page.setContent(content);
		logger.debug(" show user profile ...");
		ModelAndView mvc = new ModelAndView("/customer/edit_edairy");
		DairyInfo  dairyInfo=edairyServiceImpl.editPageContent(userId, dairyId, page);
		mvc.addObject("showPageNo", page.getPageNo());
		mvc.addObject("userId", userId);
		mvc.addObject("dairyId", dairyId);
		mvc.addObject("dairyInfo", dairyInfo);
		return mvc;
	}//getUserDairiesList() closing
	
	
	@RequestMapping(value="/savePageContent", method=RequestMethod.GET)
	public ModelAndView savePageContent(Principal principal, @PathVariable("userId") Integer userId,
			@PathVariable("dairyId") Integer dairyId, @ModelAttribute DairyInfo dairyInfo1){
		logger.debug(" show user profile ...");
		ModelAndView mvc = new ModelAndView("/customer/dairy_content");
		DairyInfo  dairyInfo=edairyServiceImpl.savePageContent(userId, dairyId, dairyInfo1.getPages().get(0));
		mvc.addObject("showPageNo", dairyInfo1.getPages().get(0).getPageNo());
		mvc.addObject("userId", userId);
		mvc.addObject("dairyId", dairyId);
		mvc.addObject("dairyInfo", dairyInfo);
		return mvc;
	}//getUserDairiesList() closing
	private Users getUserInfo(Principal principal){
		return userService.findUserByUserName(principal.getName());
	}//getUserInfo() closoing
	
}
