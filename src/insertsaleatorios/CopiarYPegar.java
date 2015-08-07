/*
 * Dale moreno
 */
package insertsaleatorios;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Esta clase usa el portapapeles del sistema
 * para copiar o pegar un String. <br>
 * @author Ruben
 */
public class CopiarYPegar {

    /**
     * Devuelve un String con lo que haya en el portapapeles del sistema. <br>
     * @return 
     */
    public static String pegar() {
        String salida=null;
        try {
            //Obtiene el portapapeles del sistema
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            //Obtiene el dato que haya. El objeto que se le pase no importa...
            Transferable dato = clipboard.getContents(new Object());
            //DataFlavor es para interpretar ese dato. puede ser texto, xml ...
            //Se le pasa un tipo MIME de String.
            DataFlavor dataFlavorStringJava = new DataFlavor("application/x-java-serialized-object; class=java.lang.String");

            // Si el dato se puede conseguir como String java..
            if (dato.isDataFlavorSupported(dataFlavorStringJava)) {
               salida = (String) dato.getTransferData(dataFlavorStringJava);         
            }

        } catch (ClassNotFoundException | UnsupportedFlavorException | IOException ex) {
            Logger.getLogger(CopiarYPegar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salida;
    }

    /**
     * Añade un String al portapapeles del sistema.
     * @param text 
     */
    public static void copiar(String text) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection ss = new StringSelection(text);
        clipboard.setContents(ss, ss);

    }

    /**
     * Main para probar esto. <br>
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(pegar());
    }
}
