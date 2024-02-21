package com.jdc.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.home.exceptions.NoEnoughBalanceException;
import com.jdc.home.model.service.CustomerReturnBookService;

@Controller
@RequestMapping("customer/return/book")
public class CustomerReturnBookController {

	@Autowired
	private CustomerReturnBookService customerReturnBookService;

	@GetMapping("{id:\\d+}")
	@PreAuthorize("hasAnyAuthority('MEMBER', 'CUSTOMER')")
	String returnBook(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		
		try {
			var book = customerReturnBookService.doReturn(id);

			redirectAttributes.addFlashAttribute("status", "success");
			redirectAttributes.addFlashAttribute("message", "You have successfully return '%s' book.".formatted(book.getName()));
	
		} catch (NoEnoughBalanceException e) {

			redirectAttributes.addFlashAttribute("status", "error");
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/customer/borrowing";
	}

}
