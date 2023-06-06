package com.login.service.controller;

import com.google.gson.Gson;
import com.login.service.service.UserService;
import com.login.service.entity.User;
import com.login.service.security.JWTsecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//LLAMADAS A ERRORES PERSONALIZADOS: throw new RuntimeException("Se requiere información para búsqueda");

//@CrossOrigin(origins="*",methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.POST})
@RestController
//@RequestMapping("/usuario")
@RequestMapping("/login")
public class UserController
{
    @Autowired
    private UserService userService;
    @Autowired
    private JWTsecurity jwtSecurity;

    @PostMapping
    public ResponseEntity<String> loginUser(@RequestBody User usuario)
    {
        String token="";
        Gson gson = new Gson();
        User userFound=userService.searchUserByDni(usuario.getDni(), usuario.getClave());
        if (userFound.getDni()!=null)
        {
            token=jwtSecurity.create(String.valueOf(userFound.getId()), userFound.getDni());
        }
        String userJson=gson.toJson(userFound);
        return ResponseEntity.ok(token.length()==0?userJson:userJson.substring(0,userJson.length()-1)+",\"token\": \""+token+"\"}");//Si la clave o dni no es correcta userFound seteado y devuelto con valor "incorrecto"
    }
}
