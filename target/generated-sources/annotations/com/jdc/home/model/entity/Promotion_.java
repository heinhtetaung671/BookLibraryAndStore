package com.jdc.home.model.entity;

import com.jdc.home.model.entity.Promotion.Type;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Promotion.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Promotion_ extends com.jdc.home.model.entity.ParentEntity_ {

	
	/**
	 * @see com.jdc.home.model.entity.Promotion#bookPromotion
	 **/
	public static volatile ListAttribute<Promotion, BookPromotion> bookPromotion;
	
	/**
	 * @see com.jdc.home.model.entity.Promotion#name
	 **/
	public static volatile SingularAttribute<Promotion, String> name;
	
	/**
	 * @see com.jdc.home.model.entity.Promotion#active
	 **/
	public static volatile SingularAttribute<Promotion, Boolean> active;
	
	/**
	 * @see com.jdc.home.model.entity.Promotion#id
	 **/
	public static volatile SingularAttribute<Promotion, Integer> id;
	
	/**
	 * @see com.jdc.home.model.entity.Promotion#type
	 **/
	public static volatile SingularAttribute<Promotion, Type> type;
	
	/**
	 * @see com.jdc.home.model.entity.Promotion
	 **/
	public static volatile EntityType<Promotion> class_;
	
	/**
	 * @see com.jdc.home.model.entity.Promotion#disPercent
	 **/
	public static volatile SingularAttribute<Promotion, Integer> disPercent;

	public static final String BOOK_PROMOTION = "bookPromotion";
	public static final String NAME = "name";
	public static final String ACTIVE = "active";
	public static final String ID = "id";
	public static final String TYPE = "type";
	public static final String DIS_PERCENT = "disPercent";

}

