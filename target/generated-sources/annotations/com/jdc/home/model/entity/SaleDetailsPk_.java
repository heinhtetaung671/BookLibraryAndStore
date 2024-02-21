package com.jdc.home.model.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SaleDetailsPk.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class SaleDetailsPk_ {

	
	/**
	 * @see com.jdc.home.model.entity.SaleDetailsPk#saleId
	 **/
	public static volatile SingularAttribute<SaleDetailsPk, Integer> saleId;
	
	/**
	 * @see com.jdc.home.model.entity.SaleDetailsPk
	 **/
	public static volatile EmbeddableType<SaleDetailsPk> class_;
	
	/**
	 * @see com.jdc.home.model.entity.SaleDetailsPk#bookId
	 **/
	public static volatile SingularAttribute<SaleDetailsPk, Integer> bookId;

	public static final String SALE_ID = "saleId";
	public static final String BOOK_ID = "bookId";

}

