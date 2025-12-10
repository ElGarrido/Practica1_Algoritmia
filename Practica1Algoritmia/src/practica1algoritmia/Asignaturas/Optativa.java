/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica1algoritmia.Asignaturas;

/**
 *
 * @author rpere
 */
public class Optativa extends Asignatura {

    // Definición de los diferentes Perfiles
    public static enum PerfilOptativa {
        TEORICO,
        PRACTICO
    }

    private PerfilOptativa perfil;

    public Optativa(String nombre, String identificador, PerfilOptativa perfil) {

        // Constructor Padre
        super(nombre, identificador);

        this.perfil = perfil;
    }

    public PerfilOptativa getPerfil() {
        return perfil;
    }


}
