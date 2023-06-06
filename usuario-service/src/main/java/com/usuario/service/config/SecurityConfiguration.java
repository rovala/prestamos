package com.usuario.service.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		//A. DEFINIR AL RECURSO QUE QUEREMOS ACCEDER
		//====================================
		//Endpoint level authorization

	    // ---- Matcher
	    // 1. AnyRequest
	    // 2. RequestMatchers
	    // 3. RequestMatchers with HttpMethod

		//B. DEFINIR LAS REGLAS DE AUTORIZACION
		//=====================================
	    // ---- Authorization rule
	    // 1. PermitAll
	    // 2. DenyAll
	    // 3. Authenticated
	    // 4. HasRole
	    // 5. HasAuthority
	    // 6. Access (SpEL) - Spring Expression Language

		return http
				.httpBasic()
				.and().authorizeHttpRequests()
				.anyRequest().permitAll() // todos pueden acceder a todos los recursos
				//.requestMatchers("/test").hasAnyRole("USER","DBA")
				//.requestMatchers("/test2").hasRole("ADMIN")
				//.requestMatchers("/test1").permitAll()
				//.requestMatchers(HttpMethod.GET,"/test1").authenticated()
				//.requestMatchers(HttpMethod.POST,"/test").hasRole("DBA")
				.and().cors().configurationSource(corsConfigurationSource()).and().csrf().disable().build();
	}
	
	 @Bean
	 public CorsConfigurationSource corsConfigurationSource()
	 {
	     CorsConfiguration configuration = new CorsConfiguration();
	     configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // orígenes permitidos
	     configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE")); // métodos permitidos
	     configuration.setAllowedHeaders(Arrays.asList("*")); // cabeceras permitidas
	     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	     source.registerCorsConfiguration("/**", configuration);
	     return source;
	 }

}
