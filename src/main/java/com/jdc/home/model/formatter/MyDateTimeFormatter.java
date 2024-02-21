package com.jdc.home.model.formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
@PropertySource("classpath:/application.properties")
public class MyDateTimeFormatter {
	
	@Value("${myFormat.dateTime}")
	private String pattern;
	
	private DateTimeFormatter formatter;
	
	@PostConstruct
	public void init() {
		formatter = DateTimeFormatter.ofPattern(pattern);
	}
	
	public String format(LocalDateTime dateTime) {
		
		if(dateTime == null) {
			return "-";
		}
		
		return formatter.format(dateTime);
	}
	
}
