package com.jdc.home.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Getter;
import lombok.Setter;

@Component
@SessionScope
@Getter
@Setter
public class CartContainer {

	Map<Integer, PreDataForABookInCart> cart;
	
	public CartContainer() {
		cart = new HashMap<Integer, PreDataForABookInCart>();
	}
	
}
