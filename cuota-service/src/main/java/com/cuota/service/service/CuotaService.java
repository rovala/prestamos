package com.cuota.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuota.service.entity.Cuota;
import com.cuota.service.entity.ParamsFee;
import com.cuota.service.repository.CuotaRepository;

@Service
public class CuotaService {
	@Autowired
	private CuotaRepository cuotaRepository;
	
	public void insertCuota(ParamsFee paramsFee)
	{
		cuotaRepository.saveCuota(	paramsFee.getMontoPrestamo(),
									paramsFee.getInteres(),
									paramsFee.getNumcuotas(),
									paramsFee.getFormapago(),
									paramsFee.getF_inicio());
	}
	
	public int actualizaEstado(Cuota cuota)
	{
		return cuotaRepository.updateCuota(cuota.getStatus(), cuota.getId());
	}
}
