package com.ragnax.ventapago.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ragnax.ventapago.entidad.AjusteCantidadTotal;
import com.ragnax.ventapago.entidad.Pago;

public interface AjusteCantidadTotalRepository extends JpaRepository<AjusteCantidadTotal, Integer> {
		
	
	AjusteCantidadTotal findByIdPagoAndIdTipoMonedaAndIdHistorialTipoCambioAndIdHistorialFeeComision(Pago idPago, Integer idTipoMoneda, Integer idHistorialTipoCambio,  Integer idHistorialFeeComision);
	
	List<AjusteCantidadTotal> findByIdPago(Pago idPago);
	

}
