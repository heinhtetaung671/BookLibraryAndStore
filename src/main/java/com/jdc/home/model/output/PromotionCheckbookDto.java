package com.jdc.home.model.output;

import com.jdc.home.model.entity.BookPromotion;
import com.jdc.home.model.entity.BookPromotion_;
import com.jdc.home.model.entity.Promotion.Type;
import com.jdc.home.model.entity.Promotion_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record PromotionCheckbookDto(
		int id,
		String name,
		Type type,
		int disPercent
		) {

	public static void select(CriteriaQuery<PromotionCheckbookDto> cq, Root<BookPromotion> root) {
		cq.multiselect(
				root.get(BookPromotion_.promotion).get(Promotion_.id),
				root.get(BookPromotion_.promotion).get(Promotion_.name),
				root.get(BookPromotion_.promotion).get(Promotion_.type),
				root.get(BookPromotion_.promotion).get(Promotion_.disPercent)
				);
	}
	
}
