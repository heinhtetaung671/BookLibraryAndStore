package com.jdc.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.home.model.entity.BookBorrow.Status;
import com.jdc.home.model.output.BorrowBookInfoDto;
import com.jdc.home.model.service.CustomerBorrowingBookService;

@Controller
@RequestMapping("customer/borrowing")
public class CustomerBorrowingBookController {

	@Autowired
	private CustomerBorrowingBookService borrowingBookService;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('MEMBER', 'CUSTOMER')")
	String borrowingList() {
		return "borrowing-list";
	}
	
	@ModelAttribute("list")
	@PreAuthorize("hasAnyAuthority('MEMBER', 'CUSTOMER')")
	List<BorrowBookInfoDto> list(){
		return borrowingBookService.searchBorrowBookWithStatus(Status.BORROWING);
	}
	
//	@ModelAttribute("balance")
//	Integer balance() {
//		return accountService.findBalance();
//	}
}
