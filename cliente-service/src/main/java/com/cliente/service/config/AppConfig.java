package com.cliente.service.config;

import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import com.cliente.service.exception.ErrorMessage;

@Configuration
public class AppConfig {
	
	@Bean
	public Logger logger() {
		return Logger.getLogger("logApiExceptionHandler");
	}
	
	@Bean
    public HttpHeaders httpHeaders()
    {
        return new HttpHeaders();
    }
	
	@Bean
	public ErrorMessage errorMessage() {
		return ErrorMessage.builder().build();
	}

}
