
package arbolBB;

/**
 *
 * @author Jorge Ivan Vasquez Sosa (0901-16-4067)
 */
public class Nodo {
    
  public int dato , ve;
  public Nodo izq,der;

    public Nodo(int dato, Nodo izq , Nodo der) {
        this.dato = dato;
        this.ve = 0;
        this.izq = izq;
        this.der = der;
    }

    

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public Nodo getIzq() {
        return izq;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }
 
}
