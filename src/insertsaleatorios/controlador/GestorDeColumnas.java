package insertsaleatorios.controlador;

import insertsaleatorios.Datos;
import insertsaleatorios.DobleArray;
import insertsaleatorios.modelo.Columna;
import insertsaleatorios.modelo.Tabla;
import insertsaleatorios.vista.PanelDeColumnas;
import insertsaleatorios.vista.VentanaColumna;
import insertsaleatorios.vista.componentes.PanelBasico;
import java.util.ArrayList;

/**
 * Para editar las columnas.
 *
 * @author pedruben
 */
public class GestorDeColumnas {

    private int numeroParaAsignarAColumnas = 1;
    private final PanelDeColumnas panel;
    private final DobleArray<Columna, VentanaColumna> columnasYVentanas;   //auxliar para ig guardando los cambios
    //private DobleArray<Columna, VentanaColumna> columnasYVentanas;
    /**
     * Tabla a la que pertenece la columna de este gestor
     */
    private final Tabla suTabla;

    public Tabla getSuTabla() {
        return suTabla;
    }

    private GestorDeColumnas(PanelDeColumnas vista, Tabla suTabla) {
        this.panel = vista;
        this.suTabla = suTabla;
        this.columnasYVentanas = new DobleArray<>();

        // this.columnasYVentanas = new DobleArray();
    }

    public ArrayList<Columna> getColumnas() {
        return columnasYVentanas.getClave();
    }

    /**
     * Metodo estatic que crea el gestor. <br>
     * Se le pasa el panel de columnas que usará
     * y la tabla a la 
     * @param vista
     * @param suTabla
     * @return
     */
    public static GestorDeColumnas getInstance(PanelDeColumnas vista, Tabla suTabla) {
        return new GestorDeColumnas(vista, suTabla);
    }

    /**
     * Coge los valores del objeto Columna
     * y los asigna al objeto VentanaColumna. <br>
     * @param col
     * @param ven 
     */
    private void asignarValoresDeColumnasAVistas(Columna col, VentanaColumna ven) {
        ven.getComboTipoDeDato().setSelectedIndex(col.getTipoDeDato().ordinal());
        ven.getCheckClavePrimaria().setSelected(col.isPrimaryKey());
        ven.getCheckClaveAgena().setSelected(col.isForeignKey());
        ven.getCheckNulo().setSelected(col.isNulo());
        ven.getComboMenorOIgual().setSelectedItem(col.getMayorOIgual());
        ven.getTextoValorMaximo().setText(String.valueOf(col.getValorMaximo()));
        //ven.getTextoTablaALaQueApunta().setText(col.getTablaALaQueApunta());
    }

    /**
     * Se le pasa el array de columnas y crea una ventana de columna para cada
     * columna. <br>
     *
     * @param cols
     */
    public final void pasarColumnas(ArrayList<Columna> cols) {
       // for (Columna col : cols)
        cols.stream().forEach((col) -> {
            VentanaColumna vent = new VentanaColumna(col, this);
            asignarValoresDeColumnasAVistas(col, vent);
            columnasYVentanas.add(col, vent);
        });
        actualizarVista();
    }

    private void actualizarVista() {
        //  for (Columna col : columnasYVentanas.getClave()) 
        columnasYVentanas.getClave().stream().map((col) -> 
                new VentanaColumna(col, this)).forEach((vent) -> {
            vent.setVisible(true);
        });
        
       
        PanelBasico.actualizarPanel(panel);
        panel.getPanelDeFondo().updateUI();
      
    }

    /**
     * Recoge los valores de la panel de VentanaColumna y los asigna el objeto
     * Columna. <br>
     *
     * @param suColumna
     * @param ven
     */
    public void asignarValoresDeLaVistaASuColumna(Columna suColumna, VentanaColumna ven) {
        Datos.TiposDeDato ttip = Datos.TiposDeDato.valueOf(ven.getComboTipoDeDato().getSelectedItem().toString());
        suColumna.setTipoDeDato(ttip);
        suColumna.setMayorOIgual(ven.getComboMenorOIgual().getSelectedItem().toString().charAt(0));
        //System.out.println("Valor maximo correcto: "+ven.getTextoValorMaximo().getText());
        if (ven.getTextoValorMaximo().getText().trim().isEmpty()) {
            suColumna.setValorMaximo(0);
        } else {
            suColumna.setValorMaximo(Integer.parseInt(ven.getTextoValorMaximo().getText()));
        }
        suColumna.setPrimaryKey(ven.getCheckClavePrimaria().isSelected());
        suColumna.setForeignKey(ven.getCheckClaveAgena().isSelected());
        suColumna.setNulo(ven.getCheckNulo().isSelected());
        suColumna.setTablaALaQueApunta(ven.getTextoTablaALaQueApunta());
    }

    private void guardarColumnasEnElArray() {
        // Guarda el array de columnas en la tabla que coincida suTabla.
        Datos.tablas.stream().filter((t) -> (t.equals(suTabla))).forEach((t) -> {
            t.setColumnas(columnasYVentanas.getClave());
        });
    }

    /**
     * Añade una nueva columna. <br>
     * Es usado en la barra de herramientas del panel de columnas. <br>
     * cuando se pulsa en nueva columna. <br>
     *
     *
     */
    public void nuevaColumna() {
        Columna columna = new Columna(suTabla, numeroParaAsignarAColumnas++);
        columna.setMayorOIgual('0');
        columna.setValorMaximo(0);
        VentanaColumna nuke = new VentanaColumna(columna, this);
        columnasYVentanas.add(columna, nuke);
        guardarColumnasEnElArray();
        panel.setFondo(nuke);
    }

    public void quitarUltimaColumna() {
        int pos = columnasYVentanas.size();
        if (pos > 0) {
            columnasYVentanas.getValue(pos - 1).setVisible(false);
            //Columna.reducirNumeroDeColumna();
            columnasYVentanas.remove(pos - 1);
            numeroParaAsignarAColumnas--;

        }
    }

}
