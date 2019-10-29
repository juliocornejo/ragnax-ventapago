package com.ragnax.ventapago.controller;

import java.time.Duration;
import java.time.Instant;

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

import com.ragnax.ventapago.controller.response.Response;
import com.ragnax.ventapago.entidad.CanalPago;
import com.ragnax.ventapago.entidad.DetallePago;
import com.ragnax.ventapago.entidad.MedioPago;
import com.ragnax.ventapago.entidad.Negocio;
import com.ragnax.ventapago.entidad.Pago;
import com.ragnax.ventapago.entidad.TipoMedioPago;
import com.ragnax.ventapago.entidad.TipoStatusNegocio;
import com.ragnax.ventapago.exception.LogicaImplException;
import com.ragnax.ventapago.servicio.FactoryVentaPagoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import vijnana.utilidades.component.utilidades.AppDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = { "${url.app.controller}" })
public class VentaPagoController {

	@Autowired
	FactoryVentaPagoService factoryVentaPagoService;
	
	@GetMapping(value = "${url.app.servicio.limpiarCache}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void clearAllCaches() {
		factoryVentaPagoService.limpiarCacheLocal();
	}

	/***************************************************/
	/*************** CanalPago *** *******************/
	/***************************************************/
	@ApiOperation(value = "Crear Canal de Pago", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de creacion", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@PostMapping(value = "${url.app.servicio.crearCanalPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  crearCanalPago(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid CanalPago objCanalPago, @ApiIgnore Errors errors)  throws LogicaImplException{

		Instant start = Instant.now();
		
		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.crearCanalPago(
				objCanalPago), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())), 
				request.getRequestURI()), HttpStatus.OK);
	}

	@ApiOperation(value = "Actualizar Canal de Pago", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de actualizacion", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@PutMapping(value = "${url.app.servicio.actualizarCanalPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  actualizarCanalPago(
			HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid CanalPago objCanalPago,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.actualizarCanalPago(
				Integer.parseInt(id), objCanalPago), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())), 
				request.getRequestURI()), HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar Canal de Pago", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de busqueda", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@GetMapping(value = "${url.app.servicio.buscarCanalPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  buscarCanalPago(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.buscarCanalPago(
				new CanalPago(Integer.parseInt(id))), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())),
				request.getRequestURI()), HttpStatus.OK);
	}

	@ApiOperation(value = "Listar Todos los Canales de Pago", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de busqueda", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})

	@GetMapping(value = "${url.app.servicio.listarTodoCanalPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  listarTodoCanalPago(HttpServletRequest request)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(),factoryVentaPagoService.listarTodoCanalPago(), 
				AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())),
				request.getRequestURI()), HttpStatus.OK);

	}
	/***************************************************/
	/*************** TipoMedioPago *********************/
	/***************************************************/
	@ApiOperation(value = "Crear Tipo de Medio de Pago", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de creacion", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@PostMapping(value = "${url.app.servicio.crearTipoMedioPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  crearTipoMedioPago(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid TipoMedioPago objTipoMedioPago, @ApiIgnore Errors errors)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.crearTipoMedioPago(
				objTipoMedioPago), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())), 
				request.getRequestURI()), HttpStatus.OK);
	}

	@ApiOperation(value = "Actualizar Tipo de Medio de Pago", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de actualizacion", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@PutMapping(value = "${url.app.servicio.actualizarTipoMedioPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  actualizarTipoMedioPago(
			HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid TipoMedioPago objTipoMedioPago,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.actualizarTipoMedioPago(
				Integer.parseInt(id), objTipoMedioPago), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())), 
				request.getRequestURI()), HttpStatus.OK);
	}

	@ApiOperation(value = "Listar Todos los Tipos de Medio de Pago", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de busqueda", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})

	@GetMapping(value = "${url.app.servicio.listarTodoTipoMedioPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  listarTodoTipoMedioPago(HttpServletRequest request)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(),factoryVentaPagoService.listarTodoTipoMedioPago(),
				AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())),
				request.getRequestURI()), HttpStatus.OK);
	}
	
	/***************************************************/
	/*************** TipoStatusNegocio *********************/
	/***************************************************/
	@ApiOperation(value = "Crear Tipo de Status de Negocio", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de creacion", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@PostMapping(value = "${url.app.servicio.crearTipoStatusNegocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  crearTipoStatusNegocio(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid TipoStatusNegocio objTipoStatusNegocio, @ApiIgnore Errors errors)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.crearTipoStatusNegocio(
				objTipoStatusNegocio), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())), 
				request.getRequestURI()), HttpStatus.OK);
	}

	@ApiOperation(value = "Actualizar Tipo de Status de Negocio", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de actualizacion", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@PutMapping(value = "${url.app.servicio.actualizarTipoStatusNegocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  actualizarTipoStatusNegocio(
			HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid TipoStatusNegocio objTipoStatusNegocio,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.actualizarTipoStatusNegocio(
				Integer.parseInt(id), objTipoStatusNegocio), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())), 
				request.getRequestURI()), HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar Tipo de Status de Negocio", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de busqueda", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@GetMapping(value = "${url.app.servicio.buscarTipoStatusNegocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  buscarTipoStatusNegocio(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.buscarTipoStatusNegocio(
				new TipoStatusNegocio(Integer.parseInt(id))), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())),
				request.getRequestURI()), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Listar Todos los Tipo de Status de Negocio", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de busqueda", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})

	@GetMapping(value = "${url.app.servicio.listarTodoTipoStatusNegocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  listarTodoTipoStatusNegocio(HttpServletRequest request)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(),factoryVentaPagoService.listarTodoTipoStatusNegocio(), 
				AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())),
				request.getRequestURI()), HttpStatus.OK);

	}
	
	/****************************************************/
	/*************** MedioPago MedioPago ****************/
	/****************************************************/
	@ApiOperation(value = "Crear Medio de Pago", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de creacion", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@PostMapping(value = "${url.app.servicio.crearMedioPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  crearMedioPago(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid MedioPago objMedioPago, @ApiIgnore Errors errors)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.crearMedioPago(
				objMedioPago), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())), 
				request.getRequestURI()), HttpStatus.OK);
	}

	@ApiOperation(value = "Actualizar Medio de Pago", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de actualizacion", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@PutMapping(value = "${url.app.servicio.actualizarMedioPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  actualizarMedioPago(
			HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid MedioPago objMedioPago,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String codigoMedioPago, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.actualizarMedioPago(
				codigoMedioPago, objMedioPago), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())), 
				request.getRequestURI()), HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar Medio de Pago", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de busqueda", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@GetMapping(value = "${url.app.servicio.buscarMedioPagoxCodigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  buscarMedioPagoxCodigo(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.buscarMedioPagoxCodigo(
				new MedioPago(id)), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())),
				request.getRequestURI()), HttpStatus.OK);
	}

	@ApiOperation(value = "Listar Medio de Pago", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de busqueda", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})

	@GetMapping(value = "${url.app.servicio.listarTodoMedioPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  listarTodoMedioPago(HttpServletRequest request)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(),factoryVentaPagoService.listarTodoMedioPago(), 
				AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())),
				request.getRequestURI()), HttpStatus.OK);

	}
	
	/***************************************************/
	/*************** Negocio Negocio   *****************/
	/***************************************************/
	@ApiOperation(value = "Generar Codigo Negocio", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de creacion", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@PostMapping(value = "${url.app.servicio.generarCodigoNegocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  generarCodigoNegocio(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid Negocio objNegocio, @ApiIgnore Errors errors)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.generarCodigoNegocio(
				objNegocio), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())), 
				request.getRequestURI()), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Crear Negocio", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de creacion", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@PostMapping(value = "${url.app.servicio.crearNegocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  crearNegocio(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid Negocio objNegocio, @ApiIgnore Errors errors)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.crearNegocio(
				objNegocio), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())), 
				request.getRequestURI()), HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar Negocio por Codigo", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de busqueda", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@GetMapping(value = "${url.app.servicio.buscarNegocioxCodigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  buscarNegocioxCodigo(HttpServletRequest request,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String codigonegocio)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.buscarNegocioxCodigo(
				new Negocio(codigonegocio)), 
				AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())),
				request.getRequestURI()), HttpStatus.OK);
	}

	@ApiOperation(value = "Listar Todos los Negocios", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de busqueda", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})

	@GetMapping(value = "${url.app.servicio.listarTodoNegocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  listarTodoNegocio(HttpServletRequest request)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(),factoryVentaPagoService.listarTodoNegocio(), 
				AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())), 
				request.getRequestURI()), HttpStatus.OK);

	}
	
	@ApiOperation(value = "Listar Negocio por Portal entre fechas", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de busqueda", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})

	@GetMapping(value = "${url.app.servicio.listarNegocioxPaisPortalEntreFechas}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  listarNegocioxPaisPortalEntreFechas(HttpServletRequest request
			,  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String idpaisportal
			,  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String fechainicial
			,  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String fechafinal)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(),factoryVentaPagoService.listarNegocioxPaisPortalEntreFechas(
				Integer.parseInt(idpaisportal), fechainicial, fechafinal), 
				AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())), 
				request.getRequestURI()), HttpStatus.OK);

	}
	
	/***************************************************/
	/*************** Pago Pago Pago ********************/
	/***************************************************/
	@ApiOperation(value = "Crear Pago", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de creacion", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@PostMapping(value = "${url.app.servicio.crearPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  crearPago(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid Pago objPago, @ApiIgnore Errors errors)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.crearPago(
				objPago), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())), 
				request.getRequestURI()), HttpStatus.OK);
	}

	@ApiOperation(value = "Actualizar Pago", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de actualizacion", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@PutMapping(value = "${url.app.servicio.actualizarEstadoPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  actualizarPago(
			HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid Pago objPago,  
			@ApiIgnore Errors errors)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.actualizarEstadoPago(
				objPago), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())), 
				request.getRequestURI()), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Buscar Pago por codigo de negocio", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de busqueda", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@GetMapping(value = "${url.app.servicio.buscarPagoxCodigoNegocio}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  buscarPagoxNegocio(HttpServletRequest request,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String codigonegocio)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.buscarPagoxCodigoNegocio(
				new Pago(new Negocio(codigonegocio))), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())),
				request.getRequestURI()), HttpStatus.OK);
	}

	@ApiOperation(value = "Listar Todos los Pagos", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de busqueda", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})

	@GetMapping(value = "${url.app.servicio.listarTodoPago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  listarTodoPago(HttpServletRequest request)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(),factoryVentaPagoService.listarTodoPago(), 
				AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())),
				request.getRequestURI()), HttpStatus.OK);

	}
	
	/***************************************************/
	/*************** DetallePago DetallePago DetallePago ********************/
	/***************************************************/
	@ApiOperation(value = "Generar Codigo Detalle de Pago", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de creacion", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@PostMapping(value = "${url.app.servicio.generarCodigoDetallePago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  generarCodigoDetallePago(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid DetallePago objDetallePago, @ApiIgnore Errors errors)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.generarCodigoDetallePago(
				objDetallePago), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())), 
				request.getRequestURI()), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Crear Detalle de Pago", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de creacion", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})
	@PostMapping(value = "${url.app.servicio.crearDetallePago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  crearDetallePago(HttpServletRequest request,  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid DetallePago objDetallePago, @ApiIgnore Errors errors)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(), factoryVentaPagoService.crearDetallePago(
				objDetallePago), AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())), 
				request.getRequestURI()), HttpStatus.OK);
	}

	@ApiOperation(value = "Listar Todo Detalle de Pago", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de busqueda", response = Response.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = Response.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Response.class)
	})

	@GetMapping(value = "${url.app.servicio.listarTodoDetallePago}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response>  listarTodoDetallePago(HttpServletRequest request)  throws LogicaImplException{

		Instant start = Instant.now();

		return new ResponseEntity<>(new Response(null,  HttpStatus.OK.value(),factoryVentaPagoService.listarTodoDetallePago(), 
				AppDate.generarTiempoDuracion(Duration.between(start, Instant.now())),
				request.getRequestURI()), HttpStatus.OK);

	}
	
	/***************************************************/
	/*************** DetallePago DetallePago DetallePago ********************/
	/***************************************************/

	
}
