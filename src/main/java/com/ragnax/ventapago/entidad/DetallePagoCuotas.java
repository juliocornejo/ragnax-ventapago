package com.ragnax.ventapago.entidad;


import java.math.BigDecimal;

import javax.persistence.*;

/**
 *  implementation class for : DetallePagoCuotas
 * en la base de Datos representa 
 */
@Entity
@Table (name="detalle_pago_cuotas")

public class DetallePagoCuotas{
 
	@Id
	@OrderBy
	@Column(name="id_detalle_pago_cuotas")
	private Integer idDetallePagoCuotas;
	
	@ManyToOne
	@JoinColumn(name="fk_id_detalle_pago")
	private DetallePago idDetallePago;
	
	@Column(name="cantidad_cuotas")
	private Integer cantidadCuotas;
	
	@Column(name="interes_mensual", precision=4, scale=2)
	private BigDecimal interesMensual;
	
	@Column(name="interes_anual", precision=4, scale=2)
	private BigDecimal interesAnual;
	
	@Column(name="valor_cuota", precision=9, scale=2)
	private BigDecimal valorCuota;
	
	@Column(name="cae", precision=4, scale=2)
	private BigDecimal cae;
	
	@Column(name="valor_total_credito", precision=9, scale=2)
	private BigDecimal valorTotalCredito;
	
	public DetallePagoCuotas() {
		super();
	}
	
	public DetallePagoCuotas(DetallePago idDetallePago) {
		super();
		this.idDetallePago = idDetallePago;
	}
	
	
	public DetallePagoCuotas(Integer idDetallePagoCuotas, DetallePago idDetallePago,
			Integer cantidadCuotas, BigDecimal interesMensual, BigDecimal interesAnual, BigDecimal valorCuota,
			BigDecimal cae, BigDecimal valorTotalCredito) {
		super();
		this.idDetallePagoCuotas = idDetallePagoCuotas;
		this.idDetallePago = idDetallePago;
		this.cantidadCuotas = cantidadCuotas;
		this.interesMensual = interesMensual;
		this.interesAnual = interesAnual;
		this.valorCuota = valorCuota;
		this.cae = cae;
		this.valorTotalCredito = valorTotalCredito;
	}
	
	public DetallePagoCuotas(DetallePago idDetallePago, Integer cantidadCuotas, BigDecimal interesMensual, BigDecimal interesAnual, BigDecimal valorCuota,
			BigDecimal cae, BigDecimal valorTotalCredito) {
		super();
		this.idDetallePago = idDetallePago;
		this.cantidadCuotas = cantidadCuotas;
		this.interesMensual = interesMensual;
		this.interesAnual = interesAnual;
		this.valorCuota = valorCuota;
		this.cae = cae;
		this.valorTotalCredito = valorTotalCredito;
	}

	public Integer getIdDetallePagoCuotas() {
		return idDetallePagoCuotas;
	}

	public void setIdDetallePagoCuotas(Integer idDetallePagoCuotas) {
		this.idDetallePagoCuotas = idDetallePagoCuotas;
	}

	public DetallePago getIdDetallePago() {
		return idDetallePago;
	}

	public void setIdDetallePago(DetallePago idDetallePago) {
		this.idDetallePago = idDetallePago;
	}

	public Integer getCantidadCuotas() {
		return cantidadCuotas;
	}

	public void setCantidadCuotas(Integer cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}

	public BigDecimal getInteresMensual() {
		return interesMensual;
	}

	public void setInteresMensual(BigDecimal interesMensual) {
		this.interesMensual = interesMensual;
	}

	public BigDecimal getInteresAnual() {
		return interesAnual;
	}

	public void setInteresAnual(BigDecimal interesAnual) {
		this.interesAnual = interesAnual;
	}

	public BigDecimal getValorCuota() {
		return valorCuota;
	}

	public void setValorCuota(BigDecimal valorCuota) {
		this.valorCuota = valorCuota;
	}

	public BigDecimal getCae() {
		return cae;
	}

	public void setCae(BigDecimal cae) {
		this.cae = cae;
	}

	public BigDecimal getValorTotalCredito() {
		return valorTotalCredito;
	}

	public void setValorTotalCredito(BigDecimal valorTotalCredito) {
		this.valorTotalCredito = valorTotalCredito;
	}
}
