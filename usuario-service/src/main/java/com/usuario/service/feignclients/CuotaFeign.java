package com.usuario.service.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.service.exception.ErrorMessage;
import com.usuario.service.models.Cuota;
import com.usuario.service.models.ParamsFee;

@FeignClient(name="cuota-service", url="http://localhost:8085")
public interface CuotaFeign {
	
	@PostMapping("/cuota")
	ResponseEntity<ErrorMessage> insertCuota(@RequestBody ParamsFee paramsFee);
	
	@PutMapping("/cuota/update")
	ResponseEntity<ErrorMessage> actualizaEstadoCuota(Cuota cuota);
}
