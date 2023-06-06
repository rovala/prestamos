package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.service.models.Client;

@FeignClient(name="cliente-service",url="http://localhost:8083")
public interface ClientFeign {
	@GetMapping("/cliente")
	public ResponseEntity<List<Client>> getListaClientes();
	
	@DeleteMapping("cliente/delete/{dni}")
	public ResponseEntity<?> eliminarCliente(@PathVariable(value="dni") String dni);
	
	@PostMapping("/cliente")
	public ResponseEntity<?> insertarCliente(@RequestBody Client client);
	
	@PutMapping("/cliente")
	public ResponseEntity<?> actualizarCliente(@RequestBody Client client);
}
