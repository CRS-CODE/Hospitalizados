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
public class cRegistroSocial extends cUsuario {

    private int id_registro;
    private String fecha_ingresa;
    private int estado;
    private String estado_desc;
    private int estado_civil;
    private String estado_civil_desc;
    private int situacion_laboral;
    private String situacion_laboral_desc;
    private int institucionalizado;
    private String institucionalizado_desc;
    private String institucion_nombre;
    private int vive;
    private String vive_desc;
    private int hijos;
    private String hijos_desc;
    private int hijos_cantidad;
    private String situacion;
    private String plan;
    private int id_duo;

    /*26012015*/
    private String fecha_egreso;
    private int destino;
    private String destino_desc;

    private String rut_asistente;
    private String nombre_asistente;
    private String apellidop_asistente;
    private String apellidom_asistente;

    private int hospital_origen;
    private String hospital_origen_desc;
    /*26012015*/
    // 22-05-2015
    private String diagnostico;

    public cRegistroSocial() {
        this.id_registro = 0;
        this.fecha_ingresa = "";
        this.estado = 0;
        this.estado_desc = "";
        this.estado_civil = 0;
        this.estado_civil_desc = "";
        this.situacion_laboral = 0;
        this.situacion_laboral_desc = "";
        this.institucionalizado = 0;
        this.institucionalizado_desc = "";
        this.institucion_nombre = "";
        this.vive = 0;
        this.vive_desc = "";
        this.hijos = 0;
        this.hijos_desc = "";
        this.hijos_cantidad = 0;
        this.situacion = "";
        this.plan = "";
        this.id_duo = 0;

        fecha_egreso = "";
        destino = 0;
        destino_desc = "";

        rut_asistente = "";
        nombre_asistente = "";
        apellidop_asistente = "";
        apellidom_asistente = "";

        hospital_origen = 0;
        hospital_origen_desc = "";
        diagnostico = "";
    }

    /**
     * @return the fecha_ingresa
     */
    public String getFecha_ingresa() {
        return fecha_ingresa;
    }

