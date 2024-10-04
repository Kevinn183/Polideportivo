package com.example.projectofinal.Models;

import com.example.projectofinal.Repositoris.RepositoryElegirCliente;

/**
 * @author kevin
 * @version 1
 */
public class ModelElegirCliente {
    /**
     * instancia de la clase
     */
    private static ModelElegirCliente instance;

    /**
     * Constructor porr defecto privado de la clase
     */
    private ModelElegirCliente(){}

    /**
     * Metodo que permite obtener la instancia de la clase
     * @return una instancia de la clase
     */
    public static ModelElegirCliente getInstance(){
        if (instance == null)
            instance = new ModelElegirCliente();
        return instance;
    }

    /**
     * Coje la instancia de la clase RepositoryElegirCliente
     * @see RepositoryElegirCliente
     */
        RepositoryElegirCliente repositoryElegirCliente = RepositoryElegirCliente.getInstance();

    /**
     * CXomprueba si existe el usuario llamando a un método de otra clase
     * @param usuario de tipo String
     * @return devuelve un boolean dependiendo de si existe o no
     * @see RepositoryElegirCliente
     */
    public boolean verExiste(String usuario){
        if (repositoryElegirCliente.verExiste(usuario) == 1){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Este método comprueba que los parametros que recibe no esten vacios
     * @param usuario de tipo String
     * @return un boolean dependiendo de si esta vacio o no
     */
    public boolean ComprobarVacio(String usuario){
        return !usuario.equals("");
    }
}
