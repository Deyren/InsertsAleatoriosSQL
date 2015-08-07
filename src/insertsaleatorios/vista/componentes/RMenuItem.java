/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creadordeinsertssql.vista.componentes;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JMenuItem;

/**
 *
 * @author pedruben
 */
public class RMenuItem extends JMenuItem{
    
   // private Graphics gr;
    public RMenuItem(String text){
        super(text);
       
      //  setOpaque(true);
        setBackground(Color.lightGray);
      
    }
    
    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
      //  this.gr=graphics;
      //  gr.setColor(Color.PINK);
    
    }
    
}
