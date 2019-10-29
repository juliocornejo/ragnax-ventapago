package com.ragnax.ventapago.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.ventapago.entidad.Negocio;
import com.ragnax.ventapago.entidad.Pago;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
	

	
	@Query("select p from Pago p where p.idNegocio = :idNegocio")
	Page<Pago> findByIdNegocio(Negocio idNegocio, Pageable page);
	
	
	List<Pago> findByIdNegocioIn(List<Negocio> listaNegocio);
	
}
