/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 * Campo 22 del Informe de Egreso Hospitalario (DEIS): Procedencia del
 * paciente segun clasificacion oficial (distinta del listado de
 * establecimientos/servicios derivadores ya existente, id_derivado).
 */
public class cProcedenciaPaciente {

    private int id_procedencia;
    private String descripcion_procedencia;
    private int estado_procedencia;

    public cProcedenciaPaciente() {
        this.id_procedencia = -1;
        this.descripcion_procedencia = "";
        this.estado_procedencia = -1;
    }

    public int getId_procedencia() {
        return id_procedencia;
    }

    public void setId_procedencia(int id_procedencia) {
        this.id_procedencia = id_procedencia;
    }

    public String getDescripcion_procedencia() {
        return descripcion_procedencia;
    }

    public void setDescripcion_procedencia(String descripcion_procedencia) {
        this.descripcion_procedencia = descripcion_procedencia;
    }

    public int getEstado_procedencia() {
        return estado_procedencia;
    }

    public void setEstado_procedencia(int estado_procedencia) {
        this.estado_procedencia = estado_procedencia;
    }

}
