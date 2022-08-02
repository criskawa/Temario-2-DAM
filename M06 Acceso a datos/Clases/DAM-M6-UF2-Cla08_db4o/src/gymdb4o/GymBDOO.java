/*
 * Clase para hacer la persistencia con db4o
 */
package gymdb4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.Db4oException;
import com.db4o.query.Query;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Linkia
 */
public class GymBDOO {

    // Atributo para la persistencia de OO
    private ObjectContainer db;

    public GymBDOO() {
        db = Db4oEmbedded.openFile("gimnasio");
    }

    // Ejemplo de insertar
    public void insertarSocio(Socio s) {
        db.store(s);
    }
    
    public void insertarActividad(Actividad a) {
        db.store(a);
    }

    // Ejemplo de modificar, es necesario traer el objeto de la BBDD 
    // y luego modificarlo
    public void modificarSocio(Socio antiguo, Socio modificado) {
        ObjectSet resultado = db.queryByExample(antiguo);
        Socio elSocio = (Socio) resultado.next();
        elSocio.setNombre(modificado.getNombre());
        elSocio.setApellidos(modificado.getApellidos());
        db.store(elSocio);
    }

    public List<Socio> selectAllSocios() {
        List<Socio> socios = new ArrayList<>();
        Query q = db.query();
        q.constrain(Socio.class);
        ObjectSet resultado = q.execute();
        while (resultado.hasNext()) {
            Socio s = (Socio) resultado.next();
            socios.add(s);
        }
        return socios;
    }
    
    public List<Socio> sociosOrdenadosPorNombre() {
        List<Socio> socios = selectAllSocios();
        Collections.sort(socios, Collections.reverseOrder());
        return socios;
    }

    public void insertarMatricula(Matricula m) {
        db.store(m);
    }

    // Método que devuelve la fecha de la última matrícula
    public String matriculaMasReciente() {
        Query q = db.query();
        q.constrain(Matricula.class);
        ObjectSet resultado = q.execute();
        List<Matricula> lista = new ArrayList<>();
        while (resultado.hasNext()) {
            Matricula m = (Matricula) resultado.next();
            lista.add(m);
        }
        if (lista.size() > 0) {
            return lista.get(lista.size() - 1).getFecha();
        } else {
            return null;
        }
    }

    public void cerrar() {
        db.close();
    }
}
