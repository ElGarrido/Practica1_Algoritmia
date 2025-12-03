
package practica1algoritmia;


public class Nodo {
    private Object info;
    private Nodo seg;
    
    public Nodo(Object t){
        this.info = t;
        this.seg = null; // Inicialmente solo hay un nodo, por lo que el siguiente apunta a null
    }
    
    public void setSeg(Nodo apuntador){
        this.seg = apuntador;
    }
    
    public void setInfo (Object t){
        this.info = t;
    }
    
    public Object getInfo(){
        return info;
    }
    
    public Nodo getSeg(){
        return seg;
    }

}

