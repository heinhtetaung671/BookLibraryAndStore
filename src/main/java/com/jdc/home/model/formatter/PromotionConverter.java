package com.jdc.home.model.formatter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import com.jdc.home.model.entity.Promotion;
import com.jdc.home.model.repo.PromotionRepo;

@Service
public class PromotionConverter implements Converter<String[], List<Promotion>> {

	@Autowired
	private PromotionRepo repo;
	
	@Override
	public List<Promotion> convert(String[] ids) {
		var promotions = new ArrayList<Promotion>();
		
		for(var id : ids) {
			promotions.add(repo.findById(Integer.parseInt(id)).orElseThrow());
		}
		
		return promotions;
	}

}
