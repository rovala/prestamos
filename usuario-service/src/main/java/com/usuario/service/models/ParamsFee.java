package com.usuario.service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ParamsFee {
	private double montoPrestamo;
	private double interes;
	private int numcuotas;
	private int formapago;
	private long f_inicio;
	private long id_prestamo;
}