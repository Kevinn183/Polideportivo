package com.example.projectofinal.Controladores;

import com.example.projectofinal.CambiarEscena;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author kevin
 * @version 1
 */
public class MenuController {
    /**
     * Elemento de tipo Button que sirve para ir al calendario
     */
    @FXML
    private Button calbtn;
    /**
     * Elemento de tipo Button que sirve para ir a los registros
     */
    @FXML
    private Button regbtn;
    /**
     * Elemento de tipo Button que sirve para salir
     */
    @FXML
    private Button salirbtn;

    /**
     * Metodo que se inicia al abrir la escena
     */
    public void initialize(){
        calbtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Accion que se realiza al pulsar sobre el boton del calendario
             * @param actionEvent actionEvent
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    CambiarEscena.cambiarVentana(calbtn,"elegir");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        regbtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Accion que se realiza al pulsar sobre el boton del registro
             * @param actionEvent actionEvent
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    CambiarEscena.cambiarVentana(regbtn, "menuRegistros");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        salirbtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Accion que se realiza al pulsar sobre el boton de salir
             * @param actionEvent actionEvent
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    CambiarEscena.cambiarVentana(salirbtn, "login");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
