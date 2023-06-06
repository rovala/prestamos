package com.prestamo.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prestamo.service.entity.Prestamo;

import jakarta.transaction.Transactional;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

	//@Modifying(clearAutomatically = true) //forzar a que los cambios se reflejen en el entorno de JPA COMENTADO CUANDO EL SP DEVUELVE DATOS
    @Transactional
    @Query(value="CALL p_prest_insert_prestamo(:monto,:interes,:numcuotas,:formapago,:moneda,:f_inicio,:clausulas,:id_client, :id_prestamo)",nativeQuery = true)
    Long savePrestamo(int monto,int interes,int numcuotas,int formapago, String moneda,Long f_inicio, String clausulas, Long id_client, Long id_prestamo);
    	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="CALL p_prest_delete_prestamo(:id)",nativeQuery=true)
	void deletePrestamo(Long id);
	
	@Transactional
	void deleteById(Long id);
}
