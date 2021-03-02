/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author EseGamboa
 */
public class cVistaUoce {

    private int id_duo;
    private int id_epicrisis;
    private int estado_duo;
    private String paciente_rut;
    private String paciente_nombres;
    private String paciente_apellidop;
    private String paciente_apellidom;
    private String paciente_sexo;
    private String paciente_fecha_nac;
    private String paciente_edad;
    private String paciente_consultorio_pertenencia;
    private String codigo_fonasa_descripcion;
    private String tramo_prevision_paciente;
    private String resumen_epicrisis;
    private String diagnostico_epicrisis;
    private String examen_epicrisis;
    private String indicacion_epicrisis;
    private String descripcion_derivador;
    private String descripcion_destino;
    private String fecha_duo;
    private String fecha_epicrisis;
    private String fecha_hora_alta_adm;
    private String fecha_hora_alta_med;
    private String qdias_epi_duo;
    private String qdias_altaadm_duo;
    private int fecha_dias; // dias desde creacion del duo hasta alta medica confirmada
    private String rut_usuario;
    private String nombre_usuario;
    private String apellidop_usuario;
    private String apellidom_usuario;
    private String fecha_ingreso_enfermeria;
    private String rut_medico_ingresa;
    private String rut_medico_egresa;
    private String rut_enfermera_ingresa;
    private String fecha_hora_ingresa_enfermera;
    private String fecha_hora_ingreso_medico;
    
    



    public cVistaUoce( ) {
        this.id_duo =0;
        this.id_epicrisis=0;
        this.estado_duo = 0;
        this.paciente_rut = "";
        this.paciente_nombres = "";
        this.paciente_apellidop = "";
        this.paciente_apellidom = "";
        this.paciente_sexo = "";
        this.paciente_fecha_nac = "";
        this.paciente_edad = "";
        this.paciente_consultorio_pertenencia ="";
        this.codigo_fonasa_descripcion = "";
        this.tramo_prevision_paciente = "";
        this.resumen_epicrisis ="";
        this.diagnostico_epicrisis = "";
        this.examen_epicrisis = "";
        this.indicacion_epicrisis = "";
        this.descripcion_derivador = "";
        this.descripcion_destino = "";
        this.fecha_duo = "";
        this.fecha_epicrisis = "";
        this.fecha_hora_alta_adm = "";
        fecha_hora_alta_med="";
        this.qdias_epi_duo = "";
        this.qdias_altaadm_duo = "";
        this.fecha_dias =0;
        this.rut_usuario = "";
        this.nombre_usuario = "";
        this.apellidop_usuario ="";
        this.apellidom_usuario = "";
        fecha_ingreso_enfermeria="";
    }

    public String getFecha_hora_ingreso_medico() {
        return fecha_hora_ingreso_medico;
    }

    public void setFecha_hora_ingreso_medico(String fecha_hora_ingreso_medico) {
        this.fecha_hora_ingreso_medico = fecha_hora_ingreso_medico;
    }

    
    
    public String getRut_medico_ingresa() {
        return rut_medico_ingresa;
    }

    public void setRut_medico_ingresa(String rut_medico_ingresa) {
        this.rut_medico_ingresa = rut_medico_ingresa;
    }

    public String getRut_medico_egresa() {
        return rut_medico_egresa;
    }

    public void setRut_medico_egresa(String rut_medico_egresa) {
        this.rut_medico_egresa = rut_medico_egresa;
    }

    public String getRut_enfermera_ingresa() {
        return rut_enfermera_ingresa;
    }

    public void setRut_enfermera_ingresa(String rut_enfermera_ingresa) {
        this.rut_enfermera_ingresa = rut_enfermera_ingresa;
    }

    public String getFecha_hora_ingresa_enfermera() {
        return fecha_hora_ingresa_enfermera;
    }

