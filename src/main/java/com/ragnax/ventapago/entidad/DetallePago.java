package com.ragnax.ventapago.entidad;


import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

/**
 *  implementation class for : DetallePago
 * en la base de Datos representa 
 */
@Entity
@Table (name="detalle_pago")

public class DetallePago{
 
//	@Id
//	@OrderBy
//	@Column(name="id_detalle_pago")
//	private Integer idDetallePago;
	
	@Id
	@OrderBy
	@Column(name="codigo_detalle_pago")
	private String codigoDetallePago;
	
	@Column(name="fk_id_tipo_moneda")
	private Integer idTipoMoneda;
	
	@ManyToOne
	@JoinColumn(name="fk_id_medio_pago")
	private MedioPago idMedioPago;
	
	@Column(name="monto_total_pago", precision=9, scale=2)
	private BigDecimal montoTotalPago;
	
	@Column(name="pago_en_cuotas")
	private Boolean pagoEnCuotas;
	
	@ManyToOne
	@JoinColumn(name="fk_id_pago")
	private Pago idPago;
	
	@OneToMany(mappedBy="idDetallePago")
	private List<DetallePagoCuotas> detalles_pagos_cuotas;
	
	public DetallePago() {
		super();
	}

	public DetallePago(Pago idPago) {
		super();
		this.idPago = idPago;
	}
	
	public DetallePago(String codigoDetallePago, Integer idTipoMoneda,
			MedioPago idMedioPago, BigDecimal montoTotalPago, Boolean pagoEnCuotas, Pago idPago) {
		super();
		this.codigoDetallePago = codigoDetallePago;
		this.idTipoMoneda = idTipoMoneda;
		this.idMedioPago = idMedioPago;
		this.montoTotalPago = montoTotalPago;
		this.pagoEnCuotas = pagoEnCuotas;
		this.idPago = idPago;
	}

	public DetallePago(
			Integer idTipoMoneda, MedioPago idMedioPago, BigDecimal montoTotalPago,
			Boolean pagoEnCuotas, Pago idPago) {
		super();
		this.idTipoMoneda = idTipoMoneda;
		this.idMedioPago = idMedioPago;
		this.montoTotalPago = montoTotalPago;
		this.pagoEnCuotas = pagoEnCuotas;
		this.idPago = idPago;
	}

	public String getCodigoDetallePago() {
		return codigoDetallePago;
	}

	public void setCodigoDetallePago(String codigoDetallePago) {
		this.codigoDetallePago = codigoDetallePago;
	}

	public Integer getIdTipoMoneda() {
		return idTipoMoneda;
	}

	public void setIdTipoMoneda(Integer idTipoMoneda) {
		this.idTipoMoneda = idTipoMoneda;
	}

	public MedioPago getIdMedioPago() {
		return idMedioPago;
	}

	public void setIdMedioPago(MedioPago idMedioPago) {
		this.idMedioPago = idMedioPago;
	}

	public BigDecimal getMontoTotalPago() {
		return montoTotalPago;
	}

	public void setMontoTotalPago(BigDecimal montoTotalPago) {
		this.montoTotalPago = montoTotalPago;
	}

	public Boolean getPagoEnCuotas() {
		return pagoEnCuotas;
	}

	public void setPagoEnCuotas(Boolean pagoEnCuotas) {
		this.pagoEnCuotas = pagoEnCuotas;
	}

	public Pago getIdPago() {
		return idPago;
	}

	public void setIdPago(Pago idPago) {
		this.idPago = idPago;
	}

	public List<DetallePagoCuotas> getDetalles_pagos_cuotas() {
		return detalles_pagos_cuotas;
	}

	public void setDetalles_pagos_cuotas(List<DetallePagoCuotas> detalles_pagos_cuotas) {
		this.detalles_pagos_cuotas = detalles_pagos_cuotas;
	}
}
