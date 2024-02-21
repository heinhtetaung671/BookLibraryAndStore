package com.jdc.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.home.exceptions.NoEnoughBalanceException;
import com.jdc.home.exceptions.PromotionNotAvailableException;
import com.jdc.home.model.service.CheckoutService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("customer/sale/checkout")
public class CustomerSaleController {

	@Autowired
	private CheckoutService checkoutService;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('MEMBER', 'CUSTOMER')")
	String checkout(HttpSession session, RedirectAttributes redirectAttributes) {

		try {

			var sale = checkoutService.doCheckout();
			
			session.removeAttribute("scopedTarget.cartContainer");
			session.removeAttribute("scopedTarget.containerForFocBorrow");
			
			return "redirect:/sale/details/%d".formatted(sale.getId());
		} catch (NoEnoughBalanceException e) {
			
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			redirectAttributes.addFlashAttribute("status", "error");
			
			return "redirect:/customer/cart/details";
		} catch (PromotionNotAvailableException e) {
			
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			redirectAttributes.addFlashAttribute("status", "error");
			
			return "redirect:/customer/cart/details";
		} catch (IllegalArgumentException e) {
			
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			redirectAttributes.addFlashAttribute("status", "error");
			
			return "redirect:/customer/cart/details";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/customer/cart/details";
	}

}
