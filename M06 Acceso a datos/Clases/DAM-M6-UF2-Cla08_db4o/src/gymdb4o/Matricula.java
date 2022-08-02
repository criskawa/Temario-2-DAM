/*
 * Entity Matricula
 */
package gymdb4o;

/**
 *
 * @author Linkia
 */
public class Matricula {
    
    private int id;
    private Socio socio;
    private Actividad actividad;
    private String fecha;

    public Matricula() {
        socio = new Socio();
        actividad = new Actividad();
        fecha = "";
    }

    public Matricula(int id, Socio socio, Actividad actividad, String fecha) {
        this.id = id;
        this.socio = socio;
        this.actividad = actividad;
        this.fecha = fecha;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Matricula{" + "id=" + id + ", socio=" + socio + ", actividad=" + actividad + ", fecha=" + fecha + '}';
    }

    
}
