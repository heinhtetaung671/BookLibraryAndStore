package com.jdc.home.model.input;

import com.jdc.home.model.entity.Account;
import com.jdc.home.model.validation.LoginEmail;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileEditForm {

	@NotBlank(message = "please enter name.")
	private String name;
	private String phone;
	@LoginEmail
	@NotBlank(message = "please enter email.")
	private String email;
	
	public static ProfileEditForm from (Account a) {
		var p = new ProfileEditForm();
		p.setName(a.getName());
		p.setEmail(a.getEmail());
		p.setPhone(a.getPhone());
		
		return p;
	}
	
}
