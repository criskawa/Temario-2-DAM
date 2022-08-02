
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.transform.OutputKeys;
import org.exist.security.User;
import org.exist.storage.DBBroker;
import org.exist.util.UTF8;
//import org.exist.xmldb.CollectionImpl;
import org.exist.xmldb.UserManagementService;
//import org.exist.xmldb.XQueryService;
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
        collection = "/db/Linkia";
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

    /**
     * *****************************************************************
     * Obtiene un árbol con la estructura de componentes de la coleccion
     *
     * @param collec Colección a mostrar el contenido, si es null devolvera la
     * colección raiz
     * @return Arbol con la estructura de componentes de la coleccion
     * @throws ExcepcionGestorBD Si ocurre algún error en el proceso
     * ****************************************************************
     */
    public ArrayList obtenerEstructuraColeccion(String collec, ArrayList<ElementoBD> colElementos)
            throws ExcepcionGestorBD {
        Collection col;
        ElementoBD newElemento = new ElementoBD("", 0, "");
        String nombrecol;
        String[] listaColecciones;
        String[] listaRecursos;
        //DefaultMutableTreeNode contenido;
        try {
            if (collec == null) {
                nombrecol = new String("/db");//Obtienen el nombre de la colección raíz.
            } else {
                nombrecol = new String(collec);//Coge el nombre de la colección que se le ha pasado.
            }
            col = (Collection) leerColeccion(nombrecol);
            listaColecciones = col.listChildCollections();
            for (int i = 0; i < listaColecciones.length; i++) {
                obtenerEstructuraColeccion(nombrecol + "/" + listaColecciones[i], colElementos);
            }
            listaRecursos = col.listResources();
            for (int i = 0; i < listaRecursos.length; i++) {
                int tiporec;
                Resource res = leerRecurso(col, listaRecursos[i]);
                if (res.getResourceType().equals("XMLResource")) {
                    tiporec = ElementoBD.RECURSO_XML;
                } else {
                    tiporec = ElementoBD.RECURSO_BINARIO;
                }
                newElemento
                        = new ElementoBD(listaRecursos[i],
                                tiporec,
                                nombrecol);
                colElementos.add(newElemento);
            }
            return colElementos;
        } catch (XMLDBException e) {
            throw new ExcepcionGestorBD(
                    "error generando estructura bd: "
                    + e.getMessage());
        }
    }

    /**
     * *****************************************************************
     * Muestra un árbol con la estructura de componentes de la coleccion
     *
     */
    public void mostrarEstructuraColeccion(ArrayList<ElementoBD> colElementos) {
        String nomCol, nomActual, nomRec;
        Iterator<ElementoBD> iter = colElementos.iterator();
        nomActual = "";
        while (iter.hasNext()) {
            ElementoBD elemento = iter.next();
            nomCol = elemento.getColeccion();
            if (!nomCol.equals(nomActual)) {
                System.out.println(nomCol + "\n");
                nomActual = nomCol;
            }
            nomRec = elemento.getNombre();
            System.out.println("\t" + nomRec + "\n");
        }
    }

    /**
     * *****************************************************************
     * Lee una colección de la base de datos
     *
     * @param colec Ruta de la coleccion a leer
     * @return Coleccion leida
     * @throws ExcepcionGestorBD si existe algun problema
     * ****************************************************************
     */
    public Collection leerColeccion(String colec)
            throws ExcepcionGestorBD {
        //System.out.println(colec);
        Collection colRet = null;
        try {
            colRet = DatabaseManager.getCollection(URI + colec, usuario, usuarioPwd);
        } catch (XMLDBException e) {
            throw new ExcepcionGestorBD(
                    "Error leyendo coleccion\n" + e.getMessage());
        }
        return colRet;
    }

    /**
     * *****************************************************************
     * Lee un recurso de una colección de la base de datos
     *
     * @param colec Coleccion de la que leeremos
     * @param nombrerec Recurso a leer de la coleccion
     * @return Resource Recurso leido, null en caso contrario
     * ***************************************************************
     */
    public Resource leerRecurso(Collection colec, String nombrerec) {
        Resource res = null;
        try {
            //System.out.println("\t " + nombrerec);
            res = (Resource) colec.getResource(nombrerec);
        } catch (XMLDBException e) {
            res = null;
        }
        return res;
    }

    /**
     * ***************************************************************
     * Añade una nueva coleccion a la BB.DD
     *
     * @param contexto Coleccion sobre la que insertaremos la nueva
     * @param newColec Nombre de la nueva coleccion a insertar (relativa a la
     * coleccion de contexto)
     * @return Collection Nueva colección creada
     * @throws ExcepcionGestorBD Si existe un error en la inserción
     * **************************************************************
     */
    public Collection anadirColeccion(Collection contexto,
            String newColec) throws ExcepcionGestorBD {
        Collection newCollection = null;
        try {
            //Se crea un nuevo servicio desde el contexto
            CollectionManagementService mgtService
                    = (CollectionManagementService) contexto.getService(
                            "CollectionManagementService", "1.0");
            //Se crea una nueva colección con el nombre newColec codificado UTF8.
            //La colección nueva se devuelve en newCollecion(Collection)
            newCollection = mgtService.createCollection(
                    new String(UTF8.encode(newColec)));
        } catch (XMLDBException e) {
            throw new ExcepcionGestorBD("Error añadiendo colección: " + e.getMessage());
        }
        return newCollection;
    }

    /**
     * *************************************************************
     * Borra una nueva coleccion a la BB.DD
     *
     * @param contexto Coleccion Padre que tiene la colección que se desea
     * borrar.
     * @param antColecc Nombre de la colección a eliminar (relativa a la
     * coleccion de contexto)
     * @throws ExcepcionGestorBD Si existe un error en la inserción
     * ************************************************************
     */
    public void borrarColeccion(Collection contexto,
            String antColecc) throws ExcepcionGestorBD {
        try {
            CollectionManagementService mgtService
                    = (CollectionManagementService) contexto.getService(
                            "CollectionManagementService", "1.0");
            mgtService.removeCollection(antColecc);
        } catch (XMLDBException e) {
            throw new ExcepcionGestorBD(
                    "Error eliminando colección: " + e.getMessage());
        }
    }
}
