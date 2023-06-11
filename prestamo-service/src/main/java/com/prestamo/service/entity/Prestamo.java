package com.prestamo.service.entity;

import java.sql.Date;
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
@Table(name="tbl_prestamo")
@Entity
@AllArgsConstructor @NoArgsConstructor
public class Prestamo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	private String estado;
}