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

import com.wpm.exception.WorkitemNotFound;
import com.wpm.model.Iteration;
import com.wpm.model.Project;
import com.wpm.model.Workitem;
import com.wpm.service.CustomUrlService;
import com.wpm.service.IterationService;
import com.wpm.service.ProjectService;
import com.wpm.service.WorkitemService;
import com.wpm.validation.WorkitemValidator;


@Controller
@RequestMapping(value="/secured/workitem")
public class WorkitemController {
	
	@Autowired
    private WorkitemService workitemService;
    
    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private IterationService iterationService;
    
    @Autowired
    private WorkitemValidator workitemValidator;
    
    @Autowired
    private CustomUrlService customUrlService;
    
    @InitBinder
    private void initBinder(WebDataBinder binder) {
		binder.setValidator(workitemValidator);
    } 
    
  
    @RequestMapping(value="/create/{id}", method=RequestMethod.GET)
    public ModelAndView newWorkitemPage(@PathVariable String id) {
    		int idProject = customUrlService.formatPathToInt(id,"project");
    		int idIteration = customUrlService.formatPathToInt(id,"iteration");
    		int ref = customUrlService.formatPathToInt(id,"ref");   
    		// 0 Iterationplan || 1 WIList || 2 Projectplan || 3 WIList Highlvl 
   			
    		Project project = new Project();
    		project.setIdProject(idProject);
    		Workitem workitem = new Workitem();
    		workitem.setProject(project);
    		
            ModelAndView mav = new ModelAndView("secured/WorkitemNew", "workitem", workitem);
            mav.addObject("idProject", idProject);
            mav.addObject("idIteration",idIteration);
            mav.addObject("ref",ref);
            return mav;
    }
    
    @RequestMapping(value="/create/{id}", method=RequestMethod.POST)
    public ModelAndView createNewWorkitem(@ModelAttribute @Valid Workitem workitem,
    				@PathVariable String id,
                    BindingResult result,
                    final RedirectAttributes redirectAttributes) {
    	
            if (result.hasErrors())
                    return new ModelAndView("secured/WorkitemNew");
            
        	int idProject = customUrlService.formatPathToInt(id,"project");
        	int idIteration = customUrlService.formatPathToInt(id,"iteration");
        	int ref = customUrlService.formatPathToInt(id,"ref");   
        	// 0 Iterationplan || 1 WIList || 2 Projectplan || 3 WIList Highlvl 
                        
            ModelAndView mav = new ModelAndView();
            mav = redirectViewName(mav, ref, idProject, idIteration);           
            
            if(idIteration != -1){
            	workitem.setIteration(new Iteration(idIteration));
            }
            workitemService.create(workitem);
            
            String message = "New Workitem "+workitem.getIdWorkitem()+" was successfully created.";                
            redirectAttributes.addFlashAttribute("message", message);        
            return mav;                
    }

