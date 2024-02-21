package com.jdc.home.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.home.model.CartContainer;

@Service
@Transactional(readOnly = true)
public class PromotionSelectService {

	@Autowired
	private CartContainer cartContainer;
	
	public void doPromotionSelect(Integer bookId, Integer promotionId) {

		var cart = cartContainer.getCart();
		var preDataForABook = cart.get(bookId);
		
		if(preDataForABook == null)
			throw new IllegalArgumentException("Bad Request");
		
		preDataForABook.setPromotionId(promotionId);
		
	}

	
	
}
