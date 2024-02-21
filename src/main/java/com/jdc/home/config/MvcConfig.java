package com.jdc.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import com.jdc.home.model.formatter.CategoryFormatter;
import com.jdc.home.model.formatter.PromotionConverter;
import com.jdc.home.model.formatter.SinglePromotionConverter;

@Configuration
@EnableWebMvc
@EnableJpaAuditing
@ComponentScan("com.jdc.home.controller")
public class MvcConfig implements WebMvcConfigurer {

	@Autowired
	private CategoryFormatter categoryFormatter;
	@Autowired
	private PromotionConverter promotionConverter;
	@Autowired
	private SinglePromotionConverter singlePromotionConverter;
	
	@Bean
	SpringResourceTemplateResolver resolver() {
		var resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix("/views/");
		resolver.setSuffix(".html");
		resolver.setCacheable(false);
		return resolver;
	}
	
	@Bean
	SpringTemplateEngine engine(SpringResourceTemplateResolver resolver) {
		var engine = new SpringTemplateEngine();
		engine.addDialect(new SpringSecurityDialect());
		engine.setTemplateResolver(resolver);
		return engine;
	}
	
	@Bean
	ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine engine) {
		var thymeleafresolver = new ThymeleafViewResolver();
		thymeleafresolver.setTemplateEngine(engine);
		return thymeleafresolver;
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/profileImg/**").addResourceLocations("/profileImages/");
		registry.addResourceHandler("/publicImg/**").addResourceLocations("/publicImg/");
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(categoryFormatter);
		registry.addConverter(promotionConverter);
		registry.addConverter(singlePromotionConverter);
	}
}
