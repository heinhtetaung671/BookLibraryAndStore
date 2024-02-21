package com.jdc.home.model.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(BookPromotionPk.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class BookPromotionPk_ {

	
	/**
	 * @see com.jdc.home.model.entity.BookPromotionPk
	 **/
	public static volatile EmbeddableType<BookPromotionPk> class_;
	
	/**
	 * @see com.jdc.home.model.entity.BookPromotionPk#promotionId
	 **/
	public static volatile SingularAttribute<BookPromotionPk, Integer> promotionId;
	
	/**
	 * @see com.jdc.home.model.entity.BookPromotionPk#bookId
	 **/
	public static volatile SingularAttribute<BookPromotionPk, Integer> bookId;

	public static final String PROMOTION_ID = "promotionId";
	public static final String BOOK_ID = "bookId";

}

