/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocio;

import CapaDato.cPaciente;


import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author EseGamboa
 */
public class Negocio_fonasa extends Negocio {

    public cPaciente getConsultaPrevision(String rut) {
        cPaciente pac = new cPaciente();
        try {
            String rutsindv = rut.replace(".", "");
            rutsindv = rutsindv.replace("-", "");
            String dv = rutsindv.substring(rutsindv.length() - 1);
            rutsindv = rutsindv.substring(0, rutsindv.length() - 1);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Document doc = builder.parse("http://www.fonasa.cl/trade_service/web_services/Certificado.asp?ID=65061030&RUT=" + rutsindv + "&DGV=" + dv + "");
            Document doc = builder.parse("http://200.51.172.210/trade_service/web_services/Certificado.asp?ID=65061030&RUT=" + rutsindv + "&DGV=" + dv + "");

            Element root = doc.getDocumentElement();
            pac.setRut_sdv(rutsindv);
            pac.setDv(dv);

            if (root.getLastChild().getNodeName().equalsIgnoreCase("ERROR")) {
                NodeList otro2 = root.getElementsByTagName("ERROR");
                Node otroNodo2 = otro2.item(0);
                pac.setError(String.valueOf(otroNodo2.getFirstChild().getNodeValue()));
//                logger.info("SETEO LAS PROPIEDADES ERROR " + otroNodo2.getFirstChild().getNodeValue());
                pac.setVerificado_fonasa(0);
            } else {
                pac.setError("");
                NodeList otro = root.getElementsByTagName("COD_DESC");
                Node otroNodo = otro.item(0);
                pac.setCodigo_fonasa_descripcion(String.valueOf(otroNodo.getFirstChild().getNodeValue()));
//                logger.info("SETEO LAS PROPIEDADES  COD_DESC " + pac.getCodigo_fonasa_desc());

                otro = root.getElementsByTagName("COD_CYBL");
                otroNodo = otro.item(0);
                pac.setCodigo_fonasa(otroNodo.getFirstChild().getNodeValue());
//                logger.info("SETEO LAS PROPIEDADES  COD_CYBL " + pac.getCodigo_fonasa());
                //***************

                otro = root.getElementsByTagName("NOMBRES");
                otroNodo = otro.item(0);
                pac.setNombres_paciente(String.valueOf(otroNodo.getFirstChild().getNodeValue().replace("'", "''")));
//                logger.info("SETEO LAS PROPIEDADES  NOMBRES " + pac.getPaciente_nombres());
                //***************

                otro = root.getElementsByTagName("RUTBENEF");
                otroNodo = otro.item(0);
                pac.setRut_paciente(String.valueOf(otroNodo.getFirstChild().getNodeValue().replace("'", "''")));
//                logger.info("SETEO LAS PROPIEDADES  RUTBENEF " + pac.getPaciente_rut_bd());
                //***************

                otro = root.getElementsByTagName("DGVBENEF");
                otroNodo = otro.item(0);
                pac.setDv(otroNodo.getFirstChild().getNodeValue());
//                logger.info("SETEO LAS PROPIEDADES  DGVBENEF " + pac.getDv());
                //***************

                otro = root.getElementsByTagName("APELL1");
                otroNodo = otro.item(0);
                pac.setApellidop_paciente(otroNodo.getFirstChild().getNodeValue().replaceAll("'", "''"));
//                logger.info("SETEO LAS PROPIEDADES  APELL1 " + pac.getPaciente_apellidop());

                //***************;
                otro = root.getElementsByTagName("APELL2");
                otroNodo = otro.item(0);
                pac.setApellidom_paciente(otroNodo.getFirstChild().getNodeValue().replaceAll("'", "''"));
//                logger.info("SETEO LAS PROPIEDADES  APELL2 " + pac.getPaciente_apellidom());
                //***************
                otro = root.getElementsByTagName("FEC_NAC");
                otroNodo = otro.item(0);
                pac.setFecha_nac(otroNodo.getFirstChild().getNodeValue());
                if (pac.getFecha_nac().length() > 0) {
                    String FEC_NAC = otroNodo.getFirstChild().getNodeValue();
                    String FEC_NAC_ano = FEC_NAC.substring(0, 4);
                    String FEC_NAC_mes = FEC_NAC.substring(5, 7);
                    String FEC_NAC_dia = FEC_NAC.substring(8, 10);
                    String fecha = FEC_NAC_dia + "/" + FEC_NAC_mes + "/" + FEC_NAC_ano;
                    pac.setFecha_nac(fecha);
                }
//                logger.info("SETEO LAS PROPIEDADES  FEC_NAC " + pac.getPaciente_fecha_nac());

                /*FEC_NAC = otroNodo.getFirstChild().getNodeValue();
                FEC_NAC_ano=FEC_NAC.substring(0,4);
                FEC_NAC_mes=FEC_NAC.substring(5,7);
                FEC_NAC_dia=FEC_NAC.substring(8,10);
                fecha_dma=FEC_NAC_dia+"-"+FEC_NAC_mes+"-"+FEC_NAC_ano;
                fecha_mda=FEC_NAC_mes+"-"+FEC_NAC_dia+"-"+FEC_NAC_ano;*/
                //***************

                otro = root.getElementsByTagName("SEX");
                otroNodo = otro.item(0);
                pac.setSexo_descri(otroNodo.getFirstChild().getNodeValue());

                String SEX = pac.getSexo_descri();
                if (SEX.equals("M")) {
                    pac.setSexo(0);
                    SEX = "Masculino";
                }
                if (SEX.equals("F")) {
                    pac.setSexo(1);
                    SEX = "Femenino";
                }
//                logger.info("SETEO LAS PROPIEDADES  SEX " + pac.getPaciente_sexo());
                //***************

                if (Integer.parseInt(pac.getCodigo_fonasa()) < 1900) {
                    otro = root.getElementsByTagName("TRAMO");
                    otroNodo = otro.item(0);
                    pac.setTramo_prevision(String.valueOf(otroNodo.getLastChild().getNodeValue()));
//                    logger.info("SETEO LAS PROPIEDADES  TRAMO" + pac.getPaciente_tramo());
                } else {
                    pac.setTramo_prevision("0");
//                    logger.info("SETEO LAS PROPIEDADES  TRAMO " + pac.getPaciente_tramo());
                }

                int pos = pac.getCodigo_fonasa_descripcion().indexOf("PRAIS");

                if (pos > 0) {
                    pac.setPrais(1);
                } else {
                    pac.setPrais(0);
                }

                pac.setVerificado_fonasa(1);
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return pac;
    }

    public void Actualiza_prevision(String rut_paciente) {
        cPaciente paciente = new cPaciente();
        // utilizo el servicio de fonasa.
        paciente = getConsultaPrevision(rut_paciente);
        paciente.setRut_paciente(rut_paciente);
        String codigo_fonasa = paciente.getCodigo_fonasa();
        String tramo = paciente.getTramo_prevision();
        int prais = paciente.getPrais();
        int id_prevision = 0;

        try {

            if (codigo_fonasa.length() > 1 && tramo.trim().length() == 1) {
                this.modifica_prevision_todas(rut_paciente);
                this.ingresa_prevision(paciente);
            }

            //id_prevision = PacienteD.grabaNuevaPrevision(rut_paciente, codigo_fonasa, tramo, prais, verificado_fonasa);
        } catch (Exception ex) {
            //out.print("Error Prevision: " + ex);
        }
    }

    public boolean validaConexionFonasa() {
        // compruebo el estado de utilizacion desde tabla schema-urgencia.conexion_fonasa
        boolean sw = false;
        this.configurarConexion("");
        this.cnn.setEsSelect(true);
        this.cnn.setSentenciaSQL("select tipo_conexion_fonasa from schema_urgencia.conexion_fonasa CF where CF.estado_conexion_fonasa=1");
        this.cnn.conectar();
        try {
            if (cnn.getRst().next()) {
                int zzz = cnn.getRst().getInt("tipo_conexion_fonasa");
                if (zzz == 1) {
                    sw = true; // 1 es automatica
                } else {
                    sw = false; // 0 es manual
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cnn.cerrarConexion();
        return sw;
    }
}
