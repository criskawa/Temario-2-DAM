/**************************************************************
* Clase que representa un ítem que está almacenado en la BBDD,
* guardando los datos que nos son relevantes (nombre, path y tipo de
* recurso).
***************************************************************/
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
public class ElementoBD {
    public static final int OTRO = 0;
    public static final int COLECCION = 1;
    public static final int RECURSO_XML = 21;
    public static final int RECURSO_BINARIO = 22;
    private String nombre=null;
    private String coleccion=null;
    private int tipo;
    /**
    * Constructor parametrizado
    *
    * @param nombre Nombre del recurso
    * @param tipo Tipo del recurso (coleccion,recurso_xml,recurso_binario)
    * @param coleccion Coleccion donde esta almacenado
    */
    public ElementoBD(String nombre, int tipo, String coleccion) {
        super();
        this.nombre = nombre;
        this.coleccion = coleccion;
        this.tipo=tipo;
    }
    public String getNombre() {
        return nombre;
    }
    public String getPathCompleto() {
        return LibrosXML.URI + coleccion + "/" + nombre;
    }
    public String getColeccion() {
        return coleccion;
    }
    public int getTipo() {
        return tipo;
    }
    /**
    * Metodo de conversion a cadena
    * @return Nombre del elemento (normalizado)
    */
    public String toString(){
    try {
        //return URLDecoder.decode(nombre,"UTF-8");
        String cadena;
        cadena = this.getColeccion() + "/" + this.getNombre() + "\n";
        return cadena;
    } catch (Exception e) {
        return "--error normalizando nombre--";
    }
    }
}//De la clase
