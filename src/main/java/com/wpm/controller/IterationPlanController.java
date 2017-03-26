package com.wpm.controller;


import java.security.Principal;
import java.util.ArrayList;
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

import com.wpm.exception.TaskNotFound;
import com.wpm.exception.WorkitemNotFound;
import com.wpm.model.Iteration;
import com.wpm.model.Project;
import com.wpm.model.SqlUser;
import com.wpm.model.Workitem;
import com.wpm.model.Task;
import com.wpm.service.CustomUrlService;
import com.wpm.service.IterationService;
import com.wpm.service.UserService;
import com.wpm.service.WorkitemService;
import com.wpm.service.TaskService;
import com.wpm.validation.TaskValidator;


@Controller
@RequestMapping(value="/secured/iterationplan")
public class IterationPlanController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
    private WorkitemService workitemService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private IterationService iterationService;
    
    @Autowired
    private TaskValidator taskValidator;
    
    @Autowired
    private CustomUrlService customUrlService;
    
    @InitBinder
    private void initBinder(WebDataBinder binder) {
		binder.setValidator(taskValidator);
    } 
    
  
    @RequestMapping(value="/create/{id}", method=RequestMethod.GET)
    public ModelAndView newTaskPage(@PathVariable String id, Principal principal) {

    		int idIteration = customUrlService.formatPathToInt(id, "iteration");
    		int idWorkitem = customUrlService.formatPathToInt(id, "workitem");
    		int ref = customUrlService.formatPathToInt(id, "ref"); // 0 for Iteratioplan 1 for Tasklist
    		
    		Task task = new Task(new Workitem(idWorkitem),new Iteration(idIteration));
    		
    		SqlUser user = userService.findById(principal.getName());
    		List<SqlUser> userlist = userService.findByTeam(user.getTeam());

            ModelAndView mav = new ModelAndView("secured/TaskNew", "task", task);
            mav.addObject("idWorkitem", idWorkitem);
            mav.addObject("idIteration", idIteration);
            mav.addObject("userlist", userlist);
            mav.addObject("ref",ref);
            return mav;
    }
    
    @RequestMapping(value="/create/{id}", method=RequestMethod.POST)
    public ModelAndView createNewTask(@ModelAttribute @Valid Task task,
    				@PathVariable String id,
                    BindingResult result,
                    Principal principal,
                    final RedirectAttributes redirectAttributes) {
    	
            if (result.hasErrors())
                    return new ModelAndView("secured/TaskNew");
            
    		int idIteration = customUrlService.formatPathToInt(id, "iteration");
    		int idWorkitem = customUrlService.formatPathToInt(id, "workitem");
    		int ref = customUrlService.formatPathToInt(id, "ref"); //0 for Iteratioplan 1 for Tasklist
    		
    		Workitem workitem = workitemService.findById(idWorkitem);
    		int idProject = workitem.getProject().getIdProject();
    		Iteration iteration = workitem.getIteration();
            
            ModelAndView mav = new ModelAndView();
            String message = "New Task "+task.getTitle()+" was successfully created.";
            
            task.setHoursRemaining(task.getHoursPlanned());
            task.setIteration(iteration);
            
            if(task.getUser().getIdUser().equals("-1")){            	
        		SqlUser user = userService.findById(principal.getName());
        		List<SqlUser> userlist = userService.findByTeam(user.getTeam());
        		
        		message += task.getUser().getIdUser();
        		
        		List<Task> tasklist = new ArrayList<Task>();
        		for(SqlUser user1 : userlist){
        			task.setUser(user1);
        			tasklist.add(task);
        		}
        		Iterable<Task> taskBatch = taskService.createBatch(tasklist);        		
        		for(Task task1 : taskBatch){
        			taskService.createUpdateTasklog(task1, false);
        		}
        		
            } else {
            	task = taskService.create(task);
            	taskService.createUpdateTasklog(task, false);
            }
            
            
            
            if(ref == 0){
            	mav.setViewName("redirect:/secured/iterationplan/list/project="+idProject+"&iteration="+idIteration+".html"); 
            } else {
            	mav.setViewName("redirect:/secured/iterationplan/myTaskList.html"); 
            }
            
                            
            redirectAttributes.addFlashAttribute("message", message);
            return mav;                
    }

    @RequestMapping(value="/list/{id}", method=RequestMethod.GET)
    public ModelAndView IterationPlanPage(@PathVariable String id) {
    	
    		int idProject = customUrlService.formatPathToInt(id, "project");
    		int idIteration = customUrlService.formatPathToInt(id, "iteration");
    		
            ModelAndView mav = new ModelAndView("secured/IterationPlan");            
            List<Workitem> workitemlist = workitemService.findAllAndFetchTasksEagerlyByProjectAndIteration(
            								new Project(idProject), new Iteration(idIteration));          
            //TODO Bytefilter type <> 0
            mav.addObject("workitemList", workitemlist);
            mav.addObject("idProject", idProject);
            mav.addObject("idIteration", idIteration);
            mav.addObject("title","Iterationplan");
            return mav;
    }
    
    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editTaskPage(@PathVariable String id) {
    		int idWorkitem = customUrlService.formatPathToInt(id,"workitem");
    		int idTask = customUrlService.formatPathToInt(id,"task");
    		int idIteration = customUrlService.formatPathToInt(id, "iteration");
    		int ref = customUrlService.formatPathToInt(id, "ref"); //0 for Iteratioplan 1 for Tasklist
    		
            ModelAndView mav = new ModelAndView("secured/TaskEdit");
            Task task = taskService.findById(idTask);
            mav.addObject("task", task);
            mav.addObject("idWorkitem", idWorkitem);
            mav.addObject("idIteration", idIteration);
            mav.addObject("ref",ref);
            return mav;
    }
    
    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editTask(@ModelAttribute("task") Task task,
                    BindingResult result,
                    @PathVariable String id,
                    final RedirectAttributes redirectAttributes) throws TaskNotFound {
            
            if (result.hasErrors())
                    return new ModelAndView("secured/TaskEdit");
            
            int idTask = customUrlService.formatPathToInt(id,"task");
    		int idWorkitem = customUrlService.formatPathToInt(id,"workitem");
    		int idIteration = customUrlService.formatPathToInt(id, "iteration");
    		int ref = customUrlService.formatPathToInt(id, "ref"); //0 for Iteratioplan 1 for Tasklist   		    		            
            
            ModelAndView mav = new ModelAndView();            
            if(ref == 0){
        		Workitem workitem = workitemService.findById(idWorkitem);
        		int idProject = workitem.getProject().getIdProject();
            	mav.setViewName("redirect:/secured/iterationplan/list/project="+idProject+"&iteration="+idIteration+".html"); 
            } else {
            	mav.setViewName("redirect:/secured/iterationplan/myTaskList.html"); 
            }
            
            task.setIdTask(idTask);
            task = taskService.update(task);
                       
            String message = "Task "+idTask+" was successfully updated.";                   
            redirectAttributes.addFlashAttribute("message", message);  
            return mav;
    }
    
    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteTask(@PathVariable String id,
                    final RedirectAttributes redirectAttributes) throws TaskNotFound {
            
        	int idTask = customUrlService.formatPathToInt(id,"task");
        	int idWorkitem = customUrlService.formatPathToInt(id,"workitem");
        	int idIteration = customUrlService.formatPathToInt(id, "iteration");
    		int ref = customUrlService.formatPathToInt(id, "ref"); //0 for Iteratioplan 1 for Tasklist
    	
            ModelAndView mav = new ModelAndView();            
            if(ref == 0){
        		Workitem workitem = workitemService.findById(idWorkitem);
        		int idProject = workitem.getProject().getIdProject();

            	mav.setViewName("redirect:/secured/iterationplan/list/project="+idProject+"&iteration="+idIteration+".html"); 
            } else {
            	mav.setViewName("redirect:/secured/iterationplan/myTaskList.html"); 
            }
            
            Task task = taskService.delete(idTask);
            String message = "The Task "+task.getIdTask()+"  "+task.getTitle()+" was successfully deleted.";           
            redirectAttributes.addFlashAttribute("message", message);
            return mav;
    }
    
    @RequestMapping(value="/updateWorkitem/{id}", method=RequestMethod.GET)
    public ModelAndView iterationPlanaddWorkitem(@PathVariable String id) throws WorkitemNotFound {
    	int idIteration = customUrlService.formatPathToInt(id, "iteration");
    	int idWorkitem = customUrlService.formatPathToInt(id, "workitem");
    	int idProject = customUrlService.formatPathToInt(id, "project");
    	int remove = customUrlService.formatPathToInt(id, "remove");
    	int ref = customUrlService.formatPathToInt(id, "ref");
    	Iteration iteration = null;
    	
    	ModelAndView mav = new ModelAndView();
    	
    	if(remove == 0){
    		iteration = new Iteration(idIteration);
    	}     	
    	Workitem workitem = workitemService.findById(idWorkitem);
    	workitem.setIteration(iteration);
    	workitemService.update(workitem);  
    	
    	mav = redirectViewName(mav, ref, idProject, idIteration);
    	return mav;
    }
    
    @RequestMapping(value="/projectplan/{idProject}", method=RequestMethod.GET)
    public ModelAndView projectplanPage(@PathVariable Integer idProject) {
    		
            ModelAndView mav = new ModelAndView("secured/ProjectPlan");
            Project project = new Project(idProject);
            List<Iteration> iterationlist = iterationService.findByProjectAndFetchWorkitemsEagerly(project);      
            
            mav.addObject("iterationList", iterationlist);
            mav.addObject("idProject", idProject);
            return mav;
    }    
    
    @RequestMapping(value="/myIterationPlan", method=RequestMethod.GET)
    public ModelAndView myIterationPlanPage(Principal principal,
    		 								final RedirectAttributes redirectAttributes) {
    	    
        	ModelAndView mav = new ModelAndView("secured/IterationPlan"); 
	        
	        SqlUser user = userService.findById(principal.getName());
	        Project project = user.getProject();
	        
	        if(project == null || project.getIdProject() == 0){
	            mav = new ModelAndView("redirect:/secured/home");
	            String message = "You have no preferred Project. Please assign one in your profile.";               
	            redirectAttributes.addFlashAttribute("message", message);
	        }    	
	        Iteration iteration = iterationService.findCurrentIteration(project, new Date());
	        if(iteration == null){
	            mav = new ModelAndView("redirect:/secured/home");
	            String message = "Your preferred Project is already finished. Current Iteration can't be opened.";               
	            redirectAttributes.addFlashAttribute("message", message);
	        } 
	        
            List<Workitem> workitemlist = workitemService.findAllAndFetchTasksEagerlyByProjectAndIteration(project, iteration);       
            
            mav.addObject("workitemList", workitemlist);
            mav.addObject("idProject", project.getIdProject());
            mav.addObject("idIteration",iteration.getIdIteration());
            mav.addObject("title","Current Iterationplan");
            return mav;
    }
    
    @RequestMapping(value="/myTaskList", method=RequestMethod.GET)
    public ModelAndView myTaskListpage(Principal principal,
    								   final RedirectAttributes redirectAttributes) {    	
    		
            ModelAndView mav = new ModelAndView("secured/TaskList");
            
            SqlUser user = userService.findById(principal.getName());
            Project project = user.getProject();
            if(project == null){
                mav = new ModelAndView("redirect:/secured/home");
                String message = "You have no preferred Project. Please assign one in your profile.";               
                redirectAttributes.addFlashAttribute("message", message);
            }
            
            List<Iteration> iterationlist = iterationService.findByProject(project);             
            List<Task> tasklist = taskService.findByUserAndterationInOrderByResolvedDesc(user, iterationlist);      
            
            mav.addObject("taskList", tasklist);
            mav.addObject("title","Tasklist");
            
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
