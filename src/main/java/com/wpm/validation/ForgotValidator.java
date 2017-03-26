package com.wpm.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wpm.model.Forgot;
import com.wpm.service.UserService;

@Component
public class ForgotValidator implements Validator {
    
    @Autowired
    private UserService userService;

    public boolean supports(Class<?> clazz) {
            return Forgot.class.isAssignableFrom(clazz);
    }

    
    public void validate(Object target, Errors errors) {
    	
    		Forgot forgot = (Forgot) target;
    		String idUser = forgot.getIdUser();
    		ValidationUtils.rejectIfEmpty(errors, "idUser", "user.winUser.empty");
    		
            if(idUser != null){
		        if(userService.findById(idUser) == null){
		        	errors.rejectValue("idUser", "user.not");
		        }
            }
                       
    }

}
