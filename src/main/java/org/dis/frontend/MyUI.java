package org.dis.frontend;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.Position;
import com.vaadin.ui.*;
import org.dis.backend.Identify;
import org.dis.backend.PException;
import org.dis.backend.Persona;

import java.util.ArrayList;
import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        List<Persona> personas = new ArrayList<>();
        Persona p1 = new Persona(25, "Mario Bustos", "Masculino", false);
        p1.addAficcion("Skate");
        p1.addAficcion("Surf");
        Persona p2 = new Persona(45, "Maria Tereza", "Femenino", true);
        p2.addAficcion("Cocina");
        p2.addAficcion("Musica");
        p2.addAficcion("Cine");
        Persona p3 = new Persona(52, "Daniela Rozana", "Femenino", false);
        p3.addAficcion("Bailar");
        Persona p4 = new Persona(83, "Rodrigo Madalosso", "Masculino", true);
        personas.add(p1);
        personas.add(p2);
        personas.add(p3);
        personas.add(p4);
        final VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        final VerticalLayout mainContainer = new VerticalLayout();
        final VerticalLayout gridContainer = new VerticalLayout();
        final TextField name = new TextField();
        name.setCaption("Buscar Nombre: ");

        Button button = new Button("Buscar", VaadinIcons.SEARCH);
        button.addClickListener(e -> {
            if(!name.isEmpty()) {
                try {
                    int num = Identify.nombre(name.getValue(), personas);
                    if(num != -1){
                        Notification notif = new Notification("<span style='color:green'>Sucess</span>", "¡"+name.getValue() + " es la " + (num+1) + "ª persona de la lista!", Notification.Type.HUMANIZED_MESSAGE);
                        notif.setDelayMsec(10000);
                        notif.setHtmlContentAllowed(true);
                        notif.setPosition(Position.TOP_CENTER);
                        notif.setIcon(VaadinIcons.CHECK);
                        notif.show(Page.getCurrent());
                    }else{
                        Notification notif = new Notification("Lo sentimos","La persona llamada "+ name.getValue() + " no ha sido encontrada...",Notification.Type.WARNING_MESSAGE);
                        notif.setDelayMsec(2000);
                        notif.setPosition(Position.TOP_CENTER);
                        notif.setIcon(VaadinIcons.WARNING);
                        notif.show(Page.getCurrent());
                    }
                } catch (PException pException) {
                    Notification notif = new Notification("Warning",pException.getMessage(),Notification.Type.WARNING_MESSAGE);
                    notif.setDelayMsec(2000);
                    notif.setPosition(Position.TOP_CENTER);
                    notif.setIcon(VaadinIcons.WARNING);
                    notif.show(Page.getCurrent());
                }
            }else{
                Notification notif = new Notification("Warning","No puede estar vacio!",Notification.Type.WARNING_MESSAGE);
                notif.setDelayMsec(2000);
                notif.setPosition(Position.TOP_CENTER);
                notif.setIcon(VaadinIcons.WARNING);
                notif.show(Page.getCurrent());
            }
        });

        Grid<Persona> gridPersonas = new Grid<>();
        gridPersonas.setItems(personas);
        gridPersonas.addColumn(Persona::getNombre).setCaption("Nombre");
        gridPersonas.addColumn(Persona::getEdad).setCaption("Edad");
        gridPersonas.addColumn(Persona::getSexo).setCaption("Sexo");
        gridPersonas.addColumn(Persona::getAficcion).setCaption("Aficciones");
        gridContainer.addComponent(gridPersonas);
        gridContainer.setSizeFull();
        gridPersonas.setSizeFull();
        TextArea textArea = new TextArea("Tu ip actual es: "+ Identify.userIp());
        mainContainer.addComponents(name, button);
        layout.addComponents(gridContainer,mainContainer);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
