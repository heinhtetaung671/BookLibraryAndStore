package com.jdc.home.model.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.home.model.entity.Promotion;
import com.jdc.home.model.input.PromotionEditForm;
import com.jdc.home.model.input.PromotionSearchForm;
import com.jdc.home.model.output.PromotionInfoDto;
import com.jdc.home.model.repo.PromotionRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class PromotionService {

	@Autowired
	private PromotionRepo promotionRepo;
	
	@Transactional
	public Promotion save(PromotionEditForm form) {
		
		if(form.getId() == 0) {
			var promotion = new Promotion();
			promotion.setName(form.getName());
			promotion.setType(form.getType());
			promotion.setDisPercent(form.getDisPercent());
			promotion.setActive(form.isActive());
			return promotionRepo.save(promotion);
		}
		
		var promotion = promotionRepo.findById(form.getId()).orElseThrow();
		promotion.setName(form.getName());
		promotion.setType(form.getType());
		promotion.setActive(form.isActive());
		promotion.setDisPercent(form.getDisPercent());
		
		return promotion;
	}
	
	public List<PromotionInfoDto> search(PromotionSearchForm form){
		
		Function<CriteriaBuilder, CriteriaQuery<PromotionInfoDto>> func = cb -> {
			var cq = cb.createQuery(PromotionInfoDto.class);
			var root = cq.from(Promotion.class);
			PromotionInfoDto.select(cq, root);
			cq.where(form.where(cb, root));
			return cq;
		};
		
		return promotionRepo.search(func);
	}
	
}
