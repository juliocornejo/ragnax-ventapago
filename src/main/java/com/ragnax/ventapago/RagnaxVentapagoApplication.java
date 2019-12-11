package com.ragnax.ventapago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.ragnax.ventapago.configuration.FactoryApiProperties;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties(FactoryApiProperties.class)
public class RagnaxVentapagoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RagnaxVentapagoApplication.class, args);
	}

}
