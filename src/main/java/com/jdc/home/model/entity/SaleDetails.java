package com.jdc.home.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class SaleDetails extends ParentEntity {

	@EmbeddedId
	private SaleDetailsPk saleDetailsPk;
	
	@ManyToOne
	@MapsId("saleId")
	private Sale sale;
	@ManyToOne
	@MapsId("bookId")
	private Book book;
	
	@ManyToOne
	private Promotion promotion;
	private int qty;
	
}
