package com.jdc.home.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.home.exceptions.NoEnoughBookException;
import com.jdc.home.model.CartContainer;
import com.jdc.home.model.PreDataForABookInCart;
import com.jdc.home.model.entity.Book;
import com.jdc.home.model.repo.BookRepo;

@Service
@Transactional(readOnly = true)
public class CartEditService {

	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private CartContainer cartContainer;
		
	public void doAddToCart(Book book) {
		
		if(book.getQtyForSale() > 0) {
			var cart = cartContainer.getCart();
			var preDataForABook = cart.get(book.getId());
			
			if(preDataForABook == null) {
				preDataForABook = new PreDataForABookInCart();
				cart.put(book.getId(), preDataForABook);
			}
		
			var counter = preDataForABook.getCounter();
			
			if(counter.getCount() < book.getQtyForSale()) {
				counter.countUp();
			} else {
				throw new NoEnoughBookException();
			}
		} else {
			throw new NoEnoughBookException();
		}
		
		
	}

	public void doCountUp(int bookId) {
		
		var book = bookRepo.findById(bookId).orElseThrow( () -> new IllegalArgumentException("Not Available Book."));
		
		var preDataForABook = cartContainer.getCart().get(bookId);
		if(preDataForABook == null)
			throw new IllegalArgumentException("Not Available Book.");
		
		if(preDataForABook.getCounter().getCount() < book.getQtyForSale()) {
			preDataForABook.getCounter().countUp();
		}
		
	}

	public void doCountDown(int bookId) {
		var preDataForABook = cartContainer.getCart().get(bookId);
		if(preDataForABook == null)
			throw new IllegalArgumentException("Not Available Book.");
		
		if(preDataForABook.getCounter().getCount() == 1) {
			preDataForABook.getCounter().countDown();
			cartContainer.getCart().remove(bookId);
		}
		
		if(preDataForABook.getCounter().getCount() > 1) {	
			preDataForABook.getCounter().countDown();
		}

	}
	
}
