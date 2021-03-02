/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author EseGamboa
 */
public class cHistorial_Consultorio {

    private int his_id;
    private String his_paciente_rut;
    private int his_consultorio_anterior;
    private int his_consultorio_nuevo;
    private String his_fecha;
    private String his_usuario;
    private String his_ip;
    private String his_consultorio_anterior_descripcion;
    private String his_consultorio_nuevo_descripcion;

    public cHistorial_Consultorio() {
        this.his_id = 0;
        this.his_paciente_rut ="";
        this.his_consultorio_anterior = 0;
        this.his_consultorio_nuevo = 0;
        this.his_fecha ="";
        this.his_usuario ="";
        this.his_ip ="";
        this.his_consultorio_anterior_descripcion ="";
        this.his_consultorio_nuevo_descripcion ="";
    }

    /**
     * @return the his_id
     */
    public int getHis_id() {
        return his_id;
    }

    /**
     * @param his_id the his_id to set
     */
    public void setHis_id(int his_id) {
        this.his_id = his_id;
    }

    /**
     * @return the his_paciente_rut
     */
    public String getHis_paciente_rut() {
        return his_paciente_rut;
    }

    /**
     * @param his_paciente_rut the his_paciente_rut to set
     */
    public void setHis_paciente_rut(String his_paciente_rut) {
        this.his_paciente_rut = his_paciente_rut;
    }

    /**
     * @return the his_consultorio_anterior
     */
    public int getHis_consultorio_anterior() {
        return his_consultorio_anterior;
    }

    /**
     * @param his_consultorio_anterior the his_consultorio_anterior to set
     */
    public void setHis_consultorio_anterior(int his_consultorio_anterior) {
        this.his_consultorio_anterior = his_consultorio_anterior;
    }

    /**
     * @return the his_consultorio_nuevo
     */
    public int getHis_consultorio_nuevo() {
        return his_consultorio_nuevo;
    }

    /**
     * @param his_consultorio_nuevo the his_consultorio_nuevo to set
     */
    public void setHis_consultorio_nuevo(int his_consultorio_nuevo) {
        this.his_consultorio_nuevo = his_consultorio_nuevo;
    }

    /**
     * @return the his_fecha
     */
    public String getHis_fecha() {
        return his_fecha;
    }

    /**
     * @param his_fecha the his_fecha to set
     */
    public void setHis_fecha(String his_fecha) {
        this.his_fecha = his_fecha;
    }

    /**
     * @return the his_usuario
     */
    public String getHis_usuario() {
        return his_usuario;
    }

    /**
     * @param his_usuario the his_usuario to set
     */
    public void setHis_usuario(String his_usuario) {
        this.his_usuario = his_usuario;
    }

    /**
     * @return the his_ip
     */
    public String getHis_ip() {
        return his_ip;
    }

    /**
     * @param his_ip the his_ip to set
     */
    public void setHis_ip(String his_ip) {
        this.his_ip = his_ip;
    }

    /**
     * @return the his_consultorio_anterior_descripcion
     */
    public String getHis_consultorio_anterior_descripcion() {
        return his_consultorio_anterior_descripcion;
    }

    /**
     * @param his_consultorio_anterior_descripcion the his_consultorio_anterior_descripcion to set
     */
    public void setHis_consultorio_anterior_descripcion(String his_consultorio_anterior_descripcion) {
        this.his_consultorio_anterior_descripcion = his_consultorio_anterior_descripcion;
    }

    /**
     * @return the his_consultorio_nuevo_descripcion
     */
    public String getHis_consultorio_nuevo_descripcion() {
        return his_consultorio_nuevo_descripcion;
    }

    /**
     * @param his_consultorio_nuevo_descripcion the his_consultorio_nuevo_descripcion to set
     */
    public void setHis_consultorio_nuevo_descripcion(String his_consultorio_nuevo_descripcion) {
        this.his_consultorio_nuevo_descripcion = his_consultorio_nuevo_descripcion;
    }



   
    


}
