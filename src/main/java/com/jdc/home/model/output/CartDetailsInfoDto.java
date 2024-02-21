package com.jdc.home.model.output;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDetailsInfoDto {

	private int bookId;
	private String categoryName;
	private String bookName;
	private String author;
	private int dtsPrice;
	
	private int qty;
	
	private int promotionId;
	private int disPercent;
	
	private String errorMessage;
	private boolean error;
	
	private List<PromotionCheckbookDto> availablePromotions;
}
