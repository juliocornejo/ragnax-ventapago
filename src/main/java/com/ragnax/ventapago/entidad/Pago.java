package com.ragnax.ventapago.entidad;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

/**
 *  implementation class for : Pago
 * en la base de Datos representa 
 */
@Entity
@Table (name="pago")

public class Pago{
	
	//No Utilizar, ni publicar fuera del POD correspondiente
	@Id
	@OrderBy
	@Column(name="id_pago")
	private Integer idPago;
	
	//Debe ser buscado el codigo de 
	@ManyToOne
	@JoinColumn(name="fk_id_negocio")
	private Negocio idNegocio;
	//el mismo de 
//	@Column(name="codigo_negocio")
//	private String codigoNegocio;
	
	@Column(name="estado_pago")
	private Boolean estadoPago;
	
	//Momento de confirmacion de pago con proveedor de pago
	@Column(name="fecha_pago_finalizado")
	private Timestamp fechaPagoFinalizado;
	
	@OneToMany(mappedBy="idPago")
	private List<DetallePago> detalles_pagos;
	
	@OneToMany(mappedBy="idPago")
	private List<AjusteCantidadTotal> ajustes_cantidades_totales;
	
	public Pago() {
		super();
	}
	
	public Pago(Negocio idNegocio) {
		super();
		
		this.idNegocio = idNegocio;
	}
	
	public Pago(Negocio idNegocio, Boolean estadoPago) {
		super();
		this.idNegocio = idNegocio;
		this.estadoPago = estadoPago;
	}
	
	public Integer getIdPago() {
		return idPago;
	}

	public void setIdPago(Integer idPago) {
		this.idPago = idPago;
	}

	public Negocio getIdNegocio() {
		return idNegocio;
	}

	public void setIdNegocio(Negocio idNegocio) {
		this.idNegocio = idNegocio;
	}

	public Boolean getEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(Boolean estadoPago) {
		this.estadoPago = estadoPago;
	}

	public Timestamp getFechaPagoFinalizado() {
		return fechaPagoFinalizado;
	}

	public void setFechaPagoFinalizado(Timestamp fechaPagoFinalizado) {
		this.fechaPagoFinalizado = fechaPagoFinalizado;
	}

	public List<DetallePago> getDetalles_pagos() {
		return detalles_pagos;
	}

	public void setDetalles_pagos(List<DetallePago> detalles_pagos) {
		this.detalles_pagos = detalles_pagos;
	}

	public List<AjusteCantidadTotal> getAjustes_cantidades_totales() {
		return ajustes_cantidades_totales;
	}

	public void setAjustes_cantidades_totales(List<AjusteCantidadTotal> ajustes_cantidades_totales) {
		this.ajustes_cantidades_totales = ajustes_cantidades_totales;
	}
}
