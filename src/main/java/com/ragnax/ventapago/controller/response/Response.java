package com.ragnax.ventapago.controller.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import vijnana.respuesta.wrapper.response.AbstractWrapper;
import vijnana.respuesta.wrapper.response.AbstractWrapperError;

@XmlRootElement(name="response")
public class Response extends AbstractWrapper implements Serializable{

	private static final long serialVersionUID = -6581071838120916016L;

//	private String error;
	
	private int status;
	
	private VentaPago data;
	
	public Response() {
		super();
	}
	
	
	public Response(AbstractWrapperError error, int status, VentaPago data, String tiempoRespuesta,  String url) {
		super(tiempoRespuesta,url, error);
		this.error = error;
		this.status = status;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public VentaPago getData() {
		return data;
	}

	public void setData(VentaPago data) {
		this.data = data;
	}
	
}
