package com.example.projectofinal.Repositoris;

import com.example.projectofinal.DAO.Actividad;
import com.example.projectofinal.DAO.Hora;
import com.example.projectofinal.DAO.Reserva;
import com.example.projectofinal.MyConnector;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 * @version 1
 */
public class RepositoryReservas {
    //Crea la conexion con la base de datos
    private static DataSource dataSource = MyConnector.getMySQLDataSource();
    /**
     * Actividad inicial
     */
    private static Actividad actividadActual = new Actividad(1, "Fútbol", 50);
    /**
     * Instancia de la propia clase
     */
    private static RepositoryReservas instance;

    /**
     * Constructor por defecto privado de la clase RepositoryReservas
     */
    private RepositoryReservas(){
    }

    /**
     * Metodo usado para conseguir la instancia de la clase RepositoryReservas
     * @return
     */
    public static RepositoryReservas getInstance(){
        if (instance == null){
            instance = new RepositoryReservas();
        }
        return instance;
    }

    /**
     * Método para conseguir las horas
     * @return devuelve un listado de horas
     */
    public static List<Hora> getHoras(){
        List<Hora> horas = new ArrayList<>();
        horas.add(new Hora(1, "08:00"));
        horas.add(new Hora(2, "09:00"));
        horas.add(new Hora(3, "10:00"));
        horas.add(new Hora(4, "11:00"));
        horas.add(new Hora(5, "12:00"));
        horas.add(new Hora(6, "13:00"));
        horas.add(new Hora(7, "17:00"));
        horas.add(new Hora(8, "18:00"));
        horas.add(new Hora(9, "19:00"));
        horas.add(new Hora(10,"20:00"));
        return horas;
    }

    /**
     * Método utilizado para obtener todas las reservas que se encuentren entre varias fechas
     * @param lunes de tipo String indica la primera fecha
     * @param viernes de tipo String indica la ultima fecha
     * @return devuelve unalista con todas las reservas que esten entre las 2 fechas
     */
    public List<Reserva> getReservas(String lunes, String viernes){
        List<Reserva> reservas = new ArrayList<>();
        try {
            String query = "SELECT * FROM Reserva WHERE fecha BETWEEN ? AND ?";
            PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
            pstmt.setString(1, lunes);
            pstmt.setString(2,viernes);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Reserva reserva = new Reserva();
                reserva.setId(rs.getInt("Id"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime localDateTime = LocalDateTime.parse(rs.getString("fecha"),formatter);
                reserva.setFecha(localDateTime);
                reserva.setCliente(rs.getString("cliente"));
                reserva.setActividad(rs.getInt("idActividad"));
                reserva.setEstado(rs.getInt("idEstadoReserva"));
                reserva.setEmpleado(rs.getString("empleado"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservas;
    }

    /**
     * Metodo usado para conseguir una lista con todas las actividades
     * @return devuelve una lista de actividades
     */
    public List<Actividad> listaActividades(){
        List<Actividad> actividades = new ArrayList<>();
        try {
            Statement stmt = dataSource.getConnection().createStatement();
            String query = "SELECT * FROM Actividad";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Actividad actividad = new Actividad();
                actividad.setId(rs.getInt("id"));
                actividad.setNombre(rs.getString("nombre"));
                actividad.setLimite(rs.getInt("limite"));
                actividades.add(actividad);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return actividades;
    }

    /**
     * método utilizado para realizar una reserva
     * Este método realiza un insert en la base de datos
     * @param localDateTime indica la fecha de la reserva
     * @param idReserva indica el id de la reserva
     * @param cliente indica que cliente realiza la reserva
     */
    public void realizarReserva(LocalDateTime localDateTime, int idReserva, String cliente){
        String fecha = localDateTime.toString().replace(" ", "T");
        try {
            String query = "INSERT INTO Reserva(cliente, fecha, idActividad, idEstadoReserva) VALUES (?, ?, ?, 1)";
            PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
            pstmt.setString(1, cliente);
            pstmt.setString(2, fecha);
            pstmt.setInt(3, idReserva);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método utilizado para obtener los empleados disponibles
     * Este metodo solo devolvera los empleados que pueden realizar la actividad indicada
     * @param id id de la actividad
     * @return devuelve una lista con los nombres de los empleados
     */
    public List<String> getEmpleadosDisponibles(int id){
        List<String> empleados = new ArrayList<>();
        try {
            String query = "SELECT nombre FROM Persona p INNER JOIN EmpleadoActividades e WHERE e.idActividad = ? AND p.dni = e.dniEmpleado";
            PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                empleados.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return empleados;
    }
    public static Actividad getActividadActual() {
        return actividadActual;
    }

    public static void setActividadActual(Actividad actividadActual) {
        RepositoryReservas.actividadActual = actividadActual;
    }
}
