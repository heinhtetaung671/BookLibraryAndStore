package com.jdc.home.model.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.home.model.entity.Promotion;
import com.jdc.home.model.entity.Promotion.Type;
import com.jdc.home.model.entity.Promotion_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromotionSearchForm {

	private String searchName;
	private Type searchType;
	private Integer searchDisPercent;
	private Boolean searchActive;
	
	public Predicate[] where(CriteriaBuilder cb, Root<Promotion> root) {
		
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(searchName))
			predicates.add(cb.like(cb.lower(root.get(Promotion_.name)), searchName));
			
		if(searchType != null) 
			predicates.add(cb.equal(root.get(Promotion_.type), searchType));
			
		if(searchDisPercent != null && searchDisPercent > 0) 
			predicates.add(cb.lessThanOrEqualTo(root.get(Promotion_.disPercent), searchDisPercent));
		
		if(searchActive != null)
			predicates.add(cb.equal( root.get(Promotion_.active), searchActive));
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
