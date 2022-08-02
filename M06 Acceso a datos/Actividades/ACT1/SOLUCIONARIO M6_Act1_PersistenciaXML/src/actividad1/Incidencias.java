package actividad1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "incidencia"
})
@XmlRootElement(name = "incidencias")
public class Incidencias {
	
    @XmlElement(required=true)
    protected List<Incidencias.Incidencia> incidencia;
	
    public List<Incidencias.Incidencia> getIncidencia() {
        if (incidencia == null) {
        	incidencia = new ArrayList<Incidencias.Incidencia>();
        }
        return this.incidencia;
    }
    
    public void setIncidencias(ArrayList<Incidencia> arrayList) {
        incidencia = arrayList;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "origen",
        "destino",
        "detalle",
        "tipo"
    })
    
    public static class Incidencia {
		
        @XmlElement(name = "origen", required = true)
		private String origen;
        @XmlElement(name = "destino", required = true)
		private String destino;
        @XmlElement(name = "detalle", required = true)
		private String detalle;
        @XmlElement(name = "tipo", required = true)
		private String tipo;
        @XmlAttribute(name = "fechahora")
		private String fechahora;
		       
        public Incidencia() {
		}
             
		public Incidencia(String origen, String destino, String detalle, String tipo, String fechahora) {
			this.origen = origen;
			this.destino = destino;
			this.detalle = detalle;
			this.tipo = tipo;
			this.fechahora = fechahora;
		}

		public String getFecha() {
			return fechahora;
		}
		public void setFecha(String fechahora) {
			this.fechahora = fechahora;
		}
		public String getOrigen() {
			return origen;
		}
		public void setOrigen(String origen) {
			this.origen = origen;
		}
		public String getDestino() {
			return destino;
		}
		public void setDestino(String destino) {
			this.destino = destino;
		}
		public String getDetalle() {
			return detalle;
		}
		public void setDetalle(String detalle) {
			this.detalle = detalle;
		}
		public String getTipo() {
			return tipo;
		}
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		
		@Override
		public String toString() {
			return "Incidencia [fecha=" + fechahora + ", origen=" + origen + ", destino=" + destino + ", detalle=" + detalle
					+ ", tipo=" + tipo + "]";
		}
    } //fin clase interna Incidencia
    
} //fin clase Incidencas
