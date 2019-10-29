package com.ragnax.ventapago.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.ventapago.entidad.TipoStatusNegocio;

public interface TipoStatusNegocioRepository extends JpaRepository<TipoStatusNegocio, Integer> {
	
	@Query("select tsn from TipoStatusNegocio tsn where tsn.nombreTipoStatusNegocio = :nombreTipoStatusNegocio")
	Page<TipoStatusNegocio> findByNombreTipoStatusNegocio(String nombreTipoStatusNegocio, Pageable page);
}
