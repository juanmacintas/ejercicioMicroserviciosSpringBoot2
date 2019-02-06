//package es.ejercicio.microservicios.ui;
//
//import java.awt.Panel;
//
//import com.vaadin.flow.component.UI;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.page.Page;
//
//@SpringUI
//@SpringViewDisplay
//public class BilbliotecaUI extends UI implements ViewDisplay {
//
//	private static final long serialVersionUID = -5510796932223511405L;
//
//	Navigator navigator;
//
//	private Panel springViewDisplay;
//
//	@Override
//	protected void init(VaadinRequest request) {
//		Page.getCurrent().setTitle("Biblioteca");
//
//		final VerticalLayout root = new VerticalLayout();
//		root.setSizeFull();
//		setContent(root);
//
//		VerticalLayout banner = new VerticalLayout();
//		banner.addStyleNames("banner");
//		banner.setId("banner");
//		banner.setWidth(100, Unit.PERCENTAGE);
//		banner.setSpacing(false);
//
//		root.addComponent(banner);
//
//		springViewDisplay = new Panel();
//		springViewDisplay.setSizeFull();
//		root.addComponent(springViewDisplay);
//		root.setExpandRatio(springViewDisplay, 1.0f);
//
//	}
//
//	@Override
//	public void showView(View view) {
//		springViewDisplay.setContent((Component) view);
//
//	}
//
//}
