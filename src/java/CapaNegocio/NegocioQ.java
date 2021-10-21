//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package CapaNegocio;

import CapaDato.DuoDetailBarthel;
import CapaDato.DuoIndexBarthel;
import CapaDato.cAlta_Das;
import CapaDato.cCama;
import CapaDato.cCatRiesgoDependencia;
import CapaDato.cCategorizacion;
import CapaDato.cComuna;
import CapaDato.cConsultorio;
import CapaDato.cContacto;
import CapaDato.cDas;
import CapaDato.cDau;
import CapaDato.cDiagnostico;
import CapaDato.cDocumento;
import CapaDato.cDuo;
import CapaDato.cEnfermedad;
import CapaDato.cEpicrisis;
import CapaDato.cEvaNeurologia;
import CapaDato.cEvaRespiratoria;
import CapaDato.cEvaTraumatologia;
import CapaDato.cExamen;
import CapaDato.cHistorial_Consultorio;
import CapaDato.cHito;
import CapaDato.cIngresoEnfermeria;
import CapaDato.cIngreso_Medico;
import CapaDato.cInsumo;
import CapaDato.cNacion;
import CapaDato.cObservacion;
import CapaDato.cPaciente;
import CapaDato.cPerfil;
import CapaDato.cPueblo;
import CapaDato.cReceta;
import CapaDato.cRegistroSeguimiento;
import CapaDato.cRegistroSocial;
import CapaDato.cSesionKine;
import CapaDato.cUnidadMedida;
import CapaDato.cUsuario;
import CapaDato.cVisita;
import CapaDato.cVistaUoce;
import CapaDato.prevision;
import Datos.AmbitoCRD;
import Datos.GradoCRD;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NegocioQ extends Negocio {

    public NegocioQ() {
    }

    /*new code report*/
    public String primeraMayuscula(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            //La primera letra en mayuscula y las demas en minuscula.
            return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        }
    }

    public DuoIndexBarthel getDuoIndexBarthel(int id) {
        DuoIndexBarthel duoIndexBarthel = new DuoIndexBarthel();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT\n"
                + "                p.nombre ||' ' || p.apellido_paterno ||' ' || p.apellido_moderno as nameP,\n"
                + "                p.rut as paciente_rut,\n"
                + "                to_char(dib.date_registers , 'DD-MM-YYYY') as dateRgister,\n"
                + "                dib.degree_of_dependency,\n"
                + "                case when dib.type_registers = 1 then 'Ingreso' else 'Egreso' end  as typeRegister,\n"
                + "                dib.total_puntuction,\n"
                + "                u.nombre_usuario ||' ' || u.apellidop_usuario ||' ' || u.apellidom_usuario as userRegister\n"
                + "                FROM\n"
                + "                  schema_uo.duo_index_barthel dib inner join schema_uo.duo d on dib.id_duo= d.id_duo\n"
                + "                  inner join agenda.paciente p on p.rut= d.rut_paciente\n"
                + "                  inner join schema_uo.usuario u on dib.user_registers = u.rut_usuario where dib.id = " + id + ";");
        this.cnn.conectar();
        try {
            if (cnn.getRst().next()) {

                duoIndexBarthel.setNamePaciente(cnn.getRst().getString("nameP"));
                duoIndexBarthel.setRutPaciente(cnn.getRst().getString("paciente_rut"));
                duoIndexBarthel.setDateString(cnn.getRst().getString("dateRgister"));
                duoIndexBarthel.setTotalPuntuction(cnn.getRst().getInt("total_puntuction"));
                duoIndexBarthel.setTypeString(cnn.getRst().getString("typeRegister"));
                duoIndexBarthel.setDegreeOfDependency(cnn.getRst().getString("degree_of_dependency"));
                duoIndexBarthel.setUserRegisters(cnn.getRst().getString("userRegister"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();
        return duoIndexBarthel;

    }

    public Vector<cDuo> listadelDiasector1() {
        Vector<cDuo> vi = new Vector<cDuo>();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT c.descripcion_cama, COALESCE( D.rut_paciente, '') as rut_paciente,COALESCE(lower(apellido_paterno),'') as  paciente_apellidop ,\n"
                + "   COALESCE(lower(apellido_moderno ),'')as paciente_apellidom,COALESCE( lower(nombre),'') as paciente_nombres,\n"
                + "   COALESCE(to_char(D.fecha_duo,'DD/MM/YYYY'),'') as fecha_duo ,COALESCE(D.hora_duo,'00:00 ') as hora_duo, \n"
                + "    EXTRACT(DAY FROM (CURRENT_DATE+CURRENT_TIME)-(d.fecha_duo+d.hora_duo))+1 as dias_cama ,\n"
                + "    COALESCE( to_char (age(CURRENT_TIMESTAMP, fecha_nacimiento),'yy'),'') as edad ,\n"
                + "    COALESCE((SELECT BB.cat_visita_categorizacion \n"
                + "     FROM schema_uo.visita AA  \n"
                + "    JOIN schema_uo.visita_categorizacion BB ON \n"
                + "     (AA.id_visita_categorizacion=BB.id_visita_categorizacion) \n"
                + "     where id_duo=D.id_duo order by AA.fecha_hora_visita DESC limit 1),'') as ultima_cat\n"
                + "     FROM schema_uo.cama C left JOIN schema_uo.duo D ON D.id_cama = C.id_cama \n"
                + "      and D.estado_duo in (2,1, 3, 21)\n"
                + "      left JOIN schema_uo.sala   \n"
                + "       ON (schema_uo.sala.id_sala=C.id_sala) \n"
                + "       left JOIN agenda.paciente  \n"
                + "       ON (agenda.paciente.rut=D.rut_paciente) \n"
                + "       where C.id_sala in (11) and C.estado_cama=1 order by schema_uo.sala.posicion ");
        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                cDuo duo = new cDuo();
                duo.setRut_paciente(cnn.getRst().getString("rut_paciente"));
                duo.setNombres_paciente(cnn.getRst().getString("paciente_nombres"));
                duo.setApellidop_paciente(cnn.getRst().getString("paciente_apellidop"));
                duo.setApellidom_paciente(cnn.getRst().getString("paciente_apellidom"));
                duo.setFecha_creacion(cnn.getRst().getString("fecha_duo"));
                duo.setHora_duo(cnn.getRst().getString("hora_duo"));
                duo.setCama_descripcion(cnn.getRst().getString("descripcion_cama"));
                duo.setDias_cama(cnn.getRst().getInt("dias_cama"));
                duo.setEdad(cnn.getRst().getString("edad"));
                duo.setCategorizacion_descripcion(cnn.getRst().getString("ultima_cat"));

                vi.add(duo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();
        return vi;
    }

    public Vector<cDuo> listadelDiasector2() {
        Vector<cDuo> vi = new Vector<cDuo>();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("  SELECT c.descripcion_cama, COALESCE( D.rut_paciente, '') as rut_paciente,COALESCE(lower(apellido_paterno),'') as  paciente_apellidop ,\n"
                + "        COALESCE(lower(apellido_moderno),'')as paciente_apellidom,COALESCE( lower(nombre),'') as paciente_nombres,\n"
                + "         COALESCE(to_char(D.fecha_duo,'DD/MM/YYYY'),'') as fecha_duo ,COALESCE(D.hora_duo,'00:00 ') as hora_duo,\n"
                + "           EXTRACT(DAY FROM (CURRENT_DATE+CURRENT_TIME)-(d.fecha_duo+d.hora_duo))+1 as dias_cama,COALESCE(to_char (age(CURRENT_TIMESTAMP, fecha_nacimiento),'yy'),'') as edad ,\n"
                + "           COALESCE((SELECT BB.cat_visita_categorizacion\n"
                + "           FROM schema_uo.visita AA \n"
                + "           JOIN schema_uo.visita_categorizacion BB ON\n"
                + "           (AA.id_visita_categorizacion=BB.id_visita_categorizacion)\n"
                + "          where id_duo=D.id_duo order by AA.fecha_hora_visita DESC limit 1),'') as ultima_cat\n"
                + "             FROM schema_uo.cama C left JOIN schema_uo.duo D ON D.id_cama = C.id_cama\n"
                + "              and D.estado_duo in (2,1, 3, 21)\n"
                + "                left JOIN schema_uo.sala  \n"
                + "                 ON (schema_uo.sala.id_sala=C.id_sala)\n"
                + "                left JOIN agenda.paciente \n"
                + "                     ON (agenda.paciente.rut=D.rut_paciente)\n"
                + "                    where C.id_sala in (12) and C.estado_cama=1 order by schema_uo.sala.posicion");
        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                cDuo duo = new cDuo();
                duo.setRut_paciente(cnn.getRst().getString("rut_paciente"));
                duo.setNombres_paciente(cnn.getRst().getString("paciente_nombres"));
                duo.setApellidop_paciente(cnn.getRst().getString("paciente_apellidop"));
                duo.setApellidom_paciente(cnn.getRst().getString("paciente_apellidom"));
                duo.setFecha_creacion(cnn.getRst().getString("fecha_duo"));
                duo.setHora_duo(cnn.getRst().getString("hora_duo"));
                duo.setCama_descripcion(cnn.getRst().getString("descripcion_cama"));
                duo.setDias_cama(cnn.getRst().getInt("dias_cama"));
                duo.setEdad(cnn.getRst().getString("edad"));
                duo.setCategorizacion_descripcion(cnn.getRst().getString("ultima_cat"));
                vi.add(duo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();
        return vi;
    }

    public Vector<cDuo> listadelDiasector3() {
        Vector<cDuo> vi = new Vector<cDuo>();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL(" SELECT c.descripcion_cama, COALESCE( D.rut_paciente, '') as rut_paciente,COALESCE(lower(apellido_paterno),'') as  paciente_apellidop ,\n"
                + "        COALESCE(lower(apellido_moderno),'')as paciente_apellidom,COALESCE( lower(nombre),'') as paciente_nombres,\n"
                + "         COALESCE(to_char(D.fecha_duo,'DD/MM/YYYY'),'') as fecha_duo ,COALESCE(D.hora_duo,'00:00 ') as hora_duo,\n"
                + "           EXTRACT(DAY FROM (CURRENT_DATE+CURRENT_TIME)-(d.fecha_duo+d.hora_duo))+1 as dias_cama,COALESCE(to_char (age(CURRENT_TIMESTAMP, fecha_nacimiento),'yy'),'') as edad ,\n"
                + "           COALESCE((SELECT BB.cat_visita_categorizacion\n"
                + "           FROM schema_uo.visita AA \n"
                + "           JOIN schema_uo.visita_categorizacion BB ON\n"
                + "           (AA.id_visita_categorizacion=BB.id_visita_categorizacion)\n"
                + "          where id_duo=D.id_duo order by AA.fecha_hora_visita DESC limit 1),'') as ultima_cat\n"
                + "             FROM schema_uo.cama C left JOIN schema_uo.duo D ON D.id_cama = C.id_cama\n"
                + "              and D.estado_duo in (2,1, 3, 21)\n"
                + "                left JOIN schema_uo.sala  \n"
                + "                 ON (schema_uo.sala.id_sala=C.id_sala)\n"
                + "                left JOIN agenda.paciente \n"
                + "                     ON (agenda.paciente.rut=D.rut_paciente)\n"
                + "                    where C.id_sala in (25) and C.estado_cama=1 order by C.\"posicionCama\" asc  ");
        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                cDuo duo = new cDuo();
                duo.setRut_paciente(cnn.getRst().getString("rut_paciente"));
                duo.setNombres_paciente(cnn.getRst().getString("paciente_nombres"));
                duo.setApellidop_paciente(cnn.getRst().getString("paciente_apellidop"));
                duo.setApellidom_paciente(cnn.getRst().getString("paciente_apellidom"));
                duo.setFecha_creacion(cnn.getRst().getString("fecha_duo"));
                duo.setHora_duo(cnn.getRst().getString("hora_duo"));
                duo.setCama_descripcion(cnn.getRst().getString("descripcion_cama"));
                duo.setDias_cama(cnn.getRst().getInt("dias_cama"));
                duo.setEdad(cnn.getRst().getString("edad"));
                duo.setCategorizacion_descripcion(cnn.getRst().getString("ultima_cat"));

                vi.add(duo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();
        return vi;
    }

    public Vector<cCategorizacion> searchCategory() {
        Vector<cCategorizacion> list = new Vector<cCategorizacion>();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select C.id_categorizacion,C.descripcion_categorizacion "
                + "from schema_uo.categorizacion C where C.estado_categorizacion=1 and C.id_categorizacion!=0 order by C.descripcion_categorizacion");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cCategorizacion category = new cCategorizacion();
                category.setDescripcion(this.cnn.getRst().getString("descripcion_categorizacion"));
                category.setId(this.cnn.getRst().getInt("id_categorizacion"));
                list.add(category);
            }
        } catch (SQLException var7) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, (String) null, var7);
        }

        this.cnn.cerrarConexion();
        return list;

    }

    public Vector<cVisita> searchInfoCategory(Date initDate, Date end) {
        Vector<cVisita> list = new Vector<cVisita>();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select TO_CHAR(V.fecha_visita,'DD/MM/YYYY') as fecha_visita , TO_CHAR(V.hora_visita,'HH24:MI') as hora_visita, V.rut_usuario , U.nombre_usuario ||' ' ||U.apellidop_usuario AS usuario_registra,\n"
                + "                p.rut as paciente_rut  , p.nombre ||' ' || p.apellido_paterno as datos_paciente, vc.*\n"
                + "                from schema_uo.visita V,schema_uo.visita_categorizacion VC, schema_uo.usuario U,\n"
                + "                schema_uo.duo d , agenda.paciente p\n"
                + "                where VC.id_visita_categorizacion=V.id_visita_categorizacion and v.rut_usuario = U.rut_usuario AND\n"
                + "                d.id_duo = v.id_duo and p.rut = d.rut_paciente\n"
                + "                   and\n"
                + "V.fecha_visita BETWEEN '" + initDate + "' and '" + end + "'  order by v.fecha_visita");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cVisita visita = new cVisita();
                visita.setFecha_visita(this.cnn.getRst().getString("fecha_visita"));
                visita.setHora_visita(this.cnn.getRst().getString("hora_visita"));
                visita.setRut_usuario(this.cnn.getRst().getString("rut_usuario"));
                visita.setNombre_usuario(this.cnn.getRst().getString("usuario_registra"));
                visita.setRut_paciente(this.cnn.getRst().getString("paciente_rut"));
                visita.setNombre_paciente(this.cnn.getRst().getString("datos_paciente"));
                visita.setCat_visita_categorizacion(this.cnn.getRst().getString("cat_visita_categorizacion"));
                visita.setD1_visita_categorizacion(this.cnn.getRst().getInt("d1_visita_categorizacion"));
                visita.setD2_visita_categorizacion(this.cnn.getRst().getInt("d2_visita_categorizacion"));
                visita.setD3_visita_categorizacion(this.cnn.getRst().getInt("d3_visita_categorizacion"));
                visita.setD4_visita_categorizacion(this.cnn.getRst().getInt("d4_visita_categorizacion"));
                visita.setD5_visita_categorizacion(this.cnn.getRst().getInt("d5_visita_categorizacion"));
                visita.setD6_visita_categorizacion(this.cnn.getRst().getInt("d6_visita_categorizacion"));
                visita.setR1_visita_categorizacion(this.cnn.getRst().getInt("r1_visita_categorizacion"));
                visita.setR2_visita_categorizacion(this.cnn.getRst().getInt("r2_visita_categorizacion"));
                visita.setR3_visita_categorizacion(this.cnn.getRst().getInt("r3_visita_categorizacion"));
                visita.setR4_visita_categorizacion(this.cnn.getRst().getInt("r4_visita_categorizacion"));
                visita.setR5_visita_categorizacion(this.cnn.getRst().getInt("r5_visita_categorizacion"));
                visita.setR6_visita_categorizacion(this.cnn.getRst().getInt("r6_visita_categorizacion"));
                visita.setR7_visita_categorizacion(this.cnn.getRst().getInt("r7_visita_categorizacion"));
                visita.setR8_visita_categorizacion(this.cnn.getRst().getInt("r8_visita_categorizacion"));
                list.add(visita);

            }
        } catch (SQLException var7) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, (String) null, var7);
        }

        this.cnn.cerrarConexion();
        return list;
    }

    public int countCategory(Date time, String descriptionCategory) {
        this.configurarConexion("");
        Integer count = 0;
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select  count(*)as quantity from schema_uo.visita V,schema_uo.visita_categorizacion VC \n"
                + "  where VC.id_visita_categorizacion=V.id_visita_categorizacion and\n"
                + "  V.fecha_visita='" + time + "' and VC.cat_visita_categorizacion='" + descriptionCategory + "'");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                count = this.cnn.getRst().getInt("quantity");
            }
        } catch (SQLException var7) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, (String) null, var7);
        }

        this.cnn.cerrarConexion();
        return count;
    }

    /*new code*/
    public ArrayList buscarComuna() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT id_comuna, nombre FROM agenda.comuna where estatus = 1  order by nombre;");
        this.cnn.conectar();
        try {
            while (this.cnn.getRst().next()) {
                cComuna aux = new cComuna();
                aux.setId_comuna(this.cnn.getRst().getInt("id_comuna"));
                aux.setComuna_descripcion(this.cnn.getRst().getString("nombre"));
                lista.add(aux);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.cnn.cerrarConexion();
        }
        return lista;
    }

    /*new code */
    public ArrayList lista_camas_actuales() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT duo.id_duo, \n"
                + "                  p.rut, p.nombre, p.apellido_paterno, p.apellido_moderno,\n"
                + "                to_char( p.fecha_nacimiento, 'dd/mm/yyyy') as paciente_fecha_nac, age(duo.fecha_duo, p.fecha_nacimiento) AS paciente_edad, \n"
                + "                  pp.nombre, cf.nombre, de.descripcion_derivador, \n"
                + "                  CASE \n"
                + "                  WHEN duo.estado_duo = 1 THEN 'Ingresado Adm.'  \n"
                + "                  WHEN duo.estado_duo = 2 THEN 'En Cama con Ingreso Enf. '  \n"
                + "                  WHEN duo.estado_duo = 21 THEN 'En Cama con Ingreso Med.'  \n"
                + "                  WHEN duo.estado_duo = 3 THEN 'En Cama con Alta Medica'  \n"
                + "                  WHEN duo.estado_duo = 4 THEN 'Alta Administrativa'  \n"
                + "                  ELSE NULL \n"
                + "                 END AS estado, duo.estado_duo,  duo.fecha_duo + duo.hora_duo AS fecha_duo, \n"
                + "                  e.fecha_epicrisis + e.hora_epicrisis AS fecha_epicrisis, \n"
                + "                  age(e.fecha_epicrisis + e.hora_epicrisis, duo.fecha_duo + duo.hora_duo) \n"
                + "                  AS qdias_epi_duo, \n"
                + "                  age(timenow() , datetime_pl(duo.fecha_duo, \n"
                + "                  duo.hora_duo)) AS hasta_hoy, \n"
                + "                 duo.id_cama, duo.duo_tiene_enfermeria \n"
                + "                  FROM agenda.paciente p, \n"
                + "                  schema_uo.derivador de, \n"
                + "                  agenda.prevision pp, \n"
                + "                 agenda.tramo cf, \n"
                + "                 schema_uo.duo duo \n"
                + "                  LEFT JOIN schema_uo.epicrisis e ON e.id_duo = duo.id_duo \n"
                + "                  WHERE p.rut = duo.rut_paciente AND \n"
                + "                 duo.id_derivador = de.id_derivador AND \n"
                + "                   p.provision = pp.id_prevision AND \n"
                + "                   cf.id= p.tramo and\n"
                + "                  (duo.estado_duo = ANY (ARRAY [ 1, 2, 21, 3 ])) \n"
                + "                ORDER BY duo.id_duo ;");
        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                cDuo duo = new cDuo();
                duo.setCama(cnn.getRst().getInt("id_cama"));

                duo.setId_duo(cnn.getRst().getInt("id_duo"));
                duo.setFecha_duo(cnn.getRst().getString("fecha_duo"));

                String hasta_hoy = cnn.getRst().getString("hasta_hoy");
                hasta_hoy = hasta_hoy.replace("days", "dias");
                hasta_hoy = hasta_hoy.replace("mons", "meses");
                hasta_hoy = hasta_hoy.replace("years", "años");
                hasta_hoy = hasta_hoy.replace("day", "dia");
                hasta_hoy = hasta_hoy.replace("mon", "mes");
                hasta_hoy = hasta_hoy.replace("year", "año");

                duo.setDias_hasta_hoy(hasta_hoy);
                // hasta_hoy
                duo.setRut_paciente(cnn.getRst().getString("rut"));
                duo.setNombres_paciente(cnn.getRst().getString("nombres"));
                duo.setApellidop_paciente(cnn.getRst().getString("apellido_paterno"));
                duo.setApellidom_paciente(cnn.getRst().getString("apellido_moderno"));
                duo.setFecha_nac(cnn.getRst().getString("paciente_fecha_nac"));

                String edad = cnn.getRst().getString("paciente_edad");
                edad = edad.replace("days", "dias.z");
                edad = edad.replace("mons", "meses");
                edad = edad.replace("years", "años");
                edad = edad.replace("day", "dia.z");
                edad = edad.replace("mon", "mes");
                edad = edad.replace("year", "año");
                duo.setEdad(edad);

                try {
                    edad = edad.substring(0, edad.indexOf("z") - 1);
                    duo.setEdad(edad);
                } catch (Exception exx) {
                }

                duo.setDerivador_descripcion(cnn.getRst().getString("descripcion_derivador"));
                duo.setEstado_duo(cnn.getRst().getInt("estado_duo"));
                duo.setEstado_duo_descripcion(cnn.getRst().getString("estado"));

                duo.setTramo_prevision(cnn.getRst().getString("tramo"));
                duo.setCodigo_fonasa_descripcion(cnn.getRst().getString("prevision"));

                duo.setFecha_hora_alta_med_duo(cnn.getRst().getString("fecha_epicrisis"));

                String qdias_epi_duo = cnn.getRst().getString("qdias_epi_duo");
                qdias_epi_duo = qdias_epi_duo.replace("days", "dias");
                qdias_epi_duo = qdias_epi_duo.replace("mons", "meses");
                qdias_epi_duo = qdias_epi_duo.replace("years", "años");
                qdias_epi_duo = qdias_epi_duo.replace("day", "dia");
                qdias_epi_duo = qdias_epi_duo.replace("mon", "mes");
                qdias_epi_duo = qdias_epi_duo.replace("year", "año");
                duo.setDias_epi_duo(qdias_epi_duo);

                lista.add(duo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();
        return lista;
    }

    /*code new*/
    public ArrayList lista_examenes() {

        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL(" SELECT e.id_examenes , e.nombre_examenes\n"
                + "  FROM agenda.grupo_examenes_laboratorio gel\n"
                + "     inner join agenda.examenes e on gel.idexamen = e.id_examenes where e.estatus =1; ");
        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                cUnidadMedida uni = new cUnidadMedida();

                uni.setDescripcion(cnn.getRst().getString("nombre_examenes"));
                uni.setId_unidad_medida(cnn.getRst().getInt("id_examenes"));

                lista.add(uni);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();
        return lista;
    }

    public cReceta searchLastRecipe(int duo) {
        this.configurarConexion("");
        cReceta r = new cReceta();
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT \n"
                + "  idindicaciones,\n"
                + "  idduo,\n"
                + "  reposo,\n"
                + "  regimen,\n"
                + "  otras_indicaciones,\n"
                + "  fecha,coalesce(control_signos, '') as control_signos, coalesce(aislamiento, '') as aislamiento, coalesce(alergias,' ') as alergias, coalesce(diagnostico,' ') as diagnostico, coalesce(contencion,' ') as contencion,\n"
                + "     coalesce(imagenes, '') as imagenes, coalesce(otros, '') as otros, coalesce(indicaciones_enfermeria,' ') as indicaciones_enfermeria, coalesce(indicaciones_nutricionista,' ') as indicaciones_nutricionista, \n"
                + "   coalesce(indicaciones_kinesiologo,'') as indicaciones_kinesiologo, coalesce(indicaciones_otros, '') as indicaciones_otros,\n"
                + "usu.nombre_usuario ||' '|| usu.apellidop_usuario ||' '||usu.apellidom_usuario as usuario,"
                + "  to_char(fecha, 'DD/MM/yyyy'::text) AS fechastring,\n"
                + "    to_char(fecha, 'HH24:MI:SS'::text) AS hora\n"
                + "FROM \n"
                + "  schema_uo.indicaciones ind\n"
                + "  JOIN schema_uo.usuario usu ON ind.usuario = usu.rut_usuario where idduo = " + duo + "\n"
                + "  order by hora desc limit 1 ");

        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {

                r.setId_duo(cnn.getRst().getInt("idindicaciones"));
                r.setRegimen(cnn.getRst().getString("regimen"));
                r.setReposo(cnn.getRst().getString("reposo"));
                r.setIndicacion(cnn.getRst().getString("otras_indicaciones"));
                r.setNombre_usuario(cnn.getRst().getString("usuario"));
                r.setFechadate(cnn.getRst().getDate("fecha"));
                r.setFecha(cnn.getRst().getString("fechastring"));
                r.setHgt(cnn.getRst().getString("hora"));
                r.setDiagnostico(cnn.getRst().getString("diagnostico"));
                r.setControl_signos(cnn.getRst().getString("control_signos"));
                r.setAislamiento(cnn.getRst().getString("aislamiento"));
                r.setAlergias(cnn.getRst().getString("alergias"));
                r.setContencion(cnn.getRst().getString("contencion"));
                r.setImagenes(cnn.getRst().getString("imagenes"));
                r.setOtros(cnn.getRst().getString("otros"));
                r.setIndicaciones_enfermeria(cnn.getRst().getString("indicaciones_enfermeria"));
                r.setIndicaciones_nutricionista(cnn.getRst().getString("indicaciones_nutricionista"));
                r.setIndicaciones_kinesiologo(cnn.getRst().getString("indicaciones_kinesiologo"));
                r.setIndicaciones_otros(cnn.getRst().getString("indicaciones_otros"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();

        return r;
    }

    public ArrayList bucarindicacionesporduo(int duo) {
        ArrayList vr = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT \n"
                + "  idindicaciones,\n"
                + "  idduo,\n"
                + "  reposo,\n"
                + "  regimen,\n"
                + "  otras_indicaciones,\n"
                + "  fecha,coalesce(control_signos, '') as control_signos, coalesce(aislamiento, '') as aislamiento, coalesce(alergias, '') as alergias, coalesce(diagnostico, '') as diagnostico, coalesce(contencion, '') as contencion,\n"
                + "    coalesce(imagenes, '') as imagenes, coalesce(otros, '') as otros, coalesce(indicaciones_enfermeria, '') as indicaciones_enfermeria, coalesce(indicaciones_nutricionista, '') as indicaciones_nutricionista, \n"
                + "     coalesce(indicaciones_kinesiologo, '') as indicaciones_kinesiologo, coalesce(indicaciones_otros, '') as indicaciones_otros,\n"
                + "usu.nombre_usuario ||' '|| usu.apellidop_usuario ||' '||usu.apellidom_usuario as usuario,"
                + "  to_char(fecha, 'DD/MM/yyyy'::text) AS fechastring,\n"
                + "    to_char(fecha, 'HH24:MI:SS'::text) AS hora\n"
                + "FROM \n"
                + "  schema_uo.indicaciones ind\n"
                + "  JOIN schema_uo.usuario usu ON ind.usuario = usu.rut_usuario where idduo = " + duo + "\n"
                + "  order by hora desc ");

        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                cReceta r = new cReceta();
                r.setId_duo(cnn.getRst().getInt("idindicaciones"));
                r.setRegimen(cnn.getRst().getString("regimen"));
                r.setReposo(cnn.getRst().getString("reposo"));
                r.setIndicacion(cnn.getRst().getString("otras_indicaciones"));
                r.setNombre_usuario(cnn.getRst().getString("usuario"));
                r.setFechadate(cnn.getRst().getDate("fecha"));
                r.setFecha(cnn.getRst().getString("fechastring"));
                r.setHgt(cnn.getRst().getString("hora"));
                r.setDiagnostico(cnn.getRst().getString("diagnostico"));
                r.setControl_signos(cnn.getRst().getString("control_signos"));
                r.setAislamiento(cnn.getRst().getString("aislamiento"));
                r.setAlergias(cnn.getRst().getString("alergias"));
                r.setContencion(cnn.getRst().getString("contencion"));
                r.setImagenes(cnn.getRst().getString("imagenes"));
                r.setOtros(cnn.getRst().getString("otros"));
                r.setIndicaciones_enfermeria(cnn.getRst().getString("indicaciones_enfermeria"));
                r.setIndicaciones_nutricionista(cnn.getRst().getString("indicaciones_nutricionista"));
                r.setIndicaciones_kinesiologo(cnn.getRst().getString("indicaciones_kinesiologo"));
                r.setIndicaciones_otros(cnn.getRst().getString("indicaciones_otros"));

                vr.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();

        return vr;
    }

    public int obtenerid() {
        int id = 0;
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  max(idindicaciones) as id\n"
                + "  FROM schema_uo.indicaciones ;");

        this.cnn.conectar();
        try {
            if (cnn.getRst().next()) {
                id = cnn.getRst().getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.cnn.cerrarConexion();

        }
        return id + 1;
    }

    /**/
    public int lista_duos_hoy(String fecha_hoy, int modo) {
        new ArrayList();
        int total = 0;
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        if (modo == 1) {
            this.cnn.setSentenciaSQL("select count(*) as suma  from schema_uo.duo D where D.fecha_duo=CURRENT_DATE and estado_duo NOT IN (99)");
        } else {
            this.cnn.setSentenciaSQL("select  count(*) as suma   from schema_uo.epicrisis E where E.fecha_epicrisis=CURRENT_DATE");
        }

        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                new cDuo();
                total = this.cnn.getRst().getInt("suma");
            }
        } catch (SQLException var6) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var6);
        }

        this.cnn.cerrarConexion();
        return total;
    }

    /*new code */
    public ArrayList lista_sesion_psicolo(int id_duo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  ses_id, ses_estado, ses_usuario, \n TO_CHAR(ses_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS')as ses_fecha_ingreso, TO_CHAR(ses_fecha_hora,'DD/MM/YYYY HH24:MI:SS')as ses_fecha_hora, \n TO_CHAR(ses_fecha_hora,'DD/MM/YYYY')as ses_fecha,\n TO_CHAR(ses_fecha_hora,'HH24:MI:SS')as ses_hora,\n ses_detalle, ses_duo,\n USU.nombre_usuario,USU.apellidop_usuario,USU.apellidom_usuario\n FROM  schema_uo.psicolo_sesion\n SES INNER JOIN schema_uo.usuario USU ON(SES.ses_usuario=USU.rut_usuario)\n WHERE  ses_duo='" + id_duo + "' and ses_estado='1'  order by SES.ses_fecha_hora desc  ;");
        this.cnn.conectar();

        cSesionKine ses;
        try {
            for (; this.cnn.getRst().next(); lista.add(ses)) {
                ses = new cSesionKine();
                ses.setId_sesion_kine(this.cnn.getRst().getInt("ses_id"));
                ses.setId_duo(this.cnn.getRst().getInt("ses_duo"));
                ses.setFecha_ingreso_sesion(this.cnn.getRst().getString("ses_fecha_ingreso"));
                ses.setFecha_hora(this.cnn.getRst().getString("ses_fecha_hora"));
                ses.setFecha(this.cnn.getRst().getString("ses_fecha"));
                ses.setHora(this.cnn.getRst().getString("ses_hora"));
                ses.setDetalle(this.cnn.getRst().getString("ses_detalle"));
                ses.setRut_usuario(this.cnn.getRst().getString("ses_usuario"));
                ses.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                ses.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                ses.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                ses.setEstado_sesion(this.cnn.getRst().getInt("ses_estado"));
                if (ses.getEstado_sesion() == 0) {
                    ses.setEstado_desc_sesion("Anulado");
                } else {
                    ses.setEstado_desc_sesion("Activo");
                }
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        return lista;
    }

    public String getCatRiesgo(int valor_riesgo) {
        String letra = "";
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select * from schema_uo.cat_riesgo CR where " + valor_riesgo + " between CR.desde_cat_riesgo and CR.hasta_cat_riesgo");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                letra = this.cnn.getRst().getString("resultado_cat_riesgo");
            }
        } catch (SQLException var4) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return letra;
    }

    public String getCatDependencia(int valor_dependencia) {
        String numero = "";
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select * from schema_uo.cat_dependencia CD where " + valor_dependencia + " between CD.desde_cat_dependencia and CD.hasta_cat_dependencia");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                numero = this.cnn.getRst().getString("resultado_cat_dependencia");
            }
        } catch (SQLException var4) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return numero;
    }

    public cVisita obtiene_visita_medica(int id_visita_medica) {
        cVisita vis = new cVisita();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT vis_id, vis_usuario,   vis_duo,  TO_CHAR( vis_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS') as vis_fecha_ingreso,  TO_CHAR( vis_fecha,'DD/MM HH24:MI') as vis_fecha1,  TO_CHAR( vis_fecha,'DD/MM') as vis_dia,  TO_CHAR(vis_fecha,'HH24:MI') as vis_hora,   vis_evolucion, vis_estado, vis_usuario_anula, vis_motivo_anula  FROM  schema_uo.visita_medica WHERE vis_id='" + id_visita_medica + "' ;");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                vis.setId_visita_categorizacion(this.cnn.getRst().getInt("vis_id"));
                vis.setId_duo(this.cnn.getRst().getInt("vis_duo"));
                vis.setObs_visita(this.cnn.getRst().getString("vis_evolucion"));
                vis.setFecha_ingreso_visita(this.cnn.getRst().getString("vis_fecha_ingreso"));
                vis.setFecha_visita(this.cnn.getRst().getString("vis_fecha1"));
                vis.setHora_visita(this.cnn.getRst().getString("vis_hora"));
                vis.setDia_visita(this.cnn.getRst().getString("vis_dia"));
                vis.setRut_usuario(this.cnn.getRst().getString("vis_usuario"));
                vis.setApellidop_usuario(this.cnn.getRst().getString("nombre_usuario"));
                vis.setApellidom_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                vis.setNombre_usuario(this.cnn.getRst().getString("apellidom_usuario"));
            }
        } catch (SQLException var7) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var7);
        } finally {
            this.cnn.cerrarConexion();
        }

        return vis;
    }

    public ArrayList lista_historial_paciente(int id_duo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select  fecha,    hora,    proviene,    profesional,   descripcion , fechadate from schema_uo.sesiones where duo = " + id_duo + " ORDER BY fechadate desc");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cVisita aux = new cVisita();
                aux.setFecha_visita(this.cnn.getRst().getString("fecha"));
                aux.setHora_visita(this.cnn.getRst().getString("hora"));
                aux.setDia_visita(this.cnn.getRst().getString("proviene"));
                aux.setApellidop_usuario(this.cnn.getRst().getString("profesional"));
                aux.setDescripcion_cama(this.cnn.getRst().getString("descripcion"));
                lista.add(aux);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_historial_visita_medica(int id_duo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT vis_id, vis_usuario,vis_duo,   nombre_usuario, apellidop_usuario,apellidom_usuario, TO_CHAR( vis_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS') as vis_fecha_ingreso,  TO_CHAR(  vis_fecha,'DD/MM HH24:MI') as vis_fecha1,  TO_CHAR(  vis_fecha,'DD/MM') as vis_dia,   TO_CHAR(  vis_fecha,'HH24:MI') as vis_hora,    vis_evolucion, vis_estado, vis_usuario_anula, vis_motivo_anula  FROM  schema_uo.visita_medica   VIS INNER JOIN schema_uo.usuario USU ON (VIS.vis_usuario= USU.rut_usuario) WHERE vis_duo='" + id_duo + "' order by vis_fecha DESC ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cVisita aux = new cVisita();
                aux.setFecha_visita(this.cnn.getRst().getString("vis_fecha1"));
                aux.setId_visita_categorizacion(this.cnn.getRst().getInt("vis_id"));
                aux.setId_duo(this.cnn.getRst().getInt("vis_duo"));
                aux.setObs_visita(this.cnn.getRst().getString("vis_evolucion"));
                aux.setFecha_ingreso_visita(this.cnn.getRst().getString("vis_fecha_ingreso"));
                aux.setHora_visita(this.cnn.getRst().getString("vis_hora"));
                aux.setDia_visita(this.cnn.getRst().getString("vis_dia"));
                aux.setRut_usuario(this.cnn.getRst().getString("vis_usuario"));
                aux.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                aux.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                aux.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                lista.add(aux);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_historial_visita_enfermeria(int id_duo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL(" select cat_visita_categorizacion,\n to_char(fecha_visita,'DD/MM/YYYY')as fecha_visita ,\n to_char(hora_visita,'HH24:MI')as hora_visita ,\n to_char(fecha_hora_visita,'DD/MM/YYYY HH24:MI:SS')as fecha_ingreso_visita,\n id_visita, descripcion_cama ,id_duo,\n obs_visita,fecha_visita, hora_visita, fecha_hora_visita,\n U.rut_usuario,nombre_usuario,apellidop_usuario,apellidom_usuario\n from schema_uo.visita V,schema_uo.visita_categorizacion VC,\n schema_uo.cama C,schema_uo.usuario U where V.id_cama=C.id_cama and \n V.id_duo='" + id_duo + "' and V.id_visita_categorizacion=VC.id_visita_categorizacion \n AND V.rut_usuario=U.rut_usuario\n order by V.fecha_visita DESC");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cVisita aux = new cVisita();
                aux.setCat_visita_categorizacion(this.cnn.getRst().getString("cat_visita_categorizacion"));
                aux.setFecha_visita(this.cnn.getRst().getString("fecha_visita"));
                aux.setHora_visita(this.cnn.getRst().getString("hora_visita"));
                aux.setFecha_ingreso_visita(this.cnn.getRst().getString("fecha_ingreso_visita"));
                aux.setObs_visita(this.cnn.getRst().getString("obs_visita"));
                aux.setId_duo(this.cnn.getRst().getInt("id_duo"));
                aux.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                aux.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                aux.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                aux.setId_visita_categorizacion(this.cnn.getRst().getInt("id_visita"));
                aux.setDescripcion_cama(this.cnn.getRst().getString("descripcion_cama"));
                lista.add(aux);
            }
        } catch (SQLException var7) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var7);
        } finally {
            this.cnn.cerrarConexion();
        }

        return lista;
    }

    public cVisita obtiene_visita_enfermeria(int id_visita) {
        cVisita vis = new cVisita();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT \n  id_visita,obs_visita,fecha_visita, hora_visita, fecha_hora_visita,\n  VIS.rut_usuario,nombre_usuario,apellidop_usuario,apellidom_usuario,\n  VIS.id_cama, VIS.id_visita_categorizacion, tipo_visita, id_duo,\n  d1_visita_categorizacion, d2_visita_categorizacion, d3_visita_categorizacion,\n  d4_visita_categorizacion, d5_visita_categorizacion, d6_visita_categorizacion,\n  r1_visita_categorizacion, r2_visita_categorizacion, r3_visita_categorizacion,\n  r4_visita_categorizacion, r5_visita_categorizacion, r6_visita_categorizacion,\n  r7_visita_categorizacion, r8_visita_categorizacion, cat_visita_categorizacion\n  FROM  schema_uo.visita VIS\n  INNER JOIN schema_uo.visita_categorizacion CAT ON (VIS.id_visita_categorizacion=CAT.id_visita_categorizacion)  \n  INNER JOIN schema_uo.usuario USU ON (VIS.rut_usuario=USU.rut_usuario)\n  WHERE id_visita= '" + id_visita + "'    ;");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                vis.setCat_visita_categorizacion(this.cnn.getRst().getString("cat_visita_categorizacion"));
                vis.setFecha_visita(this.cnn.getRst().getString("fecha_visita"));
                vis.setId_visita_categorizacion(this.cnn.getRst().getInt("id_visita"));
                vis.setId_duo(this.cnn.getRst().getInt("id_duo"));
                vis.setObs_visita(this.cnn.getRst().getString("obs_visita"));
                vis.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                vis.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                vis.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                vis.setD1_visita_categorizacion(this.cnn.getRst().getInt("d1_visita_categorizacion"));
                vis.setD2_visita_categorizacion(this.cnn.getRst().getInt("d2_visita_categorizacion"));
                vis.setD3_visita_categorizacion(this.cnn.getRst().getInt("d3_visita_categorizacion"));
                vis.setD4_visita_categorizacion(this.cnn.getRst().getInt("d4_visita_categorizacion"));
                vis.setD5_visita_categorizacion(this.cnn.getRst().getInt("d5_visita_categorizacion"));
                vis.setD6_visita_categorizacion(this.cnn.getRst().getInt("d6_visita_categorizacion"));
                vis.setR1_visita_categorizacion(this.cnn.getRst().getInt("r1_visita_categorizacion"));
                vis.setR2_visita_categorizacion(this.cnn.getRst().getInt("r2_visita_categorizacion"));
                vis.setR3_visita_categorizacion(this.cnn.getRst().getInt("r3_visita_categorizacion"));
                vis.setR4_visita_categorizacion(this.cnn.getRst().getInt("r4_visita_categorizacion"));
                vis.setR5_visita_categorizacion(this.cnn.getRst().getInt("r5_visita_categorizacion"));
                vis.setR6_visita_categorizacion(this.cnn.getRst().getInt("r6_visita_categorizacion"));
                vis.setR7_visita_categorizacion(this.cnn.getRst().getInt("r7_visita_categorizacion"));
                vis.setR8_visita_categorizacion(this.cnn.getRst().getInt("r8_visita_categorizacion"));
            }
        } catch (SQLException var7) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var7);
        } finally {
            this.cnn.cerrarConexion();
        }

        return vis;
    }

    public cDuo obtiene_duo_liviano(int id_duo) {
        cDuo duo = new cDuo();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT * FROM schema_uo.duo  where id_duo='" + id_duo + "' ; ");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                duo.setId_duo(this.cnn.getRst().getInt("id_duo"));
                duo.setFecha_duo(this.cnn.getRst().getString("fecha_duo"));
                duo.setHora_duo(this.cnn.getRst().getString("hora_duo"));
                duo.setEstado_duo(this.cnn.getRst().getInt("estado_duo"));
            }
        } catch (SQLException var4) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return duo;
    }

    /*change duo*/
    public cDuo obtiene_duo(int id_duo) {
        cDuo duo = new cDuo();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);

        this.cnn.setSentenciaSQL("SELECT  \n"
                + "AA.id_duo, \n"
                + "fecha_duo as fecha ,\n"
                + " to_char(fecha_duo,'DD/MM/YYYY')as fecha_duo,hora_duo,\n"
                + " estado_duo,  \n"
                + " AA.id_cama,\n"
                + "  descripcion_cama,\n"
                + "  prevision.id_prevision,  \n"
                + " to_char(fecha_hora_ing_duo,'DD/MM/YYYY HH24:MI:SS')as fecha_hora_ing_duo,  \n"
                + " rut_usuario,\n"
                + " AA.programa_duo,"
                + "c.nombre as comuna_descripcion,  \n"
                + " EXTRACT(SECOND FROM CURRENT_TIMESTAMP-fecha_hora_ing_med) as dif_ss,  \n"
                + " EXTRACT(MINUTE FROM CURRENT_TIMESTAMP-fecha_hora_ing_med) as dif_mm,   \n"
                + " EXTRACT(HOUR FROM CURRENT_TIMESTAMP-fecha_hora_ing_med)as dif_hh,   \n"
                + " EXTRACT(DAY FROM CURRENT_TIMESTAMP-fecha_hora_ing_med)as dif_dd, \n"
                + "(SELECT nombre_usuario||' '||apellidop_usuario||' '||apellidom_usuario as \n"
                + " nombre_completo  \n"
                + " FROM  schema_uo.usuario where rut_usuario=AA.rut_usuario) as nombre_admisor,  \n"
                + " rut_paciente,anamnesis_duo, \n"
                + "  AA.id_derivador,descripcion_derivador,  \n"
                + "AA.id_categorizacion,\n"
                + "descripcion_categorizacion,\n"
                + "tipo_duo_id,  \n"
                + "to_char(fecha_hora_alta_med_duo,'DD/MM/YYYY HH24:MI:SS')as fecha_hora_alta_med_duo ,\n"
                + "to_char(fecha_hora_alta_adm_duo ,'DD/MM/YYYY HH24:MI:SS')as fecha_hora_alta_adm_duo, \n"
                + "duo_tiene_enfermeria,\n"
                + "  rut_usuario_ing_med, \n"
                + "(SELECT to_char(LL.fecha_hora_ing_enfermeria,'DD/MM/YYYY HH24:MI:SS') as fecha_hora_ing_enfermeria FROM schema_uo.ing_enfermeria LL   \n"
                + "where LL.id_duo_ing_enfermeria=AA.id_duo limit 1) as fecha_hora_ing_enf,  \n"
                + "(SELECT   \n"
                + " nombre_usuario||' '||apellidop_usuario||' '||apellidom_usuario as nombre_completo  \n"
                + " FROM  schema_uo.usuario VV JOIN schema_uo.ing_enfermeria RR ON  \n"
                + "(VV.rut_usuario=RR.rut_usuario_ing_enfermeria)   \n"
                + "where RR.id_duo_ing_enfermeria=AA.id_duo limit 1) as nombre_enf,  \n"
                + "(SELECT   \n"
                + "nombre_usuario||' '||apellidop_usuario||' '||apellidom_usuario as nombre_completo  \n"
                + "FROM  schema_uo.usuario where rut_usuario= rut_usuario_ing_med) as nombre_med,  \n"
                + " to_char(fecha_hora_ing_med,'DD/MM/YYYY HH24:MI:SS')as fecha_hora_ing_med, \n"
                + " ip_ing_enf,ip_ing_med,  \n"
                + " ( SELECT BB.cat_visita_categorizacion   \n"
                + " FROM schema_uo.visita AA    \n"
                + " JOIN schema_uo.visita_categorizacion BB ON    \n"
                + " (AA.id_visita_categorizacion=BB.id_visita_categorizacion)    \n"
                + "  where id_duo=AA.id_duo order by AA.fecha_hora_visita DESC limit 1 ) as ultima_cat,  \n"
                + "   ( SELECT BB.cat_visita_categorizacion   \n"
                + "  FROM schema_uo.visita AA    \n"
                + " JOIN schema_uo.visita_categorizacion BB ON    \n"
                + " (AA.id_visita_categorizacion=BB.id_visita_categorizacion)    \n"
                + "  where id_duo=AA.id_duo order by AA.fecha_hora_visita ASC limit 1 ) as primera_cat, \n"
                + "  BB.nombre as paciente_nombres,\n"
                + "  BB.apellido_paterno as paciente_apellidop,\n"
                + "   BB.apellido_moderno as paciente_apellidom,  \n"
                + " BB.contacto1 as paciente_telefono1, \n"
                + " BB.contacto2 as paciente_telefono2,  \n"
                + " to_char( BB.fecha_nacimiento,'DD/MM/YYYY')as paciente_fecha_nac,  \n"
                + "age(CURRENT_DATE,BB.fecha_nacimiento) AS paciente_edad,  \n"
                + " BB.direccion as paciente_direccion,BB.genero as paciente_sexo, \n"
                + " BB.procedencia as paciente_consultorio,\n"
                + " BB.id_nacionalidad as paciente_nacionalidad,\n"
                + " (SELECT nac_descripcion FROM  schemaoirs.nacion where nac_id=BB.id_nacionalidad limit 1)as paciente_nacionalidad_descripcion,  \n"
                + "(SELECT PP.con_descripcion from schemaoirs.consultorio_pertenencia PP WHERE  \n"
                + " PP.con_id=BB.procedencia)as consultorio_pertenencia,  \n"
                + "prevision.nombre as codigo_fonasa_descripcion,\n"
                + "tramo.nombre as tramo_prevision_paciente,  \n"
                + "\n"
                + "EXTRACT(DAY FROM (fecha_hora_alta_med_duo)-(fecha_duo+hora_duo)) as dias_cama, BB.email as paciente_mail,  \n"
                + " EXTRACT(DAY FROM (CURRENT_DATE+CURRENT_TIME)-(fecha_duo+hora_duo)) as dias_cama2  \n"
                + "  FROM schema_uo.duo AA  \n"
                + " JOIN agenda.paciente BB ON  \n"
                + " (AA.rut_paciente=BB.rut )  \n"
                + " JOIN agenda.prevision  ON   \n"
                + "(BB.provision=prevision.id_prevision)  \n"
                + "JOIN agenda.tramo ON   \n"
                + "(tramo.id=BB.tramo)  \n"
                + " JOIN schema_uo.cama ON (AA.id_cama=cama.id_cama)"
                + " join agenda.comuna c on  (bb.id_comuna = c.id_comuna)   \n"
                + "\n"
                + " JOIN schema_uo.derivador ON (AA.id_derivador=derivador.id_derivador)  \n"
                + " JOIN schema_uo.categorizacion ON (AA.id_categorizacion=categorizacion.id_categorizacion) \n"
                + " where id_duo='" + id_duo + "' ");
        this.cnn.conectar();
        try {
            if (cnn.getRst().next()) {
                duo.setId_duo(cnn.getRst().getInt("id_duo"));
                duo.setFecha_duo(cnn.getRst().getString("fecha_duo"));
                duo.setHora_duo(cnn.getRst().getString("hora_duo"));
                duo.setEstado_duo(cnn.getRst().getInt("estado_duo"));
                duo.setFecha(cnn.getRst().getDate("fecha"));
                duo.setDias_cama(cnn.getRst().getInt("dias_cama2"));
                duo.setDias_reales_cama(this.cnn.getRst().getInt("dias_cama"));

                if (duo.getEstado_duo() == 1) {
                    duo.setEstado_duo_descripcion("INGRESADO ADM.");
                } else if (duo.getEstado_duo() == 2) {
                    duo.setEstado_duo_descripcion("RECEPCIONADO CON INGRESO DE ENFERMERIA");
                } else if (duo.getEstado_duo() == 3) {
                    duo.setEstado_duo_descripcion("CON ALTA MEDICA");
                } else if (duo.getEstado_duo() == 4) {
                    duo.setEstado_duo_descripcion("CON ALTA ADMINISTRATIVA");
                } else if (duo.getEstado_duo() == 21) {
                    duo.setEstado_duo_descripcion("EN CAMA");
                } else if (duo.getEstado_duo() == 99) {
                    duo.setEstado_duo_descripcion("ANULADO");
                }

                duo.setCama(cnn.getRst().getInt("id_cama"));
                duo.setCama_descripcion(cnn.getRst().getString("descripcion_cama"));
                duo.setId_prevision(cnn.getRst().getInt("id_prevision"));
                duo.setFecha_hora_ing_duo(cnn.getRst().getString("fecha_hora_ing_duo"));
                duo.setRut_usuario(cnn.getRst().getString("rut_usuario"));
                duo.setNombre_usuario_admision(cnn.getRst().getString("nombre_admisor"));
                duo.setRut_paciente(cnn.getRst().getString("rut_paciente"));
                duo.setAnamnesis_duo(cnn.getRst().getString("anamnesis_duo"));
                duo.setDerivador_id(cnn.getRst().getInt("id_derivador"));
                duo.setDerivador_descripcion(cnn.getRst().getString("descripcion_derivador"));
                duo.setCategorizacion_id(cnn.getRst().getInt("id_categorizacion"));
                duo.setCategorizacion_descripcion(cnn.getRst().getString("descripcion_categorizacion"));
                duo.setTipo_duo_id(cnn.getRst().getInt("tipo_duo_id"));

                duo.setFecha_hora_alta_adm_duo(cnn.getRst().getString("fecha_hora_alta_adm_duo"));
                duo.setFecha_hora_alta_med_duo(cnn.getRst().getString("fecha_hora_alta_med_duo"));
                 duo.setTengoEgreso(true);

                if (duo.getFecha_hora_alta_adm_duo() == null) {
                    duo.setFecha_hora_alta_adm_duo("---");
                    duo.setTengoEgreso(false);
                }

                if (duo.getFecha_hora_alta_med_duo() == null) {
                    duo.setFecha_hora_alta_med_duo("---");
                     duo.setTengoEgreso(false);
                }

                duo.setTiene_enfermeria(cnn.getRst().getInt("duo_tiene_enfermeria"));
                duo.setRut_usuario_ing_med(cnn.getRst().getString("rut_usuario_ing_med"));
                duo.setNombre_usuario_ing_enf(cnn.getRst().getString("nombre_enf"));
                duo.setNombre_usuario_ing_med(cnn.getRst().getString("nombre_med"));
                if (duo.getNombre_usuario_ing_med().contains("PRUEBA")) {
                    duo.setNombre_usuario_ing_med("--");
                }
                duo.setFecha_hora_ing_med(cnn.getRst().getString("fecha_hora_ing_med"));
                if (duo.getFecha_hora_ing_med() == null) {
                    duo.setFecha_hora_ing_med("---");
                }
                duo.setFecha_hora_ing_enf(cnn.getRst().getString("fecha_hora_ing_enf"));
                if (duo.getFecha_hora_ing_med() == null) {
                    duo.setFecha_hora_ing_enf("---");
                }
                duo.setIp_ing_enf(cnn.getRst().getString("ip_ing_enf"));
                duo.setIp_ing_med(cnn.getRst().getString("ip_ing_med"));

                duo.setPrimera_clasificacion(cnn.getRst().getString("primera_cat"));
                duo.setUltima_clasificacion(cnn.getRst().getString("ultima_cat"));
                duo.setNombres_paciente(cnn.getRst().getString("paciente_nombres"));
                duo.setApellidop_paciente(cnn.getRst().getString("paciente_apellidop"));
                duo.setApellidom_paciente(cnn.getRst().getString("paciente_apellidom"));
                duo.setTelefono1(cnn.getRst().getString("paciente_telefono1"));
                duo.setTelefono2(cnn.getRst().getString("paciente_telefono2"));
                duo.setFecha_nac(cnn.getRst().getString("paciente_fecha_nac"));

                String edad = cnn.getRst().getString("paciente_edad");
                edad = edad.replace("years", "años");
                edad = edad.replace("year", "año");
                edad = edad.replace("mons", "meses");
                edad = edad.replace("mon", "mes");
                edad = edad.replace("days", "dias.z");
                edad = edad.replace("day", "dia.z");

                duo.setEdad(edad);

                try {
                    edad = edad.substring(0, edad.indexOf("z") - 1);
                    duo.setEdad(edad);
                } catch (Exception exx) {
                }

                duo.setDireccion(cnn.getRst().getString("paciente_direccion"));
                duo.setComuna_descri(cnn.getRst().getString("comuna_descripcion"));
                duo.setSexo(cnn.getRst().getInt("paciente_sexo"));
                if (duo.getSexo() == 2) {
                    duo.setSexo_descri("Masculino");
                } else if (duo.getSexo() == 1) {
                    duo.setSexo_descri("Femenino");
                } else {
                    duo.setSexo_descri("---");
                }
                duo.setPueblo(0);
                duo.setConsultorio(cnn.getRst().getInt("paciente_consultorio"));
                duo.setPueblo_descri("Sin Pueblo");
                duo.setConsultorio_descri(cnn.getRst().getString("consultorio_pertenencia"));

                if (duo.getPueblo_descri() == null) {
                    duo.setPueblo_descri("");
                }
                if (duo.getConsultorio_descri() == null) {
                    duo.setConsultorio_descri("");
                }

                duo.setPrograma(cnn.getRst().getInt("programa_duo"));
                if (duo.getPrograma() == 0) {
                    duo.setPrograma_descripcion("Sin Información");
                } else if (duo.getPrograma() == 1) {
                    duo.setPrograma_descripcion("Ges Si");
                } else if (duo.getPrograma() == 2) {
                    duo.setPrograma_descripcion("Ges No");
                }

                duo.setCodigo_fonasa_descripcion(cnn.getRst().getString("codigo_fonasa_descripcion"));
                duo.setTramo_prevision(cnn.getRst().getString("tramo_prevision_paciente"));

                duo.setNacion(cnn.getRst().getInt("paciente_nacionalidad"));

                duo.setNacion_descripcion(cnn.getRst().getString("paciente_nacionalidad_descripcion"));

                if (duo.getNacion_descripcion() == null) {
                    duo.setNacion_descripcion("");
                }

                duo.setDif_dd(cnn.getRst().getInt("dif_dd"));
                duo.setDif_hh(cnn.getRst().getInt("dif_hh"));
                duo.setDif_mm(cnn.getRst().getInt("dif_mm"));
                duo.setDif_ss(cnn.getRst().getInt("dif_ss"));

                duo.setMail(cnn.getRst().getString("paciente_mail"));

                duo.setDias_cama(cnn.getRst().getInt("dias_cama2"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();
        return duo;
    }

    public ArrayList obtiene_duo_enfermedad(int duo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  id_duo_enfermedad_cro,id_duo,   AA.id_enfermedad_cro, BB.descripcion_enfermedad_cro    FROM  schema_uo.duo_enfermedad_cro AA    JOIN schema_uo.enfermedad_cro BB ON (AA.id_enfermedad_cro=BB.id_enfermedad_cro)    where id_duo='" + duo + "'");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cEnfermedad aux = new cEnfermedad();
                aux.setDescripcion(this.cnn.getRst().getString("descripcion_enfermedad_cro"));
                aux.setId(this.cnn.getRst().getInt("id_duo_enfermedad_cro"));
                lista.add(aux);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_diagnostico(int id_duo, String tipo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT   id_diagnostico_duo, descripcion_diagnostico_duo,  tipo_diagnostico_duo, id_duo  FROM  schema_uo.diagnostico_duo where id_duo='" + id_duo + "' and  tipo_diagnostico_duo IN (" + tipo + ") ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cDiagnostico diag = new cDiagnostico();
                diag.setId_diagnostico(this.cnn.getRst().getInt("id_diagnostico_duo"));
                diag.setId_duo(this.cnn.getRst().getInt("id_duo"));
                diag.setTipo_diagnostico(this.cnn.getRst().getInt("tipo_diagnostico_duo"));
                diag.setDescripcion_diagnostico(this.cnn.getRst().getString("descripcion_diagnostico_duo"));
                lista.add(diag);
            }
        } catch (SQLException var5) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var5);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_diagnostico_trazador(int id_duo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  duop_id,duop_duo, duop_prestacion,pre_nombre,  duop_estado,to_char(duop_ingreso,'DD/MM/YYYY HH24:MI:SS')as duop_ingreso  FROM  schema_uo.prestacion_duo PED JOIN schema_uo.prestacion PRE  ON (PED.duop_prestacion=PRE.pre_id) WHERE  duop_duo=" + id_duo + " and duop_estado=1  ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cDiagnostico diag = new cDiagnostico();
                diag.setId_diagnostico(this.cnn.getRst().getInt("duop_id"));
                diag.setId_duo(this.cnn.getRst().getInt("duop_duo"));
                diag.setDescripcion_diagnostico(this.cnn.getRst().getString("pre_nombre"));
                diag.setFecha_ingreso(this.cnn.getRst().getString("duop_ingreso"));
                lista.add(diag);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_enfermedad_cronica(int id_duo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  id_duo_enfermedad_cro, id_duo,DEC.id_enfermedad_cro,descripcion_enfermedad_cro  FROM  schema_uo.duo_enfermedad_cro DEC  JOIN schema_uo.enfermedad_cro ENF ON (DEC.id_enfermedad_cro=ENF.id_enfermedad_cro)  WHERE DEC.id_duo='" + id_duo + "'");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cDiagnostico diag = new cDiagnostico();
                diag.setId_diagnostico(this.cnn.getRst().getInt("id_duo_enfermedad_cro"));
                diag.setId_duo(this.cnn.getRst().getInt("id_duo"));
                diag.setDescripcion_diagnostico(this.cnn.getRst().getString("descripcion_enfermedad_cro"));
                lista.add(diag);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    /*new code */
    public cPaciente obtiene_paciente(String rut_paciente) {
        cPaciente pac = new cPaciente();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT \n"
                + "AA.rut as paciente_rut, AA.nombre AS  paciente_nombres,\n"
                + "AA.apellido_paterno AS paciente_apellidop, \n"
                + " AA.apellido_moderno AS paciente_apellidom,\n"
                + " AA.genero AS paciente_sexo,to_char(AA.fecha_nacimiento,'DD/MM/YYYY')as paciente_fecha_nac, \n"
                + " AA.direccion AS  paciente_direccion,\n"
                + " AA.contacto1 AS paciente_telefono1,AA.contacto2 AS  paciente_telefono2, \n"
                + " AA.id_comuna AS comuna_codigo,to_char(AA.fecha_registro ,'DD/MM/YYYY HH24:MI:SS') as paciente_fecha_creacion,\n"
                + " AA.procedencia as paciente_procedencia, \n"
                + " aa.id_puebloorigen as  paciente_pueblo, \n"
                + "BB.nombre as codigo_fonasa_prevision_paciente ,AA.provision as id_prevision_paciente, \n"
                + "AA.nombre as tramo_prevision_paciente, \n"
                + " \n"
                + "   bb.nombre as codigo_fonasa_descripcion, aa.email as paciente_mail \n"
                + "  FROM  agenda.paciente AA \n"
                + "    JOIN agenda.prevision BB  \n"
                + " ON (AA.provision =BB.id_prevision) \n"
                + "  JOIN agenda.tramo ON  \n"
                + " (AA.tramo=tramo.id) \n"
                + "  where  rut='" + rut_paciente + "' ");
        this.cnn.conectar();

        try {
            if (cnn.getRst().next()) {
                pac.setRut_paciente(cnn.getRst().getString("paciente_rut"));
                pac.setNombres_paciente(cnn.getRst().getString("paciente_nombres"));
                pac.setApellidom_paciente(cnn.getRst().getString("paciente_apellidom"));
                pac.setApellidop_paciente(cnn.getRst().getString("paciente_apellidop"));
                pac.setDireccion(cnn.getRst().getString("paciente_direccion"));
                pac.setTelefono1(cnn.getRst().getString("paciente_telefono1"));
                pac.setTelefono2(cnn.getRst().getString("paciente_telefono2"));
                pac.setFecha_nac(cnn.getRst().getString("paciente_fecha_nac"));
                //pac.setDv(cnn.getRst().getString("paciente_rut").substring(pac.getRut_paciente().length() - 1, pac.getRut_paciente().length()));
                pac.setSexo(cnn.getRst().getInt("paciente_sexo"));
                if (pac.getSexo() == 2) {
                    pac.setSexo_descri("MASCULINO");
                } else if (pac.getSexo() == 1) {
                    pac.setSexo_descri("FEMENINO");
                }
                pac.setComuna_codigo(cnn.getRst().getInt("comuna_codigo"));
                pac.setPueblo(cnn.getRst().getInt("paciente_pueblo"));

                pac.setId_prevision(cnn.getRst().getInt("id_prevision_paciente"));
                pac.setTramo_prevision(cnn.getRst().getString("tramo_prevision_paciente"));
                pac.setCodigo_fonasa(cnn.getRst().getString("codigo_fonasa_prevision_paciente"));
                pac.setCodigo_fonasa_descripcion(cnn.getRst().getString("codigo_fonasa_descripcion"));
                pac.setProcedencia(cnn.getRst().getInt("paciente_procedencia"));
                pac.setMail(cnn.getRst().getString("paciente_mail"));
                if (pac.getMail() == null) {
                    pac.setMail("");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.cnn.cerrarConexion();
        return pac;
    }

    public GradoCRD getGradoCRD(int id_ambito_crd, int valor) {
        GradoCRD gra = null;
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select descripcion_grado_crd from schema_uo.grado_crd  where id_ambito_crd=" + id_ambito_crd + " and puntaje_grado_crd=" + valor);
        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                gra = new GradoCRD();
                gra.setDescripcion_grado_crd(cnn.getRst().getString("descripcion_grado_crd"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();
        return (gra);

    }

    public AmbitoCRD getAmbitoCRD(int id_ambito_crd) {
        AmbitoCRD amb = null;
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select * from schema_uo.ambito_crd A where A.id_ambito_crd=" + id_ambito_crd);
        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                amb = new AmbitoCRD();
                amb.setDescripcion_ambito_crd(cnn.getRst().getString("descripcion_ambito_crd"));
                amb.setAbre_ambito_crd(cnn.getRst().getString("abre_ambito_crd"));
                amb.setTipo_ambito_crd(cnn.getRst().getInt("tipo_ambito_crd"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();

        return (amb);

    }

    /*new code*/
    public ArrayList lista_grilla_camas() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT to_char(D.fecha_duo,'DD/MM/YYYY') as fecha_duo ,D.hora_duo,\n"
                + "                            C.id_cama,C.id_sala,schema_uo.sala.descripcion_sala ,C.descripcion_cama,\n"
                + "                            D.id_duo,D.rut_paciente,D.id_categorizacion,D.estado_duo,  \n"
                + "                            nombre as paciente_nombres,apellido_paterno as paciente_apellidop,apellido_moderno  as paciente_apellidom ,\n"
                + "                            fecha_hora_alta_adm_duo,fecha_hora_alta_med_duo,\n"
                + "                            D.duo_tiene_enfermeria, (SELECT BB.cat_visita_categorizacion\n"
                + "                            FROM schema_uo.visita AA \n"
                + "                            JOIN schema_uo.visita_categorizacion BB ON\n"
                + "                            (AA.id_visita_categorizacion=BB.id_visita_categorizacion)\n"
                + "                            where id_duo=D.id_duo order by AA.fecha_hora_visita DESC limit 1) as ultima_cat,\n"
                + "                            EXTRACT(DAY FROM (CURRENT_DATE+CURRENT_TIME)-(d.fecha_duo+d.hora_duo)) as dias_cama\n"
                + "                            FROM schema_uo.cama C left JOIN schema_uo.duo D ON D.id_cama = C.id_cama\n"
                + "                            and (D.estado_duo=2 or D.estado_duo=1 or D.estado_duo=3 or D.estado_duo=21)\n"
                + "                            left JOIN schema_uo.sala  \n"
                + "                            ON (schema_uo.sala.id_sala=C.id_sala) \n"
                + "                            left JOIN agenda.paciente  \n"
                + "                            ON (agenda.paciente.rut=D.rut_paciente)\n"
                + "                            where C.id_sala in (11,12,25) and C.estado_cama=1 order by schema_uo.sala.posicion ,c.\"posicionCama\"");
        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                cDuo duo = new cDuo();
                duo.setRut_paciente(cnn.getRst().getString("rut_paciente"));
                duo.setNombres_paciente(cnn.getRst().getString("paciente_nombres"));
                duo.setApellidop_paciente(cnn.getRst().getString("paciente_apellidop"));
                duo.setApellidom_paciente(cnn.getRst().getString("paciente_apellidom"));
                duo.setFecha_duo(cnn.getRst().getString("fecha_duo"));
                duo.setHora_duo(cnn.getRst().getString("hora_duo"));
                duo.setId_duo(cnn.getRst().getInt("id_duo"));
                duo.setEstado_duo(cnn.getRst().getInt("estado_duo"));
                duo.setTiene_enfermeria(cnn.getRst().getInt("duo_tiene_enfermeria"));
                duo.setCama(cnn.getRst().getInt("id_cama"));
                duo.setCama_descripcion(cnn.getRst().getString("descripcion_cama"));
                duo.setSala(cnn.getRst().getInt("id_sala"));
                duo.setSala_descripcion(cnn.getRst().getString("descripcion_sala"));
                duo.setUltima_clasificacion(cnn.getRst().getString("ultima_cat") + "");
                if (duo.getUltima_clasificacion().equals("null")) {
                    duo.setUltima_clasificacion("P");
                } else {
                    duo.setUltima_clasificacion(cnn.getRst().getString("ultima_cat") + "");
                }
                duo.setDias_cama(cnn.getRst().getInt("dias_cama"));

                duo.setFecha_hora_alta_adm_duo(cnn.getRst().getString("fecha_hora_alta_adm_duo"));
                duo.setFecha_hora_alta_med_duo(cnn.getRst().getString("fecha_hora_alta_med_duo"));

                if (duo.getFecha_hora_alta_adm_duo() == null) {
                    duo.setFecha_hora_alta_adm_duo("---");
                }

                if (duo.getFecha_hora_alta_med_duo() == null) {
                    duo.setFecha_hora_alta_med_duo("---");
                }

                lista.add(duo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();
        return lista;
    }

    /*new code*/
    public ArrayList lista_documentos_paciente(String rut_paciente) {
        // este metodo sirve para ver si un paciente tiene un duo activo en la unidad y si esta registradoen sistema
        /*DUDA CON ESTE METODO; PIENSO QUE DEBERIA FILTRAR POR EL NUMERO DE DUO 25-05-2012 */
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        /*  FIN 23082013
         this.cnn.setSentenciaSQL("SELECT D.id_duo,D.estado_duo,to_char(D.fecha_duo,'DD/MM/YYYY')as fecha_duo ,E.id_epicrisis,"
         + " to_char(E.fecha_epicrisis,'DD/MM/YYYY')as fecha_epicrisis ,E.hora_epicrisis "
         + " FROM schema_uo.duo D LEFT JOIN schema_uo.epicrisis E "
         + " ON D.id_duo=E.id_duo where D.rut_paciente='" + rut_paciente + "' and estado_duo NOT IN (99) ORDER BY D.id_duo desc");
         23082013 */
        this.cnn.setSentenciaSQL("SELECT D.id_duo,D.estado_duo,to_char(D.fecha_duo,'DD/MM/YYYY')as fecha_duo ,E.id_epicrisis, \n"
                + "                 to_char(E.fecha_epicrisis,'DD/MM/YYYY')as fecha_epicrisis ,E.hora_epicrisis , \n"
                + "                 p.provision as id_prevision,t.nombre as tramo_prevision_paciente, \n"
                + "                 p2.nombre as codigo_fonasa_descripcion \n"
                + "                 FROM schema_uo.duo D  \n"
                + "                 JOIN agenda.paciente p on d.rut_paciente = p.rut\n"
                + "                 join agenda.prevision p2 on p2.id_prevision = p.provision\n"
                + "                 join agenda.tramo t on t.id = p.tramo"
                + "                 LEFT JOIN schema_uo.epicrisis E ON D.id_duo=E.id_duo \n"
                + "                 \n"
                + "                WHERE D.rut_paciente='" + rut_paciente + "'  \n"
                + "                 and estado_duo NOT IN (99) ORDER BY D.id_duo desc");

        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                cEpicrisis epi = new cEpicrisis();
                epi.setId_duo(cnn.getRst().getInt("id_duo"));
                epi.setEstado_duo(cnn.getRst().getInt("estado_duo"));
                epi.setId_epicrisis(cnn.getRst().getInt("id_epicrisis"));
                epi.setFecha_epicrisis(cnn.getRst().getString("fecha_epicrisis"));
                epi.setFecha_duo(cnn.getRst().getString("fecha_duo"));

                epi.setPrevision(cnn.getRst().getInt("id_prevision"));
                epi.setPrevision_descri(cnn.getRst().getString("codigo_fonasa_descripcion"));

                if (epi.getPrais() == 1) {
                    epi.setPrais_descri("PRAIS");
                } else {
                    epi.setPrais_descri("");
                }

                epi.setTramo(cnn.getRst().getString("tramo_prevision_paciente"));
                lista.add(epi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_documentos_paciente_suam(String parametro, int modo) {
        String campo = "";
        if (modo == 1) {
            campo = "das_paciente";
        } else if (modo == 2) {
            campo = "dau_id";
        } else if (modo == 3) {
            campo = "dau_nn_id";
        } else {
            campo = "das_paciente";
        }

        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  das_id, dau_id,dau_nn_id,  das_paciente, das_estado,   to_char(das_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS') as das_fecha_ingreso,  das_camilla, das_derivador, das_medico,  das_usuario, das_ip, das_indicacion_destino,  das_indicacion_usuario,das_indicacion_fecha_ingreso, das_indicacion_ip,  cam.cam_descripcion  FROM schema_suam.das da JOIN schema_suam.camilla cam ON(da.das_camilla=cam.cam_id)  WHERE " + campo + "='" + parametro + "' and das_estado NOT IN (99);");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cDas das = new cDas();
                das.setRut_paciente(this.cnn.getRst().getString("das_paciente"));
                das.setDau_nn_id(this.cnn.getRst().getInt("dau_nn_id"));
                das.setDau_id(this.cnn.getRst().getInt("dau_id"));
                das.setId_das(this.cnn.getRst().getInt("das_id"));
                das.setCamilla(this.cnn.getRst().getInt("das_camilla"));
                das.setCamilla_descri(this.cnn.getRst().getString("cam_descripcion"));
                das.setFecha_ingreso(this.cnn.getRst().getString("das_fecha_ingreso"));
                lista.add(das);
            }
        } catch (SQLException var6) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var6);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public cEpicrisis obtiene_epicrisis(String id_epicrisis) {
        cEpicrisis aux = new cEpicrisis();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  id_epicrisis,resumen_epicrisis,examen_epicrisis, \n"
                + " diagnostico_epicrisis,indicacion_epicrisis, \n"
                + "  to_char(fecha_epicrisis,'DD/MM/YYYY') as fecha_epicrisis,\n"
                + "  hora_epicrisis,  BB.rut_usuario,AA.id_duo,fecha_hora_epicrisis,  BB.nombre_usuario, \n"
                + "  BB.apellidop_usuario,BB.apellidom_usuario,  nombre,\n"
                + "  apellido_paterno,apellido_moderno  FROM schema_uo.epicrisis AA   \n"
                + "  JOIN schema_uo.usuario BB ON   (AA.rut_usuario=BB.rut_usuario)  \n"
                + "  JOIN schema_uo.duo ON  (AA.id_duo=schema_uo.duo.id_duo)  \n"
                + "  JOIN agenda.paciente ON \n"
                + "  (schema_uo.duo.rut_paciente=agenda.paciente.rut)  where id_epicrisis='" + id_epicrisis + "';");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        return aux;
    }

    public ArrayList lista_comuna() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT   comuna_codigo, AA.provincia_codigo,BB.region_codigo,comuna_descripcion,  BB.provincia_descripcion  ,region_descripcion  FROM  schemaoirs.comuna AA JOIN schemaoirs.provincia BB ON   (AA.provincia_codigo=BB.provincia_codigo)  JOIN schemaoirs.region ON (BB.region_codigo=schemaoirs.region.region_codigo)");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cComuna aux = new cComuna();
                aux.setComuna_codigo(this.cnn.getRst().getString("comuna_codigo"));
                aux.setComuna_descripcion(this.cnn.getRst().getString("comuna_descripcion"));
                aux.setProvincia_codigo(this.cnn.getRst().getString("provincia_codigo"));
                aux.setProvincia_descripcion(this.cnn.getRst().getString("provincia_descripcion"));
                aux.setRegion_codigo(this.cnn.getRst().getString("region_codigo"));
                aux.setRegion_descripcion(this.cnn.getRst().getString("region_descripcion"));
                lista.add(aux);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_cama_desocupada(String salas) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT C.id_cama,C.descripcion_cama,D.id_duo,S.descripcion_sala   FROM schema_uo.sala S,schema_uo.cama C left JOIN schema_uo.duo D  ON D.id_cama = C.id_cama and D.estado_duo NOT IN (4,99)  where D.id_duo isnull and S.id_sala=C.id_sala and C.estado_cama=1   and S.id_area_clinica IN (1) and S.id_sala IN (" + salas + ")  order by C.id_cama");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cCama aux = new cCama();
                aux.setId_cama(this.cnn.getRst().getInt("id_cama"));
                aux.setDescripcion_cama(this.cnn.getRst().getString("descripcion_cama"));
                aux.setDescripcion_sala(this.cnn.getRst().getString("descripcion_sala"));
                lista.add(aux);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_pueblo() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  pue_id,pue_descripcion, pue_fecha_ingreso  FROM  schemaoirs.pueblo_originario where pue_estado='1';");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cPueblo aux = new cPueblo();
                aux.setDescripcion_pueblo(this.cnn.getRst().getString("pue_descripcion"));
                aux.setId_pueblo(this.cnn.getRst().getInt("pue_id"));
                lista.add(aux);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_consultorio_pertenecia() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT \n"
                + "  id_servicio_salud as con_id,\n"
                + " tipo||' '|| nombre_servicio_salud as con_descripcion\n"
                + "\n"
                + "FROM \n"
                + "  agenda.procedencia where estado_servicio_salud = 1  ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cConsultorio aux = new cConsultorio();
                aux.setDescripcion(this.cnn.getRst().getString("con_descripcion"));
                aux.setId(this.cnn.getRst().getInt("con_id"));
                lista.add(aux);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList getAdmissionHospitalization(String init, String end) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select d.rut_paciente,\n"
                + "                (p.nombre || ' '|| p.apellido_paterno ||' '|| p.apellido_moderno) as nombre_paciente,\n"
                + "                EXTRACT(YEAR from age(CURRENT_TIMESTAMP ,p.fecha_registro))AS paciente_edad,\n"
                + "                case when p.genero = 1 then 'Femenino' else 'Masculino' end as paciente_sexo,\n"
                + "                (SELECT PP.con_descripcion from schemaoirs.consultorio_pertenencia PP WHERE   PP.con_id=p.procedencia)as cesfam,\n"
                + "                (select dr.descripcion_derivador from schema_uo.derivador dr where dr.id_derivador= p.procedencia) as origen,\n"
                + "                d.rut_usuario,\n"
                + "                to_char(fecha_duo,'DD/MM/YYYY')as fecha_duo,\n"
                + "                to_char(d.hora_duo,'HH24:MI') as hora_duo, \n"
                + "                d.rut_usuario_ing_med,\n"
                + "                to_char(d.fecha_hora_ing_med,'DD/MM/YYYY') as fecha_ingreso_medico,\n"
                + "                to_char(d.fecha_hora_ing_med,'HH24:MI') as hora_ingreso_medico,\n"
                + "                (SELECT LL.rut_usuario_ing_enfermeria FROM schema_uo.ing_enfermeria LL    where LL.id_duo_ing_enfermeria=d.id_duo limit 1) as rut_ing_enf,\n"
                + "                (SELECT to_char(LL.fecha_hora_ing_enfermeria,'DD/MM/YYYY') as fecha_hora_ing_enfermeria FROM schema_uo.ing_enfermeria LL    where LL.id_duo_ing_enfermeria=d.id_duo limit 1) as fecha_ing_enf,\n"
                + "                (SELECT to_char(LL.fecha_hora_ing_enfermeria,'HH24:MI') as fecha_hora_ing_enfermeria FROM schema_uo.ing_enfermeria LL    where LL.id_duo_ing_enfermeria=d.id_duo limit 1) as hora_ing_enf,  \n"
                + "                ( SELECT BB.cat_visita_categorizacion     FROM schema_uo.visita AA      JOIN schema_uo.visita_categorizacion BB ON     (AA.id_visita_categorizacion=BB.id_visita_categorizacion)      where id_duo=d.id_duo order by AA.fecha_hora_visita ASC limit 1 ) as primera_cat,\n"
                + "                ( SELECT to_char(aa.fecha_visita,'DD/MM/YYYY')     FROM schema_uo.visita AA      JOIN schema_uo.visita_categorizacion BB ON     (AA.id_visita_categorizacion=BB.id_visita_categorizacion)      where id_duo=d.id_duo order by AA.fecha_hora_visita ASC limit 1 ) as fecha_cat,\n"
                + "                ( SELECT to_char(aa.hora_visita,'HH24:MI')    FROM schema_uo.visita AA      JOIN schema_uo.visita_categorizacion BB ON     (AA.id_visita_categorizacion=BB.id_visita_categorizacion)      where id_duo=d.id_duo order by AA.fecha_hora_visita ASC limit 1 ) as hora_cat\n"
                + "                 from schema_uo.duo d inner join agenda.paciente p on p.rut = d.rut_paciente where  d.fecha_duo BETWEEN '" + init + "' and '" + end + "' ");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cDuo admission = new cDuo();
                admission.setRut_paciente(this.cnn.getRst().getString("rut_paciente"));
                admission.setNombres_paciente(this.cnn.getRst().getString("nombre_paciente"));
                admission.setEdad(this.cnn.getRst().getString("paciente_edad"));
                admission.setSexo_descri(this.cnn.getRst().getString("paciente_sexo"));
                admission.setConsultorio_descri(this.cnn.getRst().getString("cesfam"));
                admission.setDerivador_descripcion(this.cnn.getRst().getString("origen"));
                admission.setRut_usuario(this.cnn.getRst().getString("rut_usuario"));
                admission.setFecha_duo(this.cnn.getRst().getString("fecha_duo"));
                admission.setHora_duo(this.cnn.getRst().getString("hora_duo"));
                admission.setRut_usuario_ing_med(this.cnn.getRst().getString("rut_usuario_ing_med"));
                admission.setFecha_hora_ing_med(this.cnn.getRst().getString("fecha_ingreso_medico"));
                admission.setDireccion(this.cnn.getRst().getString("hora_ingreso_medico"));
                admission.setRut_sdv(this.cnn.getRst().getString("rut_ing_enf"));
                admission.setFecha_hora_ing_enf(this.cnn.getRst().getString("fecha_ing_enf"));
                admission.setComuna_descri(this.cnn.getRst().getString("hora_ing_enf"));
                admission.setCategorizacion_descripcion(this.cnn.getRst().getString("primera_cat"));
                admission.setFecha_creacion(this.cnn.getRst().getString("fecha_cat"));
                admission.setFecha_hora_alta_adm_duo(this.cnn.getRst().getString("hora_cat"));
                lista.add(admission);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_derivador() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT    id_derivador, descripcion_derivador, estado_crd_deriador  FROM  schema_uo.derivador where estado_derivador='1' ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cConsultorio aux = new cConsultorio();
                aux.setDescripcion(this.cnn.getRst().getString("descripcion_derivador"));
                aux.setId(this.cnn.getRst().getInt("id_derivador"));
                aux.setEstado_crd(this.cnn.getRst().getInt("estado_crd_deriador"));
                lista.add(aux);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_hospital() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  hos_id, hos_descripcion\n FROM  schema_uo.hospital WHERE hos_estado='1' ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cConsultorio aux = new cConsultorio();
                aux.setDescripcion(this.cnn.getRst().getString("hos_descripcion"));
                aux.setId(this.cnn.getRst().getInt("hos_id"));
                lista.add(aux);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_destino() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  id_destino,  descripcion_destino, estado_destino  FROM   schema_uo.destino where estado_destino='1' ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cConsultorio aux = new cConsultorio();
                aux.setDescripcion(this.cnn.getRst().getString("descripcion_destino"));
                aux.setId(this.cnn.getRst().getInt("id_destino"));
                aux.setEstado_crd(this.cnn.getRst().getInt("estado_destino"));
                lista.add(aux);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_categorizacion() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT id_categorizacion,descripcion_categorizacion  FROM  schema_uo.categorizacion where  estado_categorizacion='1' order by id_categorizacion;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cCategorizacion cat = new cCategorizacion();
                cat.setDescripcion(this.cnn.getRst().getString("descripcion_categorizacion"));
                cat.setId(this.cnn.getRst().getInt("id_categorizacion"));
                lista.add(cat);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_documentos() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT id_documento, descripcion_documento  FROM  schema_uo.documento where estado_documento='1' ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cDocumento doc = new cDocumento();
                doc.setDescripcion(this.cnn.getRst().getString("descripcion_documento"));
                doc.setId(this.cnn.getRst().getInt("id_documento"));
                lista.add(doc);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_prestacion_trazadora() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  pre_id,pre_nombre  FROM  schema_uo.prestacion where  pre_estado=1 order by pre_nombre;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cEnfermedad enf = new cEnfermedad();
                enf.setDescripcion(this.cnn.getRst().getString("pre_nombre"));
                enf.setId(this.cnn.getRst().getInt("pre_id"));
                lista.add(enf);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_enfermedad_cronica() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  id_enfermedad_cro,descripcion_enfermedad_cro  FROM  schema_uo.enfermedad_cro AA where  estado_enfermedad_cro='1'   order by descripcion_enfermedad_cro ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cEnfermedad enf = new cEnfermedad();
                enf.setDescripcion(this.cnn.getRst().getString("descripcion_enfermedad_cro"));
                enf.setId(this.cnn.getRst().getInt("id_enfermedad_cro"));
                lista.add(enf);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_riesgo_dependendencia() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select * from schema_uo.ambito_crd ACRD where ACRD.estado_ambito_crd=1 order by ACRD.id_ambito_crd");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cCatRiesgoDependencia cat = new cCatRiesgoDependencia();
                cat.setId_ambito_crd(this.cnn.getRst().getInt("id_ambito_crd"));
                cat.setDescripcion_ambito_crd(this.cnn.getRst().getString("descripcion_ambito_crd"));
                cat.setTipo_ambito_crd(this.cnn.getRst().getInt("tipo_ambito_crd"));
                cat.setAbre_ambito_crd(this.cnn.getRst().getString("abre_ambito_crd"));
                lista.add(cat);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public cDuo obtiene_dato_segun_duo(int id_duo) {
        cDuo duo = new cDuo();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT id_duo,id_cama  FROM   schema_uo.duo where id_duo='" + id_duo + "' ;");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                duo.setId_duo(this.cnn.getRst().getInt("id_duo"));
                duo.setCama(this.cnn.getRst().getInt("id_cama"));
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return duo;
    }

    public String obtiene_fecha_hora() {
        String hora = "";
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT to_char(current_timestamp,'DD/MM/YYYY HH24:MI:SS')as hora FROM schema_uo.duo limit 1 ;");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                hora = this.cnn.getRst().getString("hora");
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return hora;
    }

    /*new code*/
    public ArrayList listadelDia() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT c.descripcion_cama, COALESCE( D.rut_paciente, '') as rut_paciente,COALESCE(initcap(p.apellido_paterno),'') as  paciente_apellidop ,\n"
                + "               COALESCE(initcap(p.apellido_moderno),'')as paciente_apellidom,COALESCE( initcap(p.nombre),'') as paciente_nombres,\n"
                + "                                     COALESCE(to_char(D.fecha_duo,'DD/MM/YYYY'),'') as fecha_duo ,COALESCE(D.hora_duo,'00:00 ') as hora_duo, \n"
                + "                                           \n"
                + "                                              EXTRACT(DAY FROM (CURRENT_DATE+CURRENT_TIME)-(d.fecha_duo+d.hora_duo))+1 as dias_cama \n"
                + "                                          FROM schema_uo.cama C left JOIN schema_uo.duo D ON D.id_cama = C.id_cama \n"
                + "                                           and D.estado_duo in (2,1, 3, 21)\n"
                + "                                             left JOIN schema_uo.sala  \n"
                + "                                           ON (schema_uo.sala.id_sala=C.id_sala) \n"
                + "                                              left JOIN agenda.paciente  p  \n"
                + "                                          ON (p.rut=D.rut_paciente) \n"
                + "                                               where C.id_sala in (11,12) and C.estado_cama=1 order by schema_uo.sala.posicion\n"
                + "  \n"
                + "   ");
        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                cDuo duo = new cDuo();
                duo.setRut_paciente(cnn.getRst().getString("rut_paciente"));
                duo.setNombres_paciente(cnn.getRst().getString("paciente_nombres"));
                duo.setApellidop_paciente(cnn.getRst().getString("paciente_apellidop"));
                duo.setApellidom_paciente(cnn.getRst().getString("paciente_apellidom"));
                duo.setFecha_duo(cnn.getRst().getString("fecha_duo"));
                duo.setHora_duo(cnn.getRst().getString("hora_duo"));

                duo.setCama_descripcion(cnn.getRst().getString("descripcion_cama"));

                duo.setDias_cama(cnn.getRst().getInt("dias_cama"));

                lista.add(duo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();
        return lista;
    }

    public cReceta bucarindicacion(int idindicaciones) {
        cReceta r = new cReceta();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT \n"
                + "  idindicaciones,\n"
                + "  idduo,\n"
                + "  reposo,\n"
                + "  regimen,\n"
                + "  otras_indicaciones,\n"
                + "  fecha,coalesce(control_signos, '') as control_signos, coalesce(aislamiento, '') as aislamiento, coalesce(alergias, '') as alergias, coalesce(diagnostico, '') as diagnostico, coalesce(contencion, '') as contencion,\n"
                + "     coalesce(imagenes, '') as imagenes, coalesce(otros, '') as otros, coalesce(indicaciones_enfermeria, '') as indicaciones_enfermeria, coalesce(indicaciones_nutricionista, '') as indicaciones_nutricionista, \n"
                + "   coalesce(indicaciones_kinesiologo, '') as indicaciones_kinesiologo, coalesce(indicaciones_otros, '') as indicaciones_otros,\n"
                + "usu.nombre_usuario ||' '|| usu.apellidop_usuario ||' '||usu.apellidom_usuario as usuario,"
                + "  to_char(fecha, 'DD/MM/yyyy'::text) AS fechastring,\n"
                + "    to_char(fecha, 'HH24:MI:SS'::text) AS hora\n"
                + "FROM \n"
                + "  schema_uo.indicaciones ind\n"
                + "  JOIN schema_uo.usuario usu ON ind.usuario = usu.rut_usuario where idindicaciones = " + idindicaciones + "\n"
                + "  order by hora desc limit 1 ");

        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {

                r.setId_duo(cnn.getRst().getInt("idindicaciones"));
                r.setRegimen(cnn.getRst().getString("regimen"));
                r.setReposo(cnn.getRst().getString("reposo"));
                r.setIndicacion(cnn.getRst().getString("otras_indicaciones"));
                r.setNombre_usuario(cnn.getRst().getString("usuario"));
                r.setFechadate(cnn.getRst().getDate("fecha"));
                r.setFecha(cnn.getRst().getString("fechastring"));
                r.setHgt(cnn.getRst().getString("hora"));
                r.setDiagnostico(cnn.getRst().getString("diagnostico"));
                r.setControl_signos(cnn.getRst().getString("control_signos"));
                r.setAislamiento(cnn.getRst().getString("aislamiento"));
                r.setAlergias(cnn.getRst().getString("alergias"));
                r.setContencion(cnn.getRst().getString("contencion"));
                r.setImagenes(cnn.getRst().getString("imagenes"));
                r.setOtros(cnn.getRst().getString("otros"));
                r.setIndicaciones_enfermeria(cnn.getRst().getString("indicaciones_enfermeria"));
                r.setIndicaciones_nutricionista(cnn.getRst().getString("indicaciones_nutricionista"));
                r.setIndicaciones_kinesiologo(cnn.getRst().getString("indicaciones_kinesiologo"));
                r.setIndicaciones_otros(cnn.getRst().getString("indicaciones_otros"));
                r.setId_receta_detalle(cnn.getRst().getInt("idduo"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();

        return r;
    }

    /*new code*/
    public Vector<prevision> buscarPrevicion() {
        Vector<prevision> p = new Vector();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT \n"
                + "  id_prevision,\n"
                + "  nombre\n"
                + "  FROM \n"
                + "  agenda.prevision where estatus = 1 ;");
        this.cnn.conectar();
        try {
            while (this.cnn.getRst().next()) {
                prevision pre = new prevision();
                pre.setId_prevision(this.cnn.getRst().getInt("id_prevision"));
                pre.setNombre(this.cnn.getRst().getString("nombre"));
                p.add(pre);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.cnn.cerrarConexion();
        }
        return p;
    }

    /*new code */
    public Vector<prevision> buscarTramo() {
        Vector<prevision> p = new Vector();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT id,nombre FROM agenda.tramo  where status = 1 order by nombre");
        this.cnn.conectar();
        try {
            while (this.cnn.getRst().next()) {
                prevision pre = new prevision();
                pre.setId_prevision(this.cnn.getRst().getInt("id"));
                pre.setNombre(this.cnn.getRst().getString("nombre"));
                p.add(pre);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.cnn.cerrarConexion();
        }
        return p;
    }

    /*new code */
    public boolean buscarpaciente(String rut) {
        boolean tengo = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT * "
                + "  FROM  agenda.paciente AA "
                + "  where  rut='" + rut + "'  ");

        this.cnn.conectar();
        try {
            if (cnn.getRst().next()) {
                tengo = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();

        return tengo;
    }

    /*new code*/
    public cPaciente buscarpacienteporrut(String rut) {
        cPaciente p = new cPaciente();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  to_char(fecha_nacimiento,'DD/MM/YYYY') as nacimiento ,\n"
                + " case when  provision= 1 then 'FONASA'||' '|| t.nombre  else case when provision = 2 then 'Isapre' else case when provision = 3 then 'Prais' else 'Sin Prevision' end  end end as prevension ,\n"
                + "  rut,\n"
                + "  p.nombre,\n"
                + "  apellido_paterno,\n"
                + "  apellido_moderno,\n"
                + "  fecha_nacimiento,\n"
                + "  COALESCE(direccion,'') as direccion,\n"
                + "  COALESCE (email,'') as email,\n"
                + "  COALESCE (contacto1,'') as contacto1,\n"
                + "  COALESCE(contacto2,'') as contacto2 ,\n"
                + "  estatus,\n"
                + "  genero,\n"
                + "  fecha_registro,\n"
                + "  id_comuna,\n"
                + "  provision,\n"
                + "  tramo,\n"
                + "  procedencia,\n"
                + "  ipusuario,\n"
                + "  usuario,\n"
                + "  id_region,\n"
                + "  id_provincia, id_nacionalidad, COALESCE(nombresocial,' ') AS nombresocial FROM  agenda.paciente p\n"
                + "  inner join agenda.tramo t on t.id = p.tramo \n"
                + "  where upper(rut)=upper('" + rut + "') and estatus = 1;  ");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                p.setRut_paciente(this.cnn.getRst().getString("rut"));
                p.setNombres_paciente(this.cnn.getRst().getString("nombre"));
                p.setApellidop_paciente(this.cnn.getRst().getString("apellido_paterno"));
                p.setApellidom_paciente(this.cnn.getRst().getString("apellido_moderno"));
                String fecha = this.cnn.getRst().getString("nacimiento");
                Date fecha1 = null;
                try {
                    fecha1 = DeStringADate(fecha);
                } catch (ParseException ex) {
                    Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, null, ex);
                }
                p.setFechanacimiento(fecha1);
                p.setDireccion(this.cnn.getRst().getString("direccion"));
                p.setMail(this.cnn.getRst().getString("email"));
                p.setTelefono1(this.cnn.getRst().getString("contacto1"));
                p.setTelefono2(this.cnn.getRst().getString("contacto2"));
                p.setEstado_usuario(this.cnn.getRst().getInt("estatus"));
                p.setSexo(this.cnn.getRst().getInt("genero"));
                p.setFechacreacion(this.cnn.getRst().getDate("fecha_registro"));
                p.setComuna_codigo(this.cnn.getRst().getInt("id_comuna"));
                p.setId_prevision(this.cnn.getRst().getInt("provision"));
                p.setTramo(this.cnn.getRst().getInt("tramo"));
                p.setProcedencia(this.cnn.getRst().getInt("procedencia"));

                p.setVariable(this.cnn.getRst().getString("prevension"));
                p.setNacion(this.cnn.getRst().getInt("id_nacionalidad"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.cnn.cerrarConexion();
        }
        return p;
    }

    /*new code*/
    public Date DeStringADate(String fecha) throws java.text.ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String strFecha = fecha;
        Date fechaDate = null;
        fechaDate = formato.parse(strFecha);
        return fechaDate;
    }

    public Vector<String> buscarRecetadeIndicaciones(int indicaciones) {
        Vector<String> recetas = new Vector<String>();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select ir.cantidad ||' '|| i.ins_descripcion ||' en '|| um.uni_descripcion\n"
                + "                ||' cada '|| ir.frecuencia ||' horas durante '|| ir.duracion ||' dias , Via '||vm.descripcion_via_medicamento ||'. '||ir.indicaciones_especiales as receta \n"
                + "                 from  schema_uo.indicaciones_receta ir inner join\n"
                + "               schema_abastecimiento.unidad_medida um on ir.id_medida = um.uni_id\n"
                + "               join schema_abastecimiento.insumo  i on ir.id_medicamento = i.ins_id\n"
                + "               join schema_uo.via_medicamento vm on vm.id_via_medicamento = ir.id_via\n"
                + "                WHERE ins_tipo_clinico='1'  and ir.id_indicaciones=" + indicaciones + " ");

        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                String r = cnn.getRst().getString("receta");
                recetas.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();

        return recetas;
    }

    public Vector<String> buscarRecetadeIndicacionesDuo(int duo) {
        Vector<String> recetas = new Vector<String>();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select ir.cantidad ||' '|| i.ins_descripcion ||' en '|| um.uni_descripcion\n"
                + "                ||' cada '|| ir.frecuencia ||' horas durante '|| ir.duracion ||' dias , Via '||vm.descripcion_via_medicamento ||'. '||ir.indicaciones_especiales as receta \n"
                + "                 from  schema_uo.indicaciones_receta ir inner join\n"
                + "               schema_abastecimiento.unidad_medida um on ir.id_medida = um.uni_id\n"
                + "               join schema_abastecimiento.insumo  i on ir.id_medicamento = i.ins_id\n"
                + "               join schema_uo.via_medicamento vm on vm.id_via_medicamento = ir.id_via\n"
                + "               join  schema_uo.indicaciones ic on ic.idindicaciones= ir.id_indicaciones\n"
                + "                             WHERE ins_tipo_clinico='1' and ic.idduo= " + duo + " ");

        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                String r = cnn.getRst().getString("receta");
                recetas.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();

        return recetas;
    }

    /*acomodar*/
    public Vector<String> buscarSolicitudExamenesdeIndicaciones(int indicaciones) {
        Vector<String> recetas = new Vector<String>();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT e.nombre_examenes\n"
                + "  FROM schema_uo.solicitudexamenes_indicaciones sei\n"
                + "  inner join agenda.examenes e on e.id_examenes = sei.id_examenes where sei.idindicaciones=" + indicaciones + "; ");

        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                String r = cnn.getRst().getString("nombre_examenes");
                recetas.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();

        return recetas;
    }

    public Vector<String> buscarSolicitudExamenesdeIndicacionesDuo(int duo) {
        Vector<String> recetas = new Vector<String>();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT e.nombre_examenes\n"
                + "                 FROM schema_uo.solicitudexamenes_indicaciones sei\n"
                + "                 inner join agenda.examenes e on e.id_examenes = sei.id_examenes\n"
                + "                 join  schema_uo.indicaciones ic on ic.idindicaciones= sei.idindicaciones\n"
                + "               where ic.idduo =" + duo + " ");

        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                String r = cnn.getRst().getString("nombre_examenes");
                recetas.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();

        return recetas;
    }

    public cEpicrisis getEpicrisis(int idDuo) {
        cEpicrisis epi = null;
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select to_char(fecha_hora_epicrisis, 'dd/mm/yyyy HH24:MI:SS') as fecha , * from schema_uo.epicrisis e inner join schema_uo.usuario u on u.rut_usuario= e.rut_usuario  where id_duo=" + idDuo + "");
        this.cnn.conectar();
        try {

            while (cnn.getRst().next()) {

                epi = new cEpicrisis();
                epi.setRut_usuario(cnn.getRst().getString("rut_usuario"));
                epi.setApellidop_usuario(cnn.getRst().getString("apellidop_usuario"));
                epi.setApellidom_usuario(cnn.getRst().getString("apellidom_usuario"));
                epi.setNombre_usuario(cnn.getRst().getString("nombre_usuario"));
                epi.setDiagnostico_epicrisis(cnn.getRst().getString("diagnostico_epicrisis"));
                epi.setExamen_epicrisis(cnn.getRst().getString("examen_epicrisis"));
                epi.setHora_epicrisis(cnn.getRst().getString("hora_epicrisis"));
                epi.setId_duo(cnn.getRst().getInt("id_duo"));
                epi.setIndicacion_epicrisis(cnn.getRst().getString("indicacion_epicrisis"));
                epi.setResumen_epicrisis(cnn.getRst().getString("resumen_epicrisis"));
                epi.setFecha_epicrisis(cnn.getRst().getString("fecha"));
                epi.setMedicamentos_prescritos(cnn.getRst().getString("medicamentos_prescritos"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();

        return (epi);

    }

    public cIngreso_Medico Buscaringreso(int diduo) {
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT \n"
                + "  \n"
                + "  motivodeingreso,\n"
                + "  enfermedadactual,\n"
                + "  cardiovasculares,\n"
                + "  metabolicas,\n"
                + "  endocrinologicas,\n"
                + "  gastrointestinales,\n"
                + "  saludmetales,\n"
                + "  otras,\n"
                + "  quirurgicos,\n"
                + "  ginecoobstetrico,\n"
                + "  medicamentos,\n"
                + "  tabaco,\n"
                + "  ipa,\n"
                + "  alergias,\n"
                + "  alcohol,\n"
                + "  drogas,\n"
                + "  alimentacion,\n"
                + "  antecedentesspf,\n"
                + "  revisionporsistema,\n"
                + "  presionarterial,\n"
                + "  pam,\n"
                + "  pulso,\n"
                + "  peso,\n"
                + "  sat02,\n"
                + "  fio2,\n"
                + "  respiracion,\n"
                + "  talla,\n"
                + "  tauxilar,\n"
                + "  trectal,\n"
                + "  icm,\n"
                + "  posicion,\n"
                + "  exmental,\n"
                + "  pielyanexos,\n"
                + "  ganglios,\n"
                + "  carotideoderecho,\n"
                + "  braquialderecho,\n"
                + "  radialderecho,\n"
                + "  femoralderecho,\n"
                + "  popliteoderecho,\n"
                + "  tibiaderecho,\n"
                + "  pedioderecho,\n"
                + "  carotideoizquierda,\n"
                + "  braquializquierda,\n"
                + "  radializquierda,\n"
                + "  femoralizquierda,\n"
                + "  popliteoizquierda,\n"
                + "  tibiaizquierda,\n"
                + "  pedioizquierda,\n"
                + "  ojos,\n"
                + "  oidos,\n"
                + "  fosasnasales,\n"
                + "  cavidadoral,\n"
                + "  faringe,\n"
                + "  cuello,\n"
                + "  torax,\n"
                + "  corazon,\n"
                + "  pulmon,\n"
                + "  mamas,\n"
                + "  abdomen,\n"
                + "  extremidades,\n"
                + "  neurologico,\n"
                + "  patologiaaguda,\n"
                + "  comorbilidad,\n"
                + "  sindromesgeriatricos,\n"
                + "  planes,\n"
                + "  indicaciones,\n"
                + "  fecha_registro,\n"
                + "  usuario\n"
                + "FROM \n"
                + "  schema_uo.ingreso_medico WHERE id_duo = " + diduo + " ;");
        this.cnn.conectar();
        cIngreso_Medico im = new cIngreso_Medico();
        try {
            while (cnn.getRst().next()) {
                im.setAbdomen(cnn.getRst().getString("abdomen"));
                im.setAlcohol(cnn.getRst().getString("alcohol"));
                im.setAlergias(cnn.getRst().getString("alergias"));
                im.setAlimentacion(cnn.getRst().getString("alimentacion"));
                im.setAntecedentesspf(cnn.getRst().getString("antecedentesspf"));
                im.setBraquialderecho(cnn.getRst().getString("braquialderecho"));
                im.setBraquializquierda(cnn.getRst().getString("braquializquierda"));
                im.setCardiovasculares(cnn.getRst().getString("cardiovasculares"));
                im.setCarotideoderecho(cnn.getRst().getString("carotideoderecho"));
                im.setCarotideoizquierda(cnn.getRst().getString("carotideoizquierda"));
                im.setCavidadoral(cnn.getRst().getString("cavidadoral"));
                im.setComorbilidad(cnn.getRst().getString("comorbilidad"));
                im.setCorazon(cnn.getRst().getString("corazon"));
                im.setCuello(cnn.getRst().getString("cuello"));
                im.setDrogas(cnn.getRst().getString("drogas"));
                im.setEndocrinologicas(cnn.getRst().getString("endocrinologicas"));
                im.setEnfermedadactual(cnn.getRst().getString("enfermedadactual"));
                im.setExmental(cnn.getRst().getString("exmental"));
                im.setExtremidades(cnn.getRst().getString("extremidades"));
                im.setFaringe(cnn.getRst().getString("faringe"));
                im.setFemoralderecho(cnn.getRst().getString("femoralderecho"));
                im.setFemoralizquierda(cnn.getRst().getString("femoralizquierda"));
                im.setFio2(cnn.getRst().getString("fio2"));
                im.setFosasnasales(cnn.getRst().getString("fosasnasales"));
                im.setGanglios(cnn.getRst().getString("ganglios"));
                im.setGastrointestinales(cnn.getRst().getString("gastrointestinales"));
                im.setGinecoobstetrico(cnn.getRst().getString("ginecoobstetrico"));
                im.setIcm(cnn.getRst().getString("icm"));
                im.setIndicaciones(cnn.getRst().getString("indicaciones"));
                im.setIpa(cnn.getRst().getString("ipa"));
                im.setMamas(cnn.getRst().getString("mamas"));
                im.setMedicamentos(cnn.getRst().getString("medicamentos"));
                im.setMetabolicas(cnn.getRst().getString("metabolicas"));
                im.setMotivodeingreso(cnn.getRst().getString("motivodeingreso"));
                im.setNeurologico(cnn.getRst().getString("neurologico"));
                im.setOidos(cnn.getRst().getString("oidos"));
                im.setOjos(cnn.getRst().getString("ojos"));
                im.setOtras(cnn.getRst().getString("otras"));
                im.setPam(cnn.getRst().getString("pam"));
                im.setPatologiaaguda(cnn.getRst().getString("patologiaaguda"));
                im.setPedioderecho(cnn.getRst().getString("pedioderecho"));
                im.setPedioizquierda(cnn.getRst().getString("pedioizquierda"));
                im.setPeso(cnn.getRst().getString("peso"));
                im.setPielyanexos(cnn.getRst().getString("pielyanexos"));
                im.setPlanes(cnn.getRst().getString("planes"));
                im.setPopliteoderecho(cnn.getRst().getString("popliteoderecho"));
                im.setPopliteoizquierda(cnn.getRst().getString("popliteoizquierda"));
                im.setPosicion(cnn.getRst().getString("posicion"));
                im.setPresionarterial(cnn.getRst().getString("presionarterial"));
                im.setPulmon(cnn.getRst().getString("pulmon"));
                im.setPulso(cnn.getRst().getString("pulso"));
                im.setQuirurgicos(cnn.getRst().getString("quirurgicos"));
                im.setRadialderecho(cnn.getRst().getString("radialderecho"));
                im.setRadializquierda(cnn.getRst().getString("radializquierda"));
                im.setRespiracion(cnn.getRst().getString("respiracion"));
                im.setRevisionporsistema(cnn.getRst().getString("revisionporsistema"));
                im.setTabaco(cnn.getRst().getString("tabaco"));
                im.setTalla(cnn.getRst().getString("talla"));
                im.setTauxilar(cnn.getRst().getString("tauxilar"));
                im.setTibiaderecho(cnn.getRst().getString("tibiaderecho"));
                im.setTibiaizquierda(cnn.getRst().getString("tibiaizquierda"));
                im.setTorax(cnn.getRst().getString("torax"));
                im.setTrectal(cnn.getRst().getString("trectal"));
                im.setSaludmetales(cnn.getRst().getString("saludmetales"));
                im.setSat02(cnn.getRst().getString("sat02"));
                im.setSindromesgeriátricos(cnn.getRst().getString("sindromesgeriátricos"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return im;
    }

    public String tiempo_desde_alta_med(int duo) {
        String tiempo = "";
        int dia = 0;
        int hora = 0;
        int minuto = 0;
        int segundo = 0;

        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("  Select "
                + " EXTRACT(MINUTE FROM CURRENT_TIMESTAMP-fecha_hora_epicrisis) as resta_minuto,  "
                + " EXTRACT(HOUR FROM CURRENT_TIMESTAMP-fecha_hora_epicrisis) as resta_hora,  "
                + " EXTRACT(DAY FROM CURRENT_TIMESTAMP-fecha_hora_epicrisis) as resta_dia,"
                + " EXTRACT(SECOND FROM CURRENT_TIMESTAMP-fecha_hora_epicrisis) as resta_segundo"
                + " from schema_uo.epicrisis AA where AA.id_duo='" + duo + "'");
        this.cnn.conectar();
        try {
            if (cnn.getRst().next()) {

                dia = cnn.getRst().getInt("resta_dia");
                hora = cnn.getRst().getInt("resta_hora");
                minuto = cnn.getRst().getInt("resta_minuto");
                segundo = cnn.getRst().getInt("resta_segundo");

                if (dia > 0) {
                    if (dia == 1) {
                        tiempo = dia + " día ";
                    } else {
                        tiempo = dia + " días ";
                    }
                }

                if (hora < 10) {
                    tiempo += "0" + hora + ":";
                } else {
                    tiempo += "" + hora + ":";
                }

                if (minuto < 10) {
                    tiempo += "0" + minuto + ":";
                } else {
                    tiempo += "" + minuto + ":";
                }

                if (segundo < 10) {
                    tiempo += "0" + segundo;
                } else {
                    tiempo += "" + segundo;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();
        return tiempo;

    }

    public ArrayList lista_perfil() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  id_perfil, descripcion_perfil,  estado_perfil, tipo_perfil  FROM  schema_uo.perfil where estado_perfil='1' ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cPerfil per = new cPerfil();
                per.setDescripcion_perfil(this.cnn.getRst().getString("descripcion_perfil"));
                per.setEstado_perfil(this.cnn.getRst().getInt("estado_perfil"));
                per.setTipo_perfil(this.cnn.getRst().getInt("tipo_perfil"));
                per.setId_perfil(this.cnn.getRst().getInt("id_perfil"));
                lista.add(per);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_nacion() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT nac_id,nac_descripcion,nac_estado  FROM schemaoirs.nacion where nac_estado='1' order by nac_descripcion ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cNacion nac = new cNacion();
                nac.setDescripcion_nac(this.cnn.getRst().getString("nac_descripcion"));
                nac.setId_nac(this.cnn.getRst().getInt("nac_id"));
                nac.setEstado_nac(this.cnn.getRst().getInt("nac_estado"));
                lista.add(nac);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public cUsuario obtiene_funcionario_rrhh(String rut) {
        cUsuario aux = new cUsuario();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  rut_funcionario, nombres_funcionario,   apellido_p_funcionario, apellido_m_funcionario  FROM  schema_rrhh.funcionario  where  rut_funcionario='" + rut + "' ;");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                aux.setApellidom_usuario(this.cnn.getRst().getString("apellido_m_funcionario"));
                aux.setApellidop_usuario(this.cnn.getRst().getString("apellido_p_funcionario"));
                aux.setNombre_usuario(this.cnn.getRst().getString("nombres_funcionario"));
                aux.setRut_usuario(this.cnn.getRst().getString("rut_funcionario"));
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return aux;
    }

    public ArrayList obtiene_usuario_sistema(String columna, String valor) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL(" SELECT  rut_usuario,nombre_usuario,  apellidop_usuario,apellidom_usuario  FROM  schema_uo.usuario where upper(" + columna + ") LIKE '" + valor + "%';");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cUsuario aux = new cUsuario();
                aux.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                aux.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                aux.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                aux.setRut_usuario(this.cnn.getRst().getString("rut_usuario"));
                lista.add(aux);
            }
        } catch (SQLException var5) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var5);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    /*new code */
    public cPaciente obtiene_paciente_prevision(String rut) {
        cPaciente pac = new cPaciente();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);

        this.cnn.setSentenciaSQL("select p.rut as paciente_rut,\n"
                + "p.provision as id_prevision_paciente,\n"
                + "t.id as prais_prevision_paciente,\n"
                + "t.nombre as tramo_prevision_paciente\n"
                + "\n"
                + "   from agenda.paciente P inner join agenda.prevision pre\n"
                + "   on p.provision = pre.id_prevision\n"
                + "    join agenda.tramo t on t.id= p.tramo\n"
                + "                  where \n"
                + "                P.rut='" + rut + "' ");
        this.cnn.conectar();

        try {
            if (cnn.getRst().next()) {
                pac.setId_prevision(cnn.getRst().getInt("id_prevision_paciente"));
                pac.setRut_paciente(cnn.getRst().getString("paciente_rut"));
                /*26012015*/
                pac.setPrais(cnn.getRst().getInt("prais_prevision_paciente"));
                pac.setTramo_prevision(cnn.getRst().getString("tramo_prevision_paciente"));

            } else {
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.cnn.cerrarConexion();
        return pac;
    }

    /*change code*/
    public ArrayList lista_vista_duo(String campo_fecha, String fecha1, String fecha2, String resto_sql) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);

        this.cnn.setSentenciaSQL("SELECT duo.id_duo,e.id_epicrisis,duo.estado_duo, p.rut as paciente_rut, \n"
                + "                               p.nombre as paciente_nombres, p.apellido_paterno as paciente_apellidop,p.apellido_moderno as paciente_apellidom, \n"
                + "                               (CASE \n"
                + "                            WHEN p.genero = 1 THEN 'FEMENINO'  \n"
                + "                          WHEN p.genero = 0 THEN 'MASCULINO'   \n"
                + "                       END) AS paciente_sexo, to_char(p.fecha_nacimiento,'DD/MM/YYYY')as paciente_fecha_nac, \n"
                + "                           age(current_date, p.fecha_nacimiento) AS paciente_edad, \n"
                + "                            (Select con.con_descripcion from schemaoirs.consultorio_pertenencia con \n"
                + "                           where con.con_id=p.procedencia) as con_descripcion, \n"
                + "                           pp.nombre as codigo_fonasa_descripcion,   t.nombre as tramo_prevision_paciente, \n"
                + "                           to_char(fecha_hora_ing_enfermeria,'DD/MM/YYYY HH24:MI:SS') AS fecha_hora_ing_enfermeria ,e.resumen_epicrisis, \n"
                + "                              e.diagnostico_epicrisis, e.examen_epicrisis,e.indicacion_epicrisis, \n"
                + "                               de.descripcion_derivador, d.descripcion_destino, \n"
                + "                              to_char(duo.fecha_duo + duo.hora_duo,'DD/MM/YYYY HH24:MI:SS') AS fecha_duo, \n"
                + "                             to_char(e.fecha_epicrisis + e.hora_epicrisis,'DD/MM/YYYY HH24:MI:SS') AS fecha_epicrisis,  \n"
                + "                               to_char( a.fecha_hora_alta_adm, 'DD/MM/YYYY HH24:MI:SS') AS fecha_hora_alta_adm, \n"
                + "                              age(e.fecha_epicrisis + e.hora_epicrisis, duo.fecha_duo + duo.hora_duo) \n"
                + "                             AS qdias_epi_duo, \n"
                + "                               age(a.fecha_hora_alta_adm, duo.fecha_duo + duo.hora_duo) \n"
                + "                                AS qdias_altaadm_duo, fecha_hora_alta_med_duo ,\n"
                + "                               EXTRACT(DAY FROM (fecha_hora_alta_med_duo)-(fecha_duo+hora_duo)) as fecha_dias, \n"
                + "                                e.rut_usuario, gg.nombre_usuario, gg.apellidop_usuario, gg.apellidom_usuario \n"
                + "                                FROM agenda.paciente p, \n"
                + "                               schema_uo.destino d, schema_uo.duo duo, schema_uo.epicrisis e, \n"
                + "                               schema_uo.alta_adm a,schema_uo.derivador de, \n"
                + "                              agenda.prevision pp,\n"
                + "                             schema_uo.ing_enfermeria ie,schema_uo.usuario gg ,\n"
                + "                             agenda.tramo t\n"
                + "                               WHERE p.rut = duo.rut_paciente AND \n"
                + "                               e.id_duo = duo.id_duo AND  a.id_destino = d.id_destino AND \n"
                + "                               a.id_duo = duo.id_duo AND  duo.id_derivador = de.id_derivador AND \n"
                + "                              \n"
                + "                               pp.id_prevision = p.provision AND \n"
                + "                               t.id= p.tramo and\n"
                + "                            ie.id_duo_ing_enfermeria = duo.id_duo AND gg.rut_usuario = e.rut_usuario "
                + "   AND " + campo_fecha + " BETWEEN '" + fecha1 + " 00:00:00' AND '" + fecha2 + " 23:59:59' " + resto_sql + " "
                + "  ORDER BY duo.id_duo ");

        this.cnn.conectar();

        try {
            while (cnn.getRst().next()) {
                cVistaUoce vis = new cVistaUoce();

                vis.setApellidom_usuario(cnn.getRst().getString("apellidom_usuario"));
                vis.setApellidop_usuario(cnn.getRst().getString("apellidop_usuario"));
                vis.setCodigo_fonasa_descripcion(cnn.getRst().getString("codigo_fonasa_descripcion"));
                vis.setDescripcion_derivador(cnn.getRst().getString("descripcion_derivador"));
                vis.setDescripcion_destino(cnn.getRst().getString("descripcion_destino"));
                vis.setDiagnostico_epicrisis(cnn.getRst().getString("diagnostico_epicrisis"));
                vis.setEstado_duo(cnn.getRst().getInt("estado_duo"));
                vis.setExamen_epicrisis(cnn.getRst().getString("examen_epicrisis"));
                vis.setFecha_dias(cnn.getRst().getInt("fecha_dias") + 1);
                vis.setFecha_duo(cnn.getRst().getString("fecha_duo"));
                vis.setFecha_epicrisis(cnn.getRst().getString("fecha_epicrisis"));
                vis.setFecha_hora_alta_adm(cnn.getRst().getString("fecha_hora_alta_adm"));
                vis.setFecha_hora_alta_med(cnn.getRst().getString("fecha_hora_alta_med_duo") + "ww");

                vis.setId_duo(cnn.getRst().getInt("id_duo"));
                vis.setId_epicrisis(cnn.getRst().getInt("id_epicrisis"));
                vis.setIndicacion_epicrisis(cnn.getRst().getString("indicacion_epicrisis"));
                vis.setNombre_usuario(cnn.getRst().getString("nombre_usuario"));
                vis.setPaciente_apellidom(cnn.getRst().getString("paciente_apellidom"));
                vis.setPaciente_apellidop(cnn.getRst().getString("paciente_apellidop"));
                vis.setPaciente_consultorio_pertenencia(cnn.getRst().getString("con_descripcion"));

                vis.setPaciente_fecha_nac(cnn.getRst().getString("paciente_fecha_nac"));
                vis.setPaciente_nombres(cnn.getRst().getString("paciente_nombres"));
                vis.setPaciente_rut(cnn.getRst().getString("paciente_rut"));
                vis.setPaciente_sexo(cnn.getRst().getString("paciente_sexo"));

                vis.setFecha_ingreso_enfermeria(cnn.getRst().getString("fecha_hora_ing_enfermeria"));

                /**
                 * ******************************
                 */
                String edad = cnn.getRst().getString("paciente_edad");
                edad = edad.replace("days", "dias");
                edad = edad.replace("mons", "meses");
                edad = edad.replace("years", "años");
                edad = edad.replace("day", "dia");
                edad = edad.replace("mon", "mes");
                edad = edad.replace("year", "año");

                String qdias_epi_duo = cnn.getRst().getString("qdias_epi_duo");
                qdias_epi_duo = qdias_epi_duo.replace("days", "dias");
                qdias_epi_duo = qdias_epi_duo.replace("mons", "meses");
                qdias_epi_duo = qdias_epi_duo.replace("years", "años");
                qdias_epi_duo = qdias_epi_duo.replace("day", "dia");
                qdias_epi_duo = qdias_epi_duo.replace("mon", "mes");
                qdias_epi_duo = qdias_epi_duo.replace("year", "año");

                String qdias_altaadm_duo = cnn.getRst().getString("qdias_altaadm_duo");
                qdias_altaadm_duo = qdias_altaadm_duo.replace("days", "dias");
                qdias_altaadm_duo = qdias_altaadm_duo.replace("mons", "meses");
                qdias_altaadm_duo = qdias_altaadm_duo.replace("years", "años");
                qdias_altaadm_duo = qdias_altaadm_duo.replace("day", "dia");
                qdias_altaadm_duo = qdias_altaadm_duo.replace("mon", "mes");
                qdias_altaadm_duo = qdias_altaadm_duo.replace("year", "año");
                /**
                 * ******************************
                 */
                vis.setPaciente_edad(edad);
                vis.setQdias_altaadm_duo(qdias_altaadm_duo);
                vis.setQdias_epi_duo(qdias_epi_duo);

                vis.setResumen_epicrisis(cnn.getRst().getString("resumen_epicrisis"));
                vis.setRut_usuario(cnn.getRst().getString("rut_usuario"));
                vis.setTramo_prevision_paciente(cnn.getRst().getString("tramo_prevision_paciente"));

                lista.add(vis);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    /*new code*/
    public ArrayList lista_hitos(String rut_paciente) {

        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT \n"
                + " AA.hit_id,hit_tipo, hit_descripcion,to_char( hit_fecha,'DD/MM/YYYY HH24:MI:SS') AS hit_fecha,\n"
                + " hit_paciente_rut, \n"
                + "  hit_detalle,AA.hit_estado,hit_usuario,hit_ip, \n"
                + " cc.nombre as paciente_nombres, cc.apellido_paterno as paciente_apellidop,cc.apellido_moderno as paciente_apellidom, \n"
                + "  rut_usuario,nombre_usuario,apellidop_usuario,apellidom_usuario \n"
                + " FROM  schema_uo.hito_paciente AA \n"
                + "JOIN schema_uo.hito_tipo BB ON (AA.hit_tipo=BB.hit_id)  \n"
                + "  JOIN agenda.paciente CC ON (AA.hit_paciente_rut=CC.rut) \n"
                + "  JOIN schema_uo.usuario DD ON (AA.hit_usuario=DD.rut_usuario)  \n"
                + "   where hit_paciente_rut='" + rut_paciente + "' and AA.hit_estado='1' \n"
                + "   order by hit_fecha desc ;");

        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                cHito aux = new cHito();
                aux.setId(cnn.getRst().getInt("hit_id"));
                aux.setTipo(cnn.getRst().getInt("hit_tipo"));
                aux.setFecha(cnn.getRst().getString("hit_fecha"));
                aux.setDetalle(cnn.getRst().getString("hit_detalle"));
                aux.setEstado(cnn.getRst().getInt("hit_estado"));
                aux.setIp(cnn.getRst().getString("hit_ip"));
                aux.setUsuario_rut(cnn.getRst().getString("rut_usuario"));
                aux.setUsuario_nombre(cnn.getRst().getString("nombre_usuario"));
                aux.setUsuario_apellidop(cnn.getRst().getString("apellidop_usuario"));
                aux.setUsuario_apellidom(cnn.getRst().getString("apellidom_usuario"));

                aux.setEstado_descripcion("---");
                aux.setTipo_descripcion(cnn.getRst().getString("hit_descripcion"));

                lista.add(aux);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();
        return lista;

    }

    public ArrayList lista_tipo_hito() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL(" SELECT  hit_id,hit_descripcion,hit_estado,  hit_fecha_creacion FROM schema_uo.hito_tipo where hit_estado='1' ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cHito aux = new cHito();
                aux.setTipo(this.cnn.getRst().getInt("hit_id"));
                aux.setTipo_descripcion(this.cnn.getRst().getString("hit_descripcion"));
                lista.add(aux);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_usuario_segun_perfil(int perfil) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  rut_usuario,nombre_usuario,  apellidop_usuario, apellidom_usuario FROM  schema_uo.usuario WHERE estado_usuario='1' AND perfil_usuario='" + perfil + "' ; ");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cUsuario aux = new cUsuario();
                aux.setRut_usuario(this.cnn.getRst().getString("rut_usuario"));
                aux.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                aux.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                aux.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                lista.add(aux);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_historial_consultorio(String rut_paciente) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  his_id,his_consultorio_anterior,  his_consultorio_nuevo,to_char( his_fecha,'DD/MM/YYYY HH24:MI:SS') as his_fecha,  his_usuario, his_ip,BB.nombre_usuario,BB.apellidop_usuario,BB.apellidom_usuario  FROM schema_uo.historial_consultorio_pertenencia AA   JOIN schema_uo.usuario BB ON (AA.his_usuario=BB.rut_usuario)  WHERE  his_paciente_rut='" + rut_paciente + "' ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cHistorial_Consultorio aux = new cHistorial_Consultorio();
                aux.setHis_consultorio_anterior_descripcion(this.cnn.getRst().getString(""));
                lista.add(aux);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public cIngresoEnfermeria obtiene_ingreso_enfermeria(int id_duo) {
        cIngresoEnfermeria enf = new cIngresoEnfermeria();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT \n  id_ing_enfermeria, fecha_hora_ing_enfermeria,\n  otro_comorbilidad_ing_enfermeria,farmaco_ing_enfermeria,\n  obs_ing_enfermeria, rut_usuario_ing_enfermeria,\n id_examen_fisico_ing_enfermeria, id_duo_ing_enfermeria,\n  otro_ex_docto_ing_enfermeria,\n  nombre_usuario,apellidop_usuario,apellidom_usuario,\n  conciencia_ex_fisico,cabeza_ex_fisico, mucosa_ex_fisico,\n  torax_ex_fisico,abdomen_ex_fisico, eess_ex_fisico,\n  eeii_ex_fisico, z_sacra_ex_fisico, peso_ex_fisico,\n  talla_ex_fisico, pulso_ex_fisico, presion_a_ex_fisico,\n  temp_ex_fisico,satura_ex_fisico, vvp1_ex_fisico,\n  vvp2_ex_fisico, vvc_ex_fisico, sng_ex_fisico,\n  s_foley_ex_fisico, dorso_lumbar_ex_fisico, piel_ex_fisico\nFROM  schema_uo.ing_enfermeria ENF INNER JOIN \nschema_uo.ex_fisico FIS  ON (ENF.id_examen_fisico_ing_enfermeria=FIS.id_ex_fisico)\nINNER JOIN schema_uo.usuario USU ON (ENF.rut_usuario_ing_enfermeria=USU.rut_usuario)\nWHERE  id_duo_ing_enfermeria='" + id_duo + "' LIMIT 1");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                enf.setId_ing_enfermeria(this.cnn.getRst().getInt("id_ing_enfermeria"));
                enf.setId_examen_fisico_ing_enfermeria(this.cnn.getRst().getInt("id_examen_fisico_ing_enfermeria"));
                enf.setId_duo_ing_enfermeria(this.cnn.getRst().getInt("id_duo_ing_enfermeria"));
                enf.setFecha_hora_ing_enfermeria(this.cnn.getRst().getString("fecha_hora_ing_enfermeria"));
                enf.setOtro_comorbilidad_ing_enfermeria(this.cnn.getRst().getString("otro_comorbilidad_ing_enfermeria"));
                enf.setFarmaco_ing_enfermeria(this.cnn.getRst().getString("farmaco_ing_enfermeria"));
                enf.setObs_ing_enfermeria(this.cnn.getRst().getString("obs_ing_enfermeria"));
                enf.setRut_usuario(this.cnn.getRst().getString("rut_usuario_ing_enfermeria"));
                enf.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                enf.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                enf.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                enf.setOtro_ex_docto_ing_enfermeria(this.cnn.getRst().getString("otro_ex_docto_ing_enfermeria"));
                enf.setConciencia_ex_fisico(this.cnn.getRst().getString("conciencia_ex_fisico"));
                enf.setCabeza_ex_fisico(this.cnn.getRst().getString("cabeza_ex_fisico"));
                enf.setMucosa_ex_fisico(this.cnn.getRst().getString("mucosa_ex_fisico"));
                enf.setTorax_ex_fisico(this.cnn.getRst().getString("torax_ex_fisico"));
                enf.setAbdomen_ex_fisico(this.cnn.getRst().getString("abdomen_ex_fisico"));
                enf.setEess_ex_fisico(this.cnn.getRst().getString("eess_ex_fisico"));
                enf.setEeii_ex_fisico(this.cnn.getRst().getString("eeii_ex_fisico"));
                enf.setZ_sacra_ex_fisico(this.cnn.getRst().getString("z_sacra_ex_fisico"));
                enf.setPeso_ex_fisico(this.cnn.getRst().getString("peso_ex_fisico"));
                enf.setTalla_ex_fisico(this.cnn.getRst().getString("talla_ex_fisico"));
                enf.setPulso_ex_fisico(this.cnn.getRst().getString("pulso_ex_fisico"));
                enf.setPresion_a_ex_fisico(this.cnn.getRst().getString("presion_a_ex_fisico"));
                enf.setTemp_ex_fisico(this.cnn.getRst().getString("temp_ex_fisico"));
                enf.setSatura_ex_fisico(this.cnn.getRst().getString("satura_ex_fisico"));
                enf.setVvp1_ex_fisico(this.cnn.getRst().getString("vvp1_ex_fisico"));
                enf.setVvp2_ex_fisico(this.cnn.getRst().getString("vvp2_ex_fisico"));
                enf.setVvc_ex_fisico(this.cnn.getRst().getString("vvc_ex_fisico"));
                enf.setSng_ex_fisico(this.cnn.getRst().getString("sng_ex_fisico"));
                enf.setS_foley_ex_fisico(this.cnn.getRst().getString("s_foley_ex_fisico"));
                enf.setDorso_lumbar_ex_fisico(this.cnn.getRst().getString("dorso_lumbar_ex_fisico"));
                enf.setPiel_ex_fisico(this.cnn.getRst().getString("piel_ex_fisico"));
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return enf;
    }

    public cDau obtiene_paciente_segun_dau(int dau_id) {
        cDau aux = new cDau();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT   dau_id,AA.paciente_rut, admisor_rut,  AA.tipo_dau_id,CC.tipo_dau_descripcion,   AA.medio_id,DD.medio_descripcion,   AA.unidad_ingreso_id,EE.unidad_ingreso_descripcion,   dau_estado,(CASE WHEN  dau_estado=0 THEN 'Inactivo'    WHEN  dau_estado=1 THEN 'Activo'   WHEN  dau_estado=2 THEN 'Anulado' END)as estado_descripcion,  dau_clasificacion,   to_char(dau_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS')as dau_fecha_ingreso,   to_char(dau_fecha_clasificacion,'DD/MM/YYYY HH24:MI:SS')as dau_fecha_clasificacion,   to_char(dau_fecha_hora_atencion,'DD/MM/YYYY HH24:MI:SS')as dau_fecha_hora_atencion,   to_char(dau_fecha_egreso_med,'DD/MM/YYYY HH24:MI:SS')as  dau_fecha_egreso_med ,    to_char(dau_fecha_clasifica_enf,'DD/MM/YYYY HH24:MI:SS')as  dau_fecha_clasifica_enf,   dau_vif,rut_medico,brazalete,dau_rut_usuario_clasificacion,   dau_categorizado, dau_destino,dau_recupera,   dau_fallece, dau_observa,dau_clasifica_enf,   dau_diag_alta,   BB.paciente_nombres, BB.paciente_apellidop,BB.paciente_apellidom,   age(AA.dau_fecha_ingreso, BB.paciente_fecha_nac) AS paciente_edad,   BB.paciente_sexo,   (CASE WHEN BB.paciente_sexo=1 THEN 'Femenino'    WHEN BB.paciente_sexo=0 THEN 'Masculino'     END) AS genero  FROM  schema_urgencia.dau AA  JOIN schema_urgencia.paciente BB ON (AA.paciente_rut=BB.paciente_rut)   JOIN schema_urgencia.tipo_dau CC ON (CC.tipo_dau_id=AA.tipo_dau_id)  JOIN schema_urgencia.medio DD ON (AA.medio_id=DD.medio_id)  JOIN schema_urgencia.unidad_ingreso EE ON(AA.unidad_ingreso_id=EE.unidad_ingreso_id)  WHERE AA.dau_id='" + dau_id + "'  limit 1 ;");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                aux.setId_dau(this.cnn.getRst().getInt("dau_id"));
                aux.setDau_estado(this.cnn.getRst().getInt("dau_estado"));
                aux.setDau_estado_descri(this.cnn.getRst().getString("estado_descripcion"));
                aux.setTipo_dau_id(this.cnn.getRst().getInt("tipo_dau_id"));
                aux.setTipo_dau_descri(this.cnn.getRst().getString("tipo_dau_descripcion"));
                aux.setMedio_id(this.cnn.getRst().getInt("medio_id"));
                aux.setMedio_descri(this.cnn.getRst().getString("medio_descripcion"));
                aux.setUnidad_ingreso_id(this.cnn.getRst().getInt("unidad_ingreso_id"));
                aux.setUnidad_ingreso_descri(this.cnn.getRst().getString("unidad_ingreso_descripcion"));
                aux.setDau_fecha_ingreso(this.cnn.getRst().getString("dau_fecha_ingreso"));
                aux.setDau_fecha_hora_atencion(this.cnn.getRst().getString("dau_fecha_hora_atencion"));
                aux.setDau_fecha_egreso_med(this.cnn.getRst().getString("dau_fecha_egreso_med"));
                aux.setDau_fecha_clasifica_enf(this.cnn.getRst().getString("dau_fecha_clasifica_enf"));
                aux.setDau_fecha_clasificacion(this.cnn.getRst().getString("dau_fecha_clasificacion"));
                aux.setDau_clasificacion(this.cnn.getRst().getInt("dau_clasificacion"));
                aux.setDau_clasifica_enf(this.cnn.getRst().getInt("dau_clasifica_enf"));
                aux.setAdmisor_rut(this.cnn.getRst().getString("admisor_rut"));
                aux.setRut_medico(this.cnn.getRst().getString("rut_medico"));
                aux.setDau_vif(this.cnn.getRst().getInt("dau_vif"));
                aux.setBrazalete(this.cnn.getRst().getInt("brazalete"));
                aux.setDau_rut_usuario_clasificacion(this.cnn.getRst().getString("dau_rut_usuario_clasificacion"));
                aux.setDau_categorizado(this.cnn.getRst().getInt("dau_categorizado"));
                aux.setDau_destino(this.cnn.getRst().getInt("dau_destino"));
                aux.setDau_recupera(this.cnn.getRst().getInt("dau_recupera"));
                aux.setDau_fallece(this.cnn.getRst().getInt("dau_fallece"));
                aux.setDau_observa(this.cnn.getRst().getInt("dau_observa"));
                aux.setDau_diag_alta(this.cnn.getRst().getInt("dau_diag_alta"));
                aux.setRut_paciente(this.cnn.getRst().getString("paciente_rut"));
                aux.setNombres_paciente(this.cnn.getRst().getString("paciente_nombres"));
                aux.setApellidop_paciente(this.cnn.getRst().getString("paciente_apellidop"));
                aux.setApellidom_paciente(this.cnn.getRst().getString("paciente_apellidom"));
                aux.setSexo(this.cnn.getRst().getInt("paciente_sexo"));
                aux.setSexo_descri(this.cnn.getRst().getString("genero"));
                String edad = this.cnn.getRst().getString("paciente_edad");
                edad = edad.replace("days", "dias");
                edad = edad.replace("mons", "meses");
                edad = edad.replace("years", "años");
                edad = edad.replace("day", "dia");
                edad = edad.replace("mon", "mes");
                edad = edad.replace("year", "año");
                aux.setEdad(edad);

                try {
                    edad = edad.substring(0, edad.length() - 8);
                    aux.setEdad(edad);
                } catch (Exception var5) {
                }
            }
        } catch (SQLException var6) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var6);
        }

        this.cnn.cerrarConexion();
        return aux;
    }

    public cDau obtiene_paciente_segun_dauNN(int dau_nn_id) {
        cDau aux = new cDau();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  dau_nn_id,AA.paciente_nn_id,admisor_rut,   AA.tipo_dau_id,CC.tipo_dau_descripcion,   AA.medio_id,DD.medio_descripcion,   AA.unidad_ingreso_id,EE.unidad_ingreso_descripcion,   to_char(dau_nn_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS')as dau_nn_fecha_ingreso ,   to_char(dau_nn_fecha_clasificacion,'DD/MM/YYYY HH24:MI:SS')as dau_nn_fecha_clasificacion,   prevision_nn_id,dau_nn_clasificacion, dau_nn_vif,dau_nn_impresion,   dau_nn_estado,   (CASE WHEN  dau_nn_estado=0 THEN 'Inactivo'    WHEN  dau_nn_estado=1 THEN 'Activo'    WHEN dau_nn_estado=2 THEN 'Anulado' END)as estado_descripcion,   brazalete,BB.paciente_nn_ano,BB.paciente_nn_apellidom,   BB.paciente_nn_apellidop,BB.paciente_nn_descripcion,   BB.paciente_nn_edad,BB.paciente_nn_nombres,BB.paciente_nn_sexo,   (CASE WHEN BB.paciente_nn_sexo=1 THEN 'Femenino'     WHEN BB.paciente_nn_sexo=0 THEN 'Masculino'     END) AS genero,   BB.paciente_nn_telefono1   FROM schema_urgencia.dau_nn AA    JOIN schema_urgencia.paciente_nn BB ON (AA.paciente_nn_id=BB.paciente_nn_id)    JOIN schema_urgencia.tipo_dau CC ON (CC.tipo_dau_id=AA.tipo_dau_id)   JOIN schema_urgencia.medio DD ON (AA.medio_id=DD.medio_id)   JOIN schema_urgencia.unidad_ingreso EE ON(AA.unidad_ingreso_id=EE.unidad_ingreso_id)   where dau_nn_id='" + dau_nn_id + "'   limit 1 ;");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                aux.setPaciente_descripcion(this.cnn.getRst().getString("paciente_nn_descripcion"));
                aux.setId_dau(this.cnn.getRst().getInt("dau_nn_id"));
                aux.setDau_estado(this.cnn.getRst().getInt("dau_nn_estado"));
                aux.setDau_estado_descri(this.cnn.getRst().getString("estado_descripcion"));
                aux.setTipo_dau_id(this.cnn.getRst().getInt("tipo_dau_id"));
                aux.setTipo_dau_descri(this.cnn.getRst().getString("tipo_dau_descripcion"));
                aux.setMedio_id(this.cnn.getRst().getInt("medio_id"));
                aux.setMedio_descri(this.cnn.getRst().getString("medio_descripcion"));
                aux.setUnidad_ingreso_id(this.cnn.getRst().getInt("unidad_ingreso_id"));
                aux.setUnidad_ingreso_descri(this.cnn.getRst().getString("unidad_ingreso_descripcion"));
                aux.setDau_fecha_ingreso(this.cnn.getRst().getString("dau_nn_fecha_ingreso"));
                aux.setDau_fecha_clasificacion(this.cnn.getRst().getString("dau_nn_fecha_clasificacion"));
                aux.setDau_clasificacion(this.cnn.getRst().getInt("dau_nn_clasificacion"));
                aux.setDau_vif(this.cnn.getRst().getInt("dau_nn_vif"));
                aux.setBrazalete(this.cnn.getRst().getInt("brazalete"));
                aux.setAdmisor_rut(this.cnn.getRst().getString("admisor_rut"));
                aux.setNombres_paciente(this.cnn.getRst().getString("paciente_nn_nombres"));
                aux.setApellidop_paciente(this.cnn.getRst().getString("paciente_nn_apellidop"));
                aux.setApellidom_paciente(this.cnn.getRst().getString("paciente_nn_apellidom"));
                aux.setSexo(this.cnn.getRst().getInt("paciente_nn_sexo"));
                aux.setSexo_descri(this.cnn.getRst().getString("genero"));
                String edad = this.cnn.getRst().getString("paciente_nn_edad");
                aux.setEdad(edad);
                aux.setTelefono1(this.cnn.getRst().getString("paciente_nn_telefono1"));
                aux.setTelefono2("");
                aux.setDireccion("");
                aux.setComuna_descri("");
                aux.setFecha_nac("");
                aux.setPaciente_descripcion(this.cnn.getRst().getString("paciente_nn_descripcion"));
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return aux;
    }

    public ArrayList lista_derivador_suam() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL(" SELECT der_id,der_descripcion  FROM schema_suam.derivador WHERE der_estado=1 ORDER BY  der_id;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cConsultorio aux = new cConsultorio();
                aux.setDescripcion(this.cnn.getRst().getString("der_descripcion"));
                aux.setId(this.cnn.getRst().getInt("der_id"));
                lista.add(aux);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_destino_suam() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL(" SELECT des_id,des_descripcion,des_indicacionegreso,des_alta  FROM  schema_suam.destino WHERE  des_estado=1 order by des_orden");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cConsultorio aux = new cConsultorio();
                aux.setDescripcion(this.cnn.getRst().getString("des_descripcion"));
                aux.setId(this.cnn.getRst().getInt("des_id"));
                aux.setIndicacionEgreso(this.cnn.getRst().getInt("des_indicacionegreso"));
                aux.setAlta(this.cnn.getRst().getInt("des_alta"));
                lista.add(aux);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_medicos_suam() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  usuario_urgencia_rut,usuario_urgencia_apellidop,  usuario_urgencia_apellidom,usuario_urgencia_nombres,perfil_urgencia_id  FROM  schema_urgencia.usuario_urgencia where usuario_urgencia_doc='1' and  usuario_urgencia_estado=1  order by usuario_urgencia_apellidop,usuario_urgencia_apellidom,  usuario_urgencia_nombres ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cUsuario aux = new cUsuario();
                aux.setRut_usuario(this.cnn.getRst().getString("usuario_urgencia_rut"));
                aux.setApellidom_usuario(this.cnn.getRst().getString("usuario_urgencia_apellidom"));
                aux.setApellidop_usuario(this.cnn.getRst().getString("usuario_urgencia_apellidop"));
                aux.setNombre_usuario(this.cnn.getRst().getString("usuario_urgencia_nombres"));
                lista.add(aux);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_diagnostico_suam(int id_das, String tipo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  dia_id,das_id,dia_usuario,  dia_estado,dia_ip,dia_tipo,dia_detalle,  to_char(dia_ingreso,'DD/MM/YYYY HH24:MI:SS')as dia_ingreso,  to_char(dia_ingreso,'DD/MM HH24:MI')as dia_ingreso_short  FROM  schema_suam.diagnostico where dia_estado='1' and das_id='" + id_das + "'   and dia_tipo IN (" + tipo + ") ");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cDiagnostico diag = new cDiagnostico();
                diag.setId_diagnostico(this.cnn.getRst().getInt("dia_id"));
                diag.setId_duo(this.cnn.getRst().getInt("das_id"));
                diag.setTipo_diagnostico(this.cnn.getRst().getInt("dia_tipo"));
                diag.setDescripcion_diagnostico(this.cnn.getRst().getString("dia_detalle"));
                diag.setFecha_ingreso(this.cnn.getRst().getString("dia_ingreso"));
                diag.setRut_usuario(this.cnn.getRst().getString("dia_usuario"));
                diag.setFecha_corta(this.cnn.getRst().getString("dia_ingreso_short"));
                lista.add(diag);
            }
        } catch (SQLException var5) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var5);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_observacion_suam(int id_das) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  obs_id, obs_usuario, to_char(obs_ingreso,'DD/MM/YYYY HH24:MI:SS')as obs_ingreso , to_char(obs_ingreso,'DD/MM HH24:MI')as obs_ingreso_short ,   obs_estado, obs_ip, obs_espera_radiografia, obs_espera_ex_laboratorio, obs_detalle  FROM   schema_suam.observacion WHERE das_id='" + id_das + "' and obs_estado='1'  ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cObservacion obs = new cObservacion();
                obs.setId_obs(this.cnn.getRst().getInt("obs_id"));
                obs.setEspera_radiografia(this.cnn.getRst().getInt("obs_espera_radiografia"));
                obs.setEspera_ex_laboratorio(this.cnn.getRst().getInt("obs_espera_ex_laboratorio"));
                if (obs.getEspera_ex_laboratorio() == 1) {
                    obs.setEspera_ex_laboratorio_descri("SI");
                } else {
                    obs.setEspera_ex_laboratorio_descri("NO");
                }

                if (obs.getEspera_radiografia() == 1) {
                    obs.setEspera_radiografia_descri("SI");
                } else {
                    obs.setEspera_radiografia_descri("NO");
                }

                obs.setRut_usuario(this.cnn.getRst().getString("obs_usuario"));
                obs.setObservacion_detalle(this.cnn.getRst().getString("obs_detalle"));
                obs.setFecha_ingreso(this.cnn.getRst().getString("obs_ingreso"));
                obs.setFecha_corta(this.cnn.getRst().getString("obs_ingreso_short"));
                lista.add(obs);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_motivos_consulta_segun_dau(int id_dau, int modo) {
        String esNN = "";
        if (modo == 2) {
            esNN = "nn_";
        }

        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT dau_" + esNN + "id,BB.motivo_consulta_id,BB.motivo_consulta_descripcion_col  FROM schema_urgencia.dau_" + esNN + "motivo_consulta AA  JOIN schema_urgencia.motivo_consulta BB   ON(AA.motivo_consulta_id=BB.motivo_consulta_id)  WHERE dau_" + esNN + "id IN (" + id_dau + ")");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cDiagnostico diag = new cDiagnostico();
                diag.setId_diagnostico(this.cnn.getRst().getInt("motivo_consulta_id"));
                diag.setId_duo(this.cnn.getRst().getInt("dau_" + esNN + "id"));
                diag.setDescripcion_diagnostico(this.cnn.getRst().getString("motivo_consulta_descripcion_col"));
                lista.add(diag);
            }
        } catch (SQLException var6) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var6);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_pacientes_actuales_suam() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("Select das.das_id,das.das_paciente,das.das_camilla,  pac.paciente_nombres,      pac.paciente_apellidop,pac.paciente_apellidom,     to_char(pac.paciente_fecha_nac,'DD/MM/YYYY')as paciente_fecha_nac,     age(das.das_fecha_ingreso, pac.paciente_fecha_nac) AS paciente_edad,   pac.paciente_sexo, (CASE WHEN paciente_sexo=1 THEN 'FEMENINO'     WHEN paciente_sexo=0 THEN 'MASCULINO' END) AS genero   from schema_suam.das das  JOIN schema_urgencia.paciente pac ON (das.das_paciente=pac.paciente_rut)   WHERE das.das_estado IN (1,2,3)");
        this.cnn.conectar();

        cDas das;
        try {
            for (; this.cnn.getRst().next(); lista.add(das)) {
                das = new cDas();
                das.setId_das(this.cnn.getRst().getInt("das_id"));
                das.setCamilla(this.cnn.getRst().getInt("das_camilla"));
                das.setRut_paciente(this.cnn.getRst().getString("das_paciente"));
                das.setNombres_paciente(this.cnn.getRst().getString("paciente_nombres"));
                das.setApellidop_paciente(this.cnn.getRst().getString("paciente_apellidop"));
                das.setApellidom_paciente(this.cnn.getRst().getString("paciente_apellidom"));
                das.setFecha_nac(this.cnn.getRst().getString("paciente_fecha_nac"));
                String edad = this.cnn.getRst().getString("paciente_edad");
                if (edad != null) {
                    edad = edad.replace("days", "dias.z");
                    edad = edad.replace("mons", "meses");
                    edad = edad.replace("years", "años");
                    edad = edad.replace("day", "dia.z");
                    edad = edad.replace("mon", "mes");
                    edad = edad.replace("year", "año");
                    das.setEdad(edad);
                } else {
                    das.setEdad("00");
                }

                try {
                    edad = edad.substring(0, edad.indexOf("z") - 1);
                    das.setEdad(edad);
                } catch (Exception var5) {
                }
            }
        } catch (SQLException var6) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, (String) null, var6);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_camillas_actuales() {
        ArrayList lista = new ArrayList();
        ArrayList obtiene_datos_paciente = this.lista_pacientes_actuales_suam();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  cam_id,cam_descripcion,cam_estado,      sala.sal_id, sala.sal_descripcion,    das.das_id,das.das_estado,      das.dau_id,das.dau_nn_id,       das.das_paciente,  to_char( das.das_fecha_ingreso,'DD/MM HH24:MI' )as das_fecha_ingreso,      EXTRACT(SECOND FROM CURRENT_TIMESTAMP-das.das_fecha_ingreso) as dif_ss,      EXTRACT(MINUTE FROM CURRENT_TIMESTAMP-das.das_fecha_ingreso) as dif_mm,      EXTRACT(HOUR FROM CURRENT_TIMESTAMP-das.das_fecha_ingreso) as dif_hh,    EXTRACT(DAY FROM CURRENT_TIMESTAMP-das.das_fecha_ingreso) as dif_dd,     usu.usuario_urgencia_nombres,usu.usuario_urgencia_apellidop,    usu.usuario_urgencia_apellidom,     usuario.nombre_usuario,usuario.apellidop_usuario,usuario.apellidom_usuario,   des_descripcion        FROM  schema_suam.camilla cam  LEFT JOIN schema_suam.das das ON das.das_camilla=cam.cam_id    AND (das.das_estado=1 or das.das_estado=2 or das.das_estado=3)     LEFT JOIN schema_urgencia.usuario_urgencia usu ON (das.das_medico=usu.usuario_urgencia_rut)      LEFT JOIN schema_uo.usuario usuario ON (das.das_usuario=usuario.rut_usuario)     LEFT JOIN schema_suam.destino des ON (das.das_indicacion_destino=des.des_id)     JOIN schema_suam.sala sala ON (cam.sal_id=sala.sal_id)   WHERE cam_estado='1'  order by cam_id");
        this.cnn.conectar();

        cDas das;
        try {
            for (; this.cnn.getRst().next(); lista.add(das)) {
                Iterator paci = obtiene_datos_paciente.iterator();
                das = new cDas();
                das.setId_das(this.cnn.getRst().getInt("das_id"));
                das.setCamilla(this.cnn.getRst().getInt("cam_id"));
                das.setCamilla_descri(this.cnn.getRst().getString("cam_descripcion"));
                das.setSala(this.cnn.getRst().getInt("sal_id"));
                das.setSala_descri(this.cnn.getRst().getString("sal_descripcion"));
                das.setEstado(this.cnn.getRst().getInt("das_estado"));
                das.setEstado_descri("");
                das.setDau_id(this.cnn.getRst().getInt("dau_id"));
                das.setDau_nn_id(this.cnn.getRst().getInt("dau_nn_id"));
                das.setFecha_ingreso(this.cnn.getRst().getString("das_fecha_ingreso"));

                while (paci.hasNext()) {
                    cDas itera = (cDas) paci.next();
                    if (itera.getId_das() == das.getId_das()) {
                        das.setRut_paciente(itera.getRut_paciente());
                        das.setNombres_paciente(itera.getNombres_paciente());
                        das.setApellidop_paciente(itera.getApellidop_paciente());
                        das.setApellidom_paciente(itera.getApellidom_paciente());
                        das.setFecha_nac(itera.getFecha_nac());
                        das.setEdad(itera.getEdad());
                    }
                }

                das.setNombre_medico(this.cnn.getRst().getString("usuario_urgencia_nombres"));
                das.setApellidop_medico(this.cnn.getRst().getString("usuario_urgencia_apellidop"));
                das.setApellidom_medico(this.cnn.getRst().getString("usuario_urgencia_apellidom"));
                das.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                das.setApellidom_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                das.setApellidop_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                das.setDif_dd(this.cnn.getRst().getInt("dif_dd"));
                das.setDif_hh(this.cnn.getRst().getInt("dif_hh"));
                das.setDif_mm(this.cnn.getRst().getInt("dif_mm"));
                das.setDif_ss(this.cnn.getRst().getInt("dif_ss"));
                das.setIndicacion_destino_descri(this.cnn.getRst().getString("des_descripcion"));
                if (das.getIndicacion_destino_descri() == null) {
                    das.setIndicacion_destino_descri("");
                }

                if (das.getDau_id() == 0) {
                    das.setNombres_paciente("PACIENTE NN");
                    das.setApellidom_paciente("");
                    das.setApellidop_paciente("");
                    das.setEdad("");
                    das.setRut_paciente("");
                }
            }
        } catch (SQLException var6) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, (String) null, var6);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public cDas obtiene_das(int id_das) {
        cDas das = new cDas();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  cam_id,cam_descripcion,cam_estado,    sala.sal_id, sala.sal_descripcion,    das.das_id,das.das_estado,     das.dau_id,das.dau_nn_id,     das.das_paciente,pac.paciente_nombres,    pac.paciente_apellidop,pac.paciente_apellidom,     to_char( das.das_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS' )as das_fecha_ingreso,       EXTRACT(SECOND FROM CURRENT_TIMESTAMP-das.das_fecha_ingreso) as dif_ss,      EXTRACT(MINUTE FROM CURRENT_TIMESTAMP-das.das_fecha_ingreso) as dif_mm,      EXTRACT(HOUR FROM CURRENT_TIMESTAMP-das.das_fecha_ingreso) as dif_hh,     EXTRACT(DAY FROM CURRENT_TIMESTAMP-das.das_fecha_ingreso) as dif_dd,     to_char(pac.paciente_fecha_nac,'DD/MM/YYYY')as paciente_fecha_nac,      age(das.das_fecha_ingreso, pac.paciente_fecha_nac) AS paciente_edad,      pac.paciente_sexo, (CASE WHEN paciente_sexo=1 THEN 'FEMENINO'      WHEN paciente_sexo=0 THEN 'MASCULINO' END) AS genero,     pac.paciente_direccion,pac.paciente_telefono1,   pac.paciente_telefono2,comuna_descripcion ,   des_id,des_descripcion ,   to_char(das.das_indicacion_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS' )as das_indicacion_fecha_ingreso,   usuario_urgencia_rut,usuario_urgencia_nombres,usuario_urgencia_apellidop,   usuario_urgencia_apellidom,DER.der_id,der_descripcion   FROM  schema_suam.camilla cam      LEFT JOIN schema_suam.das das ON (cam.cam_id=das.das_camilla)      LEFT JOIN schema_urgencia.paciente pac ON (das.das_paciente=pac.paciente_rut)     LEFT JOIN schemaoirs.comuna comu ON (pac.comuna_codigo=comu.comuna_codigo)    LEFT JOIN schema_suam.destino des ON (das.das_indicacion_destino=des.des_id)    LEFT JOIN schema_urgencia.usuario_urgencia USU  ON (das.das_medico=USU.usuario_urgencia_rut)    LEFT JOIN schema_suam.derivador DER ON (das.das_derivador=DER.der_id)   JOIN schema_suam.sala sala ON (cam.sal_id=sala.sal_id)     WHERE das_id='" + id_das + "'  limit 1 ;  ");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                das.setId_das(this.cnn.getRst().getInt("das_id"));
                das.setCamilla(this.cnn.getRst().getInt("cam_id"));
                das.setCamilla_descri(this.cnn.getRst().getString("cam_descripcion"));
                das.setSala(this.cnn.getRst().getInt("sal_id"));
                das.setSala_descri(this.cnn.getRst().getString("sal_descripcion"));
                das.setEstado(this.cnn.getRst().getInt("das_estado"));
                das.setEstado_descri("--- ---");
                das.setDau_id(this.cnn.getRst().getInt("dau_id"));
                das.setDau_nn_id(this.cnn.getRst().getInt("dau_nn_id"));
                das.setFecha_ingreso(this.cnn.getRst().getString("das_fecha_ingreso"));
                das.setRut_paciente(this.cnn.getRst().getString("das_paciente"));
                das.setNombres_paciente(this.cnn.getRst().getString("paciente_nombres"));
                das.setApellidop_paciente(this.cnn.getRst().getString("paciente_apellidop"));
                das.setApellidom_paciente(this.cnn.getRst().getString("paciente_apellidom"));
                das.setSexo(this.cnn.getRst().getInt("paciente_sexo"));
                das.setSexo_descri(this.cnn.getRst().getString("genero"));
                String edad = this.cnn.getRst().getString("paciente_edad");
                if (edad != null) {
                    edad = edad.replace("days", "dias.z");
                    edad = edad.replace("mons", "meses");
                    edad = edad.replace("years", "años");
                    edad = edad.replace("day", "dia.z");
                    edad = edad.replace("mon", "mes");
                    edad = edad.replace("year", "año");
                    das.setEdad(edad);
                } else {
                    das.setEdad("00");
                }

                try {
                    edad = edad.substring(0, edad.indexOf("z") - 1);
                    das.setEdad(edad);
                } catch (Exception var5) {
                }

                das.setDireccion(this.cnn.getRst().getString("paciente_direccion"));
                das.setTelefono1(this.cnn.getRst().getString("paciente_telefono1"));
                das.setTelefono2(this.cnn.getRst().getString("paciente_telefono2"));
                das.setComuna_descri(this.cnn.getRst().getString("comuna_descripcion"));
                das.setFecha_nac(this.cnn.getRst().getString("paciente_fecha_nac"));
                das.setDif_dd(this.cnn.getRst().getInt("dif_dd"));
                das.setDif_hh(this.cnn.getRst().getInt("dif_hh"));
                das.setDif_mm(this.cnn.getRst().getInt("dif_mm"));
                das.setDif_ss(this.cnn.getRst().getInt("dif_ss"));
                das.setIndicacion_destino_id(this.cnn.getRst().getInt("des_id"));
                das.setIndicacion_destino_descri(this.cnn.getRst().getString("des_descripcion"));
                das.setIndicacion_fecha(this.cnn.getRst().getString("das_indicacion_fecha_ingreso"));
                das.setNombre_medico(this.cnn.getRst().getString("usuario_urgencia_nombres"));
                das.setApellidop_medico(this.cnn.getRst().getString("usuario_urgencia_apellidop"));
                das.setApellidom_medico(this.cnn.getRst().getString("usuario_urgencia_apellidom"));
                das.setMedico(this.cnn.getRst().getString("usuario_urgencia_rut"));
                das.setDerivador(this.cnn.getRst().getInt("der_id"));
                das.setDerivador_descri(this.cnn.getRst().getString("der_descripcion"));
                if (das.getDau_id() == 0) {
                    das.setRut_paciente("NN");
                    das.setNombres_paciente("PACIENTE NN");
                    das.setApellidop_paciente("");
                    das.setApellidom_paciente("");
                    das.setEdad("");
                    das.setRut_paciente("");
                    das.setDireccion("");
                    das.setTelefono1("");
                    das.setTelefono2("");
                    das.setComuna_descri("");
                    das.setFecha_nac("");
                }
            }
        } catch (SQLException var6) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, (String) null, var6);
        }

        this.cnn.cerrarConexion();
        return das;
    }

    public cAlta_Das obtiene_alta_das(int das) {
        cAlta_Das alt = new cAlta_Das();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  alt_id,alt.das_id,alt_usuario,  usu.nombre_usuario,usu.apellidop_usuario,usu.apellidom_usuario,  to_char(alt_ingreso,'DD/MM/YYYY HH24:MI:SS' )as alt_ingreso,alt_estado,alt_destino,des_descripcion,  alt_detalle, alt_ip,alt_medico,  med.usuario_urgencia_nombres,med.usuario_urgencia_apellidop,  med.usuario_urgencia_apellidom,  EXTRACT(SECOND FROM alt_ingreso-dasi.das_fecha_ingreso) as dif_ss,    EXTRACT(MINUTE FROM alt_ingreso-dasi.das_fecha_ingreso) as dif_mm,    EXTRACT(HOUR FROM alt_ingreso-dasi.das_fecha_ingreso) as dif_hh,     EXTRACT(DAY FROM alt_ingreso-dasi.das_fecha_ingreso) as dif_dd        FROM  schema_suam.alta_das alt  JOIN schema_suam.das dasi ON (dasi.das_id=alt.das_id)  JOIN schema_uo.usuario usu ON (usu.rut_usuario=alt.alt_usuario)  JOIN schema_suam.destino des ON (des.des_id=alt.alt_destino)  LEFT JOIN schema_urgencia.usuario_urgencia med ON (med.usuario_urgencia_rut=alt.alt_medico)  where dasi.das_id='" + das + "' ;");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                alt.setId_das(this.cnn.getRst().getInt("das_id"));
                alt.setId_alta_das(this.cnn.getRst().getInt("alt_id"));
                alt.setDestino(this.cnn.getRst().getInt("alt_destino"));
                alt.setDestino_descri(this.cnn.getRst().getString("des_descripcion"));
                alt.setFecha_ingreso(this.cnn.getRst().getString("alt_ingreso"));
                alt.setDetalle(this.cnn.getRst().getString("alt_detalle"));
                if (alt.getDetalle() == null || alt.getDetalle().equalsIgnoreCase("null")) {
                    alt.setDetalle("");
                }

                alt.setMedico_rut(this.cnn.getRst().getString("alt_medico"));
                alt.setMedico_nombre(this.cnn.getRst().getString("usuario_urgencia_nombres"));
                alt.setMedico_apellidop(this.cnn.getRst().getString("usuario_urgencia_apellidop"));
                alt.setMedico_apellidom(this.cnn.getRst().getString("usuario_urgencia_apellidom"));
                if (alt.getMedico_rut() == null) {
                    alt.setMedico_nombre("No registrado");
                    alt.setMedico_apellidop("");
                    alt.setMedico_apellidom("");
                }

                alt.setRut_usuario(this.cnn.getRst().getString("alt_usuario"));
                alt.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                alt.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                alt.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                alt.setDif_dd(this.cnn.getRst().getInt("dif_dd"));
                alt.setDif_hh(this.cnn.getRst().getInt("dif_hh"));
                alt.setDif_mm(this.cnn.getRst().getInt("dif_mm"));
                alt.setDif_ss(this.cnn.getRst().getInt("dif_ss"));
            }
        } catch (SQLException var4) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return alt;
    }

    public ArrayList lista_diagnosticos_das_en_camilla() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT   dia_id,das_id,dia_usuario,  dia_ingreso,dia_estado,dia_ip,  dia_tipo, dia_detalle  FROM  schema_suam.diagnostico  where das_id IN (select das_id from schema_suam.das where das_estado IN (1,2)) and  dia_estado='1' ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cDiagnostico diag = new cDiagnostico();
                diag.setId_diagnostico(this.cnn.getRst().getInt("dia_id"));
                diag.setId_duo(this.cnn.getRst().getInt("das_id"));
                diag.setTipo_diagnostico(this.cnn.getRst().getInt("dia_tipo"));
                diag.setDescripcion_diagnostico(this.cnn.getRst().getString("dia_detalle"));
                lista.add(diag);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public String dig_cero(int numero) {
        String var = "";
        if (numero < 10 && numero > -1) {
            var = var + "0" + numero;
        } else {
            var = var + "" + numero;
        }

        return var;
    }

    /*new code*/
    public ArrayList listada_categorizaciones(String fecha_inicial, String fecha_final) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL(" SELECT \n"
                + "                 id_visita,obs_visita,to_char(fecha_visita,'DD/MM/YYYY' )as fecha_visita,hora_visita, \n"
                + "                 to_char(fecha_hora_visita,'DD/MM/YYYY HH24:MI:SS' )as fecha_hora_visita ,AA.rut_usuario, \n"
                + "                 nombre_usuario,apellidop_usuario, \n"
                + "                 AA.id_duo, descripcion_cama,BB.cat_visita_categorizacion,DD.rut_paciente, \n"
                + "               ff.nombre as  paciente_nombres,ff.apellido_paterno as paciente_apellidop,ff.apellido_moderno as paciente_apellidom \n"
                + "                 FROM  \n"
                + "                 schema_uo.visita AA JOIN schema_uo.visita_categorizacion BB ON  \n"
                + "                 (AA.id_visita_categorizacion=BB.id_visita_categorizacion)\n"
                + "                 JOIN schema_uo.usuario CC ON (CC.rut_usuario=AA.rut_usuario) \n"
                + "                 JOIN schema_uo.duo DD ON (DD.id_duo=AA.id_duo) \n"
                + "                 JOIN schema_uo.cama EE ON (AA.id_cama=EE.id_cama) \n"
                + "                 JOIN agenda.paciente FF ON (DD.rut_paciente=FF.rut) "
                + " where fecha_visita BETWEEN '" + fecha_inicial + " 00:00:00' AND '" + fecha_final + " 23:59:59' "
                + " order by AA.id_cama");

        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                cDuo duo = new cDuo();
                duo.setId_duo(cnn.getRst().getInt("id_duo"));
                duo.setFecha_duo(cnn.getRst().getString("fecha_visita"));
                duo.setHora_duo(cnn.getRst().getString("hora_visita"));
                duo.setFecha_creacion(cnn.getRst().getString("fecha_hora_visita"));
// utilizo esta clase y mutadores por falta de tiempo para acomodar la cVisita 30-08-2012 18:14
                duo.setDerivador_descripcion(cnn.getRst().getString("obs_visita"));

                // duo.setFecha_duo(cnn.getRst().getString("fecha_duo"));
                // duo.setHora_duo(cnn.getRst().getString("hora_duo"));
                //duo.setCama(cnn.getRst().getInt("id_cama"));
                duo.setCama_descripcion(cnn.getRst().getString("descripcion_cama"));

                // duo.setFecha_hora_ing_duo(cnn.getRst().getString("fecha_hora_ing_duo"));
                duo.setCategorizacion_id(cnn.getRst().getInt("id_visita"));
                duo.setCategorizacion_descripcion(cnn.getRst().getString("cat_visita_categorizacion"));

                duo.setRut_paciente(cnn.getRst().getString("rut_paciente"));
                duo.setNombres_paciente(cnn.getRst().getString("paciente_nombres"));
                duo.setApellidop_paciente(cnn.getRst().getString("paciente_apellidop"));
                duo.setApellidom_paciente(cnn.getRst().getString("paciente_apellidom"));

                duo.setRut_usuario(cnn.getRst().getString("rut_usuario"));
                duo.setNombre_usuario_admision(cnn.getRst().getString("nombre_usuario") + " " + cnn.getRst().getString("apellidop_usuario"));
//                duo.setTelefono1(cnn.getRst().getString("paciente_telefono1"));
//                duo.setTelefono2(cnn.getRst().getString("paciente_telefono2"));
//                duo.setFecha_nac(cnn.getRst().getString("paciente_fecha_nac"));

                lista.add(duo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();
        return lista;
    }

    public cDuo obtiene_fecha_enfermeria_medico_ingreso(int numero_duo) {
        cDuo duo = new cDuo();
        new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("  Select fecha_hora_ing_med as fecha,  EXTRACT(SECOND FROM CURRENT_TIMESTAMP-fecha_hora_ing_med) as dif_ss,    EXTRACT(MINUTE FROM CURRENT_TIMESTAMP-fecha_hora_ing_med) as dif_mm,     EXTRACT(HOUR FROM CURRENT_TIMESTAMP-fecha_hora_ing_med)as dif_hh,     EXTRACT(DAY FROM CURRENT_TIMESTAMP-fecha_hora_ing_med)as dif_dd,  'medico' as tipo  from schema_uo.duo where id_duo='" + numero_duo + "'  UNION  SELECT   fecha_hora_ing_enfermeria as fecha,  EXTRACT(SECOND FROM CURRENT_TIMESTAMP-fecha_hora_ing_enfermeria) as dif_ss,    EXTRACT(MINUTE FROM CURRENT_TIMESTAMP-fecha_hora_ing_enfermeria) as dif_mm,     EXTRACT(HOUR FROM CURRENT_TIMESTAMP-fecha_hora_ing_enfermeria)as dif_hh,     EXTRACT(DAY FROM CURRENT_TIMESTAMP-fecha_hora_ing_enfermeria)as dif_dd,  'enfermeria' as tipo  FROM     schema_uo.ing_enfermeria   where id_duo_ing_enfermeria='" + numero_duo + "' order by fecha asc ; ");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                duo.setDif_dd(this.cnn.getRst().getInt("dif_dd"));
                duo.setDif_hh(this.cnn.getRst().getInt("dif_hh"));
                duo.setDif_mm(this.cnn.getRst().getInt("dif_mm"));
                duo.setDif_ss(this.cnn.getRst().getInt("dif_ss"));
            }
        } catch (SQLException var5) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var5);
        }

        this.cnn.cerrarConexion();
        return duo;
    }

    public String corta_cadena(String cadena) {
        int encuentra_posicion = 0;
        int recorriendo = 0;
        boolean encontro = false;
        cadena = cadena + "";
        if (cadena.length() > 0) {
            while (true) {
                if (recorriendo >= cadena.length()) {
                    if (encuentra_posicion != 0) {
                        cadena = cadena.substring(0, encuentra_posicion) + "";
                    }
                    break;
                }

                if (cadena.substring(recorriendo, recorriendo + 1).equals(" ") && !encontro) {
                    encuentra_posicion = recorriendo;
                    encontro = true;
                }

                ++recorriendo;
            }
        }

        return cadena;
    }

    public ArrayList lista_das(String fecha_inicio, String fecha_final, int modo) {
        ArrayList lista = new ArrayList();
        String campo = "";
        if (modo == 1) {
            campo = "das_fecha_ingreso";
        } else {
            campo = "alt_ingreso";
        }

        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  AA.das_id,dau_id,  das_paciente,CC.paciente_nombres,CC.paciente_apellidop,CC.paciente_apellidom,  das_estado,  (case  WHEN das_estado=1 THEN 'En cama'  WHEN das_estado=2 THEN 'Egresado'  WHEN das_estado=3 THEN '---'  WHEN das_estado=4 THEN 'Dado Alta'  WHEN das_estado=99 THEN 'Anulado'   end) as das_estado_descripcion,  to_char(das_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS')as das_fecha_ingreso,  das_camilla, cam_descripcion, das_derivador,EE.der_descripcion,   das_medico,DD.usuario_urgencia_nombres,DD.usuario_urgencia_apellidop,  das_usuario, das_ip,das_indicacion_destino,  das_indicacion_usuario,  to_char(das_indicacion_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS')as das_indicacion_fecha_ingreso,  das_indicacion_ip,  ALT.alt_id, to_char(ALT.alt_ingreso,'DD/MM/YYYY HH24:MI:SS')as alt_ingreso,ALT.alt_usuario,  ALT.alt_destino,DES.des_descripcion,ALT.alt_ip,ALT.alt_detalle  FROM  schema_suam.das AA  JOIN schema_suam.camilla BB ON (BB.cam_id=AA.das_camilla)  JOIN schema_urgencia.paciente CC ON (CC.paciente_rut=AA.das_paciente)  JOIN schema_urgencia.usuario_urgencia DD ON (DD.usuario_urgencia_rut=das_medico)  JOIN schema_suam.derivador EE ON (EE.der_id=AA.das_derivador)   JOIN schema_uo.usuario FF ON (FF.rut_usuario=AA.das_usuario)  JOIN schema_suam.alta_das ALT ON (ALT.das_id=AA.das_id)  JOIN schema_suam.destino DES ON (DES.des_id=ALT.alt_destino)  WHERE " + campo + " BETWEEN '" + fecha_inicio + " 00:00:00' AND '" + fecha_final + " 23:59:59'  AND dau_nn_id =0 and ALT.alt_estado=1  ORDER BY das_id desc ");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cDas das = new cDas();
                das.setId_das(this.cnn.getRst().getInt("das_id"));
                das.setDau_id(this.cnn.getRst().getInt("dau_id"));
                das.setRut_paciente(this.cnn.getRst().getString("das_paciente"));
                das.setNombres_paciente(this.cnn.getRst().getString("paciente_nombres"));
                das.setApellidom_paciente(this.cnn.getRst().getString("paciente_apellidom"));
                das.setApellidop_paciente(this.cnn.getRst().getString("paciente_apellidop"));
                das.setEstado(this.cnn.getRst().getInt("das_estado"));
                das.setEstado_descri(this.cnn.getRst().getString("das_estado_descripcion"));
                das.setFecha_ingreso(this.cnn.getRst().getString("das_fecha_ingreso"));
                das.setCamilla(this.cnn.getRst().getInt("das_camilla"));
                das.setCamilla_descri(this.cnn.getRst().getString("cam_descripcion"));
                das.setDerivador(this.cnn.getRst().getInt("das_derivador"));
                das.setDerivador_descri(this.cnn.getRst().getString("der_descripcion"));
                das.setMedico(this.cnn.getRst().getString("das_medico"));
                das.setUsuario(this.cnn.getRst().getString("das_usuario"));
                das.setApellidom_usuario(this.cnn.getRst().getString("das_medico"));
                das.setApellidop_usuario(this.cnn.getRst().getString("usuario_urgencia_apellidop"));
                das.setNombre_usuario(this.cnn.getRst().getString("usuario_urgencia_nombres"));
                das.setIp(this.cnn.getRst().getString("das_ip"));
                das.setIndicacion_destino_descri("-- -- --");
                das.setIndicacion_destino_id(this.cnn.getRst().getInt("das_indicacion_destino"));
                das.setIndicacion_usuario_rut(this.cnn.getRst().getString("das_indicacion_usuario"));
                das.setIndicacion_fecha(this.cnn.getRst().getString("das_indicacion_fecha_ingreso"));
                if (das.getIndicacion_fecha() == null) {
                    das.setIndicacion_fecha("");
                }

                das.setIndicacion_ip(this.cnn.getRst().getString("das_indicacion_ip"));
                das.setAlta_destino(this.cnn.getRst().getInt("alt_destino"));
                das.setAlta_destino_descri(this.cnn.getRst().getString("des_descripcion"));
                das.setAlta_detalle(this.cnn.getRst().getString("alt_detalle"));
                das.setAlta_usuario_rut(this.cnn.getRst().getString("alt_usuario"));
                das.setAlta_ip(this.cnn.getRst().getString("alt_ip"));
                das.setAlta_fecha_ingreso(this.cnn.getRst().getString("alt_ingreso"));
                lista.add(das);
            }
        } catch (SQLException var7) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var7);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_contacto(int id_das) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  con_id,das_id,con_usuario,con_ingreso,  to_char(con_ingreso,'DD/MM/YYYY HH24:MI:SS')as con_ingreso_corta, con_estado,con_ip,con_nombre, to_char(con_fecha,'DD/MM/YYYY') as con_fecha, to_char(con_fecha,'DD/MM') as con_fecha_corta,   con_hora, to_char(con_hora,'HH24:MI') as con_hora_corta , con_observacion,UU.nombre_usuario,UU.apellidop_usuario,UU.apellidom_usuario  FROM schema_suam.contacto CC JOIN schema_uo.usuario UU ON   (CC.con_usuario=UU.rut_usuario) WHERE con_estado='1' and das_id='" + id_das + "' ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cContacto con = new cContacto();
                con.setId_contacto(this.cnn.getRst().getInt("con_id"));
                con.setDas_id(this.cnn.getRst().getInt("das_id"));
                con.setEstado(this.cnn.getRst().getInt("con_estado"));
                con.setFecha_ingreso(this.cnn.getRst().getString("con_ingreso"));
                con.setFecha(this.cnn.getRst().getString("con_fecha"));
                con.setHora(this.cnn.getRst().getString("con_hora"));
                con.setIp(this.cnn.getRst().getString("con_ip"));
                con.setObservacion(this.cnn.getRst().getString("con_observacion"));
                con.setNombre(this.cnn.getRst().getString("con_nombre"));
                con.setFecha_corta(this.cnn.getRst().getString("con_fecha_corta") + " " + this.cnn.getRst().getString("con_hora_corta"));
                con.setFecha_ingreso_corta(this.cnn.getRst().getString("con_ingreso_corta"));
                con.setRut_usuario(this.cnn.getRst().getString("con_usuario"));
                con.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                con.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                con.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                lista.add(con);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_examen_radiografia(String tipo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  exa_id,exa_descripcion, exa_tipo,  CASE   WHEN exa_tipo='1' THEN 'EXAMEN'   WHEN exa_tipo='2' THEN 'RADIOGRAFIA'   ELSE 'OTRO' END as tipo ,exa_estado, to_char(exa_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS')as exa_fecha_ingreso  FROM  schema_suam.examen_radio WHERE exa_estado='1' AND exa_tipo IN (" + tipo + ")  ORDER BY exa_orden,exa_descripcion ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cExamen exa = new cExamen();
                exa.setId_examen(this.cnn.getRst().getInt("exa_id"));
                exa.setDescripcion(this.cnn.getRst().getString("exa_descripcion"));
                exa.setTipo(this.cnn.getRst().getInt("exa_tipo"));
                exa.setTipo_descripcion(this.cnn.getRst().getString("tipo"));
                exa.setFecha_ingreso(this.cnn.getRst().getString("exa_fecha_ingreso"));
                exa.setEstado(this.cnn.getRst().getInt("exa_estado"));
                lista.add(exa);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_examen_x_das(int id_das) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT   er_id, er_das_id,AA.er_examen,  BB.exa_descripcion, BB.exa_tipo,   CASE    WHEN BB.exa_tipo='1' THEN 'EXAMEN'   WHEN BB.exa_tipo='2' THEN 'RADIOGRAFIA'    ELSE 'OTRO' END as tipo ,  to_char(er_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS')as er_fecha_ingreso ,  to_char(er_fecha_ingreso,'DD/MM HH24:MI')as er_fecha_corta ,  er_usuario  FROM  schema_suam.examen_radio_das AA  JOIN schema_suam.examen_radio BB ON(AA.er_examen=BB.exa_id)  WHERE er_estado=1 and AA.er_das_id='" + id_das + "'");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cExamen exa = new cExamen();
                exa.setId_examen(this.cnn.getRst().getInt("er_examen"));
                exa.setDescripcion(this.cnn.getRst().getString("exa_descripcion"));
                exa.setTipo(this.cnn.getRst().getInt("exa_tipo"));
                exa.setTipo_descripcion(this.cnn.getRst().getString("tipo"));
                exa.setId_das(this.cnn.getRst().getInt("er_das_id"));
                exa.setId_das_examen(this.cnn.getRst().getInt("er_id"));
                exa.setFecha_corta(this.cnn.getRst().getString("er_fecha_corta"));
                exa.setFecha_ingreso(this.cnn.getRst().getString("er_fecha_ingreso"));
                exa.setRut_usuario(this.cnn.getRst().getString("er_usuario"));
                lista.add(exa);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    /*change*/
    public ArrayList obtiene_duo_liviano_x_fecha() {
        ArrayList lista = new ArrayList();

        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  \n"
                + "                 id_duo, \n"
                + "                 to_char((fecha_duo+hora_duo),'DD/MM/YYYY HH24:MI:SS')as fecha_hora_ing_duo, \n"
                + "                 rut_paciente, \n"
                + "                BB.nombre as  paciente_nombres, \n"
                + "                 BB.apellido_paterno as  paciente_apellidop, \n"
                + "                 BB.apellido_moderno as paciente_apellidom \n"
                + "                FROM  schema_uo.duo AA JOIN agenda.paciente BB ON \n"
                + "               (AA.rut_paciente=BB.rut) \n"
                + "                WHERE id_derivador=2 and estado_duo NOT IN (99) AND \n"
                + "                (fecha_duo+hora_duo) BETWEEN '01-01-2012 00:00:00' AND '12-31-2012 23:59:59' order by id_duo;");
        this.cnn.conectar();
        try {
            while (cnn.getRst().next()) {
                cDuo duo = new cDuo();
                duo.setId_duo(cnn.getRst().getInt("id_duo"));
                // duo.setFecha_duo(cnn.getRst().getString("fecha_duo"));
                // duo.setHora_duo(cnn.getRst().getString("hora_duo"));
                // duo.setEstado_duo(cnn.getRst().getInt("estado_duo"));
                duo.setFecha_hora_ing_duo(cnn.getRst().getString("fecha_hora_ing_duo"));
                duo.setRut_paciente(cnn.getRst().getString("rut_paciente"));
                duo.setNombres_paciente(cnn.getRst().getString("paciente_nombres"));
                duo.setApellidop_paciente(cnn.getRst().getString("paciente_apellidop"));
                duo.setApellidom_paciente(cnn.getRst().getString("paciente_apellidom"));
                lista.add(duo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();
        return lista;
    }

    public String espacio(int cantidad) {
        String cadena = "";

        for (int k = 0; k < cantidad; ++k) {
            cadena = cadena + "&nbsp;";
        }

        return cadena;
    }

    public String espacio2(int cantidad) {
        String cadena = "";

        for (int k = 0; k < cantidad; ++k) {
            cadena = cadena + " ";
        }

        return cadena;
    }

    public String MMDDYYY(String fecha, int MODALIDAD) {
        String cadena = "";
        String DD;
        String MM;
        String YYYY;
        if (MODALIDAD == 1) {
            DD = fecha.substring(0, 2);
            MM = fecha.substring(3, 5);
            YYYY = fecha.substring(6, 10);
            cadena = DD + "/" + MM + "/" + YYYY;
        } else {
            DD = fecha.substring(0, 4);
            MM = fecha.substring(5, 7);
            YYYY = fecha.substring(8, 10);
            cadena = YYYY + "/" + MM + "/" + DD;
        }

        return cadena;
    }

    public ArrayList lista_documento_segun_duo(int id_duo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT \n  id_duo_documento,\n  DOC.id_documento,\n DOC.descripcion_documento\nFROM  schema_uo.duo_documento DUO\nINNER JOIN schema_uo.documento DOC ON (DUO.id_documento=DOC.id_documento)\n WHERE  id_duo='" + id_duo + "';");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cDocumento doc = new cDocumento();
                doc.setDescripcion(this.cnn.getRst().getString("descripcion_documento"));
                doc.setId(this.cnn.getRst().getInt("id_documento"));
                lista.add(doc);
            }
        } catch (SQLException var4) {
            Logger.getLogger(NegocioQ.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_diagnostico_segun_duo() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL(" SELECT    id_diagnostico_duo, descripcion_diagnostico_duo,   tipo_diagnostico_duo, id_duo   FROM  schema_uo.diagnostico_duo   where id_duo  IN (SELECT id_duo  FROM  schema_uo.duo AA WHERE id_derivador=2 and estado_duo NOT IN (99) AND   (fecha_duo+hora_duo) BETWEEN '01-01-2012 00:00:00' AND '12-31-2012 23:59:59'  ) and  tipo_diagnostico_duo IN (1) ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cDiagnostico diag = new cDiagnostico();
                diag.setId_diagnostico(this.cnn.getRst().getInt("id_diagnostico_duo"));
                diag.setId_duo(this.cnn.getRst().getInt("id_duo"));
                diag.setTipo_diagnostico(this.cnn.getRst().getInt("tipo_diagnostico_duo"));
                diag.setDescripcion_diagnostico(this.cnn.getRst().getString("descripcion_diagnostico_duo"));
                lista.add(diag);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        this.cnn.cerrarConexion();
        return lista;
    }

    public ArrayList lista_sesion_kinesiologia(int id_duo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  ses_id, ses_estado, ses_usuario, \n TO_CHAR(ses_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS')as ses_fecha_ingreso, TO_CHAR(ses_fecha_hora,'DD/MM/YYYY HH24:MI:SS')as ses_fecha_hora, \n TO_CHAR(ses_fecha_hora,'DD/MM/YYYY')as ses_fecha,\n TO_CHAR(ses_fecha_hora,'HH24:MI:SS')as ses_hora,\n ses_detalle, ses_duo,\n USU.nombre_usuario,USU.apellidop_usuario,USU.apellidom_usuario\n FROM  schema_uo.kin_sesion\n SES INNER JOIN schema_uo.usuario USU ON(SES.ses_usuario=USU.rut_usuario)\n WHERE  ses_duo='" + id_duo + "' and ses_estado='1'  order by SES.ses_fecha_hora desc  ;");
        this.cnn.conectar();

        cSesionKine ses;
        try {
            for (; this.cnn.getRst().next(); lista.add(ses)) {
                ses = new cSesionKine();
                ses.setId_sesion_kine(this.cnn.getRst().getInt("ses_id"));
                ses.setId_duo(this.cnn.getRst().getInt("ses_duo"));
                ses.setFecha_ingreso_sesion(this.cnn.getRst().getString("ses_fecha_ingreso"));
                ses.setFecha_hora(this.cnn.getRst().getString("ses_fecha_hora"));
                ses.setFecha(this.cnn.getRst().getString("ses_fecha"));
                ses.setHora(this.cnn.getRst().getString("ses_hora"));
                ses.setDetalle(this.cnn.getRst().getString("ses_detalle"));
                ses.setRut_usuario(this.cnn.getRst().getString("ses_usuario"));
                ses.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                ses.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                ses.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                ses.setEstado_sesion(this.cnn.getRst().getInt("ses_estado"));
                if (ses.getEstado_sesion() == 0) {
                    ses.setEstado_desc_sesion("Anulado");
                } else {
                    ses.setEstado_desc_sesion("Activo");
                }
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        return lista;
    }

    public ArrayList lista_sesion_fonouriologa(int id_duo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  ses_id, ses_estado, ses_usuario, TO_CHAR(ses_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS')as ses_fecha_ingreso,"
                + " TO_CHAR(ses_fecha_hora,'DD/MM/YYYY HH24:MI:SS')as ses_fecha_hora,  "
                + " TO_CHAR(ses_fecha_hora,'DD/MM/YYYY')as ses_fecha, TO_CHAR(ses_fecha_hora,"
                + "'HH24:MI:SS')as ses_hora, ses_detalle, ses_duo,"
                + " USU.nombre_usuario,USU.apellidop_usuario,USU.apellidom_usuario"
                + " FROM  schema_uo.fonouriologa_sesion SES INNER JOIN schema_uo.usuario USU ON(SES.ses_usuario=USU.rut_usuario) WHERE "
                + " ses_duo='" + id_duo + "' and ses_estado='1' order by SES.ses_fecha_hora desc  ;");
        this.cnn.conectar();

        cSesionKine ses;
        try {
            for (; this.cnn.getRst().next(); lista.add(ses)) {
                ses = new cSesionKine();
                ses.setId_sesion_kine(this.cnn.getRst().getInt("ses_id"));
                ses.setId_duo(this.cnn.getRst().getInt("ses_duo"));
                ses.setFecha_ingreso_sesion(this.cnn.getRst().getString("ses_fecha_ingreso"));
                ses.setFecha_hora(this.cnn.getRst().getString("ses_fecha_hora"));
                ses.setFecha(this.cnn.getRst().getString("ses_fecha"));
                ses.setHora(this.cnn.getRst().getString("ses_hora"));
                ses.setDetalle(this.cnn.getRst().getString("ses_detalle"));
                ses.setRut_usuario(this.cnn.getRst().getString("ses_usuario"));
                ses.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                ses.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                ses.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                ses.setEstado_sesion(this.cnn.getRst().getInt("ses_estado"));
                if (ses.getEstado_sesion() == 0) {
                    ses.setEstado_desc_sesion("Anulado");
                } else {
                    ses.setEstado_desc_sesion("Activo");
                }
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        return lista;
    }

    public ArrayList lista_sesion_terapeuta(int id_duo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  ses_id, ses_estado, ses_usuario, \n TO_CHAR(ses_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS')as ses_fecha_ingreso, TO_CHAR(ses_fecha_hora,'DD/MM/YYYY HH24:MI:SS')as ses_fecha_hora, \n TO_CHAR(ses_fecha_hora,'DD/MM/YYYY')as ses_fecha,\n TO_CHAR(ses_fecha_hora,'HH24:MI:SS')as ses_hora,\n ses_detalle, ses_duo,\n USU.nombre_usuario,USU.apellidop_usuario,USU.apellidom_usuario\n FROM  schema_uo.terapeuta_sesion\n SES INNER JOIN schema_uo.usuario USU ON(SES.ses_usuario=USU.rut_usuario)\n WHERE  ses_duo='" + id_duo + "' and ses_estado='1'  order by SES.ses_fecha_hora desc ;");
        this.cnn.conectar();

        cSesionKine ses;
        try {
            for (; this.cnn.getRst().next(); lista.add(ses)) {
                ses = new cSesionKine();
                ses.setId_sesion_kine(this.cnn.getRst().getInt("ses_id"));
                ses.setId_duo(this.cnn.getRst().getInt("ses_duo"));
                ses.setFecha_ingreso_sesion(this.cnn.getRst().getString("ses_fecha_ingreso"));
                ses.setFecha_hora(this.cnn.getRst().getString("ses_fecha_hora"));
                ses.setFecha(this.cnn.getRst().getString("ses_fecha"));
                ses.setHora(this.cnn.getRst().getString("ses_hora"));
                ses.setDetalle(this.cnn.getRst().getString("ses_detalle"));
                ses.setRut_usuario(this.cnn.getRst().getString("ses_usuario"));
                ses.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                ses.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                ses.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                ses.setEstado_sesion(this.cnn.getRst().getInt("ses_estado"));
                if (ses.getEstado_sesion() == 0) {
                    ses.setEstado_desc_sesion("Anulado");
                } else {
                    ses.setEstado_desc_sesion("Activo");
                }
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        return lista;
    }
    
    /*code*/
    public ArrayList lista_sesion_social(int id_duo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  ses_id, ses_estado, ses_usuario, \n TO_CHAR(ses_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS')as ses_fecha_ingreso, TO_CHAR(ses_fecha_hora,'DD/MM/YYYY HH24:MI:SS')as ses_fecha_hora, \n TO_CHAR(ses_fecha_hora,'DD/MM/YYYY')as ses_fecha,\n TO_CHAR(ses_fecha_hora,'HH24:MI:SS')as ses_hora,\n ses_detalle, ses_duo,\n USU.nombre_usuario,USU.apellidop_usuario,USU.apellidom_usuario\n FROM  schema_uo.social_sesion\n SES INNER JOIN schema_uo.usuario USU ON(SES.ses_usuario=USU.rut_usuario)\n WHERE  ses_duo='" + id_duo + "' and ses_estado='1'  order by SES.ses_fecha_hora desc ;");
        this.cnn.conectar();

        cSesionKine ses;
        try {
            for (; this.cnn.getRst().next(); lista.add(ses)) {
                ses = new cSesionKine();
                ses.setId_sesion_kine(this.cnn.getRst().getInt("ses_id"));
                ses.setId_duo(this.cnn.getRst().getInt("ses_duo"));
                ses.setFecha_ingreso_sesion(this.cnn.getRst().getString("ses_fecha_ingreso"));
                ses.setFecha_hora(this.cnn.getRst().getString("ses_fecha_hora"));
                ses.setFecha(this.cnn.getRst().getString("ses_fecha"));
                ses.setHora(this.cnn.getRst().getString("ses_hora"));
                ses.setDetalle(this.cnn.getRst().getString("ses_detalle"));
                ses.setRut_usuario(this.cnn.getRst().getString("ses_usuario"));
                ses.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                ses.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                ses.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                ses.setEstado_sesion(this.cnn.getRst().getInt("ses_estado"));
                if (ses.getEstado_sesion() == 0) {
                    ses.setEstado_desc_sesion("Anulado");
                } else {
                    ses.setEstado_desc_sesion("Activo");
                }
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        return lista;
    }

    public ArrayList lista_sesion_nutricionista(int id_duo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  ses_id, ses_estado, ses_usuario, \n TO_CHAR(ses_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS')as ses_fecha_ingreso, TO_CHAR(ses_fecha_hora,'DD/MM/YYYY HH24:MI:SS')as ses_fecha_hora, \n TO_CHAR(ses_fecha_hora,'DD/MM/YYYY')as ses_fecha,\n TO_CHAR(ses_fecha_hora,'HH24:MI:SS')as ses_hora,\n ses_detalle, ses_duo,\n USU.nombre_usuario,USU.apellidop_usuario,USU.apellidom_usuario\n FROM  schema_uo.nutricionista_sesion\n SES INNER JOIN schema_uo.usuario USU ON(SES.ses_usuario=USU.rut_usuario)\n WHERE  ses_duo='" + id_duo + "' and ses_estado='1'  order by SES.ses_fecha_hora desc  ;");
        this.cnn.conectar();

        cSesionKine ses;
        try {
            for (; this.cnn.getRst().next(); lista.add(ses)) {
                ses = new cSesionKine();
                ses.setId_sesion_kine(this.cnn.getRst().getInt("ses_id"));
                ses.setId_duo(this.cnn.getRst().getInt("ses_duo"));
                ses.setFecha_ingreso_sesion(this.cnn.getRst().getString("ses_fecha_ingreso"));
                ses.setFecha_hora(this.cnn.getRst().getString("ses_fecha_hora"));
                ses.setFecha(this.cnn.getRst().getString("ses_fecha"));
                ses.setHora(this.cnn.getRst().getString("ses_hora"));
                ses.setDetalle(this.cnn.getRst().getString("ses_detalle"));
                ses.setRut_usuario(this.cnn.getRst().getString("ses_usuario"));
                ses.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                ses.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                ses.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                ses.setEstado_sesion(this.cnn.getRst().getInt("ses_estado"));
                if (ses.getEstado_sesion() == 0) {
                    ses.setEstado_desc_sesion("Anulado");
                } else {
                    ses.setEstado_desc_sesion("Activo");
                }
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        return lista;
    }

    public ArrayList lista_evaluacion_traumatologia(int id_duo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  tra_id, TO_CHAR(tra_fecha_ingreso,'DD/MM/YYYY')as tra_fecha_ingreso  \nFROM  schema_uo.kin_eva_traumatologia WHERE tra_id_duo='" + id_duo + "' and tra_estado='1' ");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cEvaTraumatologia tra = new cEvaTraumatologia();
                tra.setId_eva_traumatologia(this.cnn.getRst().getInt("tra_id"));
                tra.setFecha_ingreso_trauma(this.cnn.getRst().getString("tra_fecha_ingreso"));
                lista.add(tra);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        return lista;
    }

    public cEvaTraumatologia obtiene_evaluacion_traumatologia(int id_evaluacion) {
        cEvaTraumatologia eva = new cEvaTraumatologia();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  tra_id,tra_id_duo, tra_estado, tra_usuario,\n  to_char(tra_fecha_ingreso,'DD/MM/YYYY')as tra_fecha_ingreso,\n  to_char(tra_fecha_ingreso,'HH24:MI:SS')as tra_hora_ingreso,\n  tra_observacion_inicial, tra_historial, tra_dolor,\n  tra_prueba_especial,tra_palpacion, tra_marcha,\n  tra_plano_frontal, tra_plano_sagital, tra_plano_posterior,\n  tra_movimiento_pasivo, tra_movimiento_activo, tra_observacion,\n  tra_dermatoma, tra_miotoma, tra_reflejo_osteotendineo, tra_test_neurodinamico,\n  tra_diagnostico_imagen, tra_diagnostico_kinesico,\n  to_char(tra_fecha_anulacion,'DD/MM/YYYY HH24:MI:SS')as tra_fecha_anulacion, \n  tra_motivo_anulacion,tra_usuario_anulacion,\n  USU.nombre_usuario,USU.apellidop_usuario,USU.apellidom_usuario\n  FROM  schema_uo.kin_eva_traumatologia TRA INNER JOIN \n schema_uo.usuario USU ON (TRA.tra_usuario=USU.rut_usuario)\n  WHERE tra_id='" + id_evaluacion + "'  ;");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                eva.setId_eva_traumatologia(this.cnn.getRst().getInt("tra_id"));
                eva.setId_duo(this.cnn.getRst().getInt("tra_id_duo"));
                eva.setEstado_trauma(this.cnn.getRst().getInt("tra_estado"));
                eva.setRut_usuario(this.cnn.getRst().getString("tra_usuario"));
                eva.setFecha_ingreso_trauma(this.cnn.getRst().getString("tra_fecha_ingreso"));
                eva.setHora_ingreso_trauma(this.cnn.getRst().getString("tra_hora_ingreso"));
                eva.setObservacion_inicial(this.cnn.getRst().getString("tra_observacion_inicial"));
                eva.setHistorial_usuario(this.cnn.getRst().getString("tra_historial"));
                eva.setDolor(this.cnn.getRst().getString("tra_dolor"));
                eva.setPrueba_especial(this.cnn.getRst().getString("tra_prueba_especial"));
                eva.setPalpacion(this.cnn.getRst().getString("tra_palpacion"));
                eva.setMarcha(this.cnn.getRst().getString("tra_marcha"));
                eva.setPlano_frontal(this.cnn.getRst().getString("tra_plano_frontal"));
                eva.setPlano_sagital(this.cnn.getRst().getString("tra_plano_sagital"));
                eva.setPlano_posterior(this.cnn.getRst().getString("tra_plano_posterior"));
                eva.setMovimiento_activo(this.cnn.getRst().getString("tra_movimiento_activo"));
                eva.setMovimiento_pasivo(this.cnn.getRst().getString("tra_movimiento_pasivo"));
                eva.setObservacion(this.cnn.getRst().getString("tra_observacion"));
                eva.setDermatoma(this.cnn.getRst().getString("tra_dermatoma"));
                eva.setMiotoma(this.cnn.getRst().getString("tra_miotoma"));
                eva.setReflejo_osteotendineo(this.cnn.getRst().getString("tra_reflejo_osteotendineo"));
                eva.setTest_neurodinamico(this.cnn.getRst().getString("tra_test_neurodinamico"));
                eva.setDiagnostico_imagen(this.cnn.getRst().getString("tra_diagnostico_imagen"));
                eva.setDiagnostico_kinesico(this.cnn.getRst().getString("tra_diagnostico_kinesico"));
                eva.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                eva.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                eva.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                if (eva.getEstado_trauma() == 0) {
                    eva.setEstado_desc_trauma("Anulado");
                    eva.setRut_usuario_anula(this.cnn.getRst().getString("ses_usuario_anulacion"));
                    eva.setFecha_anula(this.cnn.getRst().getString("ses_usuario_anulacion"));
                } else {
                    eva.setEstado_desc_trauma("Activo");
                }

                eva.setRut_usuario((String) null);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        return eva;
    }

    public ArrayList lista_evaluacion_neurologia(int id_duo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  neu_id,  neu_id_duo, neu_estado,neu_usuario,\nto_char ( neu_fecha_ingreso,'DD/MM/YYYY')as neu_fecha_ingreso ,\nneu_lesion_evaluada,\nneu_reaccion_apoyo,neu_fecha_anulacion,neu_motivo_anulacion\nFROM schema_uo.kin_eva_neurologia WHERE neu_id_duo='" + id_duo + "'  AND neu_estado='1'  ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cEvaNeurologia neu = new cEvaNeurologia();
                neu.setId_duo(this.cnn.getRst().getInt("neu_id_duo"));
                neu.setId_neuro(this.cnn.getRst().getInt("neu_id"));
                neu.setFecha_ingreso_neuro(this.cnn.getRst().getString("neu_fecha_ingreso"));
                neu.setLesion_evaluada(this.cnn.getRst().getInt("neu_lesion_evaluada"));
                lista.add(neu);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        return lista;
    }

    public ArrayList lista_lesion() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  les_id,les_descripcion,  les_fecha_ingreso, les_categoria\nFROM  schema_uo.kin_neu_lesion WHERE les_estado='1'  ;");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cDiagnostico dia = new cDiagnostico();
                dia.setId_diagnostico(this.cnn.getRst().getInt("les_id"));
                dia.setTipo_diagnostico(this.cnn.getRst().getInt("les_categoria"));
                dia.setDescripcion_diagnostico(this.cnn.getRst().getString("les_descripcion"));
                lista.add(dia);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        return lista;
    }

    public cEvaNeurologia obtiene_evaluacion_neurologia(int id_evaluacion_neurologia) {
        cEvaNeurologia eva = new cEvaNeurologia();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT   neu_id,  neu_id_duo, neu_estado, neu_usuario,   nombre_usuario,apellidop_usuario,apellidom_usuario, \n to_char(neu_fecha_ingreso,'DD/MM/YYYY')as neu_fecha_ingreso, \n to_char(neu_fecha_ingreso,'HH24:MI:SS')as neu_hora_ingreso, \n  neu_lesion_evaluada, neu_lesion,\n neu_ashworth,\n  neu_dato1, neu_dato2, neu_dato3,\n  neu_dato4, neu_dato5, neu_dato6,\n  neu_dato7, neu_dato8, neu_dato9,\n  neu_dato10, neu_dato11,\n  neu_trofismo,  neu_reflejo_osteorendineo, neu_propiocepcion,\n  neu_reaccion_equilibrio, neu_reaccion_enderezamiento, neu_reaccion_apoyo,\n  (case when  neu_trofismo=1 then 'Normal'\n  when  neu_trofismo=0 then 'Atrofia' end ) as neu_trofismo_desc,\n  (case when  neu_propiocepcion=1 then 'Normal'\n  when  neu_propiocepcion=0 then 'Alterada' end ) as neu_propiocepcion_desc,\n   (case when  neu_reaccion_equilibrio=1 then 'Presente'\n  when  neu_reaccion_equilibrio=0 then 'No presente' end ) as neu_reaccion_equilibrio_desc,\n    (case when  neu_reaccion_enderezamiento=1 then 'Presente'\n  when  neu_reaccion_enderezamiento=0 then 'No presente' end ) as neu_reaccion_enderezamiento_desc,\n    (case when  neu_reaccion_apoyo=1 then 'Presente'\n  when  neu_reaccion_apoyo=0 then 'No presente' end ) as neu_reaccion_apoyo_desc,\n  neu_fecha_anulacion, neu_motivo_anulacion, neu_usuario_anulacion,\n  neu_opcion1, neu_opcion2, neu_opcion3,\n  neu_opcion4, neu_opcion5, neu_opcion6,\n  neu_opcion1_desc, neu_opcion2_desc, neu_opcion3_desc,\n  neu_opcion4_desc, neu_opcion5_desc, neu_opcion6_desc ,   LES1.les_descripcion as lesion_evaluada ,\n   LES2.les_descripcion as lesion_medular ,\n    LES3.les_descripcion as tipo_lesion_medular ,\n     LES12.les_descripcion as lesion_otra  ,\n      LES14.les_descripcion as reflejo_ostetendineo_otra \nFROM  schema_uo.kin_eva_neurologia NEU\nINNER JOIN schema_uo.usuario USU ON (NEU.neu_usuario=USU.rut_usuario)\nINNER JOIN schema_uo.kin_neu_lesion LES1 ON (NEU.neu_lesion_evaluada=LES1.les_id)  \nINNER JOIN schema_uo.kin_neu_lesion LES2 ON (NEU.neu_lesion=LES2.les_id)  \nINNER JOIN schema_uo.kin_neu_lesion LES3 ON (NEU.neu_tipo_lesion=LES3.les_id)  \nINNER JOIN schema_uo.kin_neu_lesion LES12 ON (NEU.neu_lesion=LES12.les_id)  \nINNER JOIN schema_uo.kin_neu_lesion LES14 ON (NEU.neu_reflejo_osteorendineo=LES14.les_id)  \nWHERE neu_id='" + id_evaluacion_neurologia + "';");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                eva.setId_neuro(this.cnn.getRst().getInt("neu_id"));
                eva.setId_duo(this.cnn.getRst().getInt("neu_id_duo"));
                eva.setLesion_evaluada(this.cnn.getRst().getInt("neu_lesion_evaluada"));
                eva.setRut_usuario(this.cnn.getRst().getString("neu_usuario"));
                eva.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                eva.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                eva.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                eva.setFecha_ingreso_neuro(this.cnn.getRst().getString("neu_fecha_ingreso"));
                eva.setHora_ingreso_neuro(this.cnn.getRst().getString("neu_hora_ingreso"));
                eva.setLesion(this.cnn.getRst().getInt("neu_lesion"));
                eva.setAshworth(this.cnn.getRst().getString("neu_ashworth"));
                if (eva.getLesion_evaluada() == 1) {
                    eva.setLesion_evaluada_desc(this.cnn.getRst().getString("lesion_evaluada"));
                    eva.setLesion_desc(this.cnn.getRst().getString("lesion_medular"));
                    eva.setAsia(this.cnn.getRst().getString("neu_dato2"));
                    eva.setLesion_tipo_desc(this.cnn.getRst().getString("tipo_lesion_medular"));
                    eva.setReflejo_osteorendineo_desc(this.cnn.getRst().getString("neu_dato1"));
                    eva.setEvaluacion_sensitiva(this.cnn.getRst().getString("neu_dato3"));
                    eva.setSensibilidad(this.cnn.getRst().getString("neu_dato4"));
                    eva.setMotor_index(this.cnn.getRst().getString("neu_dato5"));
                    eva.setContraccion(this.cnn.getRst().getString("neu_dato6"));
                    eva.setSilla_ruedas(this.cnn.getRst().getString("neu_dato7"));
                    eva.setMarcha(this.cnn.getRst().getString("neu_dato11"));
                    eva.setNivel_motor(this.cnn.getRst().getString("neu_dato9"));
                    eva.setNivel_neurologico(this.cnn.getRst().getString("neu_dato10"));
                    eva.setNivel_sentivo(this.cnn.getRst().getString("neu_dato8"));
                    eva.setOp1(this.cnn.getRst().getInt("neu_opcion1"));
                    eva.setOp2(this.cnn.getRst().getInt("neu_opcion2"));
                    eva.setOp3(this.cnn.getRst().getInt("neu_opcion3"));
                    eva.setOp4(this.cnn.getRst().getInt("neu_opcion4"));
                    eva.setOp5(this.cnn.getRst().getInt("neu_opcion5"));
                    eva.setOp6(this.cnn.getRst().getInt("neu_opcion6"));
                    eva.setOp1_desc(this.cnn.getRst().getString("neu_opcion1_desc"));
                    eva.setOp2_desc(this.cnn.getRst().getString("neu_opcion2_desc"));
                    eva.setOp3_desc(this.cnn.getRst().getString("neu_opcion3_desc"));
                    eva.setOp4_desc(this.cnn.getRst().getString("neu_opcion4_desc"));
                    eva.setOp5_desc(this.cnn.getRst().getString("neu_opcion5_desc"));
                    eva.setOp6_desc(this.cnn.getRst().getString("neu_opcion6_desc"));
                } else {
                    eva.setLesion_evaluada_desc(this.cnn.getRst().getString("lesion_evaluada"));
                    eva.setLesion_desc(this.cnn.getRst().getString("lesion_otra"));
                    eva.setMotoneurona(this.cnn.getRst().getString("neu_dato1"));
                    eva.setExtrapiramiral(this.cnn.getRst().getString("neu_dato2"));
                    eva.setPostura(this.cnn.getRst().getString("neu_dato3"));
                    eva.setFuerza(this.cnn.getRst().getString("neu_dato4"));
                    eva.setTono_muscular(this.cnn.getRst().getString("neu_dato5"));
                    eva.setTrofismo_desc(this.cnn.getRst().getString("neu_trofismo_desc"));
                    eva.setTrofismo_adicional(this.cnn.getRst().getString("neu_dato6"));
                    eva.setEess(this.cnn.getRst().getString("neu_dato7"));
                    eva.setEeii(this.cnn.getRst().getString("neu_dato8"));
                    eva.setReflejo_osteorendineo_desc(this.cnn.getRst().getString("reflejo_ostetendineo_otra"));
                    eva.setPropiocepcion_desc(this.cnn.getRst().getString("neu_propiocepcion_desc"));
                    eva.setPropiocepcion_adicional(this.cnn.getRst().getString("neu_dato9"));
                    eva.setTransicion(this.cnn.getRst().getString("neu_dato10"));
                    eva.setReaccion_apoyo_desc(this.cnn.getRst().getString("neu_reaccion_apoyo_desc"));
                    eva.setReaccion_enderezamiento_desc(this.cnn.getRst().getString("neu_reaccion_enderezamiento_desc"));
                    eva.setReaccion_equilibrio_desc(this.cnn.getRst().getString("neu_reaccion_equilibrio_desc"));
                    eva.setMarcha(this.cnn.getRst().getString("neu_dato11"));
                    eva.setOp1(this.cnn.getRst().getInt("neu_opcion1"));
                    eva.setOp2(this.cnn.getRst().getInt("neu_opcion2"));
                    eva.setOp3(this.cnn.getRst().getInt("neu_opcion3"));
                    eva.setOp4(this.cnn.getRst().getInt("neu_opcion4"));
                    eva.setOp1_desc(this.cnn.getRst().getString("neu_opcion1_desc"));
                    eva.setOp2_desc(this.cnn.getRst().getString("neu_opcion2_desc"));
                    eva.setOp3_desc(this.cnn.getRst().getString("neu_opcion3_desc"));
                    eva.setOp4_desc(this.cnn.getRst().getString("neu_opcion4_desc"));
                }
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        return eva;
    }

    public ArrayList lista_evaluacion_respiratoria(int id_duo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  res_id, TO_CHAR(res_fecha_ingreso,'DD/MM/YYYY')as res_fecha_ingreso  \nFROM  schema_uo.kin_eva_respiratoria WHERE res_duo='" + id_duo + "' and res_estado='1' ");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cEvaRespiratoria res = new cEvaRespiratoria();
                res.setId_duo(this.cnn.getRst().getInt("res_id"));
                res.setFecha_ingreso(this.cnn.getRst().getString("res_fecha_ingreso"));
                res.setId_respi(this.cnn.getRst().getInt("res_id"));
                lista.add(res);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        return lista;
    }

    public cEvaRespiratoria obtiene_evaluacion_respiratoria(int id_evaluacion_respiratoria) {
        cEvaRespiratoria res = new cEvaRespiratoria();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT \n  res_id, res_estado,res_usuario,USU.nombre_usuario,USU.apellidop_usuario,USU.apellidom_usuario,  TO_CHAR(res_fecha_ingreso,'DD/MM/YYYY')AS res_fecha_ingreso,  TO_CHAR(res_fecha_ingreso,'HH24:MI:SS')AS res_hora_ingreso ,  res_duo,   res_oxigeno,(CASE WHEN res_oxigeno=1 THEN 'SI'   WHEN res_oxigeno=2 THEN 'NO' END) as res_oxigeno_desc  , res_oxigeno_det,   res_via_venosa,(CASE WHEN res_via_venosa=1 THEN 'SI'   WHEN res_via_venosa=2 THEN 'NO'   END) AS res_via_venosa_desc,   res_via_urinaria, (CASE WHEN res_via_urinaria=1 THEN 'SI'   WHEN res_via_urinaria=2 THEN 'NO'   END) AS res_via_urinaria_desc,   res_via_urinaria_det, res_vigil, (CASE WHEN res_vigil=1 THEN 'SI'   WHEN res_vigil=2 THEN 'NO'   END) AS res_vigil_desc, res_estado_conciencia,\n  (CASE WHEN res_estado_conciencia=1 THEN 'Obnibulado'   WHEN res_estado_conciencia=2 THEN 'Soporoso'   WHEN res_estado_conciencia=3 THEN 'Comatoso'   END) AS res_estado_conciencia_desc  ,  res_alteracion_lenguaje, (CASE WHEN res_alteracion_lenguaje=1 THEN 'Si'   WHEN res_alteracion_lenguaje=2 THEN 'No' END) AS res_alteracion_lenguaje_desc  ,     res_estimulo_verbal,  (CASE WHEN res_estimulo_verbal=1 THEN 'Si'   WHEN res_estimulo_verbal=2 THEN 'No' END) AS res_estimulo_verbal_desc  ,   res_estimulo_visual,  (CASE WHEN res_estimulo_visual=1 THEN 'Si'   WHEN res_estimulo_visual=2 THEN 'No' END) AS res_estimulo_visual_desc  ,   res_ubicacion_temporal,  (CASE WHEN res_ubicacion_temporal=1 THEN 'Si'   WHEN res_ubicacion_temporal=2 THEN 'No' END) AS res_ubicacion_temporal_desc  ,   res_ubicacion_espacial,  (CASE WHEN res_ubicacion_espacial=1 THEN 'Si'   WHEN res_ubicacion_espacial=2 THEN 'No' END) AS res_ubicacion_espacial_desc  ,   res_postura,  (CASE WHEN res_postura=1 THEN 'Simétrico'   WHEN res_postura=2 THEN 'Asimétrico' END) AS res_postura_desc  ,   res_eess,  res_eeii,  res_deformidad,  res_estado_nutricional,  (CASE WHEN res_estado_nutricional=1 THEN 'Caquético'   WHEN res_estado_nutricional=2 THEN 'Normal'   WHEN res_estado_nutricional=3 THEN 'Sobrepeso'   WHEN res_estado_nutricional=4 THEN 'Obeso'   END) AS res_estado_nutricional_desc  ,  res_coloracion_piel,  (CASE WHEN res_coloracion_piel=1 THEN 'Normal'   WHEN res_coloracion_piel=2 THEN 'Cianótica'   END) AS res_coloracion_piel_desc  ,  res_cianosis,  (CASE WHEN res_cianosis=1 THEN 'Perisférica'   WHEN res_cianosis=2 THEN 'Central'   END) AS res_cianosis_desc  ,  res_ep_normal,  res_ep_escara,  res_ep_cicatriz,  res_ep_hematoma,  res_ep_petequia,  res_ep_edema,  res_ep_normal_det,  res_ep_escara_det,  res_ep_cicatriz_det,  res_ep_hematoma_det,  res_ep_petequia_det,  res_ep_edema_det,  res_patron_respiratorio,  (CASE WHEN res_patron_respiratorio=1 THEN 'Diafragmático'   WHEN res_patron_respiratorio=2 THEN 'Costal Inferior'   WHEN res_patron_respiratorio=3 THEN 'Costal Superior'   END) AS res_patron_respiratorio_desc  ,  res_respirador,  (CASE WHEN res_respirador=1 THEN 'Nasal'   WHEN res_respirador=2 THEN 'Bucal'   WHEN res_respirador=3 THEN 'Ninguno'   END) AS res_respirador_desc  ,  res_dificultad_respiratoria,  (CASE WHEN res_dificultad_respiratoria=1 THEN 'Si'   WHEN res_dificultad_respiratoria=2 THEN 'No'   END) AS res_dificultad_respiratoria_desc,   res_musculatura_accesoria,  (CASE WHEN res_musculatura_accesoria=1 THEN 'Si'   WHEN res_musculatura_accesoria=2 THEN 'No'   END) AS res_musculatura_accesoria_desc,   res_musculatura_accesoria_det,\n  res_aleteo_nasal,  (CASE WHEN res_aleteo_nasal=1 THEN 'Si'   WHEN res_aleteo_nasal=2 THEN 'No'   END) AS res_aleteo_nasal_desc,   res_aleteo_costal,  (CASE WHEN res_aleteo_costal=1 THEN 'Si'   WHEN res_aleteo_costal=2 THEN 'No'   END) AS res_aleteo_costal_desc,   res_temperatura,  (CASE WHEN res_temperatura=1 THEN 'Febril'   WHEN res_temperatura=2 THEN 'Afebril'   END) AS res_temperatura_desc,   res_elasticidad,  (CASE WHEN res_elasticidad=1 THEN 'Normal'   WHEN res_elasticidad=2 THEN 'Restringida'   END) AS res_elasticidad_desc,   res_elasticidad_det,  res_restriccion_miofascial,  res_llene_capilar,  (CASE WHEN res_llene_capilar=1 THEN 'Menor 2 seg.'   WHEN res_llene_capilar=2 THEN 'Mayor 2 seg.'   END) AS res_llene_capilar_desc,   res_edema,  (CASE WHEN res_edema=1 THEN 'Blando'   WHEN res_edema=2 THEN 'Duro'   WHEN res_edema=3 THEN 'Ninguno'   END) AS res_edema_desc,   res_dolor,  (CASE WHEN res_dolor=1 THEN 'Presente'   WHEN res_dolor=2 THEN 'No presente'   END) AS res_dolor_desc,   res_eva,  (CASE WHEN res_eva=0 THEN '0/10'   WHEN res_eva=1 THEN '1/10'   WHEN res_eva=2 THEN '2/10'   WHEN res_eva=3 THEN '3/10'   WHEN res_eva=4 THEN '4/10'   WHEN res_eva=5 THEN '5/10'   WHEN res_eva=6 THEN '6/10'   WHEN res_eva=7 THEN '7/10'   WHEN res_eva=8 THEN '8/10'   WHEN res_eva=9 THEN '9/10'   WHEN res_eva=10 THEN '10/10'   END) AS res_eva_desc,   res_desalineacion,  res_frecuencia_cardiaca,  res_frecuencia_respiratoria,  res_saturacion,  res_aus_pi_ls,  res_aus_pi_li,  res_aus_pd_ls,  res_aus_pd_lm,  res_aus_pd_li,  (CASE WHEN res_aus_pi_ls=1 THEN 'MP Presente'   WHEN res_aus_pi_ls=2 THEN 'MP Disminuido'   WHEN res_aus_pi_ls=3 THEN 'MP No Presente'   END) AS res_aus_pi_ls_desc,   (CASE WHEN res_aus_pi_li=1 THEN 'MP Presente'   WHEN res_aus_pi_li=2 THEN 'MP Disminuido'   WHEN res_aus_pi_ls=3 THEN 'MP No Presente'   END) AS res_aus_pi_li_desc,   (CASE WHEN res_aus_pd_ls=1 THEN 'MP Presente'   WHEN res_aus_pd_ls=2 THEN 'MP Disminuido'   WHEN res_aus_pi_ls=3 THEN 'MP No Presente'   END) AS res_aus_pd_ls_desc,   (CASE WHEN res_aus_pd_lm=1 THEN 'MP Presente'   WHEN res_aus_pd_lm=2 THEN 'MP Disminuido'   WHEN res_aus_pi_ls=3 THEN 'MP No Presente'   END) AS res_aus_pd_lm_desc,   (CASE WHEN res_aus_pd_li=1 THEN 'MP Presente'   WHEN res_aus_pd_li=2 THEN 'MP Disminuido'   WHEN res_aus_pi_ls=3 THEN 'MP No Presente'   END) AS res_aus_pd_li_desc,   res_sibilancia,  res_roncus,  res_estertor_traqueal,  res_crepitacion_fina,  res_crepicitacion_gruesa,  (CASE WHEN res_sibilancia=1 THEN ' X '   WHEN res_sibilancia=2 THEN ''   END) AS res_sibilancia_desc,   (CASE WHEN res_roncus=1 THEN ' X '   WHEN res_roncus=2 THEN ''   END) AS res_roncus_desc,   (CASE WHEN res_estertor_traqueal=1 THEN ' X '   WHEN res_estertor_traqueal=2 THEN ''   END) AS res_estertor_traqueal_desc,   (CASE WHEN res_crepitacion_fina=1 THEN ' X '   WHEN res_crepitacion_fina=2 THEN ''   END) AS res_crepitacion_fina_desc,   (CASE WHEN res_crepicitacion_gruesa=1 THEN ' X '   WHEN res_crepicitacion_gruesa=2 THEN ''   END) AS res_crepicitacion_gruesa_desc,   res_ra_pi_ls,  res_ra_pi_li,  res_ra_pd_ls,  res_ra_pd_lm,  res_ra_pd_li,  res_tos_presencia,  (CASE WHEN res_tos_presencia=1 THEN 'Presente'   WHEN res_tos_presencia=2 THEN 'No presente'   END) AS res_tos_presencia_desc,   res_tos_produccion,  (CASE WHEN res_tos_produccion=1 THEN 'Productiva'   WHEN res_tos_produccion=2 THEN 'No productiva'   END) AS res_tos_produccion_desc,   res_secrecion_cantidad,  (CASE WHEN res_secrecion_cantidad=1 THEN 'Abundante'   WHEN res_secrecion_cantidad=2 THEN 'Moderada'   WHEN res_secrecion_cantidad=3 THEN 'Baja'   END) AS res_secrecion_cantidad_desc,   res_secrecion_coloracion,  (CASE WHEN res_secrecion_coloracion=1 THEN 'Blanca'   WHEN res_secrecion_coloracion=2 THEN 'Verde'   WHEN res_secrecion_coloracion=3 THEN 'Amarilla'   WHEN res_secrecion_coloracion=4 THEN 'Café'   END) AS res_secrecion_coloracion_desc,   res_secrecion_tipo,   (CASE WHEN res_secrecion_tipo=1 THEN 'Serosa'   WHEN res_secrecion_tipo=2 THEN 'Mucosa'   WHEN res_secrecion_tipo=3 THEN 'Mucopurulenta'   WHEN res_secrecion_tipo=4 THEN 'Infección'   END) AS res_secrecion_tipo_desc  FROM  schema_uo.kin_eva_respiratoria RES INNER JOIN \nschema_uo.usuario USU ON (RES.res_usuario=USU.rut_usuario)\nWHERE res_id='" + id_evaluacion_respiratoria + "' ;");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                res.setFecha_ingreso(this.cnn.getRst().getString("res_fecha_ingreso"));
                res.setHora_ingreso(this.cnn.getRst().getString("res_hora_ingreso"));
                res.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                res.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                res.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                res.setRut_usuario(this.cnn.getRst().getString("res_usuario"));
                res.setId_duo(this.cnn.getRst().getInt("res_duo"));
                res.setOxigeno(this.cnn.getRst().getInt("res_oxigeno"));
                res.setOxigeno_det(this.cnn.getRst().getString("res_oxigeno_det"));
                res.setVia_venosa(this.cnn.getRst().getInt("res_via_venosa"));
                res.setVia_urinaria(this.cnn.getRst().getInt("res_via_urinaria"));
                res.setVia_urinaria_det(this.cnn.getRst().getString("res_via_urinaria_det"));
                res.setVigil(this.cnn.getRst().getInt("res_vigil"));
                res.setEstado_conciencia(this.cnn.getRst().getInt("res_estado_conciencia"));
                res.setAlteracion_lenguaje(this.cnn.getRst().getInt("res_alteracion_lenguaje"));
                res.setEstimulo_verbal(this.cnn.getRst().getInt("res_estimulo_verbal"));
                res.setEstimulo_visual(this.cnn.getRst().getInt("res_estimulo_visual"));
                res.setUbicacion_temporal(this.cnn.getRst().getInt("res_ubicacion_temporal"));
                res.setUbicacion_espacial(this.cnn.getRst().getInt("res_ubicacion_espacial"));
                res.setPostura(this.cnn.getRst().getInt("res_postura"));
                res.setEESS(this.cnn.getRst().getString("res_eess"));
                res.setEEII(this.cnn.getRst().getString("res_eeii"));
                res.setDeformidad(this.cnn.getRst().getString("res_deformidad"));
                res.setEstado_nutricional(this.cnn.getRst().getInt("res_estado_nutricional"));
                res.setColoracion_piel(this.cnn.getRst().getInt("res_coloracion_piel"));
                res.setCianosis(this.cnn.getRst().getInt("res_cianosis"));
                res.setEp_normal(this.cnn.getRst().getInt("res_ep_normal"));
                res.setEp_escara(this.cnn.getRst().getInt("res_ep_escara"));
                res.setEp_cicatriz(this.cnn.getRst().getInt("res_ep_cicatriz"));
                res.setEp_hematoma(this.cnn.getRst().getInt("res_ep_hematoma"));
                res.setEp_petequia(this.cnn.getRst().getInt("res_ep_petequia"));
                res.setEp_edema(this.cnn.getRst().getInt("res_ep_edema"));
                res.setEp_normal_det(this.cnn.getRst().getString("res_ep_normal_det"));
                res.setEp_escara_det(this.cnn.getRst().getString("res_ep_escara_det"));
                res.setEp_cicatriz_det(this.cnn.getRst().getString("res_ep_cicatriz_det"));
                res.setEp_hematoma_det(this.cnn.getRst().getString("res_ep_hematoma_det"));
                res.setEp_petequia_det(this.cnn.getRst().getString("res_ep_petequia_det"));
                res.setEp_edema_det(this.cnn.getRst().getString("res_ep_edema_det"));
                res.setPatron_respiratorio(this.cnn.getRst().getInt("res_patron_respiratorio"));
                res.setRespirador(this.cnn.getRst().getInt("res_respirador"));
                res.setDificultad_respiratoria(this.cnn.getRst().getInt("res_dificultad_respiratoria"));
                res.setMusculatura_accesoria(this.cnn.getRst().getInt("res_musculatura_accesoria"));
                res.setMusculatura_accesoria_det(this.cnn.getRst().getString("res_musculatura_accesoria_det"));
                res.setAleteo_nasal(this.cnn.getRst().getInt("res_aleteo_nasal"));
                res.setAleteo_costal(this.cnn.getRst().getInt("res_aleteo_costal"));
                res.setTemperatura(this.cnn.getRst().getInt("res_temperatura"));
                res.setElasticidad(this.cnn.getRst().getInt("res_elasticidad"));
                res.setElasticidad_det(this.cnn.getRst().getString("res_elasticidad_det"));
                res.setRestriccion_miofascial(this.cnn.getRst().getString("res_restriccion_miofascial"));
                res.setLlene_capilar(this.cnn.getRst().getInt("res_llene_capilar"));
                res.setEdema(this.cnn.getRst().getInt("res_edema"));
                res.setDolor(this.cnn.getRst().getInt("res_dolor"));
                res.setEVA(this.cnn.getRst().getInt("res_eva"));
                res.setDesalineacion(this.cnn.getRst().getString("res_desalineacion"));
                res.setFrecuencia_cardiaca(this.cnn.getRst().getString("res_frecuencia_cardiaca"));
                res.setFrecuencia_respiratoria(this.cnn.getRst().getString("res_frecuencia_respiratoria"));
                res.setSaturacion(this.cnn.getRst().getString("res_saturacion"));
                res.setAus_pi_ls(this.cnn.getRst().getInt("res_aus_pi_ls"));
                res.setAus_pi_li(this.cnn.getRst().getInt("res_aus_pi_li"));
                res.setAus_pd_ls(this.cnn.getRst().getInt("res_aus_pd_ls"));
                res.setAus_pd_lm(this.cnn.getRst().getInt("res_aus_pd_lm"));
                res.setAus_pd_li(this.cnn.getRst().getInt("res_aus_pd_li"));
                res.setSibilancia(this.cnn.getRst().getInt("res_sibilancia"));
                res.setRoncus(this.cnn.getRst().getInt("res_roncus"));
                res.setEstertor_traqueal(this.cnn.getRst().getInt("res_estertor_traqueal"));
                res.setCrepitacion_fina(this.cnn.getRst().getInt("res_crepitacion_fina"));
                res.setCrepitacion_gruesa(this.cnn.getRst().getInt("res_crepicitacion_gruesa"));
                res.setRa_pi_ls(this.cnn.getRst().getString("res_ra_pi_ls"));
                res.setRa_pi_li(this.cnn.getRst().getString("res_ra_pi_li"));
                res.setRa_pd_ls(this.cnn.getRst().getString("res_ra_pd_ls"));
                res.setRa_pd_lm(this.cnn.getRst().getString("res_ra_pd_lm"));
                res.setRa_pd_li(this.cnn.getRst().getString("res_ra_pd_li"));
                res.setTos_presencia(this.cnn.getRst().getInt("res_tos_presencia"));
                res.setTos_produccion(this.cnn.getRst().getInt("res_tos_produccion"));
                res.setSecrecion_cantidad(this.cnn.getRst().getInt("res_secrecion_cantidad"));
                res.setSecrecion_coloracion(this.cnn.getRst().getInt("res_secrecion_coloracion"));
                res.setSecrecion_tipo(this.cnn.getRst().getInt("res_secrecion_tipo"));
                res.setOxigeno_desc(this.cnn.getRst().getString("res_oxigeno_desc"));
                res.setVia_venosa_desc(this.cnn.getRst().getString("res_via_venosa_desc"));
                res.setVia_urinaria_desc(this.cnn.getRst().getString("res_via_urinaria_desc"));
                res.setVigil_desc(this.cnn.getRst().getString("res_vigil_desc"));
                res.setEstado_conciencia_desc(this.cnn.getRst().getString("res_estado_conciencia_desc"));
                res.setAlteracion_lenguaje_desc(this.cnn.getRst().getString("res_alteracion_lenguaje_desc"));
                res.setEstimulo_verbal_desc(this.cnn.getRst().getString("res_estimulo_verbal_desc"));
                res.setEstimulo_visual_desc(this.cnn.getRst().getString("res_estimulo_visual_desc"));
                res.setUbicacion_temporal_desc(this.cnn.getRst().getString("res_ubicacion_temporal_desc"));
                res.setUbicacion_espacial_desc(this.cnn.getRst().getString("res_ubicacion_espacial_desc"));
                res.setPostura_desc(this.cnn.getRst().getString("res_postura_desc"));
                res.setEstado_nutricional_desc(this.cnn.getRst().getString("res_estado_nutricional_desc"));
                res.setColoracion_piel_desc(this.cnn.getRst().getString("res_coloracion_piel_desc"));
                res.setCianosis_desc(this.cnn.getRst().getString("res_cianosis_desc"));
                res.setPatron_respiratorio_desc(this.cnn.getRst().getString("res_patron_respiratorio_desc"));
                res.setRespirador_desc(this.cnn.getRst().getString("res_respirador_desc"));
                res.setDificultad_respiratoria_desc(this.cnn.getRst().getString("res_dificultad_respiratoria_desc"));
                res.setMusculatura_accesoria_desc(this.cnn.getRst().getString("res_musculatura_accesoria_desc"));
                res.setAleteo_nasal_desc(this.cnn.getRst().getString("res_aleteo_nasal_desc"));
                res.setAleteo_costal_desc(this.cnn.getRst().getString("res_aleteo_costal_desc"));
                res.setTemperatura_desc(this.cnn.getRst().getString("res_temperatura_desc"));
                res.setElasticidad_desc(this.cnn.getRst().getString("res_elasticidad_desc"));
                res.setLlene_capilar_desc(this.cnn.getRst().getString("res_llene_capilar_desc"));
                res.setEdema_desc(this.cnn.getRst().getString("res_edema_desc"));
                res.setDolor_desc(this.cnn.getRst().getString("res_dolor_desc"));
                res.setEVA_desc(this.cnn.getRst().getString("res_eva_desc"));
                res.setAus_pi_ls_desc(this.cnn.getRst().getString("res_aus_pi_ls_desc"));
                res.setAus_pi_li_desc(this.cnn.getRst().getString("res_aus_pi_li_desc"));
                res.setAus_pd_ls_desc(this.cnn.getRst().getString("res_aus_pd_ls_desc"));
                res.setAus_pd_lm_desc(this.cnn.getRst().getString("res_aus_pd_lm_desc"));
                res.setAus_pd_li_desc(this.cnn.getRst().getString("res_aus_pd_li_desc"));
                res.setSibilancia_desc(this.cnn.getRst().getString("res_sibilancia_desc"));
                res.setRoncus_desc(this.cnn.getRst().getString("res_roncus_desc"));
                res.setEstertor_traqueal_desc(this.cnn.getRst().getString("res_estertor_traqueal_desc"));
                res.setCrepitacion_fina_desc(this.cnn.getRst().getString("res_crepitacion_fina_desc"));
                res.setCrepitacion_gruesa_desc(this.cnn.getRst().getString("res_crepicitacion_gruesa_desc"));
                res.setTos_presencia_desc(this.cnn.getRst().getString("res_tos_presencia_desc"));
                res.setTos_produccion_desc(this.cnn.getRst().getString("res_tos_produccion_desc"));
                res.setSecrecion_cantidad_desc(this.cnn.getRst().getString("res_secrecion_cantidad_desc"));
                res.setSecrecion_coloracion_desc(this.cnn.getRst().getString("res_secrecion_coloracion_desc"));
                res.setSecrecion_tipo_desc(this.cnn.getRst().getString("res_secrecion_tipo_desc"));
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        return res;
    }

    public ArrayList lista_contacto(String paciente_rut) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT \n  id_fono_direccion_paciente, rut_paciente,\n  fono1_fono_direccion_paciente, fono2_fono_direccion_paciente,\n direccion_fono_direccion_paciente,\n  fecha_transaccion_fono_direccion_paciente, usuario_fono_direccion_paciente,\n  ip_fono_direccion_paciente, id_comuna, COM.comuna_descripcion,\n  contacto_nombre,  contacto_parentesco\n FROM schema_urgencia.paciente_contacto CON\n INNER JOIN schemaoirs.comuna COM ON (CON.id_comuna=COM.comuna_codigo)\n WHERE rut_paciente='" + paciente_rut + "' and contacto_estado='1' ");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cPaciente pac = new cPaciente();
                pac.setDireccion(this.cnn.getRst().getString("direccion_fono_direccion_paciente"));
                pac.setTelefono1(this.cnn.getRst().getString("fono1_fono_direccion_paciente"));
                pac.setTelefono2(this.cnn.getRst().getString("fono2_fono_direccion_paciente"));
                pac.setComuna_descri(this.cnn.getRst().getString("comuna_descripcion"));
                pac.setRut_paciente(this.cnn.getRst().getString("rut_paciente"));
                pac.setNombres_paciente(this.cnn.getRst().getString("contacto_nombre"));
                pac.setParentesco_desc(this.cnn.getRst().getString("contacto_parentesco"));
                pac.setId_contacto(this.cnn.getRst().getInt("id_fono_direccion_paciente"));
                lista.add(pac);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        return lista;
    }

    public ArrayList lista_seguimiento(int id_duo) {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  seg_id, seg_duo_id, seg_usuario,\nTO_CHAR(seg_fecha_ingreso,'DD/MM/YYYY') AS seg_fecha_ingreso  ,\nTO_CHAR(seg_fecha,'DD/MM/YYYY') AS seg_fecha, seg_descripcion\nFROM  schema_uo.registro_social_seguimiento WHERE \nseg_duo_id='" + id_duo + "' AND seg_estado='1'");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cRegistroSeguimiento reg = new cRegistroSeguimiento();
                reg.setFecha_seguimiento(this.cnn.getRst().getString("seg_fecha"));
                reg.setDescripcion(this.cnn.getRst().getString("seg_descripcion"));
                reg.setId_registro_id(this.cnn.getRst().getInt("seg_duo_id"));
                reg.setId_seguimiento(this.cnn.getRst().getInt("seg_id"));
                lista.add(reg);
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        return lista;
    }

    public cRegistroSocial obtiene_registro_social(int id_duo) {
        cRegistroSocial reg = new cRegistroSocial();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("SELECT  reg_id,reg_usuario, to_char(reg_fecha_ingresa,'DD/MM/YYYY HH24:MI:SS') as reg_fecha_ingresa,  reg_estado, reg_estado_civil, (case \n when reg_estado_civil=1 then 'Soltero/a'\n when reg_estado_civil=2 then 'Casado/a'\n when reg_estado_civil=3 then 'Divorciado/a'\n when reg_estado_civil=4 then 'Separado/a'\n when reg_estado_civil=4 then 'Viudo/a'\n else '-' end)as estado_civil_desc,\n reg_situacion_laboral,\n (CASE WHEN reg_situacion_laboral=1 THEN 'Activo'\n WHEN reg_situacion_laboral=2 THEN 'Cesante'\n WHEN reg_situacion_laboral=3 THEN 'Jubilado'\n WHEN reg_situacion_laboral=0 THEN 'Otro'\n END) AS situacion_laboral_desc,\n reg_institucionalizado, reg_nombre_institucion,\n reg_vive, reg_hijos, reg_hijos_cantidad,\n reg_situacion,reg_plan, reg_duo,   \n reg_hospital_origen, reg_asistente_social,\n to_char(reg_fecha_egreso,'DD/MM/YYYY' ) as reg_fecha_egreso , reg_destino ,\n descripcion_destino, reg_diagnostico  FROM  schema_uo.registro_social REG\n INNER JOIN schema_uo.destino DES  ON (REG.reg_destino=DES.id_destino)\n WHERE  reg_duo='" + id_duo + "' and reg_estado='1'  ;");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                reg.setId_registro(this.cnn.getRst().getInt("reg_id"));
                reg.setRut_usuario(this.cnn.getRst().getString("reg_usuario"));
                reg.setFecha_ingresa(this.cnn.getRst().getString("reg_fecha_ingresa"));
                reg.setEstado(this.cnn.getRst().getInt("reg_estado"));
                reg.setEstado_civil(this.cnn.getRst().getInt("reg_estado_civil"));
                reg.setSituacion(this.cnn.getRst().getString("reg_situacion"));
                reg.setSituacion_laboral(this.cnn.getRst().getInt("reg_situacion_laboral"));
                reg.setSituacion_laboral_desc(this.cnn.getRst().getString("situacion_laboral_desc"));
                reg.setInstitucionalizado(this.cnn.getRst().getInt("reg_institucionalizado"));
                if (reg.getInstitucionalizado() == 1) {
                    reg.setInstitucionalizado_desc("Si");
                } else {
                    reg.setInstitucionalizado_desc("No");
                }

                reg.setInstitucion_nombre(this.cnn.getRst().getString("reg_nombre_institucion"));
                reg.setHijos(this.cnn.getRst().getInt("reg_hijos"));
                reg.setVive(this.cnn.getRst().getInt("reg_vive"));
                if (reg.getVive() == 1) {
                    reg.setVive_desc("Solo");
                } else {
                    reg.setVive_desc("Con otros");
                }

                reg.setHijos_cantidad(this.cnn.getRst().getInt("reg_hijos_cantidad"));
                reg.setId_duo(this.cnn.getRst().getInt("reg_duo"));
                reg.setHijos(this.cnn.getRst().getInt("reg_hijos"));
                if (reg.getHijos() == 1) {
                    reg.setHijos_desc("Si");
                } else {
                    reg.setHijos_desc("No");
                }

                reg.setPlan(this.cnn.getRst().getString("reg_plan"));
                reg.setHijos_cantidad(this.cnn.getRst().getInt("reg_hijos_cantidad"));
                reg.setHospital_origen_desc(this.cnn.getRst().getString("reg_hospital_origen"));
                reg.setDestino(this.cnn.getRst().getInt("reg_destino"));
                if (this.cnn.getRst().getString("reg_fecha_egreso") != null) {
                    reg.setFecha_egreso(this.cnn.getRst().getString("reg_fecha_egreso"));
                    if (reg.getFecha_egreso().equals("01/01/1900")) {
                        reg.setFecha_egreso("");
                    }
                }

                reg.setEstado_civil_desc(this.cnn.getRst().getString("estado_civil_desc"));
                reg.setNombre_asistente(this.cnn.getRst().getString("reg_asistente_social"));
                reg.setDestino_desc(this.cnn.getRst().getString("descripcion_destino"));
                reg.setDiagnostico(this.cnn.getRst().getString("reg_diagnostico"));
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        return reg;
    }

    public ArrayList lista_unidad_medida() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL(" SELECT  uni_id, uni_descripcion,\n uni_abreviacion, uni_estado, uni_fecha_ingreso\n FROM  schema_abastecimiento.unidad_medida \n WHERE uni_estado='1' ; ");
        this.cnn.conectar();

        try {
            while (this.cnn.getRst().next()) {
                cUnidadMedida uni = new cUnidadMedida();
                uni.setAbreviacion(this.cnn.getRst().getString("uni_abreviacion"));
                uni.setDescripcion(this.cnn.getRst().getString("uni_descripcion"));
                uni.setId_unidad_medida(this.cnn.getRst().getInt("uni_id"));
                lista.add(uni);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

        return lista;
    }

    public ArrayList lista_receta_detalle(cReceta rec) {
        ArrayList lista = new ArrayList();
        String resto_sql = "";
        if (rec.getId_duo() != 0) {
            resto_sql = resto_sql + " rec_duo='" + rec.getId_duo() + "'  AND";
        }

        if (rec.getId_receta() != 0) {
            resto_sql = resto_sql + " rec_receta='" + rec.getId_receta() + "'  AND";
        }

        if (!rec.getFecha().equals("")) {
            resto_sql = resto_sql + " rec_fecha='" + rec.getFecha() + "' AND";
        }

        if (resto_sql.length() > 4) {
            resto_sql = resto_sql.substring(0, resto_sql.length() - 3);
            this.configurarConexion("");
            this.cnn.setEsSelect(true);
            this.cnn.setSentenciaSQL(" SELECT  rec_id,rec_receta, rec_usuario,\n  TO_CHAR(rec_fecha_ingreso,'DD/MM/YYYY HH24:MI:SS') as rec_fecha_ingreso,\n   TO_CHAR(rec_fecha,'DD/MM/YYYY')as rec_fecha,\n  rec_tipo, rec_medicamento, rec_medida,\n  rec_cantidad, rec_frecuencia, rec_duracion,\n  rec_indicacion, rec_indicacion_no,\n  rec_estado, \n rec_usuario_anula,\n   TO_CHAR(rec_fecha_anula,'DD/MM/YYYY HH24:MI:SS') as rec_fecha_anula,\n  rec_motivo_anula,\n  rec_paciente, rec_duo, rec_duracion_desc,\n  (CASE \n   WHEN rec_estado='1' THEN '' \n   WHEN rec_estado='0' THEN 'Anulado'\n   END) as rec_estado_desc,\n  (CASE \n   WHEN rec_tipo='1' THEN 'Receta Hospitalizado'\n   WHEN rec_tipo='2' THEN 'Receta Alta'\n   END) as rec_tipo_desc,\n   nombre_usuario,apellidop_usuario,apellidom_usuario\n  FROM  schema_uo.receta_detalle REC  INNER JOIN \n  schema_uo.usuario USU ON (REC.rec_usuario= USU.rut_usuario)\n  WHERE " + resto_sql + "   AND rec_estado IN (0,1) order by rec_id DESC; ");
            this.cnn.conectar();

            try {
                while (this.cnn.getRst().next()) {
                    rec = new cReceta();
                    rec.setId_receta(this.cnn.getRst().getInt("rec_receta"));
                    rec.setId_receta_detalle(this.cnn.getRst().getInt("rec_id"));
                    rec.setRut_usuario(this.cnn.getRst().getString("rec_usuario"));
                    rec.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                    rec.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                    rec.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                    rec.setFecha_ingreso(this.cnn.getRst().getString("rec_fecha_ingreso"));
                    rec.setFecha(this.cnn.getRst().getString("rec_fecha"));
                    rec.setTipo(this.cnn.getRst().getInt("rec_tipo"));
                    rec.setTipo_desc(this.cnn.getRst().getString("rec_tipo_desc"));
                    rec.setMedicamento_desc(this.cnn.getRst().getString("rec_medicamento"));
                    rec.setMedida(this.cnn.getRst().getInt("rec_medida"));
                    rec.setCantidad(this.cnn.getRst().getDouble("rec_cantidad"));
                    rec.setFrecuencia(this.cnn.getRst().getString("rec_frecuencia"));
                    rec.setDuracion(this.cnn.getRst().getInt("rec_duracion"));
                    rec.setDuracion_desc(this.cnn.getRst().getString("rec_duracion_desc"));
                    rec.setIndicacion(this.cnn.getRst().getString("rec_indicacion"));
                    rec.setIndicacion_no(this.cnn.getRst().getString("rec_indicacion_no"));
                    rec.setEstado(this.cnn.getRst().getInt("rec_estado"));
                    rec.setEstado_desc(this.cnn.getRst().getString("rec_estado_desc"));
                    rec.setRut_usuario_anula(this.cnn.getRst().getString("rec_usuario_anula"));
                    rec.setMotivo_anula(this.cnn.getRst().getString("rec_motivo_anula"));
                    rec.setFecha_anula(this.cnn.getRst().getString("rec_fecha_anula"));
                    rec.setId_duo(this.cnn.getRst().getInt("rec_duo"));
                    lista.add(rec);
                }
            } catch (SQLException var5) {
                Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var5);
            }
        }

        return lista;
    }

    public ArrayList lista_medicamento() {
        ArrayList lista = new ArrayList();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL(" SELECT ins_id,ins_familia,ins_tipo,ins_generico,   ins_descripcion, ins_estado, ins_unidad_medida,   ins_fecha_ingreso, ins_usuario, ins_tipo_clinico  FROM  schema_abastecimiento.insumo  WHERE ins_tipo_clinico='1'   and ins_estado='1' order by ins_descripcion; ");
        this.cnn.conectar();
        try {
            while (this.cnn.getRst().next()) {
                cInsumo med = new cInsumo();
                med.setId_insumo(this.cnn.getRst().getInt("ins_id"));
                med.setInsumo_desc(this.cnn.getRst().getString("ins_descripcion"));
                lista.add(med);
            }
        } catch (SQLException var3) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var3);
        }
        this.cnn.cerrarConexion();
        return lista;
    }

    public cReceta obtiene_receta(int id) {
        cReceta rec = new cReceta();
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL(" SELECT  rec_id, rec_tipo,(case \n  WHEN rec_tipo='1' THEN 'Receta Hospitalizado'\n   WHEN rec_tipo='2' THEN 'Receta Alta'\n  end) as rec_tipo_desc, TO_CHAR(rec_fecha, 'DD/MM/YYYY' ) as rec_fecha, \n   rec_usuario,\n  nombre_usuario,apellidop_usuario,apellidom_usuario,\n  rec_fecha_ingreso,rec_estado,rec_ip,\n  rec_dia_hospitalizacion,rec_diagnostico,rec_regimen,\n  rec_reposo,rec_kinesiologia,rec_aislamiento,\n  rec_contencion, rec_dispositivo, rec_hgt  FROM  schema_uo.receta REC\n INNER JOIN schema_uo.usuario USU ON (REC.rec_usuario=USU.rut_usuario)\n WHERE rec_id='" + id + "' limit 1 ");
        this.cnn.conectar();

        try {
            if (this.cnn.getRst().next()) {
                rec.setId_receta(this.cnn.getRst().getInt("rec_id"));
                rec.setTipo(this.cnn.getRst().getInt("rec_tipo"));
                rec.setTipo_desc(this.cnn.getRst().getString("rec_tipo_desc"));
                rec.setFecha(this.cnn.getRst().getString("rec_fecha"));
                rec.setRut_usuario(this.cnn.getRst().getString("rec_usuario"));
                rec.setNombre_usuario(this.cnn.getRst().getString("nombre_usuario"));
                rec.setApellidop_usuario(this.cnn.getRst().getString("apellidop_usuario"));
                rec.setApellidom_usuario(this.cnn.getRst().getString("apellidom_usuario"));
                rec.setFecha_ingreso(this.cnn.getRst().getString("rec_fecha_ingreso"));
                rec.setEstado(this.cnn.getRst().getInt("rec_estado"));
                rec.setIp(this.cnn.getRst().getString("rec_ip"));
                rec.setDia_hospitalizacion(this.cnn.getRst().getInt("rec_dia_hospitalizacion"));
                rec.setDiagnostico(this.cnn.getRst().getString("rec_diagnostico"));
                rec.setRegimen(this.cnn.getRst().getString("rec_regimen"));
                rec.setReposo(this.cnn.getRst().getString("rec_reposo"));
                rec.setKinesiologia(this.cnn.getRst().getString("rec_kinesiologia"));
                rec.setAislamiento(this.cnn.getRst().getString("rec_aislamiento"));
                rec.setContencion(this.cnn.getRst().getString("rec_contencion"));
                rec.setDispositivo(this.cnn.getRst().getString("rec_dispositivo"));
                rec.setHgt(this.cnn.getRst().getString("rec_hgt"));
            }
        } catch (SQLException var4) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

        this.cnn.cerrarConexion();
        return rec;
    }
}
