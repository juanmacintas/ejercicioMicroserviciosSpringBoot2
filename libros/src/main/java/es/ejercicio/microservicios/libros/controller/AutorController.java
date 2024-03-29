package es.ejercicio.microservicios.libros.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.ejercicio.microservicios.dto.AutorDTO;
import es.ejercicio.microservicios.libros.entity.Autor;
import es.ejercicio.microservicios.libros.service.AutorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@Api(value = "AutorController", description="Operaciones sobre los Autores de los libros de la Biblioteca")
@RequestMapping(value = "/autores/")
public class AutorController {

    @Autowired
    private AutorService autorService;

    /** DozerMapper. */
    DozerBeanMapper mapper = new DozerBeanMapper();

    /**
     * Retorna todos los autores
     * @return Listado de autores
     * @throws SQLException
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "Retorna todos los autores",
    			  notes = "Retorna todos los autores almacenados en base de datos",
    			  response = AutorDTO.class,
    			  responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Autores retornados correctamente")})
    public List<AutorDTO> getAll() throws SQLException {

    	List<Autor> autores = autorService.findAll();
       	List<AutorDTO> autoresDTO = new ArrayList<AutorDTO>();
    	if (autores != null)
    	{
    		for (Autor autor : autores) {
    			AutorDTO autorDTO= (AutorDTO) mapper.map(autor, AutorDTO.class);
    			autoresDTO.add(autorDTO);
    		}

    	}
        return autoresDTO;

    }

    /**
     * Retorna el autor del id
     * @return Autor
     * @throws SQLException
     */
    @RequestMapping(value = "/getAutor/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Retorna un Autor",
	  notes = "Retorna el Autor del id especificado",
	  response = AutorDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Autor no encontrado"),
    					   @ApiResponse(code = 200, message = "Autor encontrado")}
    						)
    public ResponseEntity<AutorDTO> getAutor(@ApiParam(name = "id", value = "Id del Autor a buscar", required = true) @PathVariable("id") String id) throws SQLException {
    	log.debug("Llamada a getAutor para el id:" + id);
    	Integer idAutor = 0;
    	try
    	{
    		idAutor = Integer.parseInt(id);
    	} catch (NumberFormatException ex) {
    		log.error("Se ha producido un error, el id no es un valor numerico:" + ex.getMessage());
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AutorDTO());
    	}

    	//Span s = tracer.createSpan("Autor");

    	//s.logEvent("Se obtiene el autor:" + idAutor);
    	log.debug("Se busca el autor " + idAutor + " en base de datos.");
    	Autor autor = autorService.findById(idAutor);
    	if (autor != null)
    	{
    		log.debug("Autor Obtenido");
    		//s.logEvent("Autor Obtenido");
    		AutorDTO autorDTO= (AutorDTO) mapper.map(autor, AutorDTO.class);
    		//tracer.addTag("AutorId", Integer.toString(autor.getId()));
    		//tracer.addTag("AutorNombre", autor.getNombre());
    		//tracer.close(s);
    		log.debug("Autor retornado:" + autorDTO);
    		return ResponseEntity.status(HttpStatus.OK).body(autorDTO);
    	} else {
    		//s.logEvent("Autor No Encontrado");
    		//tracer.close(s);
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AutorDTO());
    	}


    }

    /**
     * Elimina el autor del id
     * @throws SQLException
     */
    @RequestMapping(value = "/deleteAutor/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Elimina un Autor",
    			notes = "Elimina el Autor del id especificado",
    		  response = HttpStatus.class)
    public HttpStatus deleteAutor(@ApiParam(name = "id", value = "Id del Autor a eliminar", required = true)@PathVariable("id") String id) throws SQLException {
    	Integer idAutor = 0;
    	try
    	{
    		idAutor = Integer.parseInt(id);
    	} catch (NumberFormatException ex) {
    		log.error("Se ha producido un error, el id no es un valor numerico:" + ex.getMessage());
    		return HttpStatus.NOT_FOUND;
    	}
    	autorService.deleteById(idAutor);

       	return HttpStatus.OK;
    }


    /**
     * Añade un nuevo autor
     * @return Autor
     * @throws SQLException
     */
    @RequestMapping(value = "/nuevoAutor", method = RequestMethod.POST)
    @ApiOperation("Inserta o actualiza en base de datos un Autor")
    public ResponseEntity<AutorDTO> nuevoAutor(@ApiParam(name = "autorDTO", value = "Autor a insertar/actualizar", required = true) @RequestBody AutorDTO input) throws SQLException {
    	log.debug("Se intenta insertar el autor:" + input);

    	Autor autor = Autor.builder().id(input.getId())
    											 .nombre(input.getNombre())
    											 .build();

    	Autor nuevoAutor = autorService.nuevoAutor(autor);
    	AutorDTO autorDTO= (AutorDTO) mapper.map(nuevoAutor, AutorDTO.class);

       	return ResponseEntity.status(HttpStatus.OK).body(autorDTO);

    }

}
