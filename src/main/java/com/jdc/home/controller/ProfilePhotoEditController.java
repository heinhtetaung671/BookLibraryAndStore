package com.jdc.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.home.model.service.ProfilePhotoEditService;

@Controller
@RequestMapping("profile/photo/edit")
public class ProfilePhotoEditController {

	@Autowired
	private ProfilePhotoEditService profilePhotoEditService;
	
	@PostMapping
	@PreAuthorize("!isAnonymous()")
	String edit(@RequestParam("profileImg") MultipartFile file) {
		
		profilePhotoEditService.editProfile(file);
		
		return "redirect:/profile";
	}
	
}
