package es.ejercicio.microservicios.biblioteca.cliente;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.ejercicio.microservicios.biblioteca.control.ControlAutores;
import es.ejercicio.microservicios.dto.AutorDTO;

@FeignClient(name="libros", fallbackFactory = ControlAutores.class)
public interface ClienteAutores {

	  @RequestMapping(path = "/autores/getAll",
		   		method = RequestMethod.GET)
	  public List<AutorDTO> obtenerAutores();

	  @RequestMapping(path = "/autores/getAutor/{id}",
    		method = RequestMethod.GET)
	  public ResponseEntity<AutorDTO> obtenerAutor(@PathVariable("id") String id);

	  @RequestMapping(path = "/autores/nuevoAutor",
   		method = RequestMethod.POST)
	  public ResponseEntity<AutorDTO>  nuevoAutor(@RequestBody AutorDTO input);


	  @RequestMapping(path = "/autores/deleteAutor/{id}",
   		method = RequestMethod.DELETE)
	  public void eliminarAutor(@PathVariable("id") String id);

}
