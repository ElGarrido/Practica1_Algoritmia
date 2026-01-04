package practica1algoritmia;

import java.util.Scanner;
import practica1algoritmia.Cursos.Curso;
import practica1algoritmia.Listas.ListaCurso;
import practica1algoritmia.Cursos.Bachiller.Bachiller;
import practica1algoritmia.Cursos.FP.FP;
import practica1algoritmia.Estudiante;
import practica1algoritmia.Listas.*;
import practica1algoritmia.Asignaturas.*;

public class Practica1Algoritmia {

    private static Scanner sc = new Scanner(System.in);

    static ListaCurso catalogoCursos = new ListaCurso();
    static ListaEstudiante catalogoEstudiantes = new ListaEstudiante();
    static ListaAsignatura catalogoAsignaturas = new ListaAsignatura();

    private static void DarAltaCurso() {
        System.out.println("\n--- NUEVO CURSO ---");
        System.out.println("1. FP");
        System.out.println("2. Bachiller");
        System.out.print("Seleccione tipo: ");

        int tipo = -1;
        try {
            tipo = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            sc.nextLine();
            System.out.println("Error: Introduce un número.");
            return;
        }

        System.out.print("Código: ");
        String cod = sc.nextLine();
        System.out.print("Nombre: ");
        String nom = sc.nextLine();

        if (tipo == 1) { // ES FP
            System.out.println("Seleccione Especialidad:");
            System.out.println("1. Mecánica");
            System.out.println("2. Electrónica");
            System.out.println("3. Informática");
            int opcEsp = sc.nextInt();
            sc.nextLine();

            // Variable para guardar el enum seleccionado
            FP.Especialidad espSeleccionada = null;

            switch (opcEsp) {
                case 1:
                    espSeleccionada = FP.Especialidad.MECANICA;
                    break;
                case 2:
                    espSeleccionada = FP.Especialidad.ELECTRONICA;
                    break;
                case 3:
                    espSeleccionada = FP.Especialidad.INFORMATICA;
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    return;
            }

            // Creamos el curso con el ENUM
            FP nuevoFP = new FP(cod, nom, espSeleccionada);
            catalogoCursos.insertar(nuevoFP);

        } else if (tipo == 2) { // ES BACHILLER
            System.out.println("Seleccione Nivel:");
            System.out.println("1. Primero");
            System.out.println("2. Segundo");
            int opcNiv = sc.nextInt();
            sc.nextLine();

            Bachiller.Nivel nivSeleccionado = null;

            switch (opcNiv) {
                case 1:
                    nivSeleccionado = Bachiller.Nivel.PRIMERO;
                    break;
                case 2:
                    nivSeleccionado = Bachiller.Nivel.SEGUNDO;
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    return;
            }

            Bachiller nuevoBach = new Bachiller(cod, nom, nivSeleccionado);
            catalogoCursos.insertar(nuevoBach);

        } else {
            System.out.println("Tipo de curso no válido.");
            return;
        }

        System.out.println("Curso creado correctamente.");
    }

    private static void DarAltaEstudiante() {

        System.out.println("--- NUEVO ESTUDIANTE ---");
        System.out.print("Nombre: ");
        String nom = sc.nextLine();
        System.out.print("DNI: ");
        String dni = sc.nextLine();

        Estudiante nuevo = new Estudiante(nom, dni);
        catalogoEstudiantes.insertar(nuevo);
        System.out.println("Estudiante registrado correctamente.");
    }

    private static void MatricularEstudiante() {
        System.out.println("\n--- MATRICULACIÓN ---");

        // Pedir DNI y Buscar Alumno
        System.out.print("Introduce el DNI del estudiante: ");
        String dni = sc.nextLine();

        Estudiante alumnoEncontrado = (Estudiante) catalogoEstudiantes.buscar(dni);

        if (alumnoEncontrado == null) {
            System.out.println("ERROR: No existe ningún estudiante con DNI: " + dni);
            return;
        }
        System.out.println("Alumno seleccionado: " + alumnoEncontrado.getNombre());

        // Pedir Código y Buscar Asignatura
        System.out.print("Introduce el ID de la asignatura: ");
        String cod = sc.nextLine();

        Asignatura asigEncontrada = (Asignatura) catalogoAsignaturas.buscar(cod);

        if (asigEncontrada == null) {
            System.out.println("ERROR: No existe asignatura con código: " + cod);
            return;
        }
        System.out.println("Asignatura seleccionada: " + asigEncontrada.getNombre());

        // Si ambos existen, Matriculamos al estudiante en la asignatura.
        Matricular(alumnoEncontrado, asigEncontrada);
    }

    public static void Matricular(Estudiante est, Asignatura asig) {

        ReferenciaAsignatura refAsig = new ReferenciaAsignatura(asig);
        ReferenciaEstudiante refEst = new ReferenciaEstudiante(est);

        est.asignaturasCursadas.insertar(refAsig);
        asig.alumnosMatriculados.insertar(refEst);

        System.out.println(" ÉXITO: " + est.getNombre() + " matriculado en " + asig.getNombre());
    }

    private static void DarBajaCurso() {

    }

