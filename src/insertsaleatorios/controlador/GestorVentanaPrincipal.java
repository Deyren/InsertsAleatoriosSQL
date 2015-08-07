/*
 * Dale moreno
 */
package creadordeinsertssql.controlador;

import creadordeinsertssql.vista.VentanaPrincipal;
import creadordeinsertssql.vista.componentes.PanelBasico;
import java.awt.BorderLayout;


/**
 * Esta clase gestiona los paneles que deben aparecer 
* en la ventana principal en cada momento.<br>
* Se usará cada vez que se tenga que cambiar el contenido del panel principal.
 * @author Ruben
 */
public class GestorVentanaPrincipal {

    /**   
     * Referenciará al objeto ventana principal
     */
    private static VentanaPrincipal ventanaPrincipal=null;
    /**
     * Obtiene la ventana principal que hereda de JFrame
     * @return 
     */
    public static VentanaPrincipal getVentanaPrincipal(){
        return ventanaPrincipal;
    }
    /**
     * Hace que esta clase obtenga la referencia a la ventana principal.<br>
     * Usar este metodo al empezar.<br>
     * Los metodos de esta clase requieren que 
     * la referencia a ventanaPrincipal no sea null.<br>
     * 
     * @param ventana El objeto ventana principal
     * @throws Error si el objeto VentanaPrincipal que se le pasa es null.
     */
    public static void establecerVentanaPrincipal(VentanaPrincipal ventana){
        if(ventana==null){
            throw new Error("El objeto VentanaPrincipal pasado no puede set null.\n"
                    + "GestorVentanaPrincipal.establecerVentanaPrincipal\n");
        }
        if(ventanaPrincipal==null){
              ventanaPrincipal=ventana;
        }
    }
    /**
     * Se le pasa un panel para que lo muestre
     * en el panel de fondo de la ventana principal.<br>
     * Tambien añade la barra de herramientas que
     * contenga el objeto PanelBasico a la ventana principal. <br>
     * @param panel 
     * @throws Error  si la referencia a la ventana principal es null.
     */
    public static void establecerPanelDeFondo(PanelBasico panel){
       
        if(ventanaPrincipal==null){
            throw new Error("\nLa referencia de la ventana principal es null.\n, "
                    + "Usar antes el metodo estatico 'establecerVentanaPrincipal'"
                    + " de la clase 'GestorVentanaPrincipal' \n");
        } 
       // PanelBasico.actualizarPanel(panel);     
        //Borra todo lo que contenga el panel principal
        panel.setVisible(true);
        ventanaPrincipal.getPanelFondo().removeAll();
        ventanaPrincipal.getPanelFondo().add(panel);    
       ventanaPrincipal.add(panel.getBarraDeHerramientasDelPanel(),BorderLayout.NORTH);  
        ventanaPrincipal.setContentPane(ventanaPrincipal.getPanelFondo());
       //ventanaPrincipal.add(panel);
    }
}
