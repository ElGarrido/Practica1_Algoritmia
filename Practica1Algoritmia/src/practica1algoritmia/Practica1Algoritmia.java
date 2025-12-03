
package practica1algoritmia;

import practica1algoritmia.Cursos.Curso;
import practica1algoritmia.Listas.ListaCurso;

public class Practica1Algoritmia {


    public static void main(String[] args) {
        ListaCurso c = new ListaCurso();
        Curso test = new Curso(null,"dwadw");
        Curso test2 = new Curso(null,"dwadwd");

        c.insertar(test);
        c.insertar(test2);
        Nodo d = c.get_Nodo();
        System.out.println(d.getInfo());
        c.eliminar("dwadwd");
        d = c.get_Nodo();
        System.out.println(d.getInfo());

    }
    
}
