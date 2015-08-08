package insertsaleatorios;

import insertsaleatorios.gestiondearchivos.GestorDeArchivosDelProyecto;
import insertsaleatorios.modelo.Tabla;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

public class Datos {

    /**
     * Color elgido para el fondo de los paneles. <br>
     */
    public static final Color COLOR_DE_FONDO_DE_PANELES = new Color(20, 100, 170);
    /**
     * El array de tablas que se usa en cada momento. <br>
     * Es el que se usa para guardar el archivo. <br>
     *
     */
    public static ArrayList<Tabla> tablas = new ArrayList<>();
    /**
     * Nombre que tiene el programa, que aparecerá en el titulo de la ventana.
     * <br>
     */
    public static final String NOMBRE_DEL_PROGRAMA = "CREADOR DE INSERTS SQL";
    /**
     * Ruta de C:/Windows/temp. <br>
     *
     */
    public static final String RUTA_ARCHIVOS_TEMPORALES = System.getenv("TEMP") + File.separator;

    /**
     * Extension elegida para los archivos de proyecto del programa. <br>
     */
    public static final String EXTENSION_DE_ARCHIVO_DEL_PROYECTO = ".criser";
    /**
     * Carpeta por defecto del proyecto. <br>
     * Es cambiado si se cambia la ruta en la ventana de nuevo proyecto. <br>
     */
    public static String CARPETA_DEL_PROYECTO = "C:\\Users\\pedruben\\Desktop\\";
    /**
     * Se cambia al crear nuevo proyecto o al abrir alguno.
     */
    public static String nombreDelProyectoActual = "";

    public static final String RUTA_DEL_XSD = ".\\src\\creadordeinsertssql\\gestiondearchivos\\EsquemaDeArchivoDeProyecto.xsd";

    /**
     * Seleccion de idiomas, de momento solo se usa español. <br>
     */
    public static enum Idiomas {

        ESPAÑOL, INGLES
    }

    /**
     * Tipos de dato que podrá tener cada columna. <br>
     */
    public static enum TiposDeDato {

        nombre, apellido, DNI, calle, ciudad,
        entero, autoEntero, decimal, fecha, telefono, caracter
    }

    /**
     * Botones de la barra de menu
     */
    public static enum BarraDeMenuEnEspañol {

        Archivo, Editar, Opciones
    }

    public static enum BarraDeMenuEnInglesl {

        File, Edit, Options
    }

    /**
     * Botones del menu Archivo de la barra de menu. <br>
     */
    public static enum MenuDeArchivoEnEspañol {

        Nuevo, Abrir, Guardar, probarVistaColumnas, Separador, Salir
    }

    public static enum MenuDeArchivoEnIngles {

        New, NewProyect, OpenProyect, Separator, Exit,
    }

    /**
     * Devuelve un String array con el nombre
     * de cada tabla en cada elemento del array.
     * @return 
     */
    public static String[] nombresDeLasTablas() {
        String salida[] = new String[tablas.size()];
        int i = 0;
        for (Tabla tabla : tablas) {
            salida[i++] = tabla.getNombre();
        }
        return salida;
    }
    
    /**
     * Devuelve true si existe una tabla con el
     *  nombre que se le pase. <br>
     * @param nombre
     * @return 
     */
    public static boolean nombreDeTablaExiste(String nombre){
        boolean salida=false;
        for (Tabla tabla : tablas) {
            if(tabla.getNombre().equals(nombre)){
                salida=true;
                break;
            }
        }
        return salida;
    }

    /**
     * Guarda un archivo de proyecto con los datos que contenga las tablas en
     * ese momento. <br>
     * lo guarda en 'Datos.CARPETA_DEL_PROYECTO' <br>
     * con el nombre 'Datos.nombreDelProyectoActual' <br>
     * y con la extensión 'Datos.EXTENSION_DE_ARCHIVO_DEL_PROYECTO'
     */
    public static final void guardarArchivoDelProyecto() {
        GestorDeArchivosDelProyecto cac = new GestorDeArchivosDelProyecto();
        cac.crearArchivo(new File(Datos.CARPETA_DEL_PROYECTO
                + nombreDelProyectoActual + Datos.EXTENSION_DE_ARCHIVO_DEL_PROYECTO));

    }

