/* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDato;

import java.util.Date;

/**
 *
 * @author EseGamboa
 */
public class cPaciente extends cUsuario {

    private String rut_paciente;
    private String nombres_paciente;
    private String apellidop_paciente;
    private String apellidom_paciente;
    private Date fechanacimiento;
    private String direccion;
    private String mail;
    private String telefono1;
    private String telefono2;

    private int sexo;

    private Date fechacreacion;
    private int comuna_codigo;
    private int id_prevision;
    private int tramo;
    

    private String fecha_nac;
    private String edad;

    private String fecha_creacion;
    private int procedencia; // servicio asistencial de procedencia al ingresar paciente
    private int consultorio;
    private int pueblo;
    private String sexo_descri;
    private String comuna_descri;
    private String procedencia_descri;
    private String consultorio_descri;
    private String pueblo_descri;
    private int tipo_prevision;
    private int prevision_prais;
    private int prevision_verificada;
    private String codigo_fonasa;
    private String codigo_fonasa_descripcion;

    private String tramo_prevision;
    private String error;
    private String rut_sdv;
    private String dv;
    private int prais;
    private int verificado_fonasa;

    private int nacion;
    private String nacion_descripcion;
    private String paciente_descripcion;

    private String parentesco_desc;
    private int id_contacto;
    private String ip_contacto;
    private String nombreusuario;
    private String variable;

