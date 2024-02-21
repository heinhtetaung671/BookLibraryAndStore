package com.jdc.home.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.home.model.service.AccountService;
import com.jdc.home.model.service.CartDetailsService;

@Controller
@RequestMapping("customer/cart/details")
public class CustomerCartDetailsController {

	@Autowired
	private CartDetailsService cartDetailsService;
	@Autowired
	private AccountService accountService;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('MEMBER', 'CUSTOMER')")
	String cartDetails(ModelMap model) {
		
		var errorList = new ArrayList<Boolean>();
		var list = cartDetailsService.selectDataToShow();
		
		list.forEach(dto -> errorList.add(dto.isError()));
		model.put("list", list);
		model.put("errorList", errorList);
		model.put("accountBalance", accountService.findBalance());
		model.put("totalPrice", cartDetailsService.caculateTotalPrice(list));
		
		return "cart-details";
	}

	
	
}
