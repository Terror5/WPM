package com.wpm.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wpm.model.Password;
import com.wpm.model.SqlUser;
import com.wpm.service.UserService;

@Component
public class PasswordValidator implements Validator {
    
    @Autowired
    private UserService userService;

    public boolean supports(Class<?> clazz) {
            return Password.class.isAssignableFrom(clazz);
    }

    
    public void validate(Object target, Errors errors) {
    	
    		Password password = (Password) target;
    		String pwString1 = password.getPwString1();
    		String pwString2 = password.getPwString2();   		
    		
    		ValidationUtils.rejectIfEmpty(errors, "pwString1", "password.empty");
    		ValidationUtils.rejectIfEmpty(errors, "pwString2", "password.empty");
    		
    		if(!pwString1.equals(pwString2)){
    			errors.rejectValue("pwString1", "password.notequal");
    		}
    		
            SqlUser user = password.getUser();
            
            String eMail = user.geteMail();
            String idUser = user.getIdUser();
                       
            ValidationUtils.rejectIfEmpty(errors, "user.firstName", "user.firstName.empty");
            ValidationUtils.rejectIfEmpty(errors, "user.lastName", "user.lastName.empty");
            ValidationUtils.rejectIfEmpty(errors, "user.idUser", "user.winUser.empty");
            ValidationUtils.rejectIfEmpty(errors, "user.eMail", "user.eMail.empty");
            
            if (eMail != null){
            	if(eMail.indexOf("@") == -1){
            		errors.rejectValue("user.eMail", "user.eMail.containsNoAt");
            	}
            }
            
            if(idUser != null){
		        if(userService.findById(idUser) != null){
		        	errors.rejectValue("user.idUser", "user.winUser.existing");
		        }
            }
                       
    }

}
