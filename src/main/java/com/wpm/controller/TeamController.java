package com.wpm.controller;

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

import com.wpm.exception.TeamNotFound;
import com.wpm.model.Team;
import com.wpm.model.SqlUser;
import com.wpm.model.TeamUsers;
import com.wpm.service.TeamService;
import com.wpm.service.UserService;
//import com.wpm.validation.TeamValidator;

@Controller
@RequestMapping(value="/admin/team")
public class TeamController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TeamService teamService;
    
    /*@Autowired
    private TeamValidator teamValidator;*/
    
    @InitBinder
    private void initBinder(WebDataBinder binder) {      	
    	//binder.setValidator(teamValidator);
    } 

    @RequestMapping(value="/editTeamUser/{id}", method=RequestMethod.GET)
    public ModelAndView editTeamUserPage(@PathVariable Integer id) {
   		ModelAndView view = new ModelAndView("admin/TeamUserEdit");
   		Team team = teamService.findById(id);
   		TeamUsers teamUsers = new TeamUsers(userService.findByTeamIsNull());
     	view.addObject("team", team);
     	view.addObject("teamUsers",teamUsers);
        return view;
    }
    
    @RequestMapping(value="/editTeamUser/{id}", method=RequestMethod.POST)
    public ModelAndView editTeamUser(@ModelAttribute @Valid Team team,
    				@PathVariable Integer id,
    				BindingResult result,
                    final RedirectAttributes redirectAttributes) throws TeamNotFound {
            
            
            ModelAndView mav = new ModelAndView("redirect:/admin/team/list.html");
            
            List<SqlUser> list = team.getUsers();
            String message = "";
            if(list != null){
		        for(SqlUser usr : list){
		        	message += usr.getIdUser() + " name " + usr.getIdUser()+ " \n"; 
		        }
            }
            
            //String message = "Team: "+team.getIdTeam()+" Title: "+team.getTitle()+" was updated successfully.";
            
            //teamService.update(team);
            
            redirectAttributes.addFlashAttribute("message", message); 
            return mav;
    } 
    
    @RequestMapping(value="/create", method=RequestMethod.GET)
    public ModelAndView newTeamPage() {
            ModelAndView mav = new ModelAndView("admin/TeamNew", "team", new Team());
            return mav;
    }
    
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView createNewTeam(@ModelAttribute("team") @Valid Team team,
                    BindingResult result,
                    final RedirectAttributes redirectAttributes) {
            
            if (result.hasErrors())
                    return new ModelAndView("admin/TeamNew");
            
            ModelAndView mav = new ModelAndView();
            String message = "New Team "+team.getIdTeam()+"  "+team.getTitle()+" was successfully created.";
            
            teamService.create(team);
            mav.setViewName("redirect:/admin/team/list.html"); 
                            
            redirectAttributes.addFlashAttribute("message", message); 
            return mav;                
    }
    
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ModelAndView UserListPage() {
            ModelAndView mav = new ModelAndView("admin/TeamList");
            List<Team> teamList = teamService.findAll();
            mav.addObject("TeamList", teamList);
            return mav;
    }
    
    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editUserPage(@PathVariable Integer id) {
            ModelAndView mav = new ModelAndView("admin/TeamEdit");
            Team team = teamService.findById(id);
            mav.addObject("team", team);
            return mav;
    }
    
    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editTeam(@ModelAttribute("team") @Valid Team team,
                    BindingResult result,
                    @PathVariable Integer id,
                    final RedirectAttributes redirectAttributes) throws TeamNotFound {
            
            if (result.hasErrors())
                    return new ModelAndView("admin/TeamEdit");
            
            team.setIdTeam(id);
            
            ModelAndView mav = new ModelAndView("redirect:/admin/team/list.html"); 
            String message = "User was successfully updated." + team.getIdTeam() + " PATH" + id;

            teamService.update(team);
            
            redirectAttributes.addFlashAttribute("message", message); 
            return mav;
    }
    
    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteTeam(@PathVariable Integer id,
                    final RedirectAttributes redirectAttributes) throws TeamNotFound {
            
            ModelAndView mav = new ModelAndView("redirect:/admin/team/list.html");                
            
            Team team = teamService.delete(id);
            String message = "The User "+team.getIdTeam()+"  "+team.getTitle()+" was successfully deleted.";
            
            redirectAttributes.addFlashAttribute("message", message);
            return mav;
    }
       
}
  


