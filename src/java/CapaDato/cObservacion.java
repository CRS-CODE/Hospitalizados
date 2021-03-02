/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author EseGamboa
 */
public class cObservacion extends cUsuario {

    private int id_obs;
    private int das_id;
    private String fecha_ingreso;
    private int estado;
    private String ip;
    private int espera_radiografia;
    private int espera_ex_laboratorio;
    private String espera_radiografia_descri;
    private String espera_ex_laboratorio_descri;
    private String observacion_detalle;

    private String fecha_corta;

    public cObservacion() {
        this.id_obs = -1;
        this.das_id =-1;
        this.fecha_ingreso = "";
        this.estado = -1;
        this.ip = "";
        this.espera_radiografia = -1;
        this.espera_ex_laboratorio = -1;
        this.espera_radiografia_descri = "";
        this.espera_ex_laboratorio_descri = "";
        this.observacion_detalle = "";
        fecha_corta="";
    }




    /**
     * @return the id_obs
     */
    public int getId_obs() {
        return id_obs;
    }

    /**
     * @param id_obs the id_obs to set
     */
    public void setId_obs(int id_obs) {
        this.id_obs = id_obs;
    }

    /**
     * @return the das_id
     */
    public int getDas_id() {
        return das_id;
    }

    /**
     * @param das_id the das_id to set
     */
    public void setDas_id(int das_id) {
        this.das_id = das_id;
    }

    /**
     * @return the fecha_ingreso
     */
    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    /**
     * @param fecha_ingreso the fecha_ingreso to set
     */
    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the espera_radiografia
     */
    public int getEspera_radiografia() {
        return espera_radiografia;
    }

    /**
     * @param espera_radiografia the espera_radiografia to set
     */
    public void setEspera_radiografia(int espera_radiografia) {
        this.espera_radiografia = espera_radiografia;
    }

    /**
     * @return the espera_ex_laboratorio
     */
    public int getEspera_ex_laboratorio() {
        return espera_ex_laboratorio;
    }

    /**
     * @param espera_ex_laboratorio the espera_ex_laboratorio to set
     */
    public void setEspera_ex_laboratorio(int espera_ex_laboratorio) {
        this.espera_ex_laboratorio = espera_ex_laboratorio;
    }

    /**
     * @return the espera_radiografia_descri
     */
    public String getEspera_radiografia_descri() {
        return espera_radiografia_descri;
    }

    /**
     * @param espera_radiografia_descri the espera_radiografia_descri to set
     */
    public void setEspera_radiografia_descri(String espera_radiografia_descri) {
        this.espera_radiografia_descri = espera_radiografia_descri;
    }

    /**
     * @return the espera_ex_laboratorio_descri
     */
    public String getEspera_ex_laboratorio_descri() {
        return espera_ex_laboratorio_descri;
    }

    /**
     * @param espera_ex_laboratorio_descri the espera_ex_laboratorio_descri to set
     */
    public void setEspera_ex_laboratorio_descri(String espera_ex_laboratorio_descri) {
        this.espera_ex_laboratorio_descri = espera_ex_laboratorio_descri;
    }

    /**
     * @return the observacion_detalle
     */
    public String getObservacion_detalle() {
        return observacion_detalle;
    }

    /**
     * @param observacion_detalle the observacion_detalle to set
     */
    public void setObservacion_detalle(String observacion_detalle) {
        this.observacion_detalle = observacion_detalle;
    }

    /**
     * @return the fecha_corta
     */
    public String getFecha_corta() {
        return fecha_corta;
    }

    /**
     * @param fecha_corta the fecha_corta to set
     */
    public void setFecha_corta(String fecha_corta) {
        this.fecha_corta = fecha_corta;
    }


    

}
