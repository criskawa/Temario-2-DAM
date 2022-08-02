/*
 * Entity Actividad
 */
package gymdb4o;

/**
 *
 * @author Linkia
 */
public class Actividad {
    
    private String nombre;
    private double precio;
    private int horas;

    public Actividad() {
        nombre = "";
    }

    public Actividad(String nombre, double precio, int horas) {
        this.nombre = nombre;
        this.precio = precio;
        this.horas = horas;
    }
    
    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    
}
