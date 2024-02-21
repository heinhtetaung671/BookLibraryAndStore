package com.jdc.home.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.jdc.home.model.output.BookBorrowInfoDto;

import lombok.Getter;
import lombok.Setter;

@Component
@SessionScope
@Getter
@Setter
public class ContainerForFocBorrow {
	
//	@Autowired
//	private BookBorrowFocRepo bookBorrowFocRepo;
//	@Autowired
//	private BookRepo bookRepo;

	
	private BookBorrowInfoDto borrowInfo;
	private String id;
		
//	@PreDestroy
//	@Transactional
//	public void destory() {
//		
//		if(borrowInfo != null && id != null) {			
//			var bookBorrowFoc = bookBorrowFocRepo.findById(id).orElseThrow();
//			var book = bookRepo.findById(bookBorrowFoc.getBook().getId()).orElseThrow();
//
//			book.setQtyForBorrow(book.getQtyForBorrow() + 1);
//			bookBorrowFoc.setStatus(Status.RETURNED);
//			
//		}
//		
//	}
	
	
}
