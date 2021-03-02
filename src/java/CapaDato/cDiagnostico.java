/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author EseGamboa
 */
public class cDiagnostico extends cUsuario {

    private int id_diagnostico;
    private String descripcion_diagnostico;
    private int tipo_diagnostico;
    private int id_duo;
    private String fecha_ingreso;
    private String ip;
    private int tipo_diagnostico_descri;
    /*asociadas a la observacion*/
    private int espera_radiografia;
    private int espera_ex_laboratorio;
    private String espera_radiografia_descri;
    private String espera_ex_laboratorio_descri;
    /*asociadas a la observacion*/

    private String fecha_corta;
    private String detalle_diagnostico;

    public cDiagnostico() {
        this.id_diagnostico = -1;
        this.descripcion_diagnostico = "";
        this.tipo_diagnostico = -1;
        this.id_duo = -1;

        fecha_ingreso = "";
        ip = "";
        tipo_diagnostico_descri = -1;

        this.espera_radiografia = -1;
        this.espera_ex_laboratorio = -1;
        this.espera_radiografia_descri = "";
        this.espera_ex_laboratorio_descri = "";
        this.fecha_corta="";
        detalle_diagnostico="";

    }

    /**
     * @return the id_diagnostico
     */
    public int getId_diagnostico() {
        return id_diagnostico;
    }

    /**
     * @param id_diagnostico the id_diagnostico to set
     */
    public void setId_diagnostico(int id_diagnostico) {
        this.id_diagnostico = id_diagnostico;
    }

    /**
     * @return the descripcion_diagnostico
     */
    public String getDescripcion_diagnostico() {
        return descripcion_diagnostico;
    }

    /**
     * @param descripcion_diagnostico the descripcion_diagnostico to set
     */
    public void setDescripcion_diagnostico(String descripcion_diagnostico) {
        this.descripcion_diagnostico = descripcion_diagnostico;
    }

    /**
     * @return the tipo_diagnostico
     */
    public int getTipo_diagnostico() {
        return tipo_diagnostico;
    }

    /**
     * @param tipo_diagnostico the tipo_diagnostico to set
     */
    public void setTipo_diagnostico(int tipo_diagnostico) {
        this.tipo_diagnostico = tipo_diagnostico;
    }

    /**
     * @return the id_duo
     */
    public int getId_duo() {
        return id_duo;
    }

    /**
     * @param id_duo the id_duo to set
     */
    public void setId_duo(int id_duo) {
        this.id_duo = id_duo;
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
     * @return the tipo_diagnostico_descri
     */
    public int getTipo_diagnostico_descri() {
        return tipo_diagnostico_descri;
    }

    /**
     * @param tipo_diagnostico_descri the tipo_diagnostico_descri to set
     */
    public void setTipo_diagnostico_descri(int tipo_diagnostico_descri) {
        this.tipo_diagnostico_descri = tipo_diagnostico_descri;
    }

    /**
     * @return the espera_radiografia
     */
    public int getEspera_radiografia() {
        return espera_radiografia;
    }

    /**
     * @param espera_radiografia the espera_radiografia to set
     */
    public void setEspera_radiografia(int espera_radiografia) {
        this.espera_radiografia = espera_radiografia;
    }

    /**
     * @return the espera_ex_laboratorio
     */
    public int getEspera_ex_laboratorio() {
        return espera_ex_laboratorio;
    }

    /**
     * @param espera_ex_laboratorio the espera_ex_laboratorio to set
     */
    public void setEspera_ex_laboratorio(int espera_ex_laboratorio) {
        this.espera_ex_laboratorio = espera_ex_laboratorio;
    }

    /**
     * @return the espera_radiografia_descri
     */
    public String getEspera_radiografia_descri() {
        return espera_radiografia_descri;
    }

    /**
     * @param espera_radiografia_descri the espera_radiografia_descri to set
     */
    public void setEspera_radiografia_descri(String espera_radiografia_descri) {
        this.espera_radiografia_descri = espera_radiografia_descri;
    }

    /**
     * @return the espera_ex_laboratorio_descri
     */
    public String getEspera_ex_laboratorio_descri() {
        return espera_ex_laboratorio_descri;
    }

    /**
     * @param espera_ex_laboratorio_descri the espera_ex_laboratorio_descri to set
     */
    public void setEspera_ex_laboratorio_descri(String espera_ex_laboratorio_descri) {
        this.espera_ex_laboratorio_descri = espera_ex_laboratorio_descri;
    }

    /**
     * @return the fecha_corta
     */
    public String getFecha_corta() {
        return fecha_corta;
    }

    /**
     * @param fecha_corta the fecha_corta to set
     */
    public void setFecha_corta(String fecha_corta) {
        this.fecha_corta = fecha_corta;
    }

    /**
     * @return the detalle_diagnostico
     */
    public String getDetalle_diagnostico() {
        return detalle_diagnostico;
    }

    /**
     * @param detalle_diagnostico the detalle_diagnostico to set
     */
    public void setDetalle_diagnostico(String detalle_diagnostico) {
        this.detalle_diagnostico = detalle_diagnostico;
    }
}
