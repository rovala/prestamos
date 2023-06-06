package com.usuario.service.repository;

import com.usuario.service.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface CRUDUserRepository extends CrudRepository<User,Long>
{
    @Transactional
    Long deleteByDni(String dni);

    //SQL utilizando directamente JPA spring. No requerido en la BD
    @Transactional
    @Modifying
    @Query("update User u set u.nombre=:nombre, u.apellidos=:apellidos, u.email=:email, u.status=:status, u.f_status=:f_status where u.id=:id")
    Integer updateUser(String nombre, String apellidos, String email, String status, Timestamp f_status, Long id);
}
