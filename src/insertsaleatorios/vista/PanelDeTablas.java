/*
 * Dale moreno
 */
package creadordeinsertssql.vista;

import creadordeinsertssql.Datos;
import creadordeinsertssql.controlador.GestorDeTablas;
import creadordeinsertssql.controlador.GestorVentanaPrincipal;
import creadordeinsertssql.vista.componentes.PanelBasico;
import creadordeinsertssql.vista.componentes.BarraDeHerramientasPanelDeTablas;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *Panel de fondo que contiene las vistas de las tablas. 
 * @author Ruben
 */
public class PanelDeTablas extends PanelBasico {

    /**
     * Gestor. El controlador de esta vista.<br>
     */
    private GestorDeTablas gestor;
    /**
     * Panel para que el fondo tenga barra de scroll.<br>
     */
    private final JScrollPane jsp;
   

    /**
     * Devuelve el gestor de esta vista.<br>
     *
     * @return
     */
    public GestorDeTablas getGestor() {
        return gestor;
    }

    public JPanel getPanelDeFondo() {
        return panelDeFondo;
    }

    private PanelDeTablas() {
        super();      
        jsp = new JScrollPane(panelDeFondo);//Agrega el panel al panel con scroll  
        jsp.getVerticalScrollBar().setUnitIncrement(10);//Incremento de la barra de scroll            
        setContentPane(jsp);//Establece el contenido del panel.
    }

    /**
     * Metodo para crear el objeto Panel de Tablas. <br>
     * Tambien crea el gestor de tablas y añade el panel a la ventana principal
     * usando el gestor de la ventana principal.
     *
     * @return
     */
    public static PanelDeTablas getInstance() {
        PanelDeTablas pan = new PanelDeTablas();
        pan.barraDeHerramientasDelPanel = BarraDeHerramientasPanelDeTablas.getInstance("Barra", pan);
        pan.gestor = GestorDeTablas.getInstance(pan);
        pan.gestor.pasarTablas(Datos.tablas);         
        GestorVentanaPrincipal.establecerPanelDeFondo(pan);              
        return pan;
    }

}
