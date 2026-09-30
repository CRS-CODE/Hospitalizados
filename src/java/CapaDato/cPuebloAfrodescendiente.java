/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 * Campo 53 del Informe de Egreso Hospitalario (DEIS): Pueblo Afrodescendiente Chileno.
 */
public class cPuebloAfrodescendiente {

    private int id_pueblo_afro;
    private String descripcion_pueblo_afro;
    private int estado_pueblo_afro;

    public cPuebloAfrodescendiente() {
        this.id_pueblo_afro = -1;
        this.descripcion_pueblo_afro = "";
        this.estado_pueblo_afro = -1;
    }

    public int getId_pueblo_afro() {
        return id_pueblo_afro;
    }

    public void setId_pueblo_afro(int id_pueblo_afro) {
        this.id_pueblo_afro = id_pueblo_afro;
    }

    public String getDescripcion_pueblo_afro() {
        return descripcion_pueblo_afro;
    }

    public void setDescripcion_pueblo_afro(String descripcion_pueblo_afro) {
        this.descripcion_pueblo_afro = descripcion_pueblo_afro;
    }

    public int getEstado_pueblo_afro() {
        return estado_pueblo_afro;
    }

    public void setEstado_pueblo_afro(int estado_pueblo_afro) {
        this.estado_pueblo_afro = estado_pueblo_afro;
    }

}
