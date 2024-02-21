package com.jdc.home.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.home.model.entity.Account;
import com.jdc.home.model.input.CustomerSignUpForm;
import com.jdc.home.model.repo.AccountRepo;

@Service
@Transactional(readOnly = true)
public class CustomerAccountService {

	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public Account save(CustomerSignUpForm form) {
		
		if(form.getId() == 0) {
			var account = form.to();
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			return accountRepo.save(account);
		}
		
			var account = accountRepo.findById(form.getId()).orElseThrow();
			account.setName(form.getName());
			account.setPhone(form.getPhone());
			account.setEmail(form.getEmail());
			account.setPassword(passwordEncoder.encode(form.getPassword()));
		
		
		return account;
		
	}

}
