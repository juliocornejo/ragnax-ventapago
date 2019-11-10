package com.ragnax.ventapago.servicio;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ragnax.ventapago.controller.response.VentaPago;
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
import com.ragnax.ventapago.repository.FactoryVentaPagoDAO;
import com.ragnax.ventapago.servicio.utilidades.UtilidadesVentaPago;

import vijnana.utilidades.component.utilidades.AppDate;
import vijnana.utilidades.component.utilidades.DateMapper;
import vijnana.utilidades.component.utilidades.TipoFormatoFecha;

@Service
@CacheConfig(cacheNames = { "listarTodoTipoStatusNegocio","listarTodoCanalPago","buscarMedioPagoxCodigo",
		"listarTodoTipoMedioPago", "listarTodoMedioPago","buscarNegocioxCodigo","buscarDetallePagoxCodigo" })
public class FactoryVentaPagoServiceImpl implements FactoryVentaPagoService {
	//Segun se necesite se van creando llamadas al repositorio para devolver entities.
	@Autowired
	private FactoryVentaPagoDAO factoryVentaPagoDAO;
	/******* CanalPago CanalPago CanalPago *****************/
	/******* CanalPago CanalPago CanalPago *****************/
	/******* CanalPago CanalPago CanalPago *****************/
	public VentaPago crearCanalPago(CanalPago objCanalPago) throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {

			Pageable pageByNombreDesc = PageRequest.of(0, 1, Sort.by("nombreCanalPago").descending());

			Page<CanalPago> pageCodigoCanalPago  = factoryVentaPagoDAO.getCanalPagoRepository().findByNombreCanalPago(objCanalPago.getNombreCanalPago(), pageByNombreDesc); 

			if(pageCodigoCanalPago.isEmpty()){
				Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idCanalPago").descending());

				Page<CanalPago> pageIdCanalPago = factoryVentaPagoDAO.getCanalPagoRepository().findAll(pageByidDesc);

				Integer idCanalPago = (!pageIdCanalPago.isEmpty()) ? (Integer) pageIdCanalPago.getContent().get(0).getIdCanalPago() + 1 : 1;

				objCanalPago.setIdCanalPago(idCanalPago);

				factoryVentaPagoDAO.getCanalPagoRepository().save(objCanalPago);

				pageCodigoCanalPago  = factoryVentaPagoDAO.getCanalPagoRepository().findByNombreCanalPago(objCanalPago.getNombreCanalPago(), pageByNombreDesc);

				ventaPago.setCanalPago(pageCodigoCanalPago.getContent().get(0));
			}else {
				throw new LogicaImplException("No se puede crear CanalPago, parametros ya existen en identificador valido");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	public VentaPago actualizarCanalPago(Integer id, CanalPago objCanalPago) throws LogicaImplException {
		VentaPago ventaPago = new VentaPago();
		try {

			CanalPago pagoCanalPago = buscarCanalPago(new CanalPago(id)).getCanalPago();

			Pageable pageByNombreDesc = PageRequest.of(0, 1, Sort.by("nombreCanalPago").descending());

			Page<CanalPago> pageNombreCanalPago  = factoryVentaPagoDAO.getCanalPagoRepository().findByNombreCanalPago(objCanalPago.getNombreCanalPago(), pageByNombreDesc);

			/***Busqueda por nombre existe en un tipoNegocio No existe. o solo existe en el pageCodigoCanalPago.idCanalPago = id 
				//... solo actualizar estado****/
			if((!pageNombreCanalPago.isEmpty() && pageNombreCanalPago.getContent().get(0).getIdCanalPago()==id)
					|| (pagoCanalPago.getIdCanalPago()!=null && pageNombreCanalPago.isEmpty())){
				
				objCanalPago.setIdCanalPago(id);

				factoryVentaPagoDAO.getCanalPagoRepository().save(objCanalPago);

				ventaPago.setCanalPago(objCanalPago);
			}
			else {
				throw new LogicaImplException("No se puede actualizar CanalPago, codigoCanalPago ya existe en un identificador distinto");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	public VentaPago buscarCanalPago(CanalPago objCanalPago) throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			//Como la lista es pequeña, obtener todos desde cache y luego buscar el Id.
			Optional<CanalPago> optPerCanalPago = factoryVentaPagoDAO.getCanalPagoRepository().findById(objCanalPago.getIdCanalPago());

			/***Si existe reemplazar por el nuevo*/
			if(optPerCanalPago!=null && optPerCanalPago.isPresent()){

				ventaPago.setCanalPago(optPerCanalPago.get());

			}else {
				throw new LogicaImplException("No existe CanalPago con identificador:" +objCanalPago.getIdCanalPago());
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return ventaPago;
	}

	@Cacheable(value="listarTodoCanalPago")
	public VentaPago listarTodoCanalPago() throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			List<CanalPago> listaCanalPago = factoryVentaPagoDAO.getCanalPagoRepository().findAll();

			if(listaCanalPago!=null && !listaCanalPago.isEmpty()){
				ventaPago.setListaCanalPago(listaCanalPago);
			}else {
				throw new LogicaImplException("No existe lista de CanalPago");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	/***** TipoMedioPago TipoMedioPago TipoMedioPago **************/
	/***** TipoMedioPago TipoMedioPago TipoMedioPago **************/
	/***** TipoMedioPago TipoMedioPago TipoMedioPago **************/
	public VentaPago crearTipoMedioPago(TipoMedioPago objTipoMedioPago) throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {

			Pageable pageByNombreDesc = PageRequest.of(0, 1, Sort.by("nombreTipoMedioPago").descending());

			Page<TipoMedioPago> pageNombreTipoMedioPago  = factoryVentaPagoDAO.getTipoMedioPagoRepository().findByNombreTipoMedioPago(objTipoMedioPago.getNombreTipoMedioPago(), pageByNombreDesc); 

			if(pageNombreTipoMedioPago.isEmpty()){
				Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idTipoMedioPago").descending());

				Page<TipoMedioPago> pageIdTipoMedioPago = factoryVentaPagoDAO.getTipoMedioPagoRepository().findAll(pageByidDesc);
				
				Integer idTipoMedioPago = (!pageIdTipoMedioPago.isEmpty()) ? (Integer) pageIdTipoMedioPago.getContent().get(0).getIdTipoMedioPago() + 1 : 1;

				objTipoMedioPago.setIdTipoMedioPago(idTipoMedioPago);

				factoryVentaPagoDAO.getTipoMedioPagoRepository().save(objTipoMedioPago);

				pageNombreTipoMedioPago  = factoryVentaPagoDAO.getTipoMedioPagoRepository().findByNombreTipoMedioPago(objTipoMedioPago.getNombreTipoMedioPago(), pageByNombreDesc);

				ventaPago.setTipoMedioPago(pageNombreTipoMedioPago.getContent().get(0));
			}else {
				throw new LogicaImplException("No se puede crear TipoMedioPago, parametros ya existen en identificador valido");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	public VentaPago actualizarTipoMedioPago(Integer id, TipoMedioPago objTipoMedioPago)
			throws LogicaImplException {
		VentaPago ventaPago = new VentaPago();
		try {

			TipoMedioPago pagoTipoMedioPago = buscarTipoMedioPago(new TipoMedioPago(id)).getTipoMedioPago();

			Pageable pageByNombreDesc = PageRequest.of(0, 1, Sort.by("nombreTipoMedioPago").descending());

			Page<TipoMedioPago> pageNombreTipoMedioPago  = factoryVentaPagoDAO.getTipoMedioPagoRepository().findByNombreTipoMedioPago(objTipoMedioPago.getNombreTipoMedioPago(), pageByNombreDesc);

			/***Busqueda por nombre existe en un tipoNegocio No existe. o solo existe en el pageCodigoTipoMedioPago.idTipoMedioPago = id 
				//... solo actualizar estado****/
			if((!pageNombreTipoMedioPago.isEmpty() && pageNombreTipoMedioPago.getContent().get(0).getIdTipoMedioPago()==id)
					|| (pagoTipoMedioPago.getIdTipoMedioPago()!=null && pageNombreTipoMedioPago.isEmpty())){
				objTipoMedioPago.setIdTipoMedioPago(id);

				factoryVentaPagoDAO.getTipoMedioPagoRepository().save(objTipoMedioPago);

				ventaPago.setTipoMedioPago(objTipoMedioPago);
			}
			else {
				throw new LogicaImplException("No se puede actualizar TipoMedioPago, codigoTipoMedioPago ya existe en un identificador distinto");
			}


		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	public VentaPago buscarTipoMedioPago(TipoMedioPago objTipoMedioPago) throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			//Como la lista es pequeña, obtener todos desde cache y luego buscar el Id.
			Optional<TipoMedioPago> optPerTipoMedioPago = factoryVentaPagoDAO.getTipoMedioPagoRepository().findById(objTipoMedioPago.getIdTipoMedioPago());

			/***Si existe reemplazar por el nuevo*/
			if(optPerTipoMedioPago!=null && optPerTipoMedioPago.isPresent()){

				ventaPago.setTipoMedioPago(optPerTipoMedioPago.get());

			}else {
				throw new LogicaImplException("No existe TipoMedioPago con identificador:" +objTipoMedioPago.getIdTipoMedioPago());
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return ventaPago;
	}

	@Cacheable(value="listarTodoTipoMedioPago")
	public VentaPago listarTodoTipoMedioPago() throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			List<TipoMedioPago> listaTipoMedioPago = factoryVentaPagoDAO.getTipoMedioPagoRepository().findAll();

			if(listaTipoMedioPago!=null && !listaTipoMedioPago.isEmpty()){
				ventaPago.setListaTipoMedioPago(listaTipoMedioPago);
			}else {
				throw new LogicaImplException("No existe lista de TipoMedioPago");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	/***** TipoStatusNegocio TipoStatusNegocio TipoStatusNegocio **************/
	/***** TipoStatusNegocio TipoStatusNegocio TipoStatusNegocio **************/
	/***** TipoStatusNegocio TipoStatusNegocio TipoStatusNegocio **************/
	public VentaPago crearTipoStatusNegocio(TipoStatusNegocio objTipoStatusNegocio) throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {

			Pageable pageByNombreDesc = PageRequest.of(0, 1, Sort.by("nombreTipoStatusNegocio").descending());

			Page<TipoStatusNegocio> pageNombreTipoStatusNegocio  = factoryVentaPagoDAO.getTipoStatusNegocioRepository().findByNombreTipoStatusNegocio(objTipoStatusNegocio.getNombreTipoStatusNegocio(), pageByNombreDesc); 

			if(pageNombreTipoStatusNegocio.isEmpty()){
				Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idTipoStatusNegocio").descending());

				Page<TipoStatusNegocio> pageIdTipoStatusNegocio = factoryVentaPagoDAO.getTipoStatusNegocioRepository().findAll(pageByidDesc);

				Integer idTipoStatusNegocio = (!pageIdTipoStatusNegocio.isEmpty()) ? (Integer) pageIdTipoStatusNegocio.getContent().get(0).getIdTipoStatusNegocio() + 1 : 1;

				objTipoStatusNegocio.setIdTipoStatusNegocio(idTipoStatusNegocio);

				factoryVentaPagoDAO.getTipoStatusNegocioRepository().save(objTipoStatusNegocio);

				pageNombreTipoStatusNegocio  = factoryVentaPagoDAO.getTipoStatusNegocioRepository().findByNombreTipoStatusNegocio(objTipoStatusNegocio.getNombreTipoStatusNegocio(), pageByNombreDesc);

				ventaPago.setTipoStatusNegocio(pageNombreTipoStatusNegocio.getContent().get(0));
			}else {
				throw new LogicaImplException("No se puede crear TipoStatusNegocio, parametros ya existen en identificador valido");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	public VentaPago actualizarTipoStatusNegocio(Integer id, TipoStatusNegocio objTipoStatusNegocio)
			throws LogicaImplException {
		VentaPago ventaPago = new VentaPago();
		try {

			TipoStatusNegocio pagoTipoStatusNegocio = buscarTipoStatusNegocio(new TipoStatusNegocio(id)).getTipoStatusNegocio();

			Pageable pageByNombreDesc = PageRequest.of(0, 1, Sort.by("nombreTipoStatusNegocio").descending());

			Page<TipoStatusNegocio> pageNombreTipoStatusNegocio  = factoryVentaPagoDAO.getTipoStatusNegocioRepository().findByNombreTipoStatusNegocio(objTipoStatusNegocio.getNombreTipoStatusNegocio(), pageByNombreDesc);

			/***Busqueda por nombre existe en un tipoNegocio No existe. o solo existe en el pageNombreTipoStatusNegocio.idTipoStatusNegocio = id 
				//... solo actualizar estado****/
			if((!pageNombreTipoStatusNegocio.isEmpty() && pageNombreTipoStatusNegocio.getContent().get(0).getIdTipoStatusNegocio()==id)
					|| (pagoTipoStatusNegocio.getIdTipoStatusNegocio()!=null && pageNombreTipoStatusNegocio.isEmpty())){
				objTipoStatusNegocio.setIdTipoStatusNegocio(id);

				factoryVentaPagoDAO.getTipoStatusNegocioRepository().save(objTipoStatusNegocio);

				ventaPago.setTipoStatusNegocio(objTipoStatusNegocio);
			}
			else {
				throw new LogicaImplException("No se puede actualizar TipoStatusNegocio, codigoTipoStatusNegocio ya existe en un identificador distinto");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	public VentaPago buscarTipoStatusNegocio(TipoStatusNegocio objTipoStatusNegocio) throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			//Como la lista es pequeña, obtener todos desde cache y luego buscar el Id.
			Optional<TipoStatusNegocio> optPerTipoStatusNegocio = factoryVentaPagoDAO.getTipoStatusNegocioRepository().findById(objTipoStatusNegocio.getIdTipoStatusNegocio());

			/***Si existe reemplazar por el nuevo*/
			if(optPerTipoStatusNegocio!=null && optPerTipoStatusNegocio.isPresent()){

				ventaPago.setTipoStatusNegocio(optPerTipoStatusNegocio.get());

			}else {
				throw new LogicaImplException("No existe TipoStatusNegocio con identificador:" +objTipoStatusNegocio.getIdTipoStatusNegocio());
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return ventaPago;
	}

	@Cacheable(value="listarTodoTipoStatusNegocio")
	public VentaPago listarTodoTipoStatusNegocio() throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			List<TipoStatusNegocio> listaTipoStatusNegocio = factoryVentaPagoDAO.getTipoStatusNegocioRepository().findAll();

			if(listaTipoStatusNegocio!=null && !listaTipoStatusNegocio.isEmpty()){
				ventaPago.setListaTipoStatusNegocio(listaTipoStatusNegocio);
			}else {
				throw new LogicaImplException("No existe lista de TipoStatusNegocio");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}


	/***** MedioPago MedioPago MedioPago **************/
	/***** MedioPago MedioPago MedioPago **************/
	/***** MedioPago MedioPago MedioPago **************/
	public VentaPago crearMedioPago(MedioPago objMedioPago) throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {

			Pageable pageByCodigoDesc = PageRequest.of(0, 1, Sort.by("codigoMedioPago").descending());

			Page<MedioPago> pageCodigoMedioPago  = factoryVentaPagoDAO.getMedioPagoRepository().findByCodigoMedioPago(objMedioPago.getCodigoMedioPago(), pageByCodigoDesc); 

			if(pageCodigoMedioPago.isEmpty()){

				objMedioPago.setEstadoMedioPago(true);

				factoryVentaPagoDAO.getMedioPagoRepository().save(objMedioPago);

				pageCodigoMedioPago  = factoryVentaPagoDAO.getMedioPagoRepository().findByCodigoMedioPago(objMedioPago.getCodigoMedioPago(), pageByCodigoDesc);

				ventaPago.setMedioPago(pageCodigoMedioPago.getContent().get(0));
			}else {
				throw new LogicaImplException("No se puede crear MedioPago, parametros ya existen en identificador valido");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	public VentaPago actualizarMedioPago(String codigoMedioPago, MedioPago objMedioPago)
			throws LogicaImplException {
		VentaPago ventaPago = new VentaPago();
		try {

//			Optional<MedioPago> optPerMedioPago = factoryVentaPagoDAO.getMedioPagoRepository().findById(id);
//
//			if(optPerMedioPago!=null && optPerMedioPago.isPresent()){

				Pageable pageByCodigoDesc = PageRequest.of(0, 1, Sort.by("codigoMedioPago").descending());

				Page<MedioPago> pageCodigoMedioPago  = factoryVentaPagoDAO.getMedioPagoRepository().findByCodigoMedioPago(codigoMedioPago, pageByCodigoDesc);

				/***Busqueda por nombre existe en un tipoNegocio No existe. o solo existe en el pageNombreMedioPago.idMedioPago = id 
				//... solo actualizar estado****/
				if(!pageCodigoMedioPago.isEmpty()){
//					objMedioPago.setIdMedioPago(id);

					factoryVentaPagoDAO.getMedioPagoRepository().save(objMedioPago);

					ventaPago.setMedioPago(objMedioPago);
				}
				else {
					throw new LogicaImplException("No se puede actualizar MedioPago, codigoMedioPago ya existe en un identificador distinto");
				}

//			}else {
//				throw new LogicaImplException("No se puede actualizar MedioPago, identificador no existe");
//			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	@Cacheable(value="buscarMedioPagoxCodigo")
	public VentaPago buscarMedioPagoxCodigo(MedioPago objMedioPago) throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			//Como la lista es pequeña, obtener todos desde cache y luego buscar el Id.
			Pageable pageByCodigoDesc = PageRequest.of(0, 1, Sort.by("codigoMedioPago").descending());

			Page<MedioPago> pageCodigoMedioPago  = factoryVentaPagoDAO.getMedioPagoRepository().findByCodigoMedioPago(objMedioPago.getCodigoMedioPago(), pageByCodigoDesc);

			/***Busqueda por nombre existe en un tipoNegocio No existe. o solo existe en el pageNombreTipoNegocio.idTipoNegocio = id 
				//... solo actualizar estado****/
			if(!pageCodigoMedioPago.isEmpty()){

				ventaPago.setMedioPago(pageCodigoMedioPago.getContent().get(0));
			}
			else {
				throw new LogicaImplException("No existe MedioPago con nombre:" +objMedioPago.getNombreMedioPago());
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return ventaPago;
	}

	@Cacheable(value="listarTodoMedioPago")
	public VentaPago listarTodoMedioPago() throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			List<MedioPago> listaMedioPago = factoryVentaPagoDAO.getMedioPagoRepository().findAll();

			if(listaMedioPago!=null && !listaMedioPago.isEmpty()){
				ventaPago.setListaMedioPago(listaMedioPago);
			}else {
				throw new LogicaImplException("No existe lista de MedioPago");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	/***** Negocio Negocio Negocio **************/
	/***** Negocio Negocio Negocio **************/
	/***** Negocio Negocio Negocio **************/
	/**Primero generar el codigo de negocio y luego crear negocio**/
	public VentaPago generarCodigoNegocio(Negocio objNegocio) throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();
		
		try {
			if(!objNegocio.getIdUsuarioContactoEmailContacto().equals("") && !objNegocio.getValue_64_negocio_producto().equals("")){
				if(buscarCanalPago(objNegocio.getIdCanalPago()).getCanalPago().getIdCanalPago() > 0){
					if(objNegocio.getIdPaisPortal()>0 && objNegocio.getIdTipoNegocio()>0){

						String codigoNegocio = UtilidadesVentaPago.obtenerCodigoNegocio(objNegocio);
						
						/**Buscar si el codigo existe*/
						Pageable pageByCodigoDesc = PageRequest.of(0, 1, Sort.by("codigoNegocio").descending());
						/*****Buscar el ProductoFeeComision por codigo *****/
						Page<Negocio> pageCodigoProducto  = factoryVentaPagoDAO.getNegocioRepository().findByCodigoNegocio(codigoNegocio, pageByCodigoDesc);
						
						if(pageCodigoProducto.isEmpty()) {
							
							objNegocio.setCodigoNegocio(codigoNegocio);
							
							ventaPago.setNegocio(objNegocio);
						}
					}else {
						throw new LogicaImplException("No se puede crear codigo de negocio, datos de negocio invalidos");
					}
				}else {
					throw new LogicaImplException("No se puede crear codigo de negocio, datos de validacion erroneos");
				}
			}else {
				throw new LogicaImplException("No se puede crear codigo de negocio, parametros invalidos");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		
		return ventaPago;
	}

	/**Se asigna al Pago, pero el detalle pago tiene la data necesaria*/


	public VentaPago crearNegocio(Negocio objNegocio) throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			String ahoraYYYY_MM_ddTHH_MM_SSZ = AppDate.obtenerFechaEnFormato(new Date(), TipoFormatoFecha.YYYY_MM_ddTHH_MM_SSZ);
			
			Timestamp tsInicial = DateMapper.mapperSimplyDateFormatYYYY_MM_DDTHH_MM_SSZToTimeStamp(ahoraYYYY_MM_ddTHH_MM_SSZ);
			
			if(objNegocio.getCodigoNegocio().equals(UtilidadesVentaPago.obtenerCodigoNegocio(objNegocio))) {
				Pageable pageByCodigoDesc = PageRequest.of(0, 1, Sort.by("codigoNegocio").descending());
				
				Page<Negocio> pageCodigoNegocio  = factoryVentaPagoDAO.getNegocioRepository().findByCodigoNegocio(
						objNegocio.getCodigoNegocio(), pageByCodigoDesc);

				if(pageCodigoNegocio.isEmpty()){
					
					Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idNegocio").descending());

					Page<Negocio> pageIdNegocio = factoryVentaPagoDAO.getNegocioRepository().findAll(pageByidDesc);

					Integer idNegocio = (!pageIdNegocio.isEmpty()) ? (Integer) pageIdNegocio.getContent().get(0).getIdNegocio() + 1 : 1;
					
					objNegocio.setIdNegocio(idNegocio);
					
					objNegocio.setFechaNegocio(tsInicial);
					
					factoryVentaPagoDAO.getNegocioRepository().save(objNegocio);

					objNegocio  = buscarNegocioxCodigo(objNegocio).getNegocio();

					/*Devolver el status service y devolver el negocio.*/
					ventaPago = crearStatusNegocio(new StatusNegocio(tsInicial, objNegocio));

					ventaPago.setNegocio(objNegocio);
				}else {
					throw new LogicaImplException("No se puede crear Negocio, parametros ya existen en identificador valido");
				}
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	@Cacheable(value="buscarNegocioxCodigo")
	public VentaPago buscarNegocioxCodigo(Negocio objNegocio) throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			//Como la lista es pequeña, obtener todos desde cache y luego buscar el Id.
			Pageable pageByCodigoDesc = PageRequest.of(0, 1, Sort.by("codigoNegocio").descending());

			Page<Negocio> pageCodigoNegocio  = factoryVentaPagoDAO.getNegocioRepository().findByCodigoNegocio(
					objNegocio.getCodigoNegocio(), pageByCodigoDesc);

			/***Busqueda por nombre existe en un tipoNegocio No existe. o solo existe en el pageNombreTipoNegocio.idTipoNegocio = id 
				//... solo actualizar estado****/
			if(!pageCodigoNegocio.isEmpty()){

				ventaPago.setNegocio(pageCodigoNegocio.getContent().get(0));
			}
			else {
				throw new LogicaImplException("No existe Negocio con codigo:" +objNegocio.getCodigoNegocio());
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return ventaPago;
	}

	public VentaPago listarTodoNegocio() throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			List<Negocio> listaNegocio = factoryVentaPagoDAO.getNegocioRepository().findAll();

			if(listaNegocio!=null && !listaNegocio.isEmpty()){
				ventaPago.setListaNegocio(listaNegocio);
			}else {
				throw new LogicaImplException("No existe lista de Negocio");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	public VentaPago listarNegocioxPaisPortalEntreFechas(Integer idPaisPortal, String sFechaInicial, String sFechaFinal)  throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			Timestamp tsInicial = DateMapper.mapperSimplyDateFormatYYYY_MM_DDTHH_MM_SSZToTimeStamp(sFechaInicial);
			
			Timestamp tsFinal = DateMapper.mapperSimplyDateFormatYYYY_MM_DDTHH_MM_SSZToTimeStamp(sFechaFinal);
			
			List<Negocio> repListaNegocio = factoryVentaPagoDAO.getNegocioRepository().
					findAllByIdPaisPortalAndFechaNegocioBetween(idPaisPortal, tsInicial, tsFinal);

			if(repListaNegocio!=null && !repListaNegocio.isEmpty()){
				ventaPago.setListaNegocio(repListaNegocio);
			}else {
				throw new LogicaImplException("No existe lista de HistorialFeeComision");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return ventaPago;
	}

	/***** StatusNegocio StatusNegocio StatusNegocio **************/
	/***** StatusNegocio StatusNegocio StatusNegocio **************/
	/***** StatusNegocio StatusNegocio StatusNegocio **************/
	//Crear status service
	//Crear segundo status service
	//Crear siguiente status service
	public VentaPago crearStatusNegocio(StatusNegocio objStatusNegocio) throws LogicaImplException{

		VentaPago ventaPago = new VentaPago();

		//Si no existe fecha declarada, se agrega la fecha
		if(objStatusNegocio.getFechaStatusNegocio()==null) {
			String ahoraYYYY_MM_ddTHH_MM_SSZ = AppDate.obtenerFechaEnFormato(new Date(), TipoFormatoFecha.YYYY_MM_ddTHH_MM_SSZ);

			objStatusNegocio.setFechaStatusNegocio(DateMapper.mapperSimplyDateFormatYYYY_MM_DDTHH_MM_SSZToTimeStamp(ahoraYYYY_MM_ddTHH_MM_SSZ));
		}

		try {
			//validar  Que existe el id de Negocio;
			buscarNegocioxCodigo(objStatusNegocio.getIdNegocio());
			/**Buscar si existe statusNegocio asociado idNegocio*/
			Pageable pageByCodigoDesc = PageRequest.of(0, 1, Sort.by("idStatusNegocio").descending());

			Page<StatusNegocio> pageStatusNegocio  = factoryVentaPagoDAO.getStatusNegocioRepository().findByIdNegocio(objStatusNegocio.getIdNegocio(), pageByCodigoDesc);
			//IdTipoStatusNegocio()==3 es el maximo disponible para el flujo
			
			if(pageStatusNegocio.isEmpty() || pageStatusNegocio.getContent().get(0).getIdTipoStatusNegocio().getIdTipoStatusNegocio()<3){
				/**si no existe statusNegocio asociado idNegocio o existe pero el tipo status de negocio es menor que 3, obtener para crear*/
				Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idStatusNegocio").descending());

				Page<StatusNegocio> pageIdStatusNegocio = factoryVentaPagoDAO.getStatusNegocioRepository().findAll(pageByidDesc);
				
				Integer idStatusNegocio = (!pageIdStatusNegocio.isEmpty()) ? (Integer) pageIdStatusNegocio.getContent().get(0).getIdStatusNegocio() + 1 : 1;
				
				//Si existe status service aumentar en uno el contador.

				Integer idTipoStatusNegocio = (pageStatusNegocio.isEmpty() && pageStatusNegocio.getContent().get(0).getIdTipoStatusNegocio().getIdTipoStatusNegocio()<3) 
						? (Integer) pageIdStatusNegocio.getContent().get(0).getIdStatusNegocio() + 1 : 1;

				objStatusNegocio.setIdStatusNegocio(idStatusNegocio);

				objStatusNegocio.setIdTipoStatusNegocio(new TipoStatusNegocio(idTipoStatusNegocio));

				factoryVentaPagoDAO.getStatusNegocioRepository().save(objStatusNegocio);

				pageStatusNegocio  = factoryVentaPagoDAO.getStatusNegocioRepository().findByIdNegocio(objStatusNegocio.getIdNegocio(), pageByCodigoDesc); 

				ventaPago.setStatusNegocio(pageStatusNegocio.getContent().get(0));
			}else {
				throw new LogicaImplException("No se puede crear StatusNegocio, parametros ya existen en identificador valido");
			}



		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	public VentaPago listarStatusNegocioxNegocio(StatusNegocio objStatusNegocio) throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			//Como la lista es pequeña, obtener ultimo status service por idNegocio... ordenar descendiente para obtener el ultimo
			List<StatusNegocio> listaStatusNegocio  = factoryVentaPagoDAO.getStatusNegocioRepository().findByIdNegocio(objStatusNegocio.getIdNegocio());

			/***Busqueda por nombre existe en un tipoStatusNegocio No existe. o solo existe en el pageNombreTipoStatusNegocio.idTipoStatusNegocio = id 
			//... solo actualizar estado****/
			if(listaStatusNegocio!=null){

				ventaPago.setListaStatusNegocio(listaStatusNegocio);
			}
			else {
				throw new LogicaImplException("No existe StatusNegocio para codigo de negocio:" +objStatusNegocio.getIdNegocio().getCodigoNegocio());
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return ventaPago;
	}

	/***** Pago Pago Pago **************/
	/***** Pago Pago Pago **************/
	/***** Pago Pago Pago **************/
//	public VentaPago generarCodigoPago(Pago objPago) throws LogicaImplException {
//		
//		VentaPago ventaPago = new VentaPago();
//		
//		try {
//			
//			String ahoraYYYY_MM_DD = AppDate.obtenerFechaEnFormato(new Date(), TipoFormatoFecha.YYYY_MM_ddTHH_MM_SSZ);
//			
//			Negocio negocio = buscarNegocioxCodigo(objPago.getIdNegocio()).getNegocio();
//			
//			if(negocio!=null){
//
//						String codigoNegocio = negocio.getCodigoNegocio()+"*"+objNegocio.getValue_64_negocio_producto()+"*"+objNegocio.getIdPaisPortal()+"*"+
//								objNegocio.getIdCanalPago().getIdCanalPago()+"*"+objNegocio.getIdTipoNegocio()+"*"+ahoraYYYY_MM_DD;
//						
//						codigoNegocio = codigoNegocio.trim();
//						codigoNegocio = codigoNegocio.replace("\\s", "").replace(" ", "");
//						codigoNegocio = codigoNegocio.toLowerCase();
//						objNegocio.setCodigoNegocio(codigoNegocio);
//						
//						/**Buscar si el codigo existe*/
//						Pageable pageByCodigoDesc = PageRequest.of(0, 1, Sort.by("codigoProductoFeeComision").descending());
//						/*****Buscar el ProductoFeeComision por codigo *****/
//						Page<Negocio> pageCodigoProducto  = factoryVentaPagoDAO.getNegocioRepository().findByCodigoNegocio(codigoNegocio, pageByCodigoDesc);
//						
//						if(pageCodigoProducto.isEmpty()) {
//							objNegocio.setCodigoNegocio(codigoNegocio);
//							ventaPago.setNegocio(objNegocio);
//						}
//					}else {
//						throw new LogicaImplException("No se puede crear ProductoFeeComision, tipo de negocio inactivo");
//					}
//				}else {
//					throw new LogicaImplException("No se puede crear ProductoFeeComision, tipo de fee inactivo");
//				}
//			}else {
//				throw new LogicaImplException("No se puede crear ProductoFeeComision, nombre producto servicio invalido");
//			}
//
//		} catch (Exception e) {
//			throw new LogicaImplException(e.getMessage());
//		}
//		
//		return ventaPago;
//	}
	
	
	public VentaPago crearPago(Pago objPago) throws LogicaImplException {

		VentaPago ventaPago = new VentaPago();

		try {

			Negocio ventaPagoNegocio = buscarNegocioxCodigo(objPago.getIdNegocio()).getNegocio();

			if(ventaPagoNegocio!=null && 
					ventaPagoNegocio.getCodigoNegocio()!=null &&
					!ventaPagoNegocio.getCodigoNegocio().equals("")) {
				
				Pageable pageByNegocioDesc = PageRequest.of(0, 1, Sort.by("idNegocio").descending());

				Page<Pago> pageNegocioPago  = factoryVentaPagoDAO.getPagoRepository().findByIdNegocio(objPago.getIdNegocio(), pageByNegocioDesc);

				if(pageNegocioPago.isEmpty()){
					Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idPago").descending());

					Page<Pago> pageIdPago = factoryVentaPagoDAO.getPagoRepository().findAll(pageByidDesc);

					Integer idPago = (!pageIdPago.isEmpty()) ? (Integer) pageIdPago.getContent().get(0).getIdPago() + 1 : 1;

					objPago.setIdPago(idPago);
					
					objPago.setIdNegocio(ventaPagoNegocio);

					objPago.setEstadoPago(false);

					factoryVentaPagoDAO.getPagoRepository().save(objPago);

					pageNegocioPago  = factoryVentaPagoDAO.getPagoRepository().findByIdNegocio(objPago.getIdNegocio(), pageByNegocioDesc);

					ventaPago.setPago(pageNegocioPago.getContent().get(0));
				}else {
					throw new LogicaImplException("No se puede crear Pago, parametros ya existen en identificador valido");
				}
			}else {
				throw new LogicaImplException("No se puede crear Pago, negocio no existe");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	public VentaPago actualizarEstadoPago(Pago objPago)
			throws LogicaImplException {
		VentaPago ventaPago = new VentaPago();
		try {

			Pago ventaPagoPago =  buscarPagoxCodigoNegocio(objPago).getPago();

			if(ventaPagoPago!=null && 
					ventaPagoPago.getIdNegocio().getCodigoNegocio()!=null &&
					!ventaPagoPago.getIdNegocio().getCodigoNegocio().equals("") && objPago.getEstadoPago()==true) {

				String ahoraYYYY_MM_ddTHH_MM_SSZ = AppDate.obtenerFechaEnFormato(new Date(), TipoFormatoFecha.YYYY_MM_ddTHH_MM_SSZ);

				objPago.setFechaPagoFinalizado(DateMapper.mapperSimplyDateFormatYYYY_MM_DDTHH_MM_SSZToTimeStamp(ahoraYYYY_MM_ddTHH_MM_SSZ));

				factoryVentaPagoDAO.getPagoRepository().save(objPago);

				ventaPago.setPago(objPago);

			}else {
				throw new LogicaImplException("No se puede actualizar Pago, identificador no existe");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	//	public VentaPago buscarPago(Pago objPago) throws LogicaImplException {
	//		
	//		VentaPago ventaPago = new VentaPago();
	//		
	//		try {
	//			//Como la lista es pequeña, obtener todos desde cache y luego buscar el Id.
	//			Optional<Pago> optPerPago = factoryVentaPagoDAO.getPagoRepository().findById(objPago.getIdPago());
	//			
	//			/***Si existe reemplazar por el nuevo*/
	//			if(optPerPago!=null && optPerPago.isPresent()){
	//				
	//				ventaPago.setPago(optPerPago.get());
	//			
	//			}else {
	//				throw new LogicaImplException("No existe Pago con identificador:" +objPago.getIdPago());
	//			}
	//			
	//		} catch (Exception e) {
	//			throw new LogicaImplException(e.getMessage());
	//		}
	//		return ventaPago;
	//	}

//	public VentaPago buscarPagoxNegocio(Pago objPago) throws LogicaImplException {
//		
//		VentaPago ventaPago = new VentaPago();
//
//		try {
//			//Como la lista es pequeña, obtener todos desde cache y luego buscar el Id.
//			Pageable pageByNegocioDesc = PageRequest.of(0, 1, Sort.by("idNegocio").descending());
//
//			Page<Pago> pageNegocioPago  = factoryVentaPagoDAO.getPagoRepository().findByIdNegocio(objPago.getIdNegocio(), pageByNegocioDesc);
//
//			/***Busqueda por nombre existe en un tipoPago No existe. o solo existe en el pageNombreTipoPago.idTipoPago = id 
//				//... solo actualizar estado****/
//			if(!pageNegocioPago.isEmpty()){
//
//				ventaPago.setPago(pageNegocioPago.getContent().get(0));
//			}
//			else {
//				throw new LogicaImplException("No existe Pago con nombre:" +objPago.getIdNegocio());
//			}
//
//		} catch (Exception e) {
//			throw new LogicaImplException(e.getMessage());
//		}
//		return ventaPago;
//	}
	
	public VentaPago buscarPagoxCodigoNegocio(Pago objPago) throws LogicaImplException {
		VentaPago ventaPago = new VentaPago();

		try {
			Negocio negocioVentaPago = buscarNegocioxCodigo(objPago.getIdNegocio()).getNegocio();


			//Como la lista es pequeña, obtener todos desde cache y luego buscar el Id.
			Pageable pageByNegocioDesc = PageRequest.of(0, 1, Sort.by("idNegocio").descending());

			Page<Pago> pageNegocioPago  = factoryVentaPagoDAO.getPagoRepository().findByIdNegocio(negocioVentaPago, pageByNegocioDesc);

			/***Busqueda por nombre existe en un tipoPago No existe. o solo existe en el pageNombreTipoPago.idTipoPago = id 
					//... solo actualizar estado****/
			if(!pageNegocioPago.isEmpty()){

				ventaPago.setPago(pageNegocioPago.getContent().get(0));
			}
			else {
				throw new LogicaImplException("No existe Pago con pago asociado a:" +objPago.getIdNegocio().getCodigoNegocio());
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return ventaPago;
	}
	
//	public VentaPago buscarPagoxNegocio(Pago objPago) throws LogicaImplException {
//		VentaPago ventaPago = new VentaPago();
//
//		try {
//			//Como la lista es pequeña, obtener todos desde cache y luego buscar el Id.
//			Pageable pageByNegocioDesc = PageRequest.of(0, 1, Sort.by("idNegocio").descending());
//
//			Page<Pago> pageNegocioPago  = factoryVentaPagoDAO.getPagoRepository().findByIdNegocio(objPago.getIdNegocio(), pageByNegocioDesc);
//
//			/***Busqueda por nombre existe en un tipoPago No existe. o solo existe en el pageNombreTipoPago.idTipoPago = id 
//				//... solo actualizar estado****/
//			if(!pageNegocioPago.isEmpty()){
//
//				ventaPago.setPago(pageNegocioPago.getContent().get(0));
//			}
//			else {
//				throw new LogicaImplException("No existe Pago con nombre:" +objPago.getIdNegocio());
//			}
//
//		} catch (Exception e) {
//			throw new LogicaImplException(e.getMessage());
//		}
//		return ventaPago;
//	}
	
	public VentaPago listarTodoPago() throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			List<Pago> listaPago = factoryVentaPagoDAO.getPagoRepository().findAll();

			if(listaPago!=null && !listaPago.isEmpty()){
				ventaPago.setListaPago(listaPago);
			}else {
				throw new LogicaImplException("No existe lista de Pago");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	public VentaPago listarPagoEntreFecha(Integer idPaisPortal, String sFechaInicial, String sFechaFinal) throws LogicaImplException {

		VentaPago ventaPago = new VentaPago();

		try {

			List<Negocio> listarNegocioxPaisPortalEntreFechas = listarNegocioxPaisPortalEntreFechas(idPaisPortal, sFechaInicial, sFechaFinal).getListaNegocio();

			List<Pago> listaPago = factoryVentaPagoDAO.getPagoRepository().findByIdNegocioIn(listarNegocioxPaisPortalEntreFechas);

			if(listaPago!=null && !listaPago.isEmpty()){
				ventaPago.setListaPago(listaPago);
			}else {
				throw new LogicaImplException("No existe lista de Pago");
			}


		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	/***** AjusteCantidadTotal AjusteCantidadTotal AjusteCantidadTotal **************/
	/***** AjusteCantidadTotal AjusteCantidadTotal AjusteCantidadTotal **************/
	/***** AjusteCantidadTotal AjusteCantidadTotal AjusteCantidadTotal **************/
	
	public VentaPago crearAjusteCantidadTotal(AjusteCantidadTotal objAjusteCantidadTotal) throws LogicaImplException {

		VentaPago ventaPago = new VentaPago();

		try {

			if(objAjusteCantidadTotal.getPorcentajeImpuestoAplicadoCarro().doubleValue()<100 && objAjusteCantidadTotal.getPorcentajeDescuentoAplicado().doubleValue()<100 
					&& objAjusteCantidadTotal.getPorcentajeRefComisionAplicado().doubleValue()<100) {

				buscarPagoxCodigoNegocio(objAjusteCantidadTotal.getIdPago()).getPago();

				AjusteCantidadTotal ajusteCantidadTotal  = factoryVentaPagoDAO.getAjusteCantidadTotalRepository().
						findByIdPagoAndIdTipoMonedaAndIdHistorialTipoCambioAndIdHistorialFeeComision(objAjusteCantidadTotal.getIdPago(), 
								objAjusteCantidadTotal.getIdTipoMoneda(), objAjusteCantidadTotal.getIdHistorialTipoCambio(), objAjusteCantidadTotal.getIdHistorialFeeComision());

				if(ajusteCantidadTotal == null){
					Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idAjusteCantidadTotal").descending());

					Page<AjusteCantidadTotal> pageIdAjusteCantidadTotal = factoryVentaPagoDAO.getAjusteCantidadTotalRepository().findAll(pageByidDesc);
					
					Integer idAjusteCantidadTotal = (!pageIdAjusteCantidadTotal.isEmpty()) ? (Integer) pageIdAjusteCantidadTotal.getContent().get(0).getIdAjusteCantidadTotal() + 1 : 1;
					
					objAjusteCantidadTotal.setIdAjusteCantidadTotal(idAjusteCantidadTotal);
					/****porcentaje_descuento_aplicado ****/
					factoryVentaPagoDAO.getAjusteCantidadTotalRepository().save(objAjusteCantidadTotal);

					objAjusteCantidadTotal  = buscarAjusteCantidadTotalxPagoxTipoMonedaXHTipoCambioxHFeeComision(objAjusteCantidadTotal).getAjusteCantidadTotal();

					ventaPago.setAjusteCantidadTotal(objAjusteCantidadTotal);
				}else {
					throw new LogicaImplException("No se puede crear AjusteCantidadTotal, parametros ya existen en identificador valido");
				}

			}else {
				throw new LogicaImplException("No se puede crear AjusteCantidadTotal, valores incorrectos");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	public VentaPago buscarAjusteCantidadTotalxPagoxTipoMonedaXHTipoCambioxHFeeComision(AjusteCantidadTotal objAjusteCantidadTotal) throws LogicaImplException {

		VentaPago ventaPago = new VentaPago();

		try {
			//Buscar el pago por el codigo de negocio
			buscarPagoxCodigoNegocio(objAjusteCantidadTotal.getIdPago()).getPago();
			//Como la lista es pequeña, obtener todos desde cache y luego buscar el Id.

			AjusteCantidadTotal ajusteCantidadTotal  = factoryVentaPagoDAO.getAjusteCantidadTotalRepository().
					findByIdPagoAndIdTipoMonedaAndIdHistorialTipoCambioAndIdHistorialFeeComision(
							objAjusteCantidadTotal.getIdPago(), objAjusteCantidadTotal.getIdTipoMoneda(), objAjusteCantidadTotal.getIdHistorialTipoCambio(), 
							objAjusteCantidadTotal.getIdHistorialFeeComision());

			/***Si existe reemplazar por el nuevo*/
			if(ajusteCantidadTotal!=null){

				ventaPago.setAjusteCantidadTotal(ajusteCantidadTotal);

			}else {
				throw new LogicaImplException("No existe Pago con identificador:" +objAjusteCantidadTotal.getIdPago().getIdNegocio().getCodigoNegocio());
			}


		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return ventaPago;
	}

	public VentaPago listarTodoAjusteCantidadTotal() throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			List<AjusteCantidadTotal> listaAjusteCantidadTotal = factoryVentaPagoDAO.getAjusteCantidadTotalRepository().findAll();

			if(listaAjusteCantidadTotal!=null && !listaAjusteCantidadTotal.isEmpty()){
				ventaPago.setListaAjusteCantidadTotal(listaAjusteCantidadTotal);
			}else {
				throw new LogicaImplException("No existe lista de Pago");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}
	
	public VentaPago listarTodoAjusteCantidadTotalxPago(AjusteCantidadTotal objAjusteCantidadTotal) throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			List<AjusteCantidadTotal> listaAjusteCantidadTotal = factoryVentaPagoDAO.getAjusteCantidadTotalRepository().findByIdPago(objAjusteCantidadTotal.getIdPago());

			if(listaAjusteCantidadTotal!=null && !listaAjusteCantidadTotal.isEmpty()){
				ventaPago.setListaAjusteCantidadTotal(listaAjusteCantidadTotal);
			}else {
				throw new LogicaImplException("No existe lista de Pago");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}
	
	/***** DetallePago DetallePago **************/
	/***** DetallePago DetallePago **************/
	/***** DetallePago DetallePago **************/
	
	public VentaPago generarCodigoDetallePago(DetallePago objDetallePago) throws LogicaImplException {

		VentaPago ventaPago = new VentaPago();

		try {
			buscarPagoxCodigoNegocio(objDetallePago.getIdPago());

			objDetallePago.setIdMedioPago(buscarMedioPagoxCodigo(objDetallePago.getIdMedioPago()).getMedioPago());

			String codigoDetallePago = UtilidadesVentaPago.obtenerDetallePago(objDetallePago);

			/**Buscar si el codigo existe*/
			Pageable pageByCodigoDesc = PageRequest.of(0, 1, Sort.by("codigoDetallePago").descending());
			/*****Buscar el ProductoFeeComision por codigo *****/
			Page<DetallePago> pageCodigoProducto  = factoryVentaPagoDAO.getDetallePagoRepository().findByFkCodigoDetallePago(
					codigoDetallePago, pageByCodigoDesc);

			if(pageCodigoProducto.isEmpty()) {
				objDetallePago.setCodigoDetallePago(codigoDetallePago);
				ventaPago.setDetallePago(objDetallePago);
			}


		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}
	
	public VentaPago crearDetallePago(DetallePago objDetallePago) throws LogicaImplException {

		VentaPago ventaPago = new VentaPago();

		try {
			Pageable pageByCodigoDesc = PageRequest.of(0, 1, Sort.by("codigoDetallePago").descending());

			Page<DetallePago> pageDetallePago  = factoryVentaPagoDAO.getDetallePagoRepository().
					findByFkCodigoDetallePago(objDetallePago.getCodigoDetallePago(), pageByCodigoDesc);

			//Siempre debe ir el codigo de negocio, e internamente obtener el id de pago
			if(pageDetallePago.isEmpty() && objDetallePago.getCodigoDetallePago().equals(UtilidadesVentaPago.obtenerDetallePago(objDetallePago))) {

				factoryVentaPagoDAO.getDetallePagoRepository().save(objDetallePago);

				objDetallePago  = buscarDetallePagoxCodigo(objDetallePago).getDetallePago();

				ventaPago.setDetallePago(objDetallePago);
			}else {
				throw new LogicaImplException("No se puede crear DetallePago, parametros ya existen en identificador valido");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	@Cacheable(value="buscarDetallePagoxCodigo")
	public VentaPago buscarDetallePagoxCodigo(DetallePago objDetallePago) throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			//Como la lista es pequeña, obtener todos desde cache y luego buscar el Id.
			Pageable pageByCodigoDesc = PageRequest.of(0, 1, Sort.by("codigoDetallePago").descending());

			Page<DetallePago> pageCodigoDetallePago  = factoryVentaPagoDAO.getDetallePagoRepository().findByFkCodigoDetallePago(objDetallePago.getCodigoDetallePago(), pageByCodigoDesc);

			/***Busqueda por nombre existe en un tipoNegocio No existe. o solo existe en el pageNombreTipoNegocio.idTipoNegocio = id 
				//... solo actualizar estado****/
			if(!pageCodigoDetallePago.isEmpty() && pageCodigoDetallePago.getContent().get(0).getIdPago().getIdNegocio().getCodigoNegocio().equals("")){

				ventaPago.setDetallePago(pageCodigoDetallePago.getContent().get(0));
			}
			else {
				throw new LogicaImplException("No existe DetallePago con nombre:" +objDetallePago.getCodigoDetallePago());
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return ventaPago;
	}
	
	public VentaPago listarTodoDetallePago() throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			List<DetallePago> listaDetallePago = factoryVentaPagoDAO.getDetallePagoRepository().findAll();

			if(listaDetallePago!=null && !listaDetallePago.isEmpty()){
				ventaPago.setListaDetallePago(listaDetallePago);
			}else {
				throw new LogicaImplException("No existe lista de DetallePago");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}

	public VentaPago listarDetallePagoxPago(DetallePago objDetallePago) throws LogicaImplException {
		
		VentaPago ventaPago = new VentaPago();

		try {
			//Como la lista es pequeña, obtener todos desde cache y luego buscar el Id.
			Pageable pageByNegocioDesc = PageRequest.of(0, 10, Sort.by("idPago").descending());

			Page<DetallePago> pagePago  = factoryVentaPagoDAO.getDetallePagoRepository().findByIdPago(objDetallePago.getIdPago(), pageByNegocioDesc);

			/***Busqueda por nombre existe en un tipoPago No existe. o solo existe en el pageNombreTipoPago.idTipoPago = id 
			//... solo actualizar estado****/
			if(!pagePago.isEmpty()){

				ventaPago.setListaDetallePago(pagePago.getContent());
			}
			else {
				throw new LogicaImplException("No existe DetallePago con codigo de negocio:" +objDetallePago.getIdPago().getIdNegocio().getCodigoNegocio());
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return ventaPago;
	}
	
	/***** DetallePagoCuotas DetallePagoCuotas **************/
	/***** DetallePagoCuotas DetallePagoCuotas **************/
	/***** DetallePagoCuotas DetallePagoCuotas **************/
	public VentaPago crearDetallePagoCuotas(DetallePagoCuotas objDetallePagoCuotas) throws LogicaImplException {

		VentaPago ventaPago = new VentaPago();

		try {
			if(objDetallePagoCuotas.getInteresMensual().doubleValue()<100 && objDetallePagoCuotas.getInteresAnual().doubleValue()<100 &&objDetallePagoCuotas.getCae().doubleValue()<100) {
				//Siempre debe ir el codigo de negocio, e internamente obtener el id de pago
				buscarDetallePagoxCodigo(objDetallePagoCuotas.getIdDetallePago()).getDetallePago();

				Pageable pageByPagoDesc = PageRequest.of(0, 1, Sort.by("idDetallePago").descending());

				Page<DetallePagoCuotas> pageDetallePagoCuotas  = factoryVentaPagoDAO.getDetallePagoCuotasRepository().
						findByIdDetallePago(objDetallePagoCuotas.getIdDetallePago(), pageByPagoDesc);

				if(pageDetallePagoCuotas.isEmpty()){
					Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idDetallePagoCuotas").descending());

					Page<DetallePagoCuotas> pageIdDetallePagoCuotas = factoryVentaPagoDAO.getDetallePagoCuotasRepository().findAll(pageByidDesc);

					Integer idDetallePagoCuotas = (!pageIdDetallePagoCuotas.isEmpty()) ? (Integer) pageIdDetallePagoCuotas.getContent().get(0).getIdDetallePagoCuotas() + 1 : 1;

					objDetallePagoCuotas.setIdDetallePagoCuotas(idDetallePagoCuotas);

					factoryVentaPagoDAO.getDetallePagoCuotasRepository().save(objDetallePagoCuotas);

					objDetallePagoCuotas  = buscarDetallePagoCuotasxCodigoDetallePago(objDetallePagoCuotas).getDetallePagoCuotas();

					ventaPago.setDetallePagoCuotas(objDetallePagoCuotas);
				}else {
					throw new LogicaImplException("No se puede crear DetallePagoCuotas, parametros ya existen en identificador valido");
				}

			}else {
				throw new LogicaImplException("No se puede crear DetallePagoCuotas, negocio no existe");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return ventaPago;
	}


		public VentaPago buscarDetallePagoCuotasxCodigoDetallePago(DetallePagoCuotas objDetallePagoCuotas)
				throws LogicaImplException {
	
			VentaPago ventaPago = new VentaPago();
	
			try {
				buscarDetallePagoxCodigo(objDetallePagoCuotas.getIdDetallePago());
	
				//Como la lista es pequeña, obtener todos desde cache y luego buscar el Id.
				Pageable pageByDetallePagoDesc = PageRequest.of(0, 1, Sort.by("idDetallePago").descending());
	
				Page<DetallePagoCuotas> pageDetallePagoCuota  = factoryVentaPagoDAO.getDetallePagoCuotasRepository().findByIdDetallePago(objDetallePagoCuotas.getIdDetallePago(), pageByDetallePagoDesc);
	
				/***Si existe reemplazar por el nuevo*/
				if(!pageDetallePagoCuota.isEmpty()){
	
					ventaPago.setDetallePagoCuotas(pageDetallePagoCuota.getContent().get(0));
	
				}else {
					throw new LogicaImplException("No existe DetallePagoCuotas asociado al codigo:" +objDetallePagoCuotas.getIdDetallePago().getCodigoDetallePago());
				}
	
	
			} catch (Exception e) {
				throw new LogicaImplException(e.getMessage());
			}
			return ventaPago;
		}
	
	public void limpiarCacheLocal() {

	}
}
