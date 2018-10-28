/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author paco
 */
public class Ave implements Serializable{
    private String anilla;
    private String especie;
    private String lugar;
    private Date fecha; //NO SE USA POR AHORA
    private String fechaString;

    /**
     * @return the anilla
     */
    public String getAnilla() {
        return anilla;
    }

    /**
     * @param anilla the anilla to set
     */
    public void setAnilla(String anilla) {
        this.anilla = anilla;
    }

    /**
     * @return the especie
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * @param especie the especie to set
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /**
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the fechaString
     */
    public String getFechaString() {
        return fechaString;
    }

    /**
     * @param fechaString the fechaString to set
     */
    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
    }
    
    
}
