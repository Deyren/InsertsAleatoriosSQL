/*
 * Dale moreno
 */
package creadordeinsertssql.vista.componentes;

import creadordeinsertssql.vista.PanelDeTablas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 * Usar metodo estatico 'getInstance'
 *
 * @author pedruben
 */
public class BarraDeHerramientasPanelDeTablas extends BarraDeHerramientasBasico implements MouseListener {
private static final String[] nombres=new String[]{
             "Nueva tabla","Editar tabla"        
     };
    private final PanelDeTablas panel;//Panel al que pertenece    
    private final JButton[] Botones;//Botones de esta barra de herramientas.

    public JButton[] getBotones() {
        return Botones;
    }

     private static JButton addBoton(String nombre){
        JButton nueva = new JButton(nombre);
        nueva.setName(nombre);
        return nueva;
   }
    
    
 private BarraDeHerramientasPanelDeTablas(String name, PanelDeTablas panel) {
        super(name);
        this.panel = panel;
        Botones = new JButton[nombres.length];
        super.setFloatable(false);
       
         for(int i=0;i<nombres.length;i++){
            Botones[i]=addBoton(nombres[i]);
                 add(Botones[i]);
               
        }
      
    }

    public static BarraDeHerramientasPanelDeTablas getInstance(String name, PanelDeTablas panel) {
        BarraDeHerramientasPanelDeTablas tb = new BarraDeHerramientasPanelDeTablas(name, panel);
        for (JButton b : tb.getBotones()) {
            b.addMouseListener(tb);
        }
        return tb;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton bot = (JButton) e.getSource();
        if (bot.getName().equals(nombres[0])) {//Boton nueva tabla pulsado
            //Pide el nombre de la nueva tabla
            String tablename = JOptionPane.showInputDialog(null, "Nombre de la tabla", "Nueva tabla...", JOptionPane.QUESTION_MESSAGE);
            if (tablename != null) {
                if ( ! tablename.isEmpty() ) {                    
                    panel.getGestor().nuevaTabla(tablename);//Añade la tabla con el metodo del gestor                   
                    PanelBasico.actualizarPanel(panel);
                   
                }else{                                                 
                      panel.getGestor().nuevaTabla("HerramientasDeTablas");//Añade la tabla con el metodo del gestor                   
                     PanelBasico.actualizarPanel(panel);
                   
                }
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
