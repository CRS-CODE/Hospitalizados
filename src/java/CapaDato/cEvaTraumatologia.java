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
public class cEvaTraumatologia extends cUsuario {

    private int id_eva_traumatologia;
    private int id_duo;
    private String observacion_inicial;
    private String historial_usuario;
    private String plano_frontal;
    private String plano_sagital;
    private String plano_posterior;
    private String dolor;
    private String movimiento_activo;
    private String movimiento_pasivo;
    private String observacion;
    private String prueba_especial;
    private String dermatoma;
    private String miotoma;
    private String reflejo_osteotendineo;
    private String test_neurodinamico;
    private String palpacion;
    private String marcha;
    private String diagnostico_imagen;
    private String diagnostico_kinesico;

    private String fecha_ingreso_trauma;
    private String hora_ingreso_trauma;
    private int estado_trauma;
    private String estado_desc_trauma;

    public cEvaTraumatologia() {
        this.id_eva_traumatologia = 0;
        this.id_duo = 0;
        this.observacion_inicial = "";
        this.historial_usuario = "";
        this.plano_frontal = "";
        this.plano_sagital = "";
        this.plano_posterior = "";
        this.dolor = "";
        this.movimiento_activo = "";
        this.movimiento_pasivo = "";
        this.observacion = "";
        this.prueba_especial = "";
        this.dermatoma = "";
        this.miotoma = "";
        this.reflejo_osteotendineo = "";
        this.test_neurodinamico = "";
        this.palpacion = "";
        this.marcha = "";
        this.diagnostico_imagen = "";
        this.diagnostico_kinesico = "";

        fecha_ingreso_trauma = "";
        hora_ingreso_trauma = "";
        estado_trauma = 0;
        estado_desc_trauma = "";

    }

    /**
     * @return the id_eva_traumatologia
     */
    public int getId_eva_traumatologia() {
        return id_eva_traumatologia;
    }

