
public class Aplicacion {

    public static void main(String[] args) {
        try {
            GestorBD gestorBD = new GestorBD();
            boolean result;
//            if(gestorBD.ejecutarInsertAfter()){
//                System.out.println("XUpdate Ejecutado insert-after");
//            }
            
//           if(gestorBD.ejecutarInsertBefore()){
//                System.out.println("XUpdate Ejecutado insert-before");
//            }
           
//            if(gestorBD.ejecutarUpdate()){
//                System.out.println("XUpdate Ejecutado update");
//            }
            
//            if(gestorBD.ejecutarAppend()){
//                System.out.println("XUpdate Ejecutado append");
//            }
//            
//           if(gestorBD.ejecutarRemove()){
//                System.out.println("XUpdate Ejecutado remove");
//            }
//          if(gestorBD.ejecutarRename()){
//                System.out.println("XUpdate Ejecutado rename");
//            }

        } catch (ExcepcionGestorBD e) {
            System.out.println("Error en carga inicial\n" + e.getMessage());
        }
    }
}