    /**
     * @param fecha_ingresa the fecha_ingresa to set
     */
    public void setFecha_ingresa(String fecha_ingresa) {
        this.fecha_ingresa = fecha_ingresa;
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
     * @return the estado_desc
     */
    public String getEstado_desc() {
        return estado_desc;
    }

    /**
     * @param estado_desc the estado_desc to set
     */
    public void setEstado_desc(String estado_desc) {
        this.estado_desc = estado_desc;
    }

    /**
     * @return the estado_civil
     */
    public int getEstado_civil() {
        return estado_civil;
    }

    /**
     * @param estado_civil the estado_civil to set
     */
    public void setEstado_civil(int estado_civil) {
        this.estado_civil = estado_civil;
    }

    /**
     * @return the estado_civil_desc
     */
    public String getEstado_civil_desc() {
        return estado_civil_desc;
    }

    /**
     * @param estado_civil_desc the estado_civil_desc to set
     */
    public void setEstado_civil_desc(String estado_civil_desc) {
        this.estado_civil_desc = estado_civil_desc;
    }

    /**
     * @return the situacion_laboral
     */
    public int getSituacion_laboral() {
        return situacion_laboral;
    }

    /**
     * @param situacion_laboral the situacion_laboral to set
     */
    public void setSituacion_laboral(int situacion_laboral) {
        this.situacion_laboral = situacion_laboral;
    }

    /**
     * @return the situacion_laboral_desc
     */
    public String getSituacion_laboral_desc() {
        return situacion_laboral_desc;
    }

    /**
     * @param situacion_laboral_desc the situacion_laboral_desc to set
     */
    public void setSituacion_laboral_desc(String situacion_laboral_desc) {
        this.situacion_laboral_desc = situacion_laboral_desc;
    }

    /**
     * @return the institucionalizado
     */
    public int getInstitucionalizado() {
        return institucionalizado;
    }

    /**
     * @param institucionalizado the institucionalizado to set
     */
    public void setInstitucionalizado(int institucionalizado) {
        this.institucionalizado = institucionalizado;
    }

    /**
     * @return the institucionalizado_desc
     */
    public String getInstitucionalizado_desc() {
        return institucionalizado_desc;
    }

    /**
     * @param institucionalizado_desc the institucionalizado_desc to set
     */
    public void setInstitucionalizado_desc(String institucionalizado_desc) {
        this.institucionalizado_desc = institucionalizado_desc;
    }

    /**
     * @return the institucion_nombre
     */
    public String getInstitucion_nombre() {
        return institucion_nombre;
    }

    /**
     * @param institucion_nombre the institucion_nombre to set
     */
    public void setInstitucion_nombre(String institucion_nombre) {
        this.institucion_nombre = institucion_nombre;
    }

    /**
     * @return the vive
     */
    public int getVive() {
        return vive;
    }

    /**
     * @param vive the vive to set
     */
    public void setVive(int vive) {
        this.vive = vive;
    }

    /**
     * @return the vive_desc
     */
    public String getVive_desc() {
        return vive_desc;
    }

    /**
     * @param vive_desc the vive_desc to set
     */
    public void setVive_desc(String vive_desc) {
        this.vive_desc = vive_desc;
    }

    /**
     * @return the hijos
     */
    public int getHijos() {
        return hijos;
    }

    /**
     * @param hijos the hijos to set
     */
    public void setHijos(int hijos) {
        this.hijos = hijos;
    }

    /**
     * @return the hijos_desc
     */
    public String getHijos_desc() {
        return hijos_desc;
    }

    /**
     * @param hijos_desc the hijos_desc to set
     */
    public void setHijos_desc(String hijos_desc) {
        this.hijos_desc = hijos_desc;
    }

    /**
     * @return the hijos_cantidad
     */
    public int getHijos_cantidad() {
        return hijos_cantidad;
    }

    /**
     * @param hijos_cantidad the hijos_cantidad to set
     */
    public void setHijos_cantidad(int hijos_cantidad) {
        this.hijos_cantidad = hijos_cantidad;
    }

    /**
     * @return the situacion
     */
    public String getSituacion() {
        return situacion;
    }

    /**
     * @param situacion the situacion to set
     */
    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    /**
     * @return the plan
     */
    public String getPlan() {
        return plan;
    }

    /**
     * @param plan the plan to set
     */
    public void setPlan(String plan) {
        this.plan = plan;
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
     * @return the id_registro
     */
    public int getId_registro() {
        return id_registro;
    }

    /**
     * @param id_registro the id_registro to set
     */
    public void setId_registro(int id_registro) {
        this.id_registro = id_registro;
    }

    /**
     * @return the fecha_egreso
     */
    public String getFecha_egreso() {
        return fecha_egreso;
    }

    /**
     * @param fecha_egreso the fecha_egreso to set
     */
    public void setFecha_egreso(String fecha_egreso) {
        this.fecha_egreso = fecha_egreso;
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
     * @return the destino_desc
     */
    public String getDestino_desc() {
        return destino_desc;
    }

    /**
     * @param destino_desc the destino_desc to set
     */
    public void setDestino_desc(String destino_desc) {
        this.destino_desc = destino_desc;
    }

    /**
     * @return the rut_asistente
     */
    public String getRut_asistente() {
        return rut_asistente;
    }

    /**
     * @param rut_asistente the rut_asistente to set
     */
    public void setRut_asistente(String rut_asistente) {
        this.rut_asistente = rut_asistente;
    }

    /**
     * @return the nombre_asistente
     */
    public String getNombre_asistente() {
        return nombre_asistente;
    }

    /**
     * @param nombre_asistente the nombre_asistente to set
     */
    public void setNombre_asistente(String nombre_asistente) {
        this.nombre_asistente = nombre_asistente;
    }

    /**
     * @return the apellidop_asistente
     */
    public String getApellidop_asistente() {
        return apellidop_asistente;
    }

    /**
     * @param apellidop_asistente the apellidop_asistente to set
     */
    public void setApellidop_asistente(String apellidop_asistente) {
        this.apellidop_asistente = apellidop_asistente;
    }

    /**
     * @return the apellidom_asistente
     */
    public String getApellidom_asistente() {
        return apellidom_asistente;
    }

    /**
     * @param apellidom_asistente the apellidom_asistente to set
     */
    public void setApellidom_asistente(String apellidom_asistente) {
        this.apellidom_asistente = apellidom_asistente;
    }

    /**
     * @return the hospital_origen
     */
    public int getHospital_origen() {
        return hospital_origen;
    }

    /**
     * @param hospital_origen the hospital_origen to set
     */
    public void setHospital_origen(int hospital_origen) {
        this.hospital_origen = hospital_origen;
    }

    /**
     * @return the hospital_origen_desc
     */
    public String getHospital_origen_desc() {
        return hospital_origen_desc;
    }

    /**
     * @param hospital_origen_desc the hospital_origen_desc to set
     */
    public void setHospital_origen_desc(String hospital_origen_desc) {
        this.hospital_origen_desc = hospital_origen_desc;
    }

    /**
     * @return the diagnostico
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * @param diagnostico the diagnostico to set
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

}
