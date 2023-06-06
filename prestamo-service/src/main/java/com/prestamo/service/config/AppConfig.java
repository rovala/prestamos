package com.prestamo.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prestamo.service.exception.ErrorMessage;

@Configuration
public class AppConfig {
	@Bean
    public ErrorMessage errorMessage()
    {
        return ErrorMessage.builder().build();
    }
}
