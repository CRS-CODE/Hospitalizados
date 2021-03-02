/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

import java.util.Date;

/**
 *
 * @author Informatica
 */
public class cReceta extends cUsuario {

    private int id_receta;
    private int id_receta_detalle;
    private String fecha_ingreso;
    private String fecha;
    private int tipo;
    private String tipo_desc;
    private int medicamento;
    private String medicamento_desc;
    private Date fechadate;
    private int id_via;
    private int dosis;
    private String dosis_desc;
    private int medida;
    private String medida_desc;
    private double cantidad;
    private String frecuencia;
    private int duracion; // revisar si es int
    private String duracion_desc;
    private String indicacion;
    private String indicacion_no;
    private int estado;
    private String estado_desc;

    private String rut_paciente; // pensar si puede ser id duo
    private int id_duo;
    private String ip;
    private int dia_hospitalizacion;
    private String diagnostico;
    private String reposo;
    private String regimen;
    private String kinesiologia;
    private String aislamiento;
    private String contencion;
    private String dispositivo;
    private String hgt;
    private String control_signos;
    private String alergias;
    private String imagenes;
    private String otros;
    private String indicaciones_enfermeria;
    private String indicaciones_nutricionista;
    private String indicaciones_kinesiologo;
    private String indicaciones_otros;
    
    
    

    public cReceta() {
        id_receta = 0;
        this.id_receta_detalle = 0;
        this.fecha_ingreso = "";
        this.fecha = "";
        this.tipo = 0;
        this.tipo_desc = "";
        this.medicamento = 0;
        this.medicamento_desc = "";
        dosis = 0;
        dosis_desc = "";
        this.medida = 0;
        this.medida_desc = "";
        this.cantidad = 0;
        this.frecuencia = "";
        this.duracion = 0;
        this.indicacion = "";
        this.indicacion_no = "";
        this.control_signos="";
        this.aislamiento="";
        this.alergias="";
        this.otros="";
        this.imagenes="";
        this.indicaciones_enfermeria="";
        this.indicaciones_kinesiologo="";
        this.indicaciones_nutricionista="";
        this.indicaciones_otros="";
        this.estado = 0;
        this.estado_desc = "";
        duracion_desc = "";
        rut_paciente = "";
        id_duo = 0;
        ip = "";
        dia_hospitalizacion=0;
        diagnostico = "";
        reposo = "";
        regimen = "";
        kinesiologia = "";
        aislamiento = "";
        contencion = "";
        dispositivo = "";
        hgt = "";
    }

    public int getId_via() {
        return id_via;
    }

    public void setId_via(int id_via) {
        this.id_via = id_via;
    }

    public String getControl_signos() {
        return control_signos;
    }

