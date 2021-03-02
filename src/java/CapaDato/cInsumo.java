/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author Informatica
 */
public class cInsumo extends cUsuario {

    private int id_insumo;

    private int familia;
    private int tipo;
    private int generico;

    private int tipo_clinico; // 1 si es medicamento, 2 si es insumo y 0 si es ninguno
    private String tipo_clinico_desc;

    private String tipo_desc;
    private String familia_desc;
    private String generico_desc;

    private String insumo_desc;
    private int stock_actual;
    private int stock_minimo;
    private int stock_critico;
    private String observacion;
    private int estado;
    private double alto;
    private double ancho;
    private double largo;
    private String fecha_ingreso;

    private int unidad_medida;
    private String unidad_medida_desc;

    private String marca;

    private String codigo_barra;

    public cInsumo() {
        this.id_insumo = 0;
        this.familia = 0;
        this.tipo = 0;
        tipo_clinico = 0;
        tipo_clinico_desc = "";
        this.generico = 0;
        familia_desc = "";
        tipo_desc = "";
        generico_desc = "";
        this.insumo_desc = "";
        stock_actual = 0;
        this.stock_minimo = 0;
        this.observacion = "";
        this.estado = 0;
        this.alto = 0;
        this.ancho = 0;
        this.largo = 0;
        this.fecha_ingreso = "";
        unidad_medida = 0;
        unidad_medida_desc = "";
        marca = "";
        stock_critico = 0;
        codigo_barra = "";

    }

    /**
     * @return the id_insumo
     */
    public int getId_insumo() {
        return id_insumo;
    }

    /**
     * @param id_insumo the id_insumo to set
     */
    public void setId_insumo(int id_insumo) {
        this.id_insumo = id_insumo;
    }

    /**
     * @return the familia
     */
    public int getFamilia() {
        return familia;
    }

    /**
     * @param familia the familia to set
     */
    public void setFamilia(int familia) {
        this.familia = familia;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the generico
     */
    public int getGenerico() {
        return generico;
    }

    /**
     * @param generico the generico to set
     */
    public void setGenerico(int generico) {
        this.generico = generico;
    }

    /**
     * @return the familia_desc
     */
    public String getFamilia_desc() {
        return familia_desc;
    }

    /**
     * @param familia_desc the familia_desc to set
     */
    public void setFamilia_desc(String familia_desc) {
        this.familia_desc = familia_desc;
    }

    /**
     * @return the tipo_desc
     */
    public String getTipo_desc() {
        return tipo_desc;
    }

    /**
     * @param tipo_desc the tipo_desc to set
     */
    public void setTipo_desc(String tipo_desc) {
        this.tipo_desc = tipo_desc;
    }

    /**
     * @return the generico_desc
     */
    public String getGenerico_desc() {
        return generico_desc;
    }

    /**
     * @param generico_desc the generico_desc to set
     */
    public void setGenerico_desc(String generico_desc) {
        this.generico_desc = generico_desc;
    }

    /**
     * @return the stock_actual
     */
    public int getStock_actual() {
        return stock_actual;
    }

    /**
     * @param stock_actual the stock_actual to set
     */
    public void setStock_actual(int stock_actual) {
        this.stock_actual = stock_actual;
    }

    /**
     * @return the stock_minimo
     */
    public int getStock_minimo() {
        return stock_minimo;
    }

    /**
     * @param stock_minimo the stock_minimo to set
     */
    public void setStock_minimo(int stock_minimo) {
        this.stock_minimo = stock_minimo;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    /**
     * @return the alto
     */
    public double getAlto() {
        return alto;
    }

    /**
     * @param alto the alto to set
     */
    public void setAlto(double alto) {
        this.alto = alto;
    }

    /**
     * @return the ancho
     */
    public double getAncho() {
        return ancho;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    /**
     * @return the largo
     */
    public double getLargo() {
        return largo;
    }

    /**
     * @param largo the largo to set
     */
    public void setLargo(double largo) {
        this.largo = largo;
    }

    /**
     * @return the fecha_ingreso
     */
    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    /**
     * @param fecha_ingreso the fecha_ingreso to set
     */
    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    /**
     * @return the unidad_medida
     */
    public int getUnidad_medida() {
        return unidad_medida;
    }

    /**
     * @param unidad_medida the unidad_medida to set
     */
    public void setUnidad_medida(int unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    /**
     * @return the unidad_medida_desc
     */
    public String getUnidad_medida_desc() {
        return unidad_medida_desc;
    }

    /**
     * @param unidad_medida_desc the unidad_medida_desc to set
     */
    public void setUnidad_medida_desc(String unidad_medida_desc) {
        this.unidad_medida_desc = unidad_medida_desc;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the stock_critico
     */
    public int getStock_critico() {
        return stock_critico;
    }

    /**
     * @param stock_critico the stock_critico to set
     */
    public void setStock_critico(int stock_critico) {
        this.stock_critico = stock_critico;
    }

    /**
     * @return the tipo_clinico
     */
    public int getTipo_clinico() {
        return tipo_clinico;
    }

    /**
     * @param tipo_clinico the tipo_clinico to set
     */
    public void setTipo_clinico(int tipo_clinico) {
        this.tipo_clinico = tipo_clinico;
    }

    /**
     * @return the tipo_clinico_desc
     */
    public String getTipo_clinico_desc() {
        return tipo_clinico_desc;
    }

    /**
     * @param tipo_clinico_desc the tipo_clinico_desc to set
     */
    public void setTipo_clinico_desc(String tipo_clinico_desc) {
        this.tipo_clinico_desc = tipo_clinico_desc;
    }

    /**
     * @return the insumo_desc
     */
    public String getInsumo_desc() {
        return insumo_desc;
    }

    /**
     * @param insumo_desc the insumo_desc to set
     */
    public void setInsumo_desc(String insumo_desc) {
        this.insumo_desc = insumo_desc;
    }

    /**
     * @return the codigo_barra
     */
    public String getCodigo_barra() {
        return codigo_barra;
    }

    /**
     * @param codigo_barra the codigo_barra to set
     */
    public void setCodigo_barra(String codigo_barra) {
        this.codigo_barra = codigo_barra;
    }
}
