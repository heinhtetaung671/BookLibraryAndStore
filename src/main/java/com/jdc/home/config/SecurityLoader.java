package com.jdc.home.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

import jakarta.servlet.ServletContext;

public class SecurityLoader extends AbstractSecurityWebApplicationInitializer{

	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		// TODO Auto-generated method stub
		insertFilters(servletContext, new MultipartFilter());
	}
	
}
