package com.wpm.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wpm.model.Password;
import com.wpm.model.SqlUser;
import com.wpm.service.UserService;

@Component
public class PasswordValidator2 implements Validator {
    
    @Autowired
    private UserService userService;

    public boolean supports(Class<?> clazz) {
            return Password.class.isAssignableFrom(clazz);
    }

    
    public void validate(Object target, Errors errors) {
    	
    		Password password = (Password) target;
    		String pwString1 = password.getPwString1();
    		String pwString2 = password.getPwString2();   		
    		String oldPw = password.getOldPw();
    		
    		SqlUser user = password.getUser();
    		String idUser = user.getIdUser();
    		user = userService.findById(idUser);
    		String pwHash = user.getPwHash();
    		
    		ValidationUtils.rejectIfEmpty(errors, "pwString1", "password.empty");
    		ValidationUtils.rejectIfEmpty(errors, "pwString2", "password.empty");
    		
    		if(!pwString1.equals(pwString2)){
    			errors.rejectValue("pwString1", "password.notequal");
    		}
    		

    		if(!isEqual(oldPw, pwHash)){
    			errors.rejectValue("oldPw", "password.oldpw");
    		}    		           
                       
    }
    
    private boolean isEqual(String rawPassword, String encodedPassword){
    	
    	PasswordEncoder encoder = new StandardPasswordEncoder();
    	return encoder.matches(rawPassword, encodedPassword);
    }

}
