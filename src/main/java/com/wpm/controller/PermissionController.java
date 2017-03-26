package com.wpm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wpm.model.Project;
import com.wpm.model.SqlUser;
import com.wpm.model.Team;
import com.wpm.model.UserProjectRole;
import com.wpm.model.UserProjectRolePK;
import com.wpm.service.CustomUrlService;
import com.wpm.service.OpenUpRoleService;
import com.wpm.service.ProjectService;
import com.wpm.service.TeamService;
import com.wpm.service.UserProjectRoleService;
import com.wpm.service.UserService;
import com.wpm.validation.UserValidator;

@Controller
@RequestMapping(value="/admin/permission")
public class PermissionController {
	
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
	
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ModelAndView permissionList(@PathVariable String id,
                    final RedirectAttributes redirectAttributes) {
    	       	
    	SqlUser user = userService.findById(id);
    	if(user.getTeam() == null){
    		String message = "The selected user has no team. Please assign one!";
    		redirectAttributes.addFlashAttribute("message", message);
    		return new ModelAndView("redirect:/admin/user/list.html");
    	}

    	ModelAndView mav = new ModelAndView("admin/ProjectList");
    	
    	Team team = user.getTeam();
		List<Project> projectList = projectService.findByTeamAndFetchRolesEagerly(team);        	
    	mav.addObject("ProjectList", projectList);
    	mav.addObject("idUser",id);
    	
    	String message = "Selected User: " + user.getFullName() + " ID: " + id +" Team " + team.getIdTeam();
       	mav.addObject("message",message);
       	
    	mav.addObject("test",true);   		
    	return mav;
    	
    }
    
    @RequestMapping(value="/add/{id}", method=RequestMethod.GET)
    public ModelAndView addPermission(@PathVariable String id,
                    final RedirectAttributes redirectAttributes) {
    	
    	int idProject = customUrlService.formatPathToInt(id, "project");
    	String idUser = customUrlService.formatPathToString(id, "user");
    	
    	Project project = new Project();
    	project.setIdProject(idProject);
    	
    	SqlUser user = new SqlUser();
    	user.setIdUser(idUser);

    	//String message = "Selected User: ID: " + id;
    	
        ModelAndView mav = new ModelAndView("admin/PermissionAdd");
        UserProjectRole userProjectRole = new UserProjectRole();
        userProjectRole.setProject(project);
        userProjectRole.setUser(user);
        mav.addObject("idProject", idProject);
        mav.addObject("idUser", idUser);
        mav.addObject("userProjectRole", userProjectRole);
        mav.addObject("openuproleList", openUpRoleService.findAll());            
        return mav;       	
    }
    
    @RequestMapping(value="/addRole/{id}", method=RequestMethod.GET)
    public ModelAndView createNewPermission(@PathVariable String id,
                    final RedirectAttributes redirectAttributes) {
            
    		int idProject = customUrlService.formatPathToInt(id, "project");
    		String idUser = customUrlService.formatPathToString(id, "user");
    		String idOpenUpRole = customUrlService.formatPathToString(id, "openuprole");
            
            ModelAndView mav = new ModelAndView();
            String message = "New Role "+idOpenUpRole+" for User "+idUser+
            				 " and Project "+idProject+" was successfully created.";
            
            UserProjectRole userProjectRole = new UserProjectRole();
            userProjectRole.setId(new UserProjectRolePK(idOpenUpRole,idProject,idUser));
            userProjectRoleService.create(userProjectRole);
           
            mav.setViewName("redirect:/admin/user/list.html");                                
            redirectAttributes.addFlashAttribute("message", message);  
            return mav;                
    }
    
    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deletePermission(@PathVariable String id,
                    final RedirectAttributes redirectAttributes) {
    	
		int idProject = customUrlService.formatPathToInt(id, "project");
		String idUser = customUrlService.formatPathToString(id, "user");
		String idOpenUpRole = customUrlService.formatPathToString(id, "openuprole");
		
        ModelAndView mav = new ModelAndView();
        String message = "New Role "+idOpenUpRole+" for User "+idUser+
        				 " and Project "+idProject+" was successfully deleted.";
		
        userProjectRoleService.delete(new UserProjectRolePK(idOpenUpRole,idProject,idUser));
        
        mav.setViewName("redirect:/admin/user/list.html");                                
        redirectAttributes.addFlashAttribute("message", message); 
    	return mav;   	
    }   
}
