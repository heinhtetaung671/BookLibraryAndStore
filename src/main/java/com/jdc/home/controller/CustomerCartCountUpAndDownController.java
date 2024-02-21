package com.jdc.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.home.model.service.CartEditService;

@Controller
@RequestMapping("customer/cart")
public class CustomerCartCountUpAndDownController {

	@Autowired
	private CartEditService cartEditService;
	
	@GetMapping("{bookId:\\d+}/{condition}")
	@PreAuthorize("hasAnyAuthority('MEMBER', 'CUSTOMER')")
	String countUpAndDown(@PathVariable("bookId") int bookId,@PathVariable("condition") String condition, RedirectAttributes redirectAttribute) {
		
		try {
			if(condition.equals("countUp")) {
				cartEditService.doCountUp(bookId);
			} 

			if(condition.equals("countDown")) {
				cartEditService.doCountDown(bookId);
			}

		} catch (Exception e) {
			redirectAttribute.addFlashAttribute("status", "error");
			redirectAttribute.addFlashAttribute("message", e.getMessage());
			
		}
		
		return "redirect:/customer/cart/details";
	}
	
}
