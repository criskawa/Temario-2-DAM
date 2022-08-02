package librosxml;

import java.io.File;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.transform.OutputKeys;
import org.exist.security.User;
import org.exist.storage.DBBroker;
import org.exist.util.UTF8;
import org.exist.xmldb.CollectionImpl;
import org.exist.xmldb.UserManagementService;
import org.exist.xmldb.XQueryService;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

import org.xmldb.api.modules.*;

public class GestorBD {

    protected static String driver = "org.exist.xmldb.DatabaseImpl";
    public static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private Database database;
    private String usuario;
    private String usuarioPwd;

    /**
     * *****************************************************************
     * Constructor de la clase
     *
     * @throws ExcepcionGestorBD si existe algún problema en la conexión
     *****************************************************************
     */
    public GestorBD() throws ExcepcionGestorBD {
        this.database = null;
        this.usuario = "admin";
        this.usuarioPwd = "password";
        conectarBD();
    }

    /**
     * *****************************************************************
     * Efectúa la conexión con el SGBD
     *****************************************************************
     */
    private void conectarBD() throws ExcepcionGestorBD {
        try {
            Class cl = Class.forName(driver);
            database = (Database) cl.newInstance();
            DatabaseManager.registerDatabase(database);
        } catch (ClassNotFoundException e) {
            throw new ExcepcionGestorBD(
                    "No se encuentra la clase del driver");
        } catch (InstantiationException e) {
            throw new ExcepcionGestorBD(
                    "Error instanciando el driver");
        } catch (IllegalAccessException e) {
            throw new ExcepcionGestorBD(
                    "Se ha producido una IllegalAccessException");
        } catch (XMLDBException e) {
            throw new ExcepcionGestorBD(
                    "error XMLDB :" + e.getMessage());
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
     *****************************************************************
     */
    public DefaultMutableTreeNode obtenerEstructuraColeccion(String collec)
            throws ExcepcionGestorBD {
        CollectionImpl col;
        String nombrecol;
        String[] listaColecciones;
        String[] listaRecursos;
        DefaultMutableTreeNode contenido;
        try {
            if (collec == null) {
                nombrecol = new String("/db");//Obtienen el nombre de la colección raíz.
            } else {
                nombrecol = new String(collec);//Coge el nombre de la colección que se le ha pasado.
            }
            col = (CollectionImpl) leerColeccion(nombrecol);
            contenido
                    = new DefaultMutableTreeNode(new ElementoBD(nombrecol.substring(nombrecol.lastIndexOf("/") + 1), ElementoBD.COLECCION, nombrecol));
            //El nodo del TreeNode es un tipo ElementoBD.
            // es para que cuando del JTree se recupere un node se saque su información en forma de ElementoDB 
            listaColecciones = col.listChildCollections();
            for (int i = 0; i < listaColecciones.length; i++) {
                contenido.add(
                        obtenerEstructuraColeccion(nombrecol
                                + "/" + listaColecciones[i]));
            }
            listaRecursos = col.getResources();
            for (int i = 0; i < listaRecursos.length; i++) {
                int tiporec;
                Resource res = leerRecurso(col, listaRecursos[i]);
                if (res.getResourceType().equals("XMLResource")) {
                    tiporec = ElementoBD.RECURSO_XML;
                } else {
                    tiporec = ElementoBD.RECURSO_BINARIO;
                }
                ElementoBD newElemento
                        = new ElementoBD(listaRecursos[i],
                                tiporec,
                                nombrecol);
                contenido.add(new DefaultMutableTreeNode(newElemento));
            }
            return contenido;
        } catch (XMLDBException e) {
            throw new ExcepcionGestorBD(
                    "error generando estructura bd: "
                    + e.getMessage());
        }
    }

    /**
     * *****************************************************************
     * Lee una colección de la base de datos
     *
     * @param colec Ruta de la coleccion a leer
     * @return Coleccion leida
     * @throws ExcepcionGestorBD si existe algun problema
     *****************************************************************
     */
    public Collection leerColeccion(String colec)
            throws ExcepcionGestorBD {
        System.out.println("En leer recurso: nombre del recurso " + colec);
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
     ****************************************************************
     */
    public Resource leerRecurso(Collection colec, String nombrerec) {
        Resource res = null;
        try {
            System.out.println("En leer recurso: nombre del recurso " + nombrerec);
            res = (Resource) colec.getResource(nombrerec);
        } catch (XMLDBException e) {
            res = null;
        }
        return res;
    }

    /**
     * *****************************************************************
     * Lanza una consulta contra la base de datos
     *
     * @param consulta Consulta a ejecutar
     * @param contexto Coleccion de contexto de la consulta, si es null se
     * usarála colección raiz del SGBD
     * @return ResourceSet Resultado de la Query
     ******************************************************************
     */
    public ResourceSet ejecutarQuery(String consulta, String contexto)
            throws ExcepcionGestorBD {
        ResourceSet result = null;
        Collection col;
        try {
            if (contexto == null) {
                col = DatabaseManager.getCollection(URI + "/db");
                System.out.print(URI + "/db");
            } else {
                col = DatabaseManager.getCollection(URI + contexto);
            }

            XQueryService service
                    = (XQueryService) col.getService("XQueryService", "1.0");
            service.setProperty(OutputKeys.INDENT, "yes");
            service.setProperty(OutputKeys.ENCODING, "UTF-8");
            CompiledExpression compiled = service.compile(consulta);
            result = service.execute(compiled);
        // podria ser: result = service.query( consulta );
        } catch (XMLDBException e) {
            throw new ExcepcionGestorBD("Error ejecutando query: "
                    + e.getMessage());
        }
        return result;
    }

    /**
     * *****************************************************************
     * Lanzar una consulta XUpdate
     *
     * @param consulta sentencia XUpdate
     * @param contexto Coleccion de contexto de la consulta, si es null se
     * usarála colección raiz del SGBD
     *     
     ******************************************************************
     */
    public void ejecutarXUpdate(String consulta, String contexto) throws ExcepcionGestorBD {
        Collection col;
        try {
            if (contexto == null) {
                col = DatabaseManager.getCollection(URI + "/db");
                System.out.print(URI + "/db");
            } else {
                col = DatabaseManager.getCollection(URI + contexto);
            }
            XUpdateQueryService serviceXUpdate = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
            serviceXUpdate.setCollection(col);

            serviceXUpdate.update(consulta);

        } catch (XMLDBException e) {
            throw new ExcepcionGestorBD("Error ejecutando query: "
                    + e.getMessage());
        }

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
     ***************************************************************
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
     *************************************************************
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

    /**
     * ***************************************************************
     * Añad una nuevo recurso a la BB.DD
     *
     * @param contexto Coleccion sobre la que insertaremos el archivo
     * @param archivo Archivo a añadir como recurso
     * @param tipoRecurso Tipo del recurso a almacenar (binario ó XML
     * * @throws ExcepcionGestorBD Si existe un error en la inserción
     ***************************************************************
     */
    public void anadirRecurso(Collection contexto,
            File archivo,
            int tipoRecurso)
            throws ExcepcionGestorBD {
        try {
            String tipoRecursoStr = null;
            if (tipoRecurso == ElementoBD.RECURSO_BINARIO) {
                tipoRecursoStr = "BinaryResource";
            } else if (tipoRecurso == ElementoBD.RECURSO_XML) {
                tipoRecursoStr = "XMLResource";
            } else {
                throw new ExcepcionGestorBD("Error añadiendo colección: " + "tipo de recurso no valido");
            }
            Resource nuevoRecurso
                    = contexto.createResource(archivo.getName(), tipoRecursoStr);
            nuevoRecurso.setContent(archivo);
            contexto.storeResource(nuevoRecurso);
        } catch (XMLDBException e) {
            throw new ExcepcionGestorBD(
                    "Error añadiendo recurso: " + e.getMessage());
        }
    }

    /**
     * ***************************************************************
     * Borra un nuevo recurso a la BB.DD
     *
     * @param contexto Coleccion de la que borraremos un recurso
     * @param nombreRec Recurso a borrar
     * @throws ExcepcionGestorBD Si existe un error en el borrado
     ***************************************************************
     */
    public void borrarRecurso(Collection contexto, String nombreRec)
            throws ExcepcionGestorBD {
        try {
            Resource recursoParaBorrar
                    = leerRecurso(contexto, nombreRec);
            contexto.removeResource(recursoParaBorrar);
        } catch (XMLDBException e) {
            throw new ExcepcionGestorBD(
                    "Error añadiendo colección: " + e.getMessage());
        }
    }

    /**
     * ***************************************************************
     * Método de prueba de clase
     *
     * @param args
     ***************************************************************
     */
   /* public static void main(String[] args) {
        GestorBD gestor;
        try {
            gestor = new GestorBD();
            DefaultMutableTreeNode estructura
                    = gestor.obtenerEstructuraColeccion("/db");
            System.out.println(estructura);
            System.out.println("numero de hijos : "
                    + estructura.getChildCount());
        } catch (ExcepcionGestorBD e) {
            System.out.println("Error durante el proceso");
            System.out.println(e.getMessage());
        }
    }*/
}
