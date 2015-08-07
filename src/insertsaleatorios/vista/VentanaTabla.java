/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creadordeinsertssql.vista;

import creadordeinsertssql.controlador.GestorVentanaPrincipal;
import creadordeinsertssql.modelo.Tabla;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *Es el panel que representa una tabla y
 * se muestra un panel por cada tabla que se tenga. <br>
 * @author Ruben
 */
public class VentanaTabla extends javax.swing.JPanel {

    /**
     * Tabla de la que recoge los datos a mostrar.
     * */
    private final Tabla suTabla;//Se le pasa al constructor
    /**
     *  popup que aparece al hace click izquierdo sobre la vista.
     */
    private JPopupMenu elPopup;
    /**
     * Menu contenido dentro del popup anterior.<br>
     * Contiene el texto del popup y el evento click.<br>
     */
    private JMenuItem elMenuItem;
    
    /**
     * Un array de vistas simples de los datos de una columna de la tabla.
     */
    private final ArrayList<VistaColumna> vistasDeColumnas;
    /**
     * Creates new form VistaDeTabla.<br>
     *
     * La vista de una tabla desde el panel de tablas.<br>
     * Cada vista contiene una tabla de la que obtiene los datos.<br>
     *
     * @param suTabla
     */
    public VentanaTabla(Tabla suTabla) {
        initComponents(); 
        vistasDeColumnas = new ArrayList<>();
        this.suTabla = suTabla;
        panelFondo.setLayout(new GridLayout());           
        setBackground(new Color(20, 40, 40));       
        this.textoTitulo.setText(this.suTabla.getNombre());   
        suTabla.getColumnas().stream().map((col)
                -> new VistaColumna(col)).map((vc) -> {
                    vistasDeColumnas.add(vc);
                    return vc;
                }).forEach((vc) -> {
                    panelFondo.add(vc);
                });
    }

    private void mostrarPopup(java.awt.event.MouseEvent evt){
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

        textoTitulo = new javax.swing.JLabel();
        panelFondo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setMaximumSize(new java.awt.Dimension(32767, 131));
        setName(""); // NOI18N
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        textoTitulo.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        textoTitulo.setForeground(new java.awt.Color(255, 255, 255));
        textoTitulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        textoTitulo.setText("Titulo");

        jPanel1.setPreferredSize(new java.awt.Dimension(0, 100));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(textoTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(textoTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        if (evt.getButton() == MouseEvent.BUTTON3) {//Raton izquierdo pulsado sobre la vista de la tabla
            mostrarPopup(evt);
        } else if (evt.getButton() == MouseEvent.BUTTON1
                || evt.getButton() == MouseEvent.BUTTON2) {
            if (elPopup != null) {
                elPopup.setVisible(false);
                elPopup = null;
            }

        }


    }//GEN-LAST:event_formMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JLabel textoTitulo;
    // End of variables declaration//GEN-END:variables
}
