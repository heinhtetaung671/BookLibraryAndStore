package com.jdc.home.security;

import java.io.IOException;

import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLoginFailerHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		var url = new StringBuffer("/login?error=true&&errorMsg=");
		
		if(exception instanceof UsernameNotFoundException e) {
			url.append("Please Check Your Username.");
		} else	if(exception instanceof BadCredentialsException e) {
			url.append("Please Check Your Password.");
		} else if(exception instanceof AccountStatusException e) {
			url.append(e.getMessage());
		} else {
			exception.printStackTrace();
		}
		
		getRedirectStrategy().sendRedirect(request, response, url.toString());
		
	}
	
}
