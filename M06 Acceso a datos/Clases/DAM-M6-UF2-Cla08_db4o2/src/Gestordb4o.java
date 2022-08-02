
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.List;

public class Gestordb4o {

    // Atributo para la persistencia de OO
    private ObjectContainer db;

    public Gestordb4o() {
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "formula1");
    }

    public void guardarObjeto(Piloto p) {
        db.store(p);
        System.out.println("Objeto guardado " + p);
    }

    public void obtenerPilotos(Piloto p) {
        ObjectSet result = db.queryByExample(p);
        System.out.println(result.size());
        while (result.hasNext()) {
            System.out.println(result.next());
        }
    }

    public void obtenerTodosLosPilotos() {
        ObjectSet result = db.queryByExample(Piloto.class);
        System.out.println(result.size());
        while (result.hasNext()) {
            System.out.println(result.next());
        }
       /* List<Piloto> pilots = db.query(Piloto.class);
        System.out.println(pilots);*/
    }

    public void buscarPilotosPorNombre(String nombre) {
        Piloto prototipo = new Piloto(nombre, 0);
        ObjectSet result = db.queryByExample(prototipo);
        System.out.println(result.size());
        while (result.hasNext()) {
            System.out.println(result.next());
        }
    }

    public void buscarPilotosPorPuntos(int puntos) {
        Piloto prototipo = new Piloto(null, puntos);
        ObjectSet result = db.queryByExample(prototipo);
        System.out.println(result.size());
        while (result.hasNext()) {
            System.out.println(result.next());
        }
    }

    public void actualizarPiloto(String nombre, int puntos) {
        ObjectSet result = db.queryByExample(new Piloto(nombre, puntos));
        System.out.println(result.size());
        while (result.hasNext()) {
            Piloto found = (Piloto) result.next();
            found.addPoints(11);
            db.store(found);
            System.out.println("Se han añadido 11 puntos a: " + found);
        }
        this.obtenerTodosLosPilotos();
    }
    
    public void eliminarPiloto(String nombre, int puntos){
        ObjectSet result = db.queryByExample(new Piloto(nombre, puntos));
        System.out.println(result.size());
        while (result.hasNext()) {
            Piloto found = (Piloto) result.next();
            db.delete(found);
            System.out.println("Objeto borrado " + found);
        }
        this.obtenerTodosLosPilotos();
    }
    
    public void cerrar() {
        db.close();
    }
}
