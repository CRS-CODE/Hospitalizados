/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author EseGamboa
 */
public class cAlta_Administrativa {

    private int id_alta_adm;
    private String fecha_ingreso_alta_adm;
    private String fecha_hora_alta_adm;
    private String rut_usuario;
    private String obs_alta_adm;
    private int id_duo;
    private int id_destino;
    private String destino_descripcion;
    private String ip_alta_adm;

    public cAlta_Administrativa() {
        this.id_alta_adm = -1;
        this.fecha_ingreso_alta_adm = "";
        this.fecha_hora_alta_adm = "";
        this.rut_usuario = "";
        this.obs_alta_adm = "";
        this.id_duo = -1;
        this.id_destino = -1;
        this.destino_descripcion = "";
        this.ip_alta_adm = "";
    }

    /**
     * @return the id_alta_adm
     */
    public int getId_alta_adm() {
        return id_alta_adm;
    }

    /**
     * @param id_alta_adm the id_alta_adm to set
     */
    public void setId_alta_adm(int id_alta_adm) {
        this.id_alta_adm = id_alta_adm;
    }

    /**
     * @return the fecha_ingreso_alta_adm
     */
    public String getFecha_ingreso_alta_adm() {
        return fecha_ingreso_alta_adm;
    }

    /**
     * @param fecha_ingreso_alta_adm the fecha_ingreso_alta_adm to set
     */
    public void setFecha_ingreso_alta_adm(String fecha_ingreso_alta_adm) {
        this.fecha_ingreso_alta_adm = fecha_ingreso_alta_adm;
    }

    /**
     * @return the fecha_hora_alta_adm
     */
    public String getFecha_hora_alta_adm() {
        return fecha_hora_alta_adm;
    }

    /**
     * @param fecha_hora_alta_adm the fecha_hora_alta_adm to set
     */
    public void setFecha_hora_alta_adm(String fecha_hora_alta_adm) {
        this.fecha_hora_alta_adm = fecha_hora_alta_adm;
    }

    /**
     * @return the rut_usuario
     */
    public String getRut_usuario() {
        return rut_usuario;
    }

    /**
     * @param rut_usuario the rut_usuario to set
     */
    public void setRut_usuario(String rut_usuario) {
        this.rut_usuario = rut_usuario;
    }

    /**
     * @return the obs_alta_adm
     */
    public String getObs_alta_adm() {
        return obs_alta_adm;
    }

    /**
     * @param obs_alta_adm the obs_alta_adm to set
     */
    public void setObs_alta_adm(String obs_alta_adm) {
        this.obs_alta_adm = obs_alta_adm;
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
     * @return the id_destino
     */
    public int getId_destino() {
        return id_destino;
    }

    /**
     * @param id_destino the id_destino to set
     */
    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
    }

    /**
     * @return the destino_descripcion
     */
    public String getDestino_descripcion() {
        return destino_descripcion;
    }

    /**
     * @param destino_descripcion the destino_descripcion to set
     */
    public void setDestino_descripcion(String destino_descripcion) {
        this.destino_descripcion = destino_descripcion;
    }

    /**
     * @return the ip_alta_adm
     */
    public String getIp_alta_adm() {
        return ip_alta_adm;
    }

    /**
     * @param ip_alta_adm the ip_alta_adm to set
     */
    public void setIp_alta_adm(String ip_alta_adm) {
        this.ip_alta_adm = ip_alta_adm;
    }
}
