/*
 * Dale moreno
 */
package insertsaleatorios.gestiondearchivos;

import insertsaleatorios.Datos;
import insertsaleatorios.modelo.Columna;
import insertsaleatorios.modelo.Tabla;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Clase con un metodo publico para crear el archivo xml con los datos de las
 * tablas.
 *
 * @author pedruben
 */
public class GestorDeArchivosDelProyecto {

    private Document doc;
    private final ManejadorDeEventosSAX saxCallbacks;

    public GestorDeArchivosDelProyecto() {
        doc = null;
        saxCallbacks = new ManejadorDeEventosSAX();
    }

    /**
     * Crea el xml con los datos de las tablas y sus columnas.
     *
     * @param lasTablas el array de tablas
     * @param archivo el archivo donde se guardará. Si existe lo sobreescribe
     *
     */
    public void crearArchivo(ArrayList<Tabla> lasTablas, File archivo) {

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.newDocument();

            doc.setXmlVersion("1.0");

            //Raiz del documento
            Element raiz = doc.createElement("creadordeinserts");
            // Añade el namespace para el xsd
            raiz.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            raiz.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:noNamespaceSchemaLocation", Datos.RUTA_DEL_XSD);

            //Añade la fecha al documento como comentario
            doc.appendChild(doc.createComment("\n\t\t\t\t" + getFecha() + "\n\t\t" + Datos.NOMBRE_DEL_PROGRAMA + "\n"));
            doc.appendChild(raiz);//Añade la raiz al documento.

            agregarDatosDeInicioAlArchivo(raiz, archivo.getAbsolutePath());
            //Elemento tablas solo hay 1
            Element tablas = doc.createElement("tablas");
            tablas.setAttribute("cantidad", String.valueOf(lasTablas.size()));
            raiz.appendChild(doc.createComment("Tablas del proyecto"));
            raiz.appendChild(tablas);

            //Recorre las tablas para ir agregando una a una.
            lasTablas.stream().forEach((lasTabla) -> {
                agregarTablaAlXml(lasTabla, tablas);
            });

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            DOMSource source = new DOMSource(doc);
            //Salida a archivo
            BufferedOutputStream bos;

            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            bos = new BufferedOutputStream(new FileOutputStream(archivo));
            StreamResult result = new StreamResult(bos);
            //Guarda los datos en el archivo.
            transformer.transform(source, result);

            // Salida a la consola
            // StreamResult consoleResult = new StreamResult(System.out);
            //  transformer.transform(source, consoleResult);
        } catch (TransformerException | ParserConfigurationException | IOException e) {
            e.printStackTrace(System.err);
        }

    }

    /**
     * Añade la etiqueta nombre y un elemento de texto con el nombre del
     * proyecto
     *
     * @param raiz
     * @param nombreArchivo
     */
    private void agregarDatosDeInicioAlArchivo(Element raiz, String nombreArchivo) {

        Element nombre = doc.createElement("nombre");
        nombre.appendChild(doc.createTextNode(nombreArchivo));
        raiz.appendChild(nombre);

    }

    /**
     * Lee un archivoXML del proyecto, lo valida con su XSD, lo consulta con SAX y
 devuelve un arrayList con las tablas del archivoXML. <br>
     *
     * @param archivoXML
     * @return
     */
    public ArrayList<Tabla> leerArchivo(File archivoXML) {
        File elXsd = new File(Datos.RUTA_DEL_XSD);
        //Comprueba que el XSD exista, si no existe, lo crea.
        if ( ! elXsd.exists() || ! xsdCorrecto()) {
            crearElXSD();
        }
        if ( !validarXMLconXSD(archivoXML)) {
            throw new Error("El xml no es valido");
        }

        SAXParserFactory xapa;
        SAXParser ppp;
        try {
            xapa = SAXParserFactory.newInstance();
            ppp = xapa.newSAXParser();
            ppp.parse(archivoXML, saxCallbacks);
        } catch (ParserConfigurationException | SAXException | IOException  ex) {
            Logger.getLogger(GestorDeArchivosDelProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    /**
     * Añade la etiquetas de los datos de la tabla que se le pase.
     *
     * @param tabla EL objeto tabla que se añadirá
     * @param tablas La etiqueta tablas que contendrá a esta tabla
     *
     */
    private void agregarTablaAlXml(Tabla tabla, Element tablas) {

        //Aqui llega cada tabla del array
        Element ta = doc.createElement("tabla");

        ta.setAttribute("nombre", tabla.getNombre());
        //Añade la etiqueta tabla dentro de la etiqueta tablas.
        tablas.appendChild(ta);
        //Elemento columnas solo hay 1
        //Dentro de el se añaden todos los elementos columna
        Element columnas = doc.createElement("columnas");
        //Añade un atributo con la cantidad de columnas de la tabla.
        columnas.setAttribute("cantidad", String.valueOf(tabla.getColumnas().size()));
        columnas.appendChild(doc.createComment("Columnas de la tabla."));
        //Añade el elemento columnas dentro de elemento tabla
        ta.appendChild(columnas);
        //Recorre las columnas que contiene la tabla
        for (Columna column : tabla.getColumnas()) {
            //crea una etiqueta columna
            Element columna = doc.createElement("columna");
            //Crea en atributo con el numero de cada columna.
            columna.setAttribute("numero", column.getNumeroDeColumna().toString());
            //Establece los valores de la columna a los del objeto Columna column
            columna.appendChild(avdc("tipodedato", column.getTipoDeDato().toString()));
            columna.appendChild(avdc("claveprimaria", column.getPrimaryKey().toString()));
            columna.appendChild(avdc("claveforanea", column.getForeignKey().toString()));
            columna.appendChild(avdc("nulo", column.getNulo().toString()));
            columna.appendChild(avdc("mayoroigual", String.valueOf(column.getMayorOIgual())));
            if (column.getValorMaximo() == null) {
                columna.appendChild(avdc("valormaximo", "0"));
            } else {
                columna.appendChild(avdc("valormaximo", column.getValorMaximo().toString()));
            }

            columna.appendChild(avdc("tablaalaqueapunta", column.getTablaALaQueApunta()));
            //Añade cada etiqueta columna dentro de la etiqueta columnas.
            columnas.appendChild(columna);

        }
        //Añade la etiqueta columnas dentro de la etiqueta tabla
        ta.appendChild(columnas);
    }

    /**
     * Devuelve un Element que contiene: <br>
     * El nombre de la etiqueta pasado <br>
     * y un nodo texto con el texto que se le pase. <br>
     *
     * @param nombreDeEtiqueta nombre que tendrá la etiqueta en el DOM
     * @param textoDeLaEtiqueta Texto que aparecerá dentro del nodo
     * @return el objeto Element con el elemento de texto dentro
     */
    private Element avdc(String nombreDeEtiqueta, String textoDeLaEtiqueta) {
        Element e = doc.createElement(nombreDeEtiqueta);
        e.appendChild(doc.createTextNode(textoDeLaEtiqueta));
        return e;
    }

    /**
     * Obtiene la fecha en formato DD/MM/AÑO
     *
     * @return
     */
    private static String getFecha() {
        GregorianCalendar greca = new GregorianCalendar();
        int dia = greca.get(Calendar.DAY_OF_MONTH);
        int mes = greca.get(Calendar.MONTH) + 1;
        int año = greca.get(Calendar.YEAR);
        return dia + "/" + mes + "/" + año;
    }

    /**
     * Valida el xml que se le pasa con el esquema XSD el proyecto. 
     * @param elXml
     * @param elXsd
     * @return 
     */
    static boolean validarXMLconXSD(File elXml) {
        try {
            InputStream xml = new FileInputStream(elXml);
            InputStream xsd = new FileInputStream(new File(Datos.RUTA_DEL_XSD));
            SchemaFactory factory= SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
            return true;
        } catch (SAXException ex) {
            //System.err.println(ex.getMessage());
            return false;
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
            System.exit(1);
            return false;
        }
    }

    
    
    
    /**
     * Crea el archivo xsd en la ruta de Datos.RUTA_DEL_XSD. <br>
     * Cada vez que abre un archivo del proyecto para leer,
     * compara el archivo con su xsd, esto es por si el XSD no existe por lo que sea. <br>
     */
    private void crearElXSD() {
        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(Datos.RUTA_DEL_XSD)))) {
                bw.write(Datos.contenidoDelXSD());
            }
        } catch (IOException ex) {
            Logger.getLogger(GestorDeArchivosDelProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
            


    }

    /**
     * Devuelve true si el contenido del archivo XSD 
     * de Datos.RUTA_DEL_XSD  es igual que el String que devuelve
     *  el metodo Datos.contenidoDelXSD(). <br>
     * Si es true es que el archivo esta bien.
     * @return 
     */
    private boolean xsdCorrecto(){
            boolean salida=false;
        try {
            //Guarda el contenido del archivo en un String
            String text = new String(Files.readAllBytes(Paths.get(Datos.RUTA_DEL_XSD)), StandardCharsets.UTF_8);
            if(text.equals(Datos.contenidoDelXSD())){//Los compara y si son iguales devueve true.
                salida=true;
            }
        } catch (IOException ex) {
            Logger.getLogger(GestorDeArchivosDelProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salida;
    }
    /**
     * Main para probar esto.
     *
     * @param args
     */
    public static void main(String[] args) {

//        ArrayList<Tabla> tablass = new ArrayList<>();
//
//        Tabla tt = new Tabla("Otra");
//        tt.añadirColumna(new Columna(tt, 1));
//        for (int i = 0; i < 1; i++) {
//            tt.añadirColumna(new Columna(tt, 2));
//        }
//        tablass.add(tt);
//
//        Tabla tt2 = new Tabla("Tablabla");
//        tt2.añadirColumna(new Columna(tt, 3));
//
//        tt2.añadirColumna(new Columna(tt, 4));
//        tablass.add(tt2);
//
//        Datos.guardarArchivoDelProyecto();
        GestorDeArchivosDelProyecto gepo = new GestorDeArchivosDelProyecto();
        File fi = new File("C:\\Users\\pedruben\\Desktop\\Hola.criser");
        gepo.leerArchivo(fi);

    }

}
