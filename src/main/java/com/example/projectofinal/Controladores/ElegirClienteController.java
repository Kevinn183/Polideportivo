package com.example.projectofinal.Controladores;

import com.example.projectofinal.CambiarEscena;
import com.example.projectofinal.Models.ModelElegirCliente;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * @author kevin
 * @version 1
 */
public class ElegirClienteController {
    /**
     * Propiedad de tipo TextField que indica el cliente
     */
    @FXML
    private TextField clientetxt;
    /**
     * Propiedad de tipo Boton que se usa para buscar el cliente
     */
    @FXML
    private Button buscarbtn;
    /**
     * Propiedad de tipo Label que esta oculta
     */
    @FXML
    private Label labelOculta;
    /**
     * Propiedad de tipo Button que se usa para ir atras
     */
    @FXML
    private Button atras;
    /**
     * Coje la instancia de la clase ModelElegirCliente
     * @see ModelElegirCliente
     */
    ModelElegirCliente modelElegirCliente = ModelElegirCliente.getInstance();

    /**
     * Método que se inicia al abrir la escena
     */
    public void initialize(){
        clientetxt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 9) {
                clientetxt.setText(oldValue);
            }
        });
        /**
         * Acciones que se ejecutan al pulsar sobre el boton buscar
         */
        buscarbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String cliente = clientetxt.getText();
                if (modelElegirCliente.ComprobarVacio(cliente) && modelElegirCliente.verExiste(cliente)){
                    ReservaController reservaController = new ReservaController();
                    reservaController.setDniCLiente(cliente);
                    try {
                        CambiarEscena.cambiarVentana(buscarbtn, "tablaReservas");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    labelOculta.setVisible(true);
                }
            }
        });
        /**
         * Metodo que se ejecuta al pulsar sobre el boton atras
         */
        atras.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    CambiarEscena.cambiarVentana(atras, "menu");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
