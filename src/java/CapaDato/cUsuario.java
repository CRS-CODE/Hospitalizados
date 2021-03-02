/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author Dis
 */
public class cUsuario {

    private String nombre_usuario;
    private String apellidop_usuario;
    private String apellidom_usuario;
    private String rut_usuario;
    private String pass_usuario;
    private int perfil_usuario;
    private String perfil_descripcion_usuario;
    private int estado_usuario;

    private String rut_usuario_anula;
    private String nombre_usuario_anula;
    private String apellidop_usuario_anula;
    private String apellidom_usuario_anula;

    private String fecha_anula;
    private String motivo_anula;
    


    public cUsuario() {
        this.nombre_usuario = "";
        this.apellidop_usuario = "";
        this.apellidom_usuario = "";
        this.rut_usuario = "";
        this.pass_usuario = "";
        this.perfil_usuario = -1;
        perfil_descripcion_usuario = "";
        estado_usuario = 0;

        this.nombre_usuario_anula = "";
        this.apellidop_usuario_anula = "";
        this.apellidom_usuario_anula = "";
        this.rut_usuario_anula = "";
        fecha_anula = "";
        motivo_anula = "";
        
    }

    /**
     * @return the nombre_usuario
     */
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    /**
     * @param nombre_usuario the nombre_usuario to set
     */
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    /**
     * @return the apellidop_usuario
     */
    public String getApellidop_usuario() {
        return apellidop_usuario;
    }

    /**
     * @param apellidop_usuario the apellidop_usuario to set
     */
    public void setApellidop_usuario(String apellidop_usuario) {
        this.apellidop_usuario = apellidop_usuario;
    }

    /**
     * @return the apellidom_usuario
     */
    public String getApellidom_usuario() {
        return apellidom_usuario;
    }

    /**
     * @param apellidom_usuario the apellidom_usuario to set
     */
    public void setApellidom_usuario(String apellidom_usuario) {
        this.apellidom_usuario = apellidom_usuario;
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
     * @return the pass_usuario
     */
    public String getPass_usuario() {
        return pass_usuario;
    }

    /**
     * @param pass_usuario the pass_usuario to set
     */
    public void setPass_usuario(String pass_usuario) {
        this.pass_usuario = pass_usuario;
    }

    /**
     * @return the perfil_usuario
     */
    public int getPerfil_usuario() {
        return perfil_usuario;
    }

    /**
     * @param perfil_usuario the perfil_usuario to set
     */
    public void setPerfil_usuario(int perfil_usuario) {
        this.perfil_usuario = perfil_usuario;
    }

    /**
     * @return the perfil_descripcion_usuario
     */
    public String getPerfil_descripcion_usuario() {
        return perfil_descripcion_usuario;
    }

    /**
     * @param perfil_descripcion_usuario the perfil_descripcion_usuario to set
     */
    public void setPerfil_descripcion_usuario(String perfil_descripcion_usuario) {
        this.perfil_descripcion_usuario = perfil_descripcion_usuario;
    }

    /**
     * @return the estado_usuario
     */
    public int getEstado_usuario() {
        return estado_usuario;
    }

    /**
     * @param estado_usuario the estado_usuario to set
     */
    public void setEstado_usuario(int estado_usuario) {
        this.estado_usuario = estado_usuario;
    }

    /**
     * @return the rut_usuario_anula
     */
    public String getRut_usuario_anula() {
        return rut_usuario_anula;
    }

    /**
     * @param rut_usuario_anula the rut_usuario_anula to set
     */
    public void setRut_usuario_anula(String rut_usuario_anula) {
        this.rut_usuario_anula = rut_usuario_anula;
    }

    /**
     * @return the nombre_usuario_anula
     */
    public String getNombre_usuario_anula() {
        return nombre_usuario_anula;
    }

    /**
     * @param nombre_usuario_anula the nombre_usuario_anula to set
     */
    public void setNombre_usuario_anula(String nombre_usuario_anula) {
        this.nombre_usuario_anula = nombre_usuario_anula;
    }

    /**
     * @return the apellidop_usuario_anula
     */
    public String getApellidop_usuario_anula() {
        return apellidop_usuario_anula;
    }

    /**
     * @param apellidop_usuario_anula the apellidop_usuario_anula to set
     */
    public void setApellidop_usuario_anula(String apellidop_usuario_anula) {
        this.apellidop_usuario_anula = apellidop_usuario_anula;
    }

    /**
     * @return the apellidom_usuario_anula
     */
    public String getApellidom_usuario_anula() {
        return apellidom_usuario_anula;
    }

    /**
     * @param apellidom_usuario_anula the apellidom_usuario_anula to set
     */
    public void setApellidom_usuario_anula(String apellidom_usuario_anula) {
        this.apellidom_usuario_anula = apellidom_usuario_anula;
    }

    /**
     * @return the fecha_anula
     */
    public String getFecha_anula() {
        return fecha_anula;
    }

    /**
     * @param fecha_anula the fecha_anula to set
     */
    public void setFecha_anula(String fecha_anula) {
        this.fecha_anula = fecha_anula;
    }

    /**
     * @return the motivo_anula
     */
    public String getMotivo_anula() {
        return motivo_anula;
    }

    /**
     * @param motivo_anula the motivo_anula to set
     */
    public void setMotivo_anula(String motivo_anula) {
        this.motivo_anula = motivo_anula;
    }

}
