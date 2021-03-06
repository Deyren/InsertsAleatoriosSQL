package insertsaleatorios.vista.componentes;

import insertsaleatorios.Datos;
import insertsaleatorios.modelo.Tabla;
import insertsaleatorios.vista.PanelDeColumnas;
import insertsaleatorios.vista.PanelDeTablas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 * Barra de herramientas para gestionar columnas.
 * 
 * @author pedruben
 */
public class BarraDeHerramientasPanelDeColumnas extends BarraDeHerramientasBasico implements MouseListener {

    private final PanelDeColumnas panel; //El panel al que pertenece

    /**
     * Nombre de los botones de la 
     * barra de herramientas.
     */
    private static final String[] nombres = new String[]{
        "Nueva columna",
        "Borrar columna",      
        "Atras"
    };
    /**
     * Array de botones.
     * Se crearán tantos botones como nombres.
     */
    private final JButton[] botones;

    /**
     * Obtiene el array de botones.
     * @return 
     */
    public JButton[] getBotones() {
        return botones;
    }

    /**
     * Contructor privado.<br>
 Usar 'getInstance' para getInstance el objeto
     * @param name
     * @param panel 
     */
    private BarraDeHerramientasPanelDeColumnas(String name, PanelDeColumnas panel) {
        super(name);
        this.panel = panel;
        botones = new JButton[nombres.length];
        super.setFloatable(false);

        //Añade los botones a la barra. <br>
        for (int i = 0; i < nombres.length; i++) {
            botones[i] = addBoton(nombres[i]);
            add(botones[i]);

        }
        setVisible(true);
    }

    /**
     * Agrega un boton al array de botones
     * El nombre del boton es el que se le pase.
     * @param nombre
     * @return 
     */
    private static JButton addBoton(String nombre) {
        JButton nueva = new JButton(nombre);
        nueva.setName(nombre);
        return nueva;
    }

    /**
     * Metodo estatico para getInstance el objeto
     * @param name
     * @param panel
     * @return 
     */
    public static BarraDeHerramientasPanelDeColumnas getInstance(String name, PanelDeColumnas panel) {
        BarraDeHerramientasPanelDeColumnas tb = new BarraDeHerramientasPanelDeColumnas(name, panel);
        for (JButton boton : tb.botones) {
            //Agrega el listener a los botones.
            //pasandole el propio objeto que contiene los botones.
            boton.addMouseListener(tb);
           
        }
        return tb;
    }

    /**
     * Eventos del ratón para la barra de herramientas de este panel.
     * @param e El objeto evento siempre es del tipo JButton.
     */
    @Override
    public void mouseClicked(MouseEvent e) {      
        JButton jb = (JButton) e.getSource();//Cast del objeto que lanzó el evento.
        if (jb.getName().equals(nombres[0])) {//Boton nueva columna pulsado
            panel.getGestor().nuevaColumna();
        } else if (jb.getName().equals(nombres[1])) {//Boton quitar columna pulsado
            panel.getGestor().quitarUltimaColumna();
        } else if (jb.getName().equals(nombres[2])) {//Boton atras pulsado            
            for(Tabla t: Datos.tablas){
                if(t.equals(panel.getTablaQueEstaEnElPanel())){
                     t.setColumnas(panel.getGestor().getColumnas());
                     break;
                }         
            }
          
           PanelDeTablas.getInstance(); 
         
          // patas.setVisible(true);
           // GestorVentanaPrincipal.establecerPanelDeFondo(patas);     
//            patas.setVisible(true);
//            patas.updateUI();
            
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {        
          dispatchEvent(e);
        }

    @Override
    public void mouseReleased(MouseEvent e) {
          dispatchEvent(e);
        }

    @Override
    public void mouseEntered(MouseEvent e) {
          dispatchEvent(e);
       }

    @Override
    public void mouseExited(MouseEvent e) {
        dispatchEvent(e);
    }


}
