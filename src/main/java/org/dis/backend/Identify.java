package org.dis.backend;

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



}
