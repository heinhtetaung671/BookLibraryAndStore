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
import com.jdc.home.model.entity.Promotion.Type;
import com.jdc.home.model.input.PromotionEditForm;
import com.jdc.home.model.input.PromotionSearchForm;
import com.jdc.home.model.repo.PromotionRepo;
import com.jdc.home.model.service.PromotionService;

@Controller
@RequestMapping("member/promotion")
public class MemberPromotionController {

	@Autowired
	private PromotionRepo promotionRepo;
	@Autowired
	private PromotionService promotionService;
	@Autowired
	private SearchDataContainer searchDataContainer;

	@GetMapping
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	String search(@ModelAttribute("searchForm") PromotionSearchForm searchForm, ModelMap model) {

		searchDataContainer.setPromotionSearchForm(searchForm);
		model.put("list", promotionService.search(searchForm));
		return "promotion-list";
	}

	@PostMapping("edit")
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	String edit(@ModelAttribute("form") @Validated PromotionEditForm form,
			BindingResult result, ModelMap model,
			@ModelAttribute(name = "searchForm", binding = false) PromotionSearchForm searchForm,
			RedirectAttributes redirectAttribute) {
		
		model.put("list", promotionService.search(searchForm));
		
		if (result.hasErrors()) {
			model.put("error", true);
			return "promotion-list";
		}

		promotionService.save(form);

		return "redirect:/member/promotion";
	}

	@ModelAttribute("form")
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	PromotionEditForm form(@RequestParam(name = "id", required = false) Integer id) {
		if (id != null && id > 0) {
			return PromotionEditForm.from(promotionRepo.findById(id).orElseThrow());
		}

		return new PromotionEditForm();
	}

	@ModelAttribute("types")
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	Type[] types() {
		return Type.values();
	}

	@ModelAttribute("searchForm")
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	PromotionSearchForm searchForm() {

		var promotionSearchForm = searchDataContainer.getPromotionSearchForm();

		if ( null != promotionSearchForm) {
			return promotionSearchForm;
		}

		return new PromotionSearchForm();
	}

}
