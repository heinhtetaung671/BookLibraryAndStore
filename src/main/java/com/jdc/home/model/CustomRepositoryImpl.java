package com.jdc.home.model;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class CustomRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomRepository<T, ID> {

	@PersistenceContext
	private EntityManager em;

	public CustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
	}

	@Override
	public <R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> searchFunc) {

		var cb = em.getCriteriaBuilder();

		var cq = searchFunc.apply(cb);

		return em.createQuery(cq).getResultList();
	}

	@Override
	public <R> R find(Function<CriteriaBuilder, CriteriaQuery<R>> findQuery) {

		var cb = em.getCriteriaBuilder();

		var cq = findQuery.apply(cb);
		
		return em.createQuery(cq).getSingleResult();
	}

	@Override
	public <R> Page<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> searchFunc,
			Function<CriteriaBuilder, CriteriaQuery<Long>> countQuery, int page, int pageSize) {

		var count = find(countQuery);

		var cb = em.getCriteriaBuilder();

		var cq = searchFunc.apply(cb);

		var query = em.createQuery(cq);

		query.setFirstResult(page * pageSize);
		query.setMaxResults(pageSize);
		var content = query.getResultList();

		var pageable = PageRequest.of(page, pageSize);

		return new PageImpl<R>(content, pageable, count);
	}

}
