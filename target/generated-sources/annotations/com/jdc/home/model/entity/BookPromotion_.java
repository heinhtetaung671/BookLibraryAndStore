package com.jdc.home.model.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(BookPromotion.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class BookPromotion_ extends com.jdc.home.model.entity.ParentEntity_ {

	
	/**
	 * @see com.jdc.home.model.entity.BookPromotion#bookPromotionPk
	 **/
	public static volatile SingularAttribute<BookPromotion, BookPromotionPk> bookPromotionPk;
	
	/**
	 * @see com.jdc.home.model.entity.BookPromotion#book
	 **/
	public static volatile SingularAttribute<BookPromotion, Book> book;
	
	/**
	 * @see com.jdc.home.model.entity.BookPromotion#active
	 **/
	public static volatile SingularAttribute<BookPromotion, Boolean> active;
	
	/**
	 * @see com.jdc.home.model.entity.BookPromotion
	 **/
	public static volatile EntityType<BookPromotion> class_;
	
	/**
	 * @see com.jdc.home.model.entity.BookPromotion#promotion
	 **/
	public static volatile SingularAttribute<BookPromotion, Promotion> promotion;

	public static final String BOOK_PROMOTION_PK = "bookPromotionPk";
	public static final String BOOK = "book";
	public static final String ACTIVE = "active";
	public static final String PROMOTION = "promotion";

}

