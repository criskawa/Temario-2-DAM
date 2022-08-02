package actividad1;

import javax.xml.bind.annotation.XmlRegistry;


@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public Incidencias createIncidencias() {
        return new Incidencias();
    }

    public Incidencias.Incidencia createIncidenciasIncidencia() {
        return new Incidencias.Incidencia();
    }

}
