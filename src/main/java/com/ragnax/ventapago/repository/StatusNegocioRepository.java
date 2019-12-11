package com.ragnax.ventapago.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.ventapago.entidad.Negocio;
import com.ragnax.ventapago.entidad.StatusNegocio;

public interface StatusNegocioRepository extends JpaRepository<StatusNegocio, Integer> {
	
	List<StatusNegocio> findByIdNegocio(Negocio idNegocio);
	
	@Query("select sn from StatusNegocio sn where sn.idNegocio = :idNegocio")
	Page<StatusNegocio> findByIdNegocio(Negocio idNegocio, Pageable page);
	
}
