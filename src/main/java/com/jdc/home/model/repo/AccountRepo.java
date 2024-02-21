package com.jdc.home.model.repo;

import java.util.List;
import java.util.Optional;

import com.jdc.home.model.CustomRepository;
import com.jdc.home.model.entity.Account;
import com.jdc.home.model.entity.Account.Authority;

public interface AccountRepo extends CustomRepository<Account, Integer>{

	Optional<Account> findAccountByEmail(String email);
	
	List<Account> findAccountByAuthority(Authority authority);
}
