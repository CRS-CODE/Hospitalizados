/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author EseGamboa
 */
public class cDau extends cPaciente {

    private int id_dau;
    private int dau_estado;
    private String dau_estado_descri;
    private int tipo_dau_id;
    private String tipo_dau_descri;
    private int medio_id;
    private String medio_descri;
    private int unidad_ingreso_id;
    private String unidad_ingreso_descri;
    private String dau_fecha_ingreso;
    private String dau_fecha_hora_atencion;
    private String dau_fecha_egreso_med;
    private String dau_fecha_clasifica_enf;
    private String dau_fecha_clasificacion;
    private int dau_clasificacion;
    private int dau_clasifica_enf;
    private String admisor_rut;
    private String rut_medico;
    private int dau_vif;
    private int brazalete;
    private String dau_rut_usuario_clasificacion;
    private int dau_categorizado;
    private int dau_destino;
    private int dau_recupera;
    private int dau_fallece;
    private int dau_observa;
    private int dau_diag_alta;
    private String admisor_nombre;
    private String admisor_apellidop;
    private String admisor_apellidom;
    private String medico_nombre;
    private String medico_apellidop;
    private String medico_apellidom;
    private String dau_clasifica_enf_descri; // viene de dau_clasifica_enf u corresponde a e1,e2,e3,e4,e5 y s/n
    private String dau_clasifica_med_descri; // viene de dau_clasificacion y corresponde a c1 c2 c3 c4 c5 y s/n

    private String paciente_descripcion;


    public cDau() {
        this.id_dau = -1;
        this.dau_estado = -1;
        this.dau_estado_descri = "";
        this.tipo_dau_id  = -1;
        this.tipo_dau_descri ="";
        this.medio_id = -1;
        this.medio_descri = "";
        this.unidad_ingreso_id  = -1;
        this.unidad_ingreso_descri = "";
        this.dau_fecha_ingreso = "";
        this.dau_fecha_hora_atencion = "";
        this.dau_fecha_egreso_med = "";
        this.dau_fecha_clasifica_enf = "";
        this.dau_fecha_clasificacion ="";
        this.dau_clasificacion = -1;
        this.dau_clasifica_enf = -1;
        this.admisor_rut = "";
        this.rut_medico = "";
        this.dau_vif = -1;
        this.brazalete = -1;
        this.dau_rut_usuario_clasificacion = "";
        this.dau_categorizado = -1;
        this.dau_destino = -1;
        this.dau_recupera = -1;
        this.dau_fallece = -1;
        this.dau_observa = -1;
        this.dau_diag_alta = -1;
        this.admisor_nombre ="";
        this.admisor_apellidop = "";
        this.admisor_apellidom = "";
        this.medico_nombre = "";
        this.medico_apellidop = "";
        this.medico_apellidom = "";
        this.dau_clasifica_enf_descri ="";
        this.dau_clasifica_med_descri = "";
        paciente_descripcion="";
    }

    /**
     * @return the id_dau
     */
    public int getId_dau() {
        return id_dau;
    }

    /**
     * @param id_dau the id_dau to set
     */
    public void setId_dau(int id_dau) {
        this.id_dau = id_dau;
    }

    /**
     * @return the dau_estado
     */
    public int getDau_estado() {
        return dau_estado;
    }

    /**
     * @param dau_estado the dau_estado to set
     */
    public void setDau_estado(int dau_estado) {
        this.dau_estado = dau_estado;
    }

    /**
     * @return the dau_estado_descri
     */
    public String getDau_estado_descri() {
        return dau_estado_descri;
    }

    /**
     * @param dau_estado_descri the dau_estado_descri to set
     */
    public void setDau_estado_descri(String dau_estado_descri) {
        this.dau_estado_descri = dau_estado_descri;
    }

    /**
     * @return the tipo_dau_id
     */
    public int getTipo_dau_id() {
        return tipo_dau_id;
    }

    /**
     * @param tipo_dau_id the tipo_dau_id to set
     */
    public void setTipo_dau_id(int tipo_dau_id) {
        this.tipo_dau_id = tipo_dau_id;
    }

    /**
     * @return the tipo_dau_descri
     */
    public String getTipo_dau_descri() {
        return tipo_dau_descri;
    }

    /**
     * @param tipo_dau_descri the tipo_dau_descri to set
     */
    public void setTipo_dau_descri(String tipo_dau_descri) {
        this.tipo_dau_descri = tipo_dau_descri;
    }

    /**
     * @return the medio_id
     */
    public int getMedio_id() {
        return medio_id;
    }

    /**
     * @param medio_id the medio_id to set
     */
    public void setMedio_id(int medio_id) {
        this.medio_id = medio_id;
    }

    /**
     * @return the medio_descri
     */
    public String getMedio_descri() {
        return medio_descri;
    }

    /**
     * @param medio_descri the medio_descri to set
     */
    public void setMedio_descri(String medio_descri) {
        this.medio_descri = medio_descri;
    }

