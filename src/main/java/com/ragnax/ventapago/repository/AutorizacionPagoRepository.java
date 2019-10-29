package com.ragnax.ventapago.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.ventapago.entidad.AutorizacionPago;
import com.ragnax.ventapago.entidad.Pago;

public interface AutorizacionPagoRepository extends JpaRepository<AutorizacionPago, Integer> {
	
	@Query("select ap from AutorizacionPago ap where ap.idPago = :idPago")
	Page<AutorizacionPago> findByIdPago(Pago idPago, Pageable page);
}