    @RequestMapping(value="/list/{id}", method=RequestMethod.GET)
    public ModelAndView WorkitemListPage(@PathVariable String id) {
    	
   			int idProject = customUrlService.formatPathToInt(id,"project");
   			int idIteration = customUrlService.formatPathToInt(id,"iteration");
   			int ref = customUrlService.formatPathToInt(id,"ref");
    	
            ModelAndView mav = new ModelAndView("secured/WorkitemList");
            Project project = new Project();
            project.setIdProject(idProject);
            
            List<Workitem> workitemlist;
            if(ref == 3){
            	workitemlist = workitemService.findByProjectAndType(project, (byte) 0); // Byte 0 = HIGHlvl         
            } else if (ref == 1){
            	workitemlist = workitemService.findByProjectAndTypeNot(project, (byte) 0);
            } else {
            	workitemlist = workitemService.findByProject(project);
            }
            
            mav.addObject("workitemList", workitemlist);
            mav.addObject("idProject", idProject);
            mav.addObject("idIteration", idIteration);
            mav.addObject("ref",ref);
            return mav;
    }
    
    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editWorkitemPage(@PathVariable String id) {
    		int idWorkitem = customUrlService.formatPathToInt(id,"workitem");
        	int idProject = customUrlService.formatPathToInt(id,"project");
        	int idIteration = customUrlService.formatPathToInt(id,"iteration");
        	int ref = customUrlService.formatPathToInt(id,"ref");   
        	// 0 Iterationplan || 1 WIList || 2 Projectplan || 3 WIList Highlvl 
    		
            ModelAndView mav = new ModelAndView("secured/WorkitemEdit");
            Workitem workitem = workitemService.findById(idWorkitem);
            mav.addObject("workitem", workitem);
            mav.addObject("idProject", idProject);
            mav.addObject("idIteration",idIteration);
            mav.addObject("ref",ref);
            return mav;
    }
    
    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editWorkitem(@ModelAttribute @Valid Workitem workitem,
                    BindingResult result,
                    @PathVariable String id,
                    final RedirectAttributes redirectAttributes) throws WorkitemNotFound {
            
            if (result.hasErrors())
                    return new ModelAndView("secured/WorkitemEdit");
            
            int idWorkitem = customUrlService.formatPathToInt(id,"workitem");
        	int idProject = customUrlService.formatPathToInt(id,"project");
        	int idIteration = customUrlService.formatPathToInt(id,"iteration");
        	int ref = customUrlService.formatPathToInt(id,"ref");   
        	// 0 Iterationplan || 1 WIList || 2 Projectplan || 3 WIList Highlvl 
    		    		
            workitem.setIdWorkitem(idWorkitem);
            
            ModelAndView mav = new ModelAndView();
            mav = redirectViewName(mav, ref, idProject, idIteration);

            if(idIteration != -1){
            	workitem.setIteration(new Iteration(idIteration));
            }
            workitemService.update(workitem);
            
            String message = "Workitem "+idWorkitem+" was successfully updated.";
            redirectAttributes.addFlashAttribute("message", message);        
            return mav;
    }
    
    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteWorkitem(@PathVariable String id,
                    final RedirectAttributes redirectAttributes) throws WorkitemNotFound {
    	
        	int idWorkitem = customUrlService.formatPathToInt(id,"workitem");
        	int idProject = customUrlService.formatPathToInt(id,"project");
        	int idIteration = customUrlService.formatPathToInt(id,"iteration");
        	int ref = customUrlService.formatPathToInt(id,"ref");   
        	// 0 Iterationplan || 1 WIList || 2 Projectplan || 3 WIList Highlvl     
                           
            
            Workitem workitem = workitemService.delete(idWorkitem);

            ModelAndView mav = new ModelAndView();
            mav = redirectViewName(mav, ref, idProject, idIteration);
            
            String message = "The Project "+workitem.getIdWorkitem()+"  "+workitem.getTitle()+" was successfully deleted.";           
            redirectAttributes.addFlashAttribute("message", message);
            return mav;
    }
    
    private ModelAndView redirectViewName(ModelAndView mav, int ref, int idProject, int idIteration){
    	
    	switch (ref) {
		case 0:
			mav.setViewName("redirect:/secured/iterationplan/list/project="+idProject+"&iteration="+idIteration+".html"); // Iterationplan
			break;
		case 1:
			mav.setViewName("redirect:/secured/workitem/list/project="+idProject+"&ref="+ref+"&iteration="+idIteration+".html"); // WIList normal
			break;
		case 2:
			mav.setViewName("redirect:/secured/iterationplan/projectplan/"+idProject+".html"); // Projectplan
			break;
		case 3:
			mav.setViewName("redirect:/secured/workitem/list/project="+idProject+"&ref="+ref+"&iteration="+idIteration+".html"); // WIList Highlvl
			break;
		default:
			mav.setViewName("redirect:/secured/workitem/list/project="+idProject+".html");   // WIList All
			break;
		}
    	
    	return mav;
    }

}
