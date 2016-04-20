package com.andieguo.spring.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.andieguo.spring.bean.User;

@Component
public class UserFormValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.userForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "NotEmpty.userForm.age");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.userForm.gender");
		
		if(user.getName()==null || user.getName().length() > 20){
			errors.rejectValue("name", "SizeLength.userForm.name");
		}
	}

}
