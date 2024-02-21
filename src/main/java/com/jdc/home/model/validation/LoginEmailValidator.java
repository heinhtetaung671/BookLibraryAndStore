package com.jdc.home.model.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.jdc.home.model.repo.AccountRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class LoginEmailValidator implements ConstraintValidator<LoginEmail, String>{

	@Autowired
	private AccountRepo accountRepo;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(!(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()) {
			return authentication.getName().equals(value);
		}
		return accountRepo.findAccountByEmail(value).isEmpty();
		
	}

}
