package com.jdc.home.controller.searchData;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.jdc.home.model.input.AccountSearchForm;
import com.jdc.home.model.input.BookSearchByMemberForm;
import com.jdc.home.model.input.CategorySearchForm;
import com.jdc.home.model.input.PromotionSearchForm;

import lombok.Getter;
import lombok.Setter;

@Component
@SessionScope
@Getter
@Setter
public class SearchDataContainer {

	private CategorySearchForm categorySearchForm;
	
	private PromotionSearchForm promotionSearchForm;
	
	private AccountSearchForm accountSearchForm;
	
	private BookSearchByMemberForm bookSearchbyMemberForm;
}
