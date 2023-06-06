package com.usuario.service.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.service.exception.ErrorMessage;
import com.usuario.service.models.Prestamo;

@FeignClient(name="prestamo-service", url="http://localhost:8084")
public interface PrestamoFeign {
	
	@PostMapping("/prestamo")
	public ResponseEntity<ErrorMessage> insertPrestamo(@RequestBody Prestamo prestamo);
	
	@DeleteMapping("/prestamo/delete/{id}")
	public ResponseEntity<ErrorMessage> borrarPrestamo(@PathVariable String id);

}
