package com.ragnax.ventapago.repository;

public interface FactoryVentaPagoDAO {
	
	public AjusteCantidadTotalRepository getAjusteCantidadTotalRepository();
	public AutorizacionPagoRepository getAutorizacionPagoRepository();
	public CanalPagoRepository getCanalPagoRepository();
	public DetallePagoCuotasRepository getDetallePagoCuotasRepository();
	public DetallePagoRepository getDetallePagoRepository();
	public MedioPagoRepository getMedioPagoRepository();
	public NegocioRepository getNegocioRepository();
	public PagoRepository getPagoRepository();
	public StatusNegocioRepository getStatusNegocioRepository();
	public TipoMedioPagoRepository getTipoMedioPagoRepository();
	public TipoStatusNegocioRepository getTipoStatusNegocioRepository();
	
}
