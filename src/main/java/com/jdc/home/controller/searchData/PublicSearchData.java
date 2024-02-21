package com.jdc.home.controller.searchData;

import com.jdc.home.model.input.BookSearchForm;

public record PublicSearchData(
			BookSearchForm searchForm,
			int page,
			int pageSize,
			String sortBy,
			String direction
			
		){

	
	
}
