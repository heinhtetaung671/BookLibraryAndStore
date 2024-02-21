package com.jdc.home.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {

	private int currentPage;
	private int totalPage;
	private int pageSize;
	private List<Integer> pageList;
	
	public Pagination(Page<?> page) {
		this.currentPage = page.getNumber() + 1;
		this.totalPage = page.getTotalPages();
		this.pageSize = page.getSize();
		
		build();
	}
	
	private void build() {
		
		pageList = new ArrayList<Integer>();
		pageList.add(currentPage);
		
		while(pageList.size() < 3 && pageList.getFirst() > 1)
			pageList.addFirst(pageList.getFirst() - 1);
		
		while(pageList.size() < 5 && pageList.getLast() < totalPage)
			pageList.addLast(pageList.getLast() + 1);
		
		while(pageList.size() < 5 && pageList.getFirst() > 1)
			pageList.addFirst(pageList.getFirst() - 1);
	}
	
}
