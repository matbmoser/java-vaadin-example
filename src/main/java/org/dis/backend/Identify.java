package org.dis.backend;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;

import java.util.List;

public class Identify {

    public static int nombre(String nombre, List<Persona> personas) throws PException {
        int i = 0;
            if(!nombre.isEmpty()) {
                for (Persona persona : personas) {

                    if (persona.getNombre().equalsIgnoreCase(nombre)) {
                        return i;
                    }
                    i++;
                }
            }else{
                throw new PException("El nombre está vacio");
            }
        return -1;
    }
    public static String userIp(){
        String IP = "127.0.0.1";

        IP = Page.getCurrent().getWebBrowser().getAddress();

        return IP;
    }
    public static String realuserIp(){
        String IP = "127.0.0.1";

        IP = VaadinRequest.getCurrent().getHeader("X-Forwarded-For");

        return IP;
    }
}
