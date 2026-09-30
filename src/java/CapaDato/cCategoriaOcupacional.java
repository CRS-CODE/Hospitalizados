/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 * Campo 12 del Informe de Egreso Hospitalario (DEIS): Categoria Ocupacional.
 */
public class cCategoriaOcupacional {

    private int id_categoria_ocupacional;
    private String descripcion_categoria_ocupacional;
    private int estado_categoria_ocupacional;

    public cCategoriaOcupacional() {
        this.id_categoria_ocupacional = -1;
        this.descripcion_categoria_ocupacional = "";
        this.estado_categoria_ocupacional = -1;
    }

    public int getId_categoria_ocupacional() {
        return id_categoria_ocupacional;
    }

    public void setId_categoria_ocupacional(int id_categoria_ocupacional) {
        this.id_categoria_ocupacional = id_categoria_ocupacional;
    }

    public String getDescripcion_categoria_ocupacional() {
        return descripcion_categoria_ocupacional;
    }

    public void setDescripcion_categoria_ocupacional(String descripcion_categoria_ocupacional) {
        this.descripcion_categoria_ocupacional = descripcion_categoria_ocupacional;
    }

    public int getEstado_categoria_ocupacional() {
        return estado_categoria_ocupacional;
    }

    public void setEstado_categoria_ocupacional(int estado_categoria_ocupacional) {
        this.estado_categoria_ocupacional = estado_categoria_ocupacional;
    }

}
