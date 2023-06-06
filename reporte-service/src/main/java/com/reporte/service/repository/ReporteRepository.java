package com.reporte.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.reporte.service.entity.PrestamoCliente;

@Repository
public interface ReporteRepository extends JpaRepository<PrestamoCliente, Long> {
	
	@Query(value="SELECT * FROM v_prest_prest_clientes",nativeQuery = true)
	List<PrestamoCliente> listFeeClient();
}
