package arbolBB;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jorge Ivan Vasquez Sosa (0901-16-4067).
 */

public class ArbolBB {
    
    //se crea una variable raiz de tipo nodo 
    private Nodo raiz;
    int num_nodos;
    int alt;
    
    //se incializa la variable raiz de tipo nodo como nulo
    public ArbolBB() {
        raiz = null;
    }

      
      //obtener el factor de equilibrio
      public int obtenerValorEqulibrio(Nodo ValorEquilibrado)
      {
          //si el nodo que se llama esta vacio retornara con un valor -1
          if(ValorEquilibrado == null)
          {
             return -1; 
          }else {
              //Y si tiene un valor retornara fe
              return ValorEquilibrado.ve;
          }
          
      }
      
      //rotacion simple a la derecha
        public Nodo rotacionSimpleDerecha(Nodo c)
      {
          //es igual que la rotacion simple a la izquierda solo que orientada a la derecha 
          Nodo auxiliar = c.der;
          c.der = auxiliar.izq;
          auxiliar.izq = c;
          c.ve =Math.max(obtenerValorEqulibrio(c.izq),obtenerValorEqulibrio(c.der))+1;
          auxiliar.ve = Math.max(obtenerValorEqulibrio(auxiliar.izq),obtenerValorEqulibrio(auxiliar.der))+1;
          return auxiliar;
      
          
      }
        //rotacion doble a la derecha
        public Nodo rotacionDobleDerecha(Nodo c)
        {
            
           Nodo temporal;
            //se ara una rotacion simple a la izquierda
           c.der = rotacionSimpleIzquierda(c.der);
           //se ara una rotacion simple a la derecha
           temporal  = rotacionSimpleDerecha(c);
           return temporal;
        }
        
        //Metodo de rotacion simple a la izquierda
      public Nodo rotacionSimpleIzquierda(Nodo c)
      {
           // el dono axiliar sera igual a lo que tenga nodo c.izquierda
          Nodo auxiliar = c.izq;
          // El auxiliar.derecha tendra el valor que traiga c.izquierda
          c.izq = auxiliar.der;
          //y c tendra el valor que traiga c derecha
          auxiliar.der = c;
         //la variable c.ve tendra los valores maximos del lado derecho e izquierdo y se le sumara 1
          c.ve =Math.max(obtenerValorEqulibrio(c.izq),obtenerValorEqulibrio(c.der))+1;
          //tendremos el valor de equilibiro del lado derecho y el izquierdo de la variable auxiliar y le sumaremos 1
          auxiliar.ve = Math.max(obtenerValorEqulibrio(auxiliar.izq),obtenerValorEqulibrio(auxiliar.der))+1;
          //y retornamos la variable auxiliar;
          return auxiliar;
      
      }
        
      //rotacion doble a la izquierda izquierda
        public Nodo rotacionDobleIzquierda(Nodo c)
        {
           
           Nodo temporal;
           //se ara una rotacion simple a la derecha
           c.izq = rotacionSimpleDerecha(c.izq);
           //se ara una rotacion simple a la izquierda
           temporal  = rotacionSimpleIzquierda(c);
           // retornamos la variable temporal
           return temporal;
        }
        
        
        //Metodo para insertar 
        public Nodo insertarAVL(Nodo nuevo, Nodo subArbol)
        {
            //el valor del nodo que tiene la varibale nuevoPadre pasara a la variable SubArbol
            Nodo nuevoPadre = subArbol ;
            //mira si el valor que trae el nuevo dato es menor al arbol que ya esta
            if(nuevo.dato<subArbol.dato)
            {
                //si el el subArbol izquierdo tiene un valor nullo se le asigna el nodo nuevo
                if(subArbol.izq == null)
                {
                    subArbol.izq = nuevo;
                }
                //DE LO CONTRARIO
                else
                {
                    //se ingresa del lado izquierdo los valores establecidos
                    subArbol.izq = insertarAVL(nuevo,subArbol.izq); 
                    if((obtenerValorEqulibrio(subArbol.izq)-obtenerValorEqulibrio(subArbol.der)==2))
                    {
                        if(nuevo.dato<subArbol.izq.dato)
                        {
                            nuevoPadre = rotacionSimpleIzquierda(subArbol);
                        }else 
                        {
                            nuevoPadre = rotacionDobleIzquierda(subArbol);
                        }
                    }
                }
            }else if(nuevo.dato >subArbol.dato)
             {
                 if(subArbol.der == null)
                 {
                     subArbol.der = nuevo;
                 }else
                 {
                     subArbol.der = insertarAVL(nuevo,subArbol.der);
                       if((obtenerValorEqulibrio(subArbol.der)-obtenerValorEqulibrio(subArbol.izq)==2))
                       {
                           if(nuevo.dato>subArbol.der.dato)
                        {
                            nuevoPadre = rotacionSimpleDerecha(subArbol);
                        }else 
                        {
                            nuevoPadre = rotacionDobleDerecha(subArbol);
                        }
                       }
                 }
             }else
            {
                JOptionPane.showMessageDialog(null,"El nodo esta repetido ingrese otro");
            }
            
            if((subArbol.izq ==  null)&&(subArbol.der != null))
            {
               subArbol.ve = subArbol.der.ve+1; 
            }else if((subArbol.der==  null) &&(subArbol.izq != null))
            {
               subArbol.ve = subArbol.izq.ve+1; 
            }else
            {
              subArbol.ve = Math.max(obtenerValorEqulibrio(subArbol.izq),obtenerValorEqulibrio(subArbol.der))+1; 
            }
            return nuevoPadre;
        }
        
        
        
