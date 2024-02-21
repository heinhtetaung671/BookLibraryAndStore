package com.jdc.home.model.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jdc.home.model.entity.Account_;
import com.jdc.home.model.entity.BookBorrow;
import com.jdc.home.model.entity.BookBorrow.Status;
import com.jdc.home.model.entity.BookBorrow_;
import com.jdc.home.model.output.BorrowBookInfoDto;
import com.jdc.home.model.repo.BookBorrowRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class CustomerBorrowingBookService {

	@Autowired
	private BookBorrowRepo bookBorrowRepo;

	@PreAuthorize("hasAnyAuthority('CUSTOMER', 'MEMBER')")
	public List<BorrowBookInfoDto> searchBorrowBookWithStatus( Status status) {

		Function<CriteriaBuilder, CriteriaQuery<BorrowBookInfoDto>> func = cb -> {
			var cq = cb.createQuery(BorrowBookInfoDto.class);
			var root = cq.from(BookBorrow.class);
			BorrowBookInfoDto.select(cq, root);
			cq.where(whereQueryForBookBorrowWithStatus(cb, root, status));
			return cq;
		};
		
		return bookBorrowRepo.search(func);
	}

	private Predicate whereQueryForBookBorrowWithStatus(CriteriaBuilder cb, Root<BookBorrow> root, Status status) {
		var email = SecurityContextHolder.getContext().getAuthentication().getName();

		return cb.and(cb.equal(root.get(BookBorrow_.account).get(Account_.email), email),
				cb.equal(root.get(BookBorrow_.status), status));

	}

}
