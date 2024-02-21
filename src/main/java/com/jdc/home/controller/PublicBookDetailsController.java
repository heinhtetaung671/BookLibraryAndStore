package com.jdc.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.home.controller.searchData.LastRequestPersisterForBook;
import com.jdc.home.model.output.BookBorrowInfoDto;
import com.jdc.home.model.output.BookBuyInfoDto;
import com.jdc.home.model.repo.BookRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("public/book")
public class PublicBookDetailsController {

	@Autowired
	private BookRepo bookRepo;

	
	@GetMapping("{buyOrBorrow}/{bId}/details")
	String details(@PathVariable(value = "bId") Integer id, @PathVariable("buyOrBorrow") String buyOrBorrow, HttpSession session, RedirectAttributes redirectAttribute) {
		
		String key = null;
		
		if(buyOrBorrow.equals("buy")) 
			key = PublicBookBuyController.RECENT_SEARCH_DATA_STORE_KEY;
	
		if(buyOrBorrow.equals("borrow")) 
			key = PublicBookBorrowController.RECENT_SEARCH_DATA_STORE_KEY;
		
		// Persist Last Request
		LastRequestPersisterForBook.doPersistRequestData(key, session, redirectAttribute);
		
		if(buyOrBorrow.equals("buy")) {
			redirectAttribute.addFlashAttribute("bookDetails", BookBuyInfoDto.toDto(bookRepo.findById(id).orElseThrow()));
		return "redirect:/public/book/buy";
		}
		if(buyOrBorrow.equals("borrow")) {
			redirectAttribute.addFlashAttribute("bookDetails", BookBorrowInfoDto.toDto(bookRepo.findById(id).orElseThrow()));
			return "redirect:/public/book/borrow";
		}	
		return "redirect:/public/book/borrow";
	}
	
}
