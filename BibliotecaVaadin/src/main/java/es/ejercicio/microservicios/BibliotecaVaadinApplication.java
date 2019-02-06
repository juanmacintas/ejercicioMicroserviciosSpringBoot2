package es.ejercicio.microservicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("es.ejercicio.microservicios")
public class BibliotecaVaadinApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaVaadinApplication.class, args);
	}
}
