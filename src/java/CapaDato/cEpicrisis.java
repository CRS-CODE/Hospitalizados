/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author EseGamboa
 */
public class cEpicrisis {
    /*nota: hacer extends a cPaciente y reemplazar mutadores y accesadores correspondientes*/

    private int id_epicrisis;
    private String resumen_epicrisis;
    private String examen_epicrisis;
    private String diagnostico_epicrisis;
    private String indicacion_epicrisis;
    private String fecha_epicrisis;
    private String hora_epicrisis;
    private String rut_usuario;
    private int id_duo;
    private String fecha_hora_epicrisis;
    private String nombre_usuario;
    private String apellidop_usuario;
    private String apellidom_usuario;
    private String paciente_nombres;
    private String paciente_apellidop;
    private String paciente_apellidom;
    private String fecha_duo;
    private String ip_epicrisis;
    private int estado_duo;
    private int prevision;
    private String prevision_descri;
    private String Tramo;
    private int prais;
    private String prais_descri;
    private String medicamentos_prescritos;
    
    private String rut_usuario_epicrisis;

    public cEpicrisis() {
        this.id_epicrisis = -1;
        this.resumen_epicrisis = "";
        this.examen_epicrisis = "";
        this.diagnostico_epicrisis = "";
        this.indicacion_epicrisis = "";
        this.fecha_epicrisis = "";
        this.hora_epicrisis = "";
        this.rut_usuario = "";
        this.id_duo = -1;
        this.fecha_hora_epicrisis = "";
        this.nombre_usuario = "";
        this.apellidop_usuario = "";
        this.apellidom_usuario = "";
        this.paciente_nombres = "";
        this.paciente_apellidop = "";
        this.paciente_apellidom = "";
        this.medicamentos_prescritos="";
        fecha_duo = "";
        ip_epicrisis = "";
        estado_duo = 0;

        prevision = 0;
        prevision_descri = "";
        Tramo = "";
        prais = 0;
        prais_descri = "";
        rut_usuario_epicrisis="";
    }


    public String getMedicamentos_prescritos() {
        return medicamentos_prescritos;
    }

    public void setMedicamentos_prescritos(String medicamentos_prescritos) {
        this.medicamentos_prescritos = medicamentos_prescritos;
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
     * @return the hora_epicrisis
     */
    public String getHora_epicrisis() {
        return hora_epicrisis;
    }

    /**
     * @param hora_epicrisis the hora_epicrisis to set
     */
    public void setHora_epicrisis(String hora_epicrisis) {
        this.hora_epicrisis = hora_epicrisis;
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
     * @return the fecha_hora_epicrisis
     */
    public String getFecha_hora_epicrisis() {
        return fecha_hora_epicrisis;
    }

    /**
     * @param fecha_hora_epicrisis the fecha_hora_epicrisis to set
     */
    public void setFecha_hora_epicrisis(String fecha_hora_epicrisis) {
        this.fecha_hora_epicrisis = fecha_hora_epicrisis;
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
     * @return the ip_epicrisis
     */
    public String getIp_epicrisis() {
        return ip_epicrisis;
    }

    /**
     * @param ip_epicrisis the ip_epicrisis to set
     */
    public void setIp_epicrisis(String ip_epicrisis) {
        this.ip_epicrisis = ip_epicrisis;
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
     * @return the prevision
     */
    public int getPrevision() {
        return prevision;
    }

    /**
     * @param prevision the prevision to set
     */
    public void setPrevision(int prevision) {
        this.prevision = prevision;
    }

    /**
     * @return the prevision_descri
     */
    public String getPrevision_descri() {
        return prevision_descri;
    }

    /**
     * @param prevision_descri the prevision_descri to set
     */
    public void setPrevision_descri(String prevision_descri) {
        this.prevision_descri = prevision_descri;
    }

    /**
     * @return the Tramo
     */
    public String getTramo() {
        return Tramo;
    }

    /**
     * @param Tramo the Tramo to set
     */
    public void setTramo(String Tramo) {
        this.Tramo = Tramo;
    }

    /**
     * @return the prais
     */
    public int getPrais() {
        return prais;
    }

    /**
     * @param prais the prais to set
     */
    public void setPrais(int prais) {
        this.prais = prais;
    }

    /**
     * @return the prais_descri
     */
    public String getPrais_descri() {
        return prais_descri;
    }

    /**
     * @param prais_descri the prais_descri to set
     */
    public void setPrais_descri(String prais_descri) {
        this.prais_descri = prais_descri;
    }

    /**
     * @return the rut_usuario_epicrisis
     */
    public String getRut_usuario_epicrisis() {
        return rut_usuario_epicrisis;
    }

    /**
     * @param rut_usuario_epicrisis the rut_usuario_epicrisis to set
     */
    public void setRut_usuario_epicrisis(String rut_usuario_epicrisis) {
        this.rut_usuario_epicrisis = rut_usuario_epicrisis;
    }
}
