package com.jdc.home.model.output;

import com.jdc.home.model.entity.Promotion;
import com.jdc.home.model.entity.Promotion_;
import com.jdc.home.model.entity.Promotion.Type;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record PromotionInfoDto(
		int id,
		String name,
		Type type,
		int disPercent,
		boolean active
		) {

	public static void select(CriteriaQuery<PromotionInfoDto> cq, Root<Promotion> root) {
		
		cq.multiselect(
				root.get(Promotion_.id),
				root.get(Promotion_.name),
				root.get(Promotion_.type),
				root.get(Promotion_.disPercent),
				root.get(Promotion_.active)
				);
		
	}

	
	
}
