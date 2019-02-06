package es.ejercicio.microservicios.screen.editoriales;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

import es.ejercicio.microservicios.dto.EditorialDTO;
import es.ejercicio.microservicios.rest.RestClient;
import es.ejercicio.microservicios.screen.BibliotecaMainLayout;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Route(value = EditorialesScreen.ROOT_EDITORIALES,layout = BibliotecaMainLayout.class)
public class EditorialesScreen extends VerticalLayout  {

	public static final String ROOT_EDITORIALES = "editoriales";

	private static final long serialVersionUID = 2851204137357460687L;

	private Grid<EditorialDTO> gridEditorial;
	private ListDataProvider<EditorialDTO> dataProvider;

	@Autowired
	RestClient restClient;


	public EditorialesScreen(@Autowired RestClient rClient) {
		log.debug("Inicio Biblioteca Editoriales");
		restClient = rClient;
		initLayout();
		initData();
		add(new VerticalLayout(new Label("Editoriales"), gridEditorial));
		log.debug("Fin Biblioteca Editoriales");
	}

	private void initLayout() {

		gridEditorial = new Grid<>();

		gridEditorial.addColumn(EditorialDTO::getId).setHeader("Id");
		gridEditorial.addColumn(EditorialDTO::getNombre).setHeader("Nombre");
		gridEditorial.setEnabled(true);
		gridEditorial.setSelectionMode(SelectionMode.SINGLE);

	}

	private void initData() {
	    dataProvider = DataProvider.ofCollection(restClient.obtenerEditorialesBiblioteca());
	    gridEditorial.setDataProvider(dataProvider);
	}

}
