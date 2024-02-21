package com.jdc.home.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.home.model.repo.AccountRepo;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	AccountRepo accountRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return accountRepo.findAccountByEmail(email).map(account -> {
			return User.builder()
			.username(account.getEmail())
			.password(account.getPassword())
			.authorities(account.getAuthority().name())
			.disabled(account.isLocked())
			.accountExpired(!account.isActive())
			.build();
		}).orElseThrow(() -> new UsernameNotFoundException("Not Found!"));
	}

}
