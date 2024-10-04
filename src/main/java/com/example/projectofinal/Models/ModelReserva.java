package com.example.projectofinal.Models;

import com.example.projectofinal.DAO.Actividad;
import com.example.projectofinal.DAO.Reserva;
import com.example.projectofinal.Repositoris.RepositoryReservas;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author kevin
 * @version 1
 */
public class ModelReserva {
    /**
     * Actividad creada por defecto
     */
    private static Actividad actividadActual = new Actividad(1, "Fútbol", 50);
    /**
     * Instancia de la propia clase
     */
    private static ModelReserva instance;

    /**
     * Constructor por defecto privado de la clase ModelReserva
     */
    private ModelReserva(){}

    /**
     * Método utilizado para obtener la instancia de la clase ModelReserva
     * @return la instancia de la clase
     */
    public static ModelReserva getInstance(){
        if (instance == null)
            instance = new ModelReserva();
        return instance;
    }

    /**
     * Coje la instancia de la clase RepositorioReserva
     * @see RepositoryReservas
     */
    RepositoryReservas repositoryReservas = RepositoryReservas.getInstance();
    /**
     * Propiedad de clase String que hace referencia al empleado
     */
    public static String Empleado;

    /**
     * Metodo que llama a otro para obtener una lista de empleaodos
     * @param id identificador  de una actividad
     * @return una lista de empleados
     * @see RepositoryReservas
     */
    public List<String> getEmpleados(int id){
        return repositoryReservas.getEmpleadosDisponibles(id);
    }
    /**
     * Metodo que llama a otro para obtener una lista de reservas
     * @param lunes de tipo String indica la fecha inicial
     * @param viernes de tipo String indica la fecha final
     * @return una lista de reservas
     * @see RepositoryReservas
     */
    public List<Reserva> getReservas(String lunes, String viernes){
        return repositoryReservas.getReservas(lunes, viernes);
    }

    /**
     * Metodo que llama a otro para insertar una reserva
     * @param localDateTime indica la fecha de la reserva
     * @param idReserva indica el id de la reserva
     * @param cliente indica el cliente de la reserva
     * @see RepositoryReservas
     */
    public void realizarReserva(LocalDateTime localDateTime, int idReserva, String cliente){
        repositoryReservas.realizarReserva(localDateTime, idReserva, cliente);
    }

    public static String getEmpleado() {
        return Empleado;
    }

    public static void setEmpleado(String empleado) {
        Empleado = empleado;
    }

    public static Actividad getActividadActual() {
        return actividadActual;
    }
    public static void setActividadActual(Actividad actividadActual) {
        ModelReserva.actividadActual = actividadActual;
    }

    public List<Actividad> listaActividades(){ return repositoryReservas.listaActividades();}

}
