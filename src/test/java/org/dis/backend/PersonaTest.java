package org.dis.backend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PersonaTest {
    Persona persona;

    @Before
    public void setUp() throws Exception {
        persona = new Persona(15, "José Manuel", "Masculino", false);
    }

    @Test
    public void addAficcionesValidas() {
        persona.setAfficones(new ArrayList<>());
        persona.addAficcion("Comer");
        persona.addAficcion("Dormir");
        persona.addAficcion("Estudiar");
        persona.addAficcion("Jugar");
        assertEquals(4, persona.getAfficones().size());
    }

    @Test
    public void addAficcionesValidasyNoValidas() {
        persona.setAfficones(new ArrayList<>());
        persona.addAficcion("Comer");
        persona.addAficcion("");
        persona.addAficcion("Estudiar");
        persona.addAficcion("");
        assertEquals(2, persona.getAfficones().size());
    }


}