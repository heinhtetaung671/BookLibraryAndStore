package com.jdc.home.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.home.model.Pagination;
import com.jdc.home.model.input.ChangePasswordForm;
import com.jdc.home.model.input.DtoForPagination;
import com.jdc.home.model.input.ProfileEditForm;
import com.jdc.home.model.output.ProfileInfoDto;
import com.jdc.home.model.output.SaleInfoDto;
import com.jdc.home.model.service.ProfileService;
import com.jdc.home.model.service.SaleService;
import com.jdc.home.model.service.UpdateProfileInfoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("profile")
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	@Autowired
	private SaleService saleService;
	@Autowired
	private UpdateProfileInfoService updateProfileInfoService;

	@GetMapping
	@PreAuthorize("!isAnonymous()")
	String search(ModelMap model) {
		return "profile-info";
	}

	@PostMapping("chg/pass")
	@PreAuthorize("!isAnonymous()")
	String chgPass(@ModelAttribute("chgPassForm") @Validated ChangePasswordForm chgPassForm, BindingResult result,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		if (result.hasErrors()) {
			model.put("chgPassError", true);
			return "profile-info";
		}
		
		if (!chgPassForm.getNewPassword().equals(chgPassForm.getConfirmPassword())) {
			model.put("chgPassError", true);
			result.rejectValue("confirmPassword", "confirmPassword", "password not match.");
			return "profile-info";
		}

		var email = SecurityContextHolder.getContext().getAuthentication().getName();

		try {
			// check entered old password is correct or not.
			updateProfileInfoService.authenticate(email, chgPassForm.getOldPassword());

		} catch (BadCredentialsException e) {
			model.put("chgPassError", true);
			result.rejectValue("oldPassword", "oldPassword", "Incorrect Password!");
			return "profile-info";
		}

		// Change Password
		profileService.chgPassword(chgPassForm);

		// do programatic login.
		updateProfileInfoService.reAuthenticateAndLogin(email, chgPassForm.getNewPassword(), request, response);

		return "redirect:/profile";
	}

	@PostMapping("edit")
	@PreAuthorize("!isAnonymous()")
	String edit(@ModelAttribute("form") @Validated ProfileEditForm form, BindingResult result,
			@RequestParam("password") final String password, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		if (result.hasErrors() || !StringUtils.hasLength(password)) {
			model.put("passwordError", true);
			model.put("passwordErrorMsg", "Enter Password!");
			model.put("error", true);
			return "profile-info";
		}

		var oldEmail = SecurityContextHolder.getContext().getAuthentication().getName();

		try {
			// check entered password is correct or not.
			updateProfileInfoService.authenticate(oldEmail, password);

		} catch (BadCredentialsException e) {
			model.put("error", true);
			model.put("passwordError", true);
			model.put("passwordErrorMsg", "Incorrect Password!");
			return "profile-info";

		}

		// Edit Profile Info
		var account = profileService.edit(form);

		var newEmail = account.getEmail();

		// If user changed email, reAuthenticate with new email and do programmatice
		// login
		if (!newEmail.equals(oldEmail)) {
			updateProfileInfoService.reAuthenticateAndLogin(newEmail, password, request, response);
		}

		return "redirect:/profile";
	}

	@ModelAttribute("chgPassForm")
	@PreAuthorize("!isAnonymous()")
	ChangePasswordForm chgPassForm() {
		return new ChangePasswordForm();
	}

	@ModelAttribute("profile")
	@PreAuthorize("!isAnonymous()")
	ProfileInfoDto profile(@RequestParam(name = "accountId") Optional<Integer> id) {
		return profileService.searchProfile(id);
	}

	@ModelAttribute("saleList")
	@PreAuthorize("!isAnonymous()")
	List<SaleInfoDto> saleList(@RequestParam(name = "accountId") Optional<Integer> id, DtoForPagination dtoForPagination, ModelMap model) {
		
		var pageResult = saleService.search(id, dtoForPagination);
		model.put("pagination", new Pagination(pageResult));
		
		return pageResult.getContent();
	}

	@ModelAttribute("form")
	@PreAuthorize("!isAnonymous()")
	ProfileEditForm form() {
		return profileService.searchForEdit();
	}

}
