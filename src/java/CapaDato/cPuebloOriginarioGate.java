/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 * Campo 55 del Informe de Egreso Hospitalario (DEIS): "Se considera
 * perteneciente a algun pueblo indigena u originario?" (SI/NO). Si la
 * respuesta es SI, se debe completar ademas el campo 10 (Pueblo Indigena,
 * ya existente: cPueblo / schemaoirs.pueblo_originario).
 */
public class cPuebloOriginarioGate {

    private int id_gate;
    private String descripcion_gate;
    private int estado_gate;

    public cPuebloOriginarioGate() {
        this.id_gate = -1;
        this.descripcion_gate = "";
        this.estado_gate = -1;
    }

    public int getId_gate() {
        return id_gate;
    }

    public void setId_gate(int id_gate) {
        this.id_gate = id_gate;
    }

    public String getDescripcion_gate() {
        return descripcion_gate;
    }

    public void setDescripcion_gate(String descripcion_gate) {
        this.descripcion_gate = descripcion_gate;
    }

    public int getEstado_gate() {
        return estado_gate;
    }

    public void setEstado_gate(int estado_gate) {
        this.estado_gate = estado_gate;
    }

}
