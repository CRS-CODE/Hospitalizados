/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author Informatica
 */
public class cSesionKine extends cUsuario {

    private int id_sesion_kine;
    private int id_duo;
    private String detalle;
    private String fecha_hora;
    private String fecha;
    private String hora;

    private String fecha_ingreso_sesion;
    private String hora_ingreso_sesion;
    private int estado_sesion;
    private String estado_desc_sesion;

    public cSesionKine() {
        this.id_sesion_kine = 0;
        this.id_duo = 0;
        detalle = "";
        fecha_hora = "";

        fecha_ingreso_sesion = "";
        hora_ingreso_sesion = "";
        estado_sesion = 0;
        estado_desc_sesion = "";
    }

    /**
     * @return the id_sesion_kine
     */
    public int getId_sesion_kine() {
        return id_sesion_kine;
    }

    /**
     * @param id_sesion_kine the id_sesion_kine to set
     */
    public void setId_sesion_kine(int id_sesion_kine) {
        this.id_sesion_kine = id_sesion_kine;
    }

    /**
     * @return the id_duo
     */
    public int getId_duo() {
        return id_duo;
    }

    /**
     * @param id_duo the id_duo to set
     */
    public void setId_duo(int id_duo) {
        this.id_duo = id_duo;
    }

    /**
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * @return the fecha_hora
     */
    public String getFecha_hora() {
        return fecha_hora;
    }

    /**
     * @param fecha_hora the fecha_hora to set
     */
    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    /**
     * @return the fecha_ingreso_sesion
     */
    public String getFecha_ingreso_sesion() {
        return fecha_ingreso_sesion;
    }

    /**
     * @param fecha_ingreso_sesion the fecha_ingreso_sesion to set
     */
    public void setFecha_ingreso_sesion(String fecha_ingreso_sesion) {
        this.fecha_ingreso_sesion = fecha_ingreso_sesion;
    }

    /**
     * @return the hora_ingreso_sesion
     */
    public String getHora_ingreso_sesion() {
        return hora_ingreso_sesion;
    }

    /**
     * @param hora_ingreso_sesion the hora_ingreso_sesion to set
     */
    public void setHora_ingreso_sesion(String hora_ingreso_sesion) {
        this.hora_ingreso_sesion = hora_ingreso_sesion;
    }

    /**
     * @return the estado_sesion
     */
    public int getEstado_sesion() {
        return estado_sesion;
    }

    /**
     * @param estado_sesion the estado_sesion to set
     */
    public void setEstado_sesion(int estado_sesion) {
        this.estado_sesion = estado_sesion;
    }

    /**
     * @return the estado_desc_sesion
     */
    public String getEstado_desc_sesion() {
        return estado_desc_sesion;
    }

    /**
     * @param estado_desc_sesion the estado_desc_sesion to set
     */
    public void setEstado_desc_sesion(String estado_desc_sesion) {
        this.estado_desc_sesion = estado_desc_sesion;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

}
