package com.ragnax.ventapago.entidad;


import java.util.List;

import javax.persistence.*;

/**
 *  implementation class for : CanalPago
 * en la base de Datos representa 
 */
@Entity
@Table (name="canal_pago")

public class CanalPago{
 
	@Id
	@OrderBy
	@Column(name="id_canal_pago")
	private Integer idCanalPago;
	
	@Column(name="nombre_canal_pago")
	private String nombreCanalPago;
	
	@OneToMany(mappedBy="idCanalPago")
	private List<Negocio> negocios;
	
	public CanalPago() {
		super();
	}
	
	public CanalPago(Integer idCanalPago) {
		super();
		this.idCanalPago = idCanalPago;
	}

	public CanalPago(Integer idCanalPago, String nombreCanalPago) {
		super();
		this.idCanalPago = idCanalPago;
		this.nombreCanalPago = nombreCanalPago;
	}

	public Integer getIdCanalPago() {
		return idCanalPago;
	}

	public void setIdCanalPago(Integer idCanalPago) {
		this.idCanalPago = idCanalPago;
	}

	public String getNombreCanalPago() {
		return nombreCanalPago;
	}

	public void setNombreCanalPago(String nombreCanalPago) {
		this.nombreCanalPago = nombreCanalPago;
	}

	public List<Negocio> getNegocios() {
		return negocios;
	}

	public void setNegocios(List<Negocio> negocios) {
		this.negocios = negocios;
	}
	
}
