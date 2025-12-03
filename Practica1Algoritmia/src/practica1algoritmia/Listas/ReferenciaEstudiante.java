
package practica1algoritmia;

public class ReferenciaEstudiante implements Interface_Elemento {
    
    private Estudiante estudiante;
    
    public ReferenciaEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
    public Estudiante getEstudiante() {
        return estudiante;
    }

    // --- Implementación de Interface_Elemento ---
    
    @Override
    public String getIdentificador() {
        // Devolvemos el nombre (o DNI) para que la lista de la asignatura
        // se ordene alfabéticamente por nombre de estudiante 
        return estudiante.getNombre(); 
    }

    @Override
    public String toString() {
        return estudiante.toString();
    }
}