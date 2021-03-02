/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDato;

/**
 *
 * @author Dis
 */
public class cPerfil {

    private int id_perfil;
 private String descripcion_perfil;
 private int estado_perfil;
 private int tipo_perfil;

    public cPerfil() {
        this.id_perfil = -1;
        this.descripcion_perfil = "";
        this.estado_perfil = -1;
        this.tipo_perfil =  -1;
    }

    /**
     * @return the id_perfil
     */
    public int getId_perfil() {
        return id_perfil;
    }

    /**
     * @param id_perfil the id_perfil to set
     */
    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    /**
     * @return the descripcion_perfil
     */
    public String getDescripcion_perfil() {
        return descripcion_perfil;
    }

    /**
     * @param descripcion_perfil the descripcion_perfil to set
     */
    public void setDescripcion_perfil(String descripcion_perfil) {
        this.descripcion_perfil = descripcion_perfil;
    }

    /**
     * @return the estado_perfil
     */
    public int getEstado_perfil() {
        return estado_perfil;
    }

    /**
     * @param estado_perfil the estado_perfil to set
     */
    public void setEstado_perfil(int estado_perfil) {
        this.estado_perfil = estado_perfil;
    }

    /**
     * @return the tipo_perfil
     */
    public int getTipo_perfil() {
        return tipo_perfil;
    }

    /**
     * @param tipo_perfil the tipo_perfil to set
     */
    public void setTipo_perfil(int tipo_perfil) {
        this.tipo_perfil = tipo_perfil;
    }

  




}
