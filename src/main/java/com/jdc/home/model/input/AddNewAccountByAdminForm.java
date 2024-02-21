package com.jdc.home.model.input;

import com.jdc.home.model.entity.Account.Authority;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNewAccountByAdminForm {

	@NotBlank(message = "please enter name.")
	private String name;
	@NotBlank(message = "please enter email.")
	private String email;
	@NotBlank(message = "please enter password")
	private String password;
	private String phone;
	@Min(value = 0, message = "please enter atleast 0.")
	private Integer balance = 0;
	@Min(value = 0, message = "please enter atleast 0.")
	private Integer point = 0;
	@NotNull(message = "please select authority.")
	private Authority authority;
	private boolean active;
	private boolean locked;
	private String statusMessage;
	
	
}
