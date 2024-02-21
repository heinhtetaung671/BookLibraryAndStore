package com.jdc.home.model.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Category.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Category_ extends com.jdc.home.model.entity.ParentEntity_ {

	
	/**
	 * @see com.jdc.home.model.entity.Category#name
	 **/
	public static volatile SingularAttribute<Category, String> name;
	
	/**
	 * @see com.jdc.home.model.entity.Category#active
	 **/
	public static volatile SingularAttribute<Category, Boolean> active;
	
	/**
	 * @see com.jdc.home.model.entity.Category#id
	 **/
	public static volatile SingularAttribute<Category, Integer> id;
	
	/**
	 * @see com.jdc.home.model.entity.Category
	 **/
	public static volatile EntityType<Category> class_;

	public static final String QUERY_CATEGORY_FIND_ALL = "Category.findAll";
	public static final String NAME = "name";
	public static final String ACTIVE = "active";
	public static final String MAPPING_CATEGORY_MAPPING = "categoryMapping";
	public static final String ID = "id";

}

