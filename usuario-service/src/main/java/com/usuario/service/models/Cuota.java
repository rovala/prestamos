package com.usuario.service.models;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cuota {
	private Long id;
	private Date f_cuota;
	private Double monto;
	private Long id_prestamo;
	private Timestamp f_registro;
	private String status;
}
