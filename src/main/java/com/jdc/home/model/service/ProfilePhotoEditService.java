package com.jdc.home.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.home.model.repo.AccountRepo;

@Service
public class ProfilePhotoEditService {

	@Autowired
	private AccountRepo accountRepo;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

	@Transactional
	public void editProfile(MultipartFile file) {

		var fileName = savePhoto(file);

		var email = SecurityContextHolder.getContext().getAuthentication().getName();

		var profile = accountRepo.findAccountByEmail(email).orElseThrow();

		profile.setProfileImage(fileName);
	}

	public String savePhoto(MultipartFile file) {

		Path path = Path.of("src/main/webapp/profileImages");

		path.getFileName();

		var fileName = getFileName(file);
		var location = path.resolve(fileName);

		try {
			Files.copy(file.getInputStream(), location);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileName;
	}

	private String getFileName(MultipartFile file) {
		var time = LocalDateTime.now().format(formatter);
		var email = SecurityContextHolder.getContext().getAuthentication().getName().trim();
		var prefix = "PROFILE";
		var suffix = getSuffix(file.getOriginalFilename());

		return "%s_%s_%s.%s".formatted(prefix, email, time, suffix);
	}

	private String getSuffix(String name) {

		var arr = name.split("\\.");

		if (arr.length > 0)
			return arr[arr.length - 1];

		return "";
	}

}
