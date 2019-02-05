package es.ejercicio.microservicios.biblioteca.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import es.ejercicio.microservicios.biblioteca.cliente.ClienteAutores;
import es.ejercicio.microservicios.dto.AutorDTO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ControlAutores implements FallbackFactory<ClienteAutores> {
	@Override
	public ClienteAutores create(Throwable cause) {
		return new ClienteAutores() {

			@Override
			public List<AutorDTO> obtenerAutores() {
				log.error("Se ha producido un error al obtener los autores." + cause.getMessage());
				cause.printStackTrace();

		        return new ArrayList<AutorDTO>();

			}

			@Override
			public ResponseEntity<AutorDTO> obtenerAutor(String id) {
				log.error("Se ha producido un error al obtener el autor." + cause.getMessage());
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
		        		AutorDTO.builder().id(0).nombre("NO DISPONIBLE").build());
			}

			@Override
			public ResponseEntity<AutorDTO> nuevoAutor(AutorDTO input) {
				log.error("Se ha producido un error al añadir el autor." + cause.getMessage());
		        return ResponseEntity
		        		.status(HttpStatus.NOT_FOUND)
		        		.body(AutorDTO.builder().id(0).nombre("NO DISPONIBLE").build());			}

			@Override
			public void eliminarAutor(String id) {
				log.error("Se ha producido un error al eliminar el autor." + cause.getMessage());

			}

		};

	}

}