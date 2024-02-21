package com.jdc.home.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.home.model.entity.Book;
import com.jdc.home.model.entity.Promotion;
import com.jdc.home.model.input.BookEditForm;
import com.jdc.home.model.output.CategorySelectionDto;
import com.jdc.home.model.output.PromotionCheckboxDto;
import com.jdc.home.model.repo.BookRepo;
import com.jdc.home.model.repo.CategoryRepo;
import com.jdc.home.model.repo.PromotionRepo;
import com.jdc.home.model.service.BookPromotionService;
import com.jdc.home.model.service.BookService;

@Controller
@RequestMapping("member/book/edit")
public class MemberBookEditController {
	
	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private PromotionRepo promotionRepo;
	@Autowired
	private BookPromotionService bookPromotionService;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	String editBookForm() {
		return "book-edit";
	}
		
	@PostMapping
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	String edit(@Validated @ModelAttribute("book") BookEditForm editForm, BindingResult result,
				@RequestParam(value = "promotions", required = false) List<Promotion> promotions,
				RedirectAttributes redirectAttribute
			) {
		
		if(result.hasErrors())
			return "book-edit";
		
		var book = editForm.toBook();
		
		Book bookResult = bookService.save(book, promotions);		
		
		redirectAttribute.addFlashAttribute("status", "success");
		redirectAttribute.addFlashAttribute("message",
							editForm.getId() == 0 ? "Your book '%s' has successfully added.".formatted(bookResult.getName())
												  : "Your book '%s' has successfully updated.".formatted(bookResult.getName()));
		
		return "redirect:/member/book";
	}
	
	@ModelAttribute("book")
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	BookEditForm form(@RequestParam(value = "bId", required = false) Integer bId) {
		
		if(bId == null)
			return new BookEditForm();
		
		return bookRepo.findById(bId).map(BookEditForm::toDto).orElseThrow();
	}
	
	@ModelAttribute("promotions")
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	List<PromotionCheckboxDto> promotions(){				
		return promotionRepo.findPromotionByActive(true);
	}
	
	@ModelAttribute("currentBookPromotionIds")
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	List<Integer> currentBookPromotionIds(@RequestParam(value = "bId", required = false) Integer bookId){
		
 		if(bookId != null) {
			return  bookPromotionService.findPromotionIdsByBookId(bookId);
		} else {
			return new ArrayList<Integer>();
		}
		
	}
	
	@ModelAttribute("categories")
	@PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
	List<CategorySelectionDto> categories(){
		return categoryRepo.findWithNativeSql();
	}
	
}
