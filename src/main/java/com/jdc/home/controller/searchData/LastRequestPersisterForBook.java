package com.jdc.home.controller.searchData;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.home.model.input.BookSearchForm;

import jakarta.servlet.http.HttpSession;

public class LastRequestPersisterForBook {

	public static void doPersistRequestData(final String storeKey, HttpSession session, RedirectAttributes redirectAttribute) {
		
	var recentPublicSearchData = (PublicSearchData) session.getAttribute(storeKey);
		
		if(recentPublicSearchData == null) {
			recentPublicSearchData =  new  PublicSearchData(new BookSearchForm(null, null), 0, 10, "name", "asc");
		}
		
		redirectAttribute.addAttribute("keyword", recentPublicSearchData.searchForm().keyword());
		redirectAttribute.addAttribute("active", recentPublicSearchData.searchForm().active());
		redirectAttribute.addAttribute("page",recentPublicSearchData.page());
		redirectAttribute.addAttribute("pageSize",recentPublicSearchData.pageSize());
		redirectAttribute.addAttribute("sortBy",recentPublicSearchData.sortBy());
		redirectAttribute.addAttribute("direction",recentPublicSearchData.direction());
	}
	
}
