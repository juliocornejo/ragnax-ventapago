package com.ragnax.ventapago.entidad;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;


/**
 *  implementation class for : Negocio
 * en la base de Datos representa 
 */
@Entity
@Table (name="negocio")
public class Negocio{
 
	@Id
	@OrderBy
	@Column(name="id_negocio")
	private Integer idNegocio;
	
	@OrderBy
	@Column(name="codigo_negocio")
	private String codigoNegocio;

	@Column(name="usuario_ejecutivo")
	private Integer idUsuarioEjecutivo;
	
	@Column(name="recibir_email_contacto")
	private Boolean recibirEmailContacto;
	
	@Column(name="fuera_del_pais")
	private Boolean fueraDelPais;
	//Todos los usuarios
	@Column(name="id_usuario_contacto_email_contacto") //id de usuario, siempre el id de usuario si vamos a crear codigos de negocio
	private String idUsuarioContactoEmailContacto;
	
	@Lob //o guardar el key asociado a ese negocio
	@Column(name="value_64_negocio_producto", length = 10000)
	private String value_64_negocio_producto;
	
	@Column(name="fecha_negocio")
	private Timestamp fechaNegocio;
	
	@Column(name="fk_id_tipo_negocio")
	private Integer idTipoNegocio;
	
	@Column(name="fk_id_pais_portal")
	private String codigoPaisPortal;
	
	@ManyToOne
	@JoinColumn(name="fk_id_canal_pago")
	private CanalPago idCanalPago;
	
	@OneToMany(mappedBy="idNegocio")
	private List<StatusNegocio> negocios;
	
	@OneToMany(mappedBy="idNegocio")
	private List<Pago> pagos;
	
	public Negocio() {
		super();
	}
	
		
	public Negocio(String codigoNegocio) {
		super();
		this.codigoNegocio = codigoNegocio;
	}
	
	public Negocio(Integer idUsuarioEjecutivo, Boolean recibirEmailContacto, Boolean fueraDelPais, String idUsuarioContactoEmailContacto, 
			Integer idTipoNegocio, String codigoPaisPortal, String value_64_negocio_producto,
			CanalPago idCanalPago) {
		this.idUsuarioEjecutivo = idUsuarioEjecutivo;
		this.recibirEmailContacto = recibirEmailContacto;
		this.fueraDelPais = fueraDelPais;
		this.idUsuarioContactoEmailContacto = idUsuarioContactoEmailContacto;
		this.idTipoNegocio = idTipoNegocio;
		this.codigoPaisPortal = codigoPaisPortal;
		this.value_64_negocio_producto = value_64_negocio_producto;
		this.idCanalPago = idCanalPago;
	}

	public Negocio(String codigoNegocio, Integer idUsuarioEjecutivo,
			Boolean recibirEmailContacto, Boolean fueraDelPais, Boolean proceso_Terminado, String idUsuarioContactoEmailContacto,
			Integer idTipoNegocio, String codigoPaisPortal, String value_64_negocio_producto,
			CanalPago idCanalPago) {
		super();
		this.codigoNegocio = codigoNegocio;
		this.idUsuarioEjecutivo = idUsuarioEjecutivo;
		this.recibirEmailContacto = recibirEmailContacto;
		this.fueraDelPais = fueraDelPais;
		this.idUsuarioContactoEmailContacto = idUsuarioContactoEmailContacto;
		this.idTipoNegocio = idTipoNegocio;
		this.codigoPaisPortal = codigoPaisPortal;
		this.value_64_negocio_producto = value_64_negocio_producto;
		this.idCanalPago = idCanalPago;
	}
	
	public Integer getIdNegocio() {
		return idNegocio;
	}

	public void setIdNegocio(Integer idNegocio) {
		this.idNegocio = idNegocio;
	}
	
	public String getCodigoNegocio() {
		return codigoNegocio;
	}

	public void setCodigoNegocio(String codigoNegocio) {
		this.codigoNegocio = codigoNegocio;
	}

	public Integer getIdUsuarioEjecutivo() {
		return idUsuarioEjecutivo;
	}

	public void setIdUsuarioEjecutivo(Integer idUsuarioEjecutivo) {
		this.idUsuarioEjecutivo = idUsuarioEjecutivo;
	}

	public Boolean getRecibirEmailContacto() {
		return recibirEmailContacto;
	}

	public void setRecibirEmailContacto(Boolean recibirEmailContacto) {
		this.recibirEmailContacto = recibirEmailContacto;
	}

	public Boolean getFueraDelPais() {
		return fueraDelPais;
	}

	public void setFueraDelPais(Boolean fueraDelPais) {
		this.fueraDelPais = fueraDelPais;
	}

	public String getIdUsuarioContactoEmailContacto() {
		return idUsuarioContactoEmailContacto;
	}

	public void setIdUsuarioContactoEmailContacto(String idUsuarioContactoEmailContacto) {
		this.idUsuarioContactoEmailContacto = idUsuarioContactoEmailContacto;
	}

	public String getValue_64_negocio_producto() {
		return value_64_negocio_producto;
	}

	public void setValue_64_negocio_producto(String value_64_negocio_producto) {
		this.value_64_negocio_producto = value_64_negocio_producto;
	}

	public Timestamp getFechaNegocio() {
		return fechaNegocio;
	}

	public void setFechaNegocio(Timestamp fechaNegocio) {
		this.fechaNegocio = fechaNegocio;
	}

	public Integer getIdTipoNegocio() {
		return idTipoNegocio;
	}

	public void setIdTipoNegocio(Integer idTipoNegocio) {
		this.idTipoNegocio = idTipoNegocio;
	}
	
	public String getCodigoPaisPortal() {
		return codigoPaisPortal;
	}

	public void setCodigoPaisPortal(String codigoPaisPortal) {
		this.codigoPaisPortal = codigoPaisPortal;
	}

	public CanalPago getIdCanalPago() {
		return idCanalPago;
	}

	public void setIdCanalPago(CanalPago idCanalPago) {
		this.idCanalPago = idCanalPago;
	}

	public List<StatusNegocio> getNegocios() {
		return negocios;
	}

	public void setNegocios(List<StatusNegocio> negocios) {
		this.negocios = negocios;
	}

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}
}
