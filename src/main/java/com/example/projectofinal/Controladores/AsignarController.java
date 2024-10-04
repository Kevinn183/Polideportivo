package com.example.projectofinal.Controladores;

import com.example.projectofinal.CambiarEscena;
import com.example.projectofinal.DAO.Reserva;
import com.example.projectofinal.Models.ModelAsignar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class AsignarController {
    /**
     * Elemento de clase ListView que indica las actividades disponibles
     */
    @FXML
    private ListView disponibles;
    /**
     * Elemento de clase ListView que indiaca las actividades asignadas
     */
    @FXML
    private ListView asignadas;
    /**
     * Elemento de clase boton que se usa para asignar
     */
    @FXML
    private Button asignar;
    /**
     * Elemento de clase boton que se usa para ir atras
     */
    @FXML
    private Button atras;
    /**
     * Elemento de clase boton que se usa para cancelar una reserva
     */
    @FXML
    private Button cancelar;
    /**
     * Lista de reservas
     */
    List<Reserva> reservas;
    /**
     * Lista de reservas asignadas
     */
    List<Reserva> reservasAsignadas;
    /**
     * Coje la instancia de la clase ModelAsignar
     */
    ModelAsignar modelAsignar = ModelAsignar.getInstance();

    /**
     * Metodo que inicia cuando se abre la escena
     */
    public void initialize(){
        int id = ModelAsignar.getIdAct();
        String empleado = ModelAsignar.getEmpleado();

        reservas = modelAsignar.getReservas(id);
        for (Reserva reserva : reservas) {
            disponibles.getItems().add(reserva);
        }
        disponibles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        reservasAsignadas = modelAsignar.getReservasAsignadas(id, empleado);
        for (Reserva reserva : reservasAsignadas) {
            asignadas.getItems().add(reserva);
        }
        /**
         * Accion que se realiza al pulsar sobre un elemento de la lista disponibles
         */
        disponibles.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Reserva reserva = (Reserva) disponibles.getSelectionModel().getSelectedItem();
                LocalDateTime fecha = reserva.getFecha();
                if (modelAsignar.comprobarHoras(fecha, empleado)){
                    modelAsignar.anyadirEmpleado(empleado, id,fecha);
                    modelAsignar.cambiarEstado(2, id, fecha);
                }
            }
        });
        /**
         * Accion que se realiza al pulsar sobre el boton asignar
         */
        asignar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    CambiarEscena.cambiarVentana(asignar, "asignar");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        /**
         * Accion que se realiza al pulsar sobre el boton atras
         */
        atras.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    CambiarEscena.cambiarVentana(atras, "tablaReservas");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        /**
         * Accion que se realiza al pulsar sobre el boton cancelar
         */
        cancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                disponibles.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Reserva reserva = (Reserva) disponibles.getSelectionModel().getSelectedItem();
                        LocalDateTime fecha = reserva.getFecha();
                        modelAsignar.cambiarEstado(3, id, fecha);
                        disponibles.getItems().remove(reserva);
                    }
                });
            }
        });

    }
}
