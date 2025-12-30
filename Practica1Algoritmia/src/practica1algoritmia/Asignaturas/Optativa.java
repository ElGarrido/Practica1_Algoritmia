package practica1algoritmia.Asignaturas;

import practica1algoritmia.Cursos.Curso;

public class Optativa extends Asignatura {
    
    public enum Perfil { 
        TEORICO, 
        PRACTICO 
    }
    
    private Perfil perfil;

    public Optativa(String nombre, String identificador, Curso cursoPadre, Perfil perfil) {
        super(nombre, identificador, cursoPadre);
        this.perfil = perfil;
    }

    @Override
    public String getDescripcionExtra() {
        return "Tipo: Optativa | Perfil: " + perfil;
    }
    
    @Override
    public String toString() {
        return "[" + identificador + "] " + nombre + " (Optativa - " + perfil + ")";
    }
}