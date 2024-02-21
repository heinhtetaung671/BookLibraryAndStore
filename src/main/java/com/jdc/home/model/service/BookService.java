package com.jdc.home.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.home.model.entity.Book;
import com.jdc.home.model.entity.BookPromotion;
import com.jdc.home.model.entity.BookPromotionPk;
import com.jdc.home.model.entity.Book_;
import com.jdc.home.model.entity.Promotion;
import com.jdc.home.model.input.BookSearchByMemberForm;
import com.jdc.home.model.input.BookSearchForm;
import com.jdc.home.model.output.BookBorrowInfoDto;
import com.jdc.home.model.output.BookBuyInfoDto;
import com.jdc.home.model.repo.BookPromotionRepo;
import com.jdc.home.model.repo.BookRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class BookService {

	@Autowired
	private BookRepo repo;
	
	@Autowired
	private BookPromotionRepo bookPromotionRepo;

	@Autowired
	private BookPromotionService bookPromotionService;
	
	// For Borrow
	public Page<BookBorrowInfoDto> searchForBorrow(BookSearchForm searchForm, int page, int pageSize, String sortBy, String direction) {

		Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc = cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Book.class);
			cq.select(cb.count(root.get(Book_.id)));
			cq.where(searchForm.where(cb, root));
			return cq;
		};

		Function<CriteriaBuilder, CriteriaQuery<BookBorrowInfoDto>> searchFunc = cb -> {
			var cq = cb.createQuery(BookBorrowInfoDto.class);
			var root = cq.from(Book.class);
			BookBorrowInfoDto.select(cq, root);
			cq.where(searchForm.where(cb, root));
			
			if(direction.equals("asc"))
				cq.orderBy(cb.asc(root.get(sortBy)));
			
			if(direction.equals("desc"))
				cq.orderBy(cb.desc(root.get(sortBy)));
			
			return cq;
		};

		return repo.search(searchFunc, countFunc, page, pageSize );
	}

	// For Buy
	public Page<BookBuyInfoDto> searchForBuy(BookSearchForm searchForm, int page, int pageSize, String sortBy, String direction) {

		Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc = cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Book.class);
			cq.select(cb.count(root.get(Book_.id)));
			cq.where(searchForm.where(cb, root));
			return cq;
		};

		Function<CriteriaBuilder, CriteriaQuery<BookBuyInfoDto>> searchFunc = cb -> {
			var cq = cb.createQuery(BookBuyInfoDto.class);
			var root = cq.from(Book.class);
			BookBuyInfoDto.select(cq, root);
			cq.where(searchForm.where(cb, root));
			
			if(direction.equals("asc"))
				cq.orderBy(cb.asc(root.get(sortBy)));
			
			if(direction.equals("desc"))
				cq.orderBy(cb.desc(root.get(sortBy)));
			
			return cq;
		};

		return repo.search(searchFunc, countFunc, page, pageSize);
	}
	
	
	// For Member
	public Page<Book> searchForMember(BookSearchByMemberForm searchForm){
		
		Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc = cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Book.class);
			cq.select(cb.count(root.get(Book_.id)));
			cq.where(searchForm.where(cb, root));
			return cq;
		};
		
		Function<CriteriaBuilder, CriteriaQuery<Book>> searchFunc = cb -> {
			var cq = cb.createQuery(Book.class);
			var root = cq.from(Book.class);
			cq.select(root);
			cq.where(searchForm.where(cb, root));
			cq.orderBy(cb.asc(root.get(searchForm.getSortBy())));
			return cq;
		};
		
		return repo.search(searchFunc, countFunc, searchForm.getPage(), searchForm.getPageSize());
	}

	@Transactional
	public Book save(Book book, List<Promotion> promotions) {

		if(promotions == null) {
			promotions = new ArrayList<Promotion>();
		}
		
		if(book.getId() > 0) {
			var oldBook = repo.findById(book.getId()).orElseThrow();
			oldBook.setName(book.getName());
			oldBook.setAuthor(book.getAuthor());
			oldBook.setWsPrice(book.getWsPrice());
			oldBook.setDtsPrice(book.getDtsPrice());
			oldBook.setBpdPrice(book.getBpdPrice());
			oldBook.setQtyForBorrow(book.getQtyForBorrow());
			oldBook.setQtyForSale(book.getQtyForSale());
			oldBook.setCategory(book.getCategory());
			oldBook.setActive(book.isActive());

			if(oldBook.getBookPromotions() == null) {
				oldBook.setBookPromotions(new ArrayList<BookPromotion>());
			}
						
			var oldPromotionIds = bookPromotionService.findPromotionIdsByBookId(oldBook.getId());
			
			var newPromotionIds = promotions.stream().map(p -> p.getId()).toList();
			
			oldBook.getBookPromotions()
			.stream()
			.filter(bp -> !newPromotionIds.contains(bp.getPromotion().getId()) && bp.isActive())
			.forEach(bp -> bp.setActive(false));
			
			promotions.stream().filter(p -> !oldPromotionIds.contains(p.getId()))
			.forEach(p -> {
				
				oldBook.getBookPromotions().stream()
				.filter(bp -> bp.getBookPromotionPk().getPromotionId() == p.getId() && !bp.isActive())
				.findFirst()
				.ifPresentOrElse(bp -> bp.setActive(true), () -> {
					
					var bookPromotion = new BookPromotion();
					bookPromotion.setBookPromotionPk(new BookPromotionPk());
					bookPromotion.setBook(oldBook);
					bookPromotion.setPromotion(p);
					bookPromotion.setActive(true);
					
					oldBook.getBookPromotions().add(bookPromotion);
					bookPromotionRepo.save(bookPromotion);

				});
				
			});
			
			return oldBook;
			
		} else {

			var bookPromotions = new ArrayList<BookPromotion>();

			for(var p : promotions) {
				var bookPromotion = new BookPromotion();
				bookPromotion.setBookPromotionPk(new BookPromotionPk());
				bookPromotion.setBook(book);
				bookPromotion.setPromotion(p);
				bookPromotion.setActive(true);
				
				bookPromotions.add(bookPromotion);
			}


			book.setBookPromotions(bookPromotions);

			var result = repo.save(book);
			bookPromotionRepo.saveAll(bookPromotions);

			return result;
			
		} 
			
	}

}
