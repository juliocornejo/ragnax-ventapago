package com.ragnax.ventapago.configuration;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:factoryapi.properties")
@ConfigurationProperties(prefix = "factory")
/****Properties que pueden cambiar el valor ****/
public class FactoryApiProperties {
	
	private Configdata configdata;
	private Cache cache;
	
    public static class Configdata {
    	
    	private String fechayyyyMMddTHHmmssZ;
    	
    	@NotBlank
		private String estadoActivoConsulta;
    	
    	@NotBlank
		private String estadoInactivoConsulta;

		public String getEstadoActivoConsulta() {
			return estadoActivoConsulta;
		}
		
		public void setEstadoActivoConsulta(String estadoActivoConsulta) {
			this.estadoActivoConsulta = estadoActivoConsulta;
		}
		
		public String getEstadoInactivoConsulta() {
			return estadoInactivoConsulta;
		}
		
		public void setEstadoInactivoConsulta(String estadoInactivoConsulta) {
			this.estadoInactivoConsulta = estadoInactivoConsulta;
		}
		
		public String getFechayyyyMMddTHHmmssZ() {
			return fechayyyyMMddTHHmmssZ;
		}
		
		public void setFechayyyyMMddTHHmmssZ(String fechayyyyMMddTHHmmssZ) {
			this.fechayyyyMMddTHHmmssZ = fechayyyyMMddTHHmmssZ;
		}
		
    }
	
    public static class Cache {
		
    	private String evictBuscarTipoMoneda;
    	
    	private String evictListarTodoTipoMoneda;
    	
    	private String evictBuscarTipoNegocio;
    	
    	private String evictListarTodoTipoNegocio;
    	
    	private String evictBuscarTipoFeeComision;
    	
    	private String evictListarTodoTipoFeeComision;
        
    	private String evictBuscarTipoValorComision;
    	
    	private String evictListarTodoTipoValorComision;
    	
    	private String evictBuscarPaisxCodigoPortal;
    	
    	private String evictListarTodoPais;
    	
    	private String evictBuscarTipoCambioxCodigo;
    	
    	private String evictListarTipoCambioxTipoMonedaBase;
    	
    	private String evictListarTodoTipoCambio;
    	
    	private String evictBuscarProductoFeeComisionxCodigo;
    	
    	private String evictListarTodoProductoFeeComision;

		public String getEvictBuscarTipoMoneda() {
			return evictBuscarTipoMoneda;
		}

		public void setEvictBuscarTipoMoneda(String evictBuscarTipoMoneda) {
			this.evictBuscarTipoMoneda = evictBuscarTipoMoneda;
		}

		public String getEvictListarTodoTipoMoneda() {
			return evictListarTodoTipoMoneda;
		}

		public void setEvictListarTodoTipoMoneda(String evictListarTodoTipoMoneda) {
			this.evictListarTodoTipoMoneda = evictListarTodoTipoMoneda;
		}

		public String getEvictBuscarTipoNegocio() {
			return evictBuscarTipoNegocio;
		}

		public void setEvictBuscarTipoNegocio(String evictBuscarTipoNegocio) {
			this.evictBuscarTipoNegocio = evictBuscarTipoNegocio;
		}

		public String getEvictListarTodoTipoNegocio() {
			return evictListarTodoTipoNegocio;
		}

		public void setEvictListarTodoTipoNegocio(String evictListarTodoTipoNegocio) {
			this.evictListarTodoTipoNegocio = evictListarTodoTipoNegocio;
		}

		public String getEvictBuscarTipoFeeComision() {
			return evictBuscarTipoFeeComision;
		}

		public void setEvictBuscarTipoFeeComision(String evictBuscarTipoFeeComision) {
			this.evictBuscarTipoFeeComision = evictBuscarTipoFeeComision;
		}

		public String getEvictListarTodoTipoFeeComision() {
			return evictListarTodoTipoFeeComision;
		}

		public void setEvictListarTodoTipoFeeComision(String evictListarTodoTipoFeeComision) {
			this.evictListarTodoTipoFeeComision = evictListarTodoTipoFeeComision;
		}

		public String getEvictBuscarTipoValorComision() {
			return evictBuscarTipoValorComision;
		}

		public void setEvictBuscarTipoValorComision(String evictBuscarTipoValorComision) {
			this.evictBuscarTipoValorComision = evictBuscarTipoValorComision;
		}

		public String getEvictListarTodoTipoValorComision() {
			return evictListarTodoTipoValorComision;
		}

		public void setEvictListarTodoTipoValorComision(String evictListarTodoTipoValorComision) {
			this.evictListarTodoTipoValorComision = evictListarTodoTipoValorComision;
		}

		public String getEvictBuscarPaisxCodigoPortal() {
			return evictBuscarPaisxCodigoPortal;
		}

		public void setEvictBuscarPaisxCodigoPortal(String evictBuscarPaisxCodigoPortal) {
			this.evictBuscarPaisxCodigoPortal = evictBuscarPaisxCodigoPortal;
		}

		public String getEvictListarTodoPais() {
			return evictListarTodoPais;
		}

		public void setEvictListarTodoPais(String evictListarTodoPais) {
			this.evictListarTodoPais = evictListarTodoPais;
		}

		public String getEvictBuscarTipoCambioxCodigo() {
			return evictBuscarTipoCambioxCodigo;
		}

		public void setEvictBuscarTipoCambioxCodigo(String evictBuscarTipoCambioxCodigo) {
			this.evictBuscarTipoCambioxCodigo = evictBuscarTipoCambioxCodigo;
		}

		public String getEvictListarTipoCambioxTipoMonedaBase() {
			return evictListarTipoCambioxTipoMonedaBase;
		}

		public void setEvictListarTipoCambioxTipoMonedaBase(String evictListarTipoCambioxTipoMonedaBase) {
			this.evictListarTipoCambioxTipoMonedaBase = evictListarTipoCambioxTipoMonedaBase;
		}

		public String getEvictListarTodoTipoCambio() {
			return evictListarTodoTipoCambio;
		}

		public void setEvictListarTodoTipoCambio(String evictListarTodoTipoCambio) {
			this.evictListarTodoTipoCambio = evictListarTodoTipoCambio;
		}

		public String getEvictBuscarProductoFeeComisionxCodigo() {
			return evictBuscarProductoFeeComisionxCodigo;
		}

		public void setEvictBuscarProductoFeeComisionxCodigo(String evictBuscarProductoFeeComisionxCodigo) {
			this.evictBuscarProductoFeeComisionxCodigo = evictBuscarProductoFeeComisionxCodigo;
		}

		public String getEvictListarTodoProductoFeeComision() {
			return evictListarTodoProductoFeeComision;
		}

		public void setEvictListarTodoProductoFeeComision(String evictListarTodoProductoFeeComision) {
			this.evictListarTodoProductoFeeComision = evictListarTodoProductoFeeComision;
		}
    	
    }

	public Configdata getConfigdata() {
		return configdata;
	}

	public void setConfigdata(Configdata configdata) {
		this.configdata = configdata;
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

}