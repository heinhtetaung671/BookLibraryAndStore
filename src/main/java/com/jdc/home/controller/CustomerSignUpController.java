package com.jdc.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import com.jdc.home.model.input.CustomerSignUpForm;
import com.jdc.home.model.repo.AccountRepo;
import com.jdc.home.model.service.CustomerAccountService;
import com.jdc.home.model.service.UpdateProfileInfoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("signUp")
public class CustomerSignUpController {

	@Autowired
	private AccountRepo accountRepo;

	@Autowired
	private CustomerAccountService accountService;
	
	@Autowired
	private UpdateProfileInfoService updateProfileInfoService;
	
	@GetExchange
	String index() {
		return "customer-signUp";
	}

	@PostMapping
	String signUp(@ModelAttribute("form") @Validated CustomerSignUpForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {

		if(result.hasErrors())
			return "customer-signUp";
		
		var account = accountService.save(form);
		
		// Programatic login
		updateProfileInfoService.reAuthenticateAndLogin(account.getEmail(), form.getPassword(), request, response);
		
		return "redirect:/profile";
	}

	@ModelAttribute("form")
	CustomerSignUpForm form(@RequestParam(name = "id", required = false) Integer id) {

		if (id != null && id > 0) {
			var dto = CustomerSignUpForm.from(accountRepo.findById(id).orElseThrow());
			dto.setPassword(null);
			return dto;
		}

		return new CustomerSignUpForm();
	}

}
