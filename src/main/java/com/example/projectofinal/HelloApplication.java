package com.example.projectofinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author kevin
 * @version 1
 */
public class HelloApplication extends Application {
    /**
     * Este método se utiliza para iniciar la aplicación
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PantallaInicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Bienvenido!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}