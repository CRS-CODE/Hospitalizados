/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

import java.util.Date;

/**
 *
 * @author EseGamboa
 */
public class cDuo extends cPaciente {

    private int id_duo;
    private String fecha_duo;
    private String hora_duo;
    private int estado_duo;
    private String estado_duo_descripcion;
    private int cama;
//    private int id_prevision;
    private String fecha_hora_ing_duo;
    private String rut_usuario;
    private String anamnesis_duo;
    private int derivador_id;
    private int categorizacion_id;
    private int tipo_duo_id; // este campo siempre tiene valor 1, y nunca se ha utilizado ¬¬
    private String fecha_hora_alta_med_duo;
    private String fecha_hora_alta_adm_duo;
    private String rut_usuario_ing_med;
    private int tiene_enfermeria;
    private int sala;
    private String sala_descripcion;
    private String cama_descripcion;
    private String nombre_usuario_admision;
    private String nombre_usuario_ing_med;
    private String nombre_usuario_ing_enf;
    private String primera_clasificacion;
    private String ultima_clasificacion;
    private String ip_ing_enf;
    private String ip_ing_med;
    private String fecha_hora_ing_enf;
    private String fecha_hora_ing_med;
    private String derivador_descripcion;
    private String categorizacion_descripcion;
    private int dias_cama;
    private int dias_reales_cama;
    private String dias_epi_duo;
    private String dias_hasta_hoy;
    private int programa;
    private String programa_descripcion;
    private int dif_ss;
    private int dif_mm;
    private int dif_hh;
    private int dif_dd;
    private int dif_ss_enf;
    private int dif_mm_enf;
    private int dif_hh_enf;
    private int dif_dd_enf;
    private Date fecha;
    
 

