package com.cliente.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cliente.service.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
