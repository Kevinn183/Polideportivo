package com.example.projectofinal.Models;

import com.example.projectofinal.DAO.Actividad;
import com.example.projectofinal.DAO.Persona;
import com.example.projectofinal.Repositoris.RepositoryActividades;
import com.example.projectofinal.Repositoris.RepositoryRegistro;

import java.util.List;

/**
 * @author kevin
 * @version 1
 */
public class ModelRegistro {
    /**
     * Coje la instancia de la clase RepositoryRegistro
     * @see RepositoryRegistro
     */
    private RepositoryRegistro repositoryRegistro = RepositoryRegistro.getInstance();
    /**
     * Coje la instancia de la clase RepositoryActividades
     * @see RepositoryActividades
     */
    private RepositoryActividades repositoryActividades = RepositoryActividades.getInstance();
    /**
     * Instancia de la propia clase
     */
    private static ModelRegistro instance;

    /**
     * Constructor por defecto privado
     */
    private ModelRegistro(){
    }

    /**
     * Metodo utilizado para obtener la instancia de la clase
     * @return devuelve la instancia de la clase
     */
    public static ModelRegistro getInstance(){
        if (instance == null)
            instance = new ModelRegistro();
        return instance;
    }

    /**
     * Metodo que llama a otro para obtener una lidta de actividades
     * @return una lista de actividades
     * @see RepositoryActividades
     */
    public List<Actividad> getActividades(){
        return repositoryActividades.listaActividades();
    }

    /**
     * Metodo que comprueba que los campos no esten vacios
     * @param usuario de tipo String
     * @param dni de tipo String
     * @param password de tipo String
     * @param repetirPassword de tipo String
     * @return devuelve un boolean dependiendo de si los campos estan vacios o no
     */
    public boolean ComprobarCampoVacio(String usuario, String dni, String password, String repetirPassword){
        return !usuario.equals("") && !password.equals("") && !dni.equals("") && !repetirPassword.equals("");
    }

    /**
     * Método utilizado para comprobar si un usuario existe ya en la base de datos
     * Este metodo llama a otro de RepositoryRegistro
     * @param dni de tipo String
     * @return un boolean dependiendo de si el usuario existe o no
     * @see RepositoryRegistro
     */
    public boolean usuarioNoExiste(String dni){
        List<String> lista = repositoryRegistro.getPersonas();
        for (String persona : lista) {
            if (persona.equals(dni))
                return false;
        }
        return true;
    }

    /**
     * Este método comprueba que las 2 contraseñas sean iguales
     * @param psw1 de tipo String
     * @param psw2 de tipo String
     * @return un boolean dependiendo de si son iguales o no
     */
    public boolean passwordsIguales(String psw1, String psw2){
        return psw1.equals(psw2);
    }

    /**
     * Método que llama a otro para insertar personas en la base de datos
     * @param persona de tipo Persona
     * @see RepositoryRegistro
     */
    public void insertarUsuario(Persona persona){repositoryRegistro.insertarPersona(persona);}

    /**
     *  Método que llama a otro para insertar empleados en la base de datos
     * @param dni de tipo String
     * @param id de tipo int
     * @see RepositoryRegistro
     */
    public void insertarEmpleado(String dni, int id){repositoryRegistro.insertarEmpleado(dni, id);}
    /**
     *  Método que llama a otro para insertar empleados de actividades en la base de datos
     * @param dni de tipo String
     * @param id de tipo int
     * @see RepositoryRegistro
     */
    public void insertarEmpleadoAct(String dni, int id){repositoryRegistro.insetrarEmpleadoAct(dni, id);}

    /**
     * Comprueba que la lista que se le pasa no este vacia
     * @param actividades lista de actividades
     * @return devuelve un boolean dependiendo de si la lista esta vacia o no
     */
    public boolean listaVacia(List<Actividad> actividades){return actividades.isEmpty();}


}
