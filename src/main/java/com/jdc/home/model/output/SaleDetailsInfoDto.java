package com.jdc.home.model.output;

import com.jdc.home.model.entity.Book_;
import com.jdc.home.model.entity.Category_;
import com.jdc.home.model.entity.Promotion_;
import com.jdc.home.model.entity.SaleDetails;
import com.jdc.home.model.entity.SaleDetails_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record SaleDetailsInfoDto(
		String bookName,
		String author,
		int price,
		int qty,
		String categoryName,
		String promotionName, 
		com.jdc.home.model.entity.Promotion.Type promotionType,
		Integer disPercent
		) {

	public static void select(CriteriaQuery<SaleDetailsInfoDto> cq, Root<SaleDetails> root) {
		var promotion = root.join(SaleDetails_.promotion, JoinType.LEFT);
		cq.multiselect(
				root.get(SaleDetails_.book).get(Book_.name),
				root.get(SaleDetails_.book).get(Book_.author),
				root.get(SaleDetails_.book).get(Book_.dtsPrice),
				root.get(SaleDetails_.qty),
				root.get(SaleDetails_.book).get(Book_.category).get(Category_.name),
				promotion.get(Promotion_.name),
				promotion.get(Promotion_.type),
				promotion.get(Promotion_.disPercent)
				);
	}
	
}
