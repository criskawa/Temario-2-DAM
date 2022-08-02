/*
 * Clase que accede a la bbdd
 */
package bibliotecaxml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.OutputKeys;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;

public class BibliotecaXND {

    private final Database database;
    private final String uri = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private final String user = "admin";
    private final String pass = "";
    private final String colecLibros = "/db/biblioteca/libros";

    public BibliotecaXND() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
        String driver = "org.exist.xmldb.DatabaseImpl";
        Class c1 = Class.forName(driver);
        database = (Database) c1.newInstance();
        DatabaseManager.registerDatabase(database);
    }

    public boolean insertarLibro(Libro miLibro) throws XMLDBException {
        if (!existeLibro(miLibro)) {
            String consulta = "update insert <Libro> <Titulo>" + miLibro.getTitulo() + "</Titulo>"
                    + "<Autor>" + miLibro.getAutor().getNombre() + "</Autor>"
                    + "<Npags>" + miLibro.getNpags() + "</Npags></Libro> into /Libros";
            ejecutarConsultaUpdate(colecLibros, consulta);
            return true;
        } else {
            return false;
        }
    }

    private boolean existeLibro(Libro l) throws XMLDBException {
        String consulta = "for $t in //Libros/Libro/Titulo where $t='" + l.getTitulo() + "' return $t";
        ResourceSet resultado = ejecutarConsultaXQuery(colecLibros, consulta);
        return resultado.getSize() > 0;
    }

    public void modificarLibro(Libro miLibro) throws XMLDBException {
        // Deberíamos verificar antes que el libro existe
        String consulta = "update replace /Libros/Libro[Titulo='" + miLibro.getTitulo() + "']/Autor "
                + "with <Autor>" + miLibro.getAutor().getNombre() + "</Autor>";
        ejecutarConsultaUpdate(colecLibros, consulta);
        consulta = "update replace /Libros/Libro[Titulo='" + miLibro.getTitulo() + "']/Npags "
                + "with <Npags>" + miLibro.getNpags() + "</Npags>";
        ejecutarConsultaUpdate(colecLibros, consulta);
    }

    // Función que devuelve los datos de todos los libros con el nombre del autor
    public List<Libro> selectAllLibros() throws XMLDBException {
        String consulta = "for $l in //Libros/Libro return $l";
        ResourceSet resultado = ejecutarConsultaXQuery(colecLibros, consulta);
        ResourceIterator iterador = resultado.getIterator();
        List<Libro> todosLosLibros = new ArrayList<>();
        while (iterador.hasMoreResources()) {
            XMLResource res = (XMLResource) iterador.nextResource();
            // Tenemos que leer el resultado como un DOM
            Node nodo = res.getContentAsDOM();
            // Leemos la lista de hijos que son tipo Libro
            NodeList hijo = nodo.getChildNodes();
            // Leemos los hijos del Libro
            NodeList datosLibro = hijo.item(0).getChildNodes();
            Libro l = leerDomLibro(datosLibro);
            todosLosLibros.add(l);
        }
        return todosLosLibros;
    }

    // Método para seleccionar libros por autor
    public List<Libro> selectLibrosByAutor(Autor a) throws XMLDBException {
        List<Libro> libros = new ArrayList<>();
        String consulta = "for $l in //Libros/Libro let $autor := $l/Autor where $autor='"
                + a.getNombre() + "'  return $l";
        ResourceSet resultado = ejecutarConsultaXQuery(colecLibros, consulta);
        ResourceIterator iterador = resultado.getIterator();
        while (iterador.hasMoreResources()) {
            XMLResource res = (XMLResource) iterador.nextResource();
            // Tenemos que leer el resultado como un DOM
            Node nodo = res.getContentAsDOM();
            // Leemos la lista de hijos que son tipo Libro
            NodeList hijo = nodo.getChildNodes();
            // Leemos los hijos del Libro
            NodeList datosLibro = hijo.item(0).getChildNodes();
            Libro l = leerDomLibro(datosLibro);
            libros.add(l);
        }

        return libros;
    }

    // Método para seleccionar libros por autor
    public List<Libro> selectLibrosByAutorNumPags(Autor a, int numPags) throws XMLDBException {
        List<Libro> libros = new ArrayList<>();
        String consulta = "for $l in //Libros/Libro let $autor := $l/Autor let $pags := $l/Npags "
                + "where $autor='" + a.getNombre() + "' and $pags =" + numPags + " return $l";
        ResourceSet resultado = ejecutarConsultaXQuery(colecLibros, consulta);
        ResourceIterator iterador = resultado.getIterator();
        while (iterador.hasMoreResources()) {
            XMLResource res = (XMLResource) iterador.nextResource();
            // Tenemos que leer el resultado como un DOM
            Node nodo = res.getContentAsDOM();
            // Leemos la lista de hijos que son tipo Libro
            NodeList hijo = nodo.getChildNodes();
            // Leemos los hijos del Libro
            NodeList datosLibro = hijo.item(0).getChildNodes();
            Libro l = leerDomLibro(datosLibro);
            libros.add(l);
        }

        return libros;
    }

    // Método auxiliar que lee los datos de un Libro
    private Libro leerDomLibro(NodeList datos) {
        int contador = 1;
        Libro l = new Libro();
        for (int i = 0; i < datos.getLength(); i++) {
            Node ntemp = datos.item(i);
            if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                switch (contador) {
                    case 1:
                        l.setTitulo(ntemp.getChildNodes().item(0).getNodeValue());
                        contador++;
                        break;
                    case 2:
                        Autor a = new Autor(ntemp.getChildNodes().item(0).getNodeValue());
                        l.setAutor(a);
                        contador++;
                        break;
                    case 3:
                        l.setNpags(Integer.parseInt(ntemp.getChildNodes().item(0).getNodeValue()));
                        contador++;
                        break;
                    default:
                        break;
                }
            }
        }
        return l;
    }

    // Función interna para ejecutar consultas de tipo update
    private void ejecutarConsultaUpdate(String coleccion, String consulta) throws XMLDBException {
        XQueryService servicio = prepararConsulta(coleccion);
        CompiledExpression consultaCompilada = servicio.compile(consulta);
        servicio.execute(consultaCompilada);
    }

    private XQueryService prepararConsulta(String coleccion) throws XMLDBException {
        Collection col = DatabaseManager.getCollection(uri + coleccion, user, pass);
        XQueryService servicio = (XQueryService) col.getService("XQueryService", "1.0");
        servicio.setProperty(OutputKeys.INDENT, "yes");
        servicio.setProperty(OutputKeys.ENCODING, "UTF-8");
        return servicio;
    }

    // Función interna para ejecutar consultas XQuery
    private ResourceSet ejecutarConsultaXQuery(String coleccion, String consulta) throws XMLDBException {
        XQueryService servicio = prepararConsulta(coleccion);
        ResourceSet resultado = servicio.query(consulta);
        return resultado;
    }

}
