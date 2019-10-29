package com.ragnax.ventapago.entidad;

import javax.persistence.*;

/**
 *  implementation class for : AutorizacionPago
 * en la base de Datos representa 
 */
@Entity
@Table (name="autorizacion_pago")

public class AutorizacionPago{
 
	@Id
	@OrderBy
	@Column(name="id_autorizacion_pago")
	private Integer idAutorizacionPago;
	
	@ManyToOne
	@JoinColumn(name="fk_id_pago")
	private Pago idPago;
	
	@Column(name="codigo_autorizacion_pago")
	private String codigoAutorizacionPago;
	
	@Column(name="proveedor_autorizacion_pago")
	private String proveedorAutorizacionPago;
	
	public AutorizacionPago() {
		super();
	}

	public AutorizacionPago(Pago idPago) {
		super();
		this.idPago = idPago;
	}

	public AutorizacionPago(Integer idAutorizacionPago, Pago idPago, String codigoAutorizacionPago,
			String proveedorAutorizacionPago) {
		super();
		this.idAutorizacionPago = idAutorizacionPago;
		this.idPago = idPago;
		this.codigoAutorizacionPago = codigoAutorizacionPago;
		this.proveedorAutorizacionPago = proveedorAutorizacionPago;
	}

	public Integer getIdAutorizacionPago() {
		return idAutorizacionPago;
	}

	public void setIdAutorizacionPago(Integer idAutorizacionPago) {
		this.idAutorizacionPago = idAutorizacionPago;
	}

	public Pago getIdPago() {
		return idPago;
	}

	public void setIdPago(Pago idPago) {
		this.idPago = idPago;
	}

	public String getCodigoAutorizacionPago() {
		return codigoAutorizacionPago;
	}

	public void setCodigoAutorizacionPago(String codigoAutorizacionPago) {
		this.codigoAutorizacionPago = codigoAutorizacionPago;
	}

	public String getProveedorAutorizacionPago() {
		return proveedorAutorizacionPago;
	}

	public void setProveedorAutorizacionPago(String proveedorAutorizacionPago) {
		this.proveedorAutorizacionPago = proveedorAutorizacionPago;
	}
	
}
