package com.wpm.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wpm.service.IterationService;
import com.wpm.service.UserService;

@Controller
public class NavigationController {
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private IterationService iterationService;

   	@RequestMapping(value="/secured/home", method=RequestMethod.GET)
   	public ModelAndView newHomePage(Principal principal){
   		ModelAndView mav = new ModelAndView("secured/home","winuser",principal.getName());   		
   		return mav;
   	}

    
   	@RequestMapping(value={"/secured/ProjectControl"}, method=RequestMethod.GET)
   	public ModelAndView newProjectControlPage()
   	{
   		return new ModelAndView("secured/ProjectControl");
   	}
   	       
   	
   	@RequestMapping(value="/admin/control", method=RequestMethod.GET)
   	public ModelAndView newAdminControlPage(){
   		
   		ModelAndView mav = new ModelAndView("admin/control");
   		return mav;
   	}     	                      
}


