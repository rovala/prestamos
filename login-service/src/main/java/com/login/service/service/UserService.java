package com.login.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.service.entity.User;
import com.login.service.repository.UserRepository;
import com.login.service.security.HashPassword;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User searchUserByDni(String dni,String clave)
	{
		 User userFound=new User();
	     HashPassword hashPassword=new HashPassword();
	     List<User> listUserFound=userRepository.findByDni(dni);
	     if (!listUserFound.isEmpty()) {
	    	 if (hashPassword.verifyKey(listUserFound.get(0).getClave(), clave)) {
	             userFound = listUserFound.get(0);
	         }
	         else
	         {
	             userFound.setClave("Clave incorrecta");
	         }
	     }
	     else
	     {
	         userFound.setClave("DNI incorrecto");
	     }
	     return userFound;
	}
}
