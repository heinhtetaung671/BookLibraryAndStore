package com.jdc.home.model.entity;

import com.jdc.home.model.entity.Account.Authority;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Account.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Account_ extends com.jdc.home.model.entity.ParentEntity_ {

	
	/**
	 * @see com.jdc.home.model.entity.Account#active
	 **/
	public static volatile SingularAttribute<Account, Boolean> active;
	
	/**
	 * @see com.jdc.home.model.entity.Account#profileImage
	 **/
	public static volatile SingularAttribute<Account, String> profileImage;
	
	/**
	 * @see com.jdc.home.model.entity.Account#point
	 **/
	public static volatile SingularAttribute<Account, Float> point;
	
	/**
	 * @see com.jdc.home.model.entity.Account#statusMessage
	 **/
	public static volatile SingularAttribute<Account, String> statusMessage;
	
	/**
	 * @see com.jdc.home.model.entity.Account#password
	 **/
	public static volatile SingularAttribute<Account, String> password;
	
	/**
	 * @see com.jdc.home.model.entity.Account#balance
	 **/
	public static volatile SingularAttribute<Account, Integer> balance;
	
	/**
	 * @see com.jdc.home.model.entity.Account#phone
	 **/
	public static volatile SingularAttribute<Account, String> phone;
	
	/**
	 * @see com.jdc.home.model.entity.Account#authority
	 **/
	public static volatile SingularAttribute<Account, Authority> authority;
	
	/**
	 * @see com.jdc.home.model.entity.Account#name
	 **/
	public static volatile SingularAttribute<Account, String> name;
	
	/**
	 * @see com.jdc.home.model.entity.Account#id
	 **/
	public static volatile SingularAttribute<Account, Integer> id;
	
	/**
	 * @see com.jdc.home.model.entity.Account#locked
	 **/
	public static volatile SingularAttribute<Account, Boolean> locked;
	
	/**
	 * @see com.jdc.home.model.entity.Account
	 **/
	public static volatile EntityType<Account> class_;
	
	/**
	 * @see com.jdc.home.model.entity.Account#email
	 **/
	public static volatile SingularAttribute<Account, String> email;

	public static final String ACTIVE = "active";
	public static final String PROFILE_IMAGE = "profileImage";
	public static final String POINT = "point";
	public static final String STATUS_MESSAGE = "statusMessage";
	public static final String PASSWORD = "password";
	public static final String BALANCE = "balance";
	public static final String PHONE = "phone";
	public static final String AUTHORITY = "authority";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String LOCKED = "locked";
	public static final String EMAIL = "email";

}

