package com.wpm.validation;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wpm.model.Project;

@Component
public class ProjectValidator implements Validator {
	

    public boolean supports(Class<?> clazz) {
            return Project.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
    	
            ValidationUtils.rejectIfEmpty(errors, "title", "project.title.empty");
            ValidationUtils.rejectIfEmpty(errors, "description", "project.description.empty");
            ValidationUtils.rejectIfEmpty(errors, "dateBegin", "project.dateBegin.empty");
            ValidationUtils.rejectIfEmpty(errors, "dateEnd", "project.dateEnd.empty");
            ValidationUtils.rejectIfEmpty(errors, "iterationCycle", "project.iterationCycle.empty");
            
            String idTeam = (String) errors.getFieldValue("team");
            if(idTeam.contains("-")){
            	errors.rejectValue("team", "project.team.isNull");
            }
            
            DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
           
            
            try {
                Date beginD = dateFormat.parse((String) errors.getFieldValue("dateBegin"));
			} catch (Exception e) {
				errors.rejectValue("dateBegin", "project.date.notValid");
			}
            
            try {
                Date endD = dateFormat.parse((String) errors.getFieldValue("dateEnd"));
			} catch (Exception e) {
				errors.rejectValue("dateEnd", "project.date.notValid");
			}

            			
         
	       	Project project = (Project) target;
       		int iterationCycle = project.getIterationCycle();
       		Date begin = project.getDateBegin();
       		Date end = project.getDateEnd();            
            
            if (iterationCycle < 7){
            	  errors.rejectValue("iterationCycle", "project.iterationCycle.ltSeven");
            }
            
            if(begin != null && end != null){
            	if(begin.after(end)){
              	  errors.rejectValue("dateBegin", "project.date.beginAfterEnd");
              }
            }
            
            Date current = new Date();                    
            if(end != null){
            	if(end.before(current)){
            		errors.rejectValue("dateEnd", "project.date.endAfterCurrent");
            	}
            }                                         
    }

}


