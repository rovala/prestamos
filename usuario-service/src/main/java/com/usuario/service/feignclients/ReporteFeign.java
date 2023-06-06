package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.usuario.service.models.PrestamoCliente;

@FeignClient(name="reporte-service",url="http://localhost:8086")
public interface ReporteFeign {
	@GetMapping("/reporte/prestamoscliente")
	ResponseEntity<List<PrestamoCliente>> listaClientePrestamo();
}