    public void setFecha_hora_ingresa_enfermera(String fecha_hora_ingresa_enfermera) {
        this.fecha_hora_ingresa_enfermera = fecha_hora_ingresa_enfermera;
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
     * @return the estado_duo
     */
    public int getEstado_duo() {
        return estado_duo;
    }

    /**
     * @param estado_duo the estado_duo to set
     */
    public void setEstado_duo(int estado_duo) {
        this.estado_duo = estado_duo;
    }

    /**
     * @return the paciente_rut
     */
    public String getPaciente_rut() {
        return paciente_rut;
    }

    /**
     * @param paciente_rut the paciente_rut to set
     */
    public void setPaciente_rut(String paciente_rut) {
        this.paciente_rut = paciente_rut;
    }

    /**
     * @return the paciente_nombres
     */
    public String getPaciente_nombres() {
        return paciente_nombres;
    }

    /**
     * @param paciente_nombres the paciente_nombres to set
     */
    public void setPaciente_nombres(String paciente_nombres) {
        this.paciente_nombres = paciente_nombres;
    }

    /**
     * @return the paciente_apellidop
     */
    public String getPaciente_apellidop() {
        return paciente_apellidop;
    }

    /**
     * @param paciente_apellidop the paciente_apellidop to set
     */
    public void setPaciente_apellidop(String paciente_apellidop) {
        this.paciente_apellidop = paciente_apellidop;
    }

    /**
     * @return the paciente_apellidom
     */
    public String getPaciente_apellidom() {
        return paciente_apellidom;
    }

    /**
     * @param paciente_apellidom the paciente_apellidom to set
     */
    public void setPaciente_apellidom(String paciente_apellidom) {
        this.paciente_apellidom = paciente_apellidom;
    }

    /**
     * @return the paciente_sexo
     */
    public String getPaciente_sexo() {
        return paciente_sexo;
    }

    /**
     * @param paciente_sexo the paciente_sexo to set
     */
    public void setPaciente_sexo(String paciente_sexo) {
        this.paciente_sexo = paciente_sexo;
    }

    /**
     * @return the paciente_fecha_nac
     */
    public String getPaciente_fecha_nac() {
        return paciente_fecha_nac;
    }

    /**
     * @param paciente_fecha_nac the paciente_fecha_nac to set
     */
    public void setPaciente_fecha_nac(String paciente_fecha_nac) {
        this.paciente_fecha_nac = paciente_fecha_nac;
    }

    /**
     * @return the paciente_edad
     */
    public String getPaciente_edad() {
        return paciente_edad;
    }

    /**
     * @param paciente_edad the paciente_edad to set
     */
    public void setPaciente_edad(String paciente_edad) {
        this.paciente_edad = paciente_edad;
    }

    /**
     * @return the paciente_consultorio_pertenencua
     */
    public String getPaciente_consultorio_pertenencia() {
        return paciente_consultorio_pertenencia;
    }

    /**
     * @param paciente_consultorio_pertenencua the paciente_consultorio_pertenencua to set
     */
    public void setPaciente_consultorio_pertenencia(String paciente_consultorio_pertenencia) {
        this.paciente_consultorio_pertenencia = paciente_consultorio_pertenencia;
    }

    /**
     * @return the codigo_fonasa_descripcion
     */
    public String getCodigo_fonasa_descripcion() {
        return codigo_fonasa_descripcion;
    }

    /**
     * @param codigo_fonasa_descripcion the codigo_fonasa_descripcion to set
     */
    public void setCodigo_fonasa_descripcion(String codigo_fonasa_descripcion) {
        this.codigo_fonasa_descripcion = codigo_fonasa_descripcion;
    }

    /**
     * @return the tramo_prevision_paciente
     */
    public String getTramo_prevision_paciente() {
        return tramo_prevision_paciente;
    }

    /**
     * @param tramo_prevision_paciente the tramo_prevision_paciente to set
     */
    public void setTramo_prevision_paciente(String tramo_prevision_paciente) {
        this.tramo_prevision_paciente = tramo_prevision_paciente;
    }

    /**
     * @return the resumen_epicrisis
     */
    public String getResumen_epicrisis() {
        return resumen_epicrisis;
    }

    /**
     * @param resumen_epicrisis the resumen_epicrisis to set
     */
    public void setResumen_epicrisis(String resumen_epicrisis) {
        this.resumen_epicrisis = resumen_epicrisis;
    }

    /**
     * @return the diagnostico_epicrisis
     */
    public String getDiagnostico_epicrisis() {
        return diagnostico_epicrisis;
    }

    /**
     * @param diagnostico_epicrisis the diagnostico_epicrisis to set
     */
    public void setDiagnostico_epicrisis(String diagnostico_epicrisis) {
        this.diagnostico_epicrisis = diagnostico_epicrisis;
    }

    /**
     * @return the examen_epicrisis
     */
    public String getExamen_epicrisis() {
        return examen_epicrisis;
    }

    /**
     * @param examen_epicrisis the examen_epicrisis to set
     */
    public void setExamen_epicrisis(String examen_epicrisis) {
        this.examen_epicrisis = examen_epicrisis;
    }

    /**
     * @return the indicacion_epicrisis
     */
    public String getIndicacion_epicrisis() {
        return indicacion_epicrisis;
    }

    /**
     * @param indicacion_epicrisis the indicacion_epicrisis to set
     */
    public void setIndicacion_epicrisis(String indicacion_epicrisis) {
        this.indicacion_epicrisis = indicacion_epicrisis;
    }

    /**
     * @return the descripcion_derivador
     */
    public String getDescripcion_derivador() {
        return descripcion_derivador;
    }

    /**
     * @param descripcion_derivador the descripcion_derivador to set
     */
    public void setDescripcion_derivador(String descripcion_derivador) {
        this.descripcion_derivador = descripcion_derivador;
    }

    /**
     * @return the descripcion_destino
     */
    public String getDescripcion_destino() {
        return descripcion_destino;
    }

    /**
     * @param descripcion_destino the descripcion_destino to set
     */
    public void setDescripcion_destino(String descripcion_destino) {
        this.descripcion_destino = descripcion_destino;
    }

    /**
     * @return the fecha_duo
     */
    public String getFecha_duo() {
        return fecha_duo;
    }

    /**
     * @param fecha_duo the fecha_duo to set
     */
    public void setFecha_duo(String fecha_duo) {
        this.fecha_duo = fecha_duo;
    }

    /**
     * @return the fecha_epicrisis
     */
    public String getFecha_epicrisis() {
        return fecha_epicrisis;
    }

    /**
     * @param fecha_epicrisis the fecha_epicrisis to set
     */
    public void setFecha_epicrisis(String fecha_epicrisis) {
        this.fecha_epicrisis = fecha_epicrisis;
    }

    /**
     * @return the fecha_hora_alta_adm
     */
    public String getFecha_hora_alta_adm() {
        return fecha_hora_alta_adm;
    }

    /**
     * @param fecha_hora_alta_adm the fecha_hora_alta_adm to set
     */
    public void setFecha_hora_alta_adm(String fecha_hora_alta_adm) {
        this.fecha_hora_alta_adm = fecha_hora_alta_adm;
    }

    /**
     * @return the qdias_epi_duo
     */
    public String getQdias_epi_duo() {
        return qdias_epi_duo;
    }

    /**
     * @param qdias_epi_duo the qdias_epi_duo to set
     */
    public void setQdias_epi_duo(String qdias_epi_duo) {
        this.qdias_epi_duo = qdias_epi_duo;
    }

    /**
     * @return the qdias_altaadm_duo
     */
    public String getQdias_altaadm_duo() {
        return qdias_altaadm_duo;
    }

    /**
     * @param qdias_altaadm_duo the qdias_altaadm_duo to set
     */
    public void setQdias_altaadm_duo(String qdias_altaadm_duo) {
        this.qdias_altaadm_duo = qdias_altaadm_duo;
    }

    /**
     * @return the fecha_dias
     */
    public int getFecha_dias() {
        return fecha_dias;
    }

    /**
     * @param fecha_dias the fecha_dias to set
     */
    public void setFecha_dias(int fecha_dias) {
        this.fecha_dias = fecha_dias;
    }

    /**
     * @return the rut_usuario
     */
    public String getRut_usuario() {
        return rut_usuario;
    }

    /**
     * @param rut_usuario the rut_usuario to set
     */
    public void setRut_usuario(String rut_usuario) {
        this.rut_usuario = rut_usuario;
    }

    /**
     * @return the nombre_usuario
     */
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    /**
     * @param nombre_usuario the nombre_usuario to set
     */
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    /**
     * @return the apellidop_usuario
     */
    public String getApellidop_usuario() {
        return apellidop_usuario;
    }

    /**
     * @param apellidop_usuario the apellidop_usuario to set
     */
    public void setApellidop_usuario(String apellidop_usuario) {
        this.apellidop_usuario = apellidop_usuario;
    }

    /**
     * @return the apellidom_usuario
     */
    public String getApellidom_usuario() {
        return apellidom_usuario;
    }

    /**
     * @param apellidom_usuario the apellidom_usuario to set
     */
    public void setApellidom_usuario(String apellidom_usuario) {
        this.apellidom_usuario = apellidom_usuario;
    }

    /**
     * @return the id_epicrisis
     */
    public int getId_epicrisis() {
        return id_epicrisis;
    }

    /**
     * @param id_epicrisis the id_epicrisis to set
     */
    public void setId_epicrisis(int id_epicrisis) {
        this.id_epicrisis = id_epicrisis;
    }

    /**
     * @return the fecha_ingreso_enfermeria
     */
    public String getFecha_ingreso_enfermeria() {
        return fecha_ingreso_enfermeria;
    }

    /**
     * @param fecha_ingreso_enfermeria the fecha_ingreso_enfermeria to set
     */
    public void setFecha_ingreso_enfermeria(String fecha_ingreso_enfermeria) {
        this.fecha_ingreso_enfermeria = fecha_ingreso_enfermeria;
    }

    /**
     * @return the fecha_hora_alta_med
     */
    public String getFecha_hora_alta_med() {
        return fecha_hora_alta_med;
    }

    /**
     * @param fecha_hora_alta_med the fecha_hora_alta_med to set
     */
    public void setFecha_hora_alta_med(String fecha_hora_alta_med) {
        this.fecha_hora_alta_med = fecha_hora_alta_med;
    }


    



}
