package com.cliente.service.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cliente.service.entity.Client;
import com.cliente.service.exception.ErrorMessage;
import com.cliente.service.service.ClientService;

import jakarta.servlet.http.HttpServletRequest;

//CRUD: C=insert, R=listado, U=update y D=delete 

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
@RequestMapping("/cliente")
public class ClientController {
	@Autowired
	private ClientService clientService;
	
	@Autowired
	ErrorMessage errorMessage;
	
	@Autowired
	HttpHeaders httpHeaders;
	
	@GetMapping
	public ResponseEntity<List<Client>> getListaClientes()
	{
		List<Client> listaClientes=clientService.getAll();
		if (listaClientes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok( listaClientes);
	}
	
	@DeleteMapping("/delete/{dni}")
	public ResponseEntity<?> eliminarCliente(@PathVariable(value="dni") String dni, HttpServletRequest request){
		if (clientService.deleteClient(dni)<=0)
		{
			errorMessage=ErrorMessage.builder().exception("Completado").message("No se encontro cliente con DNI para eliminar").path(request.getRequestURI()).build();
	        return new ResponseEntity(errorMessage,httpHeaders,HttpStatus.NOT_FOUND);
		};
        errorMessage=ErrorMessage.builder().exception("Exito").message("Exito en eliminacion de cliente").path(request.getRequestURI()).build();
        return new ResponseEntity(errorMessage,httpHeaders,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> insertarCliente(@RequestBody Client client, HttpServletRequest request){
		//throw new RuntimeException("LLegamos");
		clientService.insertClient(client);
		errorMessage=ErrorMessage.builder().exception("Exito").message("Exito en insertar cliente nuevo").path(request.getRequestURI()).build();
        return new ResponseEntity(errorMessage,httpHeaders,HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> actualizarCliente(@RequestBody Client client, HttpServletRequest request){
		if (clientService.actualizarCliente(client)<=0) {
			errorMessage=ErrorMessage.builder().exception("Completado").message("No se encontro cliente para actualizar").path(request.getRequestURI()).build();
	        return new ResponseEntity(errorMessage,httpHeaders,HttpStatus.NOT_FOUND);
		};
		errorMessage=ErrorMessage.builder().exception("Exito").message("Exito en actualizar cliente").path(request.getRequestURI()).build();
        return new ResponseEntity(errorMessage,httpHeaders,HttpStatus.OK);
	}
	
}
