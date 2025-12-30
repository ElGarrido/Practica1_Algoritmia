package practica1algoritmia.Listas;

import practica1algoritmia.Asignaturas.Asignatura;
import practica1algoritmia.Interfaces.Interface_Lista;

public class ListaAsignatura implements Interface_Lista {

    public Asignatura[] asignaturas;
    

    public ListaAsignatura() {
        this.asignaturas = new Asignatura[0]; 
        
    }

    @Override
    public void insertar(Object elemento) {
        /**
         * INSERTA UN ELEMENTO Y LO ORDENA*
         */
        if (!(elemento instanceof Asignatura)) {
            return;
        }
        boolean done = false;
        int modulator = 0;
        Asignatura tempele = (Asignatura) elemento;
        Asignatura[] tempas = new Asignatura[asignaturas.length + 1];
        for (int x = 0; x < asignaturas.length; x++) {
            if (asignaturas[x].getIdentificador().compareTo(tempele.getIdentificador()) >= 0 && modulator == 0) {
                tempas[x] = tempele;
                modulator++;
                done = true;
                x--;

            } else {
                tempas[x + modulator] = asignaturas[x];
            }
        }
        if (!done) {
            System.out.println(tempas.length);
            tempas[tempas.length - 1] = tempele;
        }
        this.asignaturas = tempas;

    }

    @Override
    public void eliminar(String identificador) {
        int indiceEncontrado = -1;

        // Buscamos si existe y dónde está
        for (int i = 0; i < asignaturas.length; i++) {

            if (asignaturas[i].getIdentificador().equals(identificador)) {
                indiceEncontrado = i;
                break;
            }
        }

        // Si no existe, salimos
        if (indiceEncontrado == -1) return;

        // Creamos un array más pequeño para evitar huecos con null
        Asignatura[] temp = new Asignatura[asignaturas.length - 1];
        
        // Copiamos todos MENOS el que queremos borrar
        int j = 0; // índice para el array nuevo
        for (int i = 0; i < asignaturas.length; i++) {
            if (i != indiceEncontrado) {
                temp[j] = asignaturas[i];
                j++;
            }
        }
        
        this.asignaturas = temp;
    }

    @Override
    public Object buscar(String identificador) {
        for (int x = 0; x < asignaturas.length; x++) {
            if (asignaturas[x].getIdentificador().equals(identificador)) {
                return asignaturas[x];

            }

        }
        return null;
    }

    @Override
    public void listar() {
        for (int x = 0; x < asignaturas.length; x++) {
            if (asignaturas[x] == null) {
                System.out.println("NULL");
            } else {
                System.out.println(asignaturas[x].getIdentificador());
            }
        }
    }

    public void setAsignaturas(Asignatura[] a) {
        this.asignaturas = a;

    }

}
