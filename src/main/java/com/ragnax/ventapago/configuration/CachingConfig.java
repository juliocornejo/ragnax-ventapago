package com.ragnax.ventapago.configuration;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
@EnableCaching
@ComponentScan("com.ragnax.ventapago.servicio")
public class CachingConfig {

    @Bean
    @Primary
    public CacheManager cacheVentapago() {
        final SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
        		new ConcurrentMapCache("listarTodoTipoStatusNegocio"), 
        		new ConcurrentMapCache("listarTodoCanalPago") , 
        		new ConcurrentMapCache("listarTodoTipoMedioPago"),
        		new ConcurrentMapCache("buscarMedioPagoxCodigo"),
        		new ConcurrentMapCache("listarTodoMedioPago"),
        		new ConcurrentMapCache("buscarNegocioxCodigo"),
        		new ConcurrentMapCache("buscarDetallePagoxCodigo")));
        return cacheManager;
    }
    
//    @Bean
//    public CacheManager cacheListaFeeComision() {
//        final SimpleCacheManager cacheManager = new SimpleCacheManager();
//        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("directorio"), new ConcurrentMapCache("listaTipoFeeComision")));
//        return cacheManager;
//    }

}