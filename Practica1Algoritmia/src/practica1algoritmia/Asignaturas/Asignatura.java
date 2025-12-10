package practica1algoritmia.Asignaturas;

import practica1algoritmia.Interfaces.Interface_Elemento;

public class Asignatura implements Interface_Elemento {

    private String identificador;
    private String nombre;

    public Asignatura(String nombre, String identificador) {
        this.identificador = identificador;
        this.nombre = nombre;
    }

    @Override
    public String getIdentificador() {
        return identificador;
    }

    public String getNombre(){
        return nombre;
    }

    @Override
    public String toString() {
        return "[" + identificador + "] " + nombre;
    }
}
