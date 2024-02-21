package com.jdc.home.model;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class FarDateCalculator {

	public long calculate(LocalDateTime recentDate) {
		
		if(recentDate == null) {
			return 0;
		}
		
		var duration = Duration.between(recentDate, LocalDateTime.now());
		return duration.toDays();
	}
	
	public long calculate(LocalDateTime recentDate, LocalDateTime now) {
		
		if(recentDate == null && now == null) {
			return 0;
		}
		
		var duration = Duration.between(recentDate, now);
		return duration.toDays();
	}
	
}
