/*
 * Cama.java
 *
 * Created on 25 de julio de 2008, 12:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package CapaDato;

import java.util.Date;

/**
 *
 * @author Victor Carcamo
 */
public class Duo {
   private int id_duo; 
   private int estado_duo;
   private String paciente_rut;
   private String paciente_nombres;
   private String paciente_apellidop;
   private String paciente_apellidom;
   private String paciente_telefono1;
   private String paciente_telefono2;
   private String paciente_direccion;
   private String comuna_descripcion;
   private String descripcion_derivador;
   private int paciente_sexo;
   private String paciente_fecha_nac;
   private String paciente_edad;
   private Date fecha_duo;
   private String hora_duo;
   private String descripcion_cama;
   private String anamnesis_duo;
   private String descripcion_categorizacion;
   private String  codigo_fonasa_descripcion;
   private String  tramo_prevision_paciente;
   private String  rut_usuario;
   private String  nombre_usuario;
   private String  apellidop_usuario;
   private String  apellidom_usuario;
   
  
    /** Creates a new instance of Cama */
    public Duo() {
    }

    public String getPaciente_rut() {
        return paciente_rut;
    }

    public void setPaciente_rut(String paciente_rut) {
        this.paciente_rut = paciente_rut;
    }

    public String getPaciente_nombres() {
        return paciente_nombres;
    }

    public void setPaciente_nombres(String paciente_nombres) {
        this.paciente_nombres = paciente_nombres;
    }

    public String getPaciente_apellidop() {
        return paciente_apellidop;
    }

    public void setPaciente_apellidop(String paciente_apellidop) {
        this.paciente_apellidop = paciente_apellidop;
    }

    public String getPaciente_apellidom() {
        return paciente_apellidom;
    }

    public void setPaciente_apellidom(String paciente_apellidom) {
        this.paciente_apellidom = paciente_apellidom;
    }

    public int getPaciente_sexo() {
        return paciente_sexo;
    }

    public void setPaciente_sexo(int paciente_sexo) {
        this.paciente_sexo = paciente_sexo;
    }

    public String getPaciente_fecha_nac() {
        return paciente_fecha_nac;
    }

    public void setPaciente_fecha_nac(String paciente_fecha_nac) {
        this.paciente_fecha_nac = paciente_fecha_nac;
    }

    public Date getFecha_duo() {
        return fecha_duo;
    }

    public void setFecha_duo(Date fecha_duo) {
        this.fecha_duo = fecha_duo;
    }

    public String getHora_duo() {
        return hora_duo;
    }

    public void setHora_duo(String hora_duo) {
        this.hora_duo = hora_duo;
    }

    public String getDescripcion_cama() {
        return descripcion_cama;
    }

    public void setDescripcion_cama(String descripcion_cama) {
        this.descripcion_cama = descripcion_cama;
    }

    public String getAnamnesis_duo() {
        return anamnesis_duo;
    }

    public void setAnamnesis_duo(String anamnesis_duo) {
        this.anamnesis_duo = anamnesis_duo;
    }

    public String getDescripcion_categorizacion() {
        return descripcion_categorizacion;
    }

    public void setDescripcion_categorizacion(String descripcion_categorizacion) {
        this.descripcion_categorizacion = descripcion_categorizacion;
    }

    public int getId_duo() {
        return id_duo;
    }

    public void setId_duo(int id_duo) {
        this.id_duo = id_duo;
    }

    public String getPaciente_telefono1() {
        return paciente_telefono1;
    }

    public void setPaciente_telefono1(String paciente_telefono1) {
        this.paciente_telefono1 = paciente_telefono1;
    }

    public String getPaciente_telefono2() {
        return paciente_telefono2;
    }

    public void setPaciente_telefono2(String paciente_telefono2) {
        this.paciente_telefono2 = paciente_telefono2;
    }

    public String getPaciente_direccion() {
        return paciente_direccion;
    }

    public void setPaciente_direccion(String paciente_direccion) {
        this.paciente_direccion = paciente_direccion;
    }

    public String getComuna_descripcion() {
        return comuna_descripcion;
    }

    public void setComuna_descripcion(String comuna_descripcion) {
        this.comuna_descripcion = comuna_descripcion;
    }

    public String getDescripcion_derivador() {
        return descripcion_derivador;
    }

    public void setDescripcion_derivador(String descripcion_derivador) {
        this.descripcion_derivador = descripcion_derivador;
    }

    public String getCodigo_fonasa_descripcion() {
        return codigo_fonasa_descripcion;
    }

    public void setCodigo_fonasa_descripcion(String codigo_fonasa_descripcion) {
        this.codigo_fonasa_descripcion = codigo_fonasa_descripcion;
    }

    public String getTramo_prevision_paciente() {
        return tramo_prevision_paciente;
    }

    public void setTramo_prevision_paciente(String tramo_prevision_paciente) {
        this.tramo_prevision_paciente = tramo_prevision_paciente;
    }

    public int getEstado_duo() {
        return estado_duo;
    }

    public void setEstado_duo(int estado_duo) {
        this.estado_duo = estado_duo;
    }

    public String getRut_usuario() {
        return rut_usuario;
    }

    public void setRut_usuario(String rut_usuario) {
        this.rut_usuario = rut_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getApellidop_usuario() {
        return apellidop_usuario;
    }

    public void setApellidop_usuario(String apellidop_usuario) {
        this.apellidop_usuario = apellidop_usuario;
    }

    public String getApellidom_usuario() {
        return apellidom_usuario;
    }

    public void setApellidom_usuario(String apellidom_usuario) {
        this.apellidom_usuario = apellidom_usuario;
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

    
    
}
