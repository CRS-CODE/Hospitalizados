/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDato;

/**
 *
 * @author Dis
 */
public class cNacion {

    private int id_nac;
    private String descripcion_nac;
    private int estado_nac;

    public cNacion() {
        this.id_nac =-1;
        this.descripcion_nac = "";
        this.estado_nac =-1;
    }



    /**
     * @return the id_nac
     */
    public int getId_nac() {
        return id_nac;
    }

    /**
     * @param id_nac the id_nac to set
     */
    public void setId_nac(int id_nac) {
        this.id_nac = id_nac;
    }

    /**
     * @return the descripcion_nac
     */
    public String getDescripcion_nac() {
        return descripcion_nac;
    }

    /**
     * @param descripcion_nac the descripcion_nac to set
     */
    public void setDescripcion_nac(String descripcion_nac) {
        this.descripcion_nac = descripcion_nac;
    }

    /**
     * @return the estado_nac
     */
    public int getEstado_nac() {
        return estado_nac;
    }

    /**
     * @param estado_nac the estado_nac to set
     */
    public void setEstado_nac(int estado_nac) {
        this.estado_nac = estado_nac;
    }

    

}
