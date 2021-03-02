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
public class cVisita {

    private int id_visita_categorizacion;

    private int d1_visita_categorizacion;
    private int d2_visita_categorizacion;
    private int d3_visita_categorizacion;
    private int d4_visita_categorizacion;
    private int d5_visita_categorizacion;
    private int d6_visita_categorizacion;
    private int r1_visita_categorizacion;
    private int r2_visita_categorizacion;
    private int r3_visita_categorizacion;
    private int r4_visita_categorizacion;
    private int r5_visita_categorizacion;
    private int r6_visita_categorizacion;
    private int r7_visita_categorizacion;
    private int r8_visita_categorizacion;

    private String d1_desc;
    private String d2_desc;
    private String d3_desc;
    private String d4_desc;
    private String d5_desc;
    private String d6_desc;
    private String r1_desc;
    private String r2_desc;
    private String r3_desc;
    private String r4_desc;
    private String r5_desc;
    private String r6_desc;
    private String r7_desc;
    private String r8_desc;
    private Date fecha;
    private String cat_visita_categorizacion;
    private String obs_visita;
    private String fecha_visita;
    private String hora_visita;
    private String rut_usuario;
    private String nombre_usuario;
    private String apellidop_usuario;
    private String apellidom_usuario;
    private int id_cama;
    private String descripcion_cama;
    private int id_duo;
    private int tipo_visita;
    private String rut_paciente;
    private String nombre_paciente;
    
    
    private String fecha_ingreso_visita;
    private String dia_visita;

    public cVisita() {
        this.id_visita_categorizacion = -1;
        this.d1_visita_categorizacion = -1;
        this.d2_visita_categorizacion = -1;
        this.d3_visita_categorizacion = -1;
        this.d4_visita_categorizacion = -1;
        this.d5_visita_categorizacion = -1;
        this.d6_visita_categorizacion = -1;
        this.r1_visita_categorizacion = -1;
        this.r2_visita_categorizacion = -1;
        this.r3_visita_categorizacion = -1;
        this.r4_visita_categorizacion = -1;
        this.r5_visita_categorizacion = -1;
        this.r6_visita_categorizacion = -1;
        this.r7_visita_categorizacion = -1;
        this.r8_visita_categorizacion = -1;
        this.cat_visita_categorizacion = "";
        this.obs_visita = "";
        this.fecha_visita = "";
        this.hora_visita = "";
        this.rut_usuario = "";
        this.nombre_usuario = "";
        this.apellidop_usuario = "";
        this.apellidom_usuario = "";
        this.id_cama = -1;
        this.descripcion_cama = "";
        this.id_duo = -1;
        this.tipo_visita = -1;
        fecha_ingreso_visita="";
dia_visita="";
        d1_desc = "";
        d2_desc = "";
        d3_desc = "";
        d4_desc = "";
        d5_desc = "";
        d6_desc = "";
        r1_desc = "";
        r2_desc = "";
        r3_desc = "";
        r4_desc = "";
        r5_desc = "";
        r6_desc = "";
        r7_desc = "";
        r8_desc = "";
    }

    public String getRut_paciente() {
        return rut_paciente;
    }

    public void setRut_paciente(String rut_paciente) {
        this.rut_paciente = rut_paciente;
    }

    public String getNombre_paciente() {
        return nombre_paciente;
    }

    public void setNombre_paciente(String nombre_paciente) {
        this.nombre_paciente = nombre_paciente;
    }
    
    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    

    /**
     * @return the id_visita_categorizacion
     */
    public int getId_visita_categorizacion() {
        return id_visita_categorizacion;
    }

    /**
     * @param id_visita_categorizacion the id_visita_categorizacion to set
     */
    public void setId_visita_categorizacion(int id_visita_categorizacion) {
        this.id_visita_categorizacion = id_visita_categorizacion;
    }

    /**
     * @return the d1_visita_categorizacion
     */
    public int getD1_visita_categorizacion() {
        return d1_visita_categorizacion;
    }

    /**
     * @param d1_visita_categorizacion the d1_visita_categorizacion to set
     */
    public void setD1_visita_categorizacion(int d1_visita_categorizacion) {
        this.d1_visita_categorizacion = d1_visita_categorizacion;
    }

    /**
     * @return the d2_visita_categorizacion
     */
    public int getD2_visita_categorizacion() {
        return d2_visita_categorizacion;
    }

    /**
     * @param d2_visita_categorizacion the d2_visita_categorizacion to set
     */
    public void setD2_visita_categorizacion(int d2_visita_categorizacion) {
        this.d2_visita_categorizacion = d2_visita_categorizacion;
    }

    /**
     * @return the d3_visita_categorizacion
     */
    public int getD3_visita_categorizacion() {
        return d3_visita_categorizacion;
    }

    /**
     * @param d3_visita_categorizacion the d3_visita_categorizacion to set
     */
    public void setD3_visita_categorizacion(int d3_visita_categorizacion) {
        this.d3_visita_categorizacion = d3_visita_categorizacion;
    }

