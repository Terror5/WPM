package com.wpm.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.wpm.model.Task;


@Component
public class TaskValidator implements Validator  {

	@Override
	public boolean supports(Class<?> clazz) {
        return Task.class.isAssignableFrom(clazz);
}
	
	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

}
