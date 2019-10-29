package com.ragnax.ventapago.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.ventapago.entidad.DetallePago;
import com.ragnax.ventapago.entidad.MedioPago;
import com.ragnax.ventapago.entidad.Pago;

public interface DetallePagoRepository extends JpaRepository<DetallePago, Integer> {
	
	@Query("select dp from DetallePago dp where dp.codigoDetallePago = :codigoDetallePago")
	Page<DetallePago> findByFkCodigoDetallePago(String codigoDetallePago, Pageable page);
	
	@Query("select dp from DetallePago dp where dp.idPago = :idPago")
	Page<DetallePago> findByIdPago(Pago idPago, Pageable page);
	
	DetallePago findByIdPagoAndIdMedioPago(Pago idPago, MedioPago idMedioPago);
}
