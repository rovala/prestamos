package com.cuota.service.entity;

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
@Table(name="tbl_cuota")
@Entity
@AllArgsConstructor @NoArgsConstructor
public class Cuota {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date f_cuota;
	private Double monto;
	private Long id_prestamo;
	private Timestamp f_registro;
	private String status;
}
