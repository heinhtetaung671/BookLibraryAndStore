package com.jdc.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.home.controller.searchData.SearchDataContainer;
import com.jdc.home.model.Pagination;
import com.jdc.home.model.entity.Account.Authority;
import com.jdc.home.model.input.AccountEditByAdminForm;
import com.jdc.home.model.input.AccountSearchForm;
import com.jdc.home.model.repo.AccountRepo;
import com.jdc.home.model.service.AccountEditByAdminService;
import com.jdc.home.model.service.AccountService;

@Controller
@RequestMapping("admin/account")
public class AdminAccountController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private SearchDataContainer searchDataContainer;
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private AccountEditByAdminService accountEditByMemberService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	String search(@ModelAttribute("searchForm") AccountSearchForm searchForm, ModelMap model) {
		
		var pageResult = accountService.searchAccounts(searchForm);
		
		model.put("list", pageResult.getContent());
		model.put("pagination", new Pagination(pageResult));
		searchDataContainer.setAccountSearchForm(searchForm);
		
		return "account-list";
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	String edit(@ModelAttribute("form") @Validated AccountEditByAdminForm form, BindingResult result,
				@ModelAttribute(name = "searchForm",binding = false) AccountSearchForm searchForm, 
				ModelMap model
				) {
		
		var pageResult = accountService.searchAccounts(searchForm);
		
		model.put("list", pageResult.getContent());
		model.put("pagination", new Pagination(pageResult));
		
		if(result.hasErrors()) {
			model.put("error", true);
			return "account-list";
		}
		
		accountEditByMemberService.edit(form);
		
		return "redirect:/admin/account";
	}

	@ModelAttribute("form")
	@PreAuthorize("hasAuthority('ADMIN')")
	AccountEditByAdminForm form(@RequestParam(name = "id", required = false) Integer id) {
		
		if(id != null) {
			return AccountEditByAdminForm.from(accountRepo.findById(id).orElseThrow());
		}
		
		return new AccountEditByAdminForm(null, null, null);
	}
		
	@ModelAttribute("searchForm")
	@PreAuthorize("hasAuthority('ADMIN')")
	AccountSearchForm searchForm() {
		
		var accountSearchform = searchDataContainer.getAccountSearchForm();
		
		if(accountSearchform != null) {
			return accountSearchform;
		}
		
		return new AccountSearchForm();
	}
	
	@ModelAttribute("authorities")
	@PreAuthorize("hasAuthority('ADMIN')")
	Authority[] authorities() {
		return Authority.values();
	}
	
}
