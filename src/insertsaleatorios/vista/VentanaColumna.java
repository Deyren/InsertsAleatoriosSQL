/*
 * CREADOR DE INSERTS
 */
package insertsaleatorios.vista;

import insertsaleatorios.Datos;
import insertsaleatorios.controlador.GestorDeColumnas;
import insertsaleatorios.modelo.Columna;
import insertsaleatorios.modelo.Tabla;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *Ventana en la que se editan los datos de una columna. <br>
 * Actualiza periodicamente el array de columnas de la clase Datos
 * con los datos de el objeto Columna que contiene. <br>
 * @author Ruben
 */
public class VentanaColumna extends javax.swing.JPanel implements ActionListener {

   /**
    * El objeto Columna de esta ventana. <br>
    * se le pasa al constructor.
    */
    private final Columna suColumna;
    private final GestorDeColumnas gestor;

    public JCheckBox getCheckClaveAgena() {
        return checkClaveAgena;
    }
    public JCheckBox getCheckClavePrimaria() {
        return checkClavePrimaria;
    }
    public JCheckBox getCheckNulo() {
        return checkNulo;
    }
    public JComboBox getComboMenorOIgual() {
        return comboMenorOIgual;
    }
    public JComboBox getComboTipoDeDato() {
        return comboTipoDeDato;
    }
    public JTextField getTextoTablaALaQueApunta() {
        return textoTablaALaQueApunta;
    }
    public JTextField getTextoValorMaximo() {
        return textoValorMaximo;
    }
    /**
     * Creates new form AddColumnasView
     *
     * @param columna
     * @param gestor
     * 
     *
     */
    public VentanaColumna(Columna columna,GestorDeColumnas gestor) {
        initComponents();     
        this.suColumna = columna;
        this.gestor=gestor;
        iniciarCampos();
        comprobacionesPeriodicas(100);
    }
    /**
     * Metodo de se ejecuta periodicamente
     * actualizando la vista y el array de Datos.tablas, <br>
     * @param evt 
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        //Clave ajena seleccionada
        if (checkClaveAgena.isSelected()) {
            //suColumna.setForeignKey(Boolean.TRUE);
            if (!textoTablaALaQueApunta.isEnabled()) {
                //Activa el texto de la tabla a la que apunta.
                textoTablaALaQueApunta.setEnabled(true);
                
            }
        } else {//Si clave ajena NO seleccionada
            if (textoTablaALaQueApunta.isEnabled()) {
                //desactuva el texto de tabla a la que apunta si esta activado.
                textoTablaALaQueApunta.setEnabled(false);
                //Quita el texto que haya.
                textoTablaALaQueApunta.setText("");
            }
        }
        //Si el combo box tiene tipo de dato caracter
        if (comboTipoDeDato.getSelectedItem().equals(Datos.TiposDeDato.caracter.toString())) {
            //Si la clave ajena NO esta seleccionada.
            if (!checkClaveAgena.isSelected()) {
                //Si el valor maximo esta DESACTIVADO.
                if (!textoValorMaximo.isEnabled()) {
                    //Activa el texto valor maximo
                    textoValorMaximo.setEnabled(true);
                    //Establece el texto de la tabla a la que apunta.
                    textoTablaALaQueApunta.setText("");

                }
                //Si el combo box de menor o igual esta DESACTIVADO
                if (!comboMenorOIgual.isEnabled()) {
                    //Activa el combo box.
                    comboMenorOIgual.setEnabled(true);
                }
                //Si la clave ajena esta seleccionada.
            } else {
                //Si el texto de valor maximo esta activado.
                if (textoValorMaximo.isEnabled()) {
                    //Desactiva el texto valor maximo.
                    textoValorMaximo.setEnabled(false);

                }
                //Si el combo menor o igual esta activado.
                if (comboMenorOIgual.isEnabled()) {
                    //Desactiva el combo menor o igual.
                    comboMenorOIgual.setEnabled(false);
                }
            }
            //Si el tipo de dato del combo box en un entero.
        } else if (comboTipoDeDato.getSelectedItem().equals(Datos.TiposDeDato.entero.toString())) {
            //Si la clave ajena no esta seleccionada.
            if (!checkClaveAgena.isSelected()) {
                //Si el valor maximo no esta activado.
                if (!textoValorMaximo.isEnabled()) {
                    //Activa el valor maximo.
                    textoValorMaximo.setEnabled(true);
                    //Establece el texto de la tabla a la que apunta.
                    textoTablaALaQueApunta.setText("");
                }
                //Si el combo menor o igual esta activado...
                if (comboMenorOIgual.isEnabled()) {
                    //Desactiva el combo menor o igual.
                    comboMenorOIgual.setEnabled(false);
                }
                //Si la clave ajena SI esta seleccionada.
            } else {
                if (textoValorMaximo.isEnabled()) {
                    textoValorMaximo.setEnabled(false);
                    textoValorMaximo.setText("");

                }
                if (comboMenorOIgual.isEnabled()) {
                    comboMenorOIgual.setEnabled(false);
                }
            }

        } else {
            if (textoValorMaximo.isEnabled()) {
                textoValorMaximo.setEnabled(false);
                textoValorMaximo.setText("");

            }
            if (comboMenorOIgual.isEnabled()) {
                comboMenorOIgual.setEnabled(false);
            }
        }

        if (checkClavePrimaria.isSelected()) {
            if (checkNulo.isEnabled()) {
                checkNulo.setSelected(false);
                checkNulo.setEnabled(false);
            }
        } else {
            if (!checkNulo.isEnabled()) {
                checkNulo.setEnabled(true);
            }
        }
    
       actualizarDatos();
        
    }   
    /**
     * Busca en el array de tablas de la clase Datos
     *  el objeto Columna que sea igual al objeto Columna 
     * que contiene esta clase, cuando lo encuentra
     * llama al metodo asignarValoresDeLaVistaASuColumna(Columna suColumna, VentanaColumna ven)
     *  del gestor de columnas, le pasa el objeto Columna de esta clase y null como segundo parametro.
     */
    private void actualizarDatos(){
           for (Tabla t :Datos.tablas) {
           if(t.equals(gestor.getSuTabla())){
               for(Columna col : t.getColumnas()){
                   if(col.getNumeroDeColumna().equals(suColumna.getNumeroDeColumna())){
                       gestor.asignarValoresDeLaVistaASuColumna(suColumna, this);
                       break;
                   }
               }
               break;
           }
        }
    }
    /**
     * Crea el objeto Timer y llama a su metodo start pasandole el delay. <br>
     * Llama al metodo actionPerformed de esta clase cada 'delay' milisegundos. <br>
     * Se usa para ir actualizando los componentes del panel y los datos.
     * @param delay 
     */
    private void comprobacionesPeriodicas(int delay) {
        new Timer(delay, this).start();
    }
    /**
     * Metodo usado en el constructor. <br>
     * establece los campos del panel, como
     *  los valores de los ComboBox.
     */
    private void iniciarCampos() {
        javax.swing.border.TitledBorder tb = (javax.swing.border.TitledBorder) this.panelContenedor.getBorder();
        tb.setTitle(Integer.toString(suColumna.getNumeroDeColumna()));
        comboTipoDeDato.removeAllItems();
        comboMenorOIgual.removeAllItems();
        //Recorre los tipos de dato para añadirlos al combo box
        for (Datos.TiposDeDato tip : Datos.TiposDeDato.values()) {
            this.comboTipoDeDato.addItem(tip.toString());
        }
        //Añade opciones al combo box de menor o igual.
        comboMenorOIgual.addItem("<");
        comboMenorOIgual.addItem("=");
        textoTablaALaQueApunta.setEnabled(false);
        comboMenorOIgual.setEnabled(false);
        textoValorMaximo.setEnabled(false);
    }
    /**
     * True si el valor que hay en el texto 'valor maximo' es correcto
     *
     * @return
     */
    public boolean valorMaximoCorrecto() {
        boolean salida = false;
        try {
            Integer.parseInt(textoValorMaximo.getText());
            salida = true;
        } catch (NumberFormatException e) {
            e.printStackTrace(System.err);
        }
        return salida;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenedor = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboTipoDeDato = new javax.swing.JComboBox();
        comboMenorOIgual = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        textoValorMaximo = new javax.swing.JTextField();
        panelCheckBoxes = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        checkNulo = new javax.swing.JCheckBox();
        checkClaveAgena = new javax.swing.JCheckBox();
        checkClavePrimaria = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        textoTablaALaQueApunta = new javax.swing.JTextField();

        panelContenedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Euphemia", 0, 18))); // NOI18N

        jLabel2.setText("Tipo de dato");

        comboTipoDeDato.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboMenorOIgual.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("MAX");

        textoValorMaximo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoValorMaximoKeyReleased(evt);
            }
        });

        jLabel4.setText("PK");

        jLabel6.setText("NULL");

        jLabel5.setText("FK");

        javax.swing.GroupLayout panelCheckBoxesLayout = new javax.swing.GroupLayout(panelCheckBoxes);
        panelCheckBoxes.setLayout(panelCheckBoxesLayout);
        panelCheckBoxesLayout.setHorizontalGroup(
            panelCheckBoxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCheckBoxesLayout.createSequentialGroup()
                .addGroup(panelCheckBoxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelCheckBoxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkNulo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(checkClaveAgena, javax.swing.GroupLayout.Alignment.TRAILING)))
            .addGroup(panelCheckBoxesLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(checkClavePrimaria))
        );
        panelCheckBoxesLayout.setVerticalGroup(
            panelCheckBoxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCheckBoxesLayout.createSequentialGroup()
                .addGroup(panelCheckBoxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCheckBoxesLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCheckBoxesLayout.createSequentialGroup()
                        .addComponent(checkClavePrimaria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(panelCheckBoxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCheckBoxesLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(panelCheckBoxesLayout.createSequentialGroup()
                        .addComponent(checkNulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkClaveAgena))))
        );

        jLabel7.setText("Tabla externa");

        javax.swing.GroupLayout panelContenedorLayout = new javax.swing.GroupLayout(panelContenedor);
        panelContenedor.setLayout(panelContenedorLayout);
        panelContenedorLayout.setHorizontalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCheckBoxes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(textoTablaALaQueApunta)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
            .addComponent(comboTipoDeDato, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContenedorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(textoValorMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(comboMenorOIgual, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelContenedorLayout.setVerticalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboTipoDeDato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboMenorOIgual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textoValorMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCheckBoxes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoTablaALaQueApunta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textoValorMaximoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoValorMaximoKeyReleased
        // TODO add your handling code here:

        if (!valorMaximoCorrecto()) {
            textoValorMaximo.setText("");
        }


    }//GEN-LAST:event_textoValorMaximoKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkClaveAgena;
    private javax.swing.JCheckBox checkClavePrimaria;
    private javax.swing.JCheckBox checkNulo;
    private javax.swing.JComboBox comboMenorOIgual;
    private javax.swing.JComboBox comboTipoDeDato;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel panelCheckBoxes;
    private javax.swing.JPanel panelContenedor;
    private javax.swing.JTextField textoTablaALaQueApunta;
    private javax.swing.JTextField textoValorMaximo;
    // End of variables declaration//GEN-END:variables
}
