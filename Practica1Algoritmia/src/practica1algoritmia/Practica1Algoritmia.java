
package practica1algoritmia;

import practica1algoritmia.Asignaturas.Asignatura;
import practica1algoritmia.Cursos.Curso;
import practica1algoritmia.Listas.ListaAsignatura;
import practica1algoritmia.Listas.ListaCurso;

public class Practica1Algoritmia {


    public static void main(String[] args) {
       ListaAsignatura a = new ListaAsignatura();
       Estudiante e = new Estudiante("dwad","dwadwa");
       Nodo no = new Nodo(e);
       Asignatura b = new Asignatura("awsd",no,"dwadw");
              Asignatura c = new Asignatura("aws",no,"dawdw");
                            Asignatura d = new Asignatura("xws",no,"dwadw");


       Asignatura[] s = new Asignatura[1];
       s[0] = b;
       a.setAsignaturas(s);
              a.insertar(d);

       a.insertar(c);
       a.listar();

    }
    
}
