//package es.ejercicio.microservicios.screen;
//
//import java.util.Iterator;
//import java.util.Optional;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.stereotype.Component;
//
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@Slf4j
//public abstract class AbstractBibliotecaScreen extends VerticalLayout {
//
//	private static final long serialVersionUID = -46212438974612614L;
//
//	@PostConstruct
//	public void init() {
//		log.info("Inicio {}", this.getClass().getName());
//
//		this.removeAllComponents();
//
//		this.addComponent(new BibliotecaMenuBar());
//
//		initScreen();
//
//		log.info("Fin {}", this.getClass().getName());
//
//	}
//
//	public abstract void initScreen();
//
//	protected Optional<Component> getComponentById(String id,
//			Iterator<Component> itComponent) {
//
//		while (itComponent.hasNext()) {
//			Component component = itComponent.next();
//			if (id.equals(component.getId())) {
//				return Optional.of(component);
//			} else if (component instanceof HasComponents) {
//				return getComponentById(id, ((HasComponents) component).iterator());
//			}
//		}
//
//		return Optional.empty();
//	}
//
//}
