package com.jdc.home.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordForm {

	@NotBlank(message = "please enter old password.")
	private String oldPassword;
	@NotBlank(message = "please enter new password.")
	private String newPassword;
	@NotBlank(message = "please enter confirm password.")
	private String confirmPassword;
	
}
