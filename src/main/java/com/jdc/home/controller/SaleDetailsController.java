package com.jdc.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.home.model.output.SaleDetailsInfoDto;
import com.jdc.home.model.service.SaleDetailsService;

@Controller
@RequestMapping("sale/details")
public class SaleDetailsController {

	@Autowired
	private SaleDetailsService saleDetailsService;
	
	@GetMapping("{saleId:\\d+}")
	@PreAuthorize("!isAnonymous()")
	String details(@PathVariable("saleId") Integer saleId, ModelMap model) {
		
		List<SaleDetailsInfoDto> saleDetailsInfos = saleDetailsService.findSaleDetailsInfos(saleId);
		int totalPrice = saleDetailsService.caculateTotalPrice(saleDetailsInfos);
	
		model.put("saleId", saleId);
		model.put("list", saleDetailsInfos);
		model.put("totalPrice", totalPrice);
		
		return "saleDetails-info";
	}
	
}
