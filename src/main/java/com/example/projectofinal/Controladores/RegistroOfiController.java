package com.example.projectofinal.Controladores;

import com.example.projectofinal.CambiarEscena;
import com.example.projectofinal.DAO.Persona;
import com.example.projectofinal.Models.ModelRegistro;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author kevin
 * @version 1
 */
public class RegistroOfiController {
    /**
     *
     */
    ModelRegistro modelRegistro = ModelRegistro.getInstance();
    /**
     * Propiedad de tipo TextField donde se pone el nombre
     */
    @FXML
    private TextField nombreField;
    /**
     * Propiedad de tipo TextField donde se pone el dni
     */
    @FXML
    private TextField dniField;
    /**
     * Propiedad de tipo TextField donde se pone la contraseña
     */
    @FXML
    private PasswordField pswdField;
    /**
     * Propiedad de tipo TextField donde se repite la contraseña
     */
    @FXML
    private PasswordField repetirPswdField;
    /**
     * Boton que se usa para registrar
     */
    @FXML
    private Button registroBtn;
    /**
     * Label oculta que indica errores
     */
    @FXML
    private Label labelOculta;

    /**
     * Mtodo que se ejecuta al iniciar la escena
     */

    public void initialize(){
        dniField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 9) {
                dniField.setText(oldValue);
            }
        });
        this.registroBtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Acciones que se ejecutan al pulsar el boton
             * @param actionEvent
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                labelOculta.setVisible(false);
                String nombre = nombreField.getText();
                String dni = dniField.getText();
                String pswd = pswdField.getText();
                String repPswd = repetirPswdField.getText();
                if (modelRegistro.ComprobarCampoVacio(nombre, dni, pswd, repPswd)) {
                    if (modelRegistro.passwordsIguales(pswd, repPswd)){
                        if (modelRegistro.usuarioNoExiste(dni)){

                            Persona persona = new Persona(nombre,dni,pswd);
                            modelRegistro.insertarUsuario(persona);
                            modelRegistro.insertarEmpleado(dni, 2);
                            cambiar();

                        }
                        else {
                            labelOculta.setText("Este usuario ya existe");
                            labelOculta.setVisible(true);
                        }
                    }
                    else {
                        labelOculta.setText("Las contraseñas no son iguales");
                        labelOculta.setVisible(true);
                    }

                } else {
                    nombreField.setPromptText("Campo vacio!!");
                    dniField.setPromptText("Campo vacio!!");
                    pswdField.setPromptText("Campo vacio!!");
                    repetirPswdField.setPromptText("Campo vacio!!");
                    labelOculta.setText("Hay campos vacios");
                    labelOculta.setVisible(true);
                }

            }
        });

    }

    /**
     * Metodo que se utiliza para cambiar de escena
     */
    public void cambiar(){
        try {
            CambiarEscena.cambiarVentana(registroBtn, "menu");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
