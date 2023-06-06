package com.cliente.service.entity;


import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name="tbl_client")
@Entity
@AllArgsConstructor @NoArgsConstructor
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nombre;
	private String dni;
	private String direccion;
	private String zona;
	private String distrito;
	private String email;
	private String telefono;
	private Long id_user;
	private Timestamp f_registro;
    private Timestamp f_status;
    private String status;

}
