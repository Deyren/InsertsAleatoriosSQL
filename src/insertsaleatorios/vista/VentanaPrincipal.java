/*
 * Dale Moreno
 */
package insertsaleatorios.vista;

import insertsaleatorios.Datos;
import insertsaleatorios.controlador.GestorVentanaPrincipal;
import insertsaleatorios.gestiondearchivos.GestorDeArchivosDelProyecto;
import insertsaleatorios.vista.componentes.BarraDeHerramientasBasico;
import insertsaleatorios.vista.componentes.RMenuItem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout; 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

/**
 * Ventana que contiene el metodo Main. <br>
 * Tambien contiene la barra de menú. <br>
 
___________________ COSAS PARA CAMBIAR_________________________
 - Al guardar el archivo se puede simplemente serializar el array de Datos
      en vez de crear posY leer el XML, que da mucho mas trabajo.
 
 - La ventana que representa una tabla no hace falta que tenga las
      vistas de las columnas.
 
  - El boton de las barras de herramientas para crear tablas debe ser tipo switch, 
      se clickea posY se queda presionado hasta que se haga click en el panel de fondo, que
      será cuando se agrege la nueva tabla en la posicion del ratón. 
 
  - El nombre de cada panel se establece en el propio panel en lugar de mostrar
      el JOptionPane.
      Haciendo doble click en el texto se podrá modificar el nombre. Como en StarUML.
 *      
 *
 * @author Ruben
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Panel principal de fondo donde se establecerá un panel concreto segun lo
     * que haya. <br>
     * Todos los paneles pasados heredan de PanelBasico que a su vez hereda de
     * JInternalFrame
     */
    private JPanel panelFondo;
    /**
     * Para establecer el idioma en el programa. <br>
     * DE momento solo español :)
     */
    private final Datos.Idiomas idioma;
   /**
    * Array que contendrá los elementos de la barra de menu.
    */
    private final JMenu[] menus;
/**
 * Barra de herramientas que se irá asignando segun el panel 
 * que hay en la ventana . <br>
 * Se establece en el metodo estatico establecerPanelDeFondo de 
 * la clase GestorVentanaPrincipal. <br>
 * 
 */
    private BarraDeHerramientasBasico barraDeHerramientas;
    /**
     * Barra de menu donde se añaden los elementos JMenu del array menus.
     */
    private final JMenuBar barraDeMenu;

    /**
     * Devuelve la barra de herramientas. 
     * @return 
     */
    public BarraDeHerramientasBasico getBarraDeHerramientas() {
        return barraDeHerramientas;
    }
   /**
    * Establece la barra de herramientas. <br>
    * @param barraDeHerramientas 
    */
    public void setBarraDeHerramientas(BarraDeHerramientasBasico barraDeHerramientas) {
        this.barraDeHerramientas = barraDeHerramientas;
    }

    /**
     * Obtiene el panel de fondo donde
     * se añaden los objetos PanelBasico e hijos. <br>
     * @return 
     */
    public JPanel getPanelFondo() {
        return panelFondo;
    }
