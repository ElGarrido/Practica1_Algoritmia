package practica1algoritmia.Cursos;

import practica1algoritmia.Asignaturas.Asignatura;
import practica1algoritmia.Interfaces.Interface_Elemento;
import practica1algoritmia.Listas.ListaAsignatura;

public abstract class Curso implements Interface_Elemento {

    public ListaAsignatura asignaturasDelCurso;
    protected String nombre;
    protected String codi;

    public Curso(String codigo, String nombre) {
        this.codi = codigo;
        this.nombre = nombre;
        this.asignaturasDelCurso = new ListaAsignatura();
    }

    public String getCodi() {
        return this.codi;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    @Override
    public String getIdentificador() {
        return codi;
    }
    
    // Obliga a los hijos a poner su tipo (si es fp (mecanica...), si es bachiller (primero...))
    public abstract String getDescripcionTipo();
}
