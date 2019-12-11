package com.ragnax.ventapago.servicio.utilidades;

import java.util.ArrayList;
import java.util.List;

import com.ragnax.ventapago.entidad.DetallePago;
import com.ragnax.ventapago.entidad.Negocio;

public class VentaPagoUtilidades {

	public static List<String> crearListaCadenaCodigoNegocio(Negocio objNegocio){

		List<String> listaCadena = new ArrayList<String>(); 

		String codigoNegocio = objNegocio.getIdUsuarioContactoEmailContacto()+
				objNegocio.getIdCanalPago().getIdCanalPago()+
				objNegocio.getIdTipoNegocio();
		codigoNegocio = codigoNegocio.trim();
		codigoNegocio = codigoNegocio.replace("\\s", "").replace(" ", "");
		codigoNegocio = codigoNegocio.toLowerCase();

		listaCadena.add(codigoNegocio);

		return listaCadena;

	}

	public static List<String> crearListaCadenaCodigoDetallePago(DetallePago objDetallePago){

		List<String> listaCadena = new ArrayList<String>(); 

		Integer pagoEnCuotas = (objDetallePago.getPagoEnCuotas()) ? 1 : 0; 

		String codigoDetallePago = objDetallePago.getIdTipoMoneda()+""+pagoEnCuotas+objDetallePago.getIdPago().getIdPago();

		codigoDetallePago = codigoDetallePago.trim();

		codigoDetallePago = codigoDetallePago.replace("\\s", "").replace(" ", "");

		codigoDetallePago = codigoDetallePago.toLowerCase();

		listaCadena.add(codigoDetallePago);
		//				codigoTipoCambio = zapalaClienteRest.generarCodigoByNumero(new GeneraCodigo(codigoTipoCambio, null)).getData().getCodigoGenerado();
		return listaCadena;
	}

	public static List<String> convertirStrFechaConFormatToTimestamp(String sFecha, String formato){

		List<String> listaCadena = new ArrayList<String>(); 

		listaCadena.add(sFecha);

		listaCadena.add(formato);

		return listaCadena;

	}


	//	public String obtenerCodigoNegocio(Negocio objNegocio){
	//
	//		try {
	//			if(objNegocio.getIdUsuarioContactoEmailContacto()!=null &&
	//					objNegocio.getCodigoPaisPortal()!=null && objNegocio.getIdCanalPago().getIdCanalPago() >0 && objNegocio.getIdTipoNegocio() > 0) {
	//				//Validar que no exista la combinacion del, negocio, tipo de fee y el nombre de ese cargo.
	//				List<String> listaCadena = new ArrayList<String>(); 
	//
	//				String codigoNegocio = objNegocio.getIdUsuarioContactoEmailContacto()+
	//						objNegocio.getIdCanalPago().getIdCanalPago()+
	//						objNegocio.getIdTipoNegocio();
	//				codigoNegocio = codigoNegocio.trim();
	//				codigoNegocio = codigoNegocio.replace("\\s", "").replace(" ", "");
	//				codigoNegocio = codigoNegocio.toLowerCase();
	//
	//				listaCadena.add(codigoNegocio);
	//
	//				//				codigoTipoCambio = zapalaClienteRest.generarCodigoByNumero(new GeneraCodigo(codigoTipoCambio, null)).getData().getCodigoGenerado();
	//				return zapalaClienteRest.generarCodigoByNumeroByEncodear(new ZapalaRequest(listaCadena)).getCodigoGenerado();
	//
	//			}
	//
	//		} catch (Exception e) {
	//			return null;
	//		}
	//
	//		return null;
	//	}

	//	public String obtenerDetallePago(DetallePago objDetallePago){
	//
	//		try {
	//			List<String> listaCadena = new ArrayList<String>(); 
	//
	//			if(objDetallePago.getIdTipoMoneda()!=null && objDetallePago.getIdPago().getIdPago() >0) {
	//				//Validar que no exista la combinacion del, negocio, tipo de fee y el nombre de ese cargo.
	//				Integer pagoEnCuotas = (objDetallePago.getPagoEnCuotas()) ? 1 : 0; 
	//
	//				String codigoDetallePago = objDetallePago.getIdTipoMoneda()+""+pagoEnCuotas+objDetallePago.getIdPago().getIdPago();
	//
	//				codigoDetallePago = codigoDetallePago.trim();
	//
	//				codigoDetallePago = codigoDetallePago.replace("\\s", "").replace(" ", "");
	//
	//				codigoDetallePago = codigoDetallePago.toLowerCase();
	//
	//				listaCadena.add(codigoDetallePago);
	//
	//				//				codigoProductoFeeComision = zapalaClienteRest.generarCodigoByNumeroByEncodear(new GeneraCodigo(codigoProductoFeeComision, encriptar)).getData().getCodigoGenerado();
	//				return zapalaClienteRest.generarCodigoByNumeroByEncodear(new ZapalaRequest(listaCadena)).getCodigoGenerado();
	//
	//				//				return codigoDetallePago;
	//			}
	//		} catch (Exception e) {
	//			return null;
	//		}
	//		return null;
	//	}

	//	public Timestamp convertirStrFechaConFormatToTimestamp(String sFecha, String formato){
	//
	//		List<String> listaCadena = new ArrayList<String>(); 
	//
	//		try {
	//
	//			listaCadena.add(sFecha);
	//
	//			listaCadena.add(formato);
	//
	//			return  zapalaClienteRest.convertirStrFechaConFormatToTimestamp(new ZapalaRequest(listaCadena)).getTiempoStrtoTimeStamp();
	//
	//		} catch (Exception e) {
	//			return null;
	//		}
	//	}



	//	private static <T> String doConvertirObjectToJson(Object objeto) throws LogicaImplException
	//	{
	//		try
	//		{
	//			return new ObjectMapper().writeValueAsString(objeto);
	//		}
	//		catch (Exception ex)
	//		{
	//
	//			throw new LogicaImplException(ex);
	//		}
	//	}

} 
