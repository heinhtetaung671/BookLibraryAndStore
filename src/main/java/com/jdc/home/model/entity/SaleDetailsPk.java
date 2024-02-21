package com.jdc.home.model.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class SaleDetailsPk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int saleId;
	private int bookId;
	
}
