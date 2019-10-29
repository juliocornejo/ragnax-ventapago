package com.ragnax.ventapago.servicio;

import com.ragnax.ventapago.controller.response.VentaPago;
import com.ragnax.ventapago.entidad.AjusteCantidadTotal;
import com.ragnax.ventapago.entidad.CanalPago;
import com.ragnax.ventapago.entidad.DetallePagoCuotas;
import com.ragnax.ventapago.entidad.DetallePago;
import com.ragnax.ventapago.entidad.MedioPago;
import com.ragnax.ventapago.entidad.Negocio;
import com.ragnax.ventapago.entidad.Pago;
import com.ragnax.ventapago.entidad.StatusNegocio;
import com.ragnax.ventapago.entidad.TipoMedioPago;
import com.ragnax.ventapago.entidad.TipoStatusNegocio;
import com.ragnax.ventapago.exception.LogicaImplException;

public interface FactoryVentaPagoService {
	/******Se Puede buscar por el Id****/
	public VentaPago crearCanalPago(CanalPago objCanalPago) throws LogicaImplException;
	public VentaPago actualizarCanalPago(Integer id, CanalPago objCanalPago) throws LogicaImplException;
	public VentaPago buscarCanalPago(CanalPago objCanalPago) throws LogicaImplException;
	public VentaPago listarTodoCanalPago() throws LogicaImplException;
	
	public VentaPago crearTipoMedioPago(TipoMedioPago objTipoMedioPago) throws LogicaImplException;
	public VentaPago actualizarTipoMedioPago(Integer id, TipoMedioPago objTipoMedioPago) throws LogicaImplException;
	public VentaPago buscarTipoMedioPago(TipoMedioPago objTipoMedioPago) throws LogicaImplException;
	public VentaPago listarTodoTipoMedioPago() throws LogicaImplException;
	
	public VentaPago crearTipoStatusNegocio(TipoStatusNegocio objTipoStatusNegocio) throws LogicaImplException;
	public VentaPago actualizarTipoStatusNegocio(Integer id, TipoStatusNegocio objTipoStatusNegocio) throws LogicaImplException;
	public VentaPago buscarTipoStatusNegocio(TipoStatusNegocio objTipoStatusNegocio) throws LogicaImplException;
	public VentaPago listarTodoTipoStatusNegocio() throws LogicaImplException;
	/**************************/
	/******Se Puede buscar solo por el codigo****/
	public VentaPago crearMedioPago(MedioPago objMedioPago) throws LogicaImplException;
	public VentaPago actualizarMedioPago(String codigoMedioPago, MedioPago objMedioPago) throws LogicaImplException;
	public VentaPago buscarMedioPagoxCodigo(MedioPago objMedioPago) throws LogicaImplException;
	public VentaPago listarTodoMedioPago() throws LogicaImplException;
	
	/****** VENTA PAGO****/
	public VentaPago generarCodigoNegocio(Negocio objNegocio) throws LogicaImplException;
	
	public VentaPago crearNegocio(Negocio objNegocio) throws LogicaImplException;
	public VentaPago buscarNegocioxCodigo(Negocio objNegocio) throws LogicaImplException;
	public VentaPago listarTodoNegocio() throws LogicaImplException;
	public VentaPago listarNegocioxPaisPortalEntreFechas(Integer fkIdPaisPortal, String sFechaInicial, String sFechaFinal) throws LogicaImplException;
	
	public VentaPago crearStatusNegocio(StatusNegocio objStatusNegocio) throws LogicaImplException;
	public VentaPago listarStatusNegocioxNegocio(StatusNegocio objStatusNegocio) throws LogicaImplException;
	
	public VentaPago crearPago(Pago objPago) throws LogicaImplException;
	public VentaPago actualizarEstadoPago(Pago objPago) throws LogicaImplException;
	public VentaPago buscarPagoxCodigoNegocio(Pago objPago) throws LogicaImplException;
	public VentaPago listarTodoPago() throws LogicaImplException;
	public VentaPago listarPagoEntreFecha(Integer fkIdPaisPortal, String sFechaInicial, String sFechaFinal) throws LogicaImplException;
	
	public VentaPago crearAjusteCantidadTotal(AjusteCantidadTotal objAjusteCantidadTotal) throws LogicaImplException;
	public VentaPago buscarAjusteCantidadTotalxPagoxTipoMonedaXHTipoCambioxHFeeComision(AjusteCantidadTotal objAjusteCantidadTotal) throws LogicaImplException;
	public VentaPago listarTodoAjusteCantidadTotal() throws LogicaImplException;
	public VentaPago listarTodoAjusteCantidadTotalxPago(AjusteCantidadTotal objAjusteCantidadTotal) throws LogicaImplException;
	
	public VentaPago generarCodigoDetallePago(DetallePago objDetallePago) throws LogicaImplException;
	public VentaPago crearDetallePago(DetallePago objDetallePago) throws LogicaImplException;
//	public VentaPago buscarDetallePagoxPagoxMedioPago(DetallePago objDetallePago) throws LogicaImplException;
	public VentaPago buscarDetallePagoxCodigo(DetallePago objDetallePago) throws LogicaImplException;
	public VentaPago listarTodoDetallePago() throws LogicaImplException;
	public VentaPago listarDetallePagoxPago(DetallePago objDetallePago) throws LogicaImplException;
	
	public VentaPago crearDetallePagoCuotas(DetallePagoCuotas objDetallePagoCuotas) throws LogicaImplException;
	public VentaPago buscarDetallePagoCuotasxCodigoDetallePago(DetallePagoCuotas objDetallePagoCuotas) throws LogicaImplException;
	
//	public VentaPago crearAutorizacionPago(AutorizacionPago objAutorizacionPago) throws LogicaImplException;
//	public VentaPago actualizarAutorizacionPago(Integer id, AutorizacionPago objAutorizacionPago) throws LogicaImplException;
//	public VentaPago buscarAutorizacionPagoxPago(AutorizacionPago objAutorizacionPago) throws LogicaImplException;
	
	public void limpiarCacheLocal();
}
