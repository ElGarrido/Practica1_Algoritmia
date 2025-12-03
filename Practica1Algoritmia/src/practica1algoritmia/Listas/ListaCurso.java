
package practica1algoritmia.Listas;

import practica1algoritmia.Cursos.Curso;
import practica1algoritmia.Interfaces.Interface_Lista;
import practica1algoritmia.Nodo;


public class ListaCurso implements Interface_Lista {
    public Nodo cursos;
    @Override
    public void insertar(Object elemento) {
        Nodo cursos = this.cursos;
        if (elemento == null){
            System.err.println("Tipo de dato nulo");
            return;
        }
        if (elemento.getClass().getName() != "practica1algoritmia.Cursos.Curso"){
            System.err.println("Tipo de dato incorrecto");
            return;
            
        }
        System.out.println(elemento.getClass().getName());
        if (cursos == null) {
            this.cursos = new Nodo(elemento);
            return;
        }
        while (cursos.getSeg() != null) {
            cursos = cursos.getSeg();
        }
         this.cursos.setSeg(new Nodo(elemento));
        System.out.println("Test completed");
    }

    @Override
    public void eliminar(String identificador) {
        
        if (((Curso) cursos.getInfo()).getCodi() == identificador) {
            this.cursos = (this.cursos.getSeg());
             System.out.println("Elemento eliminado");
            return;
        }
        do {
            if (((Curso) cursos.getSeg().getInfo()).getCodi() == identificador) {
                if (cursos.getSeg().getSeg() != null) {
                this.cursos.setSeg(cursos.getSeg().getSeg());
                }else {
                    this.cursos.setSeg(null);
                }
                System.out.println("Elemento eliminado");
                return;
                
            }
        }
        while (cursos.getSeg()!= null) ;
          
    }
    
    @Override
    public Object buscar(String identificador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void listar() {
        for (int x = 0; x < ((Curso) cursos.getInfo()).getAsignaturas().length;x++) {
            System.out.println(((Curso) cursos.getInfo()).getAsignaturas()[x]);

        }
    }
    public Nodo get_Nodo() {
        return this.cursos;
    }
}
