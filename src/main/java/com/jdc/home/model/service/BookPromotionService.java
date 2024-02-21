package com.jdc.home.model.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.home.model.entity.BookPromotion;
import com.jdc.home.model.entity.BookPromotion_;
import com.jdc.home.model.entity.Book_;
import com.jdc.home.model.entity.Promotion_;
import com.jdc.home.model.output.PromotionCheckbookDto;
import com.jdc.home.model.repo.BookPromotionRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class BookPromotionService {

	@Autowired
	private BookPromotionRepo repo;
	
	public List<Integer> findPromotionIdsByBookId( int bookId){
		
		Function<CriteriaBuilder, CriteriaQuery<Integer>> func = cb -> {
			var cq = cb.createQuery(Integer.class);
			var root = cq.from(BookPromotion.class);
			cq.select(root.get(BookPromotion_.promotion).get(Promotion_.id));
			cq.where(cb.equal(root.get(BookPromotion_.book).get(Book_.id), bookId),
						cb.equal(root.get(BookPromotion_.active), true),
						cb.equal(root.get(BookPromotion_.promotion).get(Promotion_.active), true));
			return cq;
		};
		
		return repo.search(func);
	}
	
	public List<PromotionCheckbookDto> findPromotionByBookId(int bookId){
		
		Function<CriteriaBuilder, CriteriaQuery<PromotionCheckbookDto>> func = cb -> {
			var cq = cb.createQuery(PromotionCheckbookDto.class);
			var root = cq.from(BookPromotion.class);
			PromotionCheckbookDto.select(cq, root);
			cq.where(cb.equal(root.get(BookPromotion_.book).get(Book_.id), bookId),
						cb.isTrue(root.get(BookPromotion_.active)),
						cb.isTrue(root.get(BookPromotion_.promotion).get(Promotion_.active)));
			return cq;
		};
		
		return repo.search(func);
	}
}
