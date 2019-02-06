package es.ejercicio.microservicios.screen.libros;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

import es.ejercicio.microservicios.dto.LibroBibliotecaDTO;
import es.ejercicio.microservicios.rest.RestClient;
import es.ejercicio.microservicios.screen.BibliotecaMainLayout;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Route(value = LibrosScreen.ROOT_LIBROS, layout = BibliotecaMainLayout.class)
public class LibrosScreen extends VerticalLayout {

	public static final String ROOT_LIBROS = "libros";

	private static final long serialVersionUID = 2851204137357460687L;

	private Grid<LibroBibliotecaDTO> gridLibros;
	private ListDataProvider<LibroBibliotecaDTO> dataProvider;

	@Autowired
	RestClient restClient;

	public LibrosScreen(@Autowired RestClient rClient) {
		log.debug("Inicio Biblioteca Libros");
		restClient = rClient;
		initLayout();
		initData();
		add(new VerticalLayout(new Label("Libros"), gridLibros));
		log.debug("Fin Biblioteca Libros");
	}

	private void initLayout() {

		gridLibros = new Grid<>();

		gridLibros.addColumn(LibroBibliotecaDTO::getId).setHeader("Id");
		gridLibros.addColumn(LibroBibliotecaDTO::getTitulo).setHeader("Título");
		gridLibros.addColumn(LibroBibliotecaDTO::getDescripcion).setHeader("Descripción");
		gridLibros.addColumn(LibroBibliotecaDTO::getAutor).setHeader("Autor");
		gridLibros.addColumn(LibroBibliotecaDTO::getCategoria).setHeader("Categoria");
		gridLibros.addColumn(LibroBibliotecaDTO::getEditorial).setHeader("Editorial");


		gridLibros.setEnabled(true);
		gridLibros.setSelectionMode(SelectionMode.SINGLE);

	}

	private void initData() {
	    dataProvider = DataProvider.ofCollection(restClient.obtenerLibrosBiblioteca());
	    gridLibros.setDataProvider(dataProvider);
	}

}
