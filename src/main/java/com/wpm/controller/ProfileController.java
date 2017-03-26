
package com.wpm.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wpm.exception.UserNotFound;
import com.wpm.model.Password;
import com.wpm.model.Project;
import com.wpm.model.ProjectForm;
import com.wpm.model.Role;
import com.wpm.model.SqlUser;
import com.wpm.model.Team;
import com.wpm.service.CustomUrlService;
import com.wpm.service.OpenUpRoleService;
import com.wpm.service.ProjectService;
import com.wpm.service.TeamService;
import com.wpm.service.UserProjectRoleService;
import com.wpm.service.UserService;
import com.wpm.validation.PasswordValidator2;

@Controller
@RequestMapping(value="/secured")
public class ProfileController {
        	
	
        @Autowired
        private UserService userService;
        
        @Autowired
        private ProjectService projectService;
        
        @Autowired
        private UserProjectRoleService userProjectRoleService;
        
        @Autowired
        private TeamService teamService;
        
        @Autowired
        private OpenUpRoleService openUpRoleService;
        
        @Autowired
        private CustomUrlService customUrlService;
        
        @Autowired
        private PasswordValidator2 passwordValidator;
        
      
        @RequestMapping(value="/profile", method=RequestMethod.GET)
        public ModelAndView profilePage(Principal principal) {
                ModelAndView mav = new ModelAndView("secured/profile");
                
                SqlUser user = userService.findByIdAndFetchRolesEagerly(principal.getName());               
                List<Role> roleList = user.getRoles();
                Team team = user.getTeam(); 
                if(team != null){
                	List<Project> projectList  = projectService.findByTeamAndFetchRolesEagerly(team); 
                	 mav.addObject("projectList",projectList);
                }
                Project project = user.getProject();
                if(project != null){
                    mav.addObject("pTitle",project.getTitle());
                }
                                             
                mav.addObject("projectForm", new ProjectForm());
                mav.addObject("roleList",roleList);            
                mav.addObject("test",principal.getName());
                return mav;
        }
        
        @RequestMapping(value="/profile/password", method=RequestMethod.GET)
        public ModelAndView profilePasswordPage(Principal principal) {
                ModelAndView mav = new ModelAndView("secured/profilePassword");
                
                SqlUser user = userService.findById(principal.getName());
                Password password = new Password();
                password.setUser(user);     
                
                mav.addObject("password",password);
                return mav;
        }
        
        @RequestMapping(value="/profile/password", method=RequestMethod.POST)
        public ModelAndView profilePagePassword(@ModelAttribute("password") Password password, BindingResult result,
        										final RedirectAttributes redirect, Principal principal) throws UserNotFound{
        		passwordValidator.validate(password, result);
        		if(result.hasErrors()){
        			return new ModelAndView("secured/profilePassword");
        		}
        		SqlUser user = userService.findById(principal.getName());
        		user.setPwHash(encodedPassword(password.getPwString1()));
        		userService.update(user);
        		
        		String message = "Your password has been changed.";
        		redirect.addFlashAttribute("message", message);
                ModelAndView mav = new ModelAndView("redirect:/secured/profile");
                return mav;
        }
        
        @RequestMapping(value="/profile/project", method=RequestMethod.POST)
        public ModelAndView profilePageProject(@ModelAttribute("projectForm") ProjectForm projectForm,
        									   BindingResult result,
        									   final RedirectAttributes redirect, 
        									   Principal principal) throws UserNotFound {
        	
        		SqlUser user = userService.findById(principal.getName());
        		user.setProject(projectForm.getProject());
        		userService.update(user);
        		
        		String message = "Your preferred project was succesfully changed.";
        		redirect.addFlashAttribute("message", message);
                ModelAndView mav = new ModelAndView("redirect:/secured/profile");
                return mav;
        }
        
        
        private String encodedPassword(String rawPassword)
        {
        	PasswordEncoder encoder = new StandardPasswordEncoder();
        	return encoder.encode(rawPassword);
        	
        }
        
}