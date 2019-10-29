package com.ragnax.ventapago.servicio.utilidades;

import com.ragnax.ventapago.entidad.DetallePago;
import com.ragnax.ventapago.entidad.Negocio;

import vijnana.utilidades.component.utilidades.excriptar.Encriptar1_1;

public class UtilidadesVentaPago {

	public static String obtenerCodigoNegocio(Negocio objNegocio){

		try {
			if(objNegocio.getIdUsuarioContactoEmailContacto()!=null &&
					objNegocio.getIdPaisPortal()>0 && objNegocio.getIdCanalPago().getIdCanalPago() >0 && objNegocio.getIdTipoNegocio() > 0) {
				//Validar que no exista la combinacion del, negocio, tipo de fee y el nombre de ese cargo.

				String codigoNegocio = objNegocio.getIdUsuarioContactoEmailContacto()+
						objNegocio.getIdPaisPortal()+
						objNegocio.getIdCanalPago().getIdCanalPago()+
						objNegocio.getIdTipoNegocio();
				codigoNegocio = codigoNegocio.trim();
				codigoNegocio = codigoNegocio.replace("\\s", "").replace(" ", "");
				codigoNegocio = codigoNegocio.toLowerCase();
				
				codigoNegocio = Encriptar1_1.generarCodigoByNumero(codigoNegocio);
				
				return codigoNegocio;
			}

		} catch (Exception e) {
			return null;
		}

		return null;
	}
	
	public static String obtenerDetallePago(DetallePago objDetallePago){

		try {
			if(objDetallePago.getIdTipoMoneda()!=null && objDetallePago.getIdPago().getIdPago() >0) {
				//Validar que no exista la combinacion del, negocio, tipo de fee y el nombre de ese cargo.
				Integer pagoEnCuotas = (objDetallePago.getPagoEnCuotas()) ? 1 : 0; 
				
				String codigoDetallePago = objDetallePago.getIdTipoMoneda()+""+pagoEnCuotas+objDetallePago.getIdPago().getIdPago();
				
				codigoDetallePago = codigoDetallePago.trim();
				
				codigoDetallePago = codigoDetallePago.replace("\\s", "").replace(" ", "");
				
				codigoDetallePago = codigoDetallePago.toLowerCase();
				
				codigoDetallePago = Encriptar1_1.generarCodigoByNumeroByEncodear(codigoDetallePago, objDetallePago.getIdMedioPago().getCodigoMedioPago());
				
				return codigoDetallePago;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

} 
