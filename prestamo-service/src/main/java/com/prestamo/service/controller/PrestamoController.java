package com.prestamo.service.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.service.entity.Prestamo;
import com.prestamo.service.exception.ErrorMessage;
import com.prestamo.service.service.PrestamoService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/prestamo")
public class PrestamoController {
	
	@Autowired
	private PrestamoService prestamoService;
	
	@Autowired
	private ErrorMessage errorMessage;
	
	@PostMapping
	public ResponseEntity<ErrorMessage> insertPrestamo(@RequestBody Prestamo prestamo, HttpServletRequest request) throws ParseException
	{
		Long id_prestamo;
		String tempDateStart=prestamo.getClausulas().substring(0, 10);
		prestamo.setClausulas(prestamo.getClausulas().substring(10,prestamo.getClausulas().length()));
		id_prestamo=prestamoService.insertPrestamo(prestamo,(new SimpleDateFormat("yyyy-MM-dd")).parse(tempDateStart).getTime());
		errorMessage.setException("Exito");
		errorMessage.setMessage(id_prestamo.toString());
		errorMessage.setPath(request.getRequestURI());
		return ResponseEntity.ok(errorMessage);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ErrorMessage> borrarPrestamo(@PathVariable String id, HttpServletRequest request)
	{
		prestamoService.borrarPrestamo(Long.parseLong(id));
		errorMessage.setException("Exito");
		errorMessage.setMessage("Rollback a prestamo...no se pudo completar");
		errorMessage.setPath(request.getRequestURI());
		return ResponseEntity.ok(errorMessage);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ErrorMessage> actualizaEstadoPrestamo(@RequestBody Prestamo prestamo, HttpServletRequest request)
	{
		prestamoService.actualizaEstadoPrestamo(prestamo);
		errorMessage.setException("Exito");
		errorMessage.setMessage("Actualizacion de estado: Prestamo cancelado");
		errorMessage.setPath(request.getRequestURI());
		return ResponseEntity.ok(errorMessage);
	}
	
}
