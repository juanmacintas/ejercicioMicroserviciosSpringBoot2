package es.ejercicio.microservicios.screen;

import java.util.List;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteData;
import com.vaadin.flow.router.Router;


@Route(value = IndexScreen.ROOT_NAME, layout = BibliotecaMainLayout.class)
public class IndexScreen extends VerticalLayout{

	public static final String ROOT_NAME = "";

	private static final long serialVersionUID = 6092429507520923718L;

	public IndexScreen() {
		add(new Label("Main View"));

        Router router = UI.getCurrent().getRouter();
        List<RouteData> routes = router.getRoutes();

        routes.forEach(System.out::println);
	}

}
