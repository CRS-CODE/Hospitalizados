/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 * Campo 21 del Informe de Egreso Hospitalario (DEIS): Leyes Previsionales.
 */
public class cLeyPrevisional {

    private int id_ley_previsional;
    private String descripcion_ley_previsional;
    private int estado_ley_previsional;

    public cLeyPrevisional() {
        this.id_ley_previsional = -1;
        this.descripcion_ley_previsional = "";
        this.estado_ley_previsional = -1;
    }

    public int getId_ley_previsional() {
        return id_ley_previsional;
    }

    public void setId_ley_previsional(int id_ley_previsional) {
        this.id_ley_previsional = id_ley_previsional;
    }

    public String getDescripcion_ley_previsional() {
        return descripcion_ley_previsional;
    }

    public void setDescripcion_ley_previsional(String descripcion_ley_previsional) {
        this.descripcion_ley_previsional = descripcion_ley_previsional;
    }

    public int getEstado_ley_previsional() {
        return estado_ley_previsional;
    }

    public void setEstado_ley_previsional(int estado_ley_previsional) {
        this.estado_ley_previsional = estado_ley_previsional;
    }

}
