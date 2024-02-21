package com.jdc.home.exceptions;

public class PromotionNotAvailableException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PromotionNotAvailableException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
	
	public PromotionNotAvailableException() {
		// TODO Auto-generated constructor stub
		super("Please only select the available promotions!");
	}
	
	
}
