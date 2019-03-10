package es.ejercicio.microservicios.biblioteca.cliente;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.ejercicio.microservicios.biblioteca.config.Oauth2ClientConfig;
import es.ejercicio.microservicios.biblioteca.control.ControlEditoriales;
import es.ejercicio.microservicios.dto.EditorialDTO;

@FeignClient(name="libros", fallbackFactory = ControlEditoriales.class,
			 configuration = Oauth2ClientConfig.class)
public interface ClienteEditoriales {

    @RequestMapping(path = "/editoriales/getAll",
	   		method = RequestMethod.GET)
    public List<EditorialDTO> obtenerEditoriales();

   @RequestMapping(path = "/editoriales/getEditorial/{id}",
    		method = RequestMethod.GET)
    public EditorialDTO obtenerEditorial(@PathVariable("id") String id);

   @RequestMapping(path = "/editoriales/nuevaEditorial",
   		method = RequestMethod.POST)
   public ResponseEntity<EditorialDTO> nuevaEditorial(@RequestBody EditorialDTO input);


   @RequestMapping(path = "/editoriales/deleteEditorial/{id}",
   		method = RequestMethod.DELETE)
   public void eliminarEditorial(@PathVariable("id") String id);

}
