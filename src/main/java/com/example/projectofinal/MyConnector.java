package com.example.projectofinal;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author kevin
 * @version 1
 */
public class MyConnector {

    private static MyConnector instance;

    /**
     * Constructor por defecto privado de la clase MyConnector
     */
    private MyConnector(){
    }
    private static final String ruta_fichero = "E:\\PRG\\ProjectoFinal\\src\\main\\resources\\db.properties";

    /**
     * Metodo usado para llamar a la instancia de la clase MyConnector
     * @return devuelve una instancia de la clase MyConnector
     */
    public static MyConnector getInstance(){
        if (instance == null)
            instance = new MyConnector();
        return instance;
    }

    /**
     * Este método se usa para generar la conexión con la base de datos
     * @return devuelve la conexión con la base de datos
     */
    public static DataSource getMySQLDataSource() {
        Properties props = new Properties();
        MysqlDataSource mysqlDS = null;
        try (FileInputStream fis = new FileInputStream(ruta_fichero)) {
            props.load(fis);
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
            mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mysqlDS;
    }
}
