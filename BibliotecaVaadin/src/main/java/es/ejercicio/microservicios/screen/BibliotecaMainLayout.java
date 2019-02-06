package es.ejercicio.microservicios.screen;

import com.vaadin.flow.component.applayout.AbstractAppRouterLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

@Theme(value = Material.class, variant = "light")
@Push
public class BibliotecaMainLayout extends AbstractAppRouterLayout {

	@Override
	protected void configure(AppLayout appLayout, AppLayoutMenu appLayoutMenu) {
        appLayout.setBranding(new H3("Biblioteca App Layout"));

        appLayoutMenu.addMenuItem(VaadinIcon.HOME.create(), "Home", "");
        appLayoutMenu.addMenuItem(VaadinIcon.BOOK.create(), "Libros", "libros");
        appLayoutMenu.addMenuItem(VaadinIcon.USER.create(), "Autores", "autores");
        appLayoutMenu.addMenuItem(VaadinIcon.PICTURE.create(), "Editoriales", "editoriales");
        appLayoutMenu.addMenuItem(VaadinIcon.GLOBE.create(), "Categorias", "categorias");
	}

}
