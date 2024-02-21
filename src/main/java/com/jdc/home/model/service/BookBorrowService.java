package com.jdc.home.model.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.home.exceptions.NoEnoughBookException;
import com.jdc.home.model.ContainerForFocBorrow;
import com.jdc.home.model.entity.Book;
import com.jdc.home.model.entity.BookBorrow;
import com.jdc.home.model.entity.BookBorrow.Status;
import com.jdc.home.model.output.BookBorrowInfoDto;
import com.jdc.home.model.entity.BookBorrowFoc;
import com.jdc.home.model.repo.AccountRepo;
import com.jdc.home.model.repo.BookBorrowFocRepo;
import com.jdc.home.model.repo.BookBorrowRepo;
import com.jdc.home.model.repo.BookRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class BookBorrowService {

	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private BookBorrowRepo bookBorrowRepo;
	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private ContainerForFocBorrow sessionBookContainer;
	@Autowired
	private BookBorrowFocRepo bookBorrowFocRepo;

	@Transactional
	public void doBookBorrow(int bookId,HttpSession session) {

		var book = bookRepo.findById(bookId).orElseThrow();
		
		// Check book is available  or not
		if (book.getQtyForBorrow() >= 1) {
			
			// If user is new, do a free borrow
			if (sessionBookContainer.getBorrowInfo() == null) {

				doFocBorrow(book, session);

			} else {
				// Do Real Borrow
				var email = Optional.ofNullable(SecurityContextHolder.getContext())
						.map(context -> context.getAuthentication()).map(authentication -> authentication.getName())
						.orElse(null);

				var account = accountRepo.findAccountByEmail(email).orElse(null);

				if (account != null) {
					// Check not to borrow duplicate book
					var borrowingBookList = bookBorrowRepo.findBookBorrowByAccountAndBookAndStatus(account, book,
							Status.BORROWING);
					
					for (var borrowingBook : borrowingBookList) {
						if (borrowingBook.getBook().getId() == bookId) {
							throw new IllegalArgumentException("You have already borrowed this book.");
						}
					}
					// Update book  borrow data
					book.setQtyForBorrow(book.getQtyForBorrow() - 1);

					var bookBorrow = new BookBorrow();
					bookBorrow.setAccount(account);
					bookBorrow.setBook(book);
					bookBorrow.setStatus(Status.BORROWING);

					bookBorrowRepo.save(bookBorrow);
				} else {
					// Anonymous are only permitted one book to borrow
					throw new IllegalArgumentException(
							"Public account can only borrow one book.To borrow another, wait till the session expired.");
				}

			}

		} else {
			throw new NoEnoughBookException();
		}

	}

	private void doFocBorrow(Book book, HttpSession session) {

		var id = "%s_%s".formatted(session.getId(), LocalDateTime.now());

		var bookBorrowFoc = new BookBorrowFoc();
		bookBorrowFoc.setId(id);
		bookBorrowFoc.setBook(book);
		bookBorrowFoc.setStatus(Status.BORROWING);

		// Update Borrow Data
		book.setQtyForBorrow(book.getQtyForBorrow() - 1);
		
		// Save BookBorrowForFoc
		bookBorrowFocRepo.save(bookBorrowFoc);
		
		// Set Data to Session Scope Bean
		sessionBookContainer.setBorrowInfo(BookBorrowInfoDto.toDto(book));
		sessionBookContainer.setId(id);
		
	}

}
