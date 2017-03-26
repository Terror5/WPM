package com.wpm.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wpm.model.SqlUser;
import com.wpm.service.UserService;

@Component
public class RegistrationValidator implements Validator {
       
    @Autowired
    private UserService userService;

    public boolean supports(Class<?> clazz) {
            return SqlUser.class.isAssignableFrom(clazz);
    }


    public void validate(Object target, Errors errors) {
            SqlUser user = (SqlUser) target;
            
            //String eMail = user.geteMail();
            String idUser = user.getIdUser();
                       
            ValidationUtils.rejectIfEmpty(errors, "firstName", "user.firstName.empty");
            ValidationUtils.rejectIfEmpty(errors, "lastName", "user.lastName.empty");
            ValidationUtils.rejectIfEmpty(errors, "idUser", "user.winUser.empty");
            //ValidationUtils.rejectIfEmpty(errors, "eMail", "user.eMail.empty");
            ValidationUtils.rejectIfEmpty(errors, "pwHash", "user.pwHash.empty");
            
            /*if (eMail != null){
            	if(eMail.indexOf("@") == -1){
            		errors.rejectValue("eMail", "user.eMail.containsNoAt");
            	}
            }*/
                       
            if(idUser != null){
		        if(userService.findById(idUser) != null){
		        	errors.rejectValue("idUser", "user.winUser.existing");
		        }
            }
    }

}

