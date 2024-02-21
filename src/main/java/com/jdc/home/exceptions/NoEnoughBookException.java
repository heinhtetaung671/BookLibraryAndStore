package com.jdc.home.exceptions;

public class NoEnoughBookException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoEnoughBookException() {
		super("No Enougn Book!");
	}
	
	public NoEnoughBookException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
	
}
