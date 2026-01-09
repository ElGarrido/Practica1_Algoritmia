package practica1algoritmia.Listas;

import practica1algoritmia.Interfaces.Interface_Elemento;
import practica1algoritmia.Estudiante;

public class ReferenciaEstudiante implements Interface_Elemento {

    private Estudiante estudiante;

    public ReferenciaEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    @Override
    public String getIdentificador() {
        // Devolvemos el nombre para que la lista de la asignatura
        // se ordene alfabéticamente por nombre de estudiante 
        return estudiante.getNombre();
    }

    @Override
    public String toString() {
        return estudiante.toString();
    }

    @Override
    public String getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
