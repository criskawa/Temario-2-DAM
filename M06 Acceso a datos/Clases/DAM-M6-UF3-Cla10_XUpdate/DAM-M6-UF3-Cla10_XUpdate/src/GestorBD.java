
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XUpdateQueryService;

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
     * ****************************************************************
     */
    public GestorBD() throws ExcepcionGestorBD {
        this.database = null;
        this.usuario = "admin";
        this.usuarioPwd = "";
        conectarBD();
    }

    /**
     * *****************************************************************
     * Efectúa la conexión con el SGBD
     * ****************************************************************
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

    public boolean ejecutarInsertAfter() {
        boolean result = false;
        try {
            String query = "<xupdate:modifications version='1.0' "
                    + "xmlns:xupdate=\"http://www.xmldb.org/xupdate\">"
                    + "<xupdate:insert-after select=\"/Libros/Libro/Autor[.='Nikolai Gogol']\">"
                    + "<xupdate:element name='nacionalidad'>Rusa</xupdate:element>"
                    + "</xupdate:insert-after>"
                    + "</xupdate:modifications>";
            this.ejecutarXUpdate(query, "/db/Libros/EnCastellano");
            result = true;
        } catch (ExcepcionGestorBD e) {
            System.out.println("Error en carga inicial\n" + e.getMessage());
        }
        return result;
    }
    
    public boolean ejecutarInsertBefore() {
        boolean result = false;
        try {
            String query = "<xupdate:modifications version='1.0' "
                    + "xmlns:xupdate=\"http://www.xmldb.org/xupdate\">"
                    + "<xupdate:insert-before select=\"/Libros/Libro[Titulo='El Sanador de Caballos']/Autor\">"
                    + "<xupdate:element name='nacionalidad'>Española</xupdate:element>"
                    + "</xupdate:insert-before>"
                    + "</xupdate:modifications>";
            this.ejecutarXUpdate(query, "/db/Libros/EnCastellano");
            result = true;
        } catch (ExcepcionGestorBD e) {
            System.out.println("Error en carga inicial\n" + e.getMessage());
        }
        return result;
    }
    
    public boolean ejecutarUpdate() {
        boolean result = false;
        try {
            String query = "<xupdate:modifications version='1.0' "
                    + "xmlns:xupdate=\"http://www.xmldb.org/xupdate\">"
                    + "<xupdate:update "
                    + "select=\"/Libros/Libro[Titulo='El Sanador de Caballos']/Autor\">"
                    + "Gonzalo S. Giner"
                    + "</xupdate:update>"
                    + "</xupdate:modifications>";
            this.ejecutarXUpdate(query, "/db/Libros/EnCastellano");
            result = true;
        } catch (ExcepcionGestorBD e) {
            System.out.println("Error en carga inicial\n" + e.getMessage());
        }
        return result;
    }
    
    public boolean ejecutarAppend() {
        boolean result = false;
        try {
            String query = "<xupdate:modifications version='1.0' "
                    + "xmlns:xupdate=\"http://www.xmldb.org/xupdate\">"
                    + "<xupdate:append "
                    + "select=\"/Libros\" child=\"last()\">"
                    + "<xupdate:element name='Libro'>"
                    + "<Autor>AAA</Autor>"
                    + "<Titulo>TTT</Titulo>"
                    + "</xupdate:element>"
                    + "</xupdate:append>"
                    + "</xupdate:modifications>";
            this.ejecutarXUpdate(query, "/db/Libros/EnCastellano");
            result = true;
        } catch (ExcepcionGestorBD e) {
            System.out.println("Error en carga inicial\n" + e.getMessage());
        }
        return result;
    }
    
    public boolean ejecutarRemove() {
        boolean result = false;
        try {
            String query = "<xupdate:modifications version='1.0' "
                    + "xmlns:xupdate=\"http://www.xmldb.org/xupdate\">"
                    + "<xupdate:remove "
                    + "select=\"/Libros/Libro[Titulo='TTT']\">"
                    + "</xupdate:remove>"
                    + "</xupdate:modifications>";
            this.ejecutarXUpdate(query, "/db/Libros/EnCastellano");
            result = true;
        } catch (ExcepcionGestorBD e) {
            System.out.println("Error en carga inicial\n" + e.getMessage());
        }
        return result;
    }
    
    public boolean ejecutarRename() {
        boolean result = false;
        try {
            String query = "<xupdate:modifications version='1.0' "
                    + "xmlns:xupdate=\"http://www.xmldb.org/xupdate\">"
                    + "<xupdate:rename "
                    + "select=\"/Libros/Libro/Autor\">Escritor"
                    + "</xupdate:rename>"
                    + "</xupdate:modifications>";
            this.ejecutarXUpdate(query, "/db/Libros/EnCastellano");
            result = true;
        } catch (ExcepcionGestorBD e) {
            System.out.println("Error en carga inicial\n" + e.getMessage());
        }
        return result;
    }
}
