package com.example.projectofinal.Controladores;

import com.example.projectofinal.CambiarEscena;
import com.example.projectofinal.HelloApplication;
import com.example.projectofinal.Models.ModelLogin;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author kevin
 * @version 1
 */
public class InicioSesionController {
    /**
     * Elemento de tipo TextField que indica el nombre de usuario
     */
    @FXML
    private TextField usutxt;
    /**
     * Elemento de tipo TextField que indica la contraseña
     */
    @FXML
    private PasswordField psw;
    /**
     * Elemento de tipo Button
     */
    @FXML
    private Button btn;
    /**
     * Elemento de tipo Label que esta oculto
     */
    @FXML
    private Label labeloculta;
    /**
     * Elemento de tipo ModelLogin
     * @see ModelLogin
     */
    public ModelLogin modelLogin;

    /**
     * Metodo que se inicia al abrir la escena
     * @see ModelLogin
     */
    @FXML
    public void initialize() {
        this.modelLogin = ModelLogin.getInstance();
        this.btn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Acciones que se activan al pulsar sobre el boton
             * @param actionEvent actionEvent
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                labeloculta.setVisible(false);
                if (modelLogin.ComprobarCampoVacio(usutxt.getText(), psw.getText())) {
                    if (modelLogin.usuarioExiste(usutxt.getText(), psw.getText())) {
                        if (modelLogin.esOficinista(usutxt.getText())){
                            try {
                                CambiarEscena.cambiarVentana(btn, "menu");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else {
                            labeloculta.setText("Este usuario no esta autorizado");
                            labeloculta.setVisible(true);
                            labeloculta.setStyle("-fx-text-fill: red");
                        }

                    } else {
                        labeloculta.setText("El usuario no existe");
                        labeloculta.setVisible(true);
                        labeloculta.setStyle("-fx-text-fill: red");
                    }
                } else {
                    psw.setPromptText("Campo vacio!!");
                    usutxt.setPromptText("Campo vacio!!");
                }

            }
        });
    }
}
