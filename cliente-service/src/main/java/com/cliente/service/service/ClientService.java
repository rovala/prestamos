package com.cliente.service.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cliente.service.entity.Client;
import com.cliente.service.repository.ClientRepository;
import com.cliente.service.repository.CrudClientRepository;

@Service
public class ClientService {
	@Autowired
	private CrudClientRepository crudClientRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	public List<Client> getAll(){
		return clientRepository.findAll();
	}
	
	public Long deleteClient(String dni) {
		return crudClientRepository.deleteByDni(dni);
	}
	
	public void insertClient(Client client) {
		crudClientRepository.insertClient(client.getNombre(),client.getDni(),client.getDireccion(),client.getZona(),client.getDistrito(),client.getEmail(),client.getTelefono(),client.getId_user());

	}
	
	public Integer actualizarCliente(Client client) {
		java.util.Date utilDate = new java.util.Date();
        Timestamp timestamp=new Timestamp(utilDate.getTime());
        client.setF_status(timestamp);
		return crudClientRepository.updateClient(client.getNombre(), client.getDireccion(), client.getZona(), client.getDistrito(),client.getEmail(),client.getTelefono(),client.getStatus(),client.getF_status(),client.getId(),client.getId_user());
	}

}

