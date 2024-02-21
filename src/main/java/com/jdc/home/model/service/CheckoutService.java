package com.jdc.home.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.home.exceptions.NoEnoughBalanceException;
import com.jdc.home.exceptions.PromotionNotAvailableException;
import com.jdc.home.model.CartContainer;
import com.jdc.home.model.PreDataForABookInCart;
import com.jdc.home.model.entity.Sale;
import com.jdc.home.model.entity.SaleDetails;
import com.jdc.home.model.entity.SaleDetailsPk;
import com.jdc.home.model.repo.AccountRepo;
import com.jdc.home.model.repo.BookPromotionRepo;
import com.jdc.home.model.repo.BookRepo;
import com.jdc.home.model.repo.PromotionRepo;
import com.jdc.home.model.repo.SaleDetailsRepo;
import com.jdc.home.model.repo.SaleRepo;

@Service
@Transactional(readOnly = true)
public class CheckoutService {

	@Autowired
	private CartContainer cartContainer;
	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private PromotionRepo promotionRepo;
	@Autowired
	private BookPromotionRepo bookPromotionRepo;
	@Autowired
	private SaleDetailsRepo saleDetailsRepo;
	@Autowired
	private SaleRepo saleRepo;
	@Autowired
	private AccountRepo accountRepo;
	
	
	@Transactional
	public Sale doCheckout() {
		
		var email = Optional.ofNullable(SecurityContextHolder.getContext())
					.map(c -> c.getAuthentication())
					.map(a -> a.getName()).orElse(null);
		var cart = cartContainer.getCart();
		
		var account = accountRepo.findAccountByEmail(email).orElseThrow();
		var sale = new Sale();
		
		sale.setAccount(account);
		
		// Create SaleDetails From Cart
		List<SaleDetails> saleDetails =  this.prepareSaleDetails(sale, cart);
	
		// Find Total Price
		int totalPrice = findTotalPrice(saleDetails);

		// Check account's balance is enough to buy or not
		if(account.getBalance() < totalPrice) {
			throw  new NoEnoughBalanceException(totalPrice - account.getBalance());
		}
		
		// Update Account Balance
		account.setBalance(account.getBalance() - totalPrice);
		
		// Update Account Point
		account.setPoint(account.getPoint() + totalPrice / 1000);
		
		// insert sale
		sale.setPoint(totalPrice / 1000);
		var result = saleRepo.save(sale);
		sale.setSaleDetails(saleDetails);

		// insert saleDetails
		saleDetailsRepo.saveAll(saleDetails);
		
		return result;
	}

	
	private int findTotalPrice(List<SaleDetails> saleDetails) {
		
		return saleDetails.stream().mapToInt( sd -> {

			var pricePerOneSaleDetails = sd.getBook().getDtsPrice() * sd.getQty();
			
			if(sd.getPromotion() != null) {
				return pricePerOneSaleDetails - ((pricePerOneSaleDetails * sd.getPromotion().getDisPercent()) / 100);
			}
			return pricePerOneSaleDetails;
			
		}).sum();
	}


	private List<SaleDetails> prepareSaleDetails(Sale sale, Map<Integer, PreDataForABookInCart> cart) {
		
		var list = new ArrayList<SaleDetails>();
		
		for(var entry : cart.entrySet()) {
			
			var bookId = entry.getKey();
			var preDataForABook = entry.getValue();
			
			// Skip the qty 0 
			if(preDataForABook.getCounter().getCount() == 0)
				continue;
			
			SaleDetails saleDetails = new SaleDetails();
			saleDetails.setSaleDetailsPk(new SaleDetailsPk());
			saleDetails.setSale(sale);
			saleDetails.setQty(preDataForABook.getCounter().getCount());
			
			var book = bookRepo.findBookByIdAndActive(bookId, true).orElseThrow();
			saleDetails.setBook(book);
			book.setQtyForSale(book.getQtyForSale() - preDataForABook.getCounter().getCount());
			
			// Check user take promotion or not
			if(entry.getValue().getPromotionId() > 0) {				
				var promotion = promotionRepo.findPromotionByIdAndActive(preDataForABook.getPromotionId(), true).orElseThrow();
				
				// Check promotion that user take is available or not
				if(!bookPromotionRepo.existsByBookIdAndPromotionIdAndActive(book.getId(), promotion.getId(), true)) {
					throw new PromotionNotAvailableException();
				}
				
				saleDetails.setPromotion(promotion);
			}
			list.add(saleDetails);
		}
		
		return list;
	}

	
	
	
	
	
	
}
