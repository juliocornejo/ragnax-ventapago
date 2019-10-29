package com.ragnax.ventapago.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.ventapago.entidad.DetallePagoCuotas;
import com.ragnax.ventapago.entidad.DetallePago;

public interface DetallePagoCuotasRepository extends JpaRepository<DetallePagoCuotas, Integer> {
	
	@Query("select dpc from DetallePagoCuotas dpc where dpc.idDetallePago = :idDetallePago")
	Page<DetallePagoCuotas> findByIdDetallePago(DetallePago idDetallePago, Pageable page);
}
