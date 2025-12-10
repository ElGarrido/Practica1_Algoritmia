
package practica1algoritmia;

import practica1algoritmia.Cursos.Curso;
import practica1algoritmia.Listas.ListaCurso;
import practica1algoritmia.Listas.ListaEstudiante;

public class Practica1Algoritmia {


    public static void main(String[] args) {
        ListaCurso c = new ListaCurso();
        Curso test = new Curso(null,"dwadw");
        Curso test2 = new Curso(null,"dwadwd");

        System.out.println("PRUEBA INSERTAR UN CURSO");
        c.insertar(test);
        c.insertar(test2);
        Nodo d = c.get_Nodo();
        System.out.println(d.getInfo());
        c.eliminar("dwadwd");
        d = c.get_Nodo();
        System.out.println(d.getInfo());
        
        System.out.println("");
        
        System.out.println("PRUEBA INSERTAR UN ESTUDIANTE");
        ListaEstudiante estudiantes = new ListaEstudiante();
        
        Estudiante e1 = new Estudiante("Carlos (Medio)", "A");
        Estudiante e2 = new Estudiante("Ana (Pequeño)",  "D");
        Estudiante e3 = new Estudiante("Berto (Intermedio)", "B");
        Estudiante e5 = new Estudiante("Berto (Intermedio)", "B");
        Estudiante e4 = new Estudiante("David (Grande)", "C");
        
        System.out.println("\n1. Insertando a Carlos (A)...");
        estudiantes.insertar(e1);
        estudiantes.listar();
        
        estudiantes.insertar(e2);
        estudiantes.insertar(e3);
        estudiantes.insertar(e4);
        estudiantes.insertar(e5);
        estudiantes.listar();
        
        System.out.println("ELIMINAR A");
        estudiantes.eliminar("A");
        estudiantes.listar();
        System.out.println("ELIMINAR C");
         estudiantes.eliminar("C");
        estudiantes.listar();
        System.out.println("ELIMINAR DESCONOCDIO");
        estudiantes.eliminar("u");
        estudiantes.listar();
        
        System.out.println("\n BUSCAR D");
        
        Object resultado = estudiantes.buscar("D");
        
        Estudiante busqueda = (Estudiante) resultado;
        System.out.println(busqueda);

    }
    
}
