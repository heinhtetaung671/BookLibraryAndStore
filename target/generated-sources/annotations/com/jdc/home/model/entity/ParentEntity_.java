package com.jdc.home.model.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.MappedSuperclassType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(ParentEntity.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class ParentEntity_ {

	
	/**
	 * @see com.jdc.home.model.entity.ParentEntity#createBy
	 **/
	public static volatile SingularAttribute<ParentEntity, String> createBy;
	
	/**
	 * @see com.jdc.home.model.entity.ParentEntity#updateBy
	 **/
	public static volatile SingularAttribute<ParentEntity, String> updateBy;
	
	/**
	 * @see com.jdc.home.model.entity.ParentEntity#updateAt
	 **/
	public static volatile SingularAttribute<ParentEntity, LocalDateTime> updateAt;
	
	/**
	 * @see com.jdc.home.model.entity.ParentEntity
	 **/
	public static volatile MappedSuperclassType<ParentEntity> class_;
	
	/**
	 * @see com.jdc.home.model.entity.ParentEntity#createAt
	 **/
	public static volatile SingularAttribute<ParentEntity, LocalDateTime> createAt;

	public static final String CREATE_BY = "createBy";
	public static final String UPDATE_BY = "updateBy";
	public static final String UPDATE_AT = "updateAt";
	public static final String CREATE_AT = "createAt";

}

