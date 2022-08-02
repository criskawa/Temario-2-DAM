/****************************************************************
* 
* ESTE ES UN CÓDIGO DE EJEMPLO RÁPIDO PARA VER CÓMO FUNCIONA LA CONEXIÓN
* Y EL ACCESO A UN RECURSO EN eXist. RECORDAR QUE DEBEN ESTAR LOS DRIVERS 
* INSTALADOS Y LA BASE DE DATOS CREADA PARA PODER FUNCIONAR
* 
* */

import java.io.File;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;


public class Prueba2 {

    public final static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";

    public static void main(String args[]) throws Exception {
        System.out.println("Empiezo...");
        Collection col;
        LibrosXML libros = new LibrosXML();
        
        //Conecta a la base de datos y muestra el contenido de un recurso (definido en las variables de LibroXML)
        col=libros.conectarBD(); 
        System.out.println("-----------------------------------------\n");
        
        //USADA PARA: Asociar un nuevo recurso obtenido de un archivo
//        File archivo = new File ("Libros_Favoritos.xml");
//        libros.asignarRecursoBD(col, archivo);
//        System.out.println("-----------------------------------------\n");
        
        //USADA PARA: Borrar un recurso
        File archivo = new File ("Libros_Favoritos.xml");
        libros.borrarRecurso(col, archivo);
        System.out.println("-----------------------------------------\n");
    }
}