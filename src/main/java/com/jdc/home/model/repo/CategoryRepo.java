package com.jdc.home.model.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.jdc.home.model.CustomRepository;
import com.jdc.home.model.entity.Category;
import com.jdc.home.model.output.CategorySelectionDto;

public interface CategoryRepo extends CustomRepository<Category, Integer>{

	Optional<Category> findByName(String name);
	
	@Query(name = "Category.findAll", nativeQuery = true)
	List<CategorySelectionDto> findWithNativeSql();
	
}
