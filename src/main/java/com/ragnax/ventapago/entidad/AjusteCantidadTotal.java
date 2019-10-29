package com.ragnax.ventapago.entidad;


import java.math.BigDecimal;

import javax.persistence.*;

/**
 *  implementation class for : AjusteCantidadTotal
 * en la base de Datos representa 
 */
@Entity
@Table (name="ajuste_cantidad_total")

public class AjusteCantidadTotal{
 
	@Id
	@OrderBy
	@Column(name="id_ajuste_cantidad_total")
	private Integer idAjusteCantidadTotal;
	
	@ManyToOne
	@JoinColumn(name="fk_id_pago")
	private Pago idPago;
	
	@Column(name="fk_id_tipo_moneda")
	private Integer idTipoMoneda;
	/***Total y Neto ********/
	@Column(name="valor_cantidad_bruto_carro", precision=9, scale=2)
	private BigDecimal valorCantidadBrutoCarro;
	
	@Column(name="valor_neto_carro", precision=9, scale=2)
	private BigDecimal valorNetoCarro;
	
	@Column(name="valor_cantidad_impuesto_carro", precision=9, scale=2)
	private BigDecimal valorCantidadImpuestoCarro;
	
	@Column(name="porcentaje_impuesto_aplicado_carro", precision=4, scale=2)
	private BigDecimal porcentajeImpuestoAplicadoCarro;
	
	/****************/
	@Column(name="fk_id_historial_tipo_cambio")
	private Integer idHistorialTipoCambio;
	
	@Column(name="valor_tipo_cambio_base", precision=9, scale=2)
	private BigDecimal valorRefTipoCambioBase;
	
	@Column(name="valor_tipo_cambio_destino", precision=9, scale=2)
	private BigDecimal valorRefTipoCambioDestino;
	
	@Column(name="fk_id_historial_fee_comision")
	private Integer idHistorialFeeComision;
	
	@Column(name="porcentaje_comision_aplicado", precision=4, scale=2)
	private BigDecimal porcentajeRefComisionAplicado;
	
	@Column(name="valor_cantidad_comision", precision=9, scale=2)
	private BigDecimal valorRefCantidadComision;
	
	@Column(name="porcentaje_descuento_aplicado", precision=4, scale=2)
	private BigDecimal porcentajeDescuentoAplicado;
	
	@Column(name="valor_cantidad_descuento_aplicado", precision=9, scale=2)
	private BigDecimal valorCantidadDescuentoAplicado;
	
	public AjusteCantidadTotal() {
		super();
	}
	
	public AjusteCantidadTotal(Integer idAjusteCantidadTotal, Pago idPago,
			Integer idTipoMoneda, BigDecimal valorCantidadBrutoCarro, BigDecimal valorNetoCarro,
			BigDecimal valorCantidadImpuestoCarro, BigDecimal porcentajeImpuestoAplicadoCarro,
			Integer idHistorialTipoCambio, BigDecimal valorRefTipoCambioBase, BigDecimal valorRefTipoCambioDestino,
			Integer idHistorialFeeComision, BigDecimal porcentajeRefComisionAplicado,
			BigDecimal valorRefCantidadComision, BigDecimal porcentajeDescuentoAplicado,
			BigDecimal valorCantidadDescuentoAplicado) {
		super();
		this.idAjusteCantidadTotal = idAjusteCantidadTotal;
		this.idPago = idPago;
		this.idTipoMoneda = idTipoMoneda;
		this.valorCantidadBrutoCarro = valorCantidadBrutoCarro;
		this.valorNetoCarro = valorNetoCarro;
		this.valorCantidadImpuestoCarro = valorCantidadImpuestoCarro;
		this.porcentajeImpuestoAplicadoCarro = porcentajeImpuestoAplicadoCarro;
		this.idHistorialTipoCambio = idHistorialTipoCambio;
		this.valorRefTipoCambioBase = valorRefTipoCambioBase;
		this.valorRefTipoCambioDestino = valorRefTipoCambioDestino;
		this.idHistorialFeeComision = idHistorialFeeComision;
		this.porcentajeRefComisionAplicado = porcentajeRefComisionAplicado;
		this.valorRefCantidadComision = valorRefCantidadComision;
		this.porcentajeDescuentoAplicado = porcentajeDescuentoAplicado;
		this.valorCantidadDescuentoAplicado = valorCantidadDescuentoAplicado;
	}

