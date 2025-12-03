
package practica1algoritmia;

public class Estudiante implements Interface_Elemento {
    
    private String nombre;
    private String DNI;
    
    private Interface_Lista cursosMatriculados;
    
    public Estudiante (String nombre, String DNI){
        this.nombre = nombre;
        this.DNI = DNI;
        
        this.cursosMatriculados = new ListaAsignatura();
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return DNI;
    }
    
    public void Matricular(Asignatura asignatura){
        
        ReferenciaAsignatura ref = new ReferenciaAsignatura(asignatura);
        this.cursosMatriculados.insertar(ref);
        
    }

    @Override
    public String getIdentificador() {
        return DNI;
    }
    
    @Override
    public String toString() {
        return "Estudiante: " + nombre + " | DNI: " + DNI;
    }
    
    
}
