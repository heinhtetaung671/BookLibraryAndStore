package com.jdc.home.model.input;

import com.jdc.home.model.entity.Account;
import com.jdc.home.model.entity.Account.Authority;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountEditByAdminForm {

	private int id;
	
	private final String name;
	private final String email;
	private final String phone;
	
	@NotNull(message = "please enter authority.")
	private Authority authority;
	@Min(value = 0, message = "please enter valid value.")
	private Integer balance;
	@Min(value = 0, message = "please enter valid value.")
	private Float point;
	private Boolean active;
	private Boolean locked;
	private String statusMessage;
	
	public AccountEditByAdminForm(String name, String email, String phone) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	public static AccountEditByAdminForm from(Account account) {
		var dto = new AccountEditByAdminForm(account.getName(), account.getEmail(), account.getPhone());
		dto.setId(account.getId());
		dto.setAuthority(account.getAuthority());
		dto.setBalance(account.getBalance());
		dto.setPoint(account.getPoint());
		dto.setActive(account.isActive());
		dto.setLocked(account.isLocked());
		dto.setStatusMessage(account.getStatusMessage());
		return dto;
	}
	

	
	
}
