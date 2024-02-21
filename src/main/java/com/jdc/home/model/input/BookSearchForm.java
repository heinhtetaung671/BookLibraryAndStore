package com.jdc.home.model.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.home.model.entity.Book;
import com.jdc.home.model.entity.Book_;
import com.jdc.home.model.entity.Category_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record BookSearchForm(
		String keyword,
		Boolean active
		) {

	// Predicate
	public Predicate[] where(CriteriaBuilder cb,Root<Book> root) {
		
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(keyword)) {
			predicates.add(
				cb.or(cb.equal(root.get(Book_.category).get(Category_.name), keyword),
				cb.like(cb.lower(root.get(Book_.name)), keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(root.get(Book_.author)), keyword.toLowerCase().concat("%"))));
		}
		
		if(null != active) {
			predicates.add(cb.equal(root.get(Book_.active), active));
		}
		
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
}
