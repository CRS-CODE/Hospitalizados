/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDato;

/**
 *
 * @author EseGamboa
 */
public class cPueblo {

    private int id_pueblo;
    private String descripcion_pueblo;
    private int estado_pueblo;

    public cPueblo() {
        this.id_pueblo =-1;
        this.descripcion_pueblo = "";
        this.estado_pueblo = -1;
    }

    /**
     * @return the id_pueblo
     */
    public int getId_pueblo() {
        return id_pueblo;
    }

    /**
     * @param id_pueblo the id_pueblo to set
     */
    public void setId_pueblo(int id_pueblo) {
        this.id_pueblo = id_pueblo;
    }

    /**
     * @return the descripcion_pueblo
     */
    public String getDescripcion_pueblo() {
        return descripcion_pueblo;
    }

    /**
     * @param descripcion_pueblo the descripcion_pueblo to set
     */
    public void setDescripcion_pueblo(String descripcion_pueblo) {
        this.descripcion_pueblo = descripcion_pueblo;
    }

    /**
     * @return the estado_pueblo
     */
    public int getEstado_pueblo() {
        return estado_pueblo;
    }

    /**
     * @param estado_pueblo the estado_pueblo to set
     */
    public void setEstado_pueblo(int estado_pueblo) {
        this.estado_pueblo = estado_pueblo;
    }




}
