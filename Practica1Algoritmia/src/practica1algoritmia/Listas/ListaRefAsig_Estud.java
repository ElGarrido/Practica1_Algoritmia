package practica1algoritmia.Listas;

import practica1algoritmia.Interfaces.Interface_Lista;
import practica1algoritmia.Interfaces.Interface_Elemento;
import practica1algoritmia.Nodo;

// Crear referencias entre Estudiantes y Asignaturas
public class ListaRefAsig_Estud implements Interface_Lista {

    private Nodo primero;

    public ListaRefAsig_Estud() {
        this.primero = null;
    }

    @Override
    public void insertar(Object elemento) {
        // VALIDACIÓN GENÉRICA
        if (!(elemento instanceof Interface_Elemento)) {
            return;
        }

        Interface_Elemento nuevoElem = (Interface_Elemento) elemento;
        Nodo nuevoNodo = new Nodo(nuevoElem);

        if (primero == null || nuevoElem.getIdentificador().compareTo(((Interface_Elemento) primero.getInfo()).getIdentificador()) < 0) {
            nuevoNodo.setSeg(primero);
            this.primero = nuevoNodo;
            return;
        }

        Nodo actual = primero;
        while (actual.getSeg() != null) {
            Interface_Elemento infoSig = (Interface_Elemento) actual.getSeg().getInfo();
            if (infoSig.getIdentificador().compareTo(nuevoElem.getIdentificador()) > 0) {
                break;
            }
            actual = actual.getSeg();
        }

        nuevoNodo.setSeg(actual.getSeg());
        actual.setSeg(nuevoNodo);
    }

    @Override
    public void eliminar(String identificador) {
        if (primero == null) {
            return;
        }

        Interface_Elemento infoPrimero = (Interface_Elemento) primero.getInfo();
        if (infoPrimero.getIdentificador().equals(identificador)) {
            primero = primero.getSeg();
            System.out.println("Eliminado: " + identificador);
            return;
        }

        Nodo actual = primero;

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

    @Override
    public Object buscar(String identificador) {

        Nodo actual = primero;

        while (actual != null) {
            if (((Interface_Elemento) actual.getInfo()).getIdentificador().equals(identificador)) {

                return actual.getInfo();
            } else {
                actual = actual.getSeg();
            }
        }
        return null; 

    }

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
