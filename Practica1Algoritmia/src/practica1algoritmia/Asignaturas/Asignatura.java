package practica1algoritmia.Asignaturas;

import practica1algoritmia.Interfaces.Interface_Elemento;
import practica1algoritmia.Listas.ListaRefAsig_Estud;
import practica1algoritmia.Cursos.Curso;

public abstract class Asignatura implements Interface_Elemento {

    protected String identificador;
    protected String nombre;
    
    public ListaRefAsig_Estud alumnosMatriculados;
    
    private Curso cursoPadre;
    

    public Asignatura(String nombre, String identificador, Curso cursoPadre) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.cursoPadre = cursoPadre;
        
        this.alumnosMatriculados = new ListaRefAsig_Estud();
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
    
    public abstract String getDescripcionExtra();
}
