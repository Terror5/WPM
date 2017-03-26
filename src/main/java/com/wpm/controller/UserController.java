
package com.wpm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wpm.exception.UserNotFound;
import com.wpm.model.SqlUser;
import com.wpm.model.Team;
import com.wpm.service.CustomUrlService;
import com.wpm.service.OpenUpRoleService;
import com.wpm.service.ProjectService;
import com.wpm.service.TeamService;
import com.wpm.service.UserProjectRoleService;
import com.wpm.service.UserService;
import com.wpm.validation.UserValidator;

@Controller
@RequestMapping(value="/admin/user")
public class UserController {
        	
	
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
        private UserValidator userValidator;
        
        @Autowired
        private CustomUrlService customUrlService;
        
        
        @InitBinder
        private void initBinder(WebDataBinder binder) {
        	binder.setValidator(userValidator);
        }
      
        @RequestMapping(value="/create", method=RequestMethod.GET)
        public ModelAndView newUserPage() {
                ModelAndView mav = new ModelAndView("admin/UserNew", "user", new SqlUser());
                List<Team> teamList = teamService.findAll();
                mav.addObject("teamList", teamList);
                return mav;
        }
        
        @RequestMapping(value="/create", method=RequestMethod.POST)
        public ModelAndView createNewUser(@ModelAttribute("user") @Valid SqlUser user,
                        BindingResult result,
                        final RedirectAttributes redirectAttributes) {
                
                if (result.hasErrors()){          
                    return new ModelAndView("admin/UserNew","teamList",teamService.findAll());
                }
                
                ModelAndView mav = new ModelAndView();
                String message = "New User "+user.getIdUser()+" was successfully created.";
                
                user.setPwHash(encodedPassword(user.getPwHash()));
                userService.create(user);
               
                mav.setViewName("redirect:/admin/user/list.html"); 
                                
                redirectAttributes.addFlashAttribute("message", message); 
                return mav;                
        }
        
        @RequestMapping(value="/list", method=RequestMethod.GET)
        public ModelAndView UserListPage() {
                ModelAndView mav = new ModelAndView("admin/UserList");
                List<SqlUser> userList = userService.findAll();
                mav.addObject("UserList", userList);
                return mav;
        }
        
        @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
        public ModelAndView editUserPage(@PathVariable String id) {
                ModelAndView mav = new ModelAndView("admin/UserEdit");
                SqlUser user = userService.findById(id);
                mav.addObject("user", user);
                List<Team> teamList = teamService.findAll();
                mav.addObject("teamList", teamList);
                return mav;
        }
        
        @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
        public ModelAndView editUser(@ModelAttribute("user") @Valid SqlUser user,
                        BindingResult result,
                        @PathVariable String id,
                        final RedirectAttributes redirectAttributes) throws UserNotFound {
                
        		if(!(result.getErrorCount() == 1 && result.hasFieldErrors("idUser"))){
		            if (result.hasErrors())                		
		                    return new ModelAndView("admin/UserEdit","teamList",teamService.findAll());                
        		}
                ModelAndView mav = new ModelAndView("redirect:/admin/user/list.html"); 
                String message = "User  "+ id +"was successfully updated.";
                
                user.setIdUser(id);
                userService.update(user);
                
                redirectAttributes.addFlashAttribute("message", message);   
                return mav;
        }
        
        @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
        public ModelAndView deleteUser(@PathVariable String id,
                        final RedirectAttributes redirectAttributes) throws UserNotFound {
                
                ModelAndView mav = new ModelAndView("redirect:/admin/user/list.html");                
                
                SqlUser user = userService.delete(id);
                String message = "The User "+user.getIdUser()+"  "+user.geteMail()+" was successfully deleted.";
                
                redirectAttributes.addFlashAttribute("message", message);
                return mav;
        }        
        
        private String encodedPassword(String rawPassword)
        {
        	PasswordEncoder encoder = new StandardPasswordEncoder();
        	return encoder.encode(rawPassword);
        	
        }

}