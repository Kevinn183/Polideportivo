package com.example.projectofinal.Models;

import com.example.projectofinal.DAO.Actividad;
import com.example.projectofinal.DAO.Reserva;
import com.example.projectofinal.Repositoris.RepositoryAsignar;
import com.example.projectofinal.Repositoris.RepositoryReservas;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author kevin
 * @version 1
 */
public class ModelAsignar {
    /**
     * Instancia de la clase
     */
    private static ModelAsignar instance;
    /**
     * Coje una actividad de otra clase
     * @see RepositoryReservas
     */
    public static Actividad actividad = RepositoryReservas.getActividadActual();
    /**
     * Propiedad de tipo String que hace referencia a un empleado
     */
    public static String empleado;

    /**
     * Constructor por defecto privado de la clase
     */
    private ModelAsignar(){

    }

    /**
     * Metodo que permite obtener la instancia de la clase
     * @return la instancia de la clase
     */
    public static ModelAsignar getInstance(){
        if (instance == null){
            instance = new ModelAsignar();
        }
        return instance;
    }

    /**
     * Coje la instancia de otra clase
     * @see RepositoryAsignar
     */
    RepositoryAsignar repositoryAsignar = RepositoryAsignar.getInstance();

    /**
     * Metodo que llama a otro metoodo de otra clase para obtener uina lista de reservas
     * @param id indica el identificador de lla actividad
     * @return una lista de reservas
     * @see RepositoryAsignar
     */
    public List<Reserva> getReservas(int id){
        return repositoryAsignar.getReservas(id);
    }

    /**
     * Metodo utilizado para obtener las reservas que tienen asignadas un determinado empleado
     * @param id de tipo int
     * @param empleado de tipo String
     * @return una lista de reservas
     * @see RepositoryAsignar
     */
    public List<Reserva> getReservasAsignadas(int id, String empleado){
        return repositoryAsignar.getReservasAsignadas(id,empleado);
    }

    /**
     * Metodo utilizado para comprobar que un empleado no tiene 2 actividades a la vez
     * @param localDateTime indica la fecha
     * @param empleado indica el empleado
     * @return un boolean dependiendo del resultaado
     * @see RepositoryAsignar
     */
    public boolean comprobarHoras(LocalDateTime localDateTime, String empleado){
        int aux = repositoryAsignar.comprobarHora(localDateTime, empleado);
        return aux == 0;
    }

    /**
     * Metodo que llama a otro de otra clase para anyadir empleados a una reserva
     * @param empleado de tipo String
     * @param id de tipo int
     * @param localDateTime inidca la fecha
     * @see RepositoryAsignar
     */
    public void anyadirEmpleado(String empleado,int id, LocalDateTime localDateTime){
        repositoryAsignar.anyadirEmpleado(empleado, id, localDateTime);
    }

    /**
     * Metodo que llama a otro para cambiar el estado en una reserva
     * @param estado de tipo int
     * @param id de tipo int
     * @param localDateTime indica la fecha
     * @see RepositoryAsignar
     */
    public void cambiarEstado(int estado,int id, LocalDateTime localDateTime){
        repositoryAsignar.camhbiarEstado(estado, id, localDateTime);
    }

    public static int getIdAct() {
        return actividad.getId();
    }

    public static String getEmpleado() {
        return RepositoryAsignar.getInstance().getEmpleado(empleado);
    }


    public static void setEmpleado(String empleado) {
        ModelAsignar.empleado = empleado;
    }

    public static void setActividad(Actividad actividad) {
        ModelAsignar.actividad = actividad;
    }

    //
}
