package com.jdc.home.model.entity;

import com.jdc.home.model.entity.BookBorrow.Status;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(BookBorrowFoc.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class BookBorrowFoc_ {

	
	/**
	 * @see com.jdc.home.model.entity.BookBorrowFoc#book
	 **/
	public static volatile SingularAttribute<BookBorrowFoc, Book> book;
	
	/**
	 * @see com.jdc.home.model.entity.BookBorrowFoc#updateAt
	 **/
	public static volatile SingularAttribute<BookBorrowFoc, LocalDateTime> updateAt;
	
	/**
	 * @see com.jdc.home.model.entity.BookBorrowFoc#id
	 **/
	public static volatile SingularAttribute<BookBorrowFoc, String> id;
	
	/**
	 * @see com.jdc.home.model.entity.BookBorrowFoc
	 **/
	public static volatile EntityType<BookBorrowFoc> class_;
	
	/**
	 * @see com.jdc.home.model.entity.BookBorrowFoc#createAt
	 **/
	public static volatile SingularAttribute<BookBorrowFoc, LocalDateTime> createAt;
	
	/**
	 * @see com.jdc.home.model.entity.BookBorrowFoc#status
	 **/
	public static volatile SingularAttribute<BookBorrowFoc, Status> status;

	public static final String BOOK = "book";
	public static final String UPDATE_AT = "updateAt";
	public static final String ID = "id";
	public static final String CREATE_AT = "createAt";
	public static final String STATUS = "status";

}

