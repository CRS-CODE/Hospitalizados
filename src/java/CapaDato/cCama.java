/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author EseGamboa
 */
public class cCama extends cPaciente {

    private int id_cama;
    private String descripcion_cama;
    private int id_sala;
    private String descripcion_sala;
    private int estado_cama;
    private int id_area_clinica;
    private String descripcion_area_clinica;
    private int id_institucion;
    private String descripcion_institucion;
 
    private String ultima_cat_enfermeria;
    private int cantidad_dias_estadia;
    

    public cCama() {
        this.id_sala = -1;
        this.descripcion_sala = "";
        this.id_cama = -1;
        this.descripcion_cama = "";
        this.estado_cama = -1;
        this.id_area_clinica = -1;
        this.descripcion_area_clinica = "";
        this.id_institucion = -1;
        this.descripcion_institucion = "";
        ultima_cat_enfermeria="";
        cantidad_dias_estadia=-1;
    }

    /**
     * @return the id_cama
     */
    public int getId_cama() {
        return id_cama;
    }

    /**
     * @param id_cama the id_cama to set
     */
    public void setId_cama(int id_cama) {
        this.id_cama = id_cama;
    }

    /**
     * @return the descripcion_cama
     */
    public String getDescripcion_cama() {
        return descripcion_cama;
    }

    /**
     * @param descripcion_cama the descripcion_cama to set
     */
    public void setDescripcion_cama(String descripcion_cama) {
        this.descripcion_cama = descripcion_cama;
    }

    /**
     * @return the id_sala
     */
    public int getId_sala() {
        return id_sala;
    }

    /**
     * @param id_sala the id_sala to set
     */
    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    /**
     * @return the descripcion_sala
     */
    public String getDescripcion_sala() {
        return descripcion_sala;
    }

    /**
     * @param descripcion_sala the descripcion_sala to set
     */
    public void setDescripcion_sala(String descripcion_sala) {
        this.descripcion_sala = descripcion_sala;
    }

    /**
     * @return the estado_cama
     */
    public int getEstado_cama() {
        return estado_cama;
    }

    /**
     * @param estado_cama the estado_cama to set
     */
    public void setEstado_cama(int estado_cama) {
        this.estado_cama = estado_cama;
    }

    /**
     * @return the id_area_clinica
     */
    public int getId_area_clinica() {
        return id_area_clinica;
    }

    /**
     * @param id_area_clinica the id_area_clinica to set
     */
    public void setId_area_clinica(int id_area_clinica) {
        this.id_area_clinica = id_area_clinica;
    }

    /**
     * @return the descripcion_area_clinica
     */
    public String getDescripcion_area_clinica() {
        return descripcion_area_clinica;
    }

    /**
     * @param descripcion_area_clinica the descripcion_area_clinica to set
     */
    public void setDescripcion_area_clinica(String descripcion_area_clinica) {
        this.descripcion_area_clinica = descripcion_area_clinica;
    }

    /**
     * @return the id_institucion
     */
    public int getId_institucion() {
        return id_institucion;
    }

    /**
     * @param id_institucion the id_institucion to set
     */
    public void setId_institucion(int id_institucion) {
        this.id_institucion = id_institucion;
    }

    /**
     * @return the descripcion_institucion
     */
    public String getDescripcion_institucion() {
        return descripcion_institucion;
    }

    /**
     * @param descripcion_institucion the descripcion_institucion to set
     */
    public void setDescripcion_institucion(String descripcion_institucion) {
        this.descripcion_institucion = descripcion_institucion;
    }

    /**
     * @return the cantidad_dias_estadia
     */
    public int getCantidad_dias_estadia() {
        return cantidad_dias_estadia;
    }

    /**
     * @param cantidad_dias_estadia the cantidad_dias_estadia to set
     */
    public void setCantidad_dias_estadia(int cantidad_dias_estadia) {
        this.cantidad_dias_estadia = cantidad_dias_estadia;
    }
}
