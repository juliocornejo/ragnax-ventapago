package com.ragnax.ventapago;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ragnax.ventapago.entidad.AjusteCantidadTotal;
import com.ragnax.ventapago.entidad.CanalPago;
import com.ragnax.ventapago.entidad.DetallePagoCuotas;
import com.ragnax.ventapago.entidad.DetallePago;
import com.ragnax.ventapago.entidad.MedioPago;
import com.ragnax.ventapago.entidad.Negocio;
import com.ragnax.ventapago.entidad.Pago;
import com.ragnax.ventapago.entidad.StatusNegocio;
import com.ragnax.ventapago.entidad.TipoMedioPago;
import com.ragnax.ventapago.entidad.TipoStatusNegocio;
import com.ragnax.ventapago.exception.LogicaImplException;
import com.ragnax.ventapago.servicio.FactoryVentaPagoService;

import vijnana.utilidades.component.utilidades.AppDate;
import vijnana.utilidades.component.utilidades.TipoFormatoFecha;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RagnaxVentapagoApplication.class)
public class CrearDataVentaPagoTests {
	
	@Autowired
    private FactoryVentaPagoService factoryVentaPagoService;
	
	@Test
	public void crearVentaPagoEntities() throws LogicaImplException {
		crearCanalPagoTest();
		crearTipoMedioPagoTest();
		crearTipoStatusNegocioTest();
		crearMedioPagoTest();
		crearNegocioTest();
		crearPagoTest();
		crearAjusteCantidadTotalTest();
		crearDetallePagoTest();
		crearDetallePagoCuotasTest();
		crearStatusNegocioTest();
		
	}
	
	
	public void crearCanalPagoTest() throws LogicaImplException {
		
		CanalPago[] arreglo = new CanalPago[4]; 

		int i=0;

		arreglo[i] =  new CanalPago(null, "presencial"); i = i+1;
		arreglo[i] =  new CanalPago(null, "telefonico"); i = i+1;
		arreglo[i] =  new CanalPago(null, "online"); i = i+1;
		arreglo[i] =  new CanalPago(null, "movil");
		
		for(int j=0; j< arreglo.length; j++){
			if(arreglo[j]!=null){
				try{
					factoryVentaPagoService.crearCanalPago(arreglo[j]);
				}catch(Exception e){
					System.out.println("rechazo["+j+"] ->"+arreglo[j].getNombreCanalPago());
					e.printStackTrace();
				}
			}
		}
	}
	
