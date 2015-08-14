/*
 * Dale moreno
 */
package insertsaleatorios.vista;

import insertsaleatorios.controlador.GestorDeTablas;
import insertsaleatorios.controlador.GestorVentanaPrincipal;
import insertsaleatorios.vista.componentes.PanelBasico;
import insertsaleatorios.vista.componentes.BarraDeHerramientasPanelDeTablas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *Panel de fondo que contiene las vistas de las tablas. 
 * @author Ruben
 */
public class PanelDeTablas extends PanelBasico{

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
        return super.panelDeFondo;
    }
    
    private PanelDeTablas() {
        super();      
        jsp = new JScrollPane(super.panelDeFondo);//Agrega el panel al panel con scroll  
        jsp.getVerticalScrollBar().setUnitIncrement(10);//Incremento de la barra de scroll       
        
        //jsp.setHorizontalScrollBar(null);
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
        pan.gestor.pasarTablas();
        GestorVentanaPrincipal.establecerPanelDeFondo(pan);              
        return pan;
    }

    int uu=0;
    boolean aa=false;
    @Override
    public void paint(Graphics g) {
         super.paint(g); 
        g.setColor(Color.WHITE);
        
        if(uu<0){
            aa=true;
        }else if(uu>600){
            aa=false;
        }
        if(aa){
            uu+=5;
        }else{
            uu-=5;
        }
        
        
                
      //  g.drawLine(uu, 0, 600, uu);
        if(gestor.getTablasYVentanas().size()!=0){
       g.drawString(gestor.getTablasYVentanas().getValor().get(0).getLocation().toString(), 20, 20);
            Point posR=MouseInfo.getPointerInfo().getLocation();
        posR.translate(-GestorVentanaPrincipal.getPosicionDeVentana().x, -GestorVentanaPrincipal.getPosicionDeVentana().y);
            g.drawString("Raton: "+posR, 20, 40);
            
     
       
    }
    }
    
    

}
