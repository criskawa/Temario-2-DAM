package actividad08;
import java.util.*;
import java.io.Serializable;


public class Empleado implements Serializable{
	private String nombre;
	private String edad;
	private String sexo;

	private HashMap<String,String> aficiones = new HashMap<String,String>();

	
	public Empleado(String nombre, String edad, String sexo,
	HashMap<String,String> aficiones) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
		this.aficiones= aficiones;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		String txt_aficiones="";
		  Iterator it = aficiones.entrySet().iterator();
        it = aficiones.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry e = (HashMap.Entry) it.next();
			txt_aficiones=txt_aficiones +","+ e.getValue();
        }
		return "Empleado [nombre=" + nombre + ", edad=" + edad + ", sexo=" + sexo + ", aficiones:"+txt_aficiones+"]";
	}
	
}
