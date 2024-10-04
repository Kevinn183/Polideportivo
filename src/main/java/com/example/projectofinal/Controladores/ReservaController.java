package com.example.projectofinal.Controladores;

import com.example.projectofinal.CambiarEscena;
import com.example.projectofinal.DAO.Actividad;
import com.example.projectofinal.DAO.Hora;
import com.example.projectofinal.DAO.Reserva;
import com.example.projectofinal.Models.ModelAsignar;
import com.example.projectofinal.Models.ModelReserva;
import com.example.projectofinal.Repositoris.RepositoryReservas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author kevin
 * @version 1
 */
public class ReservaController {
    /**
     * Propiedad de tipo GridPane que hace referencia al grid
     */
    @FXML
    private GridPane myGrid;
    /**
     * Un listado de horas
     */
    private List<Hora> horas;
    /**
     * Elemento de tipo label que indica la actividad
     */
    @FXML
    private Label act;
    /**
     * Elemento de tipo Button que sirve para actualizar
     */
    @FXML
    private Button actualizar;
    /**
     * Listado de reservas
     */
    private List<Reserva> reservas;
    /**
     * Listado de fechas
     */
    private List<LocalDate> dates;
    /**
     * Propiedad de tipo String que indica el dni de un cliente
     */
    private static String dniCLiente;
    /**
     * Elemento de tipo Button que se usa para asignar empleados
     */
    @FXML
    private Button asignar;
    /**
     * Elemento de tipo Button que se usa para ir atras
     */
    @FXML
    private Button atras;
    /**
     * Elemento de tipo ComboBox que se usa para ver los empleados disponibles
     */
    @FXML
    private ComboBox combo;
    /**
     * Coje la instancia de la clase ModelReserva
     * @see ModelReserva
     */
    ModelReserva modelReserva = ModelReserva.getInstance();

