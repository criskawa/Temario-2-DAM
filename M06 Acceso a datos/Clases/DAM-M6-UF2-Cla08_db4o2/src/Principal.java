
import com.db4o.ObjectSet;

public class Principal {

    public static void main(String[] args) {
        Gestordb4o gestor = new Gestordb4o();

        // GUARDAR OBJETOS
        System.out.println("***** GUARDAR OBJETOS *****");
//        Piloto pilot1 = new Piloto("Pepe Perez", 100);
//        gestor.guardarObjeto(pilot1);
//        Piloto pilot2 = new Piloto("Fernando Alonso", 99);
//        gestor.guardarObjeto(pilot2);
//        Piloto pilot3 = new Piloto("Lewis Hamilton", 99);
//        gestor.guardarObjeto(pilot3);
//        Piloto pilot4 = new Piloto("Michael Schumacher", 100);
//        gestor.guardarObjeto(pilot4);
//        System.out.println("--------------------------------");
      
        // OBTENER OBJETOS
        System.out.println("***** OBTENER OBJETOS *****");
        Piloto prototipo = new Piloto(null, 0);
        gestor.obtenerPilotos(prototipo);
        System.out.println("--------------------------------");
      
        // OBTENER TODOS LOS OBJETOS DE UNA CLASE
//       System.out.println("****** OBTENER TODOS LOS OBJETOS DE UNA CLASE ******");
//        gestor.obtenerTodosLosPilotos();
//        System.out.println("--------------------------------");
//        
//        // BUSCAR EN FUNCIÓN DEL VALOR DE UNO DE LOS ATRIBUTOS
//        System.out.println("****** BUSCAR EN FUNCIÓN DEL VALOR DE UNO DE LOS ATRIBUTOS *****");
//        gestor.buscarPilotosPorNombre("Michael Schumacher");
//        System.out.println("--------------------------------");
//        gestor.buscarPilotosPorPuntos(99);
//        System.out.println("--------------------------------");
        
      
        // ACTUALIZAR DATOS DE UN PILOTO
//        System.out.println("***** ACTUALIZAR DATOS DE UN PILOTO ******");
//        gestor.actualizarPiloto("Michael Schumacher", 0);
//        System.out.println("--------------------------------");
//        gestor.actualizarPiloto(null, 99);
//        System.out.println("--------------------------------");
//        gestor.actualizarPiloto(null, 55);
//        System.out.println("--------------------------------");
       
       
        // ELIMINAR PILOTO
//        System.out.println("***** ELIMINAR PILOTO *****");
//        gestor.eliminarPiloto("Pepe Perez", 0);
//        System.out.println("--------------------------------");
       
     
        System.out.println("***** OBTENER OBJETOS *****");
        gestor.obtenerPilotos(prototipo);
        System.out.println("--------------------------------");
        
        
        // CERRAR db4o
        System.out.println("***** CERRAR db4o *****");
        gestor.cerrar();
        System.out.println("--------------------------------");
    }
}
