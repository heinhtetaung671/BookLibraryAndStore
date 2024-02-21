package com.jdc.home.model.output;

import java.time.LocalDateTime;

import com.jdc.home.model.entity.Book_;
import com.jdc.home.model.entity.Promotion_;
import com.jdc.home.model.entity.Sale;
import com.jdc.home.model.entity.SaleDetails_;
import com.jdc.home.model.entity.Sale_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record SaleInfoDto(int id, int totalPrice, int point, LocalDateTime createAt) {

	public static void select(CriteriaBuilder cb, CriteriaQuery<SaleInfoDto> cq, Root<Sale> root) {
		
		var sd = root.join(Sale_.saleDetails);
		var promotion = sd.join(SaleDetails_.promotion, JoinType.LEFT);
		cq.multiselect(
				root.get(Sale_.id),
				cb.sum(
						cb.diff( 
								cb.prod(sd.get(SaleDetails_.qty), sd.get(SaleDetails_.book).get(Book_.dtsPrice)),
								cb.quot(
									cb.coalesce(cb.prod(
										cb.prod(sd.get(SaleDetails_.qty), sd.get(SaleDetails_.book).get(Book_.dtsPrice)),
										promotion.get(Promotion_.disPercent )), 0),
									100))),
						
				root.get(Sale_.point), root.get(Sale_.createAt));
		
		cq.groupBy(root.get(Sale_.id), root.get(Sale_.point), root.get(Sale_.createAt));
	}

}
