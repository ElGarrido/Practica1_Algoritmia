
package practica1algoritmia;

import practica1algoritmia.Cursos.Curso;
import practica1algoritmia.Listas.ListaCurso;
import practica1algoritmia.Listas.ListaEstudiante;

import java.util.Scanner;

public class Practica1Algoritmia {

    private static Scanner sc = new Scanner(System.in);

    private static void DarAltaCurso(){

    }
    private static void MatricularEstudiante(){

    }
    private static void DarBajaCurso(){

    }
    private static void DarBajaAsignatura(){

    }
    private static void ListarAsignaturasDeCurso(){

    }
    private static void ListarCursoDeAsignatura(){

    }
    private static void ListarAsignaturasDeEstudiante(){

    }

    public static void main(String[] args) {

        System.out.println("-----------------------------");
        System.out.println("       Sistema Escolar       ");
        System.out.println("-----------------------------");

        int valorMenu = -1;

        while (valorMenu != 0) {

            MenuPrincipal();
            System.out.print("Introduzca una opción: ");

            try {
                valorMenu = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer del 'Intro'
            } catch (Exception e) {
                sc.nextLine(); // Limpiar la entrada errónea (letras)
                valorMenu = -1;
                System.out.println("Error: Por favor introduzca un número válido.");
                continue;
            }

            switch (valorMenu) {
                case 1:
                    DarAltaCurso();
                    break;
                case 2:
                    MatricularEstudiante();
                    break;
                case 3:
                    DarBajaCurso();
                    break;
                case 4:
                    DarBajaAsignatura();
                    break;
                case 5:
                    ListarAsignaturasDeCurso();
                    break;
                case 6:
                    ListarCursoDeAsignatura();
                    break;
                case 7:
                    ListarAsignaturasDeEstudiante();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no reconocida. Intente de nuevo.");
                    break;
            }
        }
        System.out.println("Adios!!");

        // ---------------------------------- PRUEBAS ----------------------------------
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

    private static void MenuPrincipal(){
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Dar de alta un Curso.");
        System.out.println("2. Matricular un Estudiante a una Asignatura.");
        System.out.println("3. Dar de baja un Curso.");
        System.out.println("4. Dar de baja una Asignatura de un Curso.");
        System.out.println("5. Listar Asignaturas de un Curso (con estudiantes).");
        System.out.println("6. Listar Curso de una Asignatura (con estudiantes).");
        System.out.println("7. Listar Asignaturas de un Estudiante.");
        System.out.println("0. Salir.");
    }
}
