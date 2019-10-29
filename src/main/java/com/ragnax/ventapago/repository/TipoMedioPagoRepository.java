package com.ragnax.ventapago.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.ventapago.entidad.TipoMedioPago;

public interface TipoMedioPagoRepository extends JpaRepository<TipoMedioPago, Integer> {
	
	@Query("select tmp from TipoMedioPago tmp where tmp.nombreTipoMedioPago = :nombreTipoMedioPago")
	Page<TipoMedioPago> findByNombreTipoMedioPago(String nombreTipoMedioPago, Pageable page);
}
