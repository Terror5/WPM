package com.wpm.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller  
@RequestMapping(value="/secured/report")
public class ReportController {
	
      
    @RequestMapping(value="/list", method=RequestMethod.GET)  
    public ModelAndView loginForm() {  
        return new ModelAndView("secured/home");  
    }

}  