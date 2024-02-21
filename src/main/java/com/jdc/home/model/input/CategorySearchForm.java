package com.jdc.home.model.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.home.model.entity.Category;
import com.jdc.home.model.entity.Category_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategorySearchForm {

	private String searchName;
	private Boolean active;
	private String sortBy = "name";
	
	public Predicate[] where(CriteriaBuilder cb, Root<Category> root) {
		
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(searchName)) {
			predicates.add(cb.equal(root.get(Category_.name), searchName));
		}
		
		if(null != active) {
			predicates.add(cb.equal(root.get(Category_.active), active));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
