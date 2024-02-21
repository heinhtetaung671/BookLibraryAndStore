package com.jdc.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.home.model.service.PromotionSelectService;

@Controller
@RequestMapping("customer/cart/promotionSelect")
public class PromotionSelectController {

	@Autowired
	private PromotionSelectService promotionSelectService;
	
	@GetMapping("{bookId:\\d+}/{promotionId:\\d+}")
	@PreAuthorize("hasAnyAuthority('MEMBER', 'CUSTOMER')")
	String selectPromotion(@PathVariable("bookId") Integer bookId,@PathVariable("promotionId") Integer promotionId) {
		
		promotionSelectService.doPromotionSelect(bookId, promotionId);
		
		return "redirect:/customer/cart/details";
	}
	
}
