package com.usuario.service.controller;

import com.usuario.service.entity.User;
import com.usuario.service.exception.ErrorMessage;
import com.usuario.service.models.Client;
import com.usuario.service.models.ParamsFee;
import com.usuario.service.models.Prestamo;
import com.usuario.service.models.PrestamoCliente;
import com.usuario.service.security.JWTsecurity;
import com.usuario.service.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@SuppressWarnings({ "unchecked","rawtypes","unused"})
public class UserController
{
    @Autowired
    private UserService userService;
    @Autowired
    private ErrorMessage errorMessage;
    @Autowired
    private HttpHeaders httpHeaders;
    @Autowired
    private JWTsecurity jwtSecurity;
    

    @GetMapping
    public ResponseEntity<List<User>> getListaUsuarios(@RequestHeader(value="Authorization") String token,@RequestHeader(value="Id") Long id)
    {
        String.valueOf(id).equals(jwtSecurity.getKey(token));
        List<User> listaUsuarios=userService.getAll();
        if (listaUsuarios.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaUsuarios);
    }
    
    /**************************
     ********FEIGN LOGIN*******
     **************************/
    @CircuitBreaker(name="loginCB",fallbackMethod = "fallBackLoginUser")
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user, HttpServletRequest request)
    {
        return ResponseEntity.ok(userService.loginService(user));
    }
    /******************************
     ********END FEIGN LOGIN*******
     ******************************/
        
    @PostMapping
    public ResponseEntity<?> saveUsuario(@RequestBody User user,HttpServletRequest request)
    {
        userService.saveUser(user);
        errorMessage.setException("Exito");
    	errorMessage.setMessage("Exito en creacion de usuario");
    	errorMessage.setPath(request.getRequestURI());
        return new ResponseEntity(errorMessage,httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{dni}")
    public ResponseEntity<?> deleteUsuario(@PathVariable String dni, @RequestHeader(value="Authorization") String token, @RequestHeader(value="Id") Long id, HttpServletRequest request)
    {
        String.valueOf(id).equals(jwtSecurity.getKey(token));
        if (userService.deleteByDni(dni)<=0)
        {
        	errorMessage.setException("Exito");
        	errorMessage.setMessage("No se encontro usuario con DNI para eliminar");
        	errorMessage.setPath(request.getRequestURI());
        	return new ResponseEntity(errorMessage,httpHeaders,HttpStatus.NOT_FOUND);
        };
        errorMessage=ErrorMessage.builder().exception("Exito").message("Exito en eliminacion de usuario").path(request.getRequestURI()).build();
        return new ResponseEntity(errorMessage,httpHeaders,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUsuario(@RequestBody User user, @RequestHeader(value="Authorization") String token, @RequestHeader(value="Id") Long id, HttpServletRequest request)
    {
        String.valueOf(id).equals(jwtSecurity.getKey(token));
        if (userService.updateUser(user)<=0)
        {
        	errorMessage.setException("Exito");
        	errorMessage.setMessage("No se encontro usuario para actualizar datos");
        	errorMessage.setPath(request.getRequestURI());
        	return new ResponseEntity(errorMessage,httpHeaders,HttpStatus.NOT_FOUND);
        }
        errorMessage=ErrorMessage.builder().exception("Exito").message("Exito en actualizar datos de usuario").path(request.getRequestURI()).build();
        return new ResponseEntity(errorMessage,httpHeaders,HttpStatus.OK);
    }
    
    
    /*****************************
     *********CLIENTE FEIGN*******
     *****************************/
    @CircuitBreaker(name="clienteCB",fallbackMethod = "fallBackGetClientes")
    @GetMapping("/cliente")
    public ResponseEntity<List<Client>> getListaClientes(@RequestHeader(value="Authorization") String token, @RequestHeader(value="Id") Long id,HttpServletRequest request){
    	String.valueOf(id).equals(jwtSecurity.getKey(token));
    	List<Client> listado=userService.getAllClients().getBody();
    	if (listado.isEmpty()) {
    		return ResponseEntity.noContent().build();
    	}
    	return ResponseEntity.ok(listado);
    }
    
    @CircuitBreaker(name="clientCB", fallbackMethod="fallBackDeleteCliente")
    @DeleteMapping("/cliente/delete/{dni}")
    public ResponseEntity<ErrorMessage> deleteCliente(@RequestHeader(value="Authorization") String token, @RequestHeader(value="Id") Long Id,@PathVariable String dni, HttpServletRequest request)
    {
    	String.valueOf(Id).equals(jwtSecurity.getKey(token));
    	userService.deleteClient(dni);
    	errorMessage.setException("Exito");
    	errorMessage.setMessage("Exito en eliminar cliente");
    	errorMessage.setPath(request.getRequestURI());
        return new ResponseEntity(errorMessage,httpHeaders,HttpStatus.OK);
    }

    @CircuitBreaker(name="clienteCB", fallbackMethod="fallBackInsertarCliente")
    @PostMapping("/cliente")
    public ResponseEntity<ErrorMessage> insertarCliente(@RequestBody Client client,@RequestHeader(value="Authorization") String token, @RequestHeader(value="Id") Long Id, HttpServletRequest request)
    {
    	String.valueOf(Id).equals(jwtSecurity.getKey(token));
    	userService.insertClient(client);
    	errorMessage.setException("Exito");
    	errorMessage.setMessage("Exito en insertar cliente nuevo");
    	errorMessage.setPath(request.getRequestURI());
        return new ResponseEntity(errorMessage,httpHeaders,HttpStatus.OK);
    }
    
    @CircuitBreaker(name="clientCB", fallbackMethod = "fallBackUpdateCliente")
    @PutMapping("/cliente/update")
    public ResponseEntity<ErrorMessage> updateCliente(@RequestBody Client client,@RequestHeader(value="Authorization") String token, @RequestHeader(value="Id") Long Id, HttpServletRequest request)
    {
    	String.valueOf(Id).equals(jwtSecurity.getKey(token));
    	userService.updateClient(client);
    	errorMessage.setException("Exito");
    	errorMessage.setMessage("Exito en actualizar datos de cliente");
    	errorMessage.setPath(request.getRequestURI());
        return new ResponseEntity(errorMessage,httpHeaders,HttpStatus.OK);
    }
    /*********************************
     *********END CLIENTE FEIGN*******
     *********************************/
    
    /*****************************
     ********PRESTAMO FEIGN*******
     *****************************/
    
    @PostMapping("/prestamo")
    public ResponseEntity<ErrorMessage> insertLoan(@RequestBody Prestamo prestamo,@RequestHeader(value="Authorization") String token, @RequestHeader(value="Id") Long Id, HttpServletRequest request)
    {
    	String.valueOf(Id).equals(jwtSecurity.getKey(token));
    	return userService.insertLoan(prestamo);
    }
    
    @DeleteMapping("/prestamo/delete/{idLoan}")
    public ResponseEntity<ErrorMessage> deleteLoan(@PathVariable String idLoan, @RequestHeader(value="Authorization") String token, @RequestHeader(value="Id") Long Id)
    {
    	String.valueOf(Id).equals(jwtSecurity.getKey(token));
    	return userService.deleteLoan(idLoan);
    }
    
    /*********************************
     ********END PRESTAMO FEIGN*******
     *********************************/
    
    /*****************************
     *********CUOTA FEIGN*********
     *****************************/
    @PostMapping("/cuota")
    public ResponseEntity<ErrorMessage> insertFee(@RequestBody ParamsFee paramsFee, @RequestHeader(value="Authorization") String token, @RequestHeader(value="Id") Long Id)
    {
    	String.valueOf(Id).equals(jwtSecurity.getKey(token));
    	return userService.insertFee(paramsFee);
    }
    /*********************************
     **********END CUOTA FEIGN********
     *********************************/
    
    
     /********************************************
     *********PRESTAMOS POR CLIENTE FEIGN*********
     ********************************************/
    @GetMapping("/reporte/prestamoscliente")
    public ResponseEntity<List<PrestamoCliente>> listFeesClient()
    {
    	return userService.listFeesClient();
    }
    
     /************************************************
     **********END PRESTAMOS POR CLIENTE FEIGN********
     ************************************************/
    
    
    /**************************
     *********RESILIENCE*******
     *************************/
    private ResponseEntity<?> fallBackLoginUser(User user,HttpServletRequest request, RuntimeException exception)
    {
    	errorMessage.setException("Servicio no disponible");
    	errorMessage.setMessage("Servicio de ingreso al aplicativo bloqueado: " + exception.getMessage());
    	errorMessage.setPath(request.getRequestURI());
    	return new ResponseEntity(errorMessage,httpHeaders,HttpStatus.OK);
    }
    
    private ResponseEntity<List<Client>> fallBackGetClientes(String token,Long Id,HttpServletRequest request, RuntimeException exception)
    {
    	errorMessage.setException("Servicio no disponible");
    	errorMessage.setMessage(exception.getMessage());
    	errorMessage.setPath(request.getRequestURI());
    	return new ResponseEntity(errorMessage,httpHeaders,HttpStatus.OK);
    }
    
    private ResponseEntity<ErrorMessage> fallBackDeleteCliente(String token, Long Id, String dni, HttpServletRequest request, RuntimeException exception)
    {
    	errorMessage.setException("Servicio no disponible");
    	errorMessage.setMessage("No se pudo eliminar cliente, razon: "+ exception.getMessage());
    	errorMessage.setPath(request.getRequestURI());
    	return new ResponseEntity(errorMessage,httpHeaders,HttpStatus.OK);
    }
    
    private ResponseEntity<ErrorMessage> fallBackInsertarCliente(Client client, String token, Long Id, HttpServletRequest request, RuntimeException exception)
    {
    	errorMessage.setException("Servicio no disponible");
    	errorMessage.setMessage("No se pudo agregar cliente, razon: "+ exception.getMessage());
    	errorMessage.setPath(request.getRequestURI());
    	return new ResponseEntity(errorMessage,httpHeaders,HttpStatus.OK);
    }
    
    private ResponseEntity<ErrorMessage> fallBackUpdateCliente(Client client, String token, Long Id, HttpServletRequest request, RuntimeException exception)
    {
    	errorMessage.setException("Servicio no disponible");
    	errorMessage.setMessage("No se pudo actualizar cliente, razon: "+ exception.getMessage());
    	errorMessage.setPath(request.getRequestURI());
    	return new ResponseEntity(errorMessage, httpHeaders, HttpStatus.OK);
    }
}
