package com.jdc.home.model.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Sale.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Sale_ extends com.jdc.home.model.entity.ParentEntity_ {

	
	/**
	 * @see com.jdc.home.model.entity.Sale#id
	 **/
	public static volatile SingularAttribute<Sale, Integer> id;
	
	/**
	 * @see com.jdc.home.model.entity.Sale#saleDetails
	 **/
	public static volatile ListAttribute<Sale, SaleDetails> saleDetails;
	
	/**
	 * @see com.jdc.home.model.entity.Sale
	 **/
	public static volatile EntityType<Sale> class_;
	
	/**
	 * @see com.jdc.home.model.entity.Sale#account
	 **/
	public static volatile SingularAttribute<Sale, Account> account;
	
	/**
	 * @see com.jdc.home.model.entity.Sale#point
	 **/
	public static volatile SingularAttribute<Sale, Integer> point;

	public static final String ID = "id";
	public static final String SALE_DETAILS = "saleDetails";
	public static final String ACCOUNT = "account";
	public static final String POINT = "point";

}

