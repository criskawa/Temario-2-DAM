
import java.io.File; 
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.transform.OutputKeys;
import org.exist.security.User;
import org.exist.storage.DBBroker;
import org.exist.util.UTF8;
import org.exist.xmldb.UserManagementService;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;

public class LibrosXML {

    protected static String driver = "org.exist.xmldb.DatabaseImpl";
    public static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private Database database;
    private Collection col;
    private String usuario;
    private String usuarioPwd;
    private String collection;
    private String nombre_recurso;

    /**
     * *****************************************************************
     * Constructor de la clase
     *
     * @throws ExcepcionGestorBD si existe algún problema en la conexión
     * ****************************************************************
     */
    public LibrosXML() throws ExcepcionGestorBD {
        this.database = null;
        this.usuario = "admin";
        this.usuarioPwd = "";
        collection = "/db/Libros/EnIngles";
        nombre_recurso = "Libros_Ingles.xml";
        //System.out.println("He conectado ...");
    }

    /**
     * *****************************************************************
     * Efectúa la conexión a la base de datos y muestra el contenido de un
     * recurso (documento) que recupera de una colección
     * ****************************************************************
     */
    public Collection conectarBD() throws ExcepcionGestorBD {
        try {
            System.out.println("Intento Conectar...");
            Class cl = Class.forName(driver);
            System.out.println("Conecta el driver...");
            //Se crea un objeto Database
            database = (Database) cl.newInstance();
            DatabaseManager.registerDatabase(database);
            System.out.println("Ahora obtiene la colección " + URI + collection);
            //Ahora se obtiene la colección  (URI + collection) 
            //con el usuario y password que tiene acceso a ella.
            col = DatabaseManager.getCollection(URI + collection, usuario, usuarioPwd);
            if (col.getResourceCount() == 0) {
                //si la collección no tiene recursos no podrá devolver ninguno
                System.out.println("La colección no tiene recursos..."
                        + "No puede devolver ninguno [FIN]");
                return null;

            } else {
                System.out.println("...La colección no es nula...");
                Resource res = null;
                res = (Resource) col.getResource(nombre_recurso);
                System.out.println("De la colección saca el recurso que tiene la "
                        + "variable nombre_recurso:" + nombre_recurso);
                XMLResource xmlres = (XMLResource) res;
                System.out.println("La salida es:\n" + xmlres.getContent());
                System.out.println("El tipo es:" + xmlres.getResourceType());
                return col;
            }
        } catch (ClassNotFoundException e) {
            throw new ExcepcionGestorBD("No se encuentra la clase del driver");
        } catch (InstantiationException e) {
            throw new ExcepcionGestorBD("Error instanciando el driver");
        } catch (IllegalAccessException e) {
            throw new ExcepcionGestorBD("Se ha producido una IllegalAccessException");
        } catch (XMLDBException e) {
            throw new ExcepcionGestorBD("error XMLDB :" + e.getMessage());
        }
    }

    /**
     * *****************************************************************
     * Asocia un nuevo recurso obtenido de un archivo
     * ****************************************************************
     */
    public void asignarRecursoBD(Collection contexto, File archivo)
            throws ExcepcionGestorBD {
        try {
            // Crea un recurso vacío
            Resource nuevoRecurso = contexto.createResource(archivo.getName(),
                    "XMLResource");
            // Asigna contenido del archivo al nuevo recurso vacío
            nuevoRecurso.setContent(archivo);
            // Almacena el recurso en la colección
            contexto.storeResource(nuevoRecurso);
        } catch (XMLDBException e) {
            throw new ExcepcionGestorBD("error XMLDB :" + e.getMessage());
        }
    }

    /**
     * *****************************************************************
     * Borra un recurso
     * ****************************************************************
     */
    public void borrarRecurso(Collection contexto, File archivo)
            throws ExcepcionGestorBD {
        try {  
            Resource res = (Resource) contexto.getResource(archivo.getName());
            contexto.removeResource(res);
        } catch (XMLDBException e) {
            throw new ExcepcionGestorBD("error XMLDB :" + e.getMessage());
        }
    }
}
