/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author EseGamboa
 */
public class cConsultorio extends cUsuario{

    private int id;
    private String descripcion;
    private int IndicacionEgreso;
    private int Alta;
    private int estado;
    private int estado_crd;


    

    public cConsultorio() {
        this.id = -1;
        this.descripcion = "";
        this.IndicacionEgreso = -1;
        this.Alta = -1;
        this.estado = -1;
        this.estado_crd = -1;
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
     * @return the estado_crd
     */
    public int getEstado_crd() {
        return estado_crd;
    }

    /**
     * @param estado_crd the estado_crd to set
     */
    public void setEstado_crd(int estado_crd) {
        this.estado_crd = estado_crd;
    }

    public int getIndicacionEgreso() {
        return IndicacionEgreso;
    }

    public void setIndicacionEgreso(int IndicacionEgreso) {
        this.IndicacionEgreso = IndicacionEgreso;
    }

    public int getAlta() {
        return Alta;
    }

    public void setAlta(int Alta) {
        this.Alta = Alta;
    }
                
}
