package com.jdc.home.model.formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import com.jdc.home.model.entity.Category;
import com.jdc.home.model.repo.CategoryRepo;

@Service
public class CategoryFormatter implements Formatter<Category>{

	@Autowired
	private CategoryRepo repo;
	
	@Override
	public String print(Category category, Locale locale) {
		// TODO Auto-generated method stub
		return String.valueOf(category.getId());
	}

	@Override
	public Category parse(String text, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		return Optional.ofNullable(text).map(Integer::parseInt).flatMap(repo::findById).orElseThrow();
	}

}
