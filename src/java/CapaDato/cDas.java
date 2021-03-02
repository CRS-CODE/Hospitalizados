/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

/**
 *
 * @author EseGamboa
 */
public class cDas extends cPaciente {

    private int id_das;
    private int dau_id;
    private int dau_nn_id;
    //private String paciente;
    private int estado;
    private String fecha_ingreso;
    private int camilla;
    private int derivador;
    private String medico;
    private String usuario;
    private String ip;
    private String camilla_descri;
    private String derivador_descri;
    private String estado_descri;
    private String nombre_usuario;
    private String apellidop_usuario;
    private String apellidom_usuario;
    private String nombre_medico;
    private String apellidop_medico;
    private String apellidom_medico;
    private int sala;
    private String sala_descri;
    private int dif_ss;
    private int dif_mm;
    private int dif_hh;
    private int dif_dd;
    private int indicacion_destino_id;
    private String indicacion_destino_descri;
    private String indicacion_fecha;
    private String indicacion_usuario_rut;
    private String indicacion_usuario_nombre;
    private String indicacion_usuario_apellidop;
    private String indicacion_usuario_apeliidom;
    private String indicacion_ip;

    /*25-01-2013*/
    private int alta_id_alta_das;
    private String alta_fecha_ingreso;
    private int alta_estado;
    private String alta_estado_descri;
    private int alta_destino;
    private String alta_destino_descri;
    private String alta_detalle;
    private String alta_ip;

    private String alta_usuario_rut;
    private String alta_usuario_nombre;
    private String alta_usuario_apellidop;
    private String alta_usuario_apellidom;
    /*25-01-2013*/

    public cDas() {
        this.id_das = -1;
        this.dau_id = -1;
        this.dau_nn_id = -1;
        this.estado = -1;
        this.fecha_ingreso = "";
        this.camilla = -1;
        this.derivador = -1;
        this.medico = "";
        this.usuario = "";
        this.ip = "";
        this.camilla_descri = "";
        this.derivador_descri = "";
        this.estado_descri = "";
        this.nombre_usuario = "";
        this.apellidop_usuario = "";
        this.apellidom_usuario = "";
        this.nombre_medico = "";
        this.apellidop_medico = "";
        this.apellidom_medico = "";

        sala = -1;
        sala_descri = "";

        dif_ss = -1;
        dif_mm = -1;
        dif_hh = -1;
        dif_dd = -1;


        indicacion_destino_descri = "";
        indicacion_fecha = "";
        indicacion_usuario_rut = "";
        indicacion_usuario_nombre = "";
        indicacion_usuario_apellidop = "";
        indicacion_usuario_apeliidom = "";
        indicacion_ip = "";
 /*25-01-2013*/
        alta_id_alta_das= -1;
        alta_fecha_ingreso= "";
        alta_estado= -1;
        alta_estado_descri= "";
        alta_destino= -1;
        alta_destino_descri= "";
        alta_detalle= "";
        alta_ip= "";

        alta_usuario_rut= "";
        alta_usuario_nombre= "";
        alta_usuario_apellidop= "";
        alta_usuario_apellidom= "";
      /*25-01-2013*/

    }

    /**
     * @return the id_das
     */
    public int getId_das() {
        return id_das;
    }

    /**
     * @param id_das the id_das to set
     */
    public void setId_das(int id_das) {
        this.id_das = id_das;
    }

    /**
     * @return the dau_id
     */
    public int getDau_id() {
        return dau_id;
    }

    /**
     * @param dau_id the dau_id to set
     */
    public void setDau_id(int dau_id) {
        this.dau_id = dau_id;
    }

    /**
     * @return the dau_nn_id
     */
    public int getDau_nn_id() {
        return dau_nn_id;
    }

