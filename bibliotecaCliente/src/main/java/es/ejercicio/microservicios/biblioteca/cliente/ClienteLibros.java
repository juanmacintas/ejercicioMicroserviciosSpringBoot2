package es.ejercicio.microservicios.biblioteca.cliente;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.ejercicio.microservicios.biblioteca.control.ControlLibros;
import es.ejercicio.microservicios.dto.LibroDTO;

@FeignClient(name="libros", fallbackFactory = ControlLibros.class)
public interface ClienteLibros {

   @RequestMapping(path = "/libros/getAll",
    		method = RequestMethod.GET)
    public List<LibroDTO>  obtenerLibros();

   @RequestMapping(path = "/libros/getFavoritos",
   		method = RequestMethod.GET)
   public List<LibroDTO>  obtenerLibrosFavoritos();


   @RequestMapping(path = "/libros/getByExample",
	   		method = RequestMethod.POST)
   public List<LibroDTO>  obtenerLibrosByExample(@RequestBody LibroDTO input);

   @RequestMapping(path = "/libros/getLibro/{id}",
   		method = RequestMethod.GET)
   public LibroDTO obtenerLibro(@PathVariable("id") String id);

}
