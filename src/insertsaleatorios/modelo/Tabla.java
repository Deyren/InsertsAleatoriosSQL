package insertsaleatorios.modelo;

import java.util.ArrayList;

/**
 * Representa una tabla con todo lo necesrio.
 * Tiene un array de Columnas.
 * @author pedruben
 */
public class Tabla {

    /**
     * Nombre de la tabla. Se le pasa al constructor
     */
    private final String nombre;
    
    private ArrayList<Columna> columnas;
    public String getNombre() {
        return nombre;
    }

    public ArrayList<Columna> getColumnas() {
        return columnas;
    }
    
    /**
     * Devuelve una tabla vacia, sin columnas.
     * @param nombre
     * @return 
     */
    public static Tabla getTablaVacia(String nombre){
        Tabla t=new Tabla(nombre);
        return t;
    }

    /**
     * Constructor
     * @param nombre 
     */
    public Tabla(String nombre) {
        this.nombre=nombre;      
        columnas=new ArrayList<>();
    }
    
    public void setColumnas(ArrayList<Columna> columnas){
        this.columnas=columnas;
    }
    
    public void añadirColumna(Columna columna) {        
        columnas.add(columna);
    }
    /**
     * Quita la ultima columna
     */
    public void quitarUltimaColumna() {        
        columnas.remove(columnas.size()-1);
    }

}
