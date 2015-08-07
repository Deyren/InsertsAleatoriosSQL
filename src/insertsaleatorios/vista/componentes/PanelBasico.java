/*
 * Dale moreno
 */
package creadordeinsertssql.vista.componentes;

import creadordeinsertssql.Datos;
import java.awt.FlowLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *Panel del que heredan todos los paneles
 * que van apareciendo en la ventana principal.<br>
 * Contiene un objeto BarraDeHerramientasBasico
 * y los paneles que heredan de esta clase asignan su propia barra
 *  de herramientas. <br>
 *
 * @author Ruben
 */
public class PanelBasico extends JInternalFrame{
    /**
     * Barra de herramientas que las clases que 
     * hereden de esta asignaran el suyo propio. <br>
     */
    protected BarraDeHerramientasBasico barraDeHerramientasDelPanel;
    /**
     * Panel de fondo donde se añadirán las columnas
     */
    protected final JPanel panelDeFondo;
     /**
      * Devuelve la barra de herramientas de este panel
      * @return 
      */
    public BarraDeHerramientasBasico getBarraDeHerramientasDelPanel() {
        return barraDeHerramientasDelPanel;
    }
    /**
     * CONSTRUCTOR
     */
    public PanelBasico(){
        panelDeFondo = new JPanel();           
        panelDeFondo.setBackground(Datos.COLOR_DE_FONDO_DE_PANELES);       
        panelDeFondo.setBorder(null);
        panelDeFondo.setLayout(new FlowLayout(FlowLayout.LEFT)); 
    }
    /**
     * Actualiza el panel con updateUI() del panel,
     *  borra la barra de titulo del panel y establece
     *  setVisible a true. <br>
     * Se ve que al usar updateUI() vuelve a aparecer la
     *  barra de titulo del panel y hay que volver a quitarla. <br>
     * <br><br><br>CODIGO______<br>
     *  panel.updateUI(); <br>
         BasicInternalFrameUI bi = (BasicInternalFrameUI) panel.getUI(); <br>
         bi.setNorthPane(null); <br>
         panel.setVisible(true);  <br>
         *  
     * @param panel PanelBasico que hereda de JInternalFrame
     */
     public static void actualizarPanel(PanelBasico panel){
         panel.updateUI();
         BasicInternalFrameUI bi = (BasicInternalFrameUI) panel.getUI();
         bi.setNorthPane(null);
         //panel.setVisible(true);
     }
   
}
