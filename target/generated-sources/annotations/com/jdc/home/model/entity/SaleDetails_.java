package com.jdc.home.model.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SaleDetails.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class SaleDetails_ extends com.jdc.home.model.entity.ParentEntity_ {

	
	/**
	 * @see com.jdc.home.model.entity.SaleDetails#sale
	 **/
	public static volatile SingularAttribute<SaleDetails, Sale> sale;
	
	/**
	 * @see com.jdc.home.model.entity.SaleDetails#book
	 **/
	public static volatile SingularAttribute<SaleDetails, Book> book;
	
	/**
	 * @see com.jdc.home.model.entity.SaleDetails#qty
	 **/
	public static volatile SingularAttribute<SaleDetails, Integer> qty;
	
	/**
	 * @see com.jdc.home.model.entity.SaleDetails
	 **/
	public static volatile EntityType<SaleDetails> class_;
	
	/**
	 * @see com.jdc.home.model.entity.SaleDetails#saleDetailsPk
	 **/
	public static volatile SingularAttribute<SaleDetails, SaleDetailsPk> saleDetailsPk;
	
	/**
	 * @see com.jdc.home.model.entity.SaleDetails#promotion
	 **/
	public static volatile SingularAttribute<SaleDetails, Promotion> promotion;

	public static final String SALE = "sale";
	public static final String BOOK = "book";
	public static final String QTY = "qty";
	public static final String SALE_DETAILS_PK = "saleDetailsPk";
	public static final String PROMOTION = "promotion";

}

