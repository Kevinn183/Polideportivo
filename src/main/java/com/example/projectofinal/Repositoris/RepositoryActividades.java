package com.example.projectofinal.Repositoris;

import com.example.projectofinal.DAO.Actividad;
import com.example.projectofinal.MyConnector;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 * @version 1
 */
public class RepositoryActividades {
    //Crea la conexion a la base de datos
    private static DataSource dataSource = MyConnector.getMySQLDataSource();
    /**
     * Instancia de la propia clase
     */
    private static RepositoryActividades instance;

    /**
     * Constructor por defecto priavado de la clase RepositoryActividades
     */
    private RepositoryActividades(){}

    /**
     * Método para sacar la instancia de la clase aactividades
     * @return devuelve la instancia de la clase actividades
     */
    public static RepositoryActividades getInstance(){
        if (instance == null)
            instance = new RepositoryActividades();
        return instance;
    }

    /**
     * Metodo utilizado para obtener una lista de actividades
     * @return una lista con todas las actividaades de la base de datos
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
}
