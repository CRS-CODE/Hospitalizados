/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 * Campo 12 (2do nivel) del Informe de Egreso Hospitalario (DEIS):
 * Ocupacion Declarada. Solo aplica cuando Categoria Ocupacional = Activos.
 */
public class cOcupacionDeclarada {

    private int id_ocupacion_declarada;
    private String descripcion_ocupacion_declarada;
    private int estado_ocupacion_declarada;

    public cOcupacionDeclarada() {
        this.id_ocupacion_declarada = -1;
        this.descripcion_ocupacion_declarada = "";
        this.estado_ocupacion_declarada = -1;
    }

    public int getId_ocupacion_declarada() {
        return id_ocupacion_declarada;
    }

    public void setId_ocupacion_declarada(int id_ocupacion_declarada) {
        this.id_ocupacion_declarada = id_ocupacion_declarada;
    }

    public String getDescripcion_ocupacion_declarada() {
        return descripcion_ocupacion_declarada;
    }

    public void setDescripcion_ocupacion_declarada(String descripcion_ocupacion_declarada) {
        this.descripcion_ocupacion_declarada = descripcion_ocupacion_declarada;
    }

    public int getEstado_ocupacion_declarada() {
        return estado_ocupacion_declarada;
    }

    public void setEstado_ocupacion_declarada(int estado_ocupacion_declarada) {
        this.estado_ocupacion_declarada = estado_ocupacion_declarada;
    }

}