    /**
     * @return the d4_visita_categorizacion
     */
    public int getD4_visita_categorizacion() {
        return d4_visita_categorizacion;
    }

    /**
     * @param d4_visita_categorizacion the d4_visita_categorizacion to set
     */
    public void setD4_visita_categorizacion(int d4_visita_categorizacion) {
        this.d4_visita_categorizacion = d4_visita_categorizacion;
    }

    /**
     * @return the d5_visita_categorizacion
     */
    public int getD5_visita_categorizacion() {
        return d5_visita_categorizacion;
    }

    /**
     * @param d5_visita_categorizacion the d5_visita_categorizacion to set
     */
    public void setD5_visita_categorizacion(int d5_visita_categorizacion) {
        this.d5_visita_categorizacion = d5_visita_categorizacion;
    }

    /**
     * @return the d6_visita_categorizacion
     */
    public int getD6_visita_categorizacion() {
        return d6_visita_categorizacion;
    }

    /**
     * @param d6_visita_categorizacion the d6_visita_categorizacion to set
     */
    public void setD6_visita_categorizacion(int d6_visita_categorizacion) {
        this.d6_visita_categorizacion = d6_visita_categorizacion;
    }

    /**
     * @return the r1_visita_categorizacion
     */
    public int getR1_visita_categorizacion() {
        return r1_visita_categorizacion;
    }

    /**
     * @param r1_visita_categorizacion the r1_visita_categorizacion to set
     */
    public void setR1_visita_categorizacion(int r1_visita_categorizacion) {
        this.r1_visita_categorizacion = r1_visita_categorizacion;
    }

    /**
     * @return the r2_visita_categorizacion
     */
    public int getR2_visita_categorizacion() {
        return r2_visita_categorizacion;
    }

    /**
     * @param r2_visita_categorizacion the r2_visita_categorizacion to set
     */
    public void setR2_visita_categorizacion(int r2_visita_categorizacion) {
        this.r2_visita_categorizacion = r2_visita_categorizacion;
    }

    /**
     * @return the r3_visita_categorizacion
     */
    public int getR3_visita_categorizacion() {
        return r3_visita_categorizacion;
    }

    /**
     * @param r3_visita_categorizacion the r3_visita_categorizacion to set
     */
    public void setR3_visita_categorizacion(int r3_visita_categorizacion) {
        this.r3_visita_categorizacion = r3_visita_categorizacion;
    }

    /**
     * @return the r4_visita_categorizacion
     */
    public int getR4_visita_categorizacion() {
        return r4_visita_categorizacion;
    }

    /**
     * @param r4_visita_categorizacion the r4_visita_categorizacion to set
     */
    public void setR4_visita_categorizacion(int r4_visita_categorizacion) {
        this.r4_visita_categorizacion = r4_visita_categorizacion;
    }

    /**
     * @return the r5_visita_categorizacion
     */
    public int getR5_visita_categorizacion() {
        return r5_visita_categorizacion;
    }

    /**
     * @param r5_visita_categorizacion the r5_visita_categorizacion to set
     */
    public void setR5_visita_categorizacion(int r5_visita_categorizacion) {
        this.r5_visita_categorizacion = r5_visita_categorizacion;
    }

    /**
     * @return the r6_visita_categorizacion
     */
    public int getR6_visita_categorizacion() {
        return r6_visita_categorizacion;
    }

    /**
     * @param r6_visita_categorizacion the r6_visita_categorizacion to set
     */
    public void setR6_visita_categorizacion(int r6_visita_categorizacion) {
        this.r6_visita_categorizacion = r6_visita_categorizacion;
    }

    /**
     * @return the r7_visita_categorizacion
     */
    public int getR7_visita_categorizacion() {
        return r7_visita_categorizacion;
    }

    /**
     * @param r7_visita_categorizacion the r7_visita_categorizacion to set
     */
    public void setR7_visita_categorizacion(int r7_visita_categorizacion) {
        this.r7_visita_categorizacion = r7_visita_categorizacion;
    }

    /**
     * @return the r8_visita_categorizacion
     */
    public int getR8_visita_categorizacion() {
        return r8_visita_categorizacion;
    }

    /**
     * @param r8_visita_categorizacion the r8_visita_categorizacion to set
     */
    public void setR8_visita_categorizacion(int r8_visita_categorizacion) {
        this.r8_visita_categorizacion = r8_visita_categorizacion;
    }

    /**
     * @return the cat_visita_categorizacion
     */
    public String getCat_visita_categorizacion() {
        return cat_visita_categorizacion;
    }

    /**
     * @param cat_visita_categorizacion the cat_visita_categorizacion to set
     */
    public void setCat_visita_categorizacion(String cat_visita_categorizacion) {
        this.cat_visita_categorizacion = cat_visita_categorizacion;
    }

    /**
     * @return the obs_visita
     */
    public String getObs_visita() {
        return obs_visita;
    }

    /**
     * @param obs_visita the obs_visita to set
     */
    public void setObs_visita(String obs_visita) {
        this.obs_visita = obs_visita;
    }

    /**
     * @return the fecha_visita
     */
    public String getFecha_visita() {
        return fecha_visita;
    }

    /**
     * @param fecha_visita the fecha_visita to set
     */
    public void setFecha_visita(String fecha_visita) {
        this.fecha_visita = fecha_visita;
    }

    /**
     * @return the hora_visita
     */
    public String getHora_visita() {
        return hora_visita;
    }

    /**
     * @param hora_visita the hora_visita to set
     */
    public void setHora_visita(String hora_visita) {
        this.hora_visita = hora_visita;
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
     * @return the tipo_visita
     */
    public int getTipo_visita() {
        return tipo_visita;
    }

    /**
     * @param tipo_visita the tipo_visita to set
     */
    public void setTipo_visita(int tipo_visita) {
        this.tipo_visita = tipo_visita;
    }

    /**
     * @return the d1_desc
     */
    public String getD1_desc() {
        return d1_desc;
    }

    /**
     * @param d1_desc the d1_desc to set
     */
    public void setD1_desc(String d1_desc) {
        this.d1_desc = d1_desc;
    }

    /**
     * @return the d2_desc
     */
    public String getD2_desc() {
        return d2_desc;
    }

    /**
     * @param d2_desc the d2_desc to set
     */
    public void setD2_desc(String d2_desc) {
        this.d2_desc = d2_desc;
    }

    /**
     * @return the d3_desc
     */
    public String getD3_desc() {
        return d3_desc;
    }

    /**
     * @param d3_desc the d3_desc to set
     */
    public void setD3_desc(String d3_desc) {
        this.d3_desc = d3_desc;
    }

    /**
     * @return the d4_desc
     */
    public String getD4_desc() {
        return d4_desc;
    }

    /**
     * @param d4_desc the d4_desc to set
     */
    public void setD4_desc(String d4_desc) {
        this.d4_desc = d4_desc;
    }

    /**
     * @return the d5_desc
     */
    public String getD5_desc() {
        return d5_desc;
    }

    /**
     * @param d5_desc the d5_desc to set
     */
    public void setD5_desc(String d5_desc) {
        this.d5_desc = d5_desc;
    }

    /**
     * @return the d6_desc
     */
    public String getD6_desc() {
        return d6_desc;
    }

    /**
     * @param d6_desc the d6_desc to set
     */
    public void setD6_desc(String d6_desc) {
        this.d6_desc = d6_desc;
    }

    /**
     * @return the r1_desc
     */
    public String getR1_desc() {
        return r1_desc;
    }

    /**
     * @param r1_desc the r1_desc to set
     */
    public void setR1_desc(String r1_desc) {
        this.r1_desc = r1_desc;
    }

    /**
     * @return the r2_desc
     */
    public String getR2_desc() {
        return r2_desc;
    }

    /**
     * @param r2_desc the r2_desc to set
     */
    public void setR2_desc(String r2_desc) {
        this.r2_desc = r2_desc;
    }

    /**
     * @return the r3_desc
     */
    public String getR3_desc() {
        return r3_desc;
    }

    /**
     * @param r3_desc the r3_desc to set
     */
    public void setR3_desc(String r3_desc) {
        this.r3_desc = r3_desc;
    }

    /**
     * @return the r4_desc
     */
    public String getR4_desc() {
        return r4_desc;
    }

    /**
     * @param r4_desc the r4_desc to set
     */
    public void setR4_desc(String r4_desc) {
        this.r4_desc = r4_desc;
    }

    /**
     * @return the r5_desc
     */
    public String getR5_desc() {
        return r5_desc;
    }

    /**
     * @param r5_desc the r5_desc to set
     */
    public void setR5_desc(String r5_desc) {
        this.r5_desc = r5_desc;
    }

    /**
     * @return the r6_desc
     */
    public String getR6_desc() {
        return r6_desc;
    }

    /**
     * @param r6_desc the r6_desc to set
     */
    public void setR6_desc(String r6_desc) {
        this.r6_desc = r6_desc;
    }

    /**
     * @return the r7_desc
     */
    public String getR7_desc() {
        return r7_desc;
    }

    /**
     * @param r7_desc the r7_desc to set
     */
    public void setR7_desc(String r7_desc) {
        this.r7_desc = r7_desc;
    }

    /**
     * @return the r8_desc
     */
    public String getR8_desc() {
        return r8_desc;
    }

    /**
     * @param r8_desc the r8_desc to set
     */
    public void setR8_desc(String r8_desc) {
        this.r8_desc = r8_desc;
    }

    /**
     * @return the fecha_ingreso_visita
     */
    public String getFecha_ingreso_visita() {
        return fecha_ingreso_visita;
    }

    /**
     * @param fecha_ingreso_visita the fecha_ingreso_visita to set
     */
    public void setFecha_ingreso_visita(String fecha_ingreso_visita) {
        this.fecha_ingreso_visita = fecha_ingreso_visita;
    }

    /**
     * @return the dia_visita
     */
    public String getDia_visita() {
        return dia_visita;
    }

    /**
     * @param dia_visita the dia_visita to set
     */
    public void setDia_visita(String dia_visita) {
        this.dia_visita = dia_visita;
    }

}
