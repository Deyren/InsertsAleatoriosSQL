package creadordeinsertssql.controlador;

import creadordeinsertssql.Datos;
import creadordeinsertssql.DobleArray;
import creadordeinsertssql.modelo.Tabla;
import creadordeinsertssql.vista.PanelDeTablas;
import creadordeinsertssql.vista.VentanaTabla;
import creadordeinsertssql.vista.componentes.PanelBasico;
import java.util.ArrayList;
import javax.swing.SpringLayout;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 * Esta clase es para gestional el panel de tablas.<br>
 creando y borrando tablas y actualizando la panel del PanelDeTablas.<br>
 * Es quien tiene el doble array de tablas y vistas<br>
 Es quien crea y borra tablas y agrega la panel de cada tabla al panel.<br>
 * Se crea con un metodo estatico. 'getInstance' y hay que pasarle un objeto del tipo PanelDeTablas.<br>
 * @author pedruben
 */
public class GestorDeTablas {

    /**
     * Panel que contiene a este gestor.<br>
     * Hay que pasarselo al constructor para iniciarlo.<br>
     */
    private final PanelDeTablas panel;
 
    private final DobleArray<Tabla, VentanaTabla> tablasYVistas;
 
    /**
     * Metodo estatico que devuelve un objeto de esta clase
     * Solo devuelve uno. siempre es el mismo.
     * @param vista Se le pasa la panel de las tablas
     * @return El objeto de esta clase.
     */
    public static GestorDeTablas getInstance(PanelDeTablas vista) {               
        return new GestorDeTablas(vista);
    }
/**
 * Constructor privado.<br>
 El gestor se crea desde la panel de tablas.
 * Al crearlo agrega las tablas de lo que haya en Datos.tablas
 * @param vista 
 */
    private GestorDeTablas(PanelDeTablas vista) {
        this.panel = vista;
        tablasYVistas = new DobleArray<>();        
        
    }
    /**
     * Se le pasa el array de tablas, <br>
       el metodo crea un panel para cada tabla
 y lo añade a su array tablasYVistas. <br>
     *
     * @param tablas
     */
    public final void pasarTablas(ArrayList<Tabla> tablas) {
        tablas.stream().forEach((tabla) -> {
            VentanaTabla vt = new VentanaTabla(tabla);
            tablasYVistas.add(tabla, vt);
            
        });
        actualizarVista();
    }

    /**
     * Se le pasa un String con el nombre de la tabla
     * La tabla se crea dentro  del metodo y la añade al array.
     * @param tablename 
     */
     public void nuevaTabla(String tablename) {
         Tabla tabla = Tabla.getTablaVacia(tablename);//Crea el objeto Tabla.
        VentanaTabla vt = new VentanaTabla(tabla);//Se le pasa la tabla a la panel.
        vt.setVisible(true);
         Datos.tablas.add(tabla);//Agrega la tabla al array estatico
        tablasYVistas.add(tabla, vt);//Añade la tabla y la panel de esa tabla.
        this.panel.getPanelDeFondo().removeAll();//Borra lo que haya en la panel       
        //Recorre el arrayList
        for (int i = 0; i < tablasYVistas.size(); i++) {
           // Tabla tx = tablasYVistas.getKey(i);//Obtiene la tabla
            vt = tablasYVistas.getValue(i);//Obtiene la panel de cada elemento del array
            this.panel.getPanelDeFondo().add(vt);//Añade las vistas al panel de fondo           
        }            
          
          PanelBasico.actualizarPanel(panel);
         panel.getPanelDeFondo().updateUI();
      
    }
     
     private void actualizarVista(){
         for (Tabla tab:tablasYVistas.getClave()) {
              VentanaTabla vt = new VentanaTabla(tab);//Se le pasa la tabla a la panel.
              vt.setVisible(true);
              this.panel.getPanelDeFondo().add(vt);
         }
        
         PanelBasico.actualizarPanel(panel);
         panel.getPanelDeFondo().updateUI();    
    }

     
     
}