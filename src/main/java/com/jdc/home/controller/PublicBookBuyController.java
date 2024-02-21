package com.jdc.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.home.controller.searchData.PublicSearchData;
import com.jdc.home.model.Pagination;
import com.jdc.home.model.input.BookSearchForm;
import com.jdc.home.model.service.BookService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("public/book/buy")
public class PublicBookBuyController {

	@Autowired
	private BookService service;

	public static final String RECENT_SEARCH_DATA_STORE_KEY = "recentPublicBuySearchData";
	
	@GetMapping
	String search(BookSearchForm searchForm,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
			@RequestParam(value = "direction", required = false, defaultValue = "asc") String direction,
			HttpSession session, ModelMap model) {

		var pageResult = service.searchForBuy(searchForm, page, pageSize, sortBy, direction);
		model.put("list", pageResult.getContent());
		model.put("pagination", new Pagination(pageResult));

		session.setAttribute(PublicBookBuyController.RECENT_SEARCH_DATA_STORE_KEY, new PublicSearchData(searchForm, page, pageSize, sortBy, direction));

		return "book-buy-list";
	}

}
