
package practica1algoritmia.Cursos;

import practica1algoritmia.Asignaturas.Asignatura;

public class Curso {
   Asignatura[] asignaturas;
   String codi;
   
   public Curso(Asignatura[] asignaturas,String codi) {
       this.asignaturas = asignaturas;
       this.codi = codi;
   }
   public String getCodi() {
       return this.codi;
   }
   public Asignatura[] getAsignaturas() {
       return this.asignaturas;
   }
}
