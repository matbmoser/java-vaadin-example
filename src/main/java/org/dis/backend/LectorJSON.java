package org.dis.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LectorJSON {
    public static boolean write(List<Persona> personas){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(personas);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("Personas.json"));
            writer.write(json);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static List<Persona> read(String path){
        List<Persona> personas = new ArrayList<>();
        try {
            personas = new Gson().fromJson(new FileReader(path), new TypeToken<List<Persona>>(){}.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return personas;
    }

}
