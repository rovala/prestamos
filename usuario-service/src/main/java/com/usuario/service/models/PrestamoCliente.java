package com.usuario.service.models;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PrestamoCliente {
	private Long id;
	private String nombre;
	private String dni;
	private String direccion;
	private String telefono;
	private long idprestamo;
	private double montoprestamo;
	private int interes;
	private Timestamp f_registro;
	private int numcuotas;
	private int formapago;
	private long idcuota;
	private int numcuota;
	private double montocuota;
	private Date f_cuota;
}
