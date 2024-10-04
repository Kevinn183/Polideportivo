package com.example.projectofinal.Models;

import com.example.projectofinal.Repositoris.RepositorySecurity;

import java.util.List;

/**
 * @author kevin
 * @version 1
 */
public class ModelLogin {
    /**
     * Instancia de la propia clase
     */
    private static ModelLogin instance;

    /**
     *  Constructor por defecto privado
     */
    private ModelLogin(){}

    /**
     * Metodo para obtener la instancia de la clase
     * @return devuelve la instancia de la clase
     */
    public static ModelLogin getInstance(){
        if (instance == null)
            instance = new ModelLogin();
        return instance;
    }

    /**
     * Coje la instancia de la clase RepositorySecurity
     * @see RepositorySecurity
     */
    private RepositorySecurity repositorySecurity = RepositorySecurity.getInstance();

    /**
     * Comprueba que los campos no esten vacios
     * @param usuario de tipo String
     * @param password de tipo String
     * @return devuelve un boolean dependiendo de si estan vacios o no
     */
    public boolean ComprobarCampoVacio(String usuario, String password){
        return !usuario.equals("") && !password.equals("");
    }

    /**
     * Este metodo comprueba si el usuario introducido existe
     * Para realizar la comprobacion, llama a un metodo de otra clase
     * @param usuario de tipo String
     * @param password de tipo String
     * @return un boolean dependiendo de si el usuairo existe
     * @see RepositorySecurity
     */
    public boolean usuarioExiste(String usuario, String password){
        if (repositorySecurity.usuarioExiste(usuario, password) == 1){
            System.out.println("Usuario encontrado");
            return true;
        }
        else{
            System.out.println("Usuario no encontrado");
            return false;
        }
    }

    /**
     * Este metodo comprueba si el usuario introducido es oficinista llamando a un metodo de otra clase
     * @param dni de tipo String
     * @return un boolean dependiendo de si es oficinista
     * @see RepositorySecurity
     */
    public boolean esOficinista(String dni){
        List<String> lista = repositorySecurity.getOficinistas();
        for (String persona : lista) {
            if (persona.equals(dni))
                return true;
        }
        return false;
    }
}
