package com.jdc.home.model.repo;

import java.util.List;

import com.jdc.home.model.CustomRepository;
import com.jdc.home.model.entity.Account;
import com.jdc.home.model.entity.Book;
import com.jdc.home.model.entity.BookBorrow;
import com.jdc.home.model.entity.BookBorrow.Status;

public interface BookBorrowRepo extends CustomRepository<BookBorrow, Integer>{

	List<BookBorrow> findBookBorrowByAccountAndBookAndStatus(Account account, Book book, Status status);
	
}
