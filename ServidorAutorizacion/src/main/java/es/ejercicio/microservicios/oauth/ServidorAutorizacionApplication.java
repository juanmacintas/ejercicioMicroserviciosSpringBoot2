package es.ejercicio.microservicios.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServidorAutorizacionApplication   {

	public static void main(String[] args) {
		SpringApplication.run(ServidorAutorizacionApplication.class, args);
	}

}
