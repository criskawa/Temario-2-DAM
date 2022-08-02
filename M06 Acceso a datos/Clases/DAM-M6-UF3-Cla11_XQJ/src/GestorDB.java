import javax.xml.xquery.*;
import javax.xml.namespace.QName;
import net.xqj.exist.ExistXQDataSource;

public class GestorDB {

    XQConnection conn;
    XQDataSource xqs;
    
    private String usuario;
    private String usuarioPwd;
/*******************************************************************
* Constructor de la clase
* ******************************************************************/
    public GestorDB()  throws XQException{
        this.usuario = "admin";
        this.usuarioPwd = "";
        conectarBD();
    }

    private void conectarBD() throws XQException{      
        //Se crea el DataSource (origen de datos)
        xqs = new ExistXQDataSource();
        //Se establecen las propiedades de la conexión
        xqs.setProperty("serverName", "localhost");
        xqs.setProperty("port", "8080");
        //Se obtiene la conexión
        conn = xqs.getConnection("admin","");
    }
    
    public void cerrarBD() throws XQException{
        conn.close();
    }
    
    public XQResultSequence ejecutarQuery(String textoConsulta) throws XQException{
        // Crea un objeto expresión (reusable) XQuery Expression 
        XQExpression expr = conn.createExpression(); 
        // Ejecuta la XQuery expression
        XQResultSequence result = expr.executeQuery(textoConsulta); 
        return result;
    }
}