package insertsaleatorios.vista;

import insertsaleatorios.controlador.GestorDeColumnas;
import insertsaleatorios.controlador.GestorVentanaPrincipal;
import insertsaleatorios.modelo.Tabla;
import insertsaleatorios.vista.componentes.PanelBasico;
import insertsaleatorios.vista.componentes.BarraDeHerramientasPanelDeColumnas;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *Es el panel de fondo donde se añadirán las columnas . <br>
 * Contiene su propia barra de herramientas. 
 * @author Ruben
 */
public class PanelDeColumnas extends PanelBasico {

    /**
     *
     * gestor que añade o quita columnas. 
     */
    private GestorDeColumnas gestor;

    /**
     * Tabla que se muestra en el panel. <br>
     * Se le pasa al constructor. <br>
     */
    private final Tabla suTabla;

    /**
     * Devuelve la tabla que se esta mostrando en el panel. <br>
     *
     * @return la tabla
     */
    public Tabla getTablaQueEstaEnElPanel() {
        return suTabla;
    }

    /**
     * Devuelve el objeto gestor de este panel. <br>
     *
     * @return
     */
    public GestorDeColumnas getGestor() {
        return gestor;
    }

    /**
     * Se usa para que el panel de fondo tenga scroll.
     */
    private final JScrollPane jsp;
  
    
    public JPanel getPanelDeFondo() {
        return panelDeFondo;
    }

    public void setFondo(JPanel panel) {
        this.panelDeFondo.add(panel);
        setContentPane(this.panelDeFondo);
        PanelBasico.actualizarPanel(this);      
    }

    /**
     * CONSTRUCTOR Se le pasa la tabla que se editará en el panel. <br>
     *
     * @param tablaParaEstePanel
     */
    private PanelDeColumnas(Tabla tablaParaEstePanel) {
        super();
        super.barraDeHerramientasDelPanel =  BarraDeHerramientasPanelDeColumnas.getInstance("Barra", this);
        this.suTabla = tablaParaEstePanel;
        jsp = new JScrollPane(panelDeFondo); 
        jsp.getVerticalScrollBar().setUnitIncrement(10);
        jsp.add(panelDeFondo);
        setContentPane(jsp);
    }

    /**
     * Crea un objeto panel de columnas pasandole la tabla a la que pertenecerá. <br>
     * 1º Crea el objeto PanelDeColumnas <br>
     * 2º Establece su barra de herramientas <br>
     * 3º Crea el objeto GestorDeColumnas y le pasa el panel <br>
     * 4º Crea una vista para cada columna a traves del gestor con el metodo pasarColumnas<br>
     * 5º Establece el panel de fondo a este panel creado <br>
     * 6º Devuelve el panel.
     * <br><br>CODIGO<br>
     *    PanelDeColumnas pan = new PanelDeColumnas(tablaParaEstePanel);
        pan.barraDeHerramientasDelPanel= BarraDeHerramientasPanelDeColumnas.getInstance("Barra", pan);
        pan.gestor = GestorDeColumnas.getInstance(pan, tablaParaEstePanel);
        pan.gestor.pasarColumnas(pan.suTabla.getColumnas());
        GestorVentanaPrincipal.establecerPanelDeFondo(pan);
        return pan;
     * @param tablaParaEstePanel
     * @return el objeto de esta clase.
     */
    public static PanelDeColumnas getInstance(Tabla tablaParaEstePanel) {
        PanelDeColumnas pan = new PanelDeColumnas(tablaParaEstePanel);
        pan.barraDeHerramientasDelPanel= BarraDeHerramientasPanelDeColumnas.getInstance("Barra", pan);
        pan.gestor = GestorDeColumnas.getInstance(pan, tablaParaEstePanel);
        pan.gestor.pasarColumnas(pan.suTabla.getColumnas());
        GestorVentanaPrincipal.establecerPanelDeFondo(pan);
        return pan;
    }

}
