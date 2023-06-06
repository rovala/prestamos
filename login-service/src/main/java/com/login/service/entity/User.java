package com.login.service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Table(name="tbl_user")
@Entity
@AllArgsConstructor @NoArgsConstructor
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String dni;
    private String clave;
    private Timestamp f_registro;
    private Timestamp f_status;
    private String status;//A=activo,X=eliminado,S=suspendido
}
