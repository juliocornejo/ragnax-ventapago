package com.ragnax.ventapago.repository;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Repository;

@Repository
public class FactoryVentaPagoDAOImpl implements FactoryVentaPagoDAO {

	@Autowired
	private AjusteCantidadTotalRepository ajusteCantidadTotalRepository;	
	
	public AjusteCantidadTotalRepository getAjusteCantidadTotalRepository() {
		return ajusteCantidadTotalRepository;
	}
	
	@Autowired
	private AutorizacionPagoRepository autorizacionPagoRepository;	
	
	public AutorizacionPagoRepository getAutorizacionPagoRepository() {
		return autorizacionPagoRepository;
	}
	
	@Autowired
	private CanalPagoRepository canalPagoRepository;	
	
	public CanalPagoRepository getCanalPagoRepository() {
		return canalPagoRepository;
	}
	
	@Autowired
	private DetallePagoRepository detallePagoRepository;	
	
	public DetallePagoRepository getDetallePagoRepository() {
		return detallePagoRepository;
	}
	
	@Autowired
	private DetallePagoCuotasRepository detallePagoCuotasRepository;	
	
	public DetallePagoCuotasRepository getDetallePagoCuotasRepository() {
		return detallePagoCuotasRepository;
	}

	@Autowired
	private MedioPagoRepository medioPagoRepository;	
	
	public MedioPagoRepository getMedioPagoRepository() {
		return medioPagoRepository;
	}
	
	@Autowired
	private NegocioRepository negocioRepository;	
	
	public NegocioRepository getNegocioRepository() {
		return negocioRepository;
	}
	
	@Autowired
	private PagoRepository pagoRepository;	
	
	public PagoRepository getPagoRepository() {
		return pagoRepository;
	}
	
	@Autowired
	private StatusNegocioRepository statusNegocioRepository;	
	
	public StatusNegocioRepository getStatusNegocioRepository() {
		return statusNegocioRepository;
	}
	
	@Autowired
	private TipoMedioPagoRepository tipoMedioPagoRepository;	
	
	public TipoMedioPagoRepository getTipoMedioPagoRepository() {
		return tipoMedioPagoRepository;
	}

	@Autowired
	private TipoStatusNegocioRepository tipoStatusNegocioRepository;	
	
	public TipoStatusNegocioRepository getTipoStatusNegocioRepository() {
		return tipoStatusNegocioRepository;
	}
	
}