    /**
     * Metodso que se ejecuta al abrir la escena
     */
    @FXML
    public void initialize(){
        actualizar.setDisable(true);
        actualizar.setVisible(false);
        combo.setDisable(true);
        combo.setVisible(false);

        reservas = sacarReservas();
        //Acciones que se realizar para llenar el comboBox
        final Actividad actividad1 = ModelReserva.getActividadActual();
        final int id = actividad1.getId();
        final List<String> empleadosDisp = modelReserva.getEmpleados(id);
        for (String s : empleadosDisp) {
            combo.getItems().add(s);
        }
        //Acciones que se realizan para ir generando los botones correctamente
        for(int i = 0; i < myGrid.getColumnCount()-1; i++){
            for(int j = 0; j < myGrid.getRowCount()-1; j++) {

                LocalTime localTime = LocalTime.parse(horas.get(j).getHora());
                LocalDateTime localDateTime = LocalDateTime.of(dates.get(i), localTime);
                Actividad actual = ModelReserva.getActividadActual();
                act.setText(actual.getNombre().toUpperCase());

                int idact = actividad1.getId();
                /**
                 * Pone los botones correctamente
                 */
                Button btn = new Button("Disponible");
                if(!isDisponible(localDateTime, idact) ) {
                    btn.setText("Pendiente");
                    btn.setDisable(true);
                }
                if (verDisponible(localDateTime, idact)){
                    btn.setText("Reservado");
                }
                if (verEstado(localDateTime, idact)){
                    btn.setText("Cancelado");
                }

                btn.setOnAction(new EventHandler<ActionEvent>() {
                    /**
                     * Acciones que se realizan al pulsar en un boton
                     * @param actionEvent apretar un boton
                     */
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        modelReserva.realizarReserva(localDateTime, id, dniCLiente);
                        try {
                            CambiarEscena.cambiarVentana(btn, "tablaReservas");

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                //da formato a los botones
                btn.setPrefHeight(61);
                btn.setPrefWidth(190);
                myGrid.add(btn, i+1, j+1);
                String aux = act.getText();
                act.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    /**
                     * Acciones que se realizan al pulsar sobre el label de actividad
                     * @param mouseEvent pulsar el label
                     */
                   @Override
                   public void handle(MouseEvent mouseEvent) {
                       actualizar.setDisable(false);
                       actualizar.setVisible(true);
                       CambiarEscena.abrirVentana("elegirActividad");
                       String aux2 = act.getText();
                       if (!aux.equals(aux2)){
                           Stage myStage = (Stage) act.getScene().getWindow();
                           myStage.close();
                       }
                   }
                });
                actualizar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    /**
                     * Acciones que se realizan al pulsar sobre el boton actualizar
                     * @param mouseEvent pulsar el boton actualizar
                     */
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        try {
                            CambiarEscena.cambiarVentana(actualizar, "tablaReservas");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                asignar.setOnAction(new EventHandler<ActionEvent>() {
                    /**
                     * Acciones que se realizan al pulsar el boton Aasignar
                     * @param actionEvent pulsar el boton asignar
                     */
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        combo.setVisible(true);
                        combo.setDisable(false);
                    }
                });
                combo.setOnAction(new EventHandler<ActionEvent>() {
                    /**
                     * Acciones que se realizan al pulsar sobre un elemento del comboBox
                     * @param actionEvent pulsar un elemento del comboBox
                     */
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        String empleado = (String) combo.getValue();
                        ModelAsignar.setEmpleado(empleado);
                        try {
                            CambiarEscena.cambiarVentana(combo, "asignar");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                atras.setOnAction(new EventHandler<ActionEvent>() {
                    /**
                     * Acciones que se realizan al pulsar sobre el boton atras
                     * @param actionEvent pulsar el boton atras
                     */
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
    }

    /**
     * Metodo que se utiliza para obtener una lista con las reservas llamando a otra clase
     * @return una lista de reservas
     * @see ModelReserva
     */
    private List<Reserva> sacarReservas() {
        horas = RepositoryReservas.getHoras();
        dates = getDatesCurrentWeek();
        final String lunes = dates.get(0).toString();
        final String viernes = dates.get(4).plusDays(1).toString();
        return modelReserva.getReservas(lunes,viernes);
    }

    /**
     * Metodo que se utiliza para obtener las fechas dentro de la semana actual
     * @return un listado de fechas
     */
    public List<LocalDate> getDatesCurrentWeek(){
        List<LocalDate> dates = new ArrayList<>();
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        for(int i = 1; i <= 5; i++){
            dates.add(now.with(fieldISO, i));
        }
        return dates;
    }

    /**
     * Metodo que se utiliza para ver que reservas estan disponibles
     * @param horaReserva una fecha
     * @param id un id de tipo int
     * @return un boolean dependiendo ed si esta disponible o no
     */
    public boolean isDisponible(LocalDateTime horaReserva, int id){
        for (Reserva reserva: reservas){
            if(reserva.getFecha().equals(horaReserva) && id == reserva.getActividad()){
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo que se utiliza para obtener todas las reservas que tengan un empleado asignado
     * @param horaReserva una fecha
     * @param id de tipo int
     * @return un boolean dependiendo de si la reserva tiene empleado
     */
    public boolean verDisponible(LocalDateTime horaReserva, int id) {
        for (Reserva reserva : reservas) {
            if (reserva.getEmpleado() != null && reserva.getFecha().equals(horaReserva) && id == reserva.getActividad()){
                return true;
            }
        }
        return false;
    }
    /**
     * Metodo que se utiliza para obtener el estado de las reservas
     * @param hora una fecha
     * @param id de tipo int
     * @return un boolean dependiendo de si el estado de la reserva es el que se busca
     */
    public boolean verEstado(LocalDateTime hora, int id){
        for (Reserva reserva : reservas) {
            if (reserva.getEstado() == 3 && reserva.getFecha().equals(hora) && id == reserva.getActividad()){
                return true;
            }
        }
        return false;
    }

    public void setDniCLiente(String dniCLiente) {
        ReservaController.dniCLiente = dniCLiente;
    }
}
