package librosxml;

public class ExcepcionGestorBD extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * ***************************************************
     * Constructor por defecto Se asigna mensaje genérico.
     ****************************************************
     */
    public ExcepcionGestorBD() {
        super("Problema en la conexión con la BD");
    }

    /**
     * **************************************************
     * pTexto Es la descripción de la excepción
     ***************************************************
     */
    public ExcepcionGestorBD(String pTexto) {
        super(pTexto);
    }
}
