package com.usuario.service.models;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Client {
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
