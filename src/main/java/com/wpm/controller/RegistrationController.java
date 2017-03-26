
package com.wpm.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wpm.exception.UserNotFound;
import com.wpm.model.Forgot;
import com.wpm.model.Password;
import com.wpm.model.SqlUser;
import com.wpm.model.UserRole;
import com.wpm.model.UserRolePK;
import com.wpm.service.CustomUrlService;
import com.wpm.service.EmailService;
import com.wpm.service.TeamService;
import com.wpm.service.UserRoleService;
import com.wpm.service.UserService;
import com.wpm.validation.ForgotValidator;
import com.wpm.validation.PasswordValidator;

@Controller
@RequestMapping(value="/public")
public class RegistrationController {

	@Autowired
    private UserService userService;
    
	@Autowired
	private UserRoleService userRoleService;
	
    @Autowired
    private TeamService teamService;
    
    @Autowired
    private PasswordValidator passwordValidator;       
  
    @Autowired
    private CustomUrlService customUrlService;
    
    @Autowired
    private ForgotValidator forgotValidator;
    
    @Autowired
    private EmailService emailService;
    
	@Resource
	private Environment env;
    
    @RequestMapping(value="/register", method=RequestMethod.GET)
    public ModelAndView newUserPage() {
            ModelAndView mav = new ModelAndView("public/register", "password", new Password());
            return mav;
    }
    
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public ModelAndView createNewUser(@ModelAttribute("password") Password password,
                    BindingResult result,
                    final RedirectAttributes redirectAttributes) {
    	
    		passwordValidator.validate(password, result);
    		
            if (result.hasErrors()){          
                return new ModelAndView("public/register");
            }
            
            SqlUser user = password.getUser();
            String idUser = user.getIdUser();
            ModelAndView mav = new ModelAndView();
            String message = "New User "+idUser+" was successfully created.";
            
        	String url = env.getProperty("host.url") + "public/activate/user="+idUser+"&hash="+encodedPassword(idUser);
            
            emailService.sendActivationMail(user,url);
            
            user.setPwHash(encodedPassword(password.getPwString1()));
            userService.create(user);            
            userRoleService.create(new UserRole(new UserRolePK("USER",user.getIdUser())));	
           
            mav.setViewName("redirect:/secured/home"); 
                            
            redirectAttributes.addFlashAttribute("message", message);        
            return mav;                
    }    
    
    
    @RequestMapping(value="/activate/{id}", method=RequestMethod.GET)
    public ModelAndView activateUserPage(@PathVariable String id) throws UserNotFound{    	
    	ModelAndView mav = new ModelAndView("public/activate");
    	
    	String idUser = customUrlService.formatPathToString(id, "user");
    	String hash = customUrlService.formatPathToString(id, "hash");
    	
    	SqlUser user = userService.findById(idUser);
    	String message = "";
    	
    	if(isEqual(idUser, hash)){
    		message = "Welcome " + user.getFullName() + "! Your account was successfully activated.";
    		user.setActive(true);
    		userService.update(user);
    	} else {
    		message = "Wrong activation URL. Trying to send new activation E-Mail!";
    		if(user != null){
    			String url = env.getProperty("host.url") + "public/activate/user="+idUser+"&hash="+encodedPassword(idUser);
    			emailService.sendActivationMail(user,url);
    		}
    	}    	   	
    	
    	mav.addObject("message",message);
    	mav.addObject("title","Activation");
    	return mav;
    }
    
    @RequestMapping(value="/forgot", method=RequestMethod.GET)
    public ModelAndView forgotUserPage(){
    	ModelAndView mav = new ModelAndView("public/forgot");
    	mav.addObject("forgot", new Forgot());
    	mav.addObject("title","Password forgotten?");
    	return mav;
    }
   
    @RequestMapping(value="/forgot", method=RequestMethod.POST)
    public ModelAndView forgotUserForm(@ModelAttribute("forgot") Forgot forgot,
    									BindingResult result,
    									RedirectAttributes redirect){
    	forgotValidator.validate(forgot, result);
    	if(result.hasErrors()){
    		return new ModelAndView("public/forgot");
    	}
    	
    	SqlUser user = userService.findById(forgot.getIdUser());
    	String password = newPassword(8);
    	user.setPwHash(encodedPassword(password));
    	try {
    		userService.update(user);
		} catch (Exception e) {
			return new ModelAndView("public/forgot");
		}
    
    	emailService.sendPasswordMail(user, password);
    	
    	ModelAndView mav = new ModelAndView("redirect:/secured/home");
    	return mav;
    	
    }
    
    public String newPassword(int length){
    	String pw = "";
    	String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	for(int i = 1; i <= length; i++){	
    		int index = (int) (Math.random()*chars.length());
    		pw += chars.charAt(index);
    	}   	
    	return pw;
    }
    
    private boolean isEqual(String rawPassword, String encodedPassword){
    	
    	PasswordEncoder encoder = new StandardPasswordEncoder();
    	return encoder.matches(rawPassword, encodedPassword);
    }

    
    private String encodedPassword(String rawPassword)
    {
    	PasswordEncoder encoder = new StandardPasswordEncoder();
    	return encoder.encode(rawPassword);
    	
    }
}
