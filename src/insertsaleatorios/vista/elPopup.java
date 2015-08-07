package creadordeinsertssql.vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class elPopup extends JPopupMenu implements MouseListener {

    JMenuItem ji;
   
    /**
     * De momento no se usa
     */
    public elPopup(){ 
    }
    public void iniciar(Point pos) {
        ji = new JMenuItem("Quitar el último");
        ji.addMouseListener(this);
        ji.setBackground(Color.red);
        ji.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pos.x -= 30;
        pos.y -= 15;
        setLocation(pos);
        add(ji);
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       

    }

    @Override
    public void mousePressed(MouseEvent e) {
      //   System.out.println("EEEEEE");
         
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setVisible(false);
        setEnabled(false);

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        dispatchEvent(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setVisible(false);
       
    }
}
