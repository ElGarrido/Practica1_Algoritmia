package practica1algoritmia.Listas;

import practica1algoritmia.Interfaces.Interface_Lista;
import practica1algoritmia.Interfaces.Interface_Elemento;
import practica1algoritmia.Nodo;

public class ListaEstudiante implements Interface_Lista {

    private Nodo primero;   // Cabecera
    
    
    public ListaEstudiante() {
        this.primero = null; // Crear lista
        
    }

    // ----------------------------------------------
    // INSERTAR ESTUDIANTE ORDENADO POR IDENTIFICADOR
    // ----------------------------------------------
    @Override
    public void insertar(Object elemento) {

        if (!(elemento instanceof Interface_Elemento)) {
            System.out.println("Error: El objeto no es válido para esta lista.");
            return;
        }

        Interface_Elemento nuevoElem = (Interface_Elemento) elemento;
        Nodo nuevoNodo = new Nodo(nuevoElem);

        // Insertar al principio (Lista vacía o es el menor de todos)
        if (primero == null
                || nuevoElem.getNombre().compareTo(((Interface_Elemento) primero.getInfo()).getNombre()) < 0) {
            nuevoNodo.setSeg(primero);
            primero = nuevoNodo;
            return;
        }

        // Recorrer para buscar el hueco
        Nodo actual = primero;

        // Avanzamos mientras haya siguiente y el siguiente sea menor que el nuevo
        while (actual.getSeg() != null) {
            Interface_Elemento infoSiguiente = (Interface_Elemento) actual.getSeg().getInfo();

            // Si encontramos uno igual, es un DUPLICADO. Salimos.
            if (infoSiguiente.getNombre().equals(nuevoElem.getNombre())) {
                System.out.println("Error: Ya existe ID " + nuevoElem.getNombre());
                return;
            }

            // Si el siguiente ya es mayor que el nuevo, paramos aquí
            if (infoSiguiente.getNombre().compareTo(nuevoElem.getNombre()) > 0) {
                break;
            }

            actual = actual.getSeg();
        }

        // Si el duplicado era el primero 
        if (((Interface_Elemento) primero.getInfo()).getNombre().equals(nuevoElem.getNombre())) {
            System.out.println("Error: Ya existe ID " + nuevoElem.getNombre());
            return;
        }

        // Insertamos el nodo en el hueco encontrado (entre actual y actual.siguiente)
        nuevoNodo.setSeg(actual.getSeg());
        actual.setSeg(nuevoNodo);
    }

    // ----------------------------------------------
    //     ELIMINAR ESTUDIANTE POR IDENTIFICADOR
    // ----------------------------------------------
    @Override
    public void eliminar(String identificador) {
        if (primero == null) {
            return;
        }

        // 1. Borrar el primero si coincide
        // Hacemos casting
        Interface_Elemento infoPrimero = (Interface_Elemento) primero.getInfo();
        if (infoPrimero.getIdentificador().equals(identificador)) {
            primero = primero.getSeg();
            System.out.println("Eliminado: " + identificador);
            return;
        }

        // Borrar en el resto de la lista
        Nodo actual = primero;

        // Mientras haya un "siguiente" nodo, miramos si es el que buscamos
        while (actual.getSeg() != null) {
            Interface_Elemento infoSiguiente = (Interface_Elemento) actual.getSeg().getInfo();

            if (infoSiguiente.getIdentificador().equals(identificador)) {
                // Saltamos el nodo 
                actual.setSeg(actual.getSeg().getSeg());
                System.out.println("Eliminado: " + identificador);
                return;
            }
            actual = actual.getSeg();
        }

        System.out.println("No se encontró: " + identificador);
    }

    // ----------------------------------------------
    //      BUSCAR ESTUDIANTE POR IDENTIFICADOR
    // ----------------------------------------------
    @Override
    public Object buscar(String identificador) {

        Nodo actual = primero;

        while (actual != null) {
            // Hacemos el casting para poder preguntar por el ID
            if (((Interface_Elemento) actual.getInfo()).getIdentificador().equals(identificador)) {

                return actual.getInfo();
            } else {
                actual = actual.getSeg();
            }
        }
        return null; // Si llegamos al final y no estaba

    }

    // ----------------------------------------------
    //         LISTAR TODOS LOS ESTUDIANTES
    // ----------------------------------------------
    @Override
    public void listar() {

        Nodo aux;

        if (primero == null) {
            System.out.println("\nLa lista está vacía.");
            return;
        }

        for (aux = primero; aux != null; aux = aux.getSeg()) {
            System.out.print(aux.getInfo() + " -> ");

        }
        System.out.println("null");

    }

}
