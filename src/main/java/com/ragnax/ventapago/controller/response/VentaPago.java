package com.ragnax.ventapago.controller.response;

import java.io.Serializable;
import java.util.List;

import com.ragnax.ventapago.entidad.AjusteCantidadTotal;
import com.ragnax.ventapago.entidad.AutorizacionPago;
import com.ragnax.ventapago.entidad.CanalPago;
import com.ragnax.ventapago.entidad.DetallePagoCuotas;
import com.ragnax.ventapago.entidad.DetallePago;
import com.ragnax.ventapago.entidad.MedioPago;
import com.ragnax.ventapago.entidad.Negocio;
import com.ragnax.ventapago.entidad.Pago;
import com.ragnax.ventapago.entidad.StatusNegocio;
import com.ragnax.ventapago.entidad.TipoMedioPago;
import com.ragnax.ventapago.entidad.TipoStatusNegocio;

public class VentaPago implements Serializable{

	private static final long serialVersionUID = -4301293450469130528L;
	
	private AjusteCantidadTotal ajusteCantidadTotal;
	private AutorizacionPago autorizacionPago;
	private CanalPago canalPago;
	private DetallePagoCuotas detallePagoCuotas;
	private DetallePago detallePago;
	private MedioPago medioPago;
	private Negocio negocio;
	private Pago pago;
	private StatusNegocio statusNegocio;
	private TipoMedioPago tipoMedioPago;
	private TipoStatusNegocio tipoStatusNegocio;
	
	private List<AjusteCantidadTotal> listaAjusteCantidadTotal;
	private List<AutorizacionPago> listaAutorizacionPago;
	private List<CanalPago> listaCanalPago;
	private List<DetallePagoCuotas> listaDetallePagoCuotas;
	private List<DetallePago> listaDetallePago;
	private List<MedioPago> listaMedioPago;
	private List<Negocio> listaNegocio;
	private List<Pago> listaPago;
	private List<StatusNegocio> listaStatusNegocio;
	private List<TipoMedioPago> listaTipoMedioPago;
	private List<TipoStatusNegocio> listaTipoStatusNegocio;
	
	public VentaPago() {
		super();
	}

	public AjusteCantidadTotal getAjusteCantidadTotal() {
		return ajusteCantidadTotal;
	}

	public void setAjusteCantidadTotal(AjusteCantidadTotal ajusteCantidadTotal) {
		this.ajusteCantidadTotal = ajusteCantidadTotal;
	}

	public AutorizacionPago getAutorizacionPago() {
		return autorizacionPago;
	}

	public void setAutorizacionPago(AutorizacionPago autorizacionPago) {
		this.autorizacionPago = autorizacionPago;
	}

	public CanalPago getCanalPago() {
		return canalPago;
	}

	public void setCanalPago(CanalPago canalPago) {
		this.canalPago = canalPago;
	}

	public DetallePagoCuotas getDetallePagoCuotas() {
		return detallePagoCuotas;
	}

	public void setDetallePagoCuotas(DetallePagoCuotas detallePagoCuotas) {
		this.detallePagoCuotas = detallePagoCuotas;
	}

	public DetallePago getDetallePago() {
		return detallePago;
	}

	public void setDetallePago(DetallePago detallePago) {
		this.detallePago = detallePago;
	}

	public MedioPago getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	public Negocio getNegocio() {
		return negocio;
	}

	public void setNegocio(Negocio negocio) {
		this.negocio = negocio;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public StatusNegocio getStatusNegocio() {
		return statusNegocio;
	}

	public void setStatusNegocio(StatusNegocio statusNegocio) {
		this.statusNegocio = statusNegocio;
	}

	public TipoMedioPago getTipoMedioPago() {
		return tipoMedioPago;
	}

	public void setTipoMedioPago(TipoMedioPago tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}

	public TipoStatusNegocio getTipoStatusNegocio() {
		return tipoStatusNegocio;
	}

	public void setTipoStatusNegocio(TipoStatusNegocio tipoStatusNegocio) {
		this.tipoStatusNegocio = tipoStatusNegocio;
	}

	public List<AjusteCantidadTotal> getListaAjusteCantidadTotal() {
		return listaAjusteCantidadTotal;
	}

	public void setListaAjusteCantidadTotal(List<AjusteCantidadTotal> listaAjusteCantidadTotal) {
		this.listaAjusteCantidadTotal = listaAjusteCantidadTotal;
	}

	public List<AutorizacionPago> getListaAutorizacionPago() {
		return listaAutorizacionPago;
	}

	public void setListaAutorizacionPago(List<AutorizacionPago> listaAutorizacionPago) {
		this.listaAutorizacionPago = listaAutorizacionPago;
	}

	public List<CanalPago> getListaCanalPago() {
		return listaCanalPago;
	}

	public void setListaCanalPago(List<CanalPago> listaCanalPago) {
		this.listaCanalPago = listaCanalPago;
	}

	public List<DetallePagoCuotas> getListaDetallePagoCuotas() {
		return listaDetallePagoCuotas;
	}

	public void setListaDetallePagoCuotas(List<DetallePagoCuotas> listaDetallePagoCuotas) {
		this.listaDetallePagoCuotas = listaDetallePagoCuotas;
	}

	public List<DetallePago> getListaDetallePago() {
		return listaDetallePago;
	}

	public void setListaDetallePago(List<DetallePago> listaDetallePago) {
		this.listaDetallePago = listaDetallePago;
	}

	public List<MedioPago> getListaMedioPago() {
		return listaMedioPago;
	}

	public void setListaMedioPago(List<MedioPago> listaMedioPago) {
		this.listaMedioPago = listaMedioPago;
	}

	public List<Negocio> getListaNegocio() {
		return listaNegocio;
	}

	public void setListaNegocio(List<Negocio> listaNegocio) {
		this.listaNegocio = listaNegocio;
	}

	public List<Pago> getListaPago() {
		return listaPago;
	}

	public void setListaPago(List<Pago> listaPago) {
		this.listaPago = listaPago;
	}

	public List<StatusNegocio> getListaStatusNegocio() {
		return listaStatusNegocio;
	}

	public void setListaStatusNegocio(List<StatusNegocio> listaStatusNegocio) {
		this.listaStatusNegocio = listaStatusNegocio;
	}

	public List<TipoMedioPago> getListaTipoMedioPago() {
		return listaTipoMedioPago;
	}

	public void setListaTipoMedioPago(List<TipoMedioPago> listaTipoMedioPago) {
		this.listaTipoMedioPago = listaTipoMedioPago;
	}

	public List<TipoStatusNegocio> getListaTipoStatusNegocio() {
		return listaTipoStatusNegocio;
	}

	public void setListaTipoStatusNegocio(List<TipoStatusNegocio> listaTipoStatusNegocio) {
		this.listaTipoStatusNegocio = listaTipoStatusNegocio;
	}
	
}
