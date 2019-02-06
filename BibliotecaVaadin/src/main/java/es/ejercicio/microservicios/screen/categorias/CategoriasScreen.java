package es.ejercicio.microservicios.screen.categorias;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

import es.ejercicio.microservicios.dto.CategoriaDTO;
import es.ejercicio.microservicios.rest.RestClient;
import es.ejercicio.microservicios.screen.BibliotecaMainLayout;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Route(value = CategoriasScreen.ROOT_CATEGORIAS,layout = BibliotecaMainLayout.class)
public class CategoriasScreen extends VerticalLayout  {

	public static final String ROOT_CATEGORIAS = "categorias";

	private static final long serialVersionUID = 2851204137357460687L;

	private Grid<CategoriaDTO> gridCategoria;
	private ListDataProvider<CategoriaDTO> dataProvider;

	RestClient restClient;

	public CategoriasScreen(@Autowired RestClient rClient) {
		log.debug("Inicio Biblioteca Categorias");
		restClient = rClient;
		initLayout();
		initData();
		add(new VerticalLayout(new Label("Categorías"), gridCategoria));
		log.debug("Fin Biblioteca Categorias");
	}

	private void initLayout() {

		gridCategoria = new Grid<>();

		gridCategoria.addColumn(CategoriaDTO::getId).setHeader("Id");
		gridCategoria.addColumn(CategoriaDTO::getNombre).setHeader("Nombre");
		gridCategoria.setEnabled(true);
		gridCategoria.setSelectionMode(SelectionMode.SINGLE);

	}

	private void initData() {
	    dataProvider = DataProvider.ofCollection(restClient.obtenerCategoriasBiblioteca());
	    log.debug("Data Provider:" + dataProvider);
	    gridCategoria.setDataProvider(dataProvider);
	}

}
