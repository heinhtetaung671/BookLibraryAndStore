package com.jdc.home.model.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Book.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Book_ extends com.jdc.home.model.entity.ParentEntity_ {

	
	/**
	 * @see com.jdc.home.model.entity.Book#bookPromotions
	 **/
	public static volatile ListAttribute<Book, BookPromotion> bookPromotions;
	
	/**
	 * @see com.jdc.home.model.entity.Book#wsPrice
	 **/
	public static volatile SingularAttribute<Book, Integer> wsPrice;
	
	/**
	 * @see com.jdc.home.model.entity.Book#dtsPrice
	 **/
	public static volatile SingularAttribute<Book, Integer> dtsPrice;
	
	/**
	 * @see com.jdc.home.model.entity.Book#author
	 **/
	public static volatile SingularAttribute<Book, String> author;
	
	/**
	 * @see com.jdc.home.model.entity.Book#name
	 **/
	public static volatile SingularAttribute<Book, String> name;
	
	/**
	 * @see com.jdc.home.model.entity.Book#active
	 **/
	public static volatile SingularAttribute<Book, Boolean> active;
	
	/**
	 * @see com.jdc.home.model.entity.Book#qtyForBorrow
	 **/
	public static volatile SingularAttribute<Book, Integer> qtyForBorrow;
	
	/**
	 * @see com.jdc.home.model.entity.Book#id
	 **/
	public static volatile SingularAttribute<Book, Integer> id;
	
	/**
	 * @see com.jdc.home.model.entity.Book#category
	 **/
	public static volatile SingularAttribute<Book, Category> category;
	
	/**
	 * @see com.jdc.home.model.entity.Book
	 **/
	public static volatile EntityType<Book> class_;
	
	/**
	 * @see com.jdc.home.model.entity.Book#bpdPrice
	 **/
	public static volatile SingularAttribute<Book, Integer> bpdPrice;
	
	/**
	 * @see com.jdc.home.model.entity.Book#qtyForSale
	 **/
	public static volatile SingularAttribute<Book, Integer> qtyForSale;

	public static final String BOOK_PROMOTIONS = "bookPromotions";
	public static final String WS_PRICE = "wsPrice";
	public static final String DTS_PRICE = "dtsPrice";
	public static final String AUTHOR = "author";
	public static final String NAME = "name";
	public static final String ACTIVE = "active";
	public static final String QTY_FOR_BORROW = "qtyForBorrow";
	public static final String ID = "id";
	public static final String CATEGORY = "category";
	public static final String BPD_PRICE = "bpdPrice";
	public static final String QTY_FOR_SALE = "qtyForSale";

}

