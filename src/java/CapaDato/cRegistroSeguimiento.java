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
public class cRegistroSeguimiento extends cUsuario {

    private int id_seguimiento;
    private int id_registro_id;
    private String fecha_ingreso;
    private String fecha_seguimiento;
    private String descripcion;
    private int estado;
    private String estado_desc;

    public cRegistroSeguimiento() {
        this.id_seguimiento = 0;
        this.id_registro_id = 0;
        this.fecha_ingreso = "";
        this.fecha_seguimiento = "";
        this.descripcion = "";
        this.estado = 0;
        this.estado_desc = "";
    }

    /**
     * @return the id_seguimiento
     */
    public int getId_seguimiento() {
        return id_seguimiento;
    }

    /**
     * @param id_seguimiento the id_seguimiento to set
     */
    public void setId_seguimiento(int id_seguimiento) {
        this.id_seguimiento = id_seguimiento;
    }

    /**
     * @return the id_registro_id
     */
    public int getId_registro_id() {
        return id_registro_id;
    }

    /**
     * @param id_registro_id the id_registro_id to set
     */
    public void setId_registro_id(int id_registro_id) {
        this.id_registro_id = id_registro_id;
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
     * @return the fecha_seguimiento
     */
    public String getFecha_seguimiento() {
        return fecha_seguimiento;
    }

    /**
     * @param fecha_seguimiento the fecha_seguimiento to set
     */
    public void setFecha_seguimiento(String fecha_seguimiento) {
        this.fecha_seguimiento = fecha_seguimiento;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
     * @return the estado_desc
     */
    public String getEstado_desc() {
        return estado_desc;
    }

    /**
     * @param estado_desc the estado_desc to set
     */
    public void setEstado_desc(String estado_desc) {
        this.estado_desc = estado_desc;
    }
    

}
