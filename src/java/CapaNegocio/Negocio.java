//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package CapaNegocio;

import CapaConexion.Conexion;
import CapaDato.ClinicData;
import CapaDato.DetailIndexBarthel;
import CapaDato.DuoDetailBarthel;
import CapaDato.DuoIndexBarthel;
import CapaDato.HistorialVisita;
import CapaDato.cAlta_Das;
import CapaDato.cCategorizacion;
import CapaDato.cContacto;
import CapaDato.cDas;
import CapaDato.cDato;
import CapaDato.cDiagnostico;
import CapaDato.cDuo;
import CapaDato.cEpicrisis;
import CapaDato.cEvaNeurologia;
import CapaDato.cEvaRespiratoria;
import CapaDato.cEvaTraumatologia;
import CapaDato.cExamen;
import CapaDato.cHistorial_Consultorio;
import CapaDato.cHito;
import CapaDato.cPaciente;
import CapaDato.cReceta;
import CapaDato.cRegistroSeguimiento;
import CapaDato.cRegistroSocial;
import CapaDato.cSesionKine;
import CapaDato.cUsuario;
import CapaDato.cVisita;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Negocio {

    public Conexion cnn;

    public Negocio() {
    }

    public void configurarConexion(String tabla) {
        this.cnn = new Conexion();
        this.cnn.setDriver("org.postgresql.Driver");
        this.cnn.setNombreTabla(tabla);
        this.cnn.setUser("postgres");
        this.cnn.setPassword("crsdb2008");
        this.cnn.setNombreBaseDatos("jdbc:postgresql://localhost:5432/crsm");
    }

    public String getLocal() {
       // String local = "http://10.8.4.163:8080/modulo_uhce/";
       String local = "http://localhost:8080/modulo_uhce/";
        return local;
    }

    /*NEW INFO*/
    public HistorialVisita getVisita(int id_visita) {
        HistorialVisita h = null;
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select * from schema_uo.visita V, schema_uo.visita_categorizacion VC,"
                + "schema_uo.cama C,schema_uo.usuario U where V.rut_usuario=U.rut_usuario and V.id_cama=C.id_cama and "
                + "V.id_visita_categorizacion=VC.id_visita_categorizacion and V.id_visita=" + id_visita + "");

        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                h = new HistorialVisita();
                h.setD1_visita_categorizacion(this.cnn.getRst().getInt("d1_visita_categorizacion"));
                h.setD2_visita_categorizacion(this.cnn.getRst().getInt("d2_visita_categorizacion"));
                h.setD3_visita_categorizacion(this.cnn.getRst().getInt("d3_visita_categorizacion"));
                h.setD4_visita_categorizacion(this.cnn.getRst().getInt("d4_visita_categorizacion"));
                h.setD5_visita_categorizacion(this.cnn.getRst().getInt("d5_visita_categorizacion"));
                h.setD6_visita_categorizacion(this.cnn.getRst().getInt("d6_visita_categorizacion"));
                h.setR1_visita_categorizacion(this.cnn.getRst().getInt("r1_visita_categorizacion"));
                h.setR2_visita_categorizacion(this.cnn.getRst().getInt("r2_visita_categorizacion"));
                h.setR3_visita_categorizacion(this.cnn.getRst().getInt("r3_visita_categorizacion"));
                h.setR4_visita_categorizacion(this.cnn.getRst().getInt("r4_visita_categorizacion"));
                h.setR5_visita_categorizacion(this.cnn.getRst().getInt("r5_visita_categorizacion"));
                h.setR6_visita_categorizacion(this.cnn.getRst().getInt("r6_visita_categorizacion"));
                h.setR7_visita_categorizacion(this.cnn.getRst().getInt("r7_visita_categorizacion"));
                h.setR8_visita_categorizacion(this.cnn.getRst().getInt("r8_visita_categorizacion"));
                h.setCat_visita_categorizacion(this.cnn.getRst().getString("cat_visita_categorizacion"));
                h.setFecha_visita(this.cnn.getRst().getDate("fecha_visita"));
                h.setHora_visita(this.cnn.getRst().getString("hora_visita"));
                h.setObs_visita(this.cnn.getRst().getString("obs_visita"));
                h.setRut_usuario(this.cnn.getRst().getString("rut_usuario"));
                h.setId_duo(this.cnn.getRst().getInt("id_duo"));
                h.setId_cama(this.cnn.getRst().getInt("id_cama"));
                h.setTipo_visita(this.cnn.getRst().getInt("tipo_visita"));
                h.setDescripcion_cama(this.cnn.getRst().getString("descripcion_cama"));
                h.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                h.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                h.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));

            }
        } catch (SQLException var7) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, (String) null, var7);
        }

        this.cnn.cerrarConexion();
        return (h);

    }

    /*new code*/
    public boolean ingresa_sesion_psicologo(cSesionKine ses) {
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO   schema_uo.psicolo_sesion ( ses_estado, ses_usuario,\n  ses_fecha_ingreso, ses_fecha_hora, ses_detalle,ses_duo ) \nVALUES ( '1', '" + ses.getRut_usuario() + "',\n  CURRENT_TIMESTAMP, '" + ses.getFecha_hora() + "', '" + ses.getDetalle() + "', '" + ses.getId_duo() + "' );");

        try {
            this.cnn.conectar();
            sw = true;
        } catch (Exception var7) {
            sw = false;
        } finally {
            this.cnn.cerrarConexion();
        }

        return sw;
    }

    public void ingresa_visita_medica(cVisita vis) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO  schema_uo.visita_medica ( vis_usuario, vis_fecha_ingreso, vis_duo,   vis_fecha, vis_evolucion, vis_estado )  VALUES (   '" + vis.getRut_usuario() + "', CURRENT_TIMESTAMP,  '" + vis.getId_duo() + "' ,   '" + vis.getFecha_visita() + "', '" + vis.getObs_visita() + "', '" + 1 + "'  ); ");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void anula_visita_medica(String motivo, String usuario, int id_visita_medica) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE  schema_uo.visita_medica  SET  vis_estado ='0', vis_usuario_anula = '" + usuario + "',  vis_motivo_anula = '" + motivo + "'  WHERE  vis_id = '" + id_visita_medica + "'  ");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    /*new code index barthel*/
    public void insertDuoIndexBarthel(DuoIndexBarthel duoIndexBarthel) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO schema_uo.duo_index_barthel(\n"
                + "             status, user_registers, date_registers, type_registers, total_puntuction, \n"
                + "            degree_of_dependency,id_duo)\n"
                + "    VALUES (1, '" + duoIndexBarthel.getUserRegisters() + "', CURRENT_TIMESTAMP, " + duoIndexBarthel.getTypeRegisters() + ", " + duoIndexBarthel.getTotalPuntuction() + ", \n"
                + "            '" + duoIndexBarthel.getDegreeOfDependency() + "', " + duoIndexBarthel.getIdDuo() + ");");
        this.cnn.conectar();
        this.cnn.cerrarConexion();

    }

    public int getIdRegisterDuoIndexBarthel(int idDuo, int TypeRegisters) {
        int idRegister = 0;
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT id\n"
                + "  FROM schema_uo.duo_index_barthel where id_duo = " + idDuo + " and type_registers = " + TypeRegisters + " ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                idRegister = this.cnn.getRst().getInt("id");
            }
        } catch (SQLException var7) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, (String) null, var7);
        }

        this.cnn.cerrarConexion();
        return idRegister;
    }

    public void insertDouDetailBarthel(DuoDetailBarthel duoDetailBarthel) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO schema_uo.duo_detail_barthel(\n"
                + "            id_register_duo, id_detail_barthel)\n"
                + "    VALUES (" + duoDetailBarthel.getIdRegisterDuo() + ", " + duoDetailBarthel.getIdDetailBarthel() + ");");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    /*new code report clinitc data*/
    public List<ClinicData> searchClinicData(Date dateInic, Date dateEnd) {
        List<ClinicData> listClinicData = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("(SELECT \n"
                + " sum(quantity) , 1 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinicha where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "  \n"
                + "  union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 2 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichb where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 3 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichc where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 4 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichd where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 5 as tipe\n"
                + "FROM \n"
                + "  schema_uo.datacliniche where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT  \n"
                + " sum(quantity) , 6 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichf where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 7 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichg where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 8 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichh where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 9 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichi where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 10 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichj where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 11 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichk where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 12 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichl where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 13 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichm where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 14 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichn where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "  \n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 15 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinicho where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 16 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichp where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 17 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichq where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 18 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichr where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 19 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichs where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 20 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinicht where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 21 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichu where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT  \n"
                + " sum(quantity) , 22 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichv where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "'\n"
                + "    union \n"
                + "  \n"
                + "  SELECT \n"
                + " sum(quantity) , 23 as tipe\n"
                + "FROM \n"
                + "  schema_uo.dataclinichw where dataRegister BETWEEN '" + dateInic + "' and '" + dateEnd + "' ) order by tipe ");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                ClinicData clinicData = new ClinicData();
                clinicData.setQuantity(this.cnn.getRst().getInt("sum"));
                clinicData.setType(this.cnn.getRst().getInt("tipe"));
                listClinicData.add(clinicData);

            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return listClinicData;

    }

    /*new code*/
    public String guardarPaciente(cPaciente p) throws UnknownHostException {
        String mensaje = "";
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO "
                + "  agenda.paciente "
                + "VALUES ("
                + "  '" + p.getRut_paciente().toUpperCase() + "',"
                + "  '" + p.getNombres_paciente() + "',"
                + "  '" + p.getApellidop_paciente() + "',"
                + "  '" + p.getApellidom_paciente() + "',"
                + "  '" + p.getFechanacimiento() + "',"
                + "  '" + p.getDireccion() + "',"
                + "  '" + p.getMail() + "',"
                + "  '" + p.getTelefono1() + "',"
                + "  '" + p.getTelefono2() + "',"
                + "  1,"
                + "  " + p.getSexo() + ","
                + "   CURRENT_DATE ,"
                + "  " + p.getComuna_codigo() + ","
                + "  " + p.getId_prevision() + ","
                + "  " + p.getTramo() + ","
                + "  " + p.getProcedencia() + ", "
                + "  '" + p.getIp_contacto() + "' ,"
                + "   '" + p.getNombreusuario() + "', "
                + "  -1, "
                + "  -1,"
                + "  " + p.getNacion() + ", "
                + "  ' ' ,"
                + "" + p.getPueblo() + ""
                + ");");
        this.cnn.conectar();
        if (this.cnn.getResultadoSQL() == 1) {
            mensaje = "Paciente Registrado Exitosamente.";
        } else {
            mensaje = "Ocurrio un problema!! Intente mas tarde";
        }
        this.cnn.cerrarConexion();
        return mensaje;
    }

    public List<DetailIndexBarthel> searchDetailIndexBarthel() {
        List<DetailIndexBarthel> listDetailIndexBarthel = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT index.id_index_barthel, description_barthel, description_detail, punctuction , detail.id_detail\n"
                + "  FROM schema_uo.index_barthel index inner join schema_uo.detail_index_barthel detail on index.id_index_barthel = detail.id_index_barthel\n"
                + "  where index.status_barthel = 1 and detail.status=1 order by index.id_index_barthel asc , punctuction desc");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                DetailIndexBarthel detailIndexBarthel = new DetailIndexBarthel();
                detailIndexBarthel.setIdIndexBarthel(this.cnn.getRst().getInt("id_index_barthel"));
                detailIndexBarthel.setDescriptionDetail(this.cnn.getRst().getString("description_detail"));
                detailIndexBarthel.setDescriptionBarthel(this.cnn.getRst().getString("description_barthel"));
                detailIndexBarthel.setPunctuction(this.cnn.getRst().getInt("punctuction"));
                detailIndexBarthel.setIdDetail(this.cnn.getRst().getInt("id_detail"));
                listDetailIndexBarthel.add(detailIndexBarthel);

            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return listDetailIndexBarthel;

    }

    public List<DetailIndexBarthel> listDetailIndexBarthel(int id) {
        List<DetailIndexBarthel> listDetail = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT ib.id_index_barthel, description_barthel, description_detail, punctuction , dib.id_detail\n"
                + "  FROM schema_uo.duo_detail_barthel ddb\n"
                + "  inner join schema_uo.detail_index_barthel dib\n"
                + "  on ddb.id_detail_barthel = dib.id_detail\n"
                + "  inner join  schema_uo.index_barthel ib on ib.id_index_barthel = dib.id_index_barthel\n"
                + "  where ib.status_barthel = 1 and dib.status=1 and ddb.id_register_duo =" + id + "\n"
                + "  order by ib.id_index_barthel asc ,\n"
                + "  punctuction desc ");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                DetailIndexBarthel detailIndexBarthel = new DetailIndexBarthel();
                detailIndexBarthel.setIdIndexBarthel(this.cnn.getRst().getInt("id_index_barthel"));
                detailIndexBarthel.setDescriptionDetail(this.cnn.getRst().getString("description_detail"));
                detailIndexBarthel.setDescriptionBarthel(this.cnn.getRst().getString("description_barthel"));
                detailIndexBarthel.setPunctuction(this.cnn.getRst().getInt("punctuction"));
                detailIndexBarthel.setIdDetail(this.cnn.getRst().getInt("id_detail"));
                listDetail.add(detailIndexBarthel);

            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return listDetail;
    }


    /*new code*/
    public Vector<cDato> searchViaAdmision() {
        Vector<cDato> lista = new Vector<cDato>();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT \n"
                + "  id_via_medicamento,\n"
                + "  descripcion_via_medicamento,\n"
                + "  estado_via_medicamento\n"
                + "FROM \n"
                + "  schema_uo.via_medicamento where estado_via_medicamento = 1 order by descripcion_via_medicamento;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cDato via = new cDato();
                via.setId(this.cnn.getRst().getInt("id_via_medicamento"));
                via.setDescription(this.cnn.getRst().getString("descripcion_via_medicamento"));
                lista.add(via);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public void ingresarindicaones(cReceta r) {
        this.configurarConexion("");
        cnn.setEsSelect(false);
        cnn.setSentenciaSQL("INSERT INTO schema_uo.indicaciones(\n"
                + " idindicaciones, idduo, reposo, regimen, otras_indicaciones, fecha, \n"
                + "  usuario, control_signos, aislamiento, alergias, diagnostico, contencion, \n"
                + "            imagenes, otros, indicaciones_enfermeria, indicaciones_nutricionista, \n"
                + "            indicaciones_kinesiologo, indicaciones_otros)\n"
                + "    VALUES ( " + r.getId_receta() + "," + r.getId_duo() + ", '" + r.getReposo() + "', '" + r.getRegimen() + "',"
                + " '" + r.getIndicacion() + "', CURRENT_TIMESTAMP, \n"
                + " '" + r.getNombre_usuario() + "', '" + r.getControl_signos() + "', '" + r.getAislamiento() + "',"
                + " '" + r.getAlergias() + "', '" + r.getDiagnostico() + "', '" + r.getContencion() + "', '" + r.getImagenes() + "',"
                + " '" + r.getOtros() + "', '" + r.getIndicaciones_enfermeria() + "' , '" + r.getIndicaciones_nutricionista() + "' , "
                + " '" + r.getIndicaciones_kinesiologo() + "', '" + r.getIndicaciones_otros() + "');");
        cnn.conectar();
        cnn.cerrarConexion();
    }

    public void ingresarindicaonesReceta(cReceta r) {
        this.configurarConexion("");
        cnn.setEsSelect(false);
        cnn.setSentenciaSQL("INSERT INTO schema_uo.indicaciones_receta(\n"
                + "             id_indicaciones, id_medicamento, cantidad, id_medida, \n"
                + "            frecuencia, duracion, indicaciones_especiales,id_via)\n"
                + "    VALUES ( " + r.getId_receta() + ", " + r.getId_receta_detalle() + ", " + r.getCantidad() + ", " + r.getMedida() + ", \n"
                + "            '" + r.getFrecuencia() + "', " + r.getDuracion() + ", '" + r.getIndicacion() + "', " + r.getId_via() + ");");
        cnn.conectar();
        cnn.cerrarConexion();
    }

    public void ingresarSolicitudExamenes(cReceta r) {
        this.configurarConexion("");
        cnn.setEsSelect(false);
        cnn.setSentenciaSQL("INSERT INTO schema_uo.solicitudexamenes_indicaciones(\n"
                + "            idindicaciones,  id_examenes)\n"
                + "    VALUES (" + r.getId_duo() + ",  " + r.getId_receta() + "); ");
        cnn.conectar();
        cnn.cerrarConexion();
    }

    public String upperCaseFirst(String value) {

        value = value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
        return value;
    }

    /*new code*/
    public boolean buscarpaciente(String rut) {
        boolean siexiste = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  * FROM agenda.paciente where upper(rut)=upper('" + rut + "') and estatus = 1  ");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                siexiste = true;
            }
        } catch (SQLException ex) {

        }

        this.cnn.cerrarConexion();

        return siexiste;
    }

    public void modifica_paciente_datos(String rut, int consultorio) {
        this.configurarConexion("");
        cnn.setEsSelect(false);
        cnn.setSentenciaSQL("UPDATE  agenda.paciente  "
                + " SET  procedencia = '" + consultorio + "' "
                + " WHERE  rut ='" + rut + "' ;");
        cnn.conectar();
        cnn.cerrarConexion();
    }

    public void ingresa_anula_duo(String motivo, String usuario, int duo) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO  schema_uo.anulo_duo  (  motivo_anulo_duo,  fecha_anulo_duo, rut_usuario, id_duo  ) VALUES (  '" + motivo + "',CURRENT_TIMESTAMP,    '" + usuario + "','" + duo + "' );");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void modifica_paciente_fono_correo(cPaciente pac) {
        this.configurarConexion("");
        cnn.setEsSelect(false);
        cnn.setSentenciaSQL("UPDATE  "
                + " agenda.paciente   "
                + " SET  "
                + "  contacto1 = '" + pac.getTelefono1() + "', "
                + "  contacto2 = '" + pac.getTelefono2() + "', "
                + "  email = '" + pac.getMail() + "'  "
                + "  WHERE   rut = '" + pac.getRut_paciente() + "';");
        cnn.conectar();
        cnn.cerrarConexion();
    }

    public void modifica_valida_epicrisis(int duo_id) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE  schema_uo.duo   SET  fecha_hora_alta_med_duo = CURRENT_TIMESTAMP  WHERE  id_duo = '" + duo_id + "'");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void modifica_estado_duo_alta_administrativa(int duo_id, int nuevo_estado) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE  schema_uo.duo   SET  estado_duo = '" + nuevo_estado + "' , fecha_hora_alta_adm_duo = CURRENT_TIMESTAMP  WHERE  id_duo ='" + duo_id + "';");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void modifica_estado_duo(int duo_id, int nuevo_estado) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE  schema_uo.duo   SET  estado_duo = '" + nuevo_estado + "'  WHERE  id_duo ='" + duo_id + "';");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void ingresa_epicrisis(cEpicrisis epi) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("insert into schema_uo.epicrisis (resumen_epicrisis,examen_epicrisis,diagnostico_epicrisis,indicacion_epicrisis,fecha_epicrisis,hora_epicrisis,rut_usuario,id_duo,ip_epicrisis,medicamentos_prescritos) values('" + epi.getResumen_epicrisis() + "','" + epi.getExamen_epicrisis() + "', '" + epi.getDiagnostico_epicrisis() + "','" + epi.getIndicacion_epicrisis() + "','" + epi.getFecha_epicrisis() + "','" + epi.getHora_epicrisis() + "','" + epi.getRut_usuario() + "'," + epi.getId_duo() + ",'" + epi.getIp_epicrisis() + "' ,'" + epi.getMedicamentos_prescritos() + "')");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void ingresa_alta_adm(String obs, String fecha, String hora, String rut_usuario, int id_duo, int id_destino, String ip) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("insert into schema_uo.alta_adm(fecha_hora_alta_adm,rut_usuario,obs_alta_adm,id_duo,id_destino,ip_alta_adm) values('" + fecha + " " + hora + "','" + rut_usuario + "','" + obs + "'," + id_duo + "," + id_destino + ",'" + ip + "')");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void modifica_duo_x_medico(cDuo duo) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE schema_uo.duo SET  estado_duo='" + duo.getEstado_duo() + "',anamnesis_duo='" + duo.getAnamnesis_duo() + "', id_categorizacion='" + duo.getCategorizacion_id() + "', rut_usuario_ing_med='" + duo.getRut_usuario_ing_med() + "',  fecha_hora_ing_med=current_timestamp, ip_ing_med='" + duo.getIp_ing_med() + "'    where id_duo='" + duo.getId_duo() + "';");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void modifica_duo_x_enfermeria(cDuo duo) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE schema_uo.duo SET  estado_duo='" + duo.getEstado_duo() + "',duo_tiene_enfermeria='1', ip_ing_enf='" + duo.getIp_ing_enf() + "'  where id_duo='" + duo.getId_duo() + "' ;");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void ingresa_ingreso_enfermeria(String morbilidades, String farmacos, String observacion, String rut_usuario, int id_ex_fisico, int id_duo, String otro_ex_docto_ing_enfermeria) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        String query = "insert into schema_uo.ing_enfermeria(otro_comorbilidad_ing_enfermeria,farmaco_ing_enfermeria,obs_ing_enfermeria,rut_usuario_ing_enfermeria,id_examen_fisico_ing_enfermeria,id_duo_ing_enfermeria,otro_ex_docto_ing_enfermeria) values('" + morbilidades.toUpperCase().replace("'", "''") + "','" + farmacos.toUpperCase().replace("'", "''") + "','" + observacion.toUpperCase().replace("'", "''") + "','" + rut_usuario.toUpperCase().replace("'", "''") + "'," + id_ex_fisico + "," + id_duo + ",'" + otro_ex_docto_ing_enfermeria.toUpperCase().replace("'", "''") + "')";
        this.cnn.setSentenciaSQL(query);
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public int ingresa_ExamenFisico(String conciencia, String cabeza, String mucoza, String torax, String abdomen, String eess, String eeii, String zona, String sng, String sfoley, String peso, String talla, String pulso, String presion, String temp, String sat, String vvp1, String vvp2, String vvc, String dorso_lumbar_ex_fisico, String piel_ex_fisico) {
        int id_examen_fisico = 0;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        String query = "insert into schema_uo.ex_fisico(conciencia_ex_fisico,cabeza_ex_fisico,mucosa_ex_fisico,torax_ex_fisico,abdomen_ex_fisico,eess_ex_fisico,eeii_ex_fisico,z_sacra_ex_fisico,peso_ex_fisico,talla_ex_fisico,pulso_ex_fisico,presion_a_ex_fisico,temp_ex_fisico,satura_ex_fisico,vvp1_ex_fisico,vvp2_ex_fisico,vvc_ex_fisico,sng_ex_fisico,s_foley_ex_fisico,dorso_lumbar_ex_fisico,piel_ex_fisico) VALUES('" + conciencia.toUpperCase().replace("'", "''") + "','" + cabeza.toUpperCase().replace("'", "''") + "','" + mucoza.toUpperCase().replace("'", "''") + "','" + torax.toUpperCase().replace("'", "''") + "','" + abdomen.toUpperCase().replace("'", "''") + "','" + eess.toUpperCase().replace("'", "''") + "','" + eeii.toUpperCase().replace("'", "''") + "','" + zona.toUpperCase().replace("'", "''") + "','" + peso.toUpperCase().replace("'", "''") + "','" + talla.toUpperCase().replace("'", "''") + "','" + pulso.toUpperCase().replace("'", "''") + "','" + presion.toUpperCase().replace("'", "''") + "','" + temp.toUpperCase().replace("'", "''") + "','" + sat.toUpperCase().replace("'", "''") + "','" + vvp1.toUpperCase().replace("'", "''") + "','" + vvp2.toUpperCase().replace("'", "''") + "','" + vvc.toUpperCase().replace("'", "''") + "','" + sng.toUpperCase().replace("'", "''") + "','" + sfoley.toUpperCase().replace("'", "''") + "','" + dorso_lumbar_ex_fisico.toUpperCase().replace("'", "''") + "','" + piel_ex_fisico.toUpperCase().replace("'", "''") + "')";
        this.cnn.setSentenciaSQL(query);
        this.cnn.conectar();
        this.cnn.cerrarConexion();
        String query_curval = "select id_ex_fisico from schema_uo.ex_fisico order by id_ex_fisico desc limit 1 ";
        this.cnn.setSentenciaSQL(query_curval);
        this.cnn.setEsSelect(true);
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                id_examen_fisico = this.cnn.getRst().getInt("id_ex_fisico");
            }
        } catch (SQLException var26) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var26);
        }

        this.cnn.cerrarConexion();
        return id_examen_fisico;
    }

    public void ingresa_DoctoAdjunto(int id_duo, int id_documento) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("insert into schema_uo.duo_documento(id_duo,id_documento) values(" + id_duo + "," + id_documento + ")");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void ingresa_EnfCronica(int id_duo, int id_enfermedad) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("insert into schema_uo.duo_enfermedad_cro(id_duo,id_enfermedad_cro) values(" + id_duo + "," + id_enfermedad + ")");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void ingresa_PrestacionesDuo(int duo, int prestacion) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO  schema_uo.prestacion_duo   (duop_duo,duop_prestacion, duop_estado)    VALUES ('" + duo + "', '" + prestacion + "','1'); ");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void ingresa_duo(cDuo duo) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO schema_uo.duo  (fecha_duo, hora_duo,    estado_duo, id_cama, id_prevision,   fecha_hora_ing_duo, rut_usuario, rut_paciente,    id_derivador, id_categorizacion,   tipo_duo_id, duo_tiene_enfermeria,programa_duo) VALUES (  '" + duo.getFecha_duo() + "','" + duo.getHora_duo() + "','1','" + duo.getCama() + "','" + duo.getId_prevision() + "',CURRENT_TIMESTAMP,  '" + duo.getRut_usuario() + "','" + duo.getRut_paciente() + "','" + duo.getDerivador_id() + "','0','1','0' ,'" + duo.getPrograma() + "' );");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void ingresa_diagnostico_duo(cDiagnostico diag) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO  schema_uo.diagnostico_duo  ( descripcion_diagnostico_duo, tipo_diagnostico_duo, id_duo ) VALUES ( '" + diag.getDescripcion_diagnostico() + "', '" + diag.getTipo_diagnostico() + "', '" + diag.getId_duo() + "');");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void elimina_diagnostico_duo(int id_diagnostico) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("Delete from schema_uo.diagnostico_duo where  id_diagnostico_duo='" + id_diagnostico + "' ");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void modifica_cama_duo(int duo_id, int cama_nueva) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE  schema_uo.duo   SET  id_cama = '" + cama_nueva + "'   WHERE  id_duo ='" + duo_id + "'");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void modifica_paciente(cPaciente pac) {
        this.configurarConexion("");
        cnn.setEsSelect(false);
        cnn.setSentenciaSQL("UPDATE  "
                + " agenda.paciente   "
                + " SET  "
                + " direccion = '" + pac.getDireccion() + "', "
                + "  contacto1 = '" + pac.getTelefono1() + "', "
                + "  contacto2 = '" + pac.getTelefono2() + "', "
                + "  id_comuna = '" + pac.getComuna_codigo() + "',  "
                + "  id_puebloorigen = '" + pac.getPueblo() + "', "
                + "  procedencia = '" + pac.getConsultorio() + "', "
                + "  email = '" + pac.getMail() + "',  "
                + "  id_nacionalidad = '" + pac.getNacion() + "' "
                + "  WHERE    rut = '" + pac.getRut_paciente() + "';");
        cnn.conectar();
        cnn.cerrarConexion();
    }

    public void ingresa_prevision(cPaciente pac) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO schema_urgencia.prevision_paciente  ( id_paciente_prevision_paciente,   codigo_fonasa_prevision_paciente, tramo_prevision_paciente,estado_prevision_paciente,   prais_prevision_paciente,verificado_fonasa  ) VALUES (   '" + pac.getRut_paciente() + "',   '" + pac.getCodigo_fonasa() + "',   '" + pac.getTramo_prevision() + "',   '1', '" + pac.getPrais() + "',   '" + pac.getVerificado_fonasa() + "');");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void modifica_prevision(int id_num) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL(" UPDATE schema_urgencia.prevision_paciente   SET  estado_prevision_paciente = '0'  WHERE  id_prevision_paciente = '" + id_num + "'");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void modifica_prevision_todas(String rut) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL(" UPDATE schema_urgencia.prevision_paciente   SET  estado_prevision_paciente = '0'  WHERE  id_paciente_prevision_paciente = '" + rut + "'");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public cUsuario valida_Usuario(String rut, String pass) {
        cUsuario aux = new cUsuario();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  rut_usuario, perfil_usuario, pass_usuario,  nombre_usuario, apellidop_usuario,apellidom_usuario,  BB.descripcion_perfil  FROM schema_uo.usuario AA JOIN schema_uo.perfil BB ON   (AA.perfil_usuario=BB.id_perfil)   where rut_usuario='" + rut + "'  and pass_usuario='" + pass + "' and estado_usuario=1 ; ");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                aux.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                aux.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                aux.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                aux.setPass_usuario(this.cnn.getRst().getString("pass_usuario"));
                aux.setPerfil_usuario(this.cnn.getRst().getInt("perfil_usuario"));
                aux.setRut_usuario(this.cnn.getRst().getString("rut_usuario"));
                aux.setPerfil_descripcion_usuario(this.cnn.getRst().getString("descripcion_perfil"));
            }
        } catch (SQLException var5) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var5);
        }

        this.cnn.cerrarConexion();
        return aux;
    }

    public cUsuario valida_Usuario_sinPass(String rut) {
        cUsuario aux = new cUsuario();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  rut_usuario, perfil_usuario, pass_usuario,  nombre_usuario, apellidop_usuario,apellidom_usuario,estado_usuario,  BB.descripcion_perfil  FROM schema_uo.usuario AA JOIN schema_uo.perfil BB ON   (AA.perfil_usuario=BB.id_perfil)   where rut_usuario='" + rut + "'  ");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                aux.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                aux.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                aux.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                aux.setPass_usuario(this.cnn.getRst().getString("pass_usuario"));
                aux.setPerfil_usuario(this.cnn.getRst().getInt("perfil_usuario"));
                aux.setRut_usuario(this.cnn.getRst().getString("rut_usuario"));
                aux.setPerfil_descripcion_usuario(this.cnn.getRst().getString("descripcion_perfil"));
                aux.setEstado_usuario(this.cnn.getRst().getInt("estado_usuario"));
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return aux;
    }

    public int ingresa_categorizacion_enfermeria(int d1, int d2, int d3, int d4, int d5, int d6, int r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, String cat) {
        int id_categorizacion = 0;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("insert into schema_uo.visita_categorizacion (d1_visita_categorizacion,d2_visita_categorizacion,d3_visita_categorizacion,d4_visita_categorizacion,d5_visita_categorizacion,d6_visita_categorizacion,r1_visita_categorizacion,r2_visita_categorizacion,r3_visita_categorizacion,r4_visita_categorizacion,r5_visita_categorizacion,r6_visita_categorizacion,r7_visita_categorizacion,r8_visita_categorizacion,cat_visita_categorizacion) values(" + d1 + "," + d2 + "," + d3 + "," + d4 + "," + d5 + "," + d6 + "," + r1 + "," + r2 + "," + r3 + "," + r4 + "," + r5 + "," + r6 + "," + r7 + "," + r8 + ",'" + cat.trim() + "')");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  id_visita_categorizacion FROM  schema_uo.visita_categorizacion order by    id_visita_categorizacion desc limit 1  ;");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                id_categorizacion = this.cnn.getRst().getInt("id_visita_categorizacion");
            }
        } catch (SQLException var18) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var18);
        }

        this.cnn.cerrarConexion();
        return id_categorizacion;
    }

    public int ingresa_visita_enfermeria(String obs, String fecha, String hora, String rut_usu, int id_cama, int id_cat, int tipo, int id_duo) {
        int id_visita = 0;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("insert into schema_uo.visita (obs_visita,fecha_visita,hora_visita,rut_usuario,id_cama,id_visita_categorizacion,tipo_visita,id_duo) values('" + obs + "','" + fecha + "','" + hora + "','" + rut_usu + "'," + id_cama + "," + id_cat + "," + tipo + "," + id_duo + ")");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT id_visita  FROM   schema_uo.visita order by id_visita desc limit 1 ;");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                id_visita = this.cnn.getRst().getInt("id_visita");
            }
        } catch (SQLException var11) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var11);
        }

        this.cnn.cerrarConexion();
        return id_visita;
    }

    public void modifica_ultima_sesion(String rut_usuario) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE  schema_uo.usuario set ultima_sesion=current_timestamp where rut_usuario='" + rut_usuario + "'   ");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void modifica_clave(String rut_usuario, String clave_usuario) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE  schema_uo.usuario  set pass_usuario='" + clave_usuario + "' where rut_usuario='" + rut_usuario + "'   ");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void ingresa_usuario(cUsuario usu) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO   schema_uo.usuario (  rut_usuario,  perfil_usuario,  pass_usuario,   nombre_usuario, apellidop_usuario,   apellidom_usuario,estado_usuario  )  VALUES (   '" + usu.getRut_usuario() + "', " + usu.getPerfil_usuario() + ",  '" + usu.getPass_usuario() + "',  '" + usu.getNombre_usuario() + "',    '" + usu.getApellidop_usuario() + "','" + usu.getApellidom_usuario() + "','1');");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void modifica_usuario(cUsuario usu) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE  schema_uo.usuario  \nSET  perfil_usuario = '" + usu.getPerfil_usuario() + "', \n  nombre_usuario = '" + usu.getNombre_usuario() + "', \n  apellidop_usuario =  '" + usu.getApellidop_usuario() + "', \n  apellidom_usuario = '" + usu.getApellidom_usuario() + "', \n  estado_usuario = '" + usu.getEstado_usuario() + "' \nWHERE  rut_usuario = '" + usu.getRut_usuario() + "' ");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void modifica_password_usuario(cUsuario usu) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE   schema_uo.usuario  \nSET pass_usuario = '" + usu.getPass_usuario() + "' \nWHERE  rut_usuario ='" + usu.getRut_usuario() + "' ");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void modifica_duo_x_alta_adm(int duo, int estado, int prevision) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE   schema_uo.duo   SET  estado_duo = '" + estado + "', id_prevision = '" + prevision + "',  fecha_hora_alta_adm_duo = CURRENT_TIMESTAMP  WHERE id_duo = '" + duo + "';");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void ingresa_historial_consultorio_pertenencia(cHistorial_Consultorio his) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO  schema_uo.historial_consultorio_pertenencia  ( his_paciente_rut, his_consultorio_anterior, his_consultorio_nuevo,   his_fecha, his_usuario, his_ip) VALUES (   '" + his.getHis_paciente_rut() + "', '" + his.getHis_consultorio_anterior() + "', '" + his.getHis_consultorio_nuevo() + "',   CURRENT_TIMESTAMP, '" + his.getHis_usuario() + "', '" + his.getHis_ip() + "');");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void ingresa_hito_paciente(cHito hit) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("  INSERT INTO schema_uo.hito_paciente  ( hit_tipo,hit_fecha, hit_paciente_rut,   hit_detalle, hit_estado, hit_usuario,hit_ip ) VALUES (   '" + hit.getTipo() + "',CURRENT_TIMESTAMP, '" + hit.getRut_paciente() + "', '" + hit.getDetalle() + "',   '1', '" + hit.getUsuario_rut() + "', '" + hit.getIp() + "' );");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void ingresa_diagnostico_suam(cDiagnostico diag) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO  schema_suam.diagnostico ( das_id, dia_usuario,dia_ingreso,dia_estado,   dia_ip,dia_tipo, dia_detalle) VALUES (   '" + diag.getId_duo() + "', '" + diag.getRut_usuario() + "',   CURRENT_TIMESTAMP, '1',   '" + diag.getIp() + "','" + diag.getTipo_diagnostico() + "','" + diag.getDescripcion_diagnostico() + "'  );");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void elimina_diagnostico_suam(int id_diagnostico) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE  schema_suam.diagnostico   SET  dia_estado ='0'  WHERE  dia_id = '" + id_diagnostico + "' ");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void ingresa_observacion_suam(cDiagnostico diag) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO  schema_suam.observacion  ( das_id,obs_usuario,obs_ingreso,obs_estado,   obs_ip,obs_espera_radiografia,obs_espera_ex_laboratorio,obs_detalle  ) VALUES (    '" + diag.getId_duo() + "',   '" + diag.getRut_usuario() + "',   CURRENT_TIMESTAMP,   '1',   '" + diag.getIp() + "',   '" + diag.getEspera_radiografia() + "',   '" + diag.getEspera_ex_laboratorio() + "',   '" + diag.getDescripcion_diagnostico() + "');");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void elimina_observacion_suam(int id_observacion) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE  schema_suam.observacion   SET  obs_estado = '0'  WHERE  obs_id = '" + id_observacion + "'");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void ingresa_das(cDas das) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO  schema_suam.das  ( dau_id,dau_nn_id,das_paciente,das_estado,das_fecha_ingreso,   das_camilla,das_derivador,das_medico,das_usuario,das_ip  ) VALUES (    '" + das.getDau_id() + "',  '" + das.getDau_nn_id() + "',   '" + das.getRut_paciente() + "',  '1',   CURRENT_TIMESTAMP,  '" + das.getCamilla() + "',   '" + das.getDerivador() + "',  '" + das.getMedico() + "',   '" + das.getUsuario() + "',  '" + das.getIp() + "' );");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void modifica_camilla(int das_id, int camilla_id) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE  schema_suam.das   SET  das_camilla ='" + camilla_id + "' WHERE  das_id = '" + das_id + "';");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void ingresa_alta_das(cAlta_Das aux) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("  INSERT INTO  schema_suam.alta_das  ( das_id,alt_usuario,alt_ingreso,   alt_estado,alt_destino, alt_detalle, alt_ip,alt_medico  ) VALUES (   '" + aux.getId_das() + "',   '" + aux.getRut_usuario() + "',   CURRENT_TIMESTAMP,   '1',   '" + aux.getDestino() + "',   '" + aux.getDetalle() + "',   '" + aux.getIp() + "','" + aux.getMedico_rut() + "'  );");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void modifica_das_x_indicacion_alta(cDas aux) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE  schema_suam.das  SET   das_estado = '2',  das_indicacion_destino = '" + aux.getIndicacion_destino_id() + "',  das_indicacion_usuario = '" + aux.getIndicacion_usuario_rut() + "',  das_indicacion_fecha_ingreso = CURRENT_TIMESTAMP,  das_indicacion_ip = '" + aux.getIndicacion_ip() + "'  WHERE  das_id = '" + aux.getId_das() + "' ");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public void modifica_estado_das(int id_das, int nuevo_estado) {
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE  schema_suam.das  SET   das_estado = '" + nuevo_estado + "'  WHERE  das_id = '" + id_das + "' ");
        this.cnn.conectar();
        this.cnn.cerrarConexion();
    }

    public boolean obtiene_ip_prohibida(String ip) {
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select ip_descripcion from schema_uo.ip_prohibida  where ip_descripcion='" + ip + "' and ip_estado='1' limit 1");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                sw = true;
            } else {
                sw = false;
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return sw;
    }

    public boolean elimina_epicrisis_segun_duo(int id_duo, String usuario) {
        boolean sw = false;
        this.configurarConexion("");

        try {
            this.cnn.setEsSelect(false);
            this.cnn.setSentenciaSQL("INSERT INTO  schema_uo.epicrisis_anulada  (id_epicrisis,resumen_epicrisis,examen_epicrisis,diagnostico_epicrisis,   indicacion_epicrisis,fecha_epicrisis,hora_epicrisis,rut_usuario,   id_duo,fecha_hora_epicrisis,ip_epicrisis,usuario_responsable )   SELECT  id_epicrisis,resumen_epicrisis,examen_epicrisis,   diagnostico_epicrisis,indicacion_epicrisis,fecha_epicrisis,   hora_epicrisis,rut_usuario,id_duo,   fecha_hora_epicrisis,ip_epicrisis,'" + usuario + "'  FROM  schema_uo.epicrisis WHERE id_duo='" + id_duo + "';");
            this.cnn.conectar();
            this.cnn.cerrarConexion();
            this.configurarConexion("");
            this.cnn.setEsSelect(false);
            this.cnn.setSentenciaSQL("DELETE FROM  schema_uo.epicrisis  EP   WHERE   EP.id_duo='" + id_duo + "' ");
            this.cnn.conectar();
            this.cnn.cerrarConexion();
            this.cnn.setEsSelect(false);
            this.cnn.setSentenciaSQL("UPDATE  schema_uo.duo  SET  estado_duo = '21',  fecha_hora_alta_med_duo = null WHERE  id_duo = '" + id_duo + "' ;");
            this.cnn.conectar();
            this.cnn.cerrarConexion();
            sw = true;
        } catch (Exception var8) {
            sw = false;
        } finally {
            this.cnn.cerrarConexion();
        }

        return sw;
    }

    public boolean elimina_ingreso_enfermeria_duo(int id_duo, String usuario) {
        boolean sw = false;
        this.configurarConexion("");

        try {
            this.cnn.setEsSelect(false);
            this.cnn.setSentenciaSQL("INSERT INTO   schema_uo.ing_enfermeria_anulada( id_ing_enfermeria, fecha_hora_ing_enfermeria,  otro_comorbilidad_ing_enfermeria, farmaco_ing_enfermeria,  obs_ing_enfermeria, rut_usuario_ing_enfermeria,  id_examen_fisico_ing_enfermeria, id_duo_ing_enfermeria,  otro_ex_docto_ing_enfermeria,usuario_responsable)  (SELECT id_ing_enfermeria, fecha_hora_ing_enfermeria,  otro_comorbilidad_ing_enfermeria, farmaco_ing_enfermeria,  obs_ing_enfermeria,rut_usuario_ing_enfermeria,  id_examen_fisico_ing_enfermeria, id_duo_ing_enfermeria,  otro_ex_docto_ing_enfermeria, '" + usuario + "' FROM  schema_uo.ing_enfermeria where id_duo_ing_enfermeria='" + id_duo + "');");
            this.cnn.conectar();
            this.cnn.setSentenciaSQL("UPDATE  schema_uo.duo   SET   duo_tiene_enfermeria = '0', ip_ing_enf = ''  WHERE  id_duo = '999999999'");
            this.cnn.conectar();
        } catch (Exception var8) {
            sw = false;
        } finally {
            this.cnn.cerrarConexion();
        }

        return sw;
    }

    public boolean ingresa_examen_radiografia(cExamen exa) {
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO  schema_suam.examen_radio_das  ( er_das_id,er_examen,er_usuario, er_fecha_ingreso, er_estado  ) VALUES ('" + exa.getId_das() + "','" + exa.getId_examen() + "',    '" + exa.getRut_usuario() + "',CURRENT_TIMESTAMP,'1');");

        try {
            this.cnn.conectar();
            sw = true;
        } catch (Exception var4) {
            sw = false;
        }

        this.cnn.cerrarConexion();
        return sw;
    }

    public boolean elimina_examen_radiografia(int id) {
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE schema_suam.examen_radio_das   SET  er_estado = '0'  WHERE  er_id = '" + id + "' ");

        try {
            this.cnn.conectar();
            sw = true;
        } catch (Exception var4) {
            sw = false;
        }

        return sw;
    }

    public boolean ingresa_sesion_kine(cSesionKine ses) {
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO   schema_uo.kin_sesion ( ses_estado, ses_usuario,\n  ses_fecha_ingreso, ses_fecha_hora, ses_detalle,ses_duo ) \nVALUES ( '1', '" + ses.getRut_usuario() + "',\n  CURRENT_TIMESTAMP, '" + ses.getFecha_hora() + "', '" + ses.getDetalle() + "', '" + ses.getId_duo() + "' );");

        try {
            this.cnn.conectar();
            sw = true;
        } catch (Exception var7) {
            sw = false;
        } finally {
            this.cnn.cerrarConexion();
        }

        return sw;
    }

    public boolean ingresa_sesion_fono(cSesionKine ses) {
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO   schema_uo.fonouriologa_sesion ( ses_estado, ses_usuario,\n  ses_fecha_ingreso, ses_fecha_hora, ses_detalle,ses_duo ) \nVALUES ( '1', '" + ses.getRut_usuario() + "',\n  CURRENT_TIMESTAMP, '" + ses.getFecha_hora() + "', '" + ses.getDetalle() + "', '" + ses.getId_duo() + "' );");

        try {
            this.cnn.conectar();
            sw = true;
        } catch (Exception var7) {
            sw = false;
        } finally {
            this.cnn.cerrarConexion();
        }

        return sw;
    }

    public boolean ingresa_sesion_nutri(cSesionKine ses) {
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO   schema_uo.nutricionista_sesion ( ses_estado, ses_usuario,\n  ses_fecha_ingreso, ses_fecha_hora, ses_detalle,ses_duo ) \nVALUES ( '1', '" + ses.getRut_usuario() + "',\n  CURRENT_TIMESTAMP, '" + ses.getFecha_hora() + "', '" + ses.getDetalle() + "', '" + ses.getId_duo() + "' );");

        try {
            this.cnn.conectar();
            sw = true;
        } catch (Exception var7) {
            sw = false;
        } finally {
            this.cnn.cerrarConexion();
        }

        return sw;
    }

    public boolean ingresa_sesion_terapeuta(cSesionKine ses) {
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO   schema_uo.terapeuta_sesion ( ses_estado, ses_usuario,\n  ses_fecha_ingreso, ses_fecha_hora, ses_detalle,ses_duo ) \nVALUES ( '1', '" + ses.getRut_usuario() + "',\n  CURRENT_TIMESTAMP, '" + ses.getFecha_hora() + "', '" + ses.getDetalle() + "', '" + ses.getId_duo() + "' );");

        try {
            this.cnn.conectar();
            sw = true;
        } catch (Exception var7) {
            sw = false;
        } finally {
            this.cnn.cerrarConexion();
        }

        return sw;
    }

    public boolean elimina_sesion_kine(int id_sesionKine, String rut_usuario, String motivo) {
        boolean sw = false;
        return sw;
    }

    public boolean ingresa_evaluacion_traumatologica(cEvaTraumatologia tra) {
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO   schema_uo.kin_eva_traumatologia  \n(tra_id_duo,tra_estado, tra_usuario,tra_fecha_ingreso,  \n  tra_observacion_inicial, tra_historial, tra_dolor,  \n  tra_prueba_especial, tra_palpacion, tra_marcha,  \n  tra_plano_frontal, tra_plano_sagital, tra_plano_posterior,  \n  tra_movimiento_pasivo,tra_movimiento_activo,tra_observacion,  \n  tra_dermatoma, tra_miotoma, tra_reflejo_osteotendineo,  \n  tra_test_neurodinamico, tra_diagnostico_imagen, tra_diagnostico_kinesico  \n)  VALUES ( '" + tra.getId_duo() + "', '1', '" + tra.getRut_usuario() + "',CURRENT_TIMESTAMP,  \n  '" + tra.getObservacion_inicial() + "','" + tra.getHistorial_usuario() + "','" + tra.getDolor() + "',  \n  '" + tra.getPrueba_especial() + "', '" + tra.getPalpacion() + "', '" + tra.getMarcha() + "',  \n  '" + tra.getPlano_frontal() + "', '" + tra.getPlano_sagital() + "', '" + tra.getPlano_posterior() + "',  \n  '" + tra.getMovimiento_pasivo() + "','" + tra.getMovimiento_activo() + "','" + tra.getObservacion() + "',  \n  '" + tra.getDermatoma() + "', '" + tra.getMiotoma() + "', '" + tra.getReflejo_osteotendineo() + "',  \n  '" + tra.getTest_neurodinamico() + "', '" + tra.getDiagnostico_imagen() + "','" + tra.getDiagnostico_kinesico() + "');");

        try {
            this.cnn.conectar();
            sw = true;
        } catch (Exception var7) {
            sw = false;
        } finally {
            this.cnn.cerrarConexion();
        }

        return sw;
    }

    public boolean elimina_evaluacion_traumatologica(int id_evaTrauma, String rut_usuario, String motivo) {
        boolean sw = false;
        return sw;
    }

    public boolean ingresa_contacto(cContacto con) {
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO  schema_suam.contacto "
                + " ( das_id, con_usuario, con_ingreso, "
                + "  con_estado, con_ip, con_nombre, con_fecha, "
                + "  con_hora, con_observacion )  VALUES ( "
                + "  '" + con.getDas_id() + "',  '" + con.getRut_usuario() + "', "
                + "  CURRENT_TIMESTAMP, '" + con.getEstado() + "', "
                + "  '" + con.getIp() + "', '" + con.getNombre() + "', "
                + "  '" + con.getFecha() + "', '" + con.getHora() + "', '" + con.getObservacion() + "');");
        try {
            this.cnn.conectar();
            sw = true;
        } catch (Exception ex) {
            sw = false;
        }

        this.cnn.cerrarConexion();
        return sw;
    }

    public boolean ingresa_registro_social(cRegistroSocial reg) {
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO  schema_uo.registro_social\n( reg_usuario, reg_fecha_ingresa, reg_estado, reg_estado_civil,\n  reg_situacion_laboral, reg_institucionalizado, reg_nombre_institucion,\n  reg_vive,reg_hijos, reg_hijos_cantidad,\n  reg_situacion, reg_plan,  reg_duo,     reg_hospital_origen, reg_asistente_social,reg_fecha_egreso, reg_destino,reg_diagnostico )  VALUES (\n  '" + reg.getRut_usuario() + "', CURRENT_TIMESTAMP, '1', '" + reg.getEstado_civil() + "',\n  '" + reg.getSituacion_laboral() + "', '" + reg.getInstitucionalizado() + "', '" + reg.getInstitucion_nombre() + "',\n  '" + reg.getVive() + "','" + reg.getHijos() + "', '" + reg.getHijos_cantidad() + "',\n  '" + reg.getSituacion() + "', '" + reg.getPlan() + "', '" + reg.getId_duo() + "',  '" + reg.getHospital_origen_desc() + "','" + reg.getNombre_asistente() + "','" + reg.getFecha_egreso() + "','" + reg.getDestino() + "','" + reg.getDiagnostico() + "' ); ");

        try {
            this.cnn.conectar();
            sw = true;
        } catch (Exception var7) {
            sw = false;
        } finally {
            this.cnn.cerrarConexion();
        }

        return sw;
    }

    public boolean modifica_registro_social(cRegistroSocial reg) {
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL(" UPDATE  schema_uo.registro_social  SET  reg_usuario = '" + reg.getRut_usuario() + "',  reg_estado_civil = '" + reg.getEstado_civil() + "',  reg_situacion_laboral = '" + reg.getSituacion_laboral() + "',  reg_institucionalizado = '" + reg.getInstitucionalizado() + "',  reg_nombre_institucion = '" + reg.getInstitucion_nombre() + "',  reg_vive = '" + reg.getVive() + "',  reg_hijos = '" + reg.getHijos() + "',  reg_hijos_cantidad = '" + reg.getHijos_cantidad() + "',  reg_situacion = '" + reg.getSituacion() + "',  reg_plan =  '" + reg.getPlan() + "',   reg_hospital_origen = '" + reg.getHospital_origen_desc() + "',  reg_asistente_social = '" + reg.getNombre_asistente() + "',  reg_fecha_egreso = '" + reg.getFecha_egreso() + "',  reg_destino = '" + reg.getDestino() + "' ,   reg_diagnostico = '" + reg.getDiagnostico() + "'   WHERE  reg_id = '" + reg.getId_registro() + "' ;");

        try {
            this.cnn.conectar();
            sw = true;
        } catch (Exception var7) {
            sw = false;
        } finally {
            this.cnn.cerrarConexion();
        }

        return sw;
    }

    public boolean ingresa_registro_seguimiento(cRegistroSeguimiento seg) {
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO schema_uo.registro_social_seguimiento\n(  seg_usuario, seg_duo_id,seg_fecha_ingreso,\n  seg_fecha, seg_descripcion, seg_estado\n)  VALUES (  '" + seg.getRut_usuario() + "', '" + seg.getId_registro_id() + "', CURRENT_TIMESTAMP,\n  '" + seg.getFecha_seguimiento() + "','" + seg.getDescripcion() + "','1' );");

        try {
            this.cnn.conectar();
            sw = true;
        } catch (Exception var7) {
            sw = false;
        } finally {
            this.cnn.cerrarConexion();
        }

        return sw;
    }

    public boolean anula_registro_seguimiento(int id_seguimiento) {
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("UPDATE  schema_uo.registro_social_seguimiento  \nSET seg_estado = '0' WHERE  seg_id = '" + id_seguimiento + "' ");

        try {
            this.cnn.conectar();
            sw = true;
        } catch (Exception var7) {
            sw = false;
        } finally {
            this.cnn.cerrarConexion();
        }

        return sw;
    }

    public boolean ingresa_evaluacion_neurologica(cEvaNeurologia neu) {
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        if (neu.getLesion_evaluada() == 1) {
            this.cnn.setSentenciaSQL("INSERT INTO \n  schema_uo.kin_eva_neurologia\n( neu_id_duo,neu_estado, neu_usuario,\n  neu_fecha_ingreso, neu_lesion_evaluada, neu_lesion, neu_ashworth,neu_tipo_lesion,\n  neu_dato1,neu_dato2, neu_dato3,neu_dato4, \n  neu_dato5, neu_dato6, neu_dato7, neu_dato8, \n  neu_dato9, neu_dato10,neu_dato11,\n  neu_opcion1, neu_opcion2, neu_opcion3,\n  neu_opcion4, neu_opcion5, neu_opcion6,\n  neu_opcion1_desc, neu_opcion2_desc, neu_opcion3_desc,\n  neu_opcion4_desc, neu_opcion5_desc, neu_opcion6_desc\n)  VALUES (\n  '" + neu.getId_duo() + "', '1', '" + neu.getRut_usuario() + "',  CURRENT_TIMESTAMP,  '" + neu.getLesion_evaluada() + "', '" + neu.getLesion() + "', '" + neu.getAshworth() + "', '" + neu.getLesion_tipo() + "',\n   '" + neu.getReflejo_osteorendineo_desc() + "', '" + neu.getAsia() + "',  '" + neu.getEvaluacion_sensitiva() + "', '" + neu.getSensibilidad() + "',\n  '" + neu.getMotor_index() + "', '" + neu.getContraccion() + "', '" + neu.getSilla_ruedas() + "', '" + neu.getNivel_sentivo() + "',\n   '" + neu.getNivel_motor() + "', '" + neu.getNivel_neurologico() + "', '" + neu.getMarcha() + "',\n  '" + neu.getOp1() + "', '" + neu.getOp2() + "', '" + neu.getOp3() + "',\n  '" + neu.getOp4() + "','" + neu.getOp5() + "','" + neu.getOp6() + "',\n  '" + neu.getOp1_desc() + "','" + neu.getOp2_desc() + "','" + neu.getOp3_desc() + "',\n  '" + neu.getOp4_desc() + "','" + neu.getOp5_desc() + "', '" + neu.getOp6_desc() + "' );");
        } else {
            this.cnn.setSentenciaSQL("INSERT INTO \n  schema_uo.kin_eva_neurologia\n( neu_id_duo,neu_estado, neu_usuario, neu_fecha_ingreso, neu_lesion_evaluada, neu_lesion, neu_ashworth,\n  neu_dato1,neu_dato2, neu_dato3,neu_dato4, \n  neu_dato5, neu_dato6, neu_dato7, neu_dato8, \n  neu_dato9, neu_dato10,neu_dato11,\n  neu_trofismo, neu_reflejo_osteorendineo, neu_propiocepcion,\n  neu_reaccion_equilibrio,neu_reaccion_enderezamiento, neu_reaccion_apoyo,\n  neu_opcion1, neu_opcion2, neu_opcion3,\n  neu_opcion4, neu_opcion5, neu_opcion6,\n  neu_opcion1_desc, neu_opcion2_desc, neu_opcion3_desc,\n  neu_opcion4_desc, neu_opcion5_desc, neu_opcion6_desc\n) VALUES (  '" + neu.getId_duo() + "', '1', '" + neu.getRut_usuario() + "',  CURRENT_TIMESTAMP,  '" + neu.getLesion_evaluada() + "', '" + neu.getLesion() + "', '" + neu.getAshworth() + "',\n  '" + neu.getMotoneurona() + "', '" + neu.getExtrapiramiral() + "', '" + neu.getPostura() + "', '" + neu.getFuerza() + "',\n  '" + neu.getTono_muscular() + "', '" + neu.getTrofismo_adicional() + "', '" + neu.getEess() + "', '" + neu.getEeii() + "',\n  '" + neu.getPropiocepcion_adicional() + "','" + neu.getTransicion() + "', '" + neu.getMarcha() + "',\n  '" + neu.getTrofismo() + "', '" + neu.getReflejo_osteorendineo() + "','" + neu.getPropiocepcion() + "',\n '" + neu.getReaccion_equilibrio() + "','" + neu.getReaccion_enderezamiento() + "', '" + neu.getReaccion_apoyo() + "',\n  '" + neu.getOp1() + "', '" + neu.getOp2() + "', '" + neu.getOp3() + "',\n  '" + neu.getOp4() + "','" + neu.getOp5() + "','" + neu.getOp6() + "',\n  '" + neu.getOp1_desc() + "','" + neu.getOp2_desc() + "','" + neu.getOp3_desc() + "',\n  '" + neu.getOp4_desc() + "','" + neu.getOp5_desc() + "', '" + neu.getOp6_desc() + "' );");
        }

        try {
            this.cnn.conectar();
            sw = true;
        } catch (Exception var7) {
            sw = false;
        } finally {
            this.cnn.cerrarConexion();
        }

        return sw;
    }

    public boolean ingresa_evaluacion_respiratoria(cEvaRespiratoria res) {
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("INSERT INTO  schema_uo.kin_eva_respiratoria   ( res_estado,res_usuario, res_fecha_ingreso, res_duo,\n  res_oxigeno, res_oxigeno_det, res_via_venosa,res_via_urinaria,\n  res_via_urinaria_det,  res_vigil,  res_estado_conciencia,\n  res_alteracion_lenguaje,res_estimulo_verbal, res_estimulo_visual, res_ubicacion_temporal, res_ubicacion_espacial,\n  res_postura, res_eess,res_eeii,\n  res_deformidad,res_estado_nutricional,res_coloracion_piel,res_cianosis,\n  res_ep_normal,res_ep_escara,res_ep_cicatriz,res_ep_hematoma,res_ep_petequia,res_ep_edema,\n  res_ep_normal_det, res_ep_escara_det, res_ep_cicatriz_det, res_ep_hematoma_det, res_ep_petequia_det,res_ep_edema_det,\n  res_patron_respiratorio,res_respirador,\n  res_dificultad_respiratoria,res_musculatura_accesoria,res_musculatura_accesoria_det,\n  res_aleteo_nasal,res_aleteo_costal, res_temperatura,\n  res_elasticidad,res_elasticidad_det, res_restriccion_miofascial,\n  res_llene_capilar, res_edema,res_dolor,res_eva,\n  res_desalineacion, res_frecuencia_cardiaca, res_frecuencia_respiratoria, res_saturacion,\n  res_aus_pi_ls,res_aus_pi_li,res_aus_pd_ls, res_aus_pd_lm, res_aus_pd_li,\n  res_sibilancia, res_roncus, res_estertor_traqueal, res_crepitacion_fina,res_crepicitacion_gruesa,\n  res_ra_pi_ls,res_ra_pi_li,res_ra_pd_ls, res_ra_pd_lm,res_ra_pd_li,\n  res_tos_presencia, res_tos_produccion,\n  res_secrecion_cantidad, res_secrecion_coloracion,res_secrecion_tipo )    VALUES ( '1','" + res.getRut_usuario() + "',CURRENT_TIMESTAMP,'" + res.getId_duo() + "' ,\n  '" + res.getOxigeno() + "', '" + res.getOxigeno_det() + "', '" + res.getVia_venosa() + "', '" + res.getVia_urinaria() + "', '" + res.getVia_urinaria_det() + "', '" + res.getVigil() + "', '" + res.getEstado_conciencia() + "',\n  '" + res.getAlteracion_lenguaje() + "','" + res.getEstimulo_verbal() + "','" + res.getEstimulo_visual() + "', '" + res.getUbicacion_temporal() + "', '" + res.getUbicacion_espacial() + "',\n  '" + res.getPostura() + "','" + res.getEESS() + "','" + res.getEEII() + "',\n  '" + res.getDeformidad() + "','" + res.getEstado_nutricional() + "','" + res.getColoracion_piel() + "', " + res.getCianosis() + ",\n '" + res.getEp_normal() + "','" + res.getEp_escara() + "','" + res.getEp_cicatriz() + "','" + res.getEp_hematoma() + "','" + res.getEp_petequia() + "','" + res.getEp_edema() + "',\n  '" + res.getEp_normal_det() + "', '" + res.getEp_escara_det() + "', '" + res.getEp_cicatriz_det() + "', '" + res.getEp_hematoma_det() + "','" + res.getEp_petequia_det() + "','" + res.getEp_edema_det() + "',\n  '" + res.getPatron_respiratorio() + "','" + res.getRespirador() + "',\n  '" + res.getDificultad_respiratoria() + "','" + res.getMusculatura_accesoria() + "','" + res.getMusculatura_accesoria_det() + "',\n  '" + res.getAleteo_nasal() + "','" + res.getAleteo_costal() + "', '" + res.getTemperatura() + "',\n  '" + res.getElasticidad() + "','" + res.getElasticidad_det() + "','" + res.getRestriccion_miofascial() + "',\n  '" + res.getLlene_capilar() + "', '" + res.getEdema() + "','" + res.getDolor() + "','" + res.getEVA() + "',\n  '" + res.getDesalineacion() + "', '" + res.getFrecuencia_cardiaca() + "', '" + res.getFrecuencia_respiratoria() + "', '" + res.getSaturacion() + "',\n  '" + res.getAus_pi_ls() + "','" + res.getAus_pi_li() + "','" + res.getAus_pd_ls() + "', '" + res.getAus_pd_lm() + "', '" + res.getAus_pd_li() + "',\n  '" + res.getSibilancia() + "','" + res.getRoncus() + "', '" + res.getEstertor_traqueal() + "', '" + res.getCrepitacion_fina() + "','" + res.getCrepitacion_gruesa() + "',\n  '" + res.getRa_pi_ls() + "','" + res.getRa_pi_li() + "','" + res.getRa_pd_ls() + "','" + res.getRa_pd_lm() + "','" + res.getRa_pd_li() + "',\n  '" + res.getTos_presencia() + "', '" + res.getTos_produccion() + "',\n'" + res.getSecrecion_cantidad() + "','" + res.getSecrecion_coloracion() + "','" + res.getSecrecion_tipo() + "');");

        try {
            this.cnn.conectar();
            sw = true;
        } catch (Exception var7) {
            sw = false;
        } finally {
            this.cnn.cerrarConexion();
        }

        return sw;
    }

    public int ingresa_receta(cReceta rec) {
        int id = 0;
        boolean sw = true;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL("   INSERT INTO  schema_uo.receta ( rec_tipo, rec_fecha,rec_usuario,   rec_fecha_ingreso, rec_estado, rec_ip,   rec_dia_hospitalizacion,\n  rec_diagnostico,rec_regimen, rec_reposo, rec_kinesiologia,\n  rec_aislamiento,rec_contencion, rec_dispositivo,\n  rec_hgt )  VALUES ( '" + rec.getTipo() + "', '" + rec.getFecha() + "', '" + rec.getRut_usuario() + "',   CURRENT_TIMESTAMP,'1', '" + rec.getIp() + "','" + rec.getDia_hospitalizacion() + "',  '" + rec.getDiagnostico() + "','" + rec.getRegimen() + "','" + rec.getReposo() + "', '" + rec.getKinesiologia() + "','" + rec.getAislamiento() + "','" + rec.getContencion() + "','" + rec.getDispositivo() + "' ,'" + rec.getHgt() + "'); ");

        try {
            this.cnn.conectar();
        } catch (Exception var17) {
            id = 0;
            sw = false;
        } finally {
            this.cnn.cerrarConexion();
        }

        if (sw) {
            this.configurarConexion("");
            this.cnn.setEsSelect(true);
            this.cnn.setSentenciaSQL(" SELECT  rec_id  FROM  schema_uo.receta     WHERE rec_usuario = '" + rec.getRut_usuario() + "' AND   rec_fecha='" + rec.getFecha() + "' and rec_ip='" + rec.getIp() + "'   order by rec_id DESC limit 1 ; ");
            this.cnn.conectar();

            try {
                if (this.cnn.getRst().next()) {
                    id = this.cnn.getRst().getInt("rec_id");
                }
            } catch (SQLException var15) {
                Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var15);
            } finally {
                this.cnn.cerrarConexion();
            }
        }

        return id;
    }

    public boolean ingresa_receta_detalle(cReceta rec) {
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(false);
        this.cnn.setSentenciaSQL(" INSERT INTO  schema_uo.receta_detalle \n (rec_usuario,rec_fecha_ingreso, rec_fecha,\n  rec_tipo, rec_medicamento, rec_medida,\n  rec_cantidad, rec_frecuencia, rec_duracion,\n  rec_indicacion,rec_indicacion_no,rec_estado, rec_duo, rec_duracion_desc,rec_paciente,rec_receta  )  VALUES (\n  '" + rec.getRut_usuario() + "',CURRENT_TIMESTAMP,  '" + rec.getFecha() + "',\n   '" + rec.getTipo() + "',  '" + rec.getMedicamento_desc() + "',  '" + rec.getMedida() + "',\n   '" + rec.getCantidad() + "',  '" + rec.getFrecuencia() + "', '0',    '" + rec.getIndicacion() + "','" + rec.getIndicacion_no() + "', '1' ,'" + rec.getId_duo() + "','" + rec.getDuracion_desc() + "','" + rec.getRut_paciente() + "','" + rec.getId_receta() + "' ); ");

        try {
            this.cnn.conectar();
            sw = true;
        } catch (Exception var7) {
            sw = false;
        } finally {
            this.cnn.cerrarConexion();
        }

        return sw;
    }
}
