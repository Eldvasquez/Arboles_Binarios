
package arbolBB;
/**
 *
 * @author Jorge Ivan Vasquez Sosa (0901-16-4067)
 */

import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class arbolBalanceado {
    
  ArbolBB arbol = new ArbolBB();
  
    public boolean insertar(Integer dato)
    {
        return(this.arbol.insertar(dato));
    }
    //Muestra en resultado de operaciones que tipo de recorrido es
    private String recorrido(LinkedList lT, String msg)
    {
        int i = 0;
        String r = msg + "\n";
        while (i < lT.size())
        {
            r += "\t" + lT.get(i).toString() + " ";
            i++;
        }
        return (r);
    }
    
    
     public String preOrden() {
        LinkedList lT = this.arbol.preOrden();
        return (recorrido(lT, "PreOrden"));
    }

    public String inOrden() {
        LinkedList lT = this.arbol.inOrden();
        return (recorrido(lT, "InOrden"));
    }

    public String postOrden() {
        LinkedList lT = this.arbol.postOrden();
        return (recorrido(lT, "PosOrden"));
    }
    
    
    public JPanel getDibujo() {
        return this.arbol.getdibujo();
    }
    
}
    
    

