package com.jdc.home.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UpdateProfileInfoService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
	
	
	public Authentication authenticate(String email, String password) {
	
		UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(email, password);
		
		var authenticationResult = authenticationManager.authenticate(token);
		
		return authenticationResult;
	}
	
	public void reAuthenticateAndLogin(String newEmail, String password, HttpServletRequest request, HttpServletResponse response) {
		
		// Authencate with new Email
		var authenticationResult = authenticate(newEmail, password);
		
		// create new securityContext with new authenticateResult
		var securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(authenticationResult);
		
		securityContextRepository.saveContext(securityContext, request, response);

	}

}
