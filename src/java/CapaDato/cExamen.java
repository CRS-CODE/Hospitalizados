/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author Silvio
 */
public class cExamen extends cUsuario {
// tambien utilizado para radiografias.

    private int id_examen;
    private String descripcion;
    private int tipo;
    private String tipo_descripcion;
    private int estado;
    private String estado_descripcion;
    private String fecha_ingreso;
    private String fecha_corta;
    private int orden;
    // anothers
    private int id_das;
    private int id_das_examen;

    public cExamen() {
        this.id_examen = -1;
        this.descripcion = "";
        this.tipo = -1;
        this.tipo_descripcion = "";
        this.estado = -1;
        this.estado_descripcion = "";
        this.fecha_ingreso = "";
        this.orden = -1;
        this.id_das = -1;
        this.id_das_examen = -1;
        fecha_corta="";

    }

    public cExamen(int id_das) {
        this.id_das = id_das;
    }

    /**
     * @return the id_examen
     */
    public int getId_examen() {
        return id_examen;
    }

    /**
     * @param id_examen the id_examen to set
     */
    public void setId_examen(int id_examen) {
        this.id_examen = id_examen;
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
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    /**
     * @return the id_das
     */
    public int getId_das() {
        return id_das;
    }

    /**
     * @param id_das the id_das to set
     */
    public void setId_das(int id_das) {
        this.id_das = id_das;
    }

    /**
     * @return the id_das_examen
     */
    public int getId_das_examen() {
        return id_das_examen;
    }

    /**
     * @param id_das_examen the id_das_examen to set
     */
    public void setId_das_examen(int id_das_examen) {
        this.id_das_examen = id_das_examen;
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
