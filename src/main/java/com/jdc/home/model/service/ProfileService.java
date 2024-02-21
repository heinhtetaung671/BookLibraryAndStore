package com.jdc.home.model.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.home.model.entity.Account;
import com.jdc.home.model.entity.Account_;
import com.jdc.home.model.input.ChangePasswordForm;
import com.jdc.home.model.input.ProfileEditForm;
import com.jdc.home.model.output.ProfileInfoDto;
import com.jdc.home.model.repo.AccountRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class ProfileService {

	@Autowired
	private AccountRepo accountRepo;
	@Autowired	
	private PasswordEncoder passwordEncoder;
	
	public ProfileInfoDto searchProfile(Optional<Integer> accountId) {
		
		var email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Function<CriteriaBuilder, CriteriaQuery<ProfileInfoDto>> func = cb -> {
			
			var cq = cb.createQuery(ProfileInfoDto.class);
			var root = cq.from(Account.class);
			ProfileInfoDto.select(cq, root);
			
			if(accountId.isPresent()) {
				cq.where(cb.equal(root.get(Account_.id), accountId.get()));
			} else {
				cq.where(cb.equal(root.get(Account_.email), email));
			}
			
			return cq;
		};
		
		return accountRepo.find(func);
	}
	
	public ProfileEditForm searchForEdit() {
		var email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		return accountRepo.findAccountByEmail(email).map(ProfileEditForm::from).orElseThrow();
	}

	@Transactional
	public Account edit(ProfileEditForm form) {
		
		var email = SecurityContextHolder.getContext().getAuthentication().getName();
		var profile = accountRepo.findAccountByEmail(email).orElseThrow();
		
		profile.setName(form.getName());
		profile.setEmail(form.getEmail());
		profile.setPhone(form.getPhone());
		
		return profile;
	}
	
	
	@Transactional
	public Account chgPassword( ChangePasswordForm form) {
		
		var email = SecurityContextHolder.getContext().getAuthentication().getName();

		var account = accountRepo.findAccountByEmail(email).orElseThrow();
		
		account.setPassword(passwordEncoder.encode(form.getNewPassword()));
		
		return account;
	}
	
}
