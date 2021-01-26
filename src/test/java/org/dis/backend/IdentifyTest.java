package org.dis.backend;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IdentifyTest{

    List<Persona> personas;

    @Before
    public void setUp() {
        personas = new ArrayList<>();
        Persona p1 = new Persona(25, "Mario Bustos", "Masculino", false);
        p1.addAficcion("Skate");
        p1.addAficcion("Surf");
        Persona p2 = new Persona(45, "Maria Tereza", "Femenino", true);
        p2.addAficcion("Cocina");
        p2.addAficcion("Musica");
        p2.addAficcion("Cine");
        Persona p3 = new Persona(52, "Daniela Rozana", "Femenino", false);
        personas.add(p1);
        personas.add(p2);
        personas.add(p3);
        assertEquals(3,personas.size());
    }

    @Test
    public void testNombreIgual() throws PException {
        int res;
        res = Identify.nombre("Maria Tereza", personas);
        assertEquals(1, res);
    }

    @Test (expected = PException.class)
    public void testNombreVacio() throws PException {
       Identify.nombre("", personas);
    }

    @Test
    public void testNombreEscritoDiferente() throws PException {
        int res;
        res = Identify.nombre("mArIa tEREzA", personas);
        assertEquals(1, res);
    }
    @Test
    public void testNombreNoIgual() throws PException {
        int res;
        res = Identify.nombre("MariaTereza", personas);
        assertEquals(-1, res);
    }
}