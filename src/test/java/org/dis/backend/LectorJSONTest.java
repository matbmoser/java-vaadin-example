package org.dis.backend;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.DEFAULT)
public class LectorJSONTest {

    List<Persona> personas;
    String json;
    @Before
    public void setUp(){

        json = "[ { 'edad':35,'nombre':'Mario Casagrande', 'sexo': 'Masculino', 'casado':false, 'Aficciones':[ 'Comer', 'Cenar', 'Correr' ] }," +
                " { 'edad':48,'nombre':'Laura Hoffman', 'sexo': 'Femenino', 'casado':true, 'Aficciones':[ 'Cocinar' ] }";
        try {
            FileWriter fileWriter = new FileWriter("testJson.json");
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
        personas = new ArrayList<>();
    }

    @Test
    public void readJson() {
        try {
            personas = LectorJSON.read("testJson.json");
        } catch (PException e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    public void writeJson() {
        try {
            assertTrue(LectorJSON.write(personas, "testJson.json"));
        } catch (PException e) {
            e.printStackTrace();
            fail();
        }
    }
    public void writenewJson() {
        List<Persona> misPersonas = new ArrayList<>();

        try {
            assertTrue(LectorJSON.write(misPersonas, "newtestJson.json"));
        } catch (PException e) {
            e.printStackTrace();
            fail();
        }
    }

    @After
    public void tearDown(){

    }
}