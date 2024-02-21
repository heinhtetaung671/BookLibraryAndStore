package com.jdc.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jdc.home.security.CustomLoginFailerHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@ComponentScan("com.jdc.home.security")
public class SecurityConfig {

	@Autowired
	private CustomLoginFailerHandler customLoginFailerHandler;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(req -> {
			req.requestMatchers("public/**", "/", "signUp", "resources/**", "publicImg/**", "login", "logout", "profileImg/**").permitAll();
			req.requestMatchers("admin/**").hasAuthority("ADMIN");
			req.requestMatchers("member/**").hasAnyAuthority("MEMBER","ADMIN");
			req.requestMatchers("customer/**").hasAnyAuthority("CUSTOMER","MEMBER");
			req.requestMatchers("sale/**").hasAnyAuthority("CUSTOMER", "MEMBER", "ADMIN");
			req.requestMatchers("profile/**").authenticated();
		});
		
		http.formLogin(form -> {
			form.defaultSuccessUrl("/profile");
			form.loginPage("/login");
			form.failureHandler(customLoginFailerHandler);
		});
		
		http.logout(form -> {
			
		});
		return http.build();
	}
		
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		var provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userDetailsService);
		provider.setHideUserNotFoundExceptions(false);
		return provider;
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration conf) throws Exception {
		return conf.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
