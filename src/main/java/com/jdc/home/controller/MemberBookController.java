package com.jdc.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.home.controller.searchData.SearchDataContainer;
import com.jdc.home.model.Pagination;
import com.jdc.home.model.input.BookSearchByMemberForm;
import com.jdc.home.model.output.CategorySelectionDto;
import com.jdc.home.model.repo.CategoryRepo;
import com.jdc.home.model.service.BookService;

@Controller
@RequestMapping("member/book")
public class MemberBookController {

	@Autowired
	private BookService service;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private SearchDataContainer searchDataContainer;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	String search( 
			@ModelAttribute("searchForm") BookSearchByMemberForm searchForm,
			ModelMap model
			) {
		
		var pageResult =  service.searchForMember(searchForm);
		model.put("list", pageResult.getContent());
		model.put("pagination", new Pagination(pageResult));
		
		searchDataContainer.setBookSearchbyMemberForm(searchForm);
		return "book-list";
	}
	
	@ModelAttribute("categories")
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	List<CategorySelectionDto> categories(){
		return categoryRepo.findWithNativeSql();
	}
	
	@ModelAttribute("searchForm")
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	BookSearchByMemberForm searchForm() {
		
		var bookSearchByMemberForm = searchDataContainer.getBookSearchbyMemberForm();
		
		if(bookSearchByMemberForm != null) {
			return bookSearchByMemberForm;
		}
		
		return new BookSearchByMemberForm();
	}
}
