package com.wpm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wpm.service.NewUsersService;


@Controller  
public class LoginController {
	
	@Autowired
	private NewUsersService test; 
      
    @RequestMapping(value="/public/login", method=RequestMethod.GET)  
    public ModelAndView loginForm() {  
        return new ModelAndView("public/login");  
    }
    
    @RequestMapping(value="/public/admin", method=RequestMethod.GET)  
    public ModelAndView createAdmin() {
    	test.createAdminAccount();
        return new ModelAndView("redirect:/secured/home.html");  
    }
   
}  