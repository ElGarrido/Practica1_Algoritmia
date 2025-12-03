
package practica1algoritmia;

public interface Interface_Lista {
    
    // Añadir un elemento (debe insertar ordenado)
    void insertar(Object elemento); 
    
    // Eliminar un elemento dado su código o identificador
    void eliminar(String identificador);
    
    // Buscar un elemento
    Object buscar(String identificador);
    
    // Imprimir todo el contenido de la lista
    void listar();
}
