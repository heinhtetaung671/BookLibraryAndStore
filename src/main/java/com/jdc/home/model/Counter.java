package com.jdc.home.model;

public class Counter {

	private int count;
	
	public int getCount() {
		return count;
	}
	
	public void countUp() {
		++ count;
	}
	
	public void countDown() {
		-- count;
	}
}
