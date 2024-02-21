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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.home.controller.searchData.SearchDataContainer;
import com.jdc.home.model.input.CategoryEditForm;
import com.jdc.home.model.input.CategorySearchForm;
import com.jdc.home.model.repo.CategoryRepo;
import com.jdc.home.model.service.CategoryService;

@Controller
@RequestMapping("member/category")
public class MemberCategoryController {

	@Autowired
	private CategoryRepo repo;
	@Autowired
	private CategoryService service;
	@Autowired
	private SearchDataContainer searchDataContainer;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	String search(@ModelAttribute("searchForm") CategorySearchForm searchForm, 
			ModelMap model
			) {
		model.put("list", service.search(searchForm));
		searchDataContainer.setCategorySearchForm(searchForm);
		
		return "category-list";
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	String edit(@ModelAttribute(name = "searchForm", binding = false) CategorySearchForm searchForm, @ModelAttribute("form") @Validated CategoryEditForm editForm,
			BindingResult result,
			RedirectAttributes redirectAttributes,
			ModelMap model) {
		

		model.put("list", service.search(searchForm));
		
		if(result.hasErrors()) {
			model.put("error", true);
			return "category-list";
		}
		
		try {
			if(editForm.getId() == 0 && repo.findByName(editForm.getName()).isPresent()) {
				throw new IllegalArgumentException("category is already exists.");
			}
			
			service.save(editForm);
			
			return "redirect:/member/category";
		} catch (Exception e) {
			model.put("error", true);
			return "category-list";
		}
		
	}
	
	@ModelAttribute("searchForm")
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	CategorySearchForm searchForm(@RequestParam(name = "searchName", required = false) String searchName) {
		
		var recentSearchForm = searchDataContainer.getCategorySearchForm();
		
		if(recentSearchForm != null && searchName == null) {
			return recentSearchForm;
		}
		
		return new CategorySearchForm();
	}
	
	@ModelAttribute("form")
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	CategoryEditForm form( ) {
		return new CategoryEditForm();
	}
}
