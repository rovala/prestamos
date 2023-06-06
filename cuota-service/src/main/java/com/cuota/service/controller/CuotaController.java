package com.cuota.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuota.service.entity.ParamsFee;
import com.cuota.service.exception.ErrorMessage;
import com.cuota.service.service.CuotaService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cuota")
public class CuotaController {

	@Autowired
	private CuotaService cuotaService;
	
	@Autowired
	private ErrorMessage errorMessage;
	
	@PostMapping
	public ResponseEntity<ErrorMessage> insertCuota(@RequestBody ParamsFee paramsFee, HttpServletRequest request)
	{
		cuotaService.insertCuota(paramsFee);
		errorMessage.setException("Exito");
		errorMessage.setMessage("Cuotas de prestamo registradas");
		errorMessage.setPath(request.getRequestURI());
		return ResponseEntity.ok(errorMessage);
	}
}
