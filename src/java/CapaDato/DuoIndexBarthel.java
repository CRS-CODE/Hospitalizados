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
public class DuoIndexBarthel {
    
    private int id;
    private int status;
    private String userRegisters;
    private Date dateRegisters;
    private int totalPuntuction;
    private String degreeOfDependency;
    private int typeRegisters;
    private int idDuo;
    private String rutPaciente;
    private String namePaciente;
    private String typeString;
    private String dateString;

    public int getId() {
        return id;
    }

    public int getIdDuo() {
        return idDuo;
    }

    public void setIdDuo(int idDuo) {
        this.idDuo = idDuo;
    }
    
    

    public int getTypeRegisters() {
        return typeRegisters;
    }

    public void setTypeRegisters(int typeRegisters) {
        this.typeRegisters = typeRegisters;
    }

    public String getRutPaciente() {
        return rutPaciente;
    }

    public void setRutPaciente(String rutPaciente) {
        this.rutPaciente = rutPaciente;
    }

    public String getNamePaciente() {
        return namePaciente;
    }

    public void setNamePaciente(String namePaciente) {
        this.namePaciente = namePaciente;
    }

    public String getTypeString() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserRegisters() {
        return userRegisters;
    }

    public void setUserRegisters(String userRegisters) {
        this.userRegisters = userRegisters;
    }

    public Date getDateRegisters() {
        return dateRegisters;
    }

    public void setDateRegisters(Date dateRegisters) {
        this.dateRegisters = dateRegisters;
    }

    public int getTotalPuntuction() {
        return totalPuntuction;
    }

    public void setTotalPuntuction(int totalPuntuction) {
        this.totalPuntuction = totalPuntuction;
    }

    public String getDegreeOfDependency() {
        return degreeOfDependency;
    }

    public void setDegreeOfDependency(String degreeOfDependency) {
        this.degreeOfDependency = degreeOfDependency;
    }

    public DuoIndexBarthel() {
    }
    
    
    
}
