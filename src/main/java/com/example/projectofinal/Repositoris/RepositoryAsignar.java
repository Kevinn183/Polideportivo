package com.example.projectofinal.Repositoris;

import com.example.projectofinal.DAO.Actividad;
import com.example.projectofinal.DAO.Reserva;
import com.example.projectofinal.MyConnector;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RepositoryAsignar {
    /**
     * Instancia de la propia clase
     */
    private static RepositoryAsignar instance;

    /**
     * Constructor por defecto privado de la clase RepositoryAsignar
     */
    private RepositoryAsignar(){

    }

    /**
     * Método utilizado para conseguir la instancia de la clase RepositoryAsignar
     * @return devuelve la instancia de la clase
     */
    public static RepositoryAsignar getInstance(){
        if (instance == null){
            instance = new RepositoryAsignar();
        }
        return instance;
    }
    //Crea la conexion a la base de datos
    private static DataSource dataSource = MyConnector.getMySQLDataSource();

    /**
     * Método que devuelve una lista de reservas cuando su estado sea 1 y el id de la actividad sea el indicado
     * @param id identificador de la actividad
     * @return devuelve una lista de reservas
     */
    public List<Reserva> getReservas(int id){
        List<Reserva> reservas = new ArrayList<>();
        try {
            String query = "SELECT * FROM Reserva WHERE idActividad = ? AND idEstadoReserva = 1";
            PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
            pstmt.setInt(1, id);
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
     * Devuelve las reservas que ya tengan un empleado asignado
     * @param id identificador de la actividad
     * @param empleado indica el empleado asignado a la actividad
     * @return devuelve una lista de reservas
     */
    public List<Reserva> getReservasAsignadas(int id, String empleado){
        List<Reserva> reservas = new ArrayList<>();
        try {
            String query = "SELECT * FROM Reserva WHERE idActividad = ? AND empleado = ?";
            PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.setString(2, empleado);
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
     * Metodo utilizado para comporbar si el empleado tiene una actividad asignada en una fecha determinada
     * @param localDateTime indica la fecha de la reserva
     * @param empleado indica el empleado asignado
     * @return devuelve un numero que serña utilizado por otro metodo para realizar comprobaciones
     */
    public int comprobarHora(LocalDateTime localDateTime, String empleado){
        int num = 0;
        String fecha = localDateTime.toString().replace(" ", "T");
        try {
            String query = "SELECT COUNT(*) as num FROM Reserva WHERE Reserva.fecha =? AND Reserva.empleado =?";
            PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
            pstmt.setString(1,fecha);
            pstmt.setString(2, empleado);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                num = rs.getInt("num");
                return num;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return num;
    }

    /**
     * Metodo utilizado para conseguir el dni de un empleado a partir de su nombre
     * @param nombre nombre del empleado
     * @return un string con el dni de empleado
     */
    public String getEmpleado(String nombre){
        String dni = "";
        try {
            String query = "SELECT dni FROM Persona WHERE nombre = ?";
            PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                dni = rs.getString("dni");
                return dni;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dni;
    }

    /**
     * Metodo utilizado para anyadir un empleado a una actividad
     * @param empleado indica el empleado a asignar
     * @param id indica el id de la actividad
     * @param localDateTime indica la fecha de la reserva
     */
    public void anyadirEmpleado(String empleado,int id, LocalDateTime localDateTime){
        String fecha = localDateTime.toString().replace(" ", "T");
        try {
            String query = "UPDATE Reserva SET empleado = ? WHERE idActividad = ? AND fecha = ?";
            PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
            pstmt.setString(1, empleado);
            pstmt.setInt(2, id);
            pstmt.setString(3, fecha);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método utilizado para cambiar el estado de una actividad
     * @param estado de tipo int indica el estado de la actividad
     * @param id indica el id de la actividad
     * @param localDateTime indica la fecha de la reserva
     */
    public void camhbiarEstado(int estado,int id, LocalDateTime localDateTime){
        String fecha = localDateTime.toString().replace(" ", "T");
        try {
            String query = "UPDATE Reserva SET idEstadoReserva = ? WHERE idActividad = ? AND fecha = ?";
            PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
            pstmt.setInt(1, estado);
            pstmt.setInt(2, id);
            pstmt.setString(3, fecha);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