    /**
     * Devuelve un String con el contenido que debe tener el archivo XSD Schema
     * para los XMLs del proyecto. <br>
     *
     * @return
     */
    public static String contenidoDelXSD() {
        String todo = "<?xml version=\"1.0\"?>\n"
                + "<!--\n"
                + "Dale moreno\n"
                + "-->\n"
                + "\n"
                + "<xs:schema version=\"1.0\"   xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"     \n"
                + "           elementFormDefault=\"qualified\">\n"
                + "\n"
                + "<!--  ELEMENTO RAIZ  contiene un elemento nombre y un elemento TABLAS-->\n"
                + "    <xs:element name=\"creadordeinserts\">\n"
                + "        <xs:complexType>\n"
                + "            <xs:sequence>\n"
                + "                <xs:element name=\"nombre\" type=\"xs:string\"/>                  \n"
                + "                <xs:element name=\"tablas\" type=\"tablas\"/>             \n"
                + "            </xs:sequence>            \n"
                + "        </xs:complexType>\n"
                + "    </xs:element>\n"
                + "    \n"
                + "<!--  ELEMENTO TABLAS contiene de 0 a muchos elementos TABLA -->\n"
                + "    <xs:complexType name=\"tablas\">\n"
                + "        <xs:choice minOccurs=\"0\" maxOccurs=\"unbounded\">\n"
                + "            <xs:element name=\"tabla\" type=\"tabla\"/>    \n"
                + "        </xs:choice>\n"
                + "        <xs:attribute name=\"cantidad\" use=\"required\"></xs:attribute>\n"
                + "    </xs:complexType>\n"
                + "    \n"
                + "<!--  ELEMENTO TABLA contiene un elemento COLUMNAS -->\n"
                + "    <xs:complexType name=\"tabla\">\n"
                + "        <xs:sequence>\n"
                + "            <xs:element name=\"columnas\" type=\"columnas\"/>\n"
                + "        </xs:sequence>            \n"
                + "        <xs:attribute name=\"nombre\" type=\"xs:string\" use=\"required\"/>\n"
                + "    </xs:complexType>\n"
                + "    \n"
                + "<!--  ELEMENTO COLUMNAS contiene de 0 a muchos elementos COLUMNA -->\n"
                + "    <xs:complexType name=\"columnas\">\n"
                + "        <xs:choice minOccurs=\"0\" maxOccurs=\"unbounded\">\n"
                + "            <xs:element name=\"columna\" type=\"columna\"/>\n"
                + "        </xs:choice>\n"
                + "        <xs:attribute name=\"cantidad\"  type=\"xs:string\" use=\"required\"/>\n"
                + "    </xs:complexType>\n"
                + "    \n"
                + "<!--  ELEMENTO COLUMNA contiene los datos de cada columna -->\n"
                + "    <xs:complexType name=\"columna\">\n"
                + "        <xs:sequence>\n"
                + "            <xs:element name=\"tipodedato\" type=\"xs:string\"/>\n"
                + "            <xs:element name=\"claveprimaria\" type=\"xs:boolean\"/>\n"
                + "            <xs:element name=\"claveforanea\" type=\"xs:boolean\"/>\n"
                + "            <xs:element name=\"nulo\" type=\"xs:boolean\"/>\n"
                + "            <xs:element name=\"mayoroigual\" type=\"xs:string\"/>\n"
                + "            <xs:element name=\"valormaximo\" type=\"xs:integer\"/>\n"
                + "            <xs:element name=\"tablaalaqueapunta\" type=\"xs:string\"/>              \n"
                + "        </xs:sequence>\n"
                + "        <xs:attribute name=\"numero\"  type=\"xs:integer\" use=\"required\"/> \n"
                + "    </xs:complexType>\n"
                + "        \n"
                + "</xs:schema>\n"
                + "";
        return todo;
    }

}