        public boolean insertar(int d)
        {
            Nodo nuevo = new Nodo(d, null, null);
            if (raiz == null)
            {
                raiz = nuevo;
            }else
            {
                raiz = insertarAVL(nuevo,raiz);
            }
            return true;
        }
        
    public boolean agregar(int dato) {
       
        Nodo nuevo = new Nodo(dato, null, null);
        insertar(nuevo, raiz);
        return true;
    }
    
    //Metodo para insertar un dato en el arbol
    public void insertar(Nodo nuevo, Nodo pivote) {
        if (this.raiz == null) {
            raiz = nuevo;
        } else {
            if (nuevo.getDato() <= pivote.getDato()) {
                if (pivote.getIzq() == null) {
                    pivote.setIzq(nuevo);
                } else {
                    insertar(nuevo, pivote.getIzq());
                }
            } else {
                if (pivote.getDer() == null) {
                    pivote.setDer(nuevo);
                } else {
                    insertar(nuevo, pivote.getDer());
                }
            }
        }

    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    //Recorrido preorden, recibe el nodo raiz y una linkedlist para ir guardando el recorrido
    public LinkedList preOrden() {
        LinkedList rec = new LinkedList();
        preorden(raiz, rec);
        return rec;
    }
    
    public void preorden(Nodo aux, LinkedList recorrido) {
        if (aux != null) {
            recorrido.add(aux.getDato());
            preorden(aux.getIzq(), recorrido);
            preorden(aux.getDer(), recorrido);
        }
    }

    //Recorrido inorden, recibe el nodo raiz y una linkedlist para ir guardando el recorrido
    public LinkedList inOrden() {
        LinkedList rec = new LinkedList();
        inorden(raiz, rec);
        return rec;
    }
    
    public void inorden(Nodo aux, LinkedList recorrido) {
        if (aux != null) {
            inorden(aux.getIzq(), recorrido);
            recorrido.add(aux.getDato());
            inorden(aux.getDer(), recorrido);
        }
    }

    //Recorrido postorden, recibe el nodo raiz y una linkedlist para ir guardando el recorrido
    public LinkedList postOrden() {
        LinkedList rec = new LinkedList();
        postorden(raiz, rec);
        return rec;
    }
    public void postorden(Nodo aux, LinkedList recorrido) {
        if (aux != null) {
            postorden(aux.getIzq(), recorrido);
            postorden(aux.getDer(), recorrido);
            recorrido.add(aux.getDato());
        }
    }

    //Metodo para verificar si el arbol esta vacio
    public boolean existe(int dato) {
        Nodo aux = raiz;
        while (aux != null) {
            if (dato == aux.getDato()) {
                return true;
            } else if (dato > aux.getDato()) {
                aux = aux.getDer();
            } else {
                aux = aux.getIzq();
            }
        }
        return false;
    }

    private void altura(Nodo aux, int nivel) {
        if (aux != null) {
            altura(aux.getIzq(), nivel + 1);
            alt = nivel;
            altura(aux.getDer(), nivel + 1);
        }
    }

    //Devuleve la altura del arbol
    public int getAltura() {
        altura(raiz, 1);
        return alt;
    }
    
     public JPanel getdibujo() {
        return new ArbolExpresionGrafico(this);
    }
}
