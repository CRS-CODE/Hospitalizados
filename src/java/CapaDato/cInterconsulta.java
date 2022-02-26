/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

import java.util.Date;

/**
 *
 * @author Kairin
 */
public class cInterconsulta {

    private Integer idDuo;
    private Integer idInterconsulta;
    private Integer idEstablecimiento;
    private String especialidad;
    private Integer idRazon;
    private String otraRazon;
    private String diagnostico;
    private Integer idSospechaProblema;
    private String especificarProblema;
    private String subProblema;
    private String fundamentosDiagnostico;
    private String examenesRealizados;
    private String user;
    private Date fechaRegistro;
    private String establecimiento;

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    
    public Integer getIdDuo() {
        return idDuo;
    }

    public void setIdDuo(Integer idDuo) {
        this.idDuo = idDuo;
    }

    public Integer getIdInterconsulta() {
        return idInterconsulta;
    }

    public void setIdInterconsulta(Integer idInterconsulta) {
        this.idInterconsulta = idInterconsulta;
    }

    public Integer getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(Integer idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Integer getIdRazon() {
        return idRazon;
    }

    public void setIdRazon(Integer idRazon) {
        this.idRazon = idRazon;
    }

    public String getOtraRazon() {
        return otraRazon;
    }

    public void setOtraRazon(String otraRazon) {
        this.otraRazon = otraRazon;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Integer getIdSospechaProblema() {
        return idSospechaProblema;
    }

    public void setIdSospechaProblema(Integer idSospechaProblema) {
        this.idSospechaProblema = idSospechaProblema;
    }

    public String getEspecificarProblema() {
        return especificarProblema;
    }

    public void setEspecificarProblema(String especificarProblema) {
        this.especificarProblema = especificarProblema;
    }

    public String getSubProblema() {
        return subProblema;
    }

    public void setSubProblema(String subProblema) {
        this.subProblema = subProblema;
    }

    public String getFundamentosDiagnostico() {
        return fundamentosDiagnostico;
    }

    public void setFundamentosDiagnostico(String fundamentosDiagnostico) {
        this.fundamentosDiagnostico = fundamentosDiagnostico;
    }

    public String getExamenesRealizados() {
        return examenesRealizados;
    }

    public void setExamenesRealizados(String examenesRealizados) {
        this.examenesRealizados = examenesRealizados;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
