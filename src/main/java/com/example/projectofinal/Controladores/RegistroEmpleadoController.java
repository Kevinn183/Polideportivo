package com.example.projectofinal.Controladores;

import com.example.projectofinal.CambiarEscena;
import com.example.projectofinal.DAO.Actividad;
import com.example.projectofinal.DAO.Persona;
import com.example.projectofinal.Models.ModelRegistro;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistroEmpleadoController {
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
     * Elemento de tipo ListView quie indica las actividades
     */
    @FXML
    private ListView listView;
    /**
     * Lista de actividades
     */
    private List<Actividad> actividades;
    /**
     * Coje la instancia de la calse ModelRegistro
     * @see ModelRegistro
     */
    ModelRegistro modelRegistro = ModelRegistro.getInstance();

    /**
     * Metodo que se ejecuta al abrir la escena
     */
    public void initialize(){
        dniField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 9) {
                dniField.setText(oldValue);
            }
        });
        actividades = new ArrayList<>();
        for (Actividad actividad : modelRegistro.getActividades()){
            listView.getItems().add(actividad);
        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            /**
             * Acciones que se ejecutan al pulsar sobre un elemento de la ListView
             * @param mouseEvent mouseEvent
             */
            @Override
            public void handle(MouseEvent mouseEvent) {
                Actividad actividad = (Actividad) listView.getSelectionModel().getSelectedItem();
                actividades.add(actividad);
                listView.getItems().remove(actividad);
            }

        });
        registroBtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Acciones que se ejecutan al pulsar sobre el boton
             * @param actionEvent actionEvent
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                String nombre = nombreField.getText();
                String dni = dniField.getText();
                String pswd = pswdField.getText();
                String repPswd = repetirPswdField.getText();



                if (modelRegistro.ComprobarCampoVacio(nombre, dni, pswd, repPswd)){
                    if (modelRegistro.passwordsIguales(pswd, repPswd)){
                        if (modelRegistro.usuarioNoExiste(dni)){
                            if (!modelRegistro.listaVacia(actividades)){
                                Persona persona = new Persona(nombre, dni, pswd);
                                modelRegistro.insertarUsuario(persona);
                                modelRegistro.insertarEmpleado(dni, 3);
                                insertarEmpAct(dni, actividades);
                                try {
                                    cambiar(registroBtn);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            else {
                                labelOculta.setText("Tienes que elegir almenos 1 actividad");
                                labelOculta.setVisible(true);
                            }
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
                }
                else{
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
     * Metodo creado para ir insertando empleados en la base de datos
     * Este metodo llama a otro de la clase ModelRegistro
     * @param dni de tipo String
     * @param actividades una lista de actividades
     * @see ModelRegistro
     */
    public void insertarEmpAct(String dni, List<Actividad> actividades){
        for (int i = 0; i < actividades.size(); i++){
            int id = actividades.get(i).getId();
            modelRegistro.insertarEmpleadoAct(dni, id);
        }
    }

    /**
     * Metodo que se usa para cambiar de escena
     * @param btn un controlador
     * @throws IOException
     */
    public void cambiar(Button btn) throws IOException {
        CambiarEscena.cambiarVentana(btn, "menu");
    }

}
