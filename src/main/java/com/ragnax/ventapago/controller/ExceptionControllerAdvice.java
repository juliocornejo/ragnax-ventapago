package com.ragnax.ventapago.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ragnax.ventapago.controller.response.RagnaxError;
import com.ragnax.ventapago.exception.LogicaImplException;


/**
 * Created by julito el mas lindo on 09-08-19.
 */
@ControllerAdvice
@ResponseBody
public class ExceptionControllerAdvice {

    private static final Logger LOGGER = LogManager.getLogger(ExceptionControllerAdvice.class);

    /**
     * Metodo que captura la excepcion generada al no poder ser enviado el correo.
     *
     * @param mailEx excepcion del tipo MailException.
     * @return ResponseEntity<Response> con el error capturado y el codigo HTTP
     */
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ExceptionHandler(LogicaImplException.class)
    public ResponseEntity<RagnaxError> handlerException(LogicaImplException lie) {
        LOGGER.error("Error en politicacomercial: {} .", lie.getMessage());
//        return new ResponseEntity<>(new Response(e.getMessage(),null, HttpStatus.INTERNAL_SERVER_ERROR.value(), null), HttpStatus.INTERNAL_SERVER_ERROR);
//        return new ResponseEntity<>(new PoliticaComercialError(new PoliticaComercialError(e.getMessage()),  HttpStatus.SERVICE_UNAVAILABLE.value(), null, null, null), HttpStatus.SERVICE_UNAVAILABLE);
        
        return new ResponseEntity<>(new RagnaxError(HttpStatus.ACCEPTED.value(), lie.getMessage()),
                HttpStatus.ACCEPTED);
        
    }
}