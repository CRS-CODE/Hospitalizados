/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author EseGamboa
 */
public class cComuna {

    private int id_comuna;
    private String comuna_codigo;
    private String provincia_codigo;
    private String comuna_descripcion;
    private String provincia_descripcion;
    private String region_codigo;
    private String region_descripcion;

    public cComuna() {
        this.comuna_codigo = "";
        this.provincia_codigo = "";
        this.comuna_descripcion = "";
        this.provincia_descripcion = "";
        region_descripcion = "";
        region_descripcion = "";
    }

    /**
     * @return the comuna_codigo
     */
    public String getComuna_codigo() {
        return comuna_codigo;
    }

    /**
     * @param comuna_codigo the comuna_codigo to set
     */
    public void setComuna_codigo(String comuna_codigo) {
        this.comuna_codigo = comuna_codigo;
    }

    public int getId_comuna() {
        return id_comuna;
    }

    public void setId_comuna(int id_comuna) {
        this.id_comuna = id_comuna;
    }
    
    

    /**
     * @return the provincia_codigo
     */
    public String getProvincia_codigo() {
        return provincia_codigo;
    }

    /**
     * @param provincia_codigo the provincia_codigo to set
     */
    public void setProvincia_codigo(String provincia_codigo) {
        this.provincia_codigo = provincia_codigo;
    }

    /**
     * @return the comuna_descripcion
     */
    public String getComuna_descripcion() {
        return comuna_descripcion;
    }

    /**
     * @param comuna_descripcion the comuna_descripcion to set
     */
    public void setComuna_descripcion(String comuna_descripcion) {
        this.comuna_descripcion = comuna_descripcion;
    }

    /**
     * @return the provincia_descripcion
     */
    public String getProvincia_descripcion() {
        return provincia_descripcion;
    }

    /**
     * @param provincia_descripcion the provincia_descripcion to set
     */
    public void setProvincia_descripcion(String provincia_descripcion) {
        this.provincia_descripcion = provincia_descripcion;
    }

    /**
     * @return the region_codigo
     */
    public String getRegion_codigo() {
        return region_codigo;
    }

    /**
     * @param region_codigo the region_codigo to set
     */
    public void setRegion_codigo(String region_codigo) {
        this.region_codigo = region_codigo;
    }

    /**
     * @return the region_descripcion
     */
    public String getRegion_descripcion() {
        return region_descripcion;
    }

    /**
     * @param region_descripcion the region_descripcion to set
     */
    public void setRegion_descripcion(String region_descripcion) {
        this.region_descripcion = region_descripcion;
    }

    

    
}
