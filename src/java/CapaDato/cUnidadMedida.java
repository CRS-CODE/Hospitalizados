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
public class cUnidadMedida {

    private int id_unidad_medida;
    private String descripcion;
    private String abreviacion;

    public cUnidadMedida() {
        this.id_unidad_medida = 0;
        this.descripcion = "";
        this.abreviacion = "";
    }

    /**
     * @return the id_unidad_medida
     */
    public int getId_unidad_medida() {
        return id_unidad_medida;
    }

    /**
     * @param id_unidad_medida the id_unidad_medida to set
     */
    public void setId_unidad_medida(int id_unidad_medida) {
        this.id_unidad_medida = id_unidad_medida;
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
     * @return the abreviacion
     */
    public String getAbreviacion() {
        return abreviacion;
    }

    /**
     * @param abreviacion the abreviacion to set
     */
    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }

}
