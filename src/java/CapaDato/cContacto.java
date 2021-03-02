/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDato;

/**
 *
 * @author Silvio
 */
public class cContacto extends cUsuario {

  private int id_contacto;
  private int das_id;
  private String fecha_ingreso;
  private int estado;
  private String ip;
  private String nombre;
  private String fecha;
  private String hora;
  private String observacion;

  private String fecha_ingreso_corta;
  private String fecha_corta;

    public cContacto() {
        this.id_contacto = 1;
        this.das_id = 1;
        this.fecha_ingreso = "";
        this.estado = 1;
        this.ip = "";
        this.nombre = "";
        this.fecha = "";
        this.hora = "";
        this.observacion = "";
        
        fecha_ingreso_corta="";
        fecha_corta="";


    }

    /**
     * @return the id_contacto
     */
    public int getId_contacto() {
        return id_contacto;
    }

    /**
     * @param id_contacto the id_contacto to set
     */
    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the fecha_ingreso_corta
     */
    public String getFecha_ingreso_corta() {
        return fecha_ingreso_corta;
    }

    /**
     * @param fecha_ingreso_corta the fecha_ingreso_corta to set
     */
    public void setFecha_ingreso_corta(String fecha_ingreso_corta) {
        this.fecha_ingreso_corta = fecha_ingreso_corta;
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
