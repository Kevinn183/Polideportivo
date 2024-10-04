package com.example.projectofinal.Repositoris;

import com.example.projectofinal.DAO.Persona;
import com.example.projectofinal.MyConnector;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RepositoryRegistro {
    //Crea la conexion con la base de datos
    private static DataSource dataSource = MyConnector.getMySQLDataSource();
    /**
     * Instancia de la propia clase
     */
    private static RepositoryRegistro instance;

    /**
     * Constructor por defecto privado de la clase RepositoryRegistro
     */
    private RepositoryRegistro(){
        /**
         * Método utilizado para conseguir la instancia de la clase RepositoryRegistro
         */
    }
    public static RepositoryRegistro getInstance(){
        if (instance == null){
            instance = new RepositoryRegistro();
        }
        return instance;
    }

    /**
     * Método usado para conseguir una lista con todas las personas de la base de datos
     * @return devuelve una lista de personas
     */
    public List<String> getPersonas(){
        List<String> personas = new ArrayList<>();
        try {
            Statement stmt = dataSource.getConnection().createStatement();
            String query = "SELECT dni FROM Persona";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                personas.add(rs.getString("dni"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personas;
    }

    /**
     * Método utilizado para insertar una persona en la base de datos
     * @param persona se le pasa la persona que se quiere insertar
     */
    public void insertarPersona(Persona persona){
        try {
            String query = "INSERT INTO Persona(nombre, dni, password) VALUES (?, ?, ?)";
            PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
            pstmt.setString(1, persona.getNombre());
            pstmt.setString(2, persona.getDni());
            pstmt.setString(3, persona.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método utilizado para insertar un empleado en la base de datos
     * @param dni Se le pasa el dni del empleado
     * @param id se le pasa el id que indica el tipo de empleado
     */
    public void insertarEmpleado(String dni, int id){
        try {
            String query = "INSERT INTO Empleado(dni, idTipoEmpleado) VALUES (?, ?)";
            PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
            pstmt.setString(1,dni);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo utilizado para insertar un empleado de actividades
     * @param dni indica el dni del empleado
     * @param id indica el id de la actividad a la que se le puede asignar
     */
    public void insetrarEmpleadoAct(String dni, int id){
        try {
            String query = "INSERT INTO EmpleadoActividades(dniEmpleado, idActividad) VALUES (?, ?)";
            PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
            pstmt.setString(1,dni);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