/**
 * Establece el panel de fondo. <br>
 * @param panelFondo 
 */
    public void setPanelFondo(JPanel panelFondo) {
        this.panelFondo = panelFondo;
    }

    /**
     * CONSTRUCTOR sin parametros.
     */
    public VentanaPrincipal() {
        initComponents();
         try {
             //Establece el estilo que tenga el sistema operativo
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("TextField.disabledForeground",Color.RED);// No hace nada :(
        
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(VentanaTabla.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBackground(Color.lightGray);
        setLocation(200, 200);
        setTitle(Datos.NOMBRE_DEL_PROGRAMA);
        barraDeMenu = new JMenuBar();
        idioma = Datos.Idiomas.ESPAÑOL;
        menus = new JMenu[Datos.BarraDeMenuEnEspañol.values().length];       
        panelFondo = new JPanel(new GridLayout());
        panelFondo.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelFondo.setBackground(Datos.COLOR_DE_FONDO_DE_PANELES);
        BorderLayout bl = new BorderLayout();
        setLayout(null);
        panelFondo.setLayout(bl);
        panelFondo.setVisible(true);
        setContentPane(panelFondo);
        iniciarBarraDeMenu();
        setJMenuBar(barraDeMenu);
      

    }

    /**
     * Agrega los elementos a la barra de menú. <br>
 Añade su evento de ratón a cada menu posY ejecuta la accion de cada caso. <br>
     * Los elementos dependen de los enum de la clase Datos. <br>
     */
    private void iniciarBarraDeMenu() {
        if (idioma == Datos.Idiomas.ESPAÑOL) {
            int i = 0;
           
            //Recorre el static enum BarraDeMenuEnEspañol de la clase Datos.
            for (Datos.BarraDeMenuEnEspañol bm : Datos.BarraDeMenuEnEspañol.values()) {
                menus[i] = new JMenu(bm.name());//agrega un menu al array de menus con cada nombre del enum
                if (bm == Datos.BarraDeMenuEnEspañol.Archivo) {//Si es la opcion 'Archivo'
                    //Recorre el static enum de MenuDeArchivoEnEspañol de la clase Datos.
                    for (Datos.MenuDeArchivoEnEspañol op : Datos.MenuDeArchivoEnEspañol.values()) {
                        if (op == Datos.MenuDeArchivoEnEspañol.Separador) {//Si es separador.
                            menus[i].add(new JSeparator());//Agrega un separador al menu
                        } else {
                            RMenuItem unMenuItem = new RMenuItem(op.name());//crea items con los nombres del enum
                            unMenuItem.setName(op.name());//Pone el nombre para obtenerlo en el evento
                            //Agrega evento de raton a cada item del menu
                            unMenuItem.addMouseListener(new MouseAdapter() {
                                
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    JMenuItem unMenuItemInterno = (JMenuItem) e.getSource();//Cast a menuItem
                                    // System.out.println(unMenuItemInterno.getName() + ":      Ventana principal linea 117 mas o menos");

                                    //BOTON SALIR DE LA BARRA DE MENU PULSADO
                                    if (unMenuItemInterno.getName().equals(Datos.MenuDeArchivoEnEspañol.Salir.name())) {
                                        System.exit(0);
                                    }

                                    //BOTON NUEVO DE LA BARRA DE MENU PULSADO 
                                    if (unMenuItemInterno.getName().equals(Datos.MenuDeArchivoEnEspañol.Nuevo.name())) {
                                        //Establece el fondo a panel de tablas     
                                        unMenuItemInterno.setAccelerator(KeyStroke.getKeyStroke("control N"));
                                      
                                        VentanaNuevoProyecto vnp = new VentanaNuevoProyecto();
                                        vnp.setVisible(true);

                                    }
                                    //Boton abrir pulsado
                                    if (unMenuItemInterno.getName().equals(Datos.MenuDeArchivoEnEspañol.Abrir.name())) {
                                        JOptionPane.showMessageDialog(null, "N0x4A\nNo implemented.", "Error......", JOptionPane.ERROR_MESSAGE);

                                    }
                                    //Boton guardar pulsado
                                    if (unMenuItemInterno.getName().equals(Datos.MenuDeArchivoEnEspañol.Guardar.name())) {
                                        //Si hay alguna tabla creada, lo guarda.
                                        if (!Datos.tablas.isEmpty()) {
                                            GestorDeArchivosDelProyecto can = new GestorDeArchivosDelProyecto();
                                            String rutaCompleta = Datos.CARPETA_DEL_PROYECTO + Datos.nombreDelProyectoActual + Datos.EXTENSION_DE_ARCHIVO_DEL_PROYECTO;
                                            can.crearArchivo(new File(rutaCompleta));
                                        } else {
                                            JOptionPane.showMessageDialog(null, "No hay tablas que guardar.", "No hay tablas", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                    //Boton probar pulsado.
                                    if (unMenuItemInterno.getName().equals(Datos.MenuDeArchivoEnEspañol.probarVistaColumnas.name())) {
                                    
                                }}});
                            menus[i].add(unMenuItem);//Añade el menuItem a la barra de menu.
                }}}
                barraDeMenu.add(menus[i]);
                i++; // i se usa para el array de JMenu
            }
        } else if (idioma == Datos.Idiomas.INGLES) {
            throw new Error("Ingles aun no implementado...");
        }
        
      

//______Bucle para debug____________________________________________________________________
        java.util.Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                panelFondo.repaint();
                Datos.tablas.stream().forEach((tabla) -> { //Recorre las tablas
                    tabla.getColumnas().stream().forEach((coo) -> { //Recorre las columans de cada tabla
                        //
                    });});}}
       , 100, 60);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * PUNTO DE ENTRADA DEL PROGRAMA.
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            VentanaPrincipal vp = new VentanaPrincipal();
            //Se le pasa la ventana al gestor para que tenga una referencia de ella.
            GestorVentanaPrincipal.establecerVentanaPrincipal(vp);
            vp.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
