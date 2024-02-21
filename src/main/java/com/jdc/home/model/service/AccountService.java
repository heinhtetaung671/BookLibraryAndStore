package com.jdc.home.model.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.home.model.entity.Account;
import com.jdc.home.model.entity.Account_;
import com.jdc.home.model.input.AccountSearchForm;
import com.jdc.home.model.output.AccountInfoDto;
import com.jdc.home.model.repo.AccountRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class AccountService {

	@Autowired
	private AccountRepo accountRepo;

	public int findBalance() {

		var email = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
				.filter(a -> !(a instanceof AnonymousAuthenticationToken)).map(a -> a.getName())
				.orElse(null);

		if (email == null) {
			return 0;
		}

		Function<CriteriaBuilder, CriteriaQuery<Integer>> func = cb -> {
			var cq = cb.createQuery(Integer.class);
			var root = cq.from(Account.class);
			cq.select(root.get(Account_.balance));
			cq.where(cb.equal(root.get(Account_.email), email));
			return cq;
		};

		return accountRepo.find(func);
	}

	public Page<AccountInfoDto> searchAccounts(AccountSearchForm searchForm) {

		Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc = cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Account.class);
			cq.select(cb.count(root.get(Account_.id)));
			cq.where(searchForm.where(cb, root));
			return cq;
		};

		Function<CriteriaBuilder, CriteriaQuery<AccountInfoDto>> searchFunc = cb -> {
			var cq = cb.createQuery(AccountInfoDto.class);
			var root = cq.from(Account.class);
			AccountInfoDto.select(cq, root);
			cq.where(searchForm.where(cb, root));
			return cq;
		};

		return accountRepo.search(searchFunc, countFunc, searchForm.getPage(), searchForm.getPageSize());
	}

}
