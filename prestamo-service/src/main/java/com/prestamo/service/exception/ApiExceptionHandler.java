package com.prestamo.service.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@Autowired
	private ErrorMessage errorMessage;
	
	public ResponseEntity<ErrorMessage> runtimeExceptionHandler(HttpServletRequest request, RuntimeException exception)
	{
		errorMessage.setException(exception.getClass().getSimpleName());
		errorMessage.setMessage(exception.getMessage());
		errorMessage.setPath(request.getRequestURI());
		return ResponseEntity.ok(errorMessage);
	}

}
