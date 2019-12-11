package com.ragnax.ventapago.entidad;


import java.sql.Timestamp;

import javax.persistence.*;

/**
 *  implementation class for : StatusNegocio
 * en la base de Datos representa 
 */
@Entity
@Table (name="status_negocio")

public class StatusNegocio{
 
	@Id
	@OrderBy
	@Column(name="id_status_negocio")
	private Integer idStatusNegocio;
	
	@Column(name="fecha_status_negocio")
	private Timestamp fechaStatusNegocio;
	
	@ManyToOne
	@JoinColumn(name="fk_id_tipo_status_negocio")
	private TipoStatusNegocio idTipoStatusNegocio;
	
	@ManyToOne
	@JoinColumn(name="fk_id_negocio")
	private Negocio idNegocio;
	
	public StatusNegocio() {
		super();
	}

	public StatusNegocio(Negocio idNegocio) {
		super();
		this.idNegocio = idNegocio;
	}
	
	public StatusNegocio(Timestamp fechaStatusNegocio, Negocio idNegocio) {
		super();
		this.fechaStatusNegocio = fechaStatusNegocio;
		this.idNegocio = idNegocio;
	}
	
	

	public Integer getIdStatusNegocio() {
		return idStatusNegocio;
	}

	public void setIdStatusNegocio(Integer idStatusNegocio) {
		this.idStatusNegocio = idStatusNegocio;
	}

	public Timestamp getFechaStatusNegocio() {
		return fechaStatusNegocio;
	}

	public void setFechaStatusNegocio(Timestamp fechaStatusNegocio) {
		this.fechaStatusNegocio = fechaStatusNegocio;
	}

	public TipoStatusNegocio getIdTipoStatusNegocio() {
		return idTipoStatusNegocio;
	}

	public void setIdTipoStatusNegocio(TipoStatusNegocio idTipoStatusNegocio) {
		this.idTipoStatusNegocio = idTipoStatusNegocio;
	}

	public Negocio getIdNegocio() {
		return idNegocio;
	}

	public void setIdNegocio(Negocio idNegocio) {
		this.idNegocio = idNegocio;
	}
	
}
