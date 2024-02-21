package com.jdc.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.home.controller.searchData.LastRequestPersisterForBook;
import com.jdc.home.controller.searchData.PublicSearchData;
import com.jdc.home.exceptions.NoEnoughBookException;
import com.jdc.home.model.Pagination;
import com.jdc.home.model.input.BookSearchForm;
import com.jdc.home.model.repo.BookRepo;
import com.jdc.home.model.service.BookBorrowService;
import com.jdc.home.model.service.BookService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("public/book/borrow")
public class PublicBookBorrowController {
	
	@Autowired
	private BookService service;
	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private BookBorrowService bookBorrowService;
	
	public static final String RECENT_SEARCH_DATA_STORE_KEY = "recentPublicBorrowSearchData";
	
	@GetMapping
	String search(BookSearchForm searchForm,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
			@RequestParam(value = "direction", required = false, defaultValue = "asc") String direction, ModelMap model,
			HttpSession session) {

		var pageResult = service.searchForBorrow(searchForm, page, pageSize, sortBy, direction);

		model.put("list", pageResult.getContent());
		model.put("pagination", new Pagination(pageResult));

		session.setAttribute(PublicBookBorrowController.RECENT_SEARCH_DATA_STORE_KEY, new PublicSearchData(searchForm, page, pageSize, sortBy, direction));
		
		return "book-borrow-list";
	}

	@GetMapping("{bId}")
	String borrow(@PathVariable("bId") Integer bId, HttpSession session, ModelMap model, RedirectAttributes redirectAttribute) {
		
		LastRequestPersisterForBook.doPersistRequestData(RECENT_SEARCH_DATA_STORE_KEY, session, redirectAttribute);
		
		try {
			
			var book = bookRepo.findById(bId).orElseThrow();
			
			bookBorrowService.doBookBorrow(bId, session);
			redirectAttribute.addFlashAttribute("borrowBookMessage", "Your have successfully borrowed %s book.".formatted(book.getName()));
			redirectAttribute.addFlashAttribute("status", "success");
			
		} catch (IllegalArgumentException e) {
			
			redirectAttribute.addFlashAttribute("status", "error");
			redirectAttribute.addFlashAttribute("borrowBookMessage", e.getMessage());
			
		} catch (NoEnoughBookException e) {
			redirectAttribute.addFlashAttribute("status", "error");
			redirectAttribute.addFlashAttribute("borrowBookMessage", e.getMessage());
		}
		return "redirect:/public/book/borrow";
	}

}