    /**
     * @param id_eva_traumatologia the id_eva_traumatologia to set
     */
    public void setId_eva_traumatologia(int id_eva_traumatologia) {
        this.id_eva_traumatologia = id_eva_traumatologia;
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
     * @return the observacion_inicial
     */
    public String getObservacion_inicial() {
        return observacion_inicial;
    }

    /**
     * @param observacion_inicial the observacion_inicial to set
     */
    public void setObservacion_inicial(String observacion_inicial) {
        this.observacion_inicial = observacion_inicial;
    }

    /**
     * @return the historial_usuario
     */
    public String getHistorial_usuario() {
        return historial_usuario;
    }

    /**
     * @param historial_usuario the historial_usuario to set
     */
    public void setHistorial_usuario(String historial_usuario) {
        this.historial_usuario = historial_usuario;
    }

    /**
     * @return the plano_frontal
     */
    public String getPlano_frontal() {
        return plano_frontal;
    }

    /**
     * @param plano_frontal the plano_frontal to set
     */
    public void setPlano_frontal(String plano_frontal) {
        this.plano_frontal = plano_frontal;
    }

    /**
     * @return the plano_sagital
     */
    public String getPlano_sagital() {
        return plano_sagital;
    }

    /**
     * @param plano_sagital the plano_sagital to set
     */
    public void setPlano_sagital(String plano_sagital) {
        this.plano_sagital = plano_sagital;
    }

    /**
     * @return the plano_posterior
     */
    public String getPlano_posterior() {
        return plano_posterior;
    }

    /**
     * @param plano_posterior the plano_posterior to set
     */
    public void setPlano_posterior(String plano_posterior) {
        this.plano_posterior = plano_posterior;
    }

    /**
     * @return the dolor
     */
    public String getDolor() {
        return dolor;
    }

    /**
     * @param dolor the dolor to set
     */
    public void setDolor(String dolor) {
        this.dolor = dolor;
    }

    /**
     * @return the movimiento_activo
     */
    public String getMovimiento_activo() {
        return movimiento_activo;
    }

    /**
     * @param movimiento_activo the movimiento_activo to set
     */
    public void setMovimiento_activo(String movimiento_activo) {
        this.movimiento_activo = movimiento_activo;
    }

    /**
     * @return the movimiento_pasivo
     */
    public String getMovimiento_pasivo() {
        return movimiento_pasivo;
    }

    /**
     * @param movimiento_pasivo the movimiento_pasivo to set
     */
    public void setMovimiento_pasivo(String movimiento_pasivo) {
        this.movimiento_pasivo = movimiento_pasivo;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the prueba_especial
     */
    public String getPrueba_especial() {
        return prueba_especial;
    }

    /**
     * @param prueba_especial the prueba_especial to set
     */
    public void setPrueba_especial(String prueba_especial) {
        this.prueba_especial = prueba_especial;
    }

    /**
     * @return the dermatoma
     */
    public String getDermatoma() {
        return dermatoma;
    }

    /**
     * @param dermatoma the dermatoma to set
     */
    public void setDermatoma(String dermatoma) {
        this.dermatoma = dermatoma;
    }

    /**
     * @return the miotoma
     */
    public String getMiotoma() {
        return miotoma;
    }

    /**
     * @param miotoma the miotoma to set
     */
    public void setMiotoma(String miotoma) {
        this.miotoma = miotoma;
    }

    /**
     * @return the reflejo_osteotendineo
     */
    public String getReflejo_osteotendineo() {
        return reflejo_osteotendineo;
    }

    /**
     * @param reflejo_osteotendineo the reflejo_osteotendineo to set
     */
    public void setReflejo_osteotendineo(String reflejo_osteotendineo) {
        this.reflejo_osteotendineo = reflejo_osteotendineo;
    }

    /**
     * @return the test_neurodinamico
     */
    public String getTest_neurodinamico() {
        return test_neurodinamico;
    }

    /**
     * @param test_neurodinamico the test_neurodinamico to set
     */
    public void setTest_neurodinamico(String test_neurodinamico) {
        this.test_neurodinamico = test_neurodinamico;
    }

    /**
     * @return the palpacion
     */
    public String getPalpacion() {
        return palpacion;
    }

    /**
     * @param palpacion the palpacion to set
     */
    public void setPalpacion(String palpacion) {
        this.palpacion = palpacion;
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
     * @return the diagnostico_imagen
     */
    public String getDiagnostico_imagen() {
        return diagnostico_imagen;
    }

    /**
     * @param diagnostico_imagen the diagnostico_imagen to set
     */
    public void setDiagnostico_imagen(String diagnostico_imagen) {
        this.diagnostico_imagen = diagnostico_imagen;
    }

    /**
     * @return the diagnostico_kinesico
     */
    public String getDiagnostico_kinesico() {
        return diagnostico_kinesico;
    }

    /**
     * @param diagnostico_kinesico the diagnostico_kinesico to set
     */
    public void setDiagnostico_kinesico(String diagnostico_kinesico) {
        this.diagnostico_kinesico = diagnostico_kinesico;
    }

    /**
     * @return the fecha_ingreso_trauma
     */
    public String getFecha_ingreso_trauma() {
        return fecha_ingreso_trauma;
    }

    /**
     * @param fecha_ingreso_trauma the fecha_ingreso_trauma to set
     */
    public void setFecha_ingreso_trauma(String fecha_ingreso_trauma) {
        this.fecha_ingreso_trauma = fecha_ingreso_trauma;
    }

    /**
     * @return the hora_ingreso_trauma
     */
    public String getHora_ingreso_trauma() {
        return hora_ingreso_trauma;
    }

    /**
     * @param hora_ingreso_trauma the hora_ingreso_trauma to set
     */
    public void setHora_ingreso_trauma(String hora_ingreso_trauma) {
        this.hora_ingreso_trauma = hora_ingreso_trauma;
    }

    /**
     * @return the estado_trauma
     */
    public int getEstado_trauma() {
        return estado_trauma;
    }

    /**
     * @param estado_trauma the estado_trauma to set
     */
    public void setEstado_trauma(int estado_trauma) {
        this.estado_trauma = estado_trauma;
    }

    /**
     * @return the estado_desc_trauma
     */
    public String getEstado_desc_trauma() {
        return estado_desc_trauma;
    }

    /**
     * @param estado_desc_trauma the estado_desc_trauma to set
     */
    public void setEstado_desc_trauma(String estado_desc_trauma) {
        this.estado_desc_trauma = estado_desc_trauma;
    }

   
}
