package practica1algoritmia.Listas;

import practica1algoritmia.Cursos.Curso;
import practica1algoritmia.Interfaces.Interface_Lista;
import practica1algoritmia.Nodo;

public class ListaCurso implements Interface_Lista {
    
    private Nodo primero; 

    public ListaCurso() {
        this.primero = null;
    }

    @Override
    public void insertar(Object elemento) {

        if (!(elemento instanceof Curso)) {
            System.err.println("Error: El elemento no es un Curso válido.");
            return;
        }

        Nodo nuevoNodo = new Nodo(elemento);

        // Si la lista está vacía
        if (primero == null) {
            this.primero = nuevoNodo;
            return;
        }

        // Recorremos hasta el final
        Nodo actual = primero;

        while (actual.getSeg() != null) {
            actual = actual.getSeg();
        }
        
        // Insertamos al final
        actual.setSeg(nuevoNodo);
    }

    @Override
    public void eliminar(String identificador) {
        if (primero == null) return;

        // Si es el primero
        Curso infoPrimero = (Curso) primero.getInfo();

        if (infoPrimero.getIdentificador().equals(identificador)) {
            primero = primero.getSeg();
            System.out.println("Curso eliminado: " + identificador);
            return;
        }

        // Si está en el resto de la lista
        Nodo actual = primero;
        while (actual.getSeg() != null) {
            Curso infoSiguiente = (Curso) actual.getSeg().getInfo();
            
            if (infoSiguiente.getIdentificador().equals(identificador)) {
                // Saltamos el nodo 
                actual.setSeg(actual.getSeg().getSeg());
                System.out.println("Curso eliminado: " + identificador);
                return;
            }
            actual = actual.getSeg();
        }
        System.out.println("No se encontró el curso con ID: " + identificador);
    }
    
    @Override
    public Object buscar(String identificador) {

        Nodo actual = primero;
        while (actual != null) {
            Curso c = (Curso) actual.getInfo();

            if (c.getIdentificador().equals(identificador)) {
                return c; // Devolvemos el objeto Curso encontrado
            }
            actual = actual.getSeg();
        }
        return null; // No encontrado
    }

    @Override
    public void listar() {

        if (primero == null) {
            System.out.println("No hay cursos registrados.");
            return;
        }

        Nodo actual = primero;
        while (actual != null) {

            System.out.println(actual.getInfo()); 
            actual = actual.getSeg();
        }
    }
    
    public Nodo getPrimero() {
        return this.primero;
    }
}