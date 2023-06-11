package com.prestamo.service.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@Autowired
	private ErrorMessage errorMessage;
	
	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<ErrorMessage> runtimeExceptionHandler(HttpServletRequest request, RuntimeException exception)
	{
		errorMessage.setException(exception.getClass().getSimpleName());
		errorMessage.setMessage(exception.getMessage());
		errorMessage.setPath(request.getRequestURI());
		return ResponseEntity.ok(errorMessage);
	}
	
	@ExceptionHandler(value =InvalidDataAccessResourceUsageException.class)
	public ResponseEntity<ErrorMessage> invalidDataAccessResourceUsageException(HttpServletRequest request, InvalidDataAccessResourceUsageException exception)
	{
		errorMessage.setException(exception.getClass().getSimpleName());
		errorMessage.setMessage(exception.getMessage()+": " + exception.getRootCause().getMessage());
		errorMessage.setPath(request.getRequestURI());
		return ResponseEntity.ok(errorMessage);
	}
}