    private static void DarAltaAsignatura() {
        System.out.println("\n--- NUEVA ASIGNATURA ---");

        // PRIMERO BUSCAMOS EL CURSO PADRE
        System.out.print("Introduce el Código del Curso al que pertenece: ");
        String idCurso = sc.nextLine();

        // Buscamos en la lista de cursos
        Curso cursoPadre = (Curso) catalogoCursos.buscar(idCurso);

        if (cursoPadre == null) {
            System.out.println("No existe un curso con ese código.");
            return;
        }
        System.out.println("--> Añadiendo asignatura a: " + cursoPadre.getNombre());

        // PEDIMOS DATOS 
        System.out.print("Código Asignatura: ");
        String cod = sc.nextLine();
        System.out.print("Nombre Asignatura: ");
        String nom = sc.nextLine();

        // ELEGIMOS TIPO (Obligatoria vs Optativa)
        System.out.println("Tipo de Asignatura:");
        System.out.println("1. Obligatoria");
        System.out.println("2. Optativa");
        int tipo = sc.nextInt();
        sc.nextLine(); // Buffer

        Asignatura nuevaAsignatura = null;

        if (tipo == 1) { // OBLIGATORIA
            System.out.print("Número de créditos: ");
            int creditos = sc.nextInt();
            sc.nextLine();

            // Pasamos el cursoPadre al constructor
            nuevaAsignatura = new Obligatoria(nom, cod, cursoPadre, creditos);

        } else if (tipo == 2) { // OPTATIVA
            System.out.println("Perfil (1. TEORICO / 2. PRACTICO): ");
            int opcPerfil = sc.nextInt();
            sc.nextLine();

            Optativa.Perfil perfil = (opcPerfil == 1) ? Optativa.Perfil.TEORICO : Optativa.Perfil.PRACTICO;

            // Pasamos el cursoPadre al constructor
            nuevaAsignatura = new Optativa(nom, cod, cursoPadre, perfil);

        } else {
            System.out.println("Tipo incorrecto.");
            return;
        }

        // GUARDADO 
        // La guardamos dentro del curso 
        cursoPadre.asignaturasDelCurso.insertar(nuevaAsignatura);

        // La guardamos en la lista de asignaturas 
        catalogoAsignaturas.insertar(nuevaAsignatura);

        System.out.println("Asignatura creada y vinculada correctamente.");
    }

    private static void DarBajaAsignatura() {

    }

    private static void ListarAsignaturasDeCurso() {

    }

    private static void ListarCursoDeAsignatura() {

    }

    private static void ListarAsignaturasDeEstudiante() {

    }

    public static void main(String[] args) {

        System.out.println("=== PRUEBA ===\n");

        // 1. PRUEBA DE CURSOS (FP y Bachiller)
        System.out.println("--- 1. Creando Cursos de prueba ---");

        // Creamos un FP usando el ENUM
        FP cursoFP = new FP("FP-INF", "1º DAM", FP.Especialidad.INFORMATICA);

        // Creamos un Bachiller usando el ENUM
        Bachiller cursoBach = new Bachiller("BAC-1", "Humanidades", Bachiller.Nivel.PRIMERO);

        // Los metemos en la lista global
        catalogoCursos.insertar(cursoFP);
        catalogoCursos.insertar(cursoBach);

        // Listamos para ver si se guardaron bien y si el toString() funciona
        System.out.println(">> Listado de Cursos:");
        catalogoCursos.listar();

        // 2. PRUEBA DE ESTUDIANTES
        System.out.println("\n--- 2. Creando Estudiantes de prueba ---");

        Estudiante est1 = new Estudiante("Laura Gómez", "12345678A");
        Estudiante est2 = new Estudiante("Pedro Martinez", "87654321B");

        catalogoEstudiantes.insertar(est1);
        catalogoEstudiantes.insertar(est2);

        System.out.println(">> Listado de Estudiantes:");
        catalogoEstudiantes.listar();

        // 3. PRUEBA DE BÚSQUEDA
        System.out.println("\n--- 3. Probando Búsquedas ---");
        String codigoBuscar = "FP-INF";
        Object resultado = catalogoCursos.buscar(codigoBuscar);

        if (resultado != null) {
            // Hacemos casting a Curso porque la lista devuelve Object
            Curso c = (Curso) resultado;
            System.out.println("Curso encontrado: " + c.getNombre() + " (" + c.getDescripcionTipo() + ")");
        } else {
            System.out.println("Error: No se encontró el curso.");
        }

        /*
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
                case 8:
                    DarAltaEstudiante();
                case 0:
                    break;
                default:
                    System.out.println("Opción no reconocida. Intente de nuevo.");
                    break;
            }
        }
        System.out.println("Adios!!");
         */
    }

    private static void MenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Dar de alta un Curso.");
        System.out.println("2. Matricular un Estudiante a una Asignatura.");
        System.out.println("3. Dar de baja un Curso.");
        System.out.println("4. Dar de baja una Asignatura de un Curso.");
        System.out.println("5. Listar Asignaturas de un Curso (con estudiantes).");
        System.out.println("6. Listar Curso de una Asignatura (con estudiantes).");
        System.out.println("7. Listar Asignaturas de un Estudiante.");
        System.out.println("8. Dar de alta nuevo Estudiante");
        System.out.println("0. Salir.");
    }

}
