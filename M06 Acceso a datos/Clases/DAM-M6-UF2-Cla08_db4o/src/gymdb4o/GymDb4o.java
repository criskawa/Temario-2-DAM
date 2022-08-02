/*
 * Ejemplo con db4o
 */
package gymdb4o;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linkia
 */
public class GymDb4o {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // Clase de Test
        // Creamos unos cuantos objetos para testear
        Socio s1 = new Socio(1, "Pepe", "Garcia");
        Socio s2 = new Socio(3, "Manolo", "Tambor");
        Actividad a1 = new Actividad("Zumba", 20, 10);
        Matricula m1 = new Matricula(1, s1, a1, sdf.format(new Date()));
        Matricula m2 = new Matricula(2, s2, a1, sdf.format(new Date()));

        GymBDOO gestor = new GymBDOO();
        gestor.insertarSocio(s1);
        gestor.insertarSocio(s2);
        System.out.println("Socio insertado.");
        gestor.insertarActividad(a1);
        System.out.println("Actividad insertada.");
        gestor.insertarMatricula(m1);
        System.out.println("Matrícula insertada");
        
        Socio socioModificado;
 /*       try {
            socioModificado = (Socio) s1.clone();
            socioModificado.setApellidos("Gracia");
            gestor.modificarSocio(s1, socioModificado);
            System.out.println("Socio modificado");
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(GymDb4o.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        System.out.println("Listado de socios");
        List<Socio> socios = gestor.sociosOrdenadosPorNombre();
        for (Socio s : socios) {
            System.out.println(s);
        }
        
    /*    gestor.insertarMatricula(m2);
        System.out.println("Matrícula insertada");
        System.out.println("Matrícula más reciente");
        System.out.println(gestor.matriculaMasReciente());
*/
        gestor.cerrar();
    }

}
