package com.jdc.home.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jdc.home.model.entity.Account;
import com.jdc.home.model.entity.Account.Authority;
import com.jdc.home.model.repo.AccountRepo;

import jakarta.annotation.PostConstruct;

@Service
public class DataInserter {

	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private PasswordEncoder encoder;
	
	@PostConstruct
	public void insert() {
		if(accountRepo.findAccountByAuthority(Authority.ADMIN).isEmpty()) {
		
			var admin  = new Account();
			admin.setName("admin");
			admin.setEmail("admin@gmail.com");
			admin.setPassword(encoder.encode("admin"));
			admin.setAuthority(Authority.ADMIN);
			admin.setActive(true);
			admin.setLocked(false);
			accountRepo.save(admin);
		}
		
		if(accountRepo.findAccountByAuthority(Authority.ADMIN).isEmpty()) {
			var member  = new Account();
			member.setName("member");
			member.setEmail("member@gmail.com");
			member.setPassword(encoder.encode("member"));
			member.setAuthority(Authority.MEMBER);
			member.setActive(true);
			member.setLocked(false);
			accountRepo.save(member);
		}
		
		if(accountRepo.findAccountByAuthority(Authority.ADMIN).isEmpty()) {
			var customer  = new Account();
			customer.setName("customer");
			customer.setEmail("customer@gmail.com");
			customer.setPassword(encoder.encode("customer"));
			customer.setAuthority(Authority.CUSTOMER);
			customer.setActive(true);
			customer.setLocked(false);
			accountRepo.save(customer);
		}
	}
	
}
