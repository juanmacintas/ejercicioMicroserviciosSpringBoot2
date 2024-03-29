package es.ejercicio.microservicios.biblioteca.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.ejercicio.microservicios.biblioteca.cliente.ClienteAutores;
import es.ejercicio.microservicios.biblioteca.cliente.ClienteCategorias;
import es.ejercicio.microservicios.biblioteca.cliente.ClienteEditoriales;
import es.ejercicio.microservicios.biblioteca.cliente.ClienteLibros;
import es.ejercicio.microservicios.biblioteca.control.ControlLibros;
import es.ejercicio.microservicios.dto.AutorDTO;
import es.ejercicio.microservicios.dto.CategoriaDTO;
import es.ejercicio.microservicios.dto.EditorialDTO;
import es.ejercicio.microservicios.dto.LibroBibliotecaDTO;
import es.ejercicio.microservicios.dto.LibroDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/biblioteca/")
@Api(value = "BibliotecaController", description="Operaciones sobre la Biblioteca")
public class BibliotecaController {


	@Autowired
    private ClienteAutores clienteAutores;

	@Autowired
    private ClienteCategorias clienteCategorias;

	@Autowired
    private ClienteEditoriales clienteEditoriales;

	@Autowired
    private ClienteLibros clienteLibros;

    /** DozerMapper. */
    DozerBeanMapper mapper = new DozerBeanMapper();

    /**
     * Retorna todos los libros
     * @return Listado de libros
     * @throws SQLException
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "Retorna todos los libros",
	  notes = "Retorna todos los libros almacenados en base de datos",
	  response = LibroBibliotecaDTO.class,
	  responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Libros retornadas correctamente")})
    public List<LibroBibliotecaDTO> getAll() throws SQLException {


		List<LibroDTO> listaBiblioteca = clienteLibros.obtenerLibros();
		return getListaBiblioteca(listaBiblioteca);
    }

    /**
     * Retorna todos los libros favoritos
     * @return Listado de libros favoritos
     * @throws SQLException
     */
    @RequestMapping(value = "/getAllFavoritos", method = RequestMethod.GET)
    @ApiOperation(value = "Retorna todos los libros favoritos",
	  notes = "Retorna todos los libros favoritos almacenados en base de datos",
	  response = LibroBibliotecaDTO.class,
	  responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Libros retornadas correctamente")})
    public List<LibroBibliotecaDTO> getAllFavoritos() throws SQLException {


		List<LibroDTO> listaBiblioteca = clienteLibros.obtenerLibrosFavoritos();

    	return getListaBiblioteca(listaBiblioteca);
    }

    /**
     * Retorna todos los autores
     * @return Listado de autores
     * @throws SQLException
     */
    @RequestMapping(value = "/getAutores", method = RequestMethod.GET)
    @ApiOperation(value = "Retorna todos los autores",
	  notes = "Retorna todos los autores almacenados en base de datos",
	  response = AutorDTO.class,
	  responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Autores retornados correctamente")})
    public List<AutorDTO> getAllAutores() throws SQLException {


		List<AutorDTO> listaAutores = clienteAutores.obtenerAutores();
		return listaAutores;
    }

    /**
     * Retorna todos las categorias
     * @return Listado de categorias
     * @throws SQLException
     */
    @RequestMapping(value = "/getCategorias", method = RequestMethod.GET)
    @ApiOperation(value = "Retorna todas las categorias",
	  notes = "Retorna todos las categorias almacenadas en base de datos",
	  response = LibroBibliotecaDTO.class,
	  responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Categorias retornadas correctamente")})
    public List<CategoriaDTO> getAllCategorias() throws SQLException {


		List<CategoriaDTO> listaCategorias = clienteCategorias.obtenerCategorias();
		return listaCategorias;
    }

    /**
     * Retorna todos las editoriales
     * @return Listado de editoriales
     * @throws SQLException
     */
    @RequestMapping(value = "/getEditoriales", method = RequestMethod.GET)
    @ApiOperation(value = "Retorna todas las editoriales",
	  notes = "Retorna todos las editoriales almacenadas en base de datos",
	  response = LibroBibliotecaDTO.class,
	  responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Editoriales retornadas correctamente")})
    public List<EditorialDTO> getAllEditoriales() throws SQLException {


		List<EditorialDTO> listaEditoriales = clienteEditoriales.obtenerEditoriales();
		return listaEditoriales;
    }

    /**
     * Elimina el Autor
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
    	AutorDTO autor = AutorDTO.builder().id(idAutor).build();
    	LibroDTO libroAutor = LibroDTO.builder().autor(autor).build();
    	List<LibroDTO> listaBiblioteca = clienteLibros.obtenerLibrosByExample(libroAutor);
    	if (listaBiblioteca.isEmpty()) {
    		log.debug("Se elimina el autor.");
    		clienteAutores.eliminarAutor(idAutor.toString());
    		return HttpStatus.OK;
    	} else {
    		log.debug("No se puede eliminar el autor ya que tiene libros en la biblioteca.");
    		return HttpStatus.NOT_ACCEPTABLE;
    	}

    }


    /**
     * Elimina el Autor
     * @throws SQLException
     */
    @RequestMapping(value = "/getLibro/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Obtiene el libro del id",
	  notes = "Obtiene el Lirbo del id especificado",
	  response = LibroBibliotecaDTO.class)
    public ResponseEntity<LibroBibliotecaDTO> getLibro(@ApiParam(name = "id", value = "Id del Libro a buscar", required = true)@PathVariable("id") String id) throws SQLException {

    	try
    	{
    		Integer idLibro = Integer.parseInt(id);
    	} catch (NumberFormatException ex) {
    		log.error("Se ha producido un error, el id no es un valor numerico:" + ex.getMessage());
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LibroBibliotecaDTO());
    	}
    	LibroDTO libroDTO = clienteLibros.obtenerLibro(id);
     	if (libroDTO != null)
    	{
     		LibroBibliotecaDTO bibliotecaLibro = obtenerValoresLibro(libroDTO);

    		return ResponseEntity.status(HttpStatus.OK).body(bibliotecaLibro);
    	} else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LibroBibliotecaDTO());
    	}

    }


	private List<LibroBibliotecaDTO> getListaBiblioteca(List<LibroDTO> listaBiblioteca) {

		List<LibroBibliotecaDTO> librosBiblioteca = new ArrayList<LibroBibliotecaDTO>();
		log.debug("Total de Libros Obtenidos:" + listaBiblioteca.size());
		for(LibroDTO libro : listaBiblioteca) {
			log.debug("Libro:" + libro);
			LibroBibliotecaDTO bibliotecaLibro = obtenerValoresLibro(libro);
			librosBiblioteca.add(bibliotecaLibro);
		}
		return librosBiblioteca;
	}

	private LibroBibliotecaDTO obtenerValoresLibro(LibroDTO libro) {
		log.debug("Se obtienen los datos del libro:" + libro);
		return LibroBibliotecaDTO.builder()
							.id(libro.getId())
							.titulo(libro.getTitulo())
							.descripcion(libro.getDescripcion())
							.categoria(libro.getCategoria().getNombre())
							.editorial(libro.getEditorial().getNombre())
							.autor(libro.getAutor().getNombre())
							.build();
  }

}
