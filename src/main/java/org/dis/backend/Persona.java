package org.dis.backend;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    private int edad;
    private String nombre;
    private String sexo;
    private boolean casado;
    private List<String> Aficciones;

    public Persona(int edad, String nombre, String sexo, boolean casado) {
        this.edad = edad;
        this.nombre = nombre;
        this.sexo = sexo;
        this.casado = casado;
        Aficciones = new ArrayList<>();
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public boolean isCasado() {
        return casado;
    }

    public void setCasado(boolean casado) {
        this.casado = casado;
    }
    public void addAficcion(String aficcion){
        if(!aficcion.isEmpty()) {
            Aficciones.add(aficcion);
        }
    }
    public String getAficcion(){
        if(Aficciones.toString().equals("[]")){
            return "Sin Aficciones";
        }else {
            String res =  Aficciones.toString();
            res = res.replace("]", "");
            res = res.replace("[", "");
            return res;
        }
    }

    public List<String> getAfficones() {
        return Aficciones;
    }

    public void setAfficones(List<String> aficciones) {
        Aficciones = aficciones;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "edad=" + edad +
                ", nombre='" + nombre + '\'' +
                ", sexo='" + sexo + '\'' +
                ", casado=" + casado +
                ", Afficones=" + Aficciones +
                '}';
    }

    public Persona() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

}
