package es.ejercicio.microservicios.biblioteca.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import es.ejercicio.microservicios.biblioteca.cliente.ClienteCategorias;
import es.ejercicio.microservicios.biblioteca.cliente.ClienteEditoriales;
import es.ejercicio.microservicios.dto.CategoriaDTO;
import es.ejercicio.microservicios.dto.EditorialDTO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ControlCategorias implements FallbackFactory<ClienteCategorias> {
	@Override
	public ClienteCategorias create(Throwable cause) {
		return new ClienteCategorias() {

			@Override
			public List<CategoriaDTO> obtenerCategorias() {
				log.error("Se ha producido un error al obtener las categorias:" + cause.getMessage());
		        return new ArrayList<CategoriaDTO>();
			}

			@Override
			public CategoriaDTO obtenerCategoria(String id) {
				log.error("Se ha producido un error al obtener la categoria:" + cause.getMessage());
			    return CategoriaDTO.builder().id(0).nombre("NO DISPONIBLE").build();
			}

			@Override
			public ResponseEntity<CategoriaDTO> nuevaCategoria(CategoriaDTO input) {
				log.error("Se ha producido un error al crear la categoria:" + cause.getMessage());
				return ResponseEntity
			        		.status(HttpStatus.NOT_FOUND)
			        		.body(CategoriaDTO.builder().id(0).nombre("NO DISPONIBLE").build());
			}

			@Override
			public void eliminarCategoria(String id) {
				log.error("No se ha podido eliminar la categoría." + cause.getMessage());

			}
		};
	}
}
