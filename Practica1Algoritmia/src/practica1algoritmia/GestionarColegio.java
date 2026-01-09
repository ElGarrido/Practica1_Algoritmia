package practica1algoritmia;

import practica1algoritmia.Asignaturas.*;
import practica1algoritmia.Cursos.*;
import practica1algoritmia.Cursos.FP.FP;
import practica1algoritmia.Cursos.Bachiller.Bachiller;
import practica1algoritmia.Listas.*;
import practica1algoritmia.Interfaces.*;
import practica1algoritmia.Nodo;

public class GestionarColegio {

    public static ListaCurso catalogoCursos = new ListaCurso();
    public static ListaEstudiante catalogoEstudiantes = new ListaEstudiante();
    public static ListaAsignatura catalogoAsignaturas = new ListaAsignatura();

    
    public static String altaEstudiante(String nombre, String dni) {
        // Comprobamos si ya esta regustrado ese estudiante
        if (catalogoEstudiantes.buscar(dni) != null) {
            return "Error: Ya existe un estudiante con ese DNI.";
        }
        // Lo creamos y lo insertamos en la lista 
        Estudiante nuevo = new Estudiante(nombre, dni);
        catalogoEstudiantes.insertar(nuevo);
        return "Éxito: Estudiante " + nombre + " registrado.";
    }

    public static String altaCursoFP(String codigo, String nombre, FP.Especialidad esp) {
        // Comprobamos si ya existe el curso.
        if (catalogoCursos.buscar(codigo) != null) return "Error: Código de curso duplicado.";
        // Lo creamos y lo añadimos.
        FP nuevo = new FP(codigo, nombre, esp);
        catalogoCursos.insertar(nuevo);
        return "Éxito: Curso FP " + nombre + " creado.";
    }

    public static String altaCursoBach(String codigo, String nombre, Bachiller.Nivel nivel) {
        if (catalogoCursos.buscar(codigo) != null) return "Error: Código de curso duplicado.";
        Bachiller nuevo = new Bachiller(codigo, nombre, nivel);
        catalogoCursos.insertar(nuevo);
        return "Éxito: Curso Bachiller " + nombre + " creado.";
    }

    public static String altaAsignaturaObligatoria(String idCurso, String codAsig, String nombre, int creditos) {
        // Validar Curso
        Curso cursoPadre = (Curso) catalogoCursos.buscar(idCurso);
        if (cursoPadre == null) return "Error: No existe el curso " + idCurso;

        // Validar Duplicado
        if (catalogoAsignaturas.buscar(codAsig) != null) return "Error: Ya existe asignatura con ID " + codAsig;

        // Crear y Vincular
        Obligatoria nueva = new Obligatoria(nombre, codAsig, cursoPadre, creditos);
        
        cursoPadre.asignaturasDelCurso.insertar(nueva);
        catalogoAsignaturas.insertar(nueva);

        return "Éxito: Asignatura Obligatoria creada en " + cursoPadre.getNombre();
    }

    public static String altaAsignaturaOptativa(String idCurso, String codAsig, String nombre, Optativa.Perfil perfil) {
        // Validar Curso
        Curso cursoPadre = (Curso) catalogoCursos.buscar(idCurso);
        if (cursoPadre == null) return "Error: No existe el curso " + idCurso;

        // Validar Duplicado
        if (catalogoAsignaturas.buscar(codAsig) != null) return "Error: Ya existe asignatura con ID " + codAsig;

        // Crear y Vincular
        Optativa nueva = new Optativa(nombre, codAsig, cursoPadre, perfil);
        
        cursoPadre.asignaturasDelCurso.insertar(nueva);
        catalogoAsignaturas.insertar(nueva);

        return "Éxito: Asignatura Optativa creada en " + cursoPadre.getNombre();
    }

    // MATRICULACIÓN
    
    public static String matricular(String dni, String codAsig) {
        Estudiante est = (Estudiante) catalogoEstudiantes.buscar(dni);
        Asignatura asig = (Asignatura) catalogoAsignaturas.buscar(codAsig);

        if (est == null) return "Error: Estudiante no encontrado.";
        if (asig == null) return "Error: Asignatura no encontrada.";

        // Insertar referencias
        ReferenciaAsignatura refAsig = new ReferenciaAsignatura(asig);
        ReferenciaEstudiante refEst = new ReferenciaEstudiante(est);

        est.asignaturasCursadas.insertar(refAsig);
        asig.alumnosMatriculados.insertar(refEst);

        return "Éxito: " + est.getNombre() + " matriculado en " + asig.getNombre();
    }

    // BAJAS 
    
    public static String bajaAsignaturaCascada(String codAsig) {
        Asignatura asig = (Asignatura) catalogoAsignaturas.buscar(codAsig);
        if (asig == null) return "Error: Asignatura no encontrada.";

        StringBuilder log = new StringBuilder();
        log.append("Procesando baja de: ").append(asig.getNombre()).append("\n");

        // Desmatricular alumnos
        Nodo actual = asig.alumnosMatriculados.getPrimero();
        int cont = 0;
        while (actual != null) {
            ReferenciaEstudiante ref = (ReferenciaEstudiante) actual.getInfo();
            Estudiante est = ref.getEstudiante();
            est.asignaturasCursadas.eliminar(codAsig);
            cont++;
            actual = actual.getSeg();
        }
        log.append(" - Desmatriculados ").append(cont).append(" alumnos.\n");

        // Quitar del Curso
        Curso curso = asig.getCurso();
        if (curso != null) {
            curso.asignaturasDelCurso.eliminar(codAsig);
            log.append(" - Eliminada del curso ").append(curso.getNombre()).append("\n");
        }

        // Quitar de la lista 
        catalogoAsignaturas.eliminar(codAsig);
        log.append(" - Eliminada del catálogo global.");

        return log.toString();
    }

    public static String bajaCursoCascada(String codCurso) {
        Curso curso = (Curso) catalogoCursos.buscar(codCurso);
        if (curso == null) return "Error: Curso no encontrado.";

        StringBuilder log = new StringBuilder();
        log.append("Eliminando curso: ").append(curso.getNombre()).append("\n");

        // Por cada asignatura eliminamos sus matriculas d eestudiantes y la misma asignatura
        ListaAsignatura lista = curso.asignaturasDelCurso;
        
        for (int i = 0; i < lista.asignaturas.length; i++) {
            Asignatura asig = lista.asignaturas[i];
            if (asig != null) {
                log.append(bajaAsignaturaCascada(asig.getIdentificador())).append("\n");
            }
        }

        catalogoCursos.eliminar(codCurso);
        log.append("Curso eliminado definitivamente.");
        
        return log.toString();
    }
}