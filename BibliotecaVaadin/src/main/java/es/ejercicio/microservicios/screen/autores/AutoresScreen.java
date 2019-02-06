package es.ejercicio.microservicios.screen.autores;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

import es.ejercicio.microservicios.dto.AutorDTO;
import es.ejercicio.microservicios.rest.RestClient;
import es.ejercicio.microservicios.screen.BibliotecaMainLayout;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Route(value = AutoresScreen.ROOT_AUTORES,layout = BibliotecaMainLayout.class)
public class AutoresScreen extends VerticalLayout {

	public static final String ROOT_AUTORES = "autores";

	private static final long serialVersionUID = 2851204137357460687L;

	private Grid<AutorDTO> gridAutores;
	private ListDataProvider<AutorDTO> dataProvider;

	@Autowired
	RestClient restClient;


	public AutoresScreen(@Autowired RestClient rClient) {
		log.debug("Inicio Biblioteca Autores");
		restClient = rClient;
		initLayout();
		initData();
		add(new VerticalLayout(new Label("Autores"), gridAutores));
		log.debug("Fub Biblioteca Autores");
	}

	private void initLayout() {

		gridAutores = new Grid<>();

		gridAutores.addColumn(AutorDTO::getId).setHeader("Id");
		gridAutores.addColumn(AutorDTO::getNombre).setHeader("Nombre");
		gridAutores.setEnabled(true);
		gridAutores.setSelectionMode(SelectionMode.SINGLE);

	}

	private void initData() {
	    dataProvider = DataProvider.ofCollection(restClient.obtenerAutoresBiblioteca());
	    gridAutores.setDataProvider(dataProvider);
	}
}
