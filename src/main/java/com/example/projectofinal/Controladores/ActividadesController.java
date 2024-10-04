package com.example.projectofinal.Controladores;

import com.example.projectofinal.DAO.Actividad;
import com.example.projectofinal.Models.ModelAsignar;
import com.example.projectofinal.Models.ModelReserva;
import com.example.projectofinal.Repositoris.RepositoryReservas;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author kevin
 * @version 1
 */
public class ActividadesController {
    /**
     * Elemento de tipo ListView
     */
    @FXML
    private ListView lista = new ListView();
    /**
     * Coje la instancia de la clase ModelReserva
     * @see ModelReserva
     */
    private ModelReserva modelReserva = ModelReserva.getInstance();
    /**
     * Coje la instancia de la clase ModelAsignar
     * @see ModelAsignar
     */
    private ModelAsignar modelAsignar = ModelAsignar.getInstance();

    /**
     * Metodo que se inicia al abrir la escena de este controlador
     */
    public void initialize(){
        for (Actividad actividad : modelReserva.listaActividades()){
            lista.getItems().add(actividad);
        }
        /**
         * Acciones que se ejecutan al pulsar sobrle la lista
         * @see ModelReserva
         * @see ModelAsignar
         */
        lista.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Actividad actividad = (Actividad) lista.getSelectionModel().getSelectedItem();
                ModelReserva.setActividadActual(actividad);
                ModelAsignar.setActividad(actividad);
                Stage mystage= (Stage) lista.getScene().getWindow();
                mystage.close();
            }
        });
    }
}