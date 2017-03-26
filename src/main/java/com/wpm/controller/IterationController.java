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

import com.wpm.exception.IterationNotFound;
import com.wpm.model.Project;
import com.wpm.model.Iteration;
import com.wpm.service.CustomUrlService;
import com.wpm.service.ProjectService;
import com.wpm.service.IterationService;
import com.wpm.validation.IterationValidator;


@Controller
@RequestMapping(value="/secured/iteration")
public class IterationController {
	
	@Autowired
    private IterationService iterationService;
    
    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private IterationValidator iterationValidator;
    
    @Autowired
    private CustomUrlService customUrlService;
    
    @InitBinder
    private void initBinder(WebDataBinder binder) {
		binder.setValidator(iterationValidator);
    } 
    
  
    @RequestMapping(value="/create/{idProject}", method=RequestMethod.GET)
    public ModelAndView newIterationPage(@PathVariable Integer idProject) {
    		Project project = new Project();
    		project.setIdProject(idProject);
    		Iteration iteration = new Iteration();
    		iteration.setProject(project);
            ModelAndView mav = new ModelAndView("secured/IterationNew", "iteration", iteration);
            mav.addObject("idProject", idProject);
            return mav;
    }
    
    @RequestMapping(value="/create/{idProject}", method=RequestMethod.POST)
    public ModelAndView createNewIteration(@ModelAttribute @Valid Iteration iteration,
    				@PathVariable Integer idProject,
                    BindingResult result,
                    final RedirectAttributes redirectAttributes) {
    	
            if (result.hasErrors())
                    return new ModelAndView("secured/IterationNew");
            
            ModelAndView mav = new ModelAndView();
            String message = "New Iteration "+iteration.getIdIteration()+" was successfully created.";
            
            iterationService.create(iteration);
           mav.setViewName("redirect:/secured/iteration/list/"+idProject+".html");  // new model and view
                            
            redirectAttributes.addFlashAttribute("message", message);   
            return mav;                
    }

    @RequestMapping(value="/list/{idProject}", method=RequestMethod.GET)
    public ModelAndView IterationListPage(@PathVariable Integer idProject) {
            ModelAndView mav = new ModelAndView("secured/IterationList");
            Project project = new Project();
            project.setIdProject(idProject);
            List<Iteration> iterationlist =iterationService.findByProject(project);
            mav.addObject("iterationList", iterationlist);
            mav.addObject("idProject", idProject);
            return mav;
    }
    
    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editIterationPage(@PathVariable String id) {
			int idProject = customUrlService.formatPathToInt(id,"project");
			int idIteration = customUrlService.formatPathToInt(id,"iteration");
            ModelAndView mav = new ModelAndView("secured/IterationEdit");
            Iteration iteration = iterationService.findById(idIteration);
            mav.addObject("iteration", iteration);
            mav.addObject("idProject", idProject);
            return mav;
    }
    
    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editIteration(@ModelAttribute @Valid Iteration iteration,
                    BindingResult result,
                    @PathVariable String id,
                    final RedirectAttributes redirectAttributes) throws IterationNotFound {
            
            if (result.hasErrors())
                    return new ModelAndView("secured/IterationEdit");
            
			int idProject = customUrlService.formatPathToInt(id,"project");
			int idIteration = customUrlService.formatPathToInt(id,"iteration");
            
            iteration.setIdIteration(idIteration);
            
            ModelAndView mav = new ModelAndView("redirect:/secured/iteration/list/"+idProject+".html"); 
            String message = "Iteration was successfully updated.";

            iterationService.update(iteration);
            
            redirectAttributes.addFlashAttribute("message", message);    
            return mav;
    }
    
    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteIteration(@PathVariable String id,
                    final RedirectAttributes redirectAttributes) throws IterationNotFound {
            
			int idProject = customUrlService.formatPathToInt(id,"project");
			int idIteration = customUrlService.formatPathToInt(id,"iteration");               
            
           iterationService.delete(idIteration);
       
           ModelAndView mav = new ModelAndView("redirect:/secured/iteration/list/"+idProject+".html");
            String message = "The Iteration "+  idIteration +" was successfully deleted.";
            
            redirectAttributes.addFlashAttribute("message", message);
            return mav;
    }
 

}
