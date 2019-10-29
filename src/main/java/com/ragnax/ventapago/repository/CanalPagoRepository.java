package com.ragnax.ventapago.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.ventapago.entidad.CanalPago;

public interface CanalPagoRepository extends JpaRepository<CanalPago, Integer> {
	
	@Query("select cp from CanalPago cp where cp.nombreCanalPago = :nombreCanalPago")
	Page<CanalPago> findByNombreCanalPago(String nombreCanalPago, Pageable page);
}
