package com.jdc.home.model.repo;

import com.jdc.home.model.CustomRepository;
import com.jdc.home.model.entity.BookPromotion;
import com.jdc.home.model.entity.BookPromotionPk;

public interface BookPromotionRepo extends CustomRepository<BookPromotion, BookPromotionPk>{

	boolean existsByBookIdAndPromotionIdAndActive(int bookId, int promotionId, boolean active);
	
}
