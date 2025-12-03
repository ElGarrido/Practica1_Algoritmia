package practica1algoritmia;

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

        Interface_Elemento nuevoElem = (Interface_Elemento) elemento;
        Nodo nuevoNodo = new Nodo(nuevoElem);

        if (elemento == null) {
            System.err.println("Tipo de dato nulo");
            return;
        }
        if (elemento.getClass().getName() != "practica1algoritmia.Estudiante") {
            System.out.println("Estas intentando crear un Estudiante desde " + elemento.getClass().getName());
            return;
        }

        // INSERTTAMOS AL INICIO SI LISTA VACIA O SI ES MENOR QUE EL SEGUNDO ELEMENTO
        if (primero == null || nuevoElem.getIdentificador().compareTo(((Interface_Elemento) primero.getInfo()).getIdentificador()) < 0) {
            nuevoNodo.setSeg(primero);
            this.primero = nuevoNodo;
            return;
        }

        // INSERTAMOS EN MEDIO O AL FINAL
        // Nodos auxiliares para recorrer la lista
        Nodo anterior = null;
        Nodo actual = primero;

        // Avanzamos mientras queden nodos y el actual sea "menor" que el nuevo
        while (actual != null && ((Interface_Elemento) actual.getInfo()).getIdentificador().compareTo(nuevoElem.getIdentificador()) < 0) {
            anterior = actual;
            actual = actual.getSeg();
        }

        // Si el estudiante que vamos a ñadir ya existe no lo añadimos de nuevo
        if (actual != null && ((Interface_Elemento) actual.getInfo()).getIdentificador().equals(nuevoElem.getIdentificador())) {
            System.out.println("Error: Ya existe un estudiante con el ID: " + nuevoNodo.getInfo());
            return;
        }

        // Ajustamos la lista
        anterior.setSeg(nuevoNodo); // El nodo anterior al nuevo estudiante apuntará al nuevo estudiante
        nuevoNodo.setSeg(actual);   // El nuevo estuidiante apuntará al siguiente nodo que era el actual.

    }

    // ----------------------------------------------
    //     ELIMINAR ESTUDIANTE POR IDENTIFICADOR
    // ----------------------------------------------
    @Override
    public void eliminar(String identificador) {        // CORREGIR  FALLO SI NO ENCUENTRA EL NODO INTENTA CONTINUAR

        if (primero == null) {
            System.out.println("La lista está vacía, no se puede borrar.");
            return;
        }

        // CASO EN EL QUE BORRAMOS EL PRIMERO
        if (((Interface_Elemento) primero.getInfo()).getIdentificador().equals(identificador)) {
            System.out.println("Usuario con identificador: " + identificador + " ha sido eliminado correctamente");
            primero = primero.getSeg(); // El puntero de inicio salta al segundo
            return;
        }

        // CASO EN EL QUE HAY QUE BUSCAR
        Nodo anterior = null;
        Nodo actual = primero;

        do {
            if (((Interface_Elemento) actual.getSeg().getInfo()).getIdentificador().equals(identificador)) {
                if (actual.getSeg().getSeg() != null) {
                    actual.setSeg(actual.getSeg().getSeg());
                    
                } else {
                    actual.setSeg(null);
                }
                
                System.out.println("Usuario con identificador: " + identificador + " ha sido eliminado correctamente");
                return;

            }
            actual = actual.getSeg();
        } while (actual.getSeg() != null);
        
        System.out.println("No se ha encontrado el usuario con identificador: " + identificador);

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
