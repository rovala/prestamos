package com.cuota.service.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ApiExceptionHandler {
	@Autowired
	private ErrorMessage errorMessage;

	@ExceptionHandler(value=HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorMessage> httpMessageNotReadableExceptionHandler(HttpServletRequest request, HttpMessageNotReadableException exception)
	{
		errorMessage.setException(exception.getClass().getSimpleName());
		errorMessage.setMessage(exception.getMessage()+": " + exception.getRootCause().getMessage());
		errorMessage.setPath(request.getRequestURI());
		return new ResponseEntity(errorMessage,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<ErrorMessage> dataIntegrityViolationException(HttpServletRequest request, DataIntegrityViolationException exception)
	{
		errorMessage.setException(exception.getClass().getSimpleName());
		errorMessage.setMessage(exception.getMessage()+": " + exception.getRootCause().getMessage());
		errorMessage.setPath(request.getRequestURI());
		return new ResponseEntity(errorMessage,HttpStatus.BAD_REQUEST);
	}
}
