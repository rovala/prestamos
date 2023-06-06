package com.cliente.service.repository;

import com.cliente.service.entity.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface CrudClientRepository extends CrudRepository<Client,Long>
{
    @Transactional
    Long deleteByDni(String dni);

    //SQL utilizando directamente JPA spring. No requerido en la BD
    @Transactional
    @Modifying
    @Query("update Client c set c.nombre=:nombre, c.direccion=:direccion, c.zona=:zona, c.distrito=:distrito, c.email=:email, c.telefono=:telefono, c.status=:status, c.f_status=:f_status, c.id_user=:id_user where c.id=:id")
    Integer updateClient(String nombre, String direccion, String zona, String distrito, String email, String telefono, String status, Timestamp f_status, Long id, Long id_user);
    
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="CALL p_prest_insert_client(:nombre,:dni,:direccion,:zona,:distrito,:email,:telefono,:id_user)",nativeQuery = true)
    void insertClient(String nombre,String dni,String direccion, String zona, String distrito, String email,String telefono,Long id_user);

}