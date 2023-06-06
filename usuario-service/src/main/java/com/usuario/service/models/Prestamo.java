package com.usuario.service.models;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Prestamo {
	private Long id;
	private int monto; 
	private int interes;
	private int numcuotas;
	private int formapago;
	private String moneda;
	private Date f_inicio;
	private String clausulas;
	private Long id_client;
	private Timestamp f_registro;
}