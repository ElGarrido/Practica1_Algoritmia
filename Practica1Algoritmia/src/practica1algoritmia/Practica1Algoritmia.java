package practica1algoritmia;

import java.util.Scanner;
import practica1algoritmia.Cursos.Curso;
import practica1algoritmia.Cursos.FP.FP;
import practica1algoritmia.Cursos.Bachiller.Bachiller;
import practica1algoritmia.Estudiante;
import practica1algoritmia.Asignaturas.*;
import practica1algoritmia.Listas.*;
import practica1algoritmia.Interfaces.*;
import practica1algoritmia.Nodo;

// Importamos la clase que tiene la lógica y los datos
import practica1algoritmia.GestionarColegio;

public class Practica1Algoritmia {

    private static Scanner sc = new Scanner(System.in);

    // ALTAS
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

        String resultado = "";

        if (tipo == 1) { // ES FP
            System.out.println("Especialidad: 1. Mecánica | 2. Electrónica | 3. Informática");
            int opcEsp = sc.nextInt();
            sc.nextLine();

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

            // DELEGAMOS A LA LÓGICA
            resultado = GestionarColegio.altaCursoFP(cod, nom, espSeleccionada);

        } else if (tipo == 2) { // ES BACHILLER
            System.out.println("Nivel: 1. Primero | 2. Segundo");
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

            // DELEGAMOS A LA LÓGICA
            resultado = GestionarColegio.altaCursoBach(cod, nom, nivSeleccionado);

        } else {
            System.out.println("Tipo de curso no válido.");
            return;
        }

        System.out.println(resultado);
    }

    private static void DarAltaEstudiante() {
        System.out.println("--- NUEVO ESTUDIANTE ---");
        System.out.print("Nombre: ");
        String nom = sc.nextLine();
        System.out.print("DNI: ");
        String dni = sc.nextLine();

        String resultado = GestionarColegio.altaEstudiante(nom, dni);
        System.out.println(resultado);
    }

    private static void DarAltaAsignatura() {
        System.out.println("\n--- NUEVA ASIGNATURA ---");

        System.out.print("Cód. Curso Padre: ");
        String idCurso = sc.nextLine();
        System.out.print("Cód. Asignatura: ");
        String cod = sc.nextLine();
        System.out.print("Nombre Asignatura: ");
        String nom = sc.nextLine();

        System.out.println("Tipo: 1. Obligatoria | 2. Optativa");
        int tipo = -1;
        try {
            tipo = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            sc.nextLine();
            System.out.println("Opción inválida.");
            return;
        }

        String resultado = "";

        if (tipo == 1) { // OBLIGATORIA
            System.out.print("Créditos: ");
            int creditos = sc.nextInt();
            sc.nextLine();

            resultado = GestionarColegio.altaAsignaturaObligatoria(idCurso, cod, nom, creditos);

        } else if (tipo == 2) { // OPTATIVA
            System.out.println("Perfil: 1. TEORICO | 2. PRACTICO");
            int opcPerfil = sc.nextInt();
            sc.nextLine();
            Optativa.Perfil perfil = (opcPerfil == 1) ? Optativa.Perfil.TEORICO : Optativa.Perfil.PRACTICO;

            resultado = GestionarColegio.altaAsignaturaOptativa(idCurso, cod, nom, perfil);

        } else {
            System.out.println("Tipo incorrecto.");
            return;
        }

        System.out.println(resultado);
    }

    private static void MatricularEstudiante() {
        System.out.println("\n--- MATRICULACIÓN ---");
        System.out.print("DNI del estudiante: ");
        String dni = sc.nextLine();
        System.out.print("ID de la asignatura: ");
        String cod = sc.nextLine();

        // DELEGAMOS A LA LÓGICA
        String resultado = GestionarColegio.matricular(dni, cod);
        System.out.println(resultado);
    }

    private static void DarBajaCurso() {
        System.out.println("\n--- ELIMINAR CURSO ---");
        System.out.print("Código del Curso a eliminar: ");
        String codi = sc.nextLine();

        System.out.println("¿Está seguro? (1. Sí / 2. Cancelar)");
        int opcion = sc.nextInt();
        sc.nextLine();

        if (opcion == 1) {
            // DELEGAMOS A LA LÓGICA (Devuelve un log con todo lo que borró)
            String log = GestionarColegio.bajaCursoCascada(codi);
            System.out.println(log);
        } else {
            System.out.println("Cancelado.");
        }
    }

    private static void DarBajaAsignatura() {
        System.out.println("\n--- BAJA DE ASIGNATURA ---");
        System.out.print("Código de la Asignatura a eliminar: ");
        String cod = sc.nextLine();

        System.out.println("¿Está seguro? (1. Sí / 2. Cancelar)");
        int opcion = sc.nextInt();
        sc.nextLine();

        if (opcion == 1) {
            // DELEGAMOS A LA LÓGICA
            String log = GestionarColegio.bajaAsignaturaCascada(cod);
            System.out.println(log);
        } else {
            System.out.println("Cancelado.");
        }
    }

    // LISTADO
    private static void ListarAsignaturasDeCurso() {
        System.out.println("\n--- LISTADO DE ASIGNATURAS POR CURSO ---");
        System.out.print("Código del Curso: ");
        String codi = sc.nextLine();

        // Usamos GestionarColegio
        Curso curso = (Curso) GestionarColegio.catalogoCursos.buscar(codi);

        if (curso == null) {
            System.out.println("ERROR: No se encontró el curso.");
            return;
        }

        System.out.println(">> Curso: " + curso.getNombre());
        ListaAsignatura lista = curso.asignaturasDelCurso;

        for (int i = 0; i < lista.asignaturas.length; i++) {
            Asignatura asig = lista.asignaturas[i];
            if (asig != null) {
                System.out.println(" * " + asig.getNombre() + " (" + asig.getDescripcionExtra() + ")");
                System.out.print("   |-> Estudiantes: ");
                asig.alumnosMatriculados.listar();
            }
        }
        System.out.println();
    }

    private static void ListarCursoDeAsignatura() {
        System.out.println("\n--- INFO ASIGNATURA ---");
        System.out.print("Código Asignatura: ");
        String codAsig = sc.nextLine();

        Asignatura asig = (Asignatura) GestionarColegio.catalogoAsignaturas.buscar(codAsig);
        if (asig == null) {
            System.out.println("ERROR: Asignatura no encontrada.");
            return;
        }

        Curso curso = asig.getCurso();
        String nomCurso = (curso != null) ? curso.getNombre() : "Sin Curso";

        System.out.println("Asignatura: " + asig.getNombre());
        System.out.println("Pertenece a: " + nomCurso);
        System.out.print("Estudiantes: ");
        asig.alumnosMatriculados.listar();
        System.out.println();
    }

    private static void ListarAsignaturasDeEstudiante() {
        System.out.println("\n--- EXPEDIENTE ESTUDIANTE ---");
        System.out.print("DNI Estudiante: ");
        String dni = sc.nextLine();

        Estudiante alumno = (Estudiante) GestionarColegio.catalogoEstudiantes.buscar(dni);
        if (alumno == null) {
            System.out.println("ERROR: Estudiante no encontrado.");
            return;
        }

        System.out.println("Estudiante: " + alumno.getNombre());
        System.out.printf("   %-20s | %-20s | %-20s \n", "ASIGNATURA", "CURSO", "TIPO");
        System.out.println("   -------------------------------------------------------");

        Nodo actual = alumno.asignaturasCursadas.getPrimero();
        while (actual != null) {
            ReferenciaAsignatura ref = (ReferenciaAsignatura) actual.getInfo();
            Asignatura asig = ref.getAsignatura();
            Curso curso = asig.getCurso();
            String nomCurso;

            if (curso != null) {
                nomCurso = curso.getNombre();
            } else {
                nomCurso = "Sin Curso";
            }
            String tipo = curso.getDescripcionTipo();

            System.out.printf("   %-20s | %-20s | %-20s\n", asig.getNombre(), nomCurso, tipo);
            actual = actual.getSeg();
        }
        System.out.println();
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
                sc.nextLine();
            } catch (Exception e) {
                sc.nextLine();
                valorMenu = -1;
                System.out.println("Error: Por favor introduzca un número válido.");
                continue;
            }

            switch (valorMenu) {
                case 1:
                    DarAltaCurso();
                    break;
                case 2:
                    DarAltaAsignatura();
                    break;
                case 3:
                    DarAltaEstudiante();
                    break;
                case 4:
                    MatricularEstudiante();
                    break;
                case 5:
                    DarBajaCurso();
                    break;
                case 6:
                    DarBajaAsignatura();
                    break;
                case 7:
                    ListarAsignaturasDeCurso();
                    break;
                case 8:
                    ListarCursoDeAsignatura();
                    break;
                case 9:
                    ListarAsignaturasDeEstudiante();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no reconocida.");
                    break;
            }
        }
        System.out.println("Adios!!");
    }

    private static void MenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Dar de alta un Curso.");
        System.out.println("2. Dar de alta una Asignatura.");
        System.out.println("3. Dar de alta nuevo Estudiante");
        System.out.println("4. Matricular un Estudiante a una Asignatura.");
        System.out.println("5. Dar de baja un Curso.");
        System.out.println("6. Dar de baja una Asignatura de un Curso.");
        System.out.println("7. Listar Asignaturas de un Curso.");
        System.out.println("8. Listar Curso de una Asignatura.");
        System.out.println("9. Listar Asignaturas de un Estudiante.");
        System.out.println("0. Salir.");
    }
}
