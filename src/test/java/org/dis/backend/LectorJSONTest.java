package org.dis.backend;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LectorJSONTest {

    List<Persona> personas;
    String json;
    @Before
    public void setUp(){

        json = "[ { \"edad\":35,\"nombre\":\"Mario Casagrande\", \"sexo\": \"Masculino\", \"casado\":false, \"Aficciones\":[ \"Comer\", \"Cenar\", \"Correr\" ] }," +
                " { \"edad\":48,\"nombre\":\"Laura Hoffman\", \"sexo\": \"Femenino\", \"casado\":true, \"Aficciones\":[ \"Cocinar\" ] } ]";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("testJson.json"));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
        personas = new ArrayList<>();
    }

    @Before
    public void readJson() {
        try {
            personas = LectorJSON.read("testJson.json");
            System.out.println("Resultado Read: \n\n"+personas.toString()+"\n\n");
        } catch (PException e) {
            fail(e.getMessage());
        }
    }
    @Before
    public void writeJson() {
        try {
            assertTrue(LectorJSON.write(personas, "testJson.json"));
            Gson gson = new Gson();
            FileReader fileReader = new FileReader("testJson.json");
            JsonElement json = gson.fromJson(fileReader, JsonElement.class);
            String result = gson.toJson(json);
            fileReader.close();
            System.out.println("Resultado Write: \n\n"+result+"\n\n");
        } catch (PException | IOException e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void writenewJson() {
        List<Persona> misPersonas = new ArrayList<>();
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
        misPersonas.add(p1);
        misPersonas.add(p2);
        misPersonas.add(p3);
        misPersonas.add(p4);
        try {
            assertTrue(LectorJSON.write(misPersonas, "newtestJson.json"));
        } catch (PException e) {
            fail(e.getMessage());
        }
    }
    @After
    public void tearDown(){
        File f1 = new File("newtestJson.json");
        File f2 = new File("testJson.json");
        assertTrue(f1.delete()&&f2.delete());
    }

}