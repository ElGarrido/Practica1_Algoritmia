
package practica1algoritmia.Asignaturas;

import practica1algoritmia.Nodo;


public class Asignatura {
    String courseId;
    String identificador;
    Nodo estudiantes;
    
    public Asignatura(String identificador, Nodo estudiantes, String courseId) {
        this.identificador = identificador;
        this.estudiantes = estudiantes;
        this.courseId = courseId;
        
    }
    public String getIdentificador() {
        return identificador;
    }
}
