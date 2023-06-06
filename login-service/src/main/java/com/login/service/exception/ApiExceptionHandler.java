package com.login.service.exception;

import org.springframework.beans.factory.annotation.Autowired;
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
		 errorMessage=ErrorMessage.builder().exception(exception.getClass().getSimpleName()).message(exception.getMessage()).path(request.getRequestURI()).build();
	     return ResponseEntity.ok(errorMessage);
	 }

}
