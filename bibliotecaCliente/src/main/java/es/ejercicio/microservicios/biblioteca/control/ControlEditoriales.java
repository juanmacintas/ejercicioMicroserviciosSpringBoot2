package es.ejercicio.microservicios.biblioteca.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import es.ejercicio.microservicios.biblioteca.cliente.ClienteEditoriales;
import es.ejercicio.microservicios.dto.EditorialDTO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ControlEditoriales implements FallbackFactory<ClienteEditoriales> {
	@Override
	public ClienteEditoriales create(Throwable cause) {
		return new ClienteEditoriales() {
			
			@Override
			public List<EditorialDTO> obtenerEditoriales() {
				log.error("Se ha producido un error al obtener las editoriales." + cause.getMessage());
		        return new ArrayList<EditorialDTO>();
			}

			@Override
			public EditorialDTO obtenerEditorial(String id) {
				log.error("Se ha producido un error al obtener la editorial {}" +id);
		        return EditorialDTO.builder().id(0).nombre("NO DISPONIBLE").build();
			}

			@Override
		    public ResponseEntity<EditorialDTO> nuevaEditorial(@RequestBody EditorialDTO input) {
				log.error("Se ha producido un error al añadir la editorial");
				return ResponseEntity
		        		.status(HttpStatus.NOT_FOUND)
		        		.body(EditorialDTO.builder().id(0).nombre("NO DISPONIBLE").build());
		   }

			@Override
			public void eliminarEditorial(String id) {
				log.error("Se ha producido un error al eliminar la editorial");
			}
		};
	}

}
