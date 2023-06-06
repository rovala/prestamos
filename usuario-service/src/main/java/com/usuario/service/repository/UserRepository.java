package com.usuario.service.repository;

import com.usuario.service.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Long>
{
    @Query(value="SELECT * FROM v_prest_list_user",nativeQuery = true)
    List<User> getAllUser();

    @Modifying(clearAutomatically = true) //forzar a que los cambios se reflejen en el entorno de JPA
    @Transactional
    @Query(value="CALL p_prest_insert_user(:nombre,:apellidos,:email,:dni,:clave)",nativeQuery = true)
    void saveUser(String nombre,String apellidos,String email,String dni,String clave);
    //@Query(value="SELECT * FROM f_prest_list_user_custom(:regexp_search)",nativeQuery = true)
    //List<User> findAllUserCriteria(String regexp_search);

}
