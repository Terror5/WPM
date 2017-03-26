
package com.wpm.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.wpm.exception.ProjectNotFound;
import com.wpm.model.Iteration;
import com.wpm.model.Project;
import com.wpm.model.SqlUser;
import com.wpm.model.Team;
import com.wpm.model.UserProjectRole;
import com.wpm.model.UserProjectRolePK;
import com.wpm.service.IterationService;
import com.wpm.service.ProjectService;
import com.wpm.service.UserProjectRoleService;
import com.wpm.service.UserService;
import com.wpm.validation.ProjectValidator;


@Controller				
@RequestMapping(value="/secured/project")
public class ProjectController {
        
        @Autowired
        private ProjectService projectService;
        
        @Autowired
        private UserService userService;
        
        @Autowired
        private IterationService iterationService;
        
        @Autowired
        private ProjectValidator projectValidator;
        
        @Autowired
        private UserProjectRoleService userProjectRoleService;
        
        @InitBinder
        private void initBinder(WebDataBinder binder) {
            binder.setValidator(projectValidator);
        } 
        
      
        @RequestMapping(value="/create", method=RequestMethod.GET)
        public ModelAndView newProjectPage(Principal principal,
        		final RedirectAttributes redirectAttributes) {
        		
        	 	SqlUser user = userService.findById(principal.getName());
        	 	ModelAndView mav = new ModelAndView();
        	 	
        	 	if(user.getTeam() != null){
        	 		mav.setViewName("secured/ProjectNew");
        	 		mav.addObject("project", new Project());
        	 	} else {
        	 		mav.setViewName("redirect:/secured/project/list.html");
        	 		String message = "You can not create a project without being in a team.";
        	 		redirectAttributes.addFlashAttribute("message", message); 
        	 	}
                return mav;
        }
        						
        @RequestMapping(value="/create", method=RequestMethod.POST)
        public ModelAndView createNewProject(@ModelAttribute @Valid Project project,
                        BindingResult result,
                        Principal principal,
                        final RedirectAttributes redirectAttributes) {
        	
                if (result.hasErrors())
                        return new ModelAndView("secured/ProjectNew");
                
                ModelAndView mav = new ModelAndView();                             
                
                SqlUser user = userService.findById(principal.getName());
                Team team = user.getTeam();
                project.setTeam(team);
                project = projectService.create(project);
                
                String message = "New Project "+project.getIdProject()+" was successfully created.";
                createIterations(project);
                createProjectRoles(team, project);
                
                mav.setViewName("redirect:/secured/project/list.html"); 
                                
                redirectAttributes.addFlashAttribute("message", message);        
                return mav;                
        }

        @RequestMapping(value="/list", method=RequestMethod.GET)
        public ModelAndView ProjectListPage() {
                ModelAndView mav = new ModelAndView("secured/ProjectList");
                List<Project> projectlist = projectService.findAll();
                mav.addObject("ProjectList", projectlist);
                return mav;
        }
        
        @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
        public ModelAndView editProjectPage(@PathVariable Integer id) {
                ModelAndView mav = new ModelAndView("secured/ProjectEdit");
                Project project = projectService.findById(id);
                mav.addObject("project", project);
                return mav;
        }
        
        @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
        public ModelAndView editProject(@ModelAttribute @Valid Project project,
                        BindingResult result,
                        @PathVariable Integer id,
                        final RedirectAttributes redirectAttributes) throws ProjectNotFound {
                
                if (result.hasErrors())
                        return new ModelAndView("secured/ProjectEdit");
                
                project.setIdProject(id);
                
                ModelAndView mav = new ModelAndView("redirect:/secured/project/list.html"); 
                String message = "Project was successfully updated." + project.getIdProject() + " PATH" + id;

                projectService.update(project);
                
                redirectAttributes.addFlashAttribute("message", message);  
                return mav;
        }
        
        @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
        public ModelAndView deleteProject(@PathVariable Integer id,
                        final RedirectAttributes redirectAttributes) throws ProjectNotFound {
                
                ModelAndView mav = new ModelAndView("redirect:/secured/project/list.html");                
                
                Project project = projectService.delete(id);
                String message = "The Project "+project.getIdProject()+"  "+project.getTitle()+" was successfully deleted.";
                
                redirectAttributes.addFlashAttribute("message", message);
                return mav;
        }
        
        public void createIterations(Project project){
        	Date refDate = project.getDateBegin();
        	int iterationCycle = project.getIterationCycle();
        	List<Iteration> iterationList = new ArrayList<Iteration>();
        	
        	while(refDate.before(project.getDateEnd())){
        		Iteration iteration = new Iteration();
        		iteration.setProject(project);
        		iteration.setStartIteration(refDate);
        		
	        	Calendar c = Calendar.getInstance();
	        	c.setTime(refDate);
	        	c.add(Calendar.DATE, (iterationCycle - 1));
	        	refDate = c.getTime();
	        	
	        	iteration.setEndIteration(refDate);
	        	iterationList.add(iteration);

	        	c.add(Calendar.DATE, 1);
	        	refDate = c.getTime();
        	}
        	
        	iterationService.createBatch(iterationList);
        }
        
      public void createProjectRoles(Team team, Project project){
    	  List<SqlUser> userlist = userService.findByTeam(team);
    	  List<UserProjectRole> rolelist = new ArrayList<UserProjectRole>();
    	  for(SqlUser user : userlist){
    		  UserProjectRole role = new UserProjectRole();
    		  role.setId(new UserProjectRolePK("DEV", project.getIdProject(), user.getIdUser()));;
    		  rolelist.add(role);
    	  }
    	  userProjectRoleService.createBatch(rolelist);
      }
        
}