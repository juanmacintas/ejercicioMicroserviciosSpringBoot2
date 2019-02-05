package es.ejercicio.microservicios.biblioteca.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.ejercicio.microservicios.biblioteca.cliente.ClienteLibros;
import es.ejercicio.microservicios.dto.LibroDTO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ControlLibros implements FallbackFactory<ClienteLibros> {
	@Override
	public ClienteLibros create(Throwable cause) {
		return new ClienteLibros() {

			@Override
			public List<LibroDTO> obtenerLibros() {
				log.error("Se ha producido un error al obtener los libros." + cause.getMessage());
			    return new ArrayList<LibroDTO>() ;
			}

			@Override
			public List<LibroDTO> obtenerLibrosFavoritos() {
				log.error("Se ha producido un error al obtener los libros favoritos." + cause.getMessage());
			    return new ArrayList<LibroDTO>() ;
			}

			@Override
			public List<LibroDTO> obtenerLibrosByExample(LibroDTO input) {
				log.error("Se ha producido un error al obtener los libros by example." + cause.getMessage());
				return new ArrayList<LibroDTO>() ;
			}

			@Override
			public LibroDTO obtenerLibro(String id) {
				log.error("Se ha producido un error al obtener el libro." + cause.getMessage());
				return LibroDTO.builder().id(0).titulo("NO DISPONIBLE").build();
			}

		};

	}

}
