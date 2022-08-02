/*
 * Ejemplo con sistema gestor XML nativo (eXist)
 */
package bibliotecaxml;

import java.util.List;
import org.xmldb.api.base.XMLDBException;

public class BibliotecaXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
            // Creamos el gestor y "establecemos conexión"
            BibliotecaXND gestor = new BibliotecaXND();
            System.out.println("Conexión establecida.");
            Autor a = new Autor("Michael Ende", "EEUU");
            Libro l = new Libro("Juan sin miedo", a, 320);
            if (gestor.insertarLibro(l)) {
                System.out.println("Libro insertado");
            } else {
                System.out.println("Ya existe el libro");
            }
            System.out.println("-----------------------");
            List<Libro> libros = gestor.selectAllLibros();
            for (Libro libro : libros) {
                System.out.println(libro);
            }
            libros = gestor.selectLibrosByAutor(a);
            System.out.println("-----------------------");
            System.out.println("Consulta de libros por autor");
            for (Libro libro : libros) {
                System.out.println(libro);
            }
            System.out.println("-----------------------");
            System.out.println("Libros por autor y num pags");
            libros = gestor.selectLibrosByAutorNumPags(a, 320);
            System.out.println("-----------------------");
            System.out.println("Consulta de libros por autor");
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | XMLDBException ex) {
            System.out.println("Error con la BBDD: " + ex.getMessage());
        }
    }

}
