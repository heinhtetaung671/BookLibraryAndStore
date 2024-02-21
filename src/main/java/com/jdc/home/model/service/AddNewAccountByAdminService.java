package com.jdc.home.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.home.model.entity.Account;
import com.jdc.home.model.input.AddNewAccountByAdminForm;
import com.jdc.home.model.repo.AccountRepo;

@Service
@Transactional(readOnly = true)
public class AddNewAccountByAdminService {

	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional
	public Account addNewAccount(AddNewAccountByAdminForm form) {
	
		if(accountRepo.findAccountByEmail(form.getEmail()).isPresent())
			throw new IllegalArgumentException("Email is already exists.");
		
		var account = new Account();
		account.setName(form.getName());
		account.setEmail(form.getEmail());
		account.setPhone(form.getPhone());
		account.setPassword(encoder.encode(form.getPassword()));
		account.setAuthority(form.getAuthority());
		account.setBalance(form.getBalance());
		account.setPoint(form.getPoint());
		account.setActive(form.isActive());
		account.setLocked(form.isLocked());
		account.setStatusMessage(form.getStatusMessage());
		return accountRepo.save(account);
	
	}
	
}
