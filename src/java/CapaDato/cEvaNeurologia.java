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
public class cEvaNeurologia extends cUsuario {

    private String fecha_ingreso_neuro;
    private String hora_ingreso_neuro;
    private int estado_neuro;
    private String estado_desc_neuro;
    private int id_duo;

    private int id_neuro;
    private String fecha_ingreso;
    private String hora_ingreso;

    private int lesion_evaluada;
    private String lesion_evaluada_desc;

    //generales
    private int lesion;
    private String lesion_desc;

    private String ashworth;
    private int reflejo_osteorendineo;
    private String reflejo_osteorendineo_desc;
    private String marcha;

    //LESION MEDULAR
    private String asia;
    private int lesion_tipo;
    private String lesion_tipo_desc;
 
    private String evaluacion_sensitiva;
    private String sensibilidad;
    private String motor_index;
    private String contraccion;
    //
    private String silla_ruedas;
    private String nivel_sentivo;
    private String nivel_motor;
    private String nivel_neurologico;

    //OTRO TIPO LESION
    private String motoneurona;
    private String extrapiramiral;
    private String postura;
    private String fuerza;
    private String tono_muscular;
    private int trofismo;
    private String trofismo_desc;
    private String trofismo_adicional;

    private String eess;
    private String eeii;

    private int propiocepcion;
    private String propiocepcion_desc;
    private String propiocepcion_adicional;
    private String transicion;

    private int reaccion_equilibrio;
    private String reaccion_equilibrio_desc;
    private int reaccion_enderezamiento;
    private String reaccion_enderezamiento_desc;
    private int reaccion_apoyo;
    private String reaccion_apoyo_desc;

    private String test_especial;

    /**/
    private int op1;
    private int op2;
    private int op3;
    private int op4;
    private int op5;
    private int op6;

    private String op1_desc;
    private String op2_desc;
    private String op3_desc;
    private String op4_desc;
    private String op5_desc;
    private String op6_desc;

    public cEvaNeurologia() {
        this.id_neuro = 0;
        this.fecha_ingreso = "";
        this.hora_ingreso = "";
        this.estado_neuro = 0;
        this.estado_desc_neuro = "";

        lesion_tipo = 0;
        lesion_tipo_desc = "";
        this.lesion = 0;
        this.lesion_desc = "";
        this.ashworth = "";
        this.reflejo_osteorendineo = 0;
        this.reflejo_osteorendineo_desc = "";
        this.marcha = "";
        this.asia = "";
        this.evaluacion_sensitiva = "";
        this.sensibilidad = "";
        this.motor_index = "";
        this.contraccion = "";
        this.silla_ruedas = "";
        this.nivel_sentivo = "";
        this.nivel_motor = "";
        this.nivel_neurologico = "";
        this.motoneurona = "";
        this.extrapiramiral = "";
        this.postura = "";
        this.fuerza = "";
        this.tono_muscular = "";
        this.trofismo = 0;
        this.trofismo_desc = "";
        this.trofismo_adicional = "";
        this.eess = "";
        this.eeii = "";
        this.propiocepcion = 0;
        this.propiocepcion_desc = "";
        this.propiocepcion_adicional = "";
        this.transicion = "";
        this.reaccion_equilibrio = 0;
        this.reaccion_equilibrio_desc = "";
        this.reaccion_enderezamiento = 0;
        this.reaccion_enderezamiento_desc = "";
        this.reaccion_apoyo = 0;
        this.reaccion_apoyo_desc = "";
        test_especial = "";

        op1 = 0;
        op2 = 0;
        op3 = 0;
        op4 = 0;
        op5 = 0;
        op6 = 0;

        op1_desc = "";
        op2_desc = "";
        op3_desc = "";
        op4_desc = "";
        op5_desc = "";
        op6_desc = "";
    }

    /**
     * @return the id_neuro
     */
    public int getId_neuro() {
        return id_neuro;
    }

