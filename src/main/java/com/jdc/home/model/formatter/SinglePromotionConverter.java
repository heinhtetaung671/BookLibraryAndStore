package com.jdc.home.model.formatter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import com.jdc.home.model.entity.Promotion;
import com.jdc.home.model.repo.PromotionRepo;

@Service
public class SinglePromotionConverter implements Converter<String, List<Promotion>>{

	@Autowired
	private PromotionRepo promotionRepo;
	
	@Override
	public List<Promotion> convert(String id) {
		
		var promotions = new ArrayList<Promotion>();
		
		if(id != null) {
			promotions.add(promotionRepo.findById(Integer.parseInt(id)).orElseThrow());
		}
		
		return promotions;
	}

}
