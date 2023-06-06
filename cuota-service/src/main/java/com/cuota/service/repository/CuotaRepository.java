package com.cuota.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cuota.service.entity.Cuota;
import jakarta.transaction.Transactional;

@Repository
public interface CuotaRepository extends JpaRepository<Cuota, Long> {
	@Modifying(clearAutomatically = true) //forzar a que los cambios se reflejen en el entorno de JPA
	@Transactional
	@Query(value="CALL p_prest_insert_cuota(:montoPrestamo,:interes,:numcuotas,:formapago,:f_inicio)", nativeQuery=true)
	void saveCuota(Double montoPrestamo, Double interes, int numcuotas, int formapago, Long f_inicio);
}