    /**
     * @return the unidad_ingreso_id
     */
    public int getUnidad_ingreso_id() {
        return unidad_ingreso_id;
    }

    /**
     * @param unidad_ingreso_id the unidad_ingreso_id to set
     */
    public void setUnidad_ingreso_id(int unidad_ingreso_id) {
        this.unidad_ingreso_id = unidad_ingreso_id;
    }

    /**
     * @return the unidad_ingreso_descri
     */
    public String getUnidad_ingreso_descri() {
        return unidad_ingreso_descri;
    }

    /**
     * @param unidad_ingreso_descri the unidad_ingreso_descri to set
     */
    public void setUnidad_ingreso_descri(String unidad_ingreso_descri) {
        this.unidad_ingreso_descri = unidad_ingreso_descri;
    }

    /**
     * @return the dau_fecha_ingreso
     */
    public String getDau_fecha_ingreso() {
        return dau_fecha_ingreso;
    }

    /**
     * @param dau_fecha_ingreso the dau_fecha_ingreso to set
     */
    public void setDau_fecha_ingreso(String dau_fecha_ingreso) {
        this.dau_fecha_ingreso = dau_fecha_ingreso;
    }

    /**
     * @return the dau_fecha_hora_atencion
     */
    public String getDau_fecha_hora_atencion() {
        return dau_fecha_hora_atencion;
    }

    /**
     * @param dau_fecha_hora_atencion the dau_fecha_hora_atencion to set
     */
    public void setDau_fecha_hora_atencion(String dau_fecha_hora_atencion) {
        this.dau_fecha_hora_atencion = dau_fecha_hora_atencion;
    }

    /**
     * @return the dau_fecha_egreso_med
     */
    public String getDau_fecha_egreso_med() {
        return dau_fecha_egreso_med;
    }

    /**
     * @param dau_fecha_egreso_med the dau_fecha_egreso_med to set
     */
    public void setDau_fecha_egreso_med(String dau_fecha_egreso_med) {
        this.dau_fecha_egreso_med = dau_fecha_egreso_med;
    }

    /**
     * @return the dau_fecha_clasifica_enf
     */
    public String getDau_fecha_clasifica_enf() {
        return dau_fecha_clasifica_enf;
    }

    /**
     * @param dau_fecha_clasifica_enf the dau_fecha_clasifica_enf to set
     */
    public void setDau_fecha_clasifica_enf(String dau_fecha_clasifica_enf) {
        this.dau_fecha_clasifica_enf = dau_fecha_clasifica_enf;
    }

    /**
     * @return the dau_fecha_clasificacion
     */
    public String getDau_fecha_clasificacion() {
        return dau_fecha_clasificacion;
    }

    /**
     * @param dau_fecha_clasificacion the dau_fecha_clasificacion to set
     */
    public void setDau_fecha_clasificacion(String dau_fecha_clasificacion) {
        this.dau_fecha_clasificacion = dau_fecha_clasificacion;
    }

    /**
     * @return the dau_clasificacion
     */
    public int getDau_clasificacion() {
        return dau_clasificacion;
    }

    /**
     * @param dau_clasificacion the dau_clasificacion to set
     */
    public void setDau_clasificacion(int dau_clasificacion) {
        this.dau_clasificacion = dau_clasificacion;
    }

    /**
     * @return the dau_clasifica_enf
     */
    public int getDau_clasifica_enf() {
        return dau_clasifica_enf;
    }

    /**
     * @param dau_clasifica_enf the dau_clasifica_enf to set
     */
    public void setDau_clasifica_enf(int dau_clasifica_enf) {
        this.dau_clasifica_enf = dau_clasifica_enf;
    }

    /**
     * @return the admisor_rut
     */
    public String getAdmisor_rut() {
        return admisor_rut;
    }

    /**
     * @param admisor_rut the admisor_rut to set
     */
    public void setAdmisor_rut(String admisor_rut) {
        this.admisor_rut = admisor_rut;
    }

    /**
     * @return the rut_medico
     */
    public String getRut_medico() {
        return rut_medico;
    }

    /**
     * @param rut_medico the rut_medico to set
     */
    public void setRut_medico(String rut_medico) {
        this.rut_medico = rut_medico;
    }

    /**
     * @return the dau_vif
     */
    public int getDau_vif() {
        return dau_vif;
    }

    /**
     * @param dau_vif the dau_vif to set
     */
    public void setDau_vif(int dau_vif) {
        this.dau_vif = dau_vif;
    }

    /**
     * @return the brazalete
     */
    public int getBrazalete() {
        return brazalete;
    }

    /**
     * @param brazalete the brazalete to set
     */
    public void setBrazalete(int brazalete) {
        this.brazalete = brazalete;
    }

    /**
     * @return the dau_rut_usuario_clasificacion
     */
    public String getDau_rut_usuario_clasificacion() {
        return dau_rut_usuario_clasificacion;
    }

    /**
     * @param dau_rut_usuario_clasificacion the dau_rut_usuario_clasificacion to set
     */
    public void setDau_rut_usuario_clasificacion(String dau_rut_usuario_clasificacion) {
        this.dau_rut_usuario_clasificacion = dau_rut_usuario_clasificacion;
    }

