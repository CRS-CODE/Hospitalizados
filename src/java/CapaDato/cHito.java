/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author EseGamboa
 */
public class cHito extends cPaciente {

    private int id;
    private int tipo;
    private String fecha;
    private String detalle;
    private int estado;
    private String ip;
    private String usuario_rut;
    private String usuario_nombre;
    private String usuario_apellidop;
    private String usuario_apellidom;

    private String estado_descripcion;
    private String tipo_descripcion;

    public cHito() {
        this.id = 0;
        this.tipo = 0;
        this.fecha = "";
        this.detalle = "";
        this.estado = 0;
        this.ip = "";
        this.usuario_rut = "";
        this.usuario_nombre = "";
        this.usuario_apellidop = "";
        this.usuario_apellidom = "";
        this.estado_descripcion = "";
        this.tipo_descripcion = "";
    }



    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
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
     * @return the usuario_rut
     */
    public String getUsuario_rut() {
        return usuario_rut;
    }

    /**
     * @param usuario_rut the usuario_rut to set
     */
    public void setUsuario_rut(String usuario_rut) {
        this.usuario_rut = usuario_rut;
    }

    /**
     * @return the usuario_nombre
     */
    public String getUsuario_nombre() {
        return usuario_nombre;
    }

    /**
     * @param usuario_nombre the usuario_nombre to set
     */
    public void setUsuario_nombre(String usuario_nombre) {
        this.usuario_nombre = usuario_nombre;
    }

    /**
     * @return the usuario_apellidop
     */
    public String getUsuario_apellidop() {
        return usuario_apellidop;
    }

    /**
     * @param usuario_apellidop the usuario_apellidop to set
     */
    public void setUsuario_apellidop(String usuario_apellidop) {
        this.usuario_apellidop = usuario_apellidop;
    }

    /**
     * @return the usuario_apellidom
     */
    public String getUsuario_apellidom() {
        return usuario_apellidom;
    }

    /**
     * @param usuario_apellidom the usuario_apellidom to set
     */
    public void setUsuario_apellidom(String usuario_apellidom) {
        this.usuario_apellidom = usuario_apellidom;
    }

    /**
     * @return the estado_descripcion
     */
    public String getEstado_descripcion() {
        return estado_descripcion;
    }

    /**
     * @param estado_descripcion the estado_descripcion to set
     */
    public void setEstado_descripcion(String estado_descripcion) {
        this.estado_descripcion = estado_descripcion;
    }

    /**
     * @return the tipo_descripcion
     */
    public String getTipo_descripcion() {
        return tipo_descripcion;
    }

    /**
     * @param tipo_descripcion the tipo_descripcion to set
     */
    public void setTipo_descripcion(String tipo_descripcion) {
        this.tipo_descripcion = tipo_descripcion;
    }

    
    


}
