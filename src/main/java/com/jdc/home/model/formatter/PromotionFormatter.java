package com.jdc.home.model.formatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.jdc.home.model.entity.Promotion;
import com.jdc.home.model.repo.PromotionRepo;

//@Service
public class PromotionFormatter implements Formatter<List<Promotion>> {

//	@Autowired
	private PromotionRepo repo;
	
	@Override
	public String print(List<Promotion> promotions, Locale locale) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public List<Promotion> parse(String promotionIds, Locale locale) throws ParseException {
		
		String[] ids = promotionIds.split(",");
		var promotions = new ArrayList<Promotion>();
		
		for(var id : ids) {
			promotions.add(repo.findById(Integer.parseInt(id)).orElseThrow());
		}
		
		return promotions;
	}

}
