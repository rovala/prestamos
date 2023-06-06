package com.login.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	List<User> findByDni(String dni);

}
