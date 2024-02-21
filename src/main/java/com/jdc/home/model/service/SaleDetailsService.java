package com.jdc.home.model.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.home.model.entity.SaleDetails;
import com.jdc.home.model.entity.SaleDetails_;
import com.jdc.home.model.entity.Sale_;
import com.jdc.home.model.output.SaleDetailsInfoDto;
import com.jdc.home.model.repo.SaleDetailsRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class SaleDetailsService {

	@Autowired
	private SaleDetailsRepo saleDetailsRepo;

	public List<SaleDetailsInfoDto> findSaleDetailsInfos(Integer saleId) {

		Function<CriteriaBuilder, CriteriaQuery<SaleDetailsInfoDto>> func = cb -> {
			var cq = cb.createQuery(SaleDetailsInfoDto.class);
			var root = cq.from(SaleDetails.class);
			SaleDetailsInfoDto.select(cq, root);
			cq.where(cb.equal(root.get(SaleDetails_.sale).get(Sale_.id), saleId));
			return cq;
		};

		return saleDetailsRepo.search(func);
	}

	public int caculateTotalPrice(List<SaleDetailsInfoDto> saleDetailsInfos) {
		
		return saleDetailsInfos.stream().mapToInt(sdInfo -> {
			if (sdInfo.disPercent() != null) {
				return (sdInfo.price() * sdInfo.qty())
						- (((sdInfo.price() * sdInfo.qty()) * sdInfo.disPercent()) / 100);
			}
			return sdInfo.price() * sdInfo.qty();
		}).sum();
		
	}

}
