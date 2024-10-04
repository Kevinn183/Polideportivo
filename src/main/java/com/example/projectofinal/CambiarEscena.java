package com.example.projectofinal;

import com.example.projectofinal.Controladores.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author kevin
 * @version 1
 */
public class CambiarEscena {
    /**
     * Este método se usa para cambiar la ventana actual
     * @param control se le pasa el control desde el que accedes a este método
     * @param ventana de tipo String que indica a que ventana quieres cambiar
     *
     * @throws IOException
     */
    public static void cambiarVentana(Control control, String ventana) throws IOException {
        Stage stage;
        stage = (Stage) control.getScene().getWindow();
        try {
            FXMLLoader fmxLoader = getScene(ventana);
            Parent root = fmxLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ventana);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método se utiliza para abrir una ventana nueva
     * @param ventana de tipo String que indica  que ventana quieres abrir
     */
    public static void abrirVentana(String ventana){
        Parent root;
        try {
            FXMLLoader fxmlLoader = getScene(ventana);
            root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Selecciona una actividad!!");
            stage.setScene(new Scene(root, 300, 400));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Este método se utiliza para elegir a que ventana cambiar dependiendo de la ventana que le le pase
     * @param ventana de tipo String que indica la ventana que se quiere elegir
     * @return
     */
    private static FXMLLoader getScene(String ventana){
        String url = "/com/example/projectofinal/";
        switch (ventana){
            case "login":
                return new FXMLLoader(InicioSesionController.class.getResource(url +"InicioSesion.fxml"));
            case "tablaReservas":
                return new FXMLLoader(ReservaController.class.getResource(url + "tablaReservas.fxml"));
            case "elegirActividad":
                return new FXMLLoader(HelloApplication.class.getResource(url + "eleccionActividades.fxml"));
            case "registroOfi":
                return new FXMLLoader(RegistroOfiController.class.getResource(url + "RegistrarOficinistas.fxml"));
            case "menu":
                return new FXMLLoader(MenuController.class.getResource(url + "MenuEleccion.fxml"));
            case "elegir":
                return new FXMLLoader(HelloApplication.class.getResource(url + "elegirCliente.fxml"));
            case "menuRegistros":
                return new FXMLLoader(HelloApplication.class.getResource(url + "menuRegistros.fxml"));
            case "registroCli":
                return new FXMLLoader(RegistroOfiController.class.getResource(url + "registroClientes.fxml"));
            case "registroEmp":
                return new FXMLLoader(RegistroOfiController.class.getResource(url + "RegistroEmpleado.fxml"));
            case "asignar":
                return new FXMLLoader(AsignarController.class.getResource(url + "asignar.fxml"));
        }
        return  null;
    }
}
