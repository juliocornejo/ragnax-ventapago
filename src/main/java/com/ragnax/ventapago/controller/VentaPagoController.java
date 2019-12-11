package com.ragnax.ventapago.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

import com.ragnax.ventapago.controller.response.RagnaxError;
import com.ragnax.ventapago.entidad.CanalPago;
import com.ragnax.ventapago.entidad.DetallePago;
import com.ragnax.ventapago.entidad.MedioPago;
import com.ragnax.ventapago.entidad.Negocio;
import com.ragnax.ventapago.entidad.Pago;
import com.ragnax.ventapago.entidad.TipoMedioPago;
import com.ragnax.ventapago.entidad.TipoStatusNegocio;
import com.ragnax.ventapago.exception.LogicaImplException;
import com.ragnax.ventapago.servicio.VentaPagoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

@RestController
@RequestMapping
public class VentaPagoController {

	@Autowired
	VentaPagoService factoryVentaPagoService;
	
	@GetMapping(value = "${servicio.app.uri.limpiarCache}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void clearAllCaches() {
		factoryVentaPagoService.limpiarCacheLocal();
	}

	/***************************************************/
	/*************** CanalPago *** *******************/
	/***************************************************/
	@ApiOperation(value = "Crear Canal de Pago", response = CanalPago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = CanalPago.class)
	})
	@PostMapping(value = "${servicio.app.uri.crearCanalPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CanalPago>  crearCanalPago(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid CanalPago objCanalPago, @ApiIgnore Errors errors)  throws LogicaImplException{
		
		return new ResponseEntity<>(factoryVentaPagoService.crearCanalPago(
				objCanalPago).getCanalPago(), HttpStatus.OK);
	}

	@ApiOperation(value = "Actualizar Canal de Pago", response = CanalPago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = CanalPago.class)
	})
	@PutMapping(value = "${servicio.app.uri.actualizarCanalPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CanalPago>  actualizarCanalPago(
			HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid CanalPago objCanalPago,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.actualizarCanalPago(
				Integer.parseInt(id), objCanalPago).getCanalPago(), HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar Canal de Pago", response = CanalPago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = CanalPago.class)
	})
	@GetMapping(value = "${servicio.app.uri.buscarCanalPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CanalPago>  buscarCanalPago(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.buscarCanalPago(
				new CanalPago(Integer.parseInt(id))).getCanalPago(), HttpStatus.OK);

	}

	@ApiOperation(value = "Listar Todos los Canales de Pago", response = CanalPago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = CanalPago.class)
	})

	@GetMapping(value = "${servicio.app.uri.listarTodoCanalPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CanalPago>>  listarTodoCanalPago(HttpServletRequest request)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.listarTodoCanalPago().getListaCanalPago(), HttpStatus.OK);
		
//		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(),factoryVentaPagoService.listarTodoCanalPago(), 
//				UtilidadesVentaPago.generarTiempoDuracion(new Duration(now, new DateTime())),
//				request.getRequestURI()), HttpStatus.OK);

	}
	/***************************************************/
	/*************** TipoMedioPago *********************/
	/***************************************************/
	@ApiOperation(value = "Crear Tipo de Medio de Pago", response = TipoMedioPago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoMedioPago.class)
	})
	@PostMapping(value = "${servicio.app.uri.crearTipoMedioPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoMedioPago>  crearTipoMedioPago(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid TipoMedioPago objTipoMedioPago, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.crearTipoMedioPago(
				objTipoMedioPago).getTipoMedioPago(), HttpStatus.OK);

	}

	@ApiOperation(value = "Actualizar Tipo de Medio de Pago", response = TipoMedioPago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoMedioPago.class)
	})
	@PutMapping(value = "${servicio.app.uri.actualizarTipoMedioPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoMedioPago>  actualizarTipoMedioPago(
			HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid TipoMedioPago objTipoMedioPago,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.actualizarTipoMedioPago(
				Integer.parseInt(id), objTipoMedioPago).getTipoMedioPago(), HttpStatus.OK);

	}

	@ApiOperation(value = "Listar Todos los Tipos de Medio de Pago", response = TipoMedioPago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoMedioPago.class)
	})

	@GetMapping(value = "${servicio.app.uri.listarTodoTipoMedioPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipoMedioPago>>  listarTodoTipoMedioPago(HttpServletRequest request)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.listarTodoTipoMedioPago().getListaTipoMedioPago(), HttpStatus.OK);

	}
	
	/***************************************************/
	/*************** TipoStatusNegocio *********************/
	/***************************************************/
	@ApiOperation(value = "Crear Tipo de Status de Negocio", response = TipoStatusNegocio.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoStatusNegocio.class)
	})
	@PostMapping(value = "${servicio.app.uri.crearTipoStatusNegocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoStatusNegocio>  crearTipoStatusNegocio(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid TipoStatusNegocio objTipoStatusNegocio, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.crearTipoStatusNegocio(
				objTipoStatusNegocio).getTipoStatusNegocio(), HttpStatus.OK);

	}

	@ApiOperation(value = "Actualizar Tipo de Status de Negocio", response = TipoStatusNegocio.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoStatusNegocio.class)
	})
	@PutMapping(value = "${servicio.app.uri.actualizarTipoStatusNegocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoStatusNegocio>  actualizarTipoStatusNegocio(
			HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid TipoStatusNegocio objTipoStatusNegocio,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.actualizarTipoStatusNegocio(
				Integer.parseInt(id), objTipoStatusNegocio).getTipoStatusNegocio(), HttpStatus.OK);

	}

	@ApiOperation(value = "Buscar Tipo de Status de Negocio", response = TipoStatusNegocio.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoStatusNegocio.class)
	})
	@GetMapping(value = "${servicio.app.uri.buscarTipoStatusNegocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoStatusNegocio>  buscarTipoStatusNegocio(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.buscarTipoStatusNegocio(
				new TipoStatusNegocio(Integer.parseInt(id))).getTipoStatusNegocio(), HttpStatus.OK);

	}
	
	@ApiOperation(value = "Listar Todos los Tipo de Status de Negocio", response = TipoStatusNegocio.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoStatusNegocio.class)
	})

	@GetMapping(value = "${servicio.app.uri.listarTodoTipoStatusNegocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipoStatusNegocio>>  listarTodoTipoStatusNegocio(HttpServletRequest request)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.listarTodoTipoStatusNegocio().getListaTipoStatusNegocio(), HttpStatus.OK);

	}
	
	/****************************************************/
	/*************** MedioPago MedioPago ****************/
	/****************************************************/
	@ApiOperation(value = "Crear Medio de Pago", response = MedioPago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = MedioPago.class)
	})
	@PostMapping(value = "${servicio.app.uri.crearMedioPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MedioPago>  crearMedioPago(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid MedioPago objMedioPago, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.crearMedioPago(
				objMedioPago).getMedioPago(), HttpStatus.OK);

	}

	@ApiOperation(value = "Actualizar Medio de Pago", response = MedioPago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = MedioPago.class)
	})
	@PutMapping(value = "${servicio.app.uri.actualizarMedioPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MedioPago>  actualizarMedioPago(
			HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid MedioPago objMedioPago,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String codigoMedioPago, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.actualizarMedioPago(
				codigoMedioPago, objMedioPago).getMedioPago(), HttpStatus.OK);
		
	}

	@ApiOperation(value = "Buscar Medio de Pago", response = MedioPago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = MedioPago.class)
	})
	@GetMapping(value = "${servicio.app.uri.buscarMedioPagoxCodigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MedioPago>  buscarMedioPagoxCodigo(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.buscarMedioPagoxCodigo(
				new MedioPago(id)).getMedioPago(), HttpStatus.OK);

	}

	@ApiOperation(value = "Listar Medio de Pago", response = MedioPago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = MedioPago.class)
	})

	@GetMapping(value = "${servicio.app.uri.listarTodoMedioPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedioPago>>  listarTodoMedioPago(HttpServletRequest request)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.listarTodoMedioPago().getListaMedioPago(), HttpStatus.OK);

	}
	
	/***************************************************/
	/*************** Negocio Negocio   *****************/
	/***************************************************/
	@ApiOperation(value = "Generar Codigo Negocio", response = Negocio.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Negocio.class)
	})
	@PostMapping(value = "${servicio.app.uri.generarCodigoNegocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Negocio>  generarCodigoNegocio(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid Negocio objNegocio, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.generarCodigoNegocio(
				objNegocio).getNegocio(), HttpStatus.OK);

	}
	
	@ApiOperation(value = "Crear Negocio", response = Negocio.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Negocio.class)
	})
	@PostMapping(value = "${servicio.app.uri.crearNegocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Negocio>  crearNegocio(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid Negocio objNegocio, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.crearNegocio(
				objNegocio).getNegocio(), HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar Negocio por Codigo", response = Negocio.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Negocio.class)
	})
	@GetMapping(value = "${servicio.app.uri.buscarNegocioxCodigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Negocio>  buscarNegocioxCodigo(HttpServletRequest request,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String codigonegocio)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.buscarNegocioxCodigo(
				new Negocio(codigonegocio)).getNegocio(), HttpStatus.OK);
	}

	@ApiOperation(value = "Listar Todos los Negocios", response = Negocio.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Negocio.class)
	})

	@GetMapping(value = "${servicio.app.uri.listarTodoNegocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Negocio>>  listarTodoNegocio(HttpServletRequest request)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.listarTodoNegocio().getListaNegocio(), HttpStatus.OK);

	}
	
	@ApiOperation(value = "Listar Negocio por Portal entre fechas", response = Negocio.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Negocio.class)
	})

	@GetMapping(value = "${servicio.app.uri.listarNegocioxPaisPortalEntreFechas}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Negocio>>  listarNegocioxPaisPortalEntreFechas(HttpServletRequest request
			,  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String codigopaisportal
			,  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String fechainicial
			,  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String fechafinal)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.listarNegocioxPaisPortalEntreFechas(
				codigopaisportal, fechainicial, fechafinal).getListaNegocio(), HttpStatus.OK);

	}
	
	/***************************************************/
	/*************** Pago Pago Pago ********************/
	/***************************************************/
	@ApiOperation(value = "Crear Pago", response = Pago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Pago.class)
	})
	@PostMapping(value = "${servicio.app.uri.crearPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pago>  crearPago(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid Pago objPago, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.crearPago(
				objPago).getPago(), HttpStatus.OK);
	}

	@ApiOperation(value = "Actualizar Pago", response = Pago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Pago.class)
	})
	@PutMapping(value = "${servicio.app.uri.actualizarEstadoPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pago>  actualizarPago(
			HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid Pago objPago,  
			@ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.actualizarEstadoPago(
				objPago).getPago(), HttpStatus.OK);

	}
	
	@ApiOperation(value = "Buscar Pago por codigo de negocio", response = Pago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Pago.class)
	})
	@GetMapping(value = "${servicio.app.uri.buscarPagoxCodigoNegocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pago>  buscarPagoxNegocio(HttpServletRequest request,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String codigonegocio)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.buscarPagoxCodigoNegocio(
				new Pago(new Negocio(codigonegocio))).getPago(), HttpStatus.OK);
		
	}

	@ApiOperation(value = "Listar Todos los Pagos", response = Pago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Pago.class)
	})

	@GetMapping(value = "${servicio.app.uri.listarTodoPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pago>>  listarTodoPago(HttpServletRequest request)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.listarTodoPago().getListaPago(), HttpStatus.OK);


	}
	
	/***************************************************/
	/*************** DetallePago DetallePago DetallePago ********************/
	/***************************************************/
	@ApiOperation(value = "Generar Codigo Detalle de Pago", response = DetallePago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = DetallePago.class)
	})
	@PostMapping(value = "${servicio.app.uri.generarCodigoDetallePago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DetallePago>  generarCodigoDetallePago(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid DetallePago objDetallePago, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.generarCodigoDetallePago(
				objDetallePago).getDetallePago(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Crear Detalle de Pago", response = DetallePago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = DetallePago.class)
	})
	@PostMapping(value = "${servicio.app.uri.crearDetallePago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DetallePago>  crearDetallePago(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid DetallePago objDetallePago, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.crearDetallePago(
				objDetallePago).getDetallePago(), HttpStatus.OK);
		
	}

	@ApiOperation(value = "Listar Todo Detalle de Pago", response = DetallePago.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = DetallePago.class)
	})

	@GetMapping(value = "${servicio.app.uri.listarTodoDetallePago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DetallePago>>  listarTodoDetallePago(HttpServletRequest request)  throws LogicaImplException{

		return new ResponseEntity<>(factoryVentaPagoService.listarTodoDetallePago().getListaDetallePago(), HttpStatus.OK);



	}	
}
