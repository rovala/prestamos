package com.usuario.service.exception;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.logging.Logger;

@RestControllerAdvice
public class ApiExceptionHandler
{
    @Autowired
    private Logger loggerApi;

    @Autowired
    private ErrorMessage errorMessage;

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorMessage> runtimeExceptionHandler(HttpServletRequest request, RuntimeException exception)
    {
        //loggerApi.info("Error de excepcion capturado (CLASS): " + exception.getClass().getSimpleName()+" (MESSAGE): "+ exception.getMessage()+" (URI): "+ request.getRequestURI());
        errorMessage.setException(exception.getClass().getSimpleName());
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(request.getRequestURI());
        return ResponseEntity.ok(errorMessage);
    }
      
}