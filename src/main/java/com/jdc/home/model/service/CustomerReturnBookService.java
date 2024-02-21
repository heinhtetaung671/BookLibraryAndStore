package com.jdc.home.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.home.exceptions.NoEnoughBalanceException;
import com.jdc.home.model.FarDateCalculator;
import com.jdc.home.model.entity.Book;
import com.jdc.home.model.entity.BookBorrow.Status;
import com.jdc.home.model.repo.BookBorrowRepo;

@Service
public class CustomerReturnBookService {

	@Autowired
	private BookBorrowRepo bookBorrowRepo;
	@Autowired
	private FarDateCalculator farDateCalculator;
	
	@Transactional
	public Book doReturn(Integer id) {
		var bookBorrow = bookBorrowRepo.findById(id).orElseThrow();
		var account = bookBorrow.getAccount();
		var book = bookBorrow.getBook();
		
		var cost = (int) (book.getBpdPrice() * farDateCalculator.calculate(bookBorrow.getBorrowDate()));

		if(account.getBalance() < cost) {
			throw new NoEnoughBalanceException(cost - account.getBalance());
		}
		
		// Update Book
		book.setQtyForBorrow(book.getQtyForBorrow() + 1);		
		
		// Update Account Balnce
		account.setBalance(account.getBalance() - cost);
		
		// Update BookBorrow Status
		bookBorrow.setStatus(Status.RETURNED);
		
		return book;
	}
	
}
