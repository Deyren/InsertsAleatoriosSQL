/*
 * Dale moreno
 */
package creadordeinsertssql.vista;

import creadordeinsertssql.Datos;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *Clase que muestra la ventana para crear un
 * nuevo proyecto.
 * @author pedruben
 */
public class VentanaNuevoProyecto extends javax.swing.JFrame {

    /**
     * Creates new form VentanaNuevoProyecto
     * @param posicion Posicion en la que aparecerá
     */
    public VentanaNuevoProyecto(Point posicion) {
        initComponents();
        setTitle("Nuevo proyecto...");
        textoNombreProyecto.requestFocus();
        setLocation(posicion);
        textoRuta.setEditable(false);
        textoRuta.setText(Datos.CARPETA_DEL_PROYECTO);
      
         }
    
    private void nuevoProyecto(){
          // Si el nombre esta vacio
        if (textoNombreProyecto.getText().trim().isEmpty()) {
            labelErrores.setText("El campo nombre no puede estar vacio.");
        } else if (textoNombreProyecto.getText().contains(".")) {
            labelErrores.setText("El texto no puede contener puntos.");
        } else {//Si el nombre esta bien escrito.
            
            // Cambia el nombe de proyecto 
            Datos.nombreDelProyectoActual=textoNombreProyecto.getText().trim();
            
             //Guarda el archivo. mejor no guardar al crear nuevo proyecto.
            //CreadorDeArchivoDeNuevoProyecto cac = new CreadorDeArchivoDeNuevoProyecto();
//            cac.crearArchivo(Datos.tablas, new File(Datos.CARPETA_DEL_PROYECTO
//                    + textoNombreProyecto.getText().trim()
//                    + Datos.EXTENSION_DE_ARCHIVO_DEL_PROYECTO));
            
            //Borra los datos de las tablas.
            
            Datos.tablas.clear();
            
            //Añade el panel de tablas a la ventana principal.
            PanelDeTablas.getInstance();
          //  GestorVentanaPrincipal.establecerPanelDeFondo(PanelDeTablas.getInstance());  
            
            setVisible(false);
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
        jLabel1 = new javax.swing.JLabel();
        textoNombreProyecto = new javax.swing.JTextField();
        botonCrear = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        textoRuta = new javax.swing.JTextField();
        botonSeleccionarCarpeta = new javax.swing.JButton();
        labelErrores = new javax.swing.JLabel();
        botonCancelar = new javax.swing.JButton();

        setIconImages(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel1.setText("Nombre:");

        textoNombreProyecto.setText("Hola");
        textoNombreProyecto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoNombreProyectoKeyPressed(evt);
            }
        });

        botonCrear.setText("Crear");
        botonCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonCrearMouseClicked(evt);
            }
        });

        jLabel2.setFont(jLabel1.getFont());
        jLabel2.setText("Ruta:");

        textoRuta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoRutaKeyPressed(evt);
            }
        });

        botonSeleccionarCarpeta.setText("...");
        botonSeleccionarCarpeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonSeleccionarCarpetaMouseClicked(evt);
            }
        });
        botonSeleccionarCarpeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonSeleccionarCarpetaKeyPressed(evt);
            }
        });

        labelErrores.setForeground(new java.awt.Color(204, 102, 0));

        botonCancelar.setText("Cancelar");
        botonCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonCancelarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(botonCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonCancelar)
                                .addGap(0, 57, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(28, 28, 28)
                                .addComponent(textoRuta))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoNombreProyecto)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonSeleccionarCarpeta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelErrores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textoNombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonSeleccionarCarpeta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelErrores, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCrear)
                    .addComponent(botonCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonSeleccionarCarpetaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonSeleccionarCarpetaMouseClicked
        // TODO add your handling code here:
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File(Datos.CARPETA_DEL_PROYECTO));
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = jfc.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File archivo = jfc.getSelectedFile();
            Datos.CARPETA_DEL_PROYECTO = archivo.getAbsolutePath();
            textoRuta.setText(Datos.CARPETA_DEL_PROYECTO);
            
        }
    }//GEN-LAST:event_botonSeleccionarCarpetaMouseClicked

    private void botonCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCrearMouseClicked
        nuevoProyecto();
    }//GEN-LAST:event_botonCrearMouseClicked

    private void botonCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCancelarMouseClicked
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_botonCancelarMouseClicked

    private void textoNombreProyectoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNombreProyectoKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            nuevoProyecto();
        }
    }//GEN-LAST:event_textoNombreProyectoKeyPressed

    private void botonSeleccionarCarpetaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botonSeleccionarCarpetaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            nuevoProyecto();
        }
    }//GEN-LAST:event_botonSeleccionarCarpetaKeyPressed

    private void textoRutaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoRutaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            nuevoProyecto();
        }
    }//GEN-LAST:event_textoRutaKeyPressed



    /**
     * Main para probar esto.<br>
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaNuevoProyecto(new Point(200, 200)).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonCrear;
    private javax.swing.JButton botonSeleccionarCarpeta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelErrores;
    private javax.swing.JTextField textoNombreProyecto;
    private javax.swing.JTextField textoRuta;
    // End of variables declaration//GEN-END:variables
}
