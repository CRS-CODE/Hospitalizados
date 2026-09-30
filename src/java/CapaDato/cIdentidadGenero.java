/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 * Campo 54 del Informe de Egreso Hospitalario (DEIS): Identidad de Genero.
 */
public class cIdentidadGenero {

    private int id_identidad_genero;
    private String descripcion_identidad_genero;
    private int estado_identidad_genero;

    public cIdentidadGenero() {
        this.id_identidad_genero = -1;
        this.descripcion_identidad_genero = "";
        this.estado_identidad_genero = -1;
    }

    public int getId_identidad_genero() {
        return id_identidad_genero;
    }

    public void setId_identidad_genero(int id_identidad_genero) {
        this.id_identidad_genero = id_identidad_genero;
    }

    public String getDescripcion_identidad_genero() {
        return descripcion_identidad_genero;
    }

    public void setDescripcion_identidad_genero(String descripcion_identidad_genero) {
        this.descripcion_identidad_genero = descripcion_identidad_genero;
    }

    public int getEstado_identidad_genero() {
        return estado_identidad_genero;
    }

    public void setEstado_identidad_genero(int estado_identidad_genero) {
        this.estado_identidad_genero = estado_identidad_genero;
    }

}
