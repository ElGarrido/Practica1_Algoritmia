package practica1algoritmia.Listas;

import practica1algoritmia.Asignaturas.Asignatura;
import practica1algoritmia.Interfaces.Interface_Lista;

public class ListaAsignatura implements Interface_Lista {

    public Asignatura[] asignaturas;

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
                x --;
                

            } else {
                tempas[x + modulator] = asignaturas[x ];
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
        boolean found = false;

        for (int x = 0; x < asignaturas.length; x++) {
            if (asignaturas[x].getIdentificador() == identificador) {

                found = true;

                if (found == true) {
                    if (x + 1 < asignaturas.length - 1) {
                        asignaturas[x] = asignaturas[x + 1];
                    } else {
                        asignaturas[asignaturas.length - 1] = null;
                    }
                } else {

                }

            }
        }

    }

    @Override
    public Object buscar(String identificador) {
          for (int x = 0; x < asignaturas.length; x++) {
            if (asignaturas[x].getIdentificador() == identificador) { 
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
    public void setAsignaturas(Asignatura[] a ){
        this.asignaturas = a;
        
    }
    

}