	public AjusteCantidadTotal(Pago idPago, Integer idTipoMoneda,
			BigDecimal valorCantidadBrutoCarro, BigDecimal valorNetoCarro, BigDecimal valorCantidadImpuestoCarro,
			BigDecimal porcentajeImpuestoAplicadoCarro, Integer idHistorialTipoCambio,
			BigDecimal valorRefTipoCambioBase, BigDecimal valorRefTipoCambioDestino, Integer idHistorialFeeComision,
			BigDecimal porcentajeRefComisionAplicado, BigDecimal valorRefCantidadComision,
			BigDecimal porcentajeDescuentoAplicado, BigDecimal valorCantidadDescuentoAplicado) {
		super();
		this.idPago = idPago;
		this.idTipoMoneda = idTipoMoneda;
		this.valorCantidadBrutoCarro = valorCantidadBrutoCarro;
		this.valorNetoCarro = valorNetoCarro;
		this.valorCantidadImpuestoCarro = valorCantidadImpuestoCarro;
		this.porcentajeImpuestoAplicadoCarro = porcentajeImpuestoAplicadoCarro;
		this.idHistorialTipoCambio = idHistorialTipoCambio;
		this.valorRefTipoCambioBase = valorRefTipoCambioBase;
		this.valorRefTipoCambioDestino = valorRefTipoCambioDestino;
		this.idHistorialFeeComision = idHistorialFeeComision;
		this.porcentajeRefComisionAplicado = porcentajeRefComisionAplicado;
		this.valorRefCantidadComision = valorRefCantidadComision;
		this.porcentajeDescuentoAplicado = porcentajeDescuentoAplicado;
		this.valorCantidadDescuentoAplicado = valorCantidadDescuentoAplicado;
	}

	public Integer getIdAjusteCantidadTotal() {
		return idAjusteCantidadTotal;
	}

	public void setIdAjusteCantidadTotal(Integer idAjusteCantidadTotal) {
		this.idAjusteCantidadTotal = idAjusteCantidadTotal;
	}

	public Pago getIdPago() {
		return idPago;
	}

	public void setIdPago(Pago idPago) {
		this.idPago = idPago;
	}

	
	public Integer getIdTipoMoneda() {
		return idTipoMoneda;
	}

	public void setIdTipoMoneda(Integer idTipoMoneda) {
		this.idTipoMoneda = idTipoMoneda;
	}

	public BigDecimal getValorCantidadBrutoCarro() {
		return valorCantidadBrutoCarro;
	}

	public void setValorCantidadBrutoCarro(BigDecimal valorCantidadBrutoCarro) {
		this.valorCantidadBrutoCarro = valorCantidadBrutoCarro;
	}

	public BigDecimal getValorNetoCarro() {
		return valorNetoCarro;
	}

	public void setValorNetoCarro(BigDecimal valorNetoCarro) {
		this.valorNetoCarro = valorNetoCarro;
	}

	public BigDecimal getValorCantidadImpuestoCarro() {
		return valorCantidadImpuestoCarro;
	}

	public void setValorCantidadImpuestoCarro(BigDecimal valorCantidadImpuestoCarro) {
		this.valorCantidadImpuestoCarro = valorCantidadImpuestoCarro;
	}

	public BigDecimal getPorcentajeImpuestoAplicadoCarro() {
		return porcentajeImpuestoAplicadoCarro;
	}

	public void setPorcentajeImpuestoAplicadoCarro(BigDecimal porcentajeImpuestoAplicadoCarro) {
		this.porcentajeImpuestoAplicadoCarro = porcentajeImpuestoAplicadoCarro;
	}

	public Integer getIdHistorialTipoCambio() {
		return idHistorialTipoCambio;
	}

	public void setIdHistorialTipoCambio(Integer idHistorialTipoCambio) {
		this.idHistorialTipoCambio = idHistorialTipoCambio;
	}

	public BigDecimal getValorRefTipoCambioBase() {
		return valorRefTipoCambioBase;
	}

	public void setValorRefTipoCambioBase(BigDecimal valorRefTipoCambioBase) {
		this.valorRefTipoCambioBase = valorRefTipoCambioBase;
	}

	public BigDecimal getValorRefTipoCambioDestino() {
		return valorRefTipoCambioDestino;
	}

	public void setValorRefTipoCambioDestino(BigDecimal valorRefTipoCambioDestino) {
		this.valorRefTipoCambioDestino = valorRefTipoCambioDestino;
	}

	public Integer getIdHistorialFeeComision() {
		return idHistorialFeeComision;
	}

	public void setIdHistorialFeeComision(Integer idHistorialFeeComision) {
		this.idHistorialFeeComision = idHistorialFeeComision;
	}

	public BigDecimal getPorcentajeRefComisionAplicado() {
		return porcentajeRefComisionAplicado;
	}

	public void setPorcentajeRefComisionAplicado(BigDecimal porcentajeRefComisionAplicado) {
		this.porcentajeRefComisionAplicado = porcentajeRefComisionAplicado;
	}

	public BigDecimal getValorRefCantidadComision() {
		return valorRefCantidadComision;
	}

	public void setValorRefCantidadComision(BigDecimal valorRefCantidadComision) {
		this.valorRefCantidadComision = valorRefCantidadComision;
	}

	public BigDecimal getPorcentajeDescuentoAplicado() {
		return porcentajeDescuentoAplicado;
	}

	public void setPorcentajeDescuentoAplicado(BigDecimal porcentajeDescuentoAplicado) {
		this.porcentajeDescuentoAplicado = porcentajeDescuentoAplicado;
	}

	public BigDecimal getValorCantidadDescuentoAplicado() {
		return valorCantidadDescuentoAplicado;
	}

	public void setValorCantidadDescuentoAplicado(BigDecimal valorCantidadDescuentoAplicado) {
		this.valorCantidadDescuentoAplicado = valorCantidadDescuentoAplicado;
	}
	
}
