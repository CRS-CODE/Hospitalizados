/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author EseGamboa
 */
public class cCatRiesgoDependencia {

    private int id_ambito_crd;
    private String descripcion_ambito_crd;
    private int tipo_ambito_crd;
    private String abre_ambito_crd;

    public cCatRiesgoDependencia() {
        id_ambito_crd = 0;
        descripcion_ambito_crd = "";
        tipo_ambito_crd = 0;
        abre_ambito_crd = "";

    }

    public int getId_ambito_crd() {
        return id_ambito_crd;
    }

    public void setId_ambito_crd(int id_ambito_crd) {
        this.id_ambito_crd = id_ambito_crd;
    }

    public String getDescripcion_ambito_crd() {
        return descripcion_ambito_crd;
    }

    public void setDescripcion_ambito_crd(String descripcion_ambito_crd) {
        this.descripcion_ambito_crd = descripcion_ambito_crd;
    }

    public int getTipo_ambito_crd() {
        return tipo_ambito_crd;
    }

    public void setTipo_ambito_crd(int tipo_ambito_crd) {
        this.tipo_ambito_crd = tipo_ambito_crd;
    }

    public String getAbre_ambito_crd() {
        return abre_ambito_crd;
    }

    public void setAbre_ambito_crd(String abre_ambito_crd) {
        this.abre_ambito_crd = abre_ambito_crd;
    }
}
