package com.prestamo.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamo.service.entity.Prestamo;
import com.prestamo.service.repository.PrestamoRepository;

@Service
public class PrestamoService {
	
	@Autowired
	private PrestamoRepository prestamoRepository;
	
	public Long insertPrestamo(Prestamo prestamo,Long f_inicio)
	{
		return prestamoRepository.savePrestamo(prestamo.getMonto(),prestamo.getInteres(),prestamo.getNumcuotas(),prestamo.getFormapago(),prestamo.getMoneda(),f_inicio,prestamo.getClausulas(),prestamo.getId_client(), 0L);
	}
	
	public void borrarPrestamo(Long id)
	{
		prestamoRepository.deleteById(id);
	}
}
