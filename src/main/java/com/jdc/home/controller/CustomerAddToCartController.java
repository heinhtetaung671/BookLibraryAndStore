package com.jdc.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.home.controller.searchData.LastRequestPersisterForBook;
import com.jdc.home.exceptions.NoEnoughBookException;
import com.jdc.home.model.repo.BookRepo;
import com.jdc.home.model.service.CartEditService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("customer/book")
public class CustomerAddToCartController {

	@Autowired
	private BookRepo bookRepo;

	@Autowired
	private CartEditService cartEditService;

	@GetMapping("{bId:\\d+}/addToCart")
	@PreAuthorize("hasAnyAuthority('MEMBER', 'CUSTOMER')")
	String addToCart(@PathVariable("bId") Integer bookId, HttpSession session, RedirectAttributes redirectAttribute) {

		LastRequestPersisterForBook.doPersistRequestData(PublicBookBuyController.RECENT_SEARCH_DATA_STORE_KEY, session,
				redirectAttribute);

		try {
			var book = bookRepo.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Not avilable Book."));

			cartEditService.doAddToCart(book);

			redirectAttribute.addFlashAttribute("status", "success");
			redirectAttribute.addFlashAttribute("message", "You have successfully added '%s' to cart.".formatted(book.getName()));


		} catch (NoEnoughBookException e) {
			redirectAttribute.addFlashAttribute("status", "error");
			redirectAttribute.addFlashAttribute("message", e.getMessage());
		} catch (IllegalArgumentException e) {
			redirectAttribute.addFlashAttribute("status", "error");
			redirectAttribute.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/public/book/buy";
	}

}
