package com.ragnax.ventapago.entidad;


import java.util.List;

import javax.persistence.*;

/**
 *  implementation class for : TipoStatusNegocio
 * en la base de Datos representa 
 */
@Entity
@Table (name="tipo_status_negocio")
public class TipoStatusNegocio{
 
	@Id
	@OrderBy
	@Column(name="id_tipo_status_negocio")
	private Integer idTipoStatusNegocio;
	
	@Column(name="nombre_tipo_status_negocio")
	private String nombreTipoStatusNegocio;
	
	@OneToMany(mappedBy="idTipoStatusNegocio")
	private List<StatusNegocio> status_negocio;
	
	public TipoStatusNegocio() {
		super();
	}

	public TipoStatusNegocio(Integer idTipoStatusNegocio) {
		super();
		this.idTipoStatusNegocio = idTipoStatusNegocio;
	}
	
	public TipoStatusNegocio(Integer idTipoStatusNegocio, String nombreTipoStatusNegocio) {
		super();
		this.idTipoStatusNegocio = idTipoStatusNegocio;
		this.nombreTipoStatusNegocio = nombreTipoStatusNegocio;
	}

	public Integer getIdTipoStatusNegocio() {
		return idTipoStatusNegocio;
	}

	public void setIdTipoStatusNegocio(Integer idTipoStatusNegocio) {
		this.idTipoStatusNegocio = idTipoStatusNegocio;
	}

	public String getNombreTipoStatusNegocio() {
		return nombreTipoStatusNegocio;
	}

	public void setNombreTipoStatusNegocio(String nombreTipoStatusNegocio) {
		this.nombreTipoStatusNegocio = nombreTipoStatusNegocio;
	}

	public List<StatusNegocio> getStatus_negocio() {
		return status_negocio;
	}

	public void setStatus_negocio(List<StatusNegocio> status_negocio) {
		this.status_negocio = status_negocio;
	}
	
}
