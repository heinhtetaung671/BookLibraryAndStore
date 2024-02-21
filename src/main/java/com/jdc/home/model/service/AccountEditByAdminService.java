package com.jdc.home.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.home.model.entity.Account;
import com.jdc.home.model.input.AccountEditByAdminForm;
import com.jdc.home.model.repo.AccountRepo;

@Service
@Transactional(readOnly = true)
public class AccountEditByAdminService {

	@Autowired
	private AccountRepo accountRepo;
	
	@Transactional
	public Account edit(AccountEditByAdminForm form) {
		var a = accountRepo.findById(form.getId()).orElseThrow();
		a.setAuthority(form.getAuthority());
		a.setBalance(form.getBalance());
		a.setPoint(form.getPoint());
		a.setActive(form.getActive());
		a.setLocked(form.getLocked());
		a.setStatusMessage(form.getStatusMessage());
		return a;
	}
	
}
