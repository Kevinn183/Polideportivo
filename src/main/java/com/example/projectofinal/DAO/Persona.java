package com.example.projectofinal.DAO;

/**
 * @author kevin
 * @version 1
 */
public class Persona {
    /**
     * Propiedad de tipo String que indica el nombre de la persona
     */
    private String nombre;
    /**
     * Propiedad de tipo String que indica el dni de la persona
     */
    private String dni;
    /**
     * Propiedad de tipo String que indica la contraseña de la persona
     */
    private String password;

    /**
     * Constructor por defecto de la clase Persona
     */
    public Persona() {
    }

    /**
     * Constructor que recibe todas las propiedades de la clase Persona
     * @param nombre de tipo String que indica el nombre de la persona
     * @param dni de tipo String que indica el dni de la persona
     * @param password de tipo String que indica la contraseña de la persona
     */
    public Persona(String nombre, String dni, String password) {
        this.nombre = nombre;
        this.dni = dni;
        this.password = password;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
