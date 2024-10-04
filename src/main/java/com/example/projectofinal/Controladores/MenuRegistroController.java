package com.example.projectofinal.Controladores;

import com.example.projectofinal.CambiarEscena;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * @author kevin
 * @version 1
 */
public class MenuRegistroController {
    /**
     * Elemento de tipo Button que indica oficinista
     */
    @FXML
    private Button ofibtn;
    /**
     * Elemento de tipo Button que indica cliente
     */
    @FXML
    private Button clibtn;
    /**
     * Elemento de tipo Button que indica empleado
     */
    @FXML
    private Button empbtn;
    /**
     * Elemento de tipo Button que indica atras
     */
    @FXML
    private Button atras;

    /**
     * Metodo que se ejecuta al abrir la escena
     */
    public void initialize(){
        ofibtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Acciones que se ejecutan al pulsar sobre el boton de oficina
             * @param actionEvent actionEvent
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    cambiar("registroOfi", ofibtn);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        clibtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Acciones que se ejecutan al pulsar sobre el boton de cliente
             * @param actionEvent actionEvent
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    cambiar("registroCli", clibtn);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        empbtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Acciones que se ejecutan al pulsar sobre el boton de empleado
             * @param actionEvent actionEvent
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    cambiar("registroEmp", empbtn);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        atras.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Acciones que se ejecutan al pulsar sobre el boton de atras
             * @param actionEvent actionEvent
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    cambiar("menu", atras);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * metodo ques e utiliza para  cambiar de escena
     * @param texto indica la ventana a la que quieres cambiar
     * @param btn controlador que le pasas
     * @throws IOException
     */
    public void cambiar(String texto, Button btn) throws IOException {
        CambiarEscena.cambiarVentana(btn,texto);
    }
}
