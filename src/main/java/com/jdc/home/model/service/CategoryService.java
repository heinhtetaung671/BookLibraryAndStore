package com.jdc.home.model.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.home.model.entity.Category;
import com.jdc.home.model.input.CategoryEditForm;
import com.jdc.home.model.input.CategorySearchForm;
import com.jdc.home.model.output.CategoryInfoDto;
import com.jdc.home.model.repo.CategoryRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo repo;
	
	public List<CategoryInfoDto> search(CategorySearchForm searchForm){
		
		Function<CriteriaBuilder, CriteriaQuery<CategoryInfoDto>> searchFunc = cb -> {
			var cq = cb.createQuery(CategoryInfoDto.class);
			var root = cq.from(Category.class);
			CategoryInfoDto.select(cq, root);
			cq.where(searchForm.where(cb,root));
			cq.orderBy(cb.asc(root.get(searchForm.getSortBy())));
			return cq;
		};
		
		return repo.search(searchFunc);
	}
		
	@Transactional
	public Category save(CategoryEditForm form) {
		
		if(form.getId() == 0) {
			return repo.save(form.toCategory());
		}
		
		var category = repo.findById(form.getId()).orElseThrow();
		category.setName(form.getName());
		category.setActive(form.getActive());
		return category;
	}
	
}