	public void crearTipoMedioPagoTest() throws LogicaImplException {
		
		TipoMedioPago[] arreglo = new TipoMedioPago[3]; 

		int i=0;

		arreglo[i] = new TipoMedioPago(null, "efectivo"); i = i+1;
		arreglo[i] = new TipoMedioPago(null, "credito"); i = i+1;
		arreglo[i] = new TipoMedioPago(null, "debito");
		
		for(int j=0; j< arreglo.length; j++){
			if(arreglo[j]!=null){
				try{
					factoryVentaPagoService.crearTipoMedioPago(arreglo[j]);
				}catch(Exception e){
					System.out.println("rechazo["+j+"] ->"+arreglo[j].getNombreTipoMedioPago());
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void crearTipoStatusNegocioTest() throws LogicaImplException {
		
		TipoStatusNegocio[] arreglo = new TipoStatusNegocio[4]; 

		int i=0;

		arreglo[i] = new TipoStatusNegocio(null, "activo"); i = i+1;
		arreglo[i] = new TipoStatusNegocio(null, "enProceso"); i = i+1;
		arreglo[i] = new TipoStatusNegocio(null, "finalizado"); i = i+1;
		arreglo[i] = new TipoStatusNegocio(null, "terminado");
		
		for(int j=0; j< arreglo.length; j++){
			if(arreglo[j]!=null){
				try{
					factoryVentaPagoService.crearTipoStatusNegocio(arreglo[j]);
				}catch(Exception e){
					System.out.println("rechazo["+j+"] ->"+arreglo[j].getNombreTipoStatusNegocio());
					e.printStackTrace();
				}
			}
		}

	}
	
	public void crearMedioPagoTest() throws LogicaImplException {

		MedioPago[] arreglo = new MedioPago[3]; 

		int i=0;

		arreglo[i] = new MedioPago( "MNC", "moneda nacional", false, new TipoMedioPago(1)); i = i+1;

		arreglo[i] =  new MedioPago( "AMX", "american express", false, new TipoMedioPago(2)); i = i+1;

		arreglo[i] =  new MedioPago( "CTD", "tarjeta bancaria", false, new TipoMedioPago(3));

		System.out.println("mediopago[i]:"+arreglo.length);

		for(int j=0; j< arreglo.length; j++){
			if(arreglo[j]!=null){
				try{
					factoryVentaPagoService.crearMedioPago(arreglo[j]);
				}catch(Exception e){
					System.out.println("rechazo["+j+"] ->"+arreglo[j].getCodigoMedioPago());
					e.printStackTrace();
				}
			}
		}
	}
	
	public void crearNegocioTest() throws LogicaImplException {

		try {

			Negocio negocioA = new Negocio(1, true, false, "1", 1, 1, "key del producto almacenado en la base de datos", new CanalPago(1));

			Negocio negocioB = new Negocio(1, true, false, "1", 2, 2, "key del producto almacenado en la base de datos", new CanalPago(4));

			Negocio negocioC = new Negocio(1, true, false, "1", 3, 3, "key del producto almacenado en la base de datos", new CanalPago(3));

			Negocio negocioD = new Negocio(1, true, false, "1", 1, 4, "key del producto almacenado en la base de datos", new CanalPago(2));

			Negocio negocioE = new Negocio(1, true, false, "1", 2, 5, "key del producto almacenado en la base de datos", new CanalPago(1));

			negocioA = factoryVentaPagoService.generarCodigoNegocio(negocioE).getNegocio();

			negocioB = factoryVentaPagoService.generarCodigoNegocio(negocioB).getNegocio();

			negocioC = factoryVentaPagoService.generarCodigoNegocio(negocioC).getNegocio();

			negocioD = factoryVentaPagoService.generarCodigoNegocio(negocioD).getNegocio();

			negocioE = factoryVentaPagoService.generarCodigoNegocio(negocioE).getNegocio();

			factoryVentaPagoService.crearNegocio(negocioA);

			factoryVentaPagoService.crearNegocio(negocioB);

			factoryVentaPagoService.crearNegocio(negocioC);

			factoryVentaPagoService.crearNegocio(negocioD);

			factoryVentaPagoService.crearNegocio(negocioE);

			List<Negocio> listaNegocio = factoryVentaPagoService.listarTodoNegocio().getListaNegocio();
			System.out.println(listaNegocio);

			Date fechaInicial = AppDate.crearDateConFormatYYYY_MM_DDTHH_MM_SS(2019, 10, 1, 0, 0, 1);
			Date fechaFinal = AppDate.crearDateConFormatYYYY_MM_DDTHH_MM_SS(2019, 12, 31, 0, 0, 1);

			String sfechaInicial = AppDate.obtenerFechaEnFormato(fechaInicial, TipoFormatoFecha.YYYY_MM_ddTHH_MM_SSZ);
			String sfechaFinal = AppDate.obtenerFechaEnFormato(fechaFinal, TipoFormatoFecha.YYYY_MM_ddTHH_MM_SSZ);

			listaNegocio = factoryVentaPagoService.listarNegocioxPaisPortalEntreFechas(1, sfechaInicial, sfechaFinal).getListaNegocio();
			System.out.println(listaNegocio);

		}catch(Exception e) {

		}

	}
	
	public void crearPagoTest() throws LogicaImplException {
		try {
		Pago[] arreglo = new Pago[5]; 

		List<Negocio> listaNegocio = factoryVentaPagoService.listarTodoNegocio().getListaNegocio();
		
		int i=0;
		
		arreglo[i] = new Pago(listaNegocio.get(0), false); i = i+1;
		
		arreglo[i] = new Pago(listaNegocio.get(1), false); i = i+1;
		
		arreglo[i] = new Pago(listaNegocio.get(2), false); i = i+1;
		
		arreglo[i] = new Pago(listaNegocio.get(3), false); i = i+1;
		
		for(int j=0; j< arreglo.length; j++){
			if(arreglo[j]!=null){
				try{
					factoryVentaPagoService.crearPago(arreglo[j]);
				}catch(Exception e){
					System.out.println("rechazo["+j+"] ->"+arreglo[j].getIdNegocio().getCodigoNegocio());
					e.printStackTrace();
				}
			}
		}
		
		Pago actPago = factoryVentaPagoService.actualizarEstadoPago(new Pago(listaNegocio.get(0),true)).getPago();
		System.out.println(actPago);
		
		Pago busPago = factoryVentaPagoService.buscarPagoxCodigoNegocio(new Pago(listaNegocio.get(0))).getPago();
		System.out.println(busPago);
		
		Date fechaInicial = AppDate.crearDateConFormatYYYY_MM_DDTHH_MM_SS(2019, 10, 1, 0, 0, 1);
		Date fechaFinal = AppDate.crearDateConFormatYYYY_MM_DDTHH_MM_SS(2019, 12, 31, 0, 0, 1);

		String sfechaInicial = AppDate.obtenerFechaEnFormato(fechaInicial, TipoFormatoFecha.YYYY_MM_ddTHH_MM_SSZ);
		String sfechaFinal = AppDate.obtenerFechaEnFormato(fechaFinal, TipoFormatoFecha.YYYY_MM_ddTHH_MM_SSZ);
		
		List<Pago> listaPago = factoryVentaPagoService.listarPagoEntreFecha(listaNegocio.get(0).getIdPaisPortal(), sfechaInicial, sfechaFinal).getListaPago();
		System.out.println(listaPago);

	}catch(Exception e) {

	}
		
	}
	
	public void crearAjusteCantidadTotalTest() throws LogicaImplException {

		try {
			AjusteCantidadTotal[] arreglo = new AjusteCantidadTotal[5]; 

			List<Pago> listaPago = factoryVentaPagoService.listarTodoPago().getListaPago();

			int i=0;
			
			BigDecimal uno = new BigDecimal(1000);
			uno = uno.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			BigDecimal dos = new BigDecimal(2000);
			dos = dos.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			BigDecimal tres = new BigDecimal(3000);
			tres = tres.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			BigDecimal cuatro = new BigDecimal(4000);
			cuatro = cuatro.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			arreglo[i] = new AjusteCantidadTotal(listaPago.get(0), 1, uno, uno, uno, new BigDecimal(25.65), 
					1, uno, uno, 1, new BigDecimal(25.65), uno, new BigDecimal(25.65), uno); i = i+1;
					
			arreglo[i] = new AjusteCantidadTotal(listaPago.get(1), 2, dos, dos, dos, new BigDecimal(15.88),
					2, dos, dos, 2, new BigDecimal(15.88), dos, new BigDecimal(15.88), dos); i = i+1;

			arreglo[i] = new AjusteCantidadTotal(listaPago.get(2), 3, tres, tres, tres, new BigDecimal(36.52), 
					3, tres, tres, 3, new BigDecimal(36.52), tres, new BigDecimal(36.52), tres); i = i+1;

			arreglo[i] = new AjusteCantidadTotal(listaPago.get(3), 4, cuatro, cuatro, cuatro, new BigDecimal(4.4), 
					3, cuatro, cuatro, 3, new BigDecimal(4.4), cuatro, new BigDecimal(4.4),  cuatro); i = i+1;

			

			for(int j=0; j< arreglo.length; j++){
				if(arreglo[j]!=null){
					try{
						factoryVentaPagoService.crearAjusteCantidadTotal(arreglo[j]);
					}catch(Exception e){
						System.out.println("rechazo["+j+"] ->"+arreglo[j].getIdPago().getIdPago());
						e.printStackTrace();
					}
				}
			}
		}catch(Exception e) {

		}
	}
	
	public void crearDetallePagoTest() throws LogicaImplException {
		try {
		
		List<Pago> listaPago = factoryVentaPagoService.listarTodoPago().getListaPago();
		
		int i=0;
		
		DetallePago detallePagoA = new DetallePago(1, new MedioPago("AMX"), new BigDecimal(1000.01), true, listaPago.get(0)); i = i+1;
		
		DetallePago detallePagoB = new DetallePago(2, new MedioPago("CTD"), new BigDecimal(2000.02), true, listaPago.get(1)); i = i+1;
		
		DetallePago detallePagoC = new DetallePago(3, new MedioPago("AMX"), new BigDecimal(3000.13), true, listaPago.get(2)); i = i+1;
		
		DetallePago detallePagoD = new DetallePago(4, new MedioPago("MNC"), new BigDecimal(3000.56), true, listaPago.get(3)); i = i+1;
		
		detallePagoA = factoryVentaPagoService.generarCodigoDetallePago(detallePagoA).getDetallePago();
		
		detallePagoB = factoryVentaPagoService.generarCodigoDetallePago(detallePagoB).getDetallePago();
		
		detallePagoC = factoryVentaPagoService.generarCodigoDetallePago(detallePagoC).getDetallePago();
		
		detallePagoD = factoryVentaPagoService.generarCodigoDetallePago(detallePagoD).getDetallePago();
		
		System.out.println("--------------------------");
		
		detallePagoA = factoryVentaPagoService.crearDetallePago(detallePagoA).getDetallePago();
		
		detallePagoB = factoryVentaPagoService.crearDetallePago(detallePagoB).getDetallePago();
		
		detallePagoC = factoryVentaPagoService.crearDetallePago(detallePagoC).getDetallePago();
		
		detallePagoD = factoryVentaPagoService.crearDetallePago(detallePagoD).getDetallePago();
		
		List<DetallePago> listaDetallePago = factoryVentaPagoService.listarDetallePagoxPago(new DetallePago(listaPago.get(0))).getListaDetallePago();
		System.out.println(listaDetallePago);
		
		}catch(Exception e) {

		}
	}
	
	//Relacion 0 a uno con detallePago
	public void crearDetallePagoCuotasTest() throws LogicaImplException {
		try {
		DetallePagoCuotas[] arreglo = new DetallePagoCuotas[5]; 
		
		List<DetallePago> listaDetallePago = factoryVentaPagoService.listarTodoDetallePago().getListaDetallePago();
		
		int i=0;
		
		//Tercer, cuarto y 6 no pueden ser mayor a 99.99;
		arreglo[i] = new DetallePagoCuotas(listaDetallePago.get(0), 3, new BigDecimal(0.15), new BigDecimal(10.34), new BigDecimal(658.99),
				new BigDecimal(31.99), new BigDecimal(45896.99)); i = i+1;
		
		arreglo[i] = new DetallePagoCuotas(listaDetallePago.get(1), 6, new BigDecimal(1.26), new BigDecimal(21.15), new BigDecimal(658.99),
				new BigDecimal(31.99), new BigDecimal(35621.99)); i = i+1;
		
		arreglo[i] = new DetallePagoCuotas(listaDetallePago.get(2), 7, new BigDecimal(12.69), new BigDecimal(17.18), new BigDecimal(658.99),
				new BigDecimal(31.99), new BigDecimal(74563.99)); i = i+1;
		
		arreglo[i] = new DetallePagoCuotas(listaDetallePago.get(3), 9, new BigDecimal(14.90), new BigDecimal(22.65), new BigDecimal(658.99),
				new BigDecimal(31.99), new BigDecimal(75698.99)); i = i+1;
		
		for(int j=0; j< arreglo.length; j++){
			if(arreglo[j]!=null){
				try{
					factoryVentaPagoService.crearDetallePagoCuotas(arreglo[j]);
				}catch(Exception e){
					System.out.println("rechazo["+j+"] ->"+arreglo[j].getIdDetallePago().getCodigoDetallePago());
					e.printStackTrace();
				}
			}
		}
		
		DetallePagoCuotas detallePagoCuotasA = factoryVentaPagoService.buscarDetallePagoCuotasxCodigoDetallePago(new DetallePagoCuotas(listaDetallePago.get(0))).getDetallePagoCuotas();
		
		System.out.println(detallePagoCuotasA);
		
		}catch(Exception e) {

		}
	}
	
	public void crearStatusNegocioTest() throws LogicaImplException {

		try {
						
			List<Negocio> listaNegocio = factoryVentaPagoService.listarTodoNegocio().getListaNegocio();
			
			StatusNegocio statusNegocioA = new StatusNegocio(listaNegocio.get(0));
			
			StatusNegocio statusNegocioB = new StatusNegocio(listaNegocio.get(1));
			
			StatusNegocio statusNegocioC = new StatusNegocio(listaNegocio.get(2));
			
			StatusNegocio statusNegocioD = new StatusNegocio(listaNegocio.get(3));
			
			factoryVentaPagoService.crearStatusNegocio(statusNegocioA);

			factoryVentaPagoService.crearStatusNegocio(statusNegocioB);

			factoryVentaPagoService.crearStatusNegocio(statusNegocioC);

			factoryVentaPagoService.crearStatusNegocio(statusNegocioD);
			
			statusNegocioA = new StatusNegocio(listaNegocio.get(0));
			
			statusNegocioB = new StatusNegocio(listaNegocio.get(1));
			
			statusNegocioC = new StatusNegocio(listaNegocio.get(2));
			
			statusNegocioD = new StatusNegocio(listaNegocio.get(3));

			factoryVentaPagoService.crearStatusNegocio(statusNegocioA);

			factoryVentaPagoService.crearStatusNegocio(statusNegocioB);

			factoryVentaPagoService.crearStatusNegocio(statusNegocioC);

			factoryVentaPagoService.crearStatusNegocio(statusNegocioD);
			
			statusNegocioA = new StatusNegocio(listaNegocio.get(0));
			
			statusNegocioB = new StatusNegocio(listaNegocio.get(1));
			
			statusNegocioC = new StatusNegocio(listaNegocio.get(2));
			
			statusNegocioD = new StatusNegocio(listaNegocio.get(3));

			List<StatusNegocio> listaStatusNegocio = factoryVentaPagoService.listarStatusNegocioxNegocio(new StatusNegocio(listaNegocio.get(0))).getListaStatusNegocio();
			
			System.out.println(listaStatusNegocio);

		}catch(Exception e) {

		}
	}
}
