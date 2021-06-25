/*
 * SelectCategorizacion.java
 *
 * Created on 24 de julio de 2008, 04:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package CapaNegocio;

import CapaDato.Epicrisis;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Logger;

/**
 *
 * @author Victor Carcamo
 */
public class Select extends Negocio {

    //private Properties pCama = null;
    /**
     * Creates a new instance of SelectCategorizacion
     */
    public Epicrisis getEpicrisis(int id_epicrisis) {
        Epicrisis epi = null;

        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select * from schema_uhd.epicrisis E, schema_uhd.duo D,agenda.paciente P,\n"
                + "              schema_uhd.usuario U where E.id_duo=D.id_duo and \n"
                + "                D.rut_paciente=P.rut and E.rut_usuario=U.rut_usuario  and E.id_epicrisis=" + id_epicrisis + "");
        this.cnn.conectar();
        try {

            while (cnn.getRst().next()) {

                epi = new Epicrisis();
                epi.setRut_usuario(cnn.getRst().getString("rut_usuario"));
                epi.setApellidop_usuario(cnn.getRst().getString("apellidop_usuario"));
                epi.setApellidom_usuario(cnn.getRst().getString("apellidom_usuario"));
                epi.setDiagnostico_epicrisis(cnn.getRst().getString("diagnostico_epicrisis"));
                epi.setExamen_epicrisis(cnn.getRst().getString("examen_epicrisis"));
                epi.setFecha_duo(cnn.getRst().getDate("fecha_duo"));
                epi.setFecha_epicrisis(cnn.getRst().getDate("fecha_epicrisis"));
                epi.setHora_duo(cnn.getRst().getString("hora_duo"));
                epi.setHora_epicrisis(cnn.getRst().getString("hora_epicrisis"));
                epi.setId_duo(cnn.getRst().getInt("id_duo"));
                epi.setIndicacion_epicrisis(cnn.getRst().getString("indicacion_epicrisis"));
                epi.setNombre_usuario(cnn.getRst().getString("nombre_usuario"));
                epi.setPaciente_apellidom(cnn.getRst().getString("paciente_apellidom"));
                epi.setPaciente_apellidop(cnn.getRst().getString("paciente_apellidop"));
                epi.setPaciente_fecha_nac(cnn.getRst().getString("paciente_fecha_nac"));
                epi.setPaciente_nombres(cnn.getRst().getString("paciente_nombres"));
                epi.setPaciente_rut(cnn.getRst().getString("paciente_rut"));
                epi.setPaciente_sexo(cnn.getRst().getInt("paciente_sexo"));
                epi.setResumen_epicrisis(cnn.getRst().getString("resumen_epicrisis"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.cnn.cerrarConexion();
        }

        return (epi);

    }

    public int getIdEpicrisis(int id_duo) {
        int epi = 0;
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select E.id_epicrisis from schema_uhd.duo D,schema_uhd.epicrisis E where "
                + "E.id_duo=D.id_duo and D.id_duo=" + id_duo + "");
        this.cnn.conectar();
        try {

            while (cnn.getRst().next()) {
                epi = cnn.getRst().getInt("id_epicrisis");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.cnn.cerrarConexion();
        }

        return (epi);

    }

}
