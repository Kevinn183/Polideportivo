package com.example.projectofinal.DAO;

/**
 * @author kevin
 * @version 1
 */
public class Actividad {
    /**
     * Propiedad de tipo int que indica el id de la actividad
     */
    private int id;
    /**
     * Propiedad de tipo String que indica el nombre de la actividad
     */
    private String nombre;
    /**
     * Propiedad de tipo int que indica el limite de personas que hay en una actividad
     */
    private int limite;

    /**
     * Constructor con todas las propiedades de la clase Actividad
     * @param id de tipo int que indica el identificador de la actividad
     * @param nombre de tipo String queindica el nombre de la actividad
     * @param limite de tipo int que indica el limite de personas en la actividad
     */
    public Actividad(int id, String nombre, int limite) {
        this.id = id;
        this.nombre = nombre;
        this.limite = limite;
    }

    /**
     * Constructor por defecto de la clase Actividad
     */
    public Actividad() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }
    public String toString(){
        return this.nombre;
    }
}
