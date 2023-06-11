package com.usuario.service.service;

import com.usuario.service.entity.User;
import com.usuario.service.exception.ErrorMessage;
import com.usuario.service.feignclients.ClientFeign;
import com.usuario.service.feignclients.CuotaFeign;
import com.usuario.service.feignclients.PrestamoFeign;
import com.usuario.service.feignclients.ReporteFeign;
import com.usuario.service.feignclients.UserFeignLogin;
import com.usuario.service.models.Client;
import com.usuario.service.models.Cuota;
import com.usuario.service.models.ParamsFee;
import com.usuario.service.models.Prestamo;
import com.usuario.service.models.PrestamoCliente;
import com.usuario.service.repository.CRUDUserRepository;
import com.usuario.service.repository.UserRepository;
import com.usuario.service.security.HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.sql.Timestamp;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CRUDUserRepository crudUserRepository;

    @Autowired
    private UserFeignLogin userFeignLogin;
    
    @Autowired
    private ClientFeign clientFeign;
    
    @Autowired
    private PrestamoFeign prestamoFeign;
    
    @Autowired
    private CuotaFeign cuotaFeign;
    
    @Autowired
    private ReporteFeign reporteFeign;
    
    //@Autowired
    //private RestTemplate restTemplate;

    /*public String loginService(User user)//USO DE RESTTEMPLATE
    {
        return restTemplate.postForObject("http://localhost:8001/usuario/login",user,String.class);
    }*/
    /**********************
    ******FEIGN USER*******
    ***********************/
    public String loginService(User user)
    {
        return userFeignLogin.loginUser(user);
    }
    /***********************
     ******FEIGN CLIENTE****
     ***********************/
    public ResponseEntity<List<Client>> getAllClients()
    {
        return  clientFeign.getListaClientes();
    }
    
    public ResponseEntity<?> deleteClient(String dni)
    {
    	return clientFeign.eliminarCliente(dni);
    }
    
    public ResponseEntity<?> insertClient(Client client)
    {
    	return clientFeign.insertarCliente(client);
    }
    
    public ResponseEntity<?> updateClient(Client client)
    {
    	return clientFeign.actualizarCliente(client);
    }
    /***********************
     *****FEIGN PRESTAMO****
     ***********************/
    public ResponseEntity<ErrorMessage> insertLoan(Prestamo prestamo){
    	return prestamoFeign.insertPrestamo(prestamo);
    }
    
    public ResponseEntity<ErrorMessage> deleteLoan(String id)
    {
    	return prestamoFeign.borrarPrestamo(id);
    }
    
    public ResponseEntity<ErrorMessage> updateLoan(Prestamo prestamo)
    {
    	return prestamoFeign.actualizaEstadoPrestamo(prestamo);
    }
    
    /***********************
     ******FEIGN CUOTA******
     ***********************/
    public ResponseEntity<ErrorMessage> insertFee(ParamsFee paramsFee){
    	return cuotaFeign.insertCuota(paramsFee);
    }
    
    public ResponseEntity<ErrorMessage> updateEstadoCuota(Cuota cuota)
    {
    	return cuotaFeign.actualizaEstadoCuota(cuota);
    }
    
     /*****************************
     ******Prestamo x cliente******
     *****************************/
    public ResponseEntity<List<PrestamoCliente>> listFeesClient()
    {
    	return reporteFeign.listaClientePrestamo();
    }
    
    public List<User> getAll()
    {
        return userRepository.getAllUser();
    }

    public void saveUser(User user)
    {
        HashPassword hashPassword=new HashPassword();
        hashPassword.setFinalKey(user.getClave());
        user.setClave(hashPassword.getFinalKey());
        userRepository.saveUser(user.getNombre(),user.getApellidos(),user.getEmail(),user.getDni(),user.getClave());
        return;
    }

    public Long deleteByDni(String dni)
    {
        return crudUserRepository.deleteByDni(dni);
    }

    public Integer updateUser(User user)
    {
        java.util.Date utilDate = new java.util.Date();
        Timestamp timestamp=new Timestamp(utilDate.getTime());
        user.setF_status(timestamp);
        return crudUserRepository.updateUser(user.getNombre(),user.getApellidos(),user.getEmail(),user.getStatus(),user.getF_status(),user.getId());
    }
}
