package com.ragnax.ventapago.entidad;


import java.util.List;

import javax.persistence.*;

/**
 *  implementation class for : TipoMedioPago
 * en la base de Datos representa 
 */
@Entity
@Table (name="tipo_medio_pago")

public class TipoMedioPago{
//Credito.
//Debito.
	@Id
	@OrderBy
	@Column(name="id_tipo_medio_pago")
	private Integer idTipoMedioPago;
	
	@Column(name="nombre_tipo_medio_pago")
	private String nombreTipoMedioPago;
	
	@OneToMany(mappedBy="idTipoMedioPago")
	private List<MedioPago> medios_pagos;
	
	public TipoMedioPago() {
		super();
	}
	
	public TipoMedioPago(Integer idTipoMedioPago) {
		super();
		this.idTipoMedioPago = idTipoMedioPago;
	}
	
	public TipoMedioPago(Integer idTipoMedioPago, String nombreTipoMedioPago) {
		super();
		this.idTipoMedioPago = idTipoMedioPago;
		this.nombreTipoMedioPago = nombreTipoMedioPago;
	}

	public Integer getIdTipoMedioPago() {
		return idTipoMedioPago;
	}

	public void setIdTipoMedioPago(Integer idTipoMedioPago) {
		this.idTipoMedioPago = idTipoMedioPago;
	}

	public String getNombreTipoMedioPago() {
		return nombreTipoMedioPago;
	}

	public void setNombreTipoMedioPago(String nombreTipoMedioPago) {
		this.nombreTipoMedioPago = nombreTipoMedioPago;
	}

	public List<MedioPago> getMedios_pagos() {
		return medios_pagos;
	}

	public void setMedios_pagos(List<MedioPago> medios_pagos) {
		this.medios_pagos = medios_pagos;
	}
}