    public cDuo() {
        this.id_duo = -1;
        this.fecha_duo = "";
        this.hora_duo = "";
        this.estado_duo = -1;
        this.cama = -1;
//        this.id_prevision = -1;
        this.fecha_hora_ing_duo = "";
        this.rut_usuario = "";
        this.anamnesis_duo = "";
        this.derivador_id = -1;
        this.categorizacion_id = -1;
        this.tipo_duo_id = -1;
        this.fecha_hora_alta_med_duo = "";
        this.fecha_hora_alta_adm_duo = "";
        this.rut_usuario_ing_med = "";
        this.tiene_enfermeria = -1;
        this.sala = -1;
        this.sala_descripcion = "";
        this.cama_descripcion = "";
        ultima_clasificacion = "";
        estado_duo_descripcion = "";
        primera_clasificacion = "";
        fecha_hora_ing_enf = "";
        fecha_hora_ing_med = "";
        ip_ing_enf = "";
        ip_ing_med = "";
        nombre_usuario_admision = "";
        nombre_usuario_ing_med = "";
        nombre_usuario_ing_enf = "";
        dias_cama = -1;

        dias_epi_duo = "";
        dias_hasta_hoy = "";
        programa = -1;
        programa_descripcion = "";

        dif_ss = -1;
        dif_mm = -1;
        dif_hh = -1;
        dif_dd = -1;

        dif_ss_enf = -1;
        dif_mm_enf = -1;
        dif_hh_enf = -1;
        dif_dd_enf = -1;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDias_reales_cama() {
        return dias_reales_cama;
    }

    public void setDias_reales_cama(int dias_reales_cama) {
        this.dias_reales_cama = dias_reales_cama;
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
     * @return the hora_duo
     */
    public String getHora_duo() {
        return hora_duo;
    }

    /**
     * @param hora_duo the hora_duo to set
     */
    public void setHora_duo(String hora_duo) {
        this.hora_duo = hora_duo;
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
     * @return the estado_duo_descripcion
     */
    public String getEstado_duo_descripcion() {
        return estado_duo_descripcion;
    }

    /**
     * @param estado_duo_descripcion the estado_duo_descripcion to set
     */
    public void setEstado_duo_descripcion(String estado_duo_descripcion) {
        this.estado_duo_descripcion = estado_duo_descripcion;
    }

    /**
     * @return the cama
     */
    public int getCama() {
        return cama;
    }

    /**
     * @param cama the cama to set
     */
    public void setCama(int cama) {
        this.cama = cama;
    }

//    /**
//     * @return the id_prevision
//     */
//    public int getId_prevision() {
//        return id_prevision;
//    }
//
//    /**
//     * @param id_prevision the id_prevision to set
//     */
//    public void setId_prevision(int id_prevision) {
//        this.id_prevision = id_prevision;
//    }
    /**
     * @return the fecha_hora_ing_duo
     */
    public String getFecha_hora_ing_duo() {
        return fecha_hora_ing_duo;
    }

    /**
     * @param fecha_hora_ing_duo the fecha_hora_ing_duo to set
     */
    public void setFecha_hora_ing_duo(String fecha_hora_ing_duo) {
        this.fecha_hora_ing_duo = fecha_hora_ing_duo;
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
     * @return the anamnesis_duo
     */
    public String getAnamnesis_duo() {
        return anamnesis_duo;
    }

    /**
     * @param anamnesis_duo the anamnesis_duo to set
     */
    public void setAnamnesis_duo(String anamnesis_duo) {
        this.anamnesis_duo = anamnesis_duo;
    }

    /**
     * @return the tipo_duo_id
     */
    public int getTipo_duo_id() {
        return tipo_duo_id;
    }

    /**
     * @param tipo_duo_id the tipo_duo_id to set
     */
    public void setTipo_duo_id(int tipo_duo_id) {
        this.tipo_duo_id = tipo_duo_id;
    }

    /**
     * @return the fecha_hora_alta_med_duo
     */
    public String getFecha_hora_alta_med_duo() {
        return fecha_hora_alta_med_duo;
    }

    /**
     * @param fecha_hora_alta_med_duo the fecha_hora_alta_med_duo to set
     */
    public void setFecha_hora_alta_med_duo(String fecha_hora_alta_med_duo) {
        this.fecha_hora_alta_med_duo = fecha_hora_alta_med_duo;
    }

    /**
     * @return the fecha_hora_alta_adm_duo
     */
    public String getFecha_hora_alta_adm_duo() {
        return fecha_hora_alta_adm_duo;
    }

    /**
     * @param fecha_hora_alta_adm_duo the fecha_hora_alta_adm_duo to set
     */
    public void setFecha_hora_alta_adm_duo(String fecha_hora_alta_adm_duo) {
        this.fecha_hora_alta_adm_duo = fecha_hora_alta_adm_duo;
    }

    /**
     * @return the rut_usuario_ing_med
     */
    public String getRut_usuario_ing_med() {
        return rut_usuario_ing_med;
    }

    /**
     * @param rut_usuario_ing_med the rut_usuario_ing_med to set
     */
    public void setRut_usuario_ing_med(String rut_usuario_ing_med) {
        this.rut_usuario_ing_med = rut_usuario_ing_med;
    }

    /**
     * @return the tiene_enfermeria
     */
    public int getTiene_enfermeria() {
        return tiene_enfermeria;
    }

    /**
     * @param tiene_enfermeria the tiene_enfermeria to set
     */
    public void setTiene_enfermeria(int tiene_enfermeria) {
        this.tiene_enfermeria = tiene_enfermeria;
    }

    /**
     * @return the sala
     */
    public int getSala() {
        return sala;
    }

    /**
     * @param sala the sala to set
     */
    public void setSala(int sala) {
        this.sala = sala;
    }

    /**
     * @return the sala_descripcion
     */
    public String getSala_descripcion() {
        return sala_descripcion;
    }

    /**
     * @param sala_descripcion the sala_descripcion to set
     */
    public void setSala_descripcion(String sala_descripcion) {
        this.sala_descripcion = sala_descripcion;
    }

    /**
     * @return the cama_descripcion
     */
    public String getCama_descripcion() {
        return cama_descripcion;
    }

    /**
     * @param cama_descripcion the cama_descripcion to set
     */
    public void setCama_descripcion(String cama_descripcion) {
        this.cama_descripcion = cama_descripcion;
    }

    /**
     * @return the nombre_usuario_admision
     */
    public String getNombre_usuario_admision() {
        return nombre_usuario_admision;
    }

    /**
     * @param nombre_usuario_admision the nombre_usuario_admision to set
     */
    public void setNombre_usuario_admision(String nombre_usuario_admision) {
        this.nombre_usuario_admision = nombre_usuario_admision;
    }

    /**
     * @return the nombre_usuario_ing_med
     */
    public String getNombre_usuario_ing_med() {
        return nombre_usuario_ing_med;
    }

    /**
     * @param nombre_usuario_ing_med the nombre_usuario_ing_med to set
     */
    public void setNombre_usuario_ing_med(String nombre_usuario_ing_med) {
        this.nombre_usuario_ing_med = nombre_usuario_ing_med;
    }

    /**
     * @return the nombre_usuario_ing_enf
     */
    public String getNombre_usuario_ing_enf() {
        return nombre_usuario_ing_enf;
    }

    /**
     * @param nombre_usuario_ing_enf the nombre_usuario_ing_enf to set
     */
    public void setNombre_usuario_ing_enf(String nombre_usuario_ing_enf) {
        this.nombre_usuario_ing_enf = nombre_usuario_ing_enf;
    }

    /**
     * @return the primera_clasificacion
     */
    public String getPrimera_clasificacion() {
        return primera_clasificacion;
    }

    /**
     * @param primera_clasificacion the primera_clasificacion to set
     */
    public void setPrimera_clasificacion(String primera_clasificacion) {
        this.primera_clasificacion = primera_clasificacion;
    }

    /**
     * @return the ultima_clasificacion
     */
    public String getUltima_clasificacion() {
        return ultima_clasificacion;
    }

    /**
     * @param ultima_clasificacion the ultima_clasificacion to set
     */
    public void setUltima_clasificacion(String ultima_clasificacion) {
        this.ultima_clasificacion = ultima_clasificacion;
    }

    /**
     * @return the ip_ing_enf
     */
    public String getIp_ing_enf() {
        return ip_ing_enf;
    }

    /**
     * @param ip_ing_enf the ip_ing_enf to set
     */
    public void setIp_ing_enf(String ip_ing_enf) {
        this.ip_ing_enf = ip_ing_enf;
    }

    /**
     * @return the ip_ing_med
     */
    public String getIp_ing_med() {
        return ip_ing_med;
    }

    /**
     * @param ip_ing_med the ip_ing_med to set
     */
    public void setIp_ing_med(String ip_ing_med) {
        this.ip_ing_med = ip_ing_med;
    }

    /**
     * @return the fecha_hora_ing_enf
     */
    public String getFecha_hora_ing_enf() {
        return fecha_hora_ing_enf;
    }

    /**
     * @param fecha_hora_ing_enf the fecha_hora_ing_enf to set
     */
    public void setFecha_hora_ing_enf(String fecha_hora_ing_enf) {
        this.fecha_hora_ing_enf = fecha_hora_ing_enf;
    }

    /**
     * @return the fecha_hora_ing_med
     */
    public String getFecha_hora_ing_med() {
        return fecha_hora_ing_med;
    }

    /**
     * @param fecha_hora_ing_med the fecha_hora_ing_med to set
     */
    public void setFecha_hora_ing_med(String fecha_hora_ing_med) {
        this.fecha_hora_ing_med = fecha_hora_ing_med;
    }

    /**
     * @return the dias_cama
     */
    public int getDias_cama() {
        return dias_cama;
    }

    /**
     * @param dias_cama the dias_cama to set
     */
    public void setDias_cama(int dias_cama) {
        this.dias_cama = dias_cama;
    }

    /**
     * @return the derivador_id
     */
    public int getDerivador_id() {
        return derivador_id;
    }

    /**
     * @param derivador_id the derivador_id to set
     */
    public void setDerivador_id(int derivador_id) {
        this.derivador_id = derivador_id;
    }

    /**
     * @return the categorizacion_id
     */
    public int getCategorizacion_id() {
        return categorizacion_id;
    }

    /**
     * @param categorizacion_id the categorizacion_id to set
     */
    public void setCategorizacion_id(int categorizacion_id) {
        this.categorizacion_id = categorizacion_id;
    }

    /**
     * @return the derivador_descripcion
     */
    public String getDerivador_descripcion() {
        return derivador_descripcion;
    }

    /**
     * @param derivador_descripcion the derivador_descripcion to set
     */
    public void setDerivador_descripcion(String derivador_descripcion) {
        this.derivador_descripcion = derivador_descripcion;
    }

    /**
     * @return the categorizacion_descripcion
     */
    public String getCategorizacion_descripcion() {
        return categorizacion_descripcion;
    }

    /**
     * @param categorizacion_descripcion the categorizacion_descripcion to set
     */
    public void setCategorizacion_descripcion(String categorizacion_descripcion) {
        this.categorizacion_descripcion = categorizacion_descripcion;
    }

    /**
     * @return the dias_epi_duo
     */
    public String getDias_epi_duo() {
        return dias_epi_duo;
    }

    /**
     * @param dias_epi_duo the dias_epi_duo to set
     */
    public void setDias_epi_duo(String dias_epi_duo) {
        this.dias_epi_duo = dias_epi_duo;
    }

    /**
     * @return the dias_hasta_hoy
     */
    public String getDias_hasta_hoy() {
        return dias_hasta_hoy;
    }

    /**
     * @param dias_hasta_hoy the dias_hasta_hoy to set
     */
    public void setDias_hasta_hoy(String dias_hasta_hoy) {
        this.dias_hasta_hoy = dias_hasta_hoy;
    }

    /**
     * @return the programa
     */
    public int getPrograma() {
        return programa;
    }

    /**
     * @param programa the programa to set
     */
    public void setPrograma(int programa) {
        this.programa = programa;
    }

    /**
     * @return the programa_descripcion
     */
    public String getPrograma_descripcion() {
        return programa_descripcion;
    }

    /**
     * @param programa_descripcion the programa_descripcion to set
     */
    public void setPrograma_descripcion(String programa_descripcion) {
        this.programa_descripcion = programa_descripcion;
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
     * @return the dif_ss_enf
     */
    public int getDif_ss_enf() {
        return dif_ss_enf;
    }

    /**
     * @param dif_ss_enf the dif_ss_enf to set
     */
    public void setDif_ss_enf(int dif_ss_enf) {
        this.dif_ss_enf = dif_ss_enf;
    }

    /**
     * @return the dif_mm_enf
     */
    public int getDif_mm_enf() {
        return dif_mm_enf;
    }

    /**
     * @param dif_mm_enf the dif_mm_enf to set
     */
    public void setDif_mm_enf(int dif_mm_enf) {
        this.dif_mm_enf = dif_mm_enf;
    }

    /**
     * @return the dif_hh_enf
     */
    public int getDif_hh_enf() {
        return dif_hh_enf;
    }

    /**
     * @param dif_hh_enf the dif_hh_enf to set
     */
    public void setDif_hh_enf(int dif_hh_enf) {
        this.dif_hh_enf = dif_hh_enf;
    }

    /**
     * @return the dif_dd_enf
     */
    public int getDif_dd_enf() {
        return dif_dd_enf;
    }

    /**
     * @param dif_dd_enf the dif_dd_enf to set
     */
    public void setDif_dd_enf(int dif_dd_enf) {
        this.dif_dd_enf = dif_dd_enf;
    }
}
