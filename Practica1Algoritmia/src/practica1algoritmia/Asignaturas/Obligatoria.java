/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica1algoritmia.Asignaturas;

/**
 *
 * @author rpere
 */
public class Obligatoria extends Asignatura {

    private int creditos;

    public Obligatoria(String nombre, String identificador, int creditos) {

        // Constructor de la clase Padre
        // Inicializacion de los atributos que Obligatoria hereda y cumple con Interface_Elemento
        super(nombre, identificador);
        // Inicializa los atributos propios
        this.creditos = creditos;
    }

    public int getCreditos() {
        return creditos;
    }
}