    /**
     * @param id_neuro the id_neuro to set
     */
    public void setId_neuro(int id_neuro) {
        this.id_neuro = id_neuro;
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
     * @return the hora_ingreso
     */
    public String getHora_ingreso() {
        return hora_ingreso;
    }

    /**
     * @param hora_ingreso the hora_ingreso to set
     */
    public void setHora_ingreso(String hora_ingreso) {
        this.hora_ingreso = hora_ingreso;
    }

    /**
     * @return the estado_neuro
     */
    public int getEstado_neuro() {
        return estado_neuro;
    }

    /**
     * @param estado_neuro the estado_neuro to set
     */
    public void setEstado_neuro(int estado_neuro) {
        this.estado_neuro = estado_neuro;
    }

    /**
     * @return the estado_desc_neuro
     */
    public String getEstado_desc_neuro() {
        return estado_desc_neuro;
    }

    /**
     * @param estado_desc_neuro the estado_desc_neuro to set
     */
    public void setEstado_desc_neuro(String estado_desc_neuro) {
        this.estado_desc_neuro = estado_desc_neuro;
    }

    /**
     * @return the lesion_evaluada
     */
    public int getLesion_evaluada() {
        return lesion_evaluada;
    }

    /**
     * @param lesion_evaluada the lesion_evaluada to set
     */
    public void setLesion_evaluada(int lesion_evaluada) {
        this.lesion_evaluada = lesion_evaluada;
    }

    /**
     * @return the lesion_evaluada_desc
     */
    public String getLesion_evaluada_desc() {
        return lesion_evaluada_desc;
    }

    /**
     * @param lesion_evaluada_desc the lesion_evaluada_desc to set
     */
    public void setLesion_evaluada_desc(String lesion_evaluada_desc) {
        this.lesion_evaluada_desc = lesion_evaluada_desc;
    }

    /**
     * @return the lesion
     */
    public int getLesion() {
        return lesion;
    }

    /**
     * @param lesion the lesion to set
     */
    public void setLesion(int lesion) {
        this.lesion = lesion;
    }

    /**
     * @return the lesion_desc
     */
    public String getLesion_desc() {
        return lesion_desc;
    }

    /**
     * @param lesion_desc the lesion_desc to set
     */
    public void setLesion_desc(String lesion_desc) {
        this.lesion_desc = lesion_desc;
    }

    /**
     * @return the ashworth
     */
    public String getAshworth() {
        return ashworth;
    }

    /**
     * @param ashworth the ashworth to set
     */
    public void setAshworth(String ashworth) {
        this.ashworth = ashworth;
    }

    /**
     * @return the reflejo_osteorendineo
     */
    public int getReflejo_osteorendineo() {
        return reflejo_osteorendineo;
    }

    /**
     * @param reflejo_osteorendineo the reflejo_osteorendineo to set
     */
    public void setReflejo_osteorendineo(int reflejo_osteorendineo) {
        this.reflejo_osteorendineo = reflejo_osteorendineo;
    }

    /**
     * @return the reflejo_osteorendineo_desc
     */
    public String getReflejo_osteorendineo_desc() {
        return reflejo_osteorendineo_desc;
    }

    /**
     * @param reflejo_osteorendineo_desc the reflejo_osteorendineo_desc to set
     */
    public void setReflejo_osteorendineo_desc(String reflejo_osteorendineo_desc) {
        this.reflejo_osteorendineo_desc = reflejo_osteorendineo_desc;
    }

    /**
     * @return the marcha
     */
    public String getMarcha() {
        return marcha;
    }

    /**
     * @param marcha the marcha to set
     */
    public void setMarcha(String marcha) {
        this.marcha = marcha;
    }

    /**
     * @return the asia
     */
    public String getAsia() {
        return asia;
    }

    /**
     * @param asia the asia to set
     */
    public void setAsia(String asia) {
        this.asia = asia;
    }

   

    /**
     * @return the evaluacion_sensitiva
     */
    public String getEvaluacion_sensitiva() {
        return evaluacion_sensitiva;
    }

    /**
     * @param evaluacion_sensitiva the evaluacion_sensitiva to set
     */
    public void setEvaluacion_sensitiva(String evaluacion_sensitiva) {
        this.evaluacion_sensitiva = evaluacion_sensitiva;
    }

    /**
     * @return the sensibilidad_profunda
     */
    public String getSensibilidad() {
        return sensibilidad;
    }

    /**
     * @param sensibilidad_profunda the sensibilidad_profunda to set
     */
    public void setSensibilidad(String sensibilidad) {
        this.sensibilidad = sensibilidad;
    }

    /**
     * @return the motor_index
     */
    public String getMotor_index() {
        return motor_index;
    }

    /**
     * @param motor_index the motor_index to set
     */
    public void setMotor_index(String motor_index) {
        this.motor_index = motor_index;
    }

    /**
     * @return the contraccion
     */
    public String getContraccion() {
        return contraccion;
    }

    /**
     * @param contraccion the contraccion to set
     */
    public void setContraccion(String contraccion) {
        this.contraccion = contraccion;
    }

    /**
     * @return the silla_ruedas
     */
    public String getSilla_ruedas() {
        return silla_ruedas;
    }

    /**
     * @param silla_ruedas the silla_ruedas to set
     */
    public void setSilla_ruedas(String silla_ruedas) {
        this.silla_ruedas = silla_ruedas;
    }

    /**
     * @return the nivel_sentivo
     */
    public String getNivel_sentivo() {
        return nivel_sentivo;
    }

    /**
     * @param nivel_sentivo the nivel_sentivo to set
     */
    public void setNivel_sentivo(String nivel_sentivo) {
        this.nivel_sentivo = nivel_sentivo;
    }

    /**
     * @return the nivel_motor
     */
    public String getNivel_motor() {
        return nivel_motor;
    }

    /**
     * @param nivel_motor the nivel_motor to set
     */
    public void setNivel_motor(String nivel_motor) {
        this.nivel_motor = nivel_motor;
    }

    /**
     * @return the nivel_neurologico
     */
    public String getNivel_neurologico() {
        return nivel_neurologico;
    }

    /**
     * @param nivel_neurologico the nivel_neurologico to set
     */
    public void setNivel_neurologico(String nivel_neurologico) {
        this.nivel_neurologico = nivel_neurologico;
    }

    /**
     * @return the motoneurona
     */
    public String getMotoneurona() {
        return motoneurona;
    }

    /**
     * @param motoneurona the motoneurona to set
     */
    public void setMotoneurona(String motoneurona) {
        this.motoneurona = motoneurona;
    }

    /**
     * @return the extrapiramiral
     */
    public String getExtrapiramiral() {
        return extrapiramiral;
    }

    /**
     * @param extrapiramiral the extrapiramiral to set
     */
    public void setExtrapiramiral(String extrapiramiral) {
        this.extrapiramiral = extrapiramiral;
    }

    /**
     * @return the postura
     */
    public String getPostura() {
        return postura;
    }

    /**
     * @param postura the postura to set
     */
    public void setPostura(String postura) {
        this.postura = postura;
    }

    /**
     * @return the fuerza
     */
    public String getFuerza() {
        return fuerza;
    }

    /**
     * @param fuerza the fuerza to set
     */
    public void setFuerza(String fuerza) {
        this.fuerza = fuerza;
    }

    /**
     * @return the tono_muscular
     */
    public String getTono_muscular() {
        return tono_muscular;
    }

    /**
     * @param tono_muscular the tono_muscular to set
     */
    public void setTono_muscular(String tono_muscular) {
        this.tono_muscular = tono_muscular;
    }

    /**
     * @return the trofismo
     */
    public int getTrofismo() {
        return trofismo;
    }

    /**
     * @param trofismo the trofismo to set
     */
    public void setTrofismo(int trofismo) {
        this.trofismo = trofismo;
    }

    /**
     * @return the trofismo_desc
     */
    public String getTrofismo_desc() {
        return trofismo_desc;
    }

    /**
     * @param trofismo_desc the trofismo_desc to set
     */
    public void setTrofismo_desc(String trofismo_desc) {
        this.trofismo_desc = trofismo_desc;
    }

    /**
     * @return the trofismo_adicional
     */
    public String getTrofismo_adicional() {
        return trofismo_adicional;
    }

    /**
     * @param trofismo_adicional the trofismo_adicional to set
     */
    public void setTrofismo_adicional(String trofismo_adicional) {
        this.trofismo_adicional = trofismo_adicional;
    }

    /**
     * @return the eess
     */
    public String getEess() {
        return eess;
    }

    /**
     * @param eess the eess to set
     */
    public void setEess(String eess) {
        this.eess = eess;
    }

    /**
     * @return the eeii
     */
    public String getEeii() {
        return eeii;
    }

    /**
     * @param eeii the eeii to set
     */
    public void setEeii(String eeii) {
        this.eeii = eeii;
    }

    /**
     * @return the propiocepcion
     */
    public int getPropiocepcion() {
        return propiocepcion;
    }

    /**
     * @param propiocepcion the propiocepcion to set
     */
    public void setPropiocepcion(int propiocepcion) {
        this.propiocepcion = propiocepcion;
    }

    /**
     * @return the propiocepcion_desc
     */
    public String getPropiocepcion_desc() {
        return propiocepcion_desc;
    }

    /**
     * @param propiocepcion_desc the propiocepcion_desc to set
     */
    public void setPropiocepcion_desc(String propiocepcion_desc) {
        this.propiocepcion_desc = propiocepcion_desc;
    }

    /**
     * @return the propiocepcion_adicional
     */
    public String getPropiocepcion_adicional() {
        return propiocepcion_adicional;
    }

    /**
     * @param propiocepcion_adicional the propiocepcion_adicional to set
     */
    public void setPropiocepcion_adicional(String propiocepcion_adicional) {
        this.propiocepcion_adicional = propiocepcion_adicional;
    }

    /**
     * @return the transicion
     */
    public String getTransicion() {
        return transicion;
    }

    /**
     * @param transicion the transicion to set
     */
    public void setTransicion(String transicion) {
        this.transicion = transicion;
    }

    /**
     * @return the reaccion_equilibrio
     */
    public int getReaccion_equilibrio() {
        return reaccion_equilibrio;
    }

    /**
     * @param reaccion_equilibrio the reaccion_equilibrio to set
     */
    public void setReaccion_equilibrio(int reaccion_equilibrio) {
        this.reaccion_equilibrio = reaccion_equilibrio;
    }

    /**
     * @return the reaccion_equilibrio_desc
     */
    public String getReaccion_equilibrio_desc() {
        return reaccion_equilibrio_desc;
    }

    /**
     * @param reaccion_equilibrio_desc the reaccion_equilibrio_desc to set
     */
    public void setReaccion_equilibrio_desc(String reaccion_equilibrio_desc) {
        this.reaccion_equilibrio_desc = reaccion_equilibrio_desc;
    }

    /**
     * @return the reaccion_enderezamiento
     */
    public int getReaccion_enderezamiento() {
        return reaccion_enderezamiento;
    }

    /**
     * @param reaccion_enderezamiento the reaccion_enderezamiento to set
     */
    public void setReaccion_enderezamiento(int reaccion_enderezamiento) {
        this.reaccion_enderezamiento = reaccion_enderezamiento;
    }

    /**
     * @return the reaccion_enderezamiento_desc
     */
    public String getReaccion_enderezamiento_desc() {
        return reaccion_enderezamiento_desc;
    }

    /**
     * @param reaccion_enderezamiento_desc the reaccion_enderezamiento_desc to
     * set
     */
    public void setReaccion_enderezamiento_desc(String reaccion_enderezamiento_desc) {
        this.reaccion_enderezamiento_desc = reaccion_enderezamiento_desc;
    }

    /**
     * @return the reaccion_apoyo
     */
    public int getReaccion_apoyo() {
        return reaccion_apoyo;
    }

    /**
     * @param reaccion_apoyo the reaccion_apoyo to set
     */
    public void setReaccion_apoyo(int reaccion_apoyo) {
        this.reaccion_apoyo = reaccion_apoyo;
    }

    /**
     * @return the reaccion_apoyo_desc
     */
    public String getReaccion_apoyo_desc() {
        return reaccion_apoyo_desc;
    }

    /**
     * @param reaccion_apoyo_desc the reaccion_apoyo_desc to set
     */
    public void setReaccion_apoyo_desc(String reaccion_apoyo_desc) {
        this.reaccion_apoyo_desc = reaccion_apoyo_desc;
    }

    /**
     * @return the test_especial
     */
    public String getTest_especial() {
        return test_especial;
    }

    /**
     * @param test_especial the test_especial to set
     */
    public void setTest_especial(String test_especial) {
        this.test_especial = test_especial;
    }

    /**
     * @return the op1
     */
    public int getOp1() {
        return op1;
    }

    /**
     * @param op1 the op1 to set
     */
    public void setOp1(int op1) {
        this.op1 = op1;
    }

    /**
     * @return the op2
     */
    public int getOp2() {
        return op2;
    }

    /**
     * @param op2 the op2 to set
     */
    public void setOp2(int op2) {
        this.op2 = op2;
    }

    /**
     * @return the op3
     */
    public int getOp3() {
        return op3;
    }

    /**
     * @param op3 the op3 to set
     */
    public void setOp3(int op3) {
        this.op3 = op3;
    }

    /**
     * @return the op4
     */
    public int getOp4() {
        return op4;
    }

    /**
     * @param op4 the op4 to set
     */
    public void setOp4(int op4) {
        this.op4 = op4;
    }

    /**
     * @return the op5
     */
    public int getOp5() {
        return op5;
    }

    /**
     * @param op5 the op5 to set
     */
    public void setOp5(int op5) {
        this.op5 = op5;
    }

    /**
     * @return the op6
     */
    public int getOp6() {
        return op6;
    }

    /**
     * @param op6 the op6 to set
     */
    public void setOp6(int op6) {
        this.op6 = op6;
    }

    /**
     * @return the op1_desc
     */
    public String getOp1_desc() {
        return op1_desc;
    }

    /**
     * @param op1_desc the op1_desc to set
     */
    public void setOp1_desc(String op1_desc) {
        this.op1_desc = op1_desc;
    }

    /**
     * @return the op2_desc
     */
    public String getOp2_desc() {
        return op2_desc;
    }

    /**
     * @param op2_desc the op2_desc to set
     */
    public void setOp2_desc(String op2_desc) {
        this.op2_desc = op2_desc;
    }

    /**
     * @return the op3_desc
     */
    public String getOp3_desc() {
        return op3_desc;
    }

    /**
     * @param op3_desc the op3_desc to set
     */
    public void setOp3_desc(String op3_desc) {
        this.op3_desc = op3_desc;
    }

    /**
     * @return the op4_desc
     */
    public String getOp4_desc() {
        return op4_desc;
    }

    /**
     * @param op4_desc the op4_desc to set
     */
    public void setOp4_desc(String op4_desc) {
        this.op4_desc = op4_desc;
    }

    /**
     * @return the op5_desc
     */
    public String getOp5_desc() {
        return op5_desc;
    }

    /**
     * @param op5_desc the op5_desc to set
     */
    public void setOp5_desc(String op5_desc) {
        this.op5_desc = op5_desc;
    }

    /**
     * @return the op6_desc
     */
    public String getOp6_desc() {
        return op6_desc;
    }

    /**
     * @param op6_desc the op6_desc to set
     */
    public void setOp6_desc(String op6_desc) {
        this.op6_desc = op6_desc;
    }

    /**
     * @return the fecha_ingreso_neuro
     */
    public String getFecha_ingreso_neuro() {
        return fecha_ingreso_neuro;
    }

    /**
     * @param fecha_ingreso_neuro the fecha_ingreso_neuro to set
     */
    public void setFecha_ingreso_neuro(String fecha_ingreso_neuro) {
        this.fecha_ingreso_neuro = fecha_ingreso_neuro;
    }

    /**
     * @return the hora_ingreso_neuro
     */
    public String getHora_ingreso_neuro() {
        return hora_ingreso_neuro;
    }

    /**
     * @param hora_ingreso_neuro the hora_ingreso_neuro to set
     */
    public void setHora_ingreso_neuro(String hora_ingreso_neuro) {
        this.hora_ingreso_neuro = hora_ingreso_neuro;
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
     * @return the lesion_tipo
     */
    public int getLesion_tipo() {
        return lesion_tipo;
    }

    /**
     * @param lesion_tipo the lesion_tipo to set
     */
    public void setLesion_tipo(int lesion_tipo) {
        this.lesion_tipo = lesion_tipo;
    }

    /**
     * @return the lesion_tipo_desc
     */
    public String getLesion_tipo_desc() {
        return lesion_tipo_desc;
    }

    /**
     * @param lesion_tipo_desc the lesion_tipo_desc to set
     */
    public void setLesion_tipo_desc(String lesion_tipo_desc) {
        this.lesion_tipo_desc = lesion_tipo_desc;
    }

}
