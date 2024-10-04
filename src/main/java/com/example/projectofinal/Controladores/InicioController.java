package com.example.projectofinal.Controladores;

import com.example.projectofinal.CambiarEscena;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class InicioController {
    /**
     * Propiedad de tipo Button que es un boton auxiliar
     */
    @FXML
    private Button auxbtn;
    /**
     * Propiedad de tipo ImageView que es una imagen
     */
    @FXML
    private ImageView img;

    /**
     * Metodo que se ejecuta al iniciar la escena
     */
    @FXML
    public void initialize(){
        /**
         * Acciones que se ejecutan al realizar la accion de mover el raton sobre la escena
         */
        img.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                continuar();
            }
        });

    }

    /**
     * Metodo utilizado para cambiar de ventana
     */
    public void continuar(){
        try {
            Thread.sleep(3*1000);
            CambiarEscena.cambiarVentana(auxbtn, "login");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