    /**
     * @param dau_nn_id the dau_nn_id to set
     */
    public void setDau_nn_id(int dau_nn_id) {
        this.dau_nn_id = dau_nn_id;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
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
     * @return the camilla
     */
    public int getCamilla() {
        return camilla;
    }

    /**
     * @param camilla the camilla to set
     */
    public void setCamilla(int camilla) {
        this.camilla = camilla;
    }

    /**
     * @return the derivador
     */
    public int getDerivador() {
        return derivador;
    }

    /**
     * @param derivador the derivador to set
     */
    public void setDerivador(int derivador) {
        this.derivador = derivador;
    }

    /**
     * @return the medico
     */
    public String getMedico() {
        return medico;
    }

    /**
     * @param medico the medico to set
     */
    public void setMedico(String medico) {
        this.medico = medico;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the camilla_descri
     */
    public String getCamilla_descri() {
        return camilla_descri;
    }

    /**
     * @param camilla_descri the camilla_descri to set
     */
    public void setCamilla_descri(String camilla_descri) {
        this.camilla_descri = camilla_descri;
    }

    /**
     * @return the derivador_descri
     */
    public String getDerivador_descri() {
        return derivador_descri;
    }

    /**
     * @param derivador_descri the derivador_descri to set
     */
    public void setDerivador_descri(String derivador_descri) {
        this.derivador_descri = derivador_descri;
    }

    /**
     * @return the estado_descri
     */
    public String getEstado_descri() {
        return estado_descri;
    }

    /**
     * @param estado_descri the estado_descri to set
     */
    public void setEstado_descri(String estado_descri) {
        this.estado_descri = estado_descri;
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
     * @return the nombre_medico
     */
    public String getNombre_medico() {
        return nombre_medico;
    }

    /**
     * @param nombre_medico the nombre_medico to set
     */
    public void setNombre_medico(String nombre_medico) {
        this.nombre_medico = nombre_medico;
    }

    /**
     * @return the apellidop_medico
     */
    public String getApellidop_medico() {
        return apellidop_medico;
    }

    /**
     * @param apellidop_medico the apellidop_medico to set
     */
    public void setApellidop_medico(String apellidop_medico) {
        this.apellidop_medico = apellidop_medico;
    }

    /**
     * @return the apellidom_medico
     */
    public String getApellidom_medico() {
        return apellidom_medico;
    }

    /**
     * @param apellidom_medico the apellidom_medico to set
     */
    public void setApellidom_medico(String apellidom_medico) {
        this.apellidom_medico = apellidom_medico;
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
     * @return the sala_descri
     */
    public String getSala_descri() {
        return sala_descri;
    }

    /**
     * @param sala_descri the sala_descri to set
     */
    public void setSala_descri(String sala_descri) {
        this.sala_descri = sala_descri;
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
     * @return the indicacion_destino_id
     */
    public int getIndicacion_destino_id() {
        return indicacion_destino_id;
    }

    /**
     * @param indicacion_destino_id the indicacion_destino_id to set
     */
    public void setIndicacion_destino_id(int indicacion_destino_id) {
        this.indicacion_destino_id = indicacion_destino_id;
    }

    /**
     * @return the indicacion_destino_descri
     */
    public String getIndicacion_destino_descri() {
        return indicacion_destino_descri;
    }

    /**
     * @param indicacion_destino_descri the indicacion_destino_descri to set
     */
    public void setIndicacion_destino_descri(String indicacion_destino_descri) {
        this.indicacion_destino_descri = indicacion_destino_descri;
    }

    /**
     * @return the indicacion_fecha
     */
    public String getIndicacion_fecha() {
        return indicacion_fecha;
    }

    /**
     * @param indicacion_fecha the indicacion_fecha to set
     */
    public void setIndicacion_fecha(String indicacion_fecha) {
        this.indicacion_fecha = indicacion_fecha;
    }

    /**
     * @return the indicacion_usuario_rut
     */
    public String getIndicacion_usuario_rut() {
        return indicacion_usuario_rut;
    }

    /**
     * @param indicacion_usuario_rut the indicacion_usuario_rut to set
     */
    public void setIndicacion_usuario_rut(String indicacion_usuario_rut) {
        this.indicacion_usuario_rut = indicacion_usuario_rut;
    }

    /**
     * @return the indicacion_usuario_nombre
     */
    public String getIndicacion_usuario_nombre() {
        return indicacion_usuario_nombre;
    }

    /**
     * @param indicacion_usuario_nombre the indicacion_usuario_nombre to set
     */
    public void setIndicacion_usuario_nombre(String indicacion_usuario_nombre) {
        this.indicacion_usuario_nombre = indicacion_usuario_nombre;
    }

    /**
     * @return the indicacion_usuario_apellidop
     */
    public String getIndicacion_usuario_apellidop() {
        return indicacion_usuario_apellidop;
    }

    /**
     * @param indicacion_usuario_apellidop the indicacion_usuario_apellidop to set
     */
    public void setIndicacion_usuario_apellidop(String indicacion_usuario_apellidop) {
        this.indicacion_usuario_apellidop = indicacion_usuario_apellidop;
    }

    /**
     * @return the indicacion_usuario_apeliidom
     */
    public String getIndicacion_usuario_apeliidom() {
        return indicacion_usuario_apeliidom;
    }

    /**
     * @param indicacion_usuario_apeliidom the indicacion_usuario_apeliidom to set
     */
    public void setIndicacion_usuario_apeliidom(String indicacion_usuario_apeliidom) {
        this.indicacion_usuario_apeliidom = indicacion_usuario_apeliidom;
    }

    /**
     * @return the indicacion_ip
     */
    public String getIndicacion_ip() {
        return indicacion_ip;
    }

    /**
     * @param indicacion_ip the indicacion_ip to set
     */
    public void setIndicacion_ip(String indicacion_ip) {
        this.indicacion_ip = indicacion_ip;
    }

    /**
     * @return the alta_id_alta_das
     */
    public int getAlta_id_alta_das() {
        return alta_id_alta_das;
    }

    /**
     * @param alta_id_alta_das the alta_id_alta_das to set
     */
    public void setAlta_id_alta_das(int alta_id_alta_das) {
        this.alta_id_alta_das = alta_id_alta_das;
    }

    /**
     * @return the alta_fecha_ingreso
     */
    public String getAlta_fecha_ingreso() {
        return alta_fecha_ingreso;
    }

    /**
     * @param alta_fecha_ingreso the alta_fecha_ingreso to set
     */
    public void setAlta_fecha_ingreso(String alta_fecha_ingreso) {
        this.alta_fecha_ingreso = alta_fecha_ingreso;
    }

    /**
     * @return the alta_estado
     */
    public int getAlta_estado() {
        return alta_estado;
    }

    /**
     * @param alta_estado the alta_estado to set
     */
    public void setAlta_estado(int alta_estado) {
        this.alta_estado = alta_estado;
    }

    /**
     * @return the alta_estado_descri
     */
    public String getAlta_estado_descri() {
        return alta_estado_descri;
    }

    /**
     * @param alta_estado_descri the alta_estado_descri to set
     */
    public void setAlta_estado_descri(String alta_estado_descri) {
        this.alta_estado_descri = alta_estado_descri;
    }

    /**
     * @return the alta_destino
     */
    public int getAlta_destino() {
        return alta_destino;
    }

    /**
     * @param alta_destino the alta_destino to set
     */
    public void setAlta_destino(int alta_destino) {
        this.alta_destino = alta_destino;
    }

    /**
     * @return the alta_destino_descri
     */
    public String getAlta_destino_descri() {
        return alta_destino_descri;
    }

    /**
     * @param alta_destino_descri the alta_destino_descri to set
     */
    public void setAlta_destino_descri(String alta_destino_descri) {
        this.alta_destino_descri = alta_destino_descri;
    }

    /**
     * @return the alta_detalle
     */
    public String getAlta_detalle() {
        return alta_detalle;
    }

    /**
     * @param alta_detalle the alta_detalle to set
     */
    public void setAlta_detalle(String alta_detalle) {
        this.alta_detalle = alta_detalle;
    }

    /**
     * @return the alta_ip
     */
    public String getAlta_ip() {
        return alta_ip;
    }

    /**
     * @param alta_ip the alta_ip to set
     */
    public void setAlta_ip(String alta_ip) {
        this.alta_ip = alta_ip;
    }

    /**
     * @return the alta_usuario_rut
     */
    public String getAlta_usuario_rut() {
        return alta_usuario_rut;
    }

    /**
     * @param alta_usuario_rut the alta_usuario_rut to set
     */
    public void setAlta_usuario_rut(String alta_usuario_rut) {
        this.alta_usuario_rut = alta_usuario_rut;
    }

    /**
     * @return the alta_usuario_nombre
     */
    public String getAlta_usuario_nombre() {
        return alta_usuario_nombre;
    }

    /**
     * @param alta_usuario_nombre the alta_usuario_nombre to set
     */
    public void setAlta_usuario_nombre(String alta_usuario_nombre) {
        this.alta_usuario_nombre = alta_usuario_nombre;
    }

    /**
     * @return the alta_usuario_apellidop
     */
    public String getAlta_usuario_apellidop() {
        return alta_usuario_apellidop;
    }

    /**
     * @param alta_usuario_apellidop the alta_usuario_apellidop to set
     */
    public void setAlta_usuario_apellidop(String alta_usuario_apellidop) {
        this.alta_usuario_apellidop = alta_usuario_apellidop;
    }

    /**
     * @return the alta_usuario_apellidom
     */
    public String getAlta_usuario_apellidom() {
        return alta_usuario_apellidom;
    }

    /**
     * @param alta_usuario_apellidom the alta_usuario_apellidom to set
     */
    public void setAlta_usuario_apellidom(String alta_usuario_apellidom) {
        this.alta_usuario_apellidom = alta_usuario_apellidom;
    }
}
