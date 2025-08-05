import controllers.ElementoController;
import spark.Spark;

public class Main {
    public static void main(String[] args) {
        // Configurar el puerto
        Spark.port(8080);
        
        // Inicializar el controlador
        new ElementoController();
        
        System.out.println("Servidor iniciado en http://localhost:8080");
    }
}
