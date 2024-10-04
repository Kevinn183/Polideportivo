package com.example.projectofinal.Repositoris;


import com.example.projectofinal.MyConnector;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 * @version 1
 */
public class RepositorySecurity {
    /**
     * Instancia de la propia clase
     */
    private static RepositorySecurity instance;

    /**
     * Constructor por defecto privado de la clase RepositorySecurity
     */
    private RepositorySecurity(){
    }

    /**
     * Metodo usado para conseguir la instancia de la clase RepositorySecurity
     * @return
     */
    public static RepositorySecurity getInstance(){
        if (instance == null){
            instance = new RepositorySecurity();
        }
        return instance;
    }
    //Crea la conexión con el conector
    private static DataSource dataSource = MyConnector.getMySQLDataSource();

    /**
     * Metodo utilizado para comprobar si el usuario introducido en el login existe
     * Este método realiza una consulta y, dependiendo del resultado devuelve un número o otro el cual se usara en otro método
     * @param usuario de tipo String indica el usuario introducido
     * @param password de tipo String indica la contraseña introducida
     * @return un número que indica si el usuario existe o no
     */
    public int usuarioExiste(String usuario, String password){
        int num = 0;
        try {
            String query = "SELECT COUNT(*) as num FROM Persona WHERE dni = ? AND password =?";
            PreparedStatement pstmt =  dataSource.getConnection().prepareStatement(query);
            pstmt.setString(1, usuario);
            pstmt.setString(2, password);
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
     * Métdo utilizado para sacar una lista de oficinistas
     * La lista se usará para ver si el usuario que intenta acceder es oficinista
     * @return una lista de oficinistas o administradores
     */
    public List<String> getOficinistas(){
        List<String> oficinistas = new ArrayList<>();
        try {
            Statement stmt = dataSource.getConnection().createStatement();
            String query = "SELECT dni FROM Empleado WHERE idTipoEmpleado < 3";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                oficinistas.add(rs.getString("dni"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return oficinistas;
    }
}
