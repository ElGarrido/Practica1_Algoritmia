package practica1algoritmia.Cursos.Bachiller;

import practica1algoritmia.Asignaturas.Asignatura;

import practica1algoritmia.Cursos.Curso;

public class Bachiller extends Curso {

    public static enum Nivel {
        PRIMERO,
        SEGUNDO
    }

    private Nivel nivel; // Primero o Segundo

    public Bachiller(String codigo, String nombre, Nivel nivel) {
        super(codigo, nombre);
        this.nivel = nivel;
    }

    public Nivel getNivel() {
        return nivel;
    }

    @Override
    public String toString() {
        return "Bachiller [" + codi + "] " + nombre + " - Curso: " + nivel;
    }

    @Override
    public String getDescripcionTipo() {
        return "Bachillerato (" + nivel + ")";
    }

}
