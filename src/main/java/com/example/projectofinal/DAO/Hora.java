package com.example.projectofinal.DAO;

/**
 * @author kevin
 * @version 1
 */
public class Hora {
    /**
     * Propiedad de tipo int que indica el id que tiene una hora
     */
    private int id;
    /**
     * Propiedad de tipo String que indica al hora
     */
    private String hora;

    /**
     * Constructor por defecto de la clase Hora
     */

    public Hora() {
    }

    /**
     * Constructor que recibe todos los parametros de la clase Hora
     * @param id de tipo int que indica el id de la hora
     * @param hora de tipo String que indica la hora
     */
    public Hora(int id, String hora) {
        this.id = id;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

}
