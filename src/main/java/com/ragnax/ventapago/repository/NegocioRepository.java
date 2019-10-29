package com.ragnax.ventapago.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.ventapago.entidad.Negocio;

public interface NegocioRepository extends JpaRepository<Negocio, Integer> {
	
	@Query("select n from Negocio n where n.codigoNegocio = :codigoNegocio")
	Page<Negocio> findByCodigoNegocio(String codigoNegocio, Pageable page);
	
	List<Negocio> findAllByIdPaisPortalAndFechaNegocioBetween(Integer idPaisPortal, Timestamp limiteInicialFechaNegocio, Timestamp limiteFinalFechaFechaNegocio);
	
	
}
