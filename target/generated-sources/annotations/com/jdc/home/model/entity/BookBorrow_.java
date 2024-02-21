package com.jdc.home.model.entity;

import com.jdc.home.model.entity.BookBorrow.Status;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(BookBorrow.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class BookBorrow_ {

	
	/**
	 * @see com.jdc.home.model.entity.BookBorrow#returnDate
	 **/
	public static volatile SingularAttribute<BookBorrow, LocalDateTime> returnDate;
	
	/**
	 * @see com.jdc.home.model.entity.BookBorrow#book
	 **/
	public static volatile SingularAttribute<BookBorrow, Book> book;
	
	/**
	 * @see com.jdc.home.model.entity.BookBorrow#id
	 **/
	public static volatile SingularAttribute<BookBorrow, Integer> id;
	
	/**
	 * @see com.jdc.home.model.entity.BookBorrow
	 **/
	public static volatile EntityType<BookBorrow> class_;
	
	/**
	 * @see com.jdc.home.model.entity.BookBorrow#account
	 **/
	public static volatile SingularAttribute<BookBorrow, Account> account;
	
	/**
	 * @see com.jdc.home.model.entity.BookBorrow#borrowDate
	 **/
	public static volatile SingularAttribute<BookBorrow, LocalDateTime> borrowDate;
	
	/**
	 * @see com.jdc.home.model.entity.BookBorrow#status
	 **/
	public static volatile SingularAttribute<BookBorrow, Status> status;

	public static final String RETURN_DATE = "returnDate";
	public static final String BOOK = "book";
	public static final String ID = "id";
	public static final String ACCOUNT = "account";
	public static final String BORROW_DATE = "borrowDate";
	public static final String STATUS = "status";

}