    public void setControl_signos(String control_signos) {
        this.control_signos = control_signos;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getImagenes() {
        return imagenes;
    }

    public void setImagenes(String imagenes) {
        this.imagenes = imagenes;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public String getIndicaciones_enfermeria() {
        return indicaciones_enfermeria;
    }

    public void setIndicaciones_enfermeria(String indicaciones_enfermeria) {
        this.indicaciones_enfermeria = indicaciones_enfermeria;
    }

    public String getIndicaciones_nutricionista() {
        return indicaciones_nutricionista;
    }

    public void setIndicaciones_nutricionista(String indicaciones_nutricionista) {
        this.indicaciones_nutricionista = indicaciones_nutricionista;
    }

    public String getIndicaciones_kinesiologo() {
        return indicaciones_kinesiologo;
    }

    public void setIndicaciones_kinesiologo(String indicaciones_kinesiologo) {
        this.indicaciones_kinesiologo = indicaciones_kinesiologo;
    }

    public String getIndicaciones_otros() {
        return indicaciones_otros;
    }

    public void setIndicaciones_otros(String indicaciones_otros) {
        this.indicaciones_otros = indicaciones_otros;
    }
    
    

    public Date getFechadate() {
        return fechadate;
    }

    public void setFechadate(Date fechadate) {
        this.fechadate = fechadate;
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
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
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
     * @return the medicamento
     */
    public int getMedicamento() {
        return medicamento;
    }

    /**
     * @param medicamento the medicamento to set
     */
    public void setMedicamento(int medicamento) {
        this.medicamento = medicamento;
    }

    /**
     * @return the medicamento_desc
     */
    public String getMedicamento_desc() {
        return medicamento_desc;
    }

    /**
     * @param medicamento_desc the medicamento_desc to set
     */
    public void setMedicamento_desc(String medicamento_desc) {
        this.medicamento_desc = medicamento_desc;
    }

    /**
     * @return the medida
     */
    public int getMedida() {
        return medida;
    }

    /**
     * @param medida the medida to set
     */
    public void setMedida(int medida) {
        this.medida = medida;
    }

    /**
     * @return the medida_desc
     */
    public String getMedida_desc() {
        return medida_desc;
    }

    /**
     * @param medida_desc the medida_desc to set
     */
    public void setMedida_desc(String medida_desc) {
        this.medida_desc = medida_desc;
    }

    /**
     * @return the cantidad
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the frecuencia
     */
    public String getFrecuencia() {
        return frecuencia;
    }

    /**
     * @param frecuencia the frecuencia to set
     */
    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    /**
     * @return the duracion
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the duracion_desc
     */
    public String getDuracion_desc() {
        return duracion_desc;
    }

    /**
     * @param duracion_desc the duracion_desc to set
     */
    public void setDuracion_desc(String duracion_desc) {
        this.duracion_desc = duracion_desc;
    }

    /**
     * @return the indicacion
     */
    public String getIndicacion() {
        return indicacion;
    }

    /**
     * @param indicacion the indicacion to set
     */
    public void setIndicacion(String indicacion) {
        this.indicacion = indicacion;
    }

    /**
     * @return the indicacion_no
     */
    public String getIndicacion_no() {
        return indicacion_no;
    }

    /**
     * @param indicacion_no the indicacion_no to set
     */
    public void setIndicacion_no(String indicacion_no) {
        this.indicacion_no = indicacion_no;
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
     * @return the rut_paciente
     */
    public String getRut_paciente() {
        return rut_paciente;
    }

    /**
     * @param rut_paciente the rut_paciente to set
     */
    public void setRut_paciente(String rut_paciente) {
        this.rut_paciente = rut_paciente;
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
     * @return the dosis
     */
    public int getDosis() {
        return dosis;
    }

    /**
     * @param dosis the dosis to set
     */
    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    /**
     * @return the dosis_desc
     */
    public String getDosis_desc() {
        return dosis_desc;
    }

    /**
     * @param dosis_desc the dosis_desc to set
     */
    public void setDosis_desc(String dosis_desc) {
        this.dosis_desc = dosis_desc;
    }

    /**
     * @return the id_receta_detalle
     */
    public int getId_receta_detalle() {
        return id_receta_detalle;
    }

    /**
     * @param id_receta_detalle the id_receta_detalle to set
     */
    public void setId_receta_detalle(int id_receta_detalle) {
        this.id_receta_detalle = id_receta_detalle;
    }

    /**
     * @return the id_receta
     */
    public int getId_receta() {
        return id_receta;
    }

    /**
     * @param id_receta the id_receta to set
     */
    public void setId_receta(int id_receta) {
        this.id_receta = id_receta;
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

    /**
     * @return the reposo
     */
    public String getReposo() {
        return reposo;
    }

    /**
     * @param reposo the reposo to set
     */
    public void setReposo(String reposo) {
        this.reposo = reposo;
    }

    /**
     * @return the regimen
     */
    public String getRegimen() {
        return regimen;
    }

    /**
     * @param regimen the regimen to set
     */
    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    /**
     * @return the kinesiologia
     */
    public String getKinesiologia() {
        return kinesiologia;
    }

    /**
     * @param kinesiologia the kinesiologia to set
     */
    public void setKinesiologia(String kinesiologia) {
        this.kinesiologia = kinesiologia;
    }

    /**
     * @return the aislamiento
     */
    public String getAislamiento() {
        return aislamiento;
    }

    /**
     * @param aislamiento the aislamiento to set
     */
    public void setAislamiento(String aislamiento) {
        this.aislamiento = aislamiento;
    }

    /**
     * @return the contencion
     */
    public String getContencion() {
        return contencion;
    }

    /**
     * @param contencion the contencion to set
     */
    public void setContencion(String contencion) {
        this.contencion = contencion;
    }

    /**
     * @return the dispositivo
     */
    public String getDispositivo() {
        return dispositivo;
    }

    /**
     * @param dispositivo the dispositivo to set
     */
    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    /**
     * @return the hgt
     */
    public String getHgt() {
        return hgt;
    }

    /**
     * @param hgt the hgt to set
     */
    public void setHgt(String hgt) {
        this.hgt = hgt;
    }

    /**
     * @return the dia_hospitalizacion
     */
    public int getDia_hospitalizacion() {
        return dia_hospitalizacion;
    }

    /**
     * @param dia_hospitalizacion the dia_hospitalizacion to set
     */
    public void setDia_hospitalizacion(int dia_hospitalizacion) {
        this.dia_hospitalizacion = dia_hospitalizacion;
    }

}
