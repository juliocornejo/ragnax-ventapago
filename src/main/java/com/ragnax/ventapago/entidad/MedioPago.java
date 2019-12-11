package com.ragnax.ventapago.entidad;


import java.util.List;

import javax.persistence.*;

/**
 *  implementation class for : MedioPago
 * en la base de Datos representa 
 */
@Entity
@Table (name="medio_pago")

public class MedioPago{
//VISA
//MASTERCARD
//Tarjeta Bancaria
//	@Id
//	@OrderBy
//	@Column(name="id_medio_pago")
//	private Integer idMedioPago;
	
	@Id
	@Column(name="codigo_medio_pago")
	private String codigoMedioPago;
	
	@Column(name="nombre_medio_pago")
	private String nombreMedioPago;
	
	@Column(name="estado_medio_pago")
	private Boolean estadoMedioPago;
	
	@ManyToOne
	@JoinColumn(name="fk_id_tipo_medio_pago")
	private TipoMedioPago idTipoMedioPago;
	
	@OneToMany(mappedBy="idMedioPago")
	private List<DetallePago> detalles_pagos;
	
	public MedioPago() {
		super();
	}
	
	public MedioPago(String codigoMedioPago) {
		super();
		this.codigoMedioPago = codigoMedioPago;
	}
	

	public MedioPago(String codigoMedioPago, String nombreMedioPago,
			Boolean estadoMedioPago, TipoMedioPago idTipoMedioPago) {
		super();
		this.codigoMedioPago = codigoMedioPago;
		this.nombreMedioPago = nombreMedioPago;
		this.estadoMedioPago = estadoMedioPago;
		this.idTipoMedioPago = idTipoMedioPago;
	}

	public MedioPago(String codigoMedioPago, String nombreMedioPago,
			Boolean estadoMedioPago, TipoMedioPago idTipoMedioPago, List<DetallePago> detalles_pagos) {
		super();
		this.codigoMedioPago = codigoMedioPago;
		this.nombreMedioPago = nombreMedioPago;
		this.estadoMedioPago = estadoMedioPago;
		this.idTipoMedioPago = idTipoMedioPago;
		this.detalles_pagos = detalles_pagos;
	}

//	public Integer getIdMedioPago() {
//		return idMedioPago;
//	}
//
//	public void setIdMedioPago(Integer idMedioPago) {
//		this.idMedioPago = idMedioPago;
//	}

	public String getCodigoMedioPago() {
		return codigoMedioPago;
	}

	public void setCodigoMedioPago(String codigoMedioPago) {
		this.codigoMedioPago = codigoMedioPago;
	}

	public String getNombreMedioPago() {
		return nombreMedioPago;
	}

	public void setNombreMedioPago(String nombreMedioPago) {
		this.nombreMedioPago = nombreMedioPago;
	}

	public Boolean getEstadoMedioPago() {
		return estadoMedioPago;
	}

	public void setEstadoMedioPago(Boolean estadoMedioPago) {
		this.estadoMedioPago = estadoMedioPago;
	}

	public TipoMedioPago getIdTipoMedioPago() {
		return idTipoMedioPago;
	}

	public void setIdTipoMedioPago(TipoMedioPago idTipoMedioPago) {
		this.idTipoMedioPago = idTipoMedioPago;
	}

	public List<DetallePago> getDetalles_pagos() {
		return detalles_pagos;
	}

	public void setDetalles_pagos(List<DetallePago> detalles_pagos) {
		this.detalles_pagos = detalles_pagos;
	}
}
