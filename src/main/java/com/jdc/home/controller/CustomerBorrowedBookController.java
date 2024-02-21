package com.jdc.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.home.model.entity.BookBorrow.Status;
import com.jdc.home.model.service.CustomerBorrowingBookService;

@Controller
@RequestMapping("customer/borrowed")
public class CustomerBorrowedBookController {

	@Autowired
	private CustomerBorrowingBookService customerBorrowingBookService;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('MEMBER', 'CUSTOMER')")
	String search(ModelMap model) {
		model.put("list", customerBorrowingBookService.searchBorrowBookWithStatus(Status.RETURNED));
		return "borrowed-list";
	}
	
}
