/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author EseGamboa
 */
public class cAlta_Das extends cUsuario {

    private int id_alta_das;
    private int id_das;
    private String fecha_ingreso;
    private int estado;
    private String estado_descri;
    private int destino;
    private String destino_descri;
    private String detalle;
    private String ip;
    private String medico_nombre;
    private String medico_apellidop;
    private String medico_apellidom;
    private String medico_rut;
    private int dif_dd;
    private int dif_ss;
    private int dif_mm;
    private int dif_hh;

    public cAlta_Das() {
        this.id_alta_das = 0;
        this.id_das = 0;
        this.fecha_ingreso = "";
        this.estado = 0;
        this.estado_descri = "";
        this.destino = 0;
        this.destino_descri = "";
        this.detalle = "";
        this.ip = "";

        medico_nombre = "";
        medico_apellidop = "";
        medico_apellidom = "";
        medico_rut = "";

        dif_dd = 0;
        dif_ss = 0;
        dif_mm = 0;
        dif_hh = 0;

    }

    /**
     * @return the id_alta_das
     */
    public int getId_alta_das() {
        return id_alta_das;
    }

    /**
     * @param id_alta_das the id_alta_das to set
     */
    public void setId_alta_das(int id_alta_das) {
        this.id_alta_das = id_alta_das;
    }

    /**
     * @return the id_das
     */
    public int getId_das() {
        return id_das;
    }

    /**
     * @param id_das the id_das to set
     */
    public void setId_das(int id_das) {
        this.id_das = id_das;
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
     * @return the estado_descri
     */
    public String getEstado_descri() {
        return estado_descri;
    }

    /**
     * @param estado_descri the estado_descri to set
     */
    public void setEstado_descri(String estado_descri) {
        this.estado_descri = estado_descri;
    }

    /**
     * @return the destino
     */
    public int getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(int destino) {
        this.destino = destino;
    }

    /**
     * @return the destino_descri
     */
    public String getDestino_descri() {
        return destino_descri;
    }

    /**
     * @param destino_descri the destino_descri to set
     */
    public void setDestino_descri(String destino_descri) {
        this.destino_descri = destino_descri;
    }

    /**
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the medico_nombre
     */
    public String getMedico_nombre() {
        return medico_nombre;
    }

    /**
     * @param medico_nombre the medico_nombre to set
     */
    public void setMedico_nombre(String medico_nombre) {
        this.medico_nombre = medico_nombre;
    }

    /**
     * @return the medico_apellidop
     */
    public String getMedico_apellidop() {
        return medico_apellidop;
    }

    /**
     * @param medico_apellidop the medico_apellidop to set
     */
    public void setMedico_apellidop(String medico_apellidop) {
        this.medico_apellidop = medico_apellidop;
    }

    /**
     * @return the medico_apellidom
     */
    public String getMedico_apellidom() {
        return medico_apellidom;
    }

    /**
     * @param medico_apellidom the medico_apellidom to set
     */
    public void setMedico_apellidom(String medico_apellidom) {
        this.medico_apellidom = medico_apellidom;
    }

    /**
     * @return the medico_rut
     */
    public String getMedico_rut() {
        return medico_rut;
    }

    /**
     * @param medico_rut the medico_rut to set
     */
    public void setMedico_rut(String medico_rut) {
        this.medico_rut = medico_rut;
    }

    /**
     * @return the dif_dd
     */
    public int getDif_dd() {
        return dif_dd;
    }

    /**
     * @param dif_dd the dif_dd to set
     */
    public void setDif_dd(int dif_dd) {
        this.dif_dd = dif_dd;
    }

    /**
     * @return the dif_ss
     */
    public int getDif_ss() {
        return dif_ss;
    }

    /**
     * @param dif_ss the dif_ss to set
     */
    public void setDif_ss(int dif_ss) {
        this.dif_ss = dif_ss;
    }

    /**
     * @return the dif_mm
     */
    public int getDif_mm() {
        return dif_mm;
    }

    /**
     * @param dif_mm the dif_mm to set
     */
    public void setDif_mm(int dif_mm) {
        this.dif_mm = dif_mm;
    }

    /**
     * @return the dif_hh
     */
    public int getDif_hh() {
        return dif_hh;
    }

    /**
     * @param dif_hh the dif_hh to set
     */
    public void setDif_hh(int dif_hh) {
        this.dif_hh = dif_hh;
    }
}
