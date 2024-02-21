package com.jdc.home.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.home.model.CartContainer;
import com.jdc.home.model.output.CartDetailsInfoDto;
import com.jdc.home.model.repo.BookRepo;
import com.jdc.home.model.repo.PromotionRepo;

@Service
@Transactional(readOnly = true)
public class CartDetailsService {

	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private BookPromotionService bookPromotionService;
	@Autowired
	private PromotionRepo promotionRepo;
	@Autowired
	private CartContainer cartContainer;
	
	// Converting data, which are store in Session, to show
	public List<CartDetailsInfoDto> selectDataToShow(){
		var cart = cartContainer.getCart();
		var list = new ArrayList<CartDetailsInfoDto>();
		
		for(var entry : cart.entrySet()) {
			var cartDetailsInfo = new CartDetailsInfoDto();
		
			var bookId = entry.getKey();
			var preDataForABook = entry.getValue();
			
			// Checking book is available or not
			var book = bookRepo.findById(bookId).orElseThrow();
			if(!book.isActive()) {
				cartDetailsInfo.setErrorMessage("%s is no longer available!".formatted(book.getName()));
				cartDetailsInfo.setError(true);
				list.add(cartDetailsInfo);
				continue;
			} 
			
			cartDetailsInfo.setBookId(bookId);
			cartDetailsInfo.setBookName(book.getName());
			cartDetailsInfo.setCategoryName(book.getCategory().getName());
			cartDetailsInfo.setAuthor(book.getAuthor());
			cartDetailsInfo.setDtsPrice(book.getDtsPrice());
			cartDetailsInfo.setQty(preDataForABook.getCounter().getCount());
			
			if(preDataForABook.getPromotionId() >= 1) {
				var promotion = promotionRepo.findById(preDataForABook.getPromotionId()).orElseThrow();
				if(promotion.isActive()) {
					cartDetailsInfo.setDisPercent(promotion.getDisPercent());
					cartDetailsInfo.setPromotionId(promotion.getId());
				}else {
					preDataForABook.setPromotionId(0);
				}
			}
			
			var promotionList = bookPromotionService.findPromotionByBookId(bookId);
			cartDetailsInfo.setAvailablePromotions(promotionList);
			list.add(cartDetailsInfo);
		}
		
		return list;
	}
	
	public int caculateTotalPrice(List<CartDetailsInfoDto> cartDetailsInfo) {
		return cartDetailsInfo.stream().mapToInt(cdInfo -> {
			if(cdInfo.getDisPercent() > 0) {
				return (cdInfo.getDtsPrice() * cdInfo.getQty()) - (((cdInfo.getDtsPrice() * cdInfo.getQty()) * cdInfo.getDisPercent() ) / 100); 
			}
				return cdInfo.getDtsPrice() * cdInfo.getQty();
		}).sum();
	}
	
}
