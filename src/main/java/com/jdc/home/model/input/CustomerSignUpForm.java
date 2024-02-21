package com.jdc.home.model.input;

import com.jdc.home.model.entity.Account;
import com.jdc.home.model.entity.Account.Authority;
import com.jdc.home.model.validation.LoginEmail;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSignUpForm {

	private int id;
	@LoginEmail
	@NotBlank(message = "please enter email.")
	private String email;
	@NotBlank(message = "please enter name.")
	private String name;
	@NotBlank(message = "please enter password.")
	private String password;
	private String phone;
	
	public Account to() {
		var a = new Account();
		a.setId(id);
		a.setEmail(email);
		a.setName(name);
		a.setPassword(password);
		a.setPhone(phone);
		a.setAuthority(Authority.CUSTOMER);
		a.setActive(true);
		return a;
	}
	
	public static CustomerSignUpForm from(Account a) {
		var form = new CustomerSignUpForm();
		form.setId(a.getId());
		form.setEmail(a.getEmail());
		form.setName(a.getName());
		form.setPassword(a.getPassword());
		form.setPhone(a.getPhone());
		return form;
	}
	
}
