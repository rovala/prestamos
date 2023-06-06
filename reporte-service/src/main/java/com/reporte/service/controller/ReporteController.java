package com.reporte.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reporte.service.entity.PrestamoCliente;
import com.reporte.service.service.ReporteService;

@RestController
@RequestMapping("/reporte")
public class ReporteController {
	
	@Autowired
	private ReporteService reporteService;
	
	@GetMapping("/prestamoscliente")
	public ResponseEntity<List<PrestamoCliente>> listaClientePrestamo()
	{
		return ResponseEntity.ok(reporteService.listaClientePrestamo());
	}

}
