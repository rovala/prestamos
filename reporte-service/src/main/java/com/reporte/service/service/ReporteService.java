package com.reporte.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reporte.service.entity.PrestamoCliente;
import com.reporte.service.repository.ReporteRepository;

@Service
public class ReporteService {

	@Autowired
	private ReporteRepository reporteRepository;
	
	public List<PrestamoCliente> listaClientePrestamo()
	{
		return reporteRepository.listFeeClient();
	}
}
