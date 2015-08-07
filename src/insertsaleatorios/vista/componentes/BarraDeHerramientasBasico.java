/*
 * Dale moreno
 */
package insertsaleatorios.vista.componentes;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.JToolBar;

/**
 *
 * @author pedruben
 */
public class BarraDeHerramientasBasico extends JToolBar{
    public BarraDeHerramientasBasico(){        
        setFloatable(false);
        setBackground(Color.lightGray);
        add(Box.createRigidArea(new Dimension(10, 25)));
        
    }
     public BarraDeHerramientasBasico(String name){
         super(name);
        setFloatable(false);
        setBackground(Color.lightGray);
        add(Box.createRigidArea(new Dimension(10, 25)));
        
    }
}
