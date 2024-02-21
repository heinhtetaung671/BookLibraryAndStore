package com.jdc.home.model.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jdc.home.model.entity.Account_;
import com.jdc.home.model.entity.Sale;
import com.jdc.home.model.entity.Sale_;
import com.jdc.home.model.input.DtoForPagination;
import com.jdc.home.model.output.SaleInfoDto;
import com.jdc.home.model.repo.SaleRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Service
public class SaleService {

	@Autowired
	private SaleRepo saleRepo;
	
	public Page<SaleInfoDto> search(Optional<Integer> accountId, DtoForPagination dtoForPagination){
		
		var email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		
		Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc = cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Sale.class);
			cq.select(cb.count(root.get(Sale_.id)));
		
			if(accountId.isPresent()) {
				whereWithId(accountId.get(), cb, cq, root);
			} else {
				cq.where(cb.equal(root.get(Sale_.account).get(Account_.email), email));
			}

			return cq;
		};
		
		Function<CriteriaBuilder, CriteriaQuery<SaleInfoDto>> func = cb -> {
			var cq = cb.createQuery(SaleInfoDto.class);
			var root = cq.from(Sale.class);
			SaleInfoDto.select(cb, cq, root);

			if(accountId.isPresent()) {
				whereWithId(accountId.get(), cb, cq, root);
			} else {
				cq.where(cb.equal(root.get(Sale_.account).get(Account_.email), email));
			}
			return cq;
		};
		
		return saleRepo.search(func, countFunc, dtoForPagination.getPage(), dtoForPagination.getPageSize());
	}

	private void whereWithId(Integer accountId, CriteriaBuilder cb, CriteriaQuery<?> cq, Root<Sale> root) {
		cq.where(cb.equal(root.get(Sale_.account).get(Account_.id), accountId));
		
	}
	
}
