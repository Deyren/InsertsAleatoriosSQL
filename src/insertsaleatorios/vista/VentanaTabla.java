/*
 * Moreno
 */
package insertsaleatorios.vista;

import insertsaleatorios.Datos;
import insertsaleatorios.modelo.Tabla;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

/**
 * Es el panel que representa una tabla posY se muestra un panel por cada tabla
 * que se tenga. <br>
 *
 * @author Ruben
 */
public final class VentanaTabla extends javax.swing.JPanel implements MouseListener, MouseMotionListener {

    /**
     * Tabla de la que recoge los datos a mostrar.
     *
     */
    private final Tabla suTabla;//Se le pasa al constructor
    /**
     * popup que aparece al hace click izquierdo sobre la vista.
     */
    private JPopupMenu elPopup;
    /**
     * Menu contenido dentro del popup anterior.<br>
     * Contiene el texto del popup posY el evento click.<br>
     */
    private JMenuItem elMenuItem;

    JTextField jtf;

    private final PanelDeTablas padre;

    /**
     * Guardará la posicion del raton en esta ventana al hacer click
     *
     */
    private int posX, posY;

    /**
     * Creates new form VistaDeTabla.<br>
     *
     * La vista de una tabla desde el panel de tablas.<br>
     * Cada vista contiene una tabla de la que obtiene los datos.<br>
     *
     * @param suTabla
     * @param posicion
     * @param padre
     *
     */
    public VentanaTabla(Tabla suTabla, Point posicion, PanelDeTablas padre) {
        initComponents();
        this.padre = padre;
        jtf = new JTextField();
        initComponents2();
        //textoNombreDeLaTabla.setFont(Font.decode("UTF-8"));

       labelPrueba.setFont(new java.awt.Font("Amstrad CPC464", 1, 24)); // NOI18N
        setMinimumSize(Datos.tamañoDeVentanaDeTabla);
        
        labelPrueba.setText(suTabla.getNombre());
        textoInfo.setText("Columnas: " + suTabla.getColumnas().size());
        actualizarTamaño();
        setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
        setLocation(posicion);
        this.suTabla = suTabla;
        setBackground(new Color(20, 40, 240));//Color del fondo del titulo de la tabla
      

    }

    private void actualizacionDePosiciones() {
        java.util.Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                // System.out.println(ratonPulsado);              
            }
        }, 100, 60);
    }

    /**
     * Devuelve el tamaño del string en pixeles como objeto rectangulo
     *
     * @param fuente. Se le pasa el objeto Font del componente
     * @param string. Se le pasa el String a medir.
     * @return
     */
    private  Rectangle2D tamañoDeUnString(Font fuente, String string) {
        FontRenderContext frc = new FontRenderContext(null,
                RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT,
                RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT);
        Rectangle2D rect = (fuente.getStringBounds(string, frc));
        return rect;
    }

    /**
     * Actualiza el tamaño del panel en funcion del tamaño del nombre de la
     * tabla.
     */
    private void actualizarTamaño() {
        Rectangle2D ii = tamañoDeUnString(labelPrueba.getFont(), " " + labelPrueba.getText() + " ");
        Rectangle2D oo =tamañoDeUnString(textoInfo.getFont(), " " + textoInfo.getText() + " ");
        if(ii.getWidth() < oo.getWidth()){
            labelPrueba.setSize((int)oo.getWidth(),(int) ii.getHeight());
             setSize((int)oo.getWidth(), (int) (ii.getHeight()+(oo.getHeight()*2)));
        }else{
             labelPrueba.setSize((int)ii.getWidth(), (int)ii.getHeight());
             setSize((int)ii.getWidth(),  (int)( ii.getHeight()+(oo.getHeight()*2)));
        }
        updateUI();
    }

    private void mostrarPopup(java.awt.event.MouseEvent evt) {
        if (elPopup == null || elMenuItem == null) {
            elPopup = new JPopupMenu();
            elMenuItem = new JMenuItem("Editar " + suTabla.getNombre());
            elPopup.setLocation(evt.getLocationOnScreen());
            elMenuItem.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {//Se pulsa sobre editar                        
                    PanelDeColumnas.getInstance(suTabla);
                    //VentanaPrincipal.setPanelPrincipal(pacol);
                    // GestorVentanaPrincipal.establecerPanelDeFondo(pacol);
                    elPopup.setVisible(false);
                    elPopup = null;
                    elMenuItem = null;
                    System.gc();
                }
            });
            elPopup.add(elMenuItem);
            elPopup.setVisible(true);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        textoInfo = new javax.swing.JLabel();
        labelPrueba = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMinimumSize(new java.awt.Dimension(192, 54));
        setName(""); // NOI18N

        textoInfo.setFont(new java.awt.Font("Andalus", 0, 14)); // NOI18N
        textoInfo.setText("Columnas:");

        labelPrueba.setFont(new java.awt.Font("Andalus", 0, 14)); // NOI18N
        labelPrueba.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPrueba.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textoInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
            .addComponent(labelPrueba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(textoInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPrueba, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void initComponents2() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelPrueba;
    private javax.swing.JLabel textoInfo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseDragged(MouseEvent e) {
        setLocation(getX() + e.getX() - posX, getY() + e.getY() - posY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        padre.setComponentZOrder(this, 0);
        posX = e.getX();
        posY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.consume();
    }
}
