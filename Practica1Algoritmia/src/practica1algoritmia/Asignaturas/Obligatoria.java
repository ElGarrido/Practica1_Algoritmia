package practica1algoritmia.Asignaturas;

import practica1algoritmia.Cursos.Curso;

public class Obligatoria extends Asignatura {
    
    private int creditos; 

    public Obligatoria(String nombre, String identificador, Curso cursoPadre, int creditos) {
        super(nombre, identificador, cursoPadre);
        this.creditos = creditos;
    }

    @Override
    public String getDescripcionExtra() {
        return "Tipo: Obligatoria | Créditos: " + creditos;
    }
    
    @Override
    public String toString() {
        return "[" + identificador + "] " + nombre + " (Obligatoria - " + creditos + " cr)";
    }
}