/*
java -Djava.library.path="/Volumes/Dades/linkiaFP-MAC/DAM-M06/Clase07/DAM-M6-UF2-Cla07_matisse copia/matisseJava.dll" pruebas
*/

import biblioteca.*;
import com.matisse.MtDatabase;
import com.matisse.MtPackageObjectFactory;
import com.matisse.MtException;
import com.matisse.MtObjectIterator;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class GestionDB {
    //La clase main invoca a diferentes métodos que prueba la insercción, borrado, modificación y consulta
    //de una base de datos Matisse con Java.

    //RECUERDA: Para que funcionen los ejemplos hay que tener:
    //   *Instaladas en NetBeans las librerias necesarias para aceso a Matisse 
    //  * la base de datos MAtisse creada y ejecutándose LINKIA con namespace biblioteca,
    //  * Tener creadas en la base de datos LINKIA las clases indicadas.
    public static void main(String[] args) {

        String hostname = "127.0.0.1";
        String dbname = "linkia";

        //USADO PARA: Crear un objeto en la base de datos
        creaObjetos(hostname, dbname);
        //USADO PARA: Borrar un objeto de la base de datos
        //borraObjetos(hostname, dbname);
        //USADO PARA: Eliminar un objeto de la base de datos
        //ModificaObjeto(hostname, dbname, "Haruki", "301");
        //USADO PARA: Ejecutar una consulta OQL de Matisse.
        ejecturaOQL(hostname, dbname);
    }

    //Crea un objeto en la base de datos.
    public static void creaObjetos(String hostname, String dbname) {
        try {
            //Abre la base de datos con el Hostname (localhost), dbname (LINKIA) y es namespace "biblioteca".
            MtDatabase db = new MtDatabase(hostname, dbname, new MtPackageObjectFactory("", "biblioteca"));

            db.open();
            db.startTransaction();

            // Crea un objeto Autor
            Autor a1 = new Autor(db);
            a1.setNombre("Haruki");
            a1.setApellidos("Murakami");
            a1.setEdad("53");
            System.out.println("Autor creado...");
            
            // Crea un objeto Autor
            Autor a2 = new Autor(db);
            a2.setNombre("Pepe");
            a2.setApellidos("Sanchez");
            a2.setEdad("25");
            System.out.println("Autor creado...");
            
            // Crea un objeto Libro
            Libro l1 = new Libro(db);
            l1.setTitulo("Baila Baila Baila");
            l1.setEditorial("TusQuests");
            l1.setPaginas(512);
            // Crea otro objeto Libro
            Libro l2 = new Libro(db);
            l2.setTitulo("Tokio Blues");
            l2.setEditorial("TusQuests");
            l2.setPaginas(498);
            System.out.println("Libros creados...");
            
            //Crea un array de Obras para guardar los libros y hacer las relaciones
            Obra o1[] = new Obra[2];
            o1[0] = l1;
            o1[1] = l2;
            //Guarda las relaciones del autor con los libros que ha escrito.
            a2.setEscribe(o1);
            //Ejecuta un commit para materializar las peticiones.
            db.commit();
            //Cierra la base de datos.
            db.close();
            System.out.println("\nHecho.");
        } catch (MtException mte) {
            System.out.println("MtException : " + mte.getMessage());
        }
    }

    /**
     * Borra un objeto
     */
    public static void borraObjetos(String hostname, String dbname) {
        System.out.println("=========== Borra objetos ==========\n");

        try {
            MtDatabase db = new MtDatabase(hostname, dbname, new MtPackageObjectFactory("", "biblioteca"));

            db.open();
            db.startTransaction();

            // Lista cuántos objetos Obra con el método getInstanceNumber
            System.out.println("\n" + Obra.getInstanceNumber(db)
                    + " Obra(s) en la DB.");
            //Crea un Iterador (propio de Java)
            MtObjectIterator<Obra> iter = Obra.<Obra>instanceIterator(db);

            System.out.println("Borra dos Obras");
            while (iter.hasNext()) {
                Obra[] obras = iter.next(2);
                System.out.println("Borrando " + obras.length + " Obra(s)...");
                for (int i = 0; i < obras.length; i++) {
                    //borra definitivamente el objeto 
                    obras[i].deepRemove();
                }
                // Solo borra dos y lo deja
                break;
            }
            iter.close();
            //materializa los cambios y cierra la BD
            db.commit();
            db.close();
            System.out.println("\nHEcho.");
        } catch (MtException mte) {
            System.out.println("MtException : " + mte.getMessage());
        }
    }

    public static void ModificaObjeto(String hostname, String dbname, String nombre, String nuevaEdad) {
        System.out.println("=========== Modifica un objeto ==========\n");
        int nAutores = 0;
        try {
            MtDatabase db = new MtDatabase(hostname, dbname, new MtPackageObjectFactory("", "biblioteca"));

            db.open();
            db.startTransaction();

            // Lista cuántos objetos Autor con el método getInstanceNumber
            System.out.println("\n" + Autor.getInstanceNumber(db)
                    + " Autores en la DB.");
            nAutores = (int) Autor.getInstanceNumber(db);
            //Crea un Iterador (propio de Java)
            MtObjectIterator<Autor> iter = Autor.<Autor>instanceIterator(db);

            System.out.println("recorro el iterador de uno en uno y cambio cuando encuentro 'nombre'");
            while (iter.hasNext()) {
                Autor[] autores = iter.next(nAutores);
                for (int i = 0; i < autores.length; i++) {
                    //Busca una autor con nombre 'nombre' 
                    if (autores[i].getNombre().compareTo(nombre) == 0) {
                        autores[i].setEdad(nuevaEdad);
                    }
                }
            }
            iter.close();
            //materializa los cambios y cierra la BD
            db.commit();
            db.close();
            System.out.println("\nHEcho.");
        } catch (MtException mte) {
            System.out.println("MtException : " + mte.getMessage());
        }
    }

    /**
     * Borra todos los objetos
     */
    public static void borrarTodos(String hostname, String dbname) {
        System.out.println("=========== Borrar Todos ==========\n");

        try {
            MtDatabase db = new MtDatabase(hostname, dbname, new MtPackageObjectFactory("", "biblioteca"));

            db.open();
            db.startTransaction();

            // Listar cuántas obras  hay en la base de datos 
            System.out.println("\n" + Obra.getInstanceNumber(db)
                    + " Obras(s) en la DB.");

            System.out.println("Borrando todas las obras...");
            // Borra todas las instancias de Obra
            Obra.getClass(db).removeAllInstances();

            db.commit();
            db.close();
            System.out.println("\nHecho.");
        } catch (MtException mte) {
            System.out.println("MtException : " + mte.getMessage());
        }
    }

    public static void ejecturaOQL(String hostname, String dbname) {
        MtDatabase dbcon = new MtDatabase(hostname, dbname);
        //Abre una conexión a la base de datos 
        dbcon.open();
        try {
            // Crea una instancia de Statement
            Statement stmt = dbcon.createStatement();
            // Asigna una consulta OQL. Esta consulta lo que hace es utilizar REF() para obtener el objeto
            //directamente en vez de obtener valores concretos (que también podría ser).
            //String commandText = "SELECT REF(a) from biblioteca.Autor a;";
            String commandText = "SELECT REF(a) from biblioteca.Autor a WHERE "
                    + "a.nombre='Pepe' AND a.apellidos='Sanchez';";
            // Ejecuta la consulta y obtiene un ResultSet
            ResultSet rset = stmt.executeQuery(commandText);
            Autor a1;
            // Lee rset uno a uno.
            while (rset.next()) {
                // Obtiene los objetos Autor.
                a1 = (Autor) rset.getObject(1);
                // Imprime los atributos de cada objeto con un formato determinado.
                System.out.println("Autor: "
                        + String.format("%16s", a1.getNombre())
                        + String.format("%16s", a1.getApellidos())
                        + " Spouse: "
                        + String.format("%16s", a1.getEdad()));
            }
            // Cierra las conexiones
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("SQLException:  " + e.getMessage());
        }
    }

    static int DEFAULT_ALLOCATOR_CNT = 50;
    static int SAMPLE_OBJECT_CNT = 100;
    static int OBJECT_PER_TRAN_CNT = 20;
}
