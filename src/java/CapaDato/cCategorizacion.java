/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDato;

/**
 *
 * @author EseGamboa
 */
public class cCategorizacion {

    private int id;
            private String descripcion;
            private int estado;

    public cCategorizacion() {
        this.id = 0;
        this.descripcion = "";
        this.estado = 0;
    }




    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

         

}
