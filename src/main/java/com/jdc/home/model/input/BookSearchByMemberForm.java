package com.jdc.home.model.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.home.model.entity.Book;
import com.jdc.home.model.entity.Book_;
import com.jdc.home.model.entity.Category;
import com.jdc.home.model.entity.Category_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSearchByMemberForm {

	private String name;
	private String author;
	private Category category;
	private Integer wsPrice;
	private Integer dtsPrice;
	private Integer bpdPrice;
	private Boolean active;

	private int page;
	private int pageSize = 10;
	private String sortBy = "id";

	public Predicate[] where(CriteriaBuilder cb, Root<Book> root) {

		var predicates = new ArrayList<Predicate>();

		if (StringUtils.hasLength(name))
			predicates.add(cb.like(cb.lower(root.get(Book_.name)), name.toLowerCase().concat("%")));

		if (StringUtils.hasLength(author))
			predicates.add(cb.equal(cb.lower(root.get(Book_.author)), author.toLowerCase()));

		if (category != null && StringUtils.hasLength(category.getName()))
			predicates.add(
					cb.equal(cb.lower(root.get(Book_.category).get(Category_.name)), category.getName().toLowerCase()));

		if (wsPrice != null && wsPrice > 0)
			predicates.add(cb.lessThanOrEqualTo(root.get(Book_.wsPrice), wsPrice));

		if (dtsPrice != null && dtsPrice > 0)
			predicates.add(cb.lessThanOrEqualTo(root.get(Book_.dtsPrice), dtsPrice));

		if (bpdPrice != null && bpdPrice > 0)
			predicates.add(cb.lessThanOrEqualTo(root.get(Book_.bpdPrice), bpdPrice));

		if (active != null)
			predicates.add(cb.equal(root.get(Book_.active), active));

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
