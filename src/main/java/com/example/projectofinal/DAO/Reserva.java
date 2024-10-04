package com.example.projectofinal.DAO;

import java.time.LocalDateTime;

/**
 * @author kevin
 * @version 1
 */
public class Reserva {
    /**
     * Propiedad de tipo int que indica el id de la reserva
     */
    private int id;
    /**
     * Propiedad de tipo String que indica el cliente que realiza la reserva
     */
    private String cliente;
    /**
     * Propiedad de tipo LocalDateTime que indica la fecha en la que se realiza la reserva
     */
    private LocalDateTime fecha;
    /**
     * Propiedad de tipo int que indica el id de la actividad que se reserva
     */
    private int actividad;
    /**
     * Propiedad de tipo int que indica el estado de la actividad
     */
    private int estado;
    /**
     * Propiedad de tipo String que indica que emleado se encargara de arbitrar/entrenar la actividad reservada
     */
    private String empleado;

    /**
     * Constructor por defecto de la clase Reserva
     */
    public Reserva() {
    }

    /**
     * Constructor que recibe todos los parámetros de la clase Reserva
     * @param id de tipo int indica el id de la reserva
     * @param cliente de tipo String que indica el cliente que realiza la reserva
     * @param fecha de tipo LocalDateTime que indica la fecha en la que se realizo la reserva
     * @param actividad de tipo int que indica el id de la actividad que se reserva
     * @param estado de tipo int que indica el estado de la reserva
     * @param empleado de tipo String que indica el empleado que se encarga de la reserva
     */

    public Reserva(int id, String cliente, LocalDateTime fecha, int actividad, int estado, String empleado) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.actividad = actividad;
        this.estado = estado;
        this.empleado = empleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getActividad() {
        return actividad;
    }

    public void setActividad(int actividad) {
        this.actividad = actividad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return actividad + " " + fecha + " " + cliente;
    }
}
