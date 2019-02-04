package es.ejercicio.microservicios.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Juan Manuel Cintas
 *
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibroDTO {

	@ApiModelProperty(notes = "ID del Libro. Clave primaria", required = true)
    private int id;

	@ApiModelProperty(notes = "Título del Libro")
    private String titulo;

	@ApiModelProperty(notes = "Descripción del Libro")
    private String descripcion;

	@ApiModelProperty(notes = "ID Categoría del Libro")
    private CategoriaDTO categoria;

	@ApiModelProperty(notes = "ID Autor del Libro")
    private AutorDTO autor;

	@ApiModelProperty(notes = "ID Editorial del Libro")
    private EditorialDTO editorial;

	@ApiModelProperty(notes = "Indicador Favorito")
    private Boolean favorite;

}
