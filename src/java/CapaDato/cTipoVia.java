/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 * Tipo de via de la direccion del paciente (Calle, Avenida, Pasaje, etc).
 */
public class cTipoVia {

    private int id_tipo_via;
    private String descripcion_tipo_via;
    private int estado_tipo_via;

    public cTipoVia() {
        this.id_tipo_via = -1;
        this.descripcion_tipo_via = "";
        this.estado_tipo_via = -1;
    }

    public int getId_tipo_via() {
        return id_tipo_via;
    }

    public void setId_tipo_via(int id_tipo_via) {
        this.id_tipo_via = id_tipo_via;
    }

    public String getDescripcion_tipo_via() {
        return descripcion_tipo_via;
    }

    public void setDescripcion_tipo_via(String descripcion_tipo_via) {
        this.descripcion_tipo_via = descripcion_tipo_via;
    }

    public int getEstado_tipo_via() {
        return estado_tipo_via;
    }

    public void setEstado_tipo_via(int estado_tipo_via) {
        this.estado_tipo_via = estado_tipo_via;
    }

}
