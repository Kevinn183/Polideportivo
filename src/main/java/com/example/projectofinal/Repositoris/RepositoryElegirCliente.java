package com.example.projectofinal.Repositoris;

import com.example.projectofinal.MyConnector;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositoryElegirCliente {
    /**
     * Instancia de la propia clase
     */
    private static RepositoryElegirCliente instance;

    /**
     * Constructor por defecto privado de la clase RepositoryElegirCliente
     */
    private RepositoryElegirCliente(){};

    /**
     * Método utilizado para conseguir la instancia de la clase RepositoryElegirCliente
     * @return devuelve la instancia de la clase RepositoryElegirCliente
     */
    public static RepositoryElegirCliente getInstance(){
        if (instance == null)
            instance = new RepositoryElegirCliente();
        return instance;
    }
    //Crea la conexion con la base de datos
    private static DataSource dataSource = MyConnector.getMySQLDataSource();

    /**
     * Método utilizado para ver si un usuario existe
     * @param usuario se le pasa un usuario
     * @return devuelve un numero que se usara en otro método para realizar comprobaciones
     */
    public int verExiste(String usuario){
        int num = 0;

        try {
            String query = "SELECT COUNT(*) as num FROM Persona WHERE dni = ?";
            PreparedStatement pstmt =  dataSource.getConnection().prepareStatement(query);
            pstmt.setString(1, usuario);
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
}
