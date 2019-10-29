package com.ragnax.ventapago.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.ventapago.entidad.MedioPago;

public interface MedioPagoRepository extends JpaRepository<MedioPago, Integer> {
	
	@Query("select mp from MedioPago mp where mp.codigoMedioPago = :codigoMedioPago")
	Page<MedioPago> findByCodigoMedioPago(String codigoMedioPago, Pageable page);
	
}
