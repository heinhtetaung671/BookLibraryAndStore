package com.jdc.home.model;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@NoRepositoryBean
public interface CustomRepository<T, ID> extends JpaRepositoryImplementation<T, ID>{

	<R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> searchFunc);
	
	<R> R find(Function<CriteriaBuilder, CriteriaQuery<R>> findQuery);
	
	<R> Page<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> searchFunc, Function<CriteriaBuilder, CriteriaQuery<Long>> countQuery, int page, int pageSize);
	
}
