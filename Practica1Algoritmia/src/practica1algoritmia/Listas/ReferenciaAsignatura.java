
package practica1algoritmia.Listas;

import practica1algoritmia.Interfaces.Interface_Elemento;

public class ReferenciaAsignatura implements Interface_Elemento {
    
    private Asignatura asignatura;
    
    public ReferenciaAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }
    
    public Asignatura getAsignatura() {
        return asignatura;
    }

    // --- Implementación de Interface_Elemento ---
    
    @Override
    public String getIdentificador() {
        // Devolvemos el código de la asignatura para que la lista del estudiante
        // se ordene por código de asignatura.
        return asignatura.getIdentificador(); 
    }

    @Override
    public String toString() {
        // Cuando listes al estudiante, esto imprimirá los datos de la asignatura
        return asignatura.toString();
    }
}
