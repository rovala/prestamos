package com.usuario.service.config;

import com.usuario.service.exception.ErrorMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Configuration
public class AppConfig {
    @Bean
    public ErrorMessage errorMessage()
    {
        return ErrorMessage.builder().build();
    }

    @Bean
    public HttpHeaders httpHeaders()
    {
        return new HttpHeaders();
    }

    @Bean
    public Logger loggerApi()
    {
        return Logger.getLogger("logApiExceptionHandler");
    }
    
    @Bean
    public RestTemplate restTemplate() {
    	return new RestTemplate();
    }
}