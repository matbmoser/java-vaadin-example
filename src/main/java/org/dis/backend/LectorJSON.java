package org.dis.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LectorJSON {
    public static boolean write(List<Persona> personas, String path) throws PException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(personas);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path));
            writer.write(json);
            writer.close();
            return true;
        } catch (IOException e) {
            throw new PException("No ha sido posible escribir el fichero!");
        }
    }
    public static List<Persona> read(String path) throws PException {
        List<Persona> personas;
        try {
            personas = new Gson().fromJson(new FileReader(path), new TypeToken<List<Persona>>(){}.getType());
        } catch (FileNotFoundException e) {
            throw new PException("No ha sido posible leer el fichero!");
        }
        return personas;
    }

}
