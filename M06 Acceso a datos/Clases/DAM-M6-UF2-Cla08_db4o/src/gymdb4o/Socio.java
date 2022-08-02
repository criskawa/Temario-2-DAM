/*
 * Entity Socio
 */
package gymdb4o;

/**
 *
 * @author Linkia
 */
public class Socio implements Cloneable, Comparable<Socio> {

    private int numSocio;
    private String nombre;
    private String apellidos;

    public Socio() {
        nombre = "";
        apellidos = "";
    }

    public Socio(int numSocio, String nombre, String apellidos) {
        this.numSocio = numSocio;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumSocio() {
        return numSocio;
    }

    public void setNumSocio(int numSocio) {
        this.numSocio = numSocio;
    }

    @Override
    public String toString() {
        return numSocio + " - " + nombre + " " + apellidos;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Socio o) {
        return nombre.compareTo(o.getNombre());
    }
    
    
}
