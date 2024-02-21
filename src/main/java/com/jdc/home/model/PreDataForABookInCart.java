package com.jdc.home.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreDataForABookInCart {

	private Counter counter;
	private int promotionId;

	public PreDataForABookInCart() {
		counter = new Counter();
	}
	
}