    /**
     * @return the dau_categorizado
     */
    public int getDau_categorizado() {
        return dau_categorizado;
    }

    /**
     * @param dau_categorizado the dau_categorizado to set
     */
    public void setDau_categorizado(int dau_categorizado) {
        this.dau_categorizado = dau_categorizado;
    }

    /**
     * @return the dau_destino
     */
    public int getDau_destino() {
        return dau_destino;
    }

    /**
     * @param dau_destino the dau_destino to set
     */
    public void setDau_destino(int dau_destino) {
        this.dau_destino = dau_destino;
    }

    /**
     * @return the dau_recupera
     */
    public int getDau_recupera() {
        return dau_recupera;
    }

    /**
     * @param dau_recupera the dau_recupera to set
     */
    public void setDau_recupera(int dau_recupera) {
        this.dau_recupera = dau_recupera;
    }

    /**
     * @return the dau_fallece
     */
    public int getDau_fallece() {
        return dau_fallece;
    }

    /**
     * @param dau_fallece the dau_fallece to set
     */
    public void setDau_fallece(int dau_fallece) {
        this.dau_fallece = dau_fallece;
    }

    /**
     * @return the dau_observa
     */
    public int getDau_observa() {
        return dau_observa;
    }

    /**
     * @param dau_observa the dau_observa to set
     */
    public void setDau_observa(int dau_observa) {
        this.dau_observa = dau_observa;
    }

    /**
     * @return the dau_diag_alta
     */
    public int getDau_diag_alta() {
        return dau_diag_alta;
    }

    /**
     * @param dau_diag_alta the dau_diag_alta to set
     */
    public void setDau_diag_alta(int dau_diag_alta) {
        this.dau_diag_alta = dau_diag_alta;
    }

    /**
     * @return the admisor_nombre
     */
    public String getAdmisor_nombre() {
        return admisor_nombre;
    }

    /**
     * @param admisor_nombre the admisor_nombre to set
     */
    public void setAdmisor_nombre(String admisor_nombre) {
        this.admisor_nombre = admisor_nombre;
    }

    /**
     * @return the admisor_apellidop
     */
    public String getAdmisor_apellidop() {
        return admisor_apellidop;
    }

    /**
     * @param admisor_apellidop the admisor_apellidop to set
     */
    public void setAdmisor_apellidop(String admisor_apellidop) {
        this.admisor_apellidop = admisor_apellidop;
    }

    /**
     * @return the admisor_apellidom
     */
    public String getAdmisor_apellidom() {
        return admisor_apellidom;
    }

    /**
     * @param admisor_apellidom the admisor_apellidom to set
     */
    public void setAdmisor_apellidom(String admisor_apellidom) {
        this.admisor_apellidom = admisor_apellidom;
    }

    /**
     * @return the medico_nombre
     */
    public String getMedico_nombre() {
        return medico_nombre;
    }

    /**
     * @param medico_nombre the medico_nombre to set
     */
    public void setMedico_nombre(String medico_nombre) {
        this.medico_nombre = medico_nombre;
    }

    /**
     * @return the medico_apellidop
     */
    public String getMedico_apellidop() {
        return medico_apellidop;
    }

    /**
     * @param medico_apellidop the medico_apellidop to set
     */
    public void setMedico_apellidop(String medico_apellidop) {
        this.medico_apellidop = medico_apellidop;
    }

    /**
     * @return the medico_apellidom
     */
    public String getMedico_apellidom() {
        return medico_apellidom;
    }

    /**
     * @param medico_apellidom the medico_apellidom to set
     */
    public void setMedico_apellidom(String medico_apellidom) {
        this.medico_apellidom = medico_apellidom;
    }

    /**
     * @return the dau_clasifica_enf_descri
     */
    public String getDau_clasifica_enf_descri() {
        return dau_clasifica_enf_descri;
    }

    /**
     * @param dau_clasifica_enf_descri the dau_clasifica_enf_descri to set
     */
    public void setDau_clasifica_enf_descri(String dau_clasifica_enf_descri) {
        this.dau_clasifica_enf_descri = dau_clasifica_enf_descri;
    }

    /**
     * @return the dau_clasifica_med_descri
     */
    public String getDau_clasifica_med_descri() {
        return dau_clasifica_med_descri;
    }

    /**
     * @param dau_clasifica_med_descri the dau_clasifica_med_descri to set
     */
    public void setDau_clasifica_med_descri(String dau_clasifica_med_descri) {
        this.dau_clasifica_med_descri = dau_clasifica_med_descri;
    }

    /**
     * @return the paciente_descripcion
     */
    public String getPaciente_descripcion() {
        return paciente_descripcion;
    }

    /**
     * @param paciente_descripcion the paciente_descripcion to set
     */
    public void setPaciente_descripcion(String paciente_descripcion) {
        this.paciente_descripcion = paciente_descripcion;
    }

    






}
