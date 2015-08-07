package creadordeinsertssql.modelo;

import creadordeinsertssql.Datos;

/**
 * Representa una columna con todos los datos necesarios. <br>
 * Esta clase solo contiene los datos y varios constructors para crear un
 * objeto de esta clase. <br>
 * Hay que pasarle el objeto tabla al que pertenece. <br>
 * Tambien hay que pasarle el numero de columna que tendrá
 *
 * @author Ruben
 */
public class Columna {

    private final Tabla tablaDeEstaColumna;
   
    /**
     * Numero propio de cada objeto. <br>
     * Es asignado desde la static autoNumero. <br>
     */
    private final Integer numeroDeColumna;
    /**
     * Tipo de dato de esta columna. <br>
     */
    private Datos.TiposDeDato tipoDeDato;
    /**
     * Signo para cuando es un numero
     * que no supere un cierto valor
     */
    private Character mayorOIgual;
    private Boolean primaryKey;
    private Boolean foreignKey;
    private Boolean Nulo;
    /**
     * Valor maximo cuando es un numero. <br>
     */
    private Integer valorMaximo;
    private String tablaALaQueApunta;

    public Boolean getPrimaryKey() {
        return primaryKey;
    }

    public Boolean getForeignKey() {
        return foreignKey;
    }

    public Boolean getNulo() {
        return Nulo;
    }

    
    
    public void setTipoDeDato(Datos.TiposDeDato tipoDeDato) {
        this.tipoDeDato = tipoDeDato;
    }

    public void setMayorOIgual(Character mayorOIgual) {
        this.mayorOIgual = mayorOIgual;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setForeignKey(Boolean foreignKey) {
        this.foreignKey = foreignKey;
    }

    public void setNulo(Boolean Nulo) {
        this.Nulo = Nulo;
    }

    public void setValorMaximo(Integer valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public void setTablaALaQueApunta(String tablaALaQueApunta) {
        this.tablaALaQueApunta = tablaALaQueApunta;
    }

    public Integer getNumeroDeColumna() {
        return numeroDeColumna;
    }

    public Datos.TiposDeDato getTipoDeDato() {
        return tipoDeDato;
    }

    public Character getMayorOIgual() {
        return mayorOIgual;
    }

    public Boolean isPrimaryKey() {
        return primaryKey;
    }

    public Boolean isForeignKey() {
        return foreignKey;
    }

    public Boolean isNulo() {
        return Nulo;
    }

    public Integer getValorMaximo() {
        return valorMaximo;
    }

    public String getTablaALaQueApunta() {
        return tablaALaQueApunta;
    }

    /**
     * Constructor simple. <br>
     * @param tabla que contendrá a esta columna.
     * @param numeroDeColumna numero de columna que tendrá
     */  
    public Columna(Tabla tabla,int numeroDeColumna) {
        this.tablaDeEstaColumna=tabla;
        this.numeroDeColumna = numeroDeColumna;
        this.tipoDeDato = Datos.TiposDeDato.nombre;
        this.primaryKey = false;
        this.mayorOIgual = '0';
        this.foreignKey = false;
        this.Nulo = false;
        this.valorMaximo = 0;
        this.tablaALaQueApunta = "";
    }
/**
 * Constructor para pasarle todos los datos. <br>
 * @param tabla que contendrá a esta columna.
 * @param numeroDeColumna  numero de columna que tendrá
 * @param tipoDeDato El tipo de dato de esta columna. es un enum de la clase Datos.
 * @param mayorOIgual
 * @param primaryKey Es o no clave primaria de la tabla
 * @param foreignKey Es o no clave foranea
 * @param Nulo Puede o no ser nulo.
 * @param valorMaximo Si el tipo de dato es un numero, se establece el tamaño maximo
 * @param tablaALaQueApunta Si clave foranea es true, aqui esta el nombre de la tabla a la que apunta.
 */
    public Columna(Tabla tabla,int numeroDeColumna,Datos.TiposDeDato tipoDeDato, Character mayorOIgual, boolean primaryKey, boolean foreignKey, boolean Nulo, Integer valorMaximo, String tablaALaQueApunta) {
          this.tablaDeEstaColumna=tabla;
        this.numeroDeColumna = numeroDeColumna;
        this.tipoDeDato = tipoDeDato;
        this.mayorOIgual = mayorOIgual;
        this.primaryKey = primaryKey;
        this.foreignKey = foreignKey;
        this.Nulo = Nulo;
        this.valorMaximo = valorMaximo;
        this.tablaALaQueApunta = tablaALaQueApunta;
    }

    /**
     * Comprueba si la columna tiene todos sus datos a null. <br>
     *
     * @return true Si todo es null.
     */
    public boolean noTieneDatos() {
        boolean salida = false;
        if (this.numeroDeColumna == null
                && this.tipoDeDato == null
                && this.primaryKey == null
                && this.mayorOIgual == null
                && this.foreignKey == null
                && this.Nulo == null
                && this.valorMaximo == null
                && this.tablaALaQueApunta == null) {
            salida = true;
        }
        return salida;
    }
    
    /**
     * Devuelve un String con los datos de esta columna. <br>
     * @return elString
     */
    @Override
    public String toString(){
        StringBuilder b=new StringBuilder();
        b.append("\nNumero de columna: ").append(numeroDeColumna).append("\n");
        b.append("Tipo de dato: ").append(this.tipoDeDato.toString()).append("\n");
        b.append("Primaria: ").append(this.primaryKey).append("\n");
        b.append("foranea: ").append(this.foreignKey).append("\n");
        b.append("nulo: ").append(this.Nulo).append("\n");
        return b.toString();
    }
}
