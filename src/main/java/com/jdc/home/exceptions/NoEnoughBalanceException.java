package com.jdc.home.exceptions;

public class NoEnoughBalanceException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoEnoughBalanceException() {
		super("No Enough Balance To CheckOut");
	}
	
	public NoEnoughBalanceException(int needAmount) {
		super("No Enough Balance To CheckOut! Need %d ks.".formatted(needAmount));
	}

	
}
