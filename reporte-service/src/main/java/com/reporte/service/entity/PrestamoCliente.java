package com.reporte.service.entity;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Getter @Setter
@Entity
@Immutable
@Table(name="v_prest_prest_clientes")
public class PrestamoCliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",updatable=false,nullable=false)
	private Long id;
	private String nombre;
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
