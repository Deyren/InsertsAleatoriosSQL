package insertsaleatorios.controlador;

import insertsaleatorios.Datos;
import insertsaleatorios.DobleArray;
import insertsaleatorios.modelo.Tabla;
import insertsaleatorios.vista.PanelDeTablas;
import insertsaleatorios.vista.VentanaTabla;
import insertsaleatorios.vista.componentes.PanelBasico;
import java.awt.Point;

/**
 * Esta clase es para gestional el panel de tablas.<br>
 * creando y borrando tablas y actualizando la panel del PanelDeTablas.<br>
 * Es quien tiene el doble array de tablas y vistas<br>
 * Es quien crea y borra tablas y agrega la panel de cada tabla al panel.<br>
 * Se crea con un metodo estatico. 'getInstance' y hay que pasarle un objeto del
 * tipo PanelDeTablas.<br>
 *
 * @author pedruben
 */
public class GestorDeTablas {

    private static int posXDeCadaTabla, posYDeCadaTabla;
    /**
     * Panel que contiene a este gestor.<br>
     * Hay que pasarselo al constructor para iniciarlo.<br>
     */
    private final PanelDeTablas panel;

    private final DobleArray<Tabla, VentanaTabla> tablasYVentanas;

    /**
     * Metodo estatico que devuelve un objeto de esta clase Solo devuelve uno.
     * siempre es el mismo.
     *
     * @param vista Se le pasa la panel de las tablas
     * @return El objeto de esta clase.
     */
    public static GestorDeTablas getInstance(PanelDeTablas vista) {
        return new GestorDeTablas(vista);
    }

    /**
     * Constructor privado.<br>
     * El gestor se crea desde la panel de tablas. Al crearlo agrega las tablas
     * de lo que haya en Datos.tablas
     *
     * @param vista
     */
    private GestorDeTablas(PanelDeTablas vista) {
        this.panel = vista;
        tablasYVentanas = new DobleArray<>();
        
    }

    /**
     * Rellena su array de tablasYVentanas con las tablas de Datos.tablas Y
     * añade un objeto VentanaTabla por cada tabla, <br>
     */
    public final void pasarTablas() {
        tablasYVentanas.removeAll();
        posXDeCadaTabla=0;
        posYDeCadaTabla=0;
        Datos.tablas.stream().forEach((tabla) -> {
            VentanaTabla vt = new VentanaTabla(tabla,
                    new Point(posXDeCadaTabla += Datos.tamañoDeVentanaDeTabla.width,
                            posYDeCadaTabla += Datos.tamañoDeVentanaDeTabla.height));
            tablasYVentanas.add(tabla, vt);
        });
        actualizarVista();
    }

    /**
     * Se le pasa un String con el nombre de la tabla La tabla se crea dentro
     * del metodo y la añade al array.
     *
     * @param tablename
     */
    public void nuevaTabla(String tablename) {
        Tabla tabla = Tabla.getTablaVacia(tablename);//Crea el objeto Tabla.
        VentanaTabla vt = new VentanaTabla(tabla,
                new Point(posXDeCadaTabla += Datos.tamañoDeVentanaDeTabla.width,
                        posYDeCadaTabla += Datos.tamañoDeVentanaDeTabla.height));
        vt.setVisible(true);
        Datos.tablas.add(tabla);//Agrega la tabla al array estatico
        tablasYVentanas.add(tabla, vt);//Añade la tabla y la ventana de esa tabla.
        this.panel.getPanelDeFondo().removeAll();//Borra lo que haya en la panel       
        //Recorre el arrayList
        for (int i = 0; i < tablasYVentanas.size(); i++) {
            // Tabla tx = tablasYVentanas.getKey(i);//Obtiene la tabla
            vt = tablasYVentanas.getValue(i);//Obtiene la panel de cada elemento del array
            this.panel.getPanelDeFondo().add(vt);//Añade las vistas al panel de fondo 

        }
    

    }

    private void actualizarVista() {
        
        for (VentanaTabla col : tablasYVentanas.getValor()) {
            col.setVisible(true);
            this.panel.getPanelDeFondo().add(col);
        }
        panel.getPanelDeFondo().updateUI();
        PanelBasico.actualizarPanel(panel);
        
    }

}
