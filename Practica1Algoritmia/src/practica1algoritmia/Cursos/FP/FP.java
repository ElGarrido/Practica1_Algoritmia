package practica1algoritmia.Cursos.FP; // Ojo, puedes ponerla en .Cursos directamente si quieres

import practica1algoritmia.Cursos.Curso;

public class FP extends Curso {
    
    
    public static enum Especialidad{
        MECANICA,
        ELECTRONICA,
        INFORMATICA
    }
    
    private Especialidad especialidad;
    
    public FP(String codigo, String nombre, Especialidad especialidad) {
        super(codigo, nombre); 

        this.especialidad = especialidad;
    }
    
    public Especialidad getEspecialidad() {
        return especialidad;
    }

    @Override
    public String getDescripcionTipo() {
        return "Formación Profesional (" + especialidad + ")";
    }

    @Override
    public String toString() {

        return "FP [" + codi + "] " + nombre + " - Especialidad: " + especialidad;
    }
    
}