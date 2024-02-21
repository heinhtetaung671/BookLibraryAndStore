package com.jdc.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.home.model.entity.Account.Authority;
import com.jdc.home.model.input.AddNewAccountByAdminForm;
import com.jdc.home.model.service.AddNewAccountByAdminService;

@Controller
@RequestMapping("admin/addNewAccount")
public class AdminAddNewAccountController {

	@Autowired
	private AddNewAccountByAdminService addNewAccountByAdminService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	String index() {
		return "add-new-account-by-admin";
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	String addNewAccount(@ModelAttribute("form") @Validated AddNewAccountByAdminForm form, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			return "add-new-account-by-admin";
		}
		
		try {
			var account = addNewAccountByAdminService.addNewAccount(form);

			redirectAttributes.addFlashAttribute("status", "success");
			redirectAttributes.addFlashAttribute("message", "You have successfully add %s.".formatted(account.getName()));
		} catch (IllegalArgumentException e) {
			
			result.rejectValue("email", "email", e.getMessage());
			return "add-new-account-by-admin";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/admin/account";
	}
	
	@ModelAttribute("form")
	@PreAuthorize("hasAuthority('ADMIN')")
	AddNewAccountByAdminForm form() {
		return new AddNewAccountByAdminForm();
	}
	
	@ModelAttribute("authorities")
	@PreAuthorize("hasAuthority('ADMIN')")
	Authority[] authorities() {
		return Authority.values();
	}
	
}
