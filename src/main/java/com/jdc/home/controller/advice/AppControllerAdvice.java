package com.jdc.home.controller.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jdc.home.model.service.AccountService;

@ControllerAdvice
public class AppControllerAdvice {

	@Autowired
	private AccountService accountService;
	
	@ModelAttribute("balance")
	int balance() {
		return accountService.findBalance();
	}
	
}
