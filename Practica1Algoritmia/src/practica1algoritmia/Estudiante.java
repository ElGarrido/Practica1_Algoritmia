package practica1algoritmia;

import practica1algoritmia.Interfaces.Interface_Elemento;
import practica1algoritmia.Asignaturas.Asignatura;
import practica1algoritmia.Listas.ListaRefAsig_Estud;

public class Estudiante implements Interface_Elemento {

    private String nombre;
    private String DNI;

    public ListaRefAsig_Estud asignaturasCursadas;

    public Estudiante(String nombre, String DNI) {
        this.nombre = nombre;
        this.DNI = DNI;

        // Lista de referencias
        this.asignaturasCursadas = new ListaRefAsig_Estud();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return DNI;
    }

    
    @Override
    public String getIdentificador() {
        return DNI;
    }

    @Override
    public String toString() {
        return "Estudiante: " + nombre + " | DNI: " + DNI;
    }
}
