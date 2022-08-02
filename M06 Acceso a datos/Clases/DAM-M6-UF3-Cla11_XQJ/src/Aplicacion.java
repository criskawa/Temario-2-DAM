
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQResultSequence;

public class Aplicacion {

    public static void main(String[] args) {
        try {
            GestorDB gestorDB = new GestorDB();
            XQResultSequence result;
            result = gestorDB.ejecutarQuery("for $a in /Libros/Libro return ($a/Autor,$a/Titulo)");
            //result = gestorDB.ejecutarQuery("let  $a :=  /Favoritos/Libro return <Libro>{$a/Autor,$a/Titulo}</Libro>"); 
            //result = gestorDB.ejecutarQuery("for $a in  /Favoritos/Libro return <Libro>{$a/Autor,$a/Titulo}</Libro>"); 
            
            // Iteracción
            if (!result.isClosed()) {
                while (result.next()) {
                    // Imprime cada uno de los elementos encontrados
                    System.out.println("\n" + result.getItemAsString(null));
                }
            } else {
                System.out.println("El Result está cerrado...hay un problema que detectar");
            }
        } catch (XQException e) {
            System.out.println("Error al Ejecutar " + e.getMessage());
        }
    }
}