    public cPaciente() {
        this.rut_paciente = "";
        this.nombres_paciente = "";
        this.apellidop_paciente = "";
        this.apellidom_paciente = "";
        this.sexo = -1;
        this.fecha_nac = "";
        this.edad = "";
        this.direccion = "";
        this.telefono1 = "";
        this.telefono2 = "";
        this.comuna_codigo = -1;
        this.fecha_creacion = "";
        this.procedencia = -1;
        this.consultorio = -1;
        this.pueblo = -1;
        this.sexo_descri = "";
        this.comuna_descri = "";
        this.procedencia_descri = "";
        this.consultorio_descri = "";
        this.pueblo_descri = "";
        this.tipo_prevision = -1;
        this.prevision_prais = -1;
        this.prevision_verificada = -1;
        this.codigo_fonasa = "";
        this.codigo_fonasa_descripcion = "";
        this.id_prevision = -1;
        this.tramo_prevision = "";
        this.error = "";
        this.rut_sdv = "";
        this.dv = "";
        this.prais = -1;
        this.verificado_fonasa = -1;
        mail = "";
        nacion = -1;
        nacion_descripcion = "";
        paciente_descripcion = "";
        parentesco_desc = "";
        id_contacto = 0;
        ip_contacto = "";
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    
    
    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    
    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    /**
     * @return the sexo
     */
    public int getSexo() {
        return sexo;
    }

    public int getTramo() {
        return tramo;
    }

    public void setTramo(int tramo) {
        this.tramo = tramo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the fecha_nac
     */
    public String getFecha_nac() {
        return fecha_nac;
    }

    /**
     * @param fecha_nac the fecha_nac to set
     */
    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    /**
     * @return the edad
     */
    public String getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(String edad) {
        this.edad = edad;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono1
     */
    public String getTelefono1() {
        return telefono1;
    }

    /**
     * @param telefono1 the telefono1 to set
     */
    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    /**
     * @return the telefono2
     */
    public String getTelefono2() {
        return telefono2;
    }

    /**
     * @param telefono2 the telefono2 to set
     */
    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    /**
     * @return the comuna_codigo
     */
    public int getComuna_codigo() {
        return comuna_codigo;
    }

    /**
     * @param comuna_codigo the comuna_codigo to set
     */
    public void setComuna_codigo(int comuna_codigo) {
        this.comuna_codigo = comuna_codigo;
    }

    /**
     * @return the fecha_creacion
     */
    public String getFecha_creacion() {
        return fecha_creacion;
    }

    /**
     * @param fecha_creacion the fecha_creacion to set
     */
    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    /**
     * @return the procedencia
     */
    public int getProcedencia() {
        return procedencia;
    }

    /**
     * @param procedencia the procedencia to set
     */
    public void setProcedencia(int procedencia) {
        this.procedencia = procedencia;
    }

    /**
     * @return the consultorio
     */
    public int getConsultorio() {
        return consultorio;
    }

    /**
     * @param consultorio the consultorio to set
     */
    public void setConsultorio(int consultorio) {
        this.consultorio = consultorio;
    }

    /**
     * @return the pueblo
     */
    public int getPueblo() {
        return pueblo;
    }

    /**
     * @param pueblo the pueblo to set
     */
    public void setPueblo(int pueblo) {
        this.pueblo = pueblo;
    }

    /**
     * @return the sexo_descri
     */
    public String getSexo_descri() {
        return sexo_descri;
    }

    /**
     * @param sexo_descri the sexo_descri to set
     */
    public void setSexo_descri(String sexo_descri) {
        this.sexo_descri = sexo_descri;
    }

    /**
     * @return the comuna_descri
     */
    public String getComuna_descri() {
        return comuna_descri;
    }

    /**
     * @param comuna_descri the comuna_descri to set
     */
    public void setComuna_descri(String comuna_descri) {
        this.comuna_descri = comuna_descri;
    }

    /**
     * @return the procedencia_descri
     */
    public String getProcedencia_descri() {
        return procedencia_descri;
    }

    /**
     * @param procedencia_descri the procedencia_descri to set
     */
    public void setProcedencia_descri(String procedencia_descri) {
        this.procedencia_descri = procedencia_descri;
    }

    /**
     * @return the consultorio_descri
     */
    public String getConsultorio_descri() {
        return consultorio_descri;
    }

    /**
     * @param consultorio_descri the consultorio_descri to set
     */
    public void setConsultorio_descri(String consultorio_descri) {
        this.consultorio_descri = consultorio_descri;
    }

    /**
     * @return the pueblo_descri
     */
    public String getPueblo_descri() {
        return pueblo_descri;
    }

    /**
     * @param pueblo_descri the pueblo_descri to set
     */
    public void setPueblo_descri(String pueblo_descri) {
        this.pueblo_descri = pueblo_descri;
    }

    /**
     * @return the tipo_prevision
     */
    public int getTipo_prevision() {
        return tipo_prevision;
    }

    /**
     * @param tipo_prevision the tipo_prevision to set
     */
    public void setTipo_prevision(int tipo_prevision) {
        this.tipo_prevision = tipo_prevision;
    }

    /**
     * @return the prevision_prais
     */
    public int getPrevision_prais() {
        return prevision_prais;
    }

    /**
     * @param prevision_prais the prevision_prais to set
     */
    public void setPrevision_prais(int prevision_prais) {
        this.prevision_prais = prevision_prais;
    }

    /**
     * @return the prevision_verificada
     */
    public int getPrevision_verificada() {
        return prevision_verificada;
    }

    /**
     * @param prevision_verificada the prevision_verificada to set
     */
    public void setPrevision_verificada(int prevision_verificada) {
        this.prevision_verificada = prevision_verificada;
    }

    /**
     * @return the codigo_fonasa
     */
    public String getCodigo_fonasa() {
        return codigo_fonasa;
    }

    /**
     * @param codigo_fonasa the codigo_fonasa to set
     */
    public void setCodigo_fonasa(String codigo_fonasa) {
        this.codigo_fonasa = codigo_fonasa;
    }

    /**
     * @return the codigo_fonasa_descripcion
     */
    public String getCodigo_fonasa_descripcion() {
        return codigo_fonasa_descripcion;
    }

    /**
     * @param codigo_fonasa_descripcion the codigo_fonasa_descripcion to set
     */
    public void setCodigo_fonasa_descripcion(String codigo_fonasa_descripcion) {
        this.codigo_fonasa_descripcion = codigo_fonasa_descripcion;
    }

    /**
     * @return the id_prevision
     */
    public int getId_prevision() {
        return id_prevision;
    }

    /**
     * @param id_prevision the id_prevision to set
     */
    public void setId_prevision(int id_prevision) {
        this.id_prevision = id_prevision;
    }

    /**
     * @return the tramo_prevision
     */
    public String getTramo_prevision() {
        return tramo_prevision;
    }

    /**
     * @param tramo_prevision the tramo_prevision to set
     */
    public void setTramo_prevision(String tramo_prevision) {
        this.tramo_prevision = tramo_prevision;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the rut_sdv
     */
    public String getRut_sdv() {
        return rut_sdv;
    }

    /**
     * @param rut_sdv the rut_sdv to set
     */
    public void setRut_sdv(String rut_sdv) {
        this.rut_sdv = rut_sdv;
    }

    /**
     * @return the dv
     */
    public String getDv() {
        return dv;
    }

    /**
     * @param dv the dv to set
     */
    public void setDv(String dv) {
        this.dv = dv;
    }

    /**
     * @return the prais
     */
    public int getPrais() {
        return prais;
    }

    /**
     * @param prais the prais to set
     */
    public void setPrais(int prais) {
        this.prais = prais;
    }

    /**
     * @return the verificado_fonasa
     */
    public int getVerificado_fonasa() {
        return verificado_fonasa;
    }

    /**
     * @param verificado_fonasa the verificado_fonasa to set
     */
    public void setVerificado_fonasa(int verificado_fonasa) {
        this.verificado_fonasa = verificado_fonasa;
    }

    /**
     * @return the rut_paciente
     */
    public String getRut_paciente() {
        return rut_paciente;
    }

    /**
     * @param rut_paciente the rut_paciente to set
     */
    public void setRut_paciente(String rut_paciente) {
        this.rut_paciente = rut_paciente;
    }

    /**
     * @return the nombres_paciente
     */
    public String getNombres_paciente() {
        return nombres_paciente;
    }

    /**
     * @param nombres_paciente the nombres_paciente to set
     */
    public void setNombres_paciente(String nombres_paciente) {
        this.nombres_paciente = nombres_paciente;
    }

    /**
     * @return the apellidop_paciente
     */
    public String getApellidop_paciente() {
        return apellidop_paciente;
    }

    /**
     * @param apellidop_paciente the apellidop_paciente to set
     */
    public void setApellidop_paciente(String apellidop_paciente) {
        this.apellidop_paciente = apellidop_paciente;
    }

    /**
     * @return the apellidom_paciente
     */
    public String getApellidom_paciente() {
        return apellidom_paciente;
    }

    /**
     * @param apellidom_paciente the apellidom_paciente to set
     */
    public void setApellidom_paciente(String apellidom_paciente) {
        this.apellidom_paciente = apellidom_paciente;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the nacion
     */
    public int getNacion() {
        return nacion;
    }

    /**
     * @param nacion the nacion to set
     */
    public void setNacion(int nacion) {
        this.nacion = nacion;
    }

    /**
     * @return the nacion_descripcion
     */
    public String getNacion_descripcion() {
        return nacion_descripcion;
    }

    /**
     * @param nacion_descripcion the nacion_descripcion to set
     */
    public void setNacion_descripcion(String nacion_descripcion) {
        this.nacion_descripcion = nacion_descripcion;
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

    /**
     * @return the parentesco_desc
     */
    public String getParentesco_desc() {
        return parentesco_desc;
    }

    /**
     * @param parentesco_desc the parentesco_desc to set
     */
    public void setParentesco_desc(String parentesco_desc) {
        this.parentesco_desc = parentesco_desc;
    }

    /**
     * @return the id_contacto
     */
    public int getId_contacto() {
        return id_contacto;
    }

    /**
     * @param id_contacto the id_contacto to set
     */
    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
    }

    /**
     * @return the ip_contacto
     */
    public String getIp_contacto() {
        return ip_contacto;
    }

    /**
     * @param ip_contacto the ip_contacto to set
     */
    public void setIp_contacto(String ip_contacto) {
        this.ip_contacto = ip_contacto;
    }

}
