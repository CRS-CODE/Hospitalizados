/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 * Campo 13 del Informe de Egreso Hospitalario (DEIS): Nivel de Instruccion.
 */
public class cNivelInstruccion {

    private int id_nivel_instruccion;
    private String descripcion_nivel_instruccion;
    private int estado_nivel_instruccion;

    public cNivelInstruccion() {
        this.id_nivel_instruccion = -1;
        this.descripcion_nivel_instruccion = "";
        this.estado_nivel_instruccion = -1;
    }

    public int getId_nivel_instruccion() {
        return id_nivel_instruccion;
    }

    public void setId_nivel_instruccion(int id_nivel_instruccion) {
        this.id_nivel_instruccion = id_nivel_instruccion;
    }

    public String getDescripcion_nivel_instruccion() {
        return descripcion_nivel_instruccion;
    }

    public void setDescripcion_nivel_instruccion(String descripcion_nivel_instruccion) {
        this.descripcion_nivel_instruccion = descripcion_nivel_instruccion;
    }

    public int getEstado_nivel_instruccion() {
        return estado_nivel_instruccion;
    }

    public void setEstado_nivel_instruccion(int estado_nivel_instruccion) {
        this.estado_nivel_instruccion = estado_nivel_instruccion;
    }

}
