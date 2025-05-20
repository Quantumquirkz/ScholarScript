package geometria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/geometria/main.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/geometria/styles.css").toExternalForm());

        // Configurar el tamaño inicial de la ventana
        primaryStage.setWidth(800); // Ancho inicial
        primaryStage.setHeight(600); // Alto inicial
        primaryStage.setMinWidth(600); // Ancho mínimo
        primaryStage.setMinHeight(500); // Alto mínimo

        primaryStage.setTitle("Calculadora Geométrica");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Centrar la ventana en la pantalla
        primaryStage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}