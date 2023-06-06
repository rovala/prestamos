package com.reporte.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.reporte.service.exception.ErrorMessage;

@Configuration
public class AppConfig {
	
	@Bean
	public ErrorMessage errorMessage() {
		return ErrorMessage.builder().build();
	}

}
