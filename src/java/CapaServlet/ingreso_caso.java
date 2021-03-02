//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package CapaServlet;

import CapaDato.cEvaNeurologia;
import CapaDato.cEvaRespiratoria;
import CapaDato.cEvaTraumatologia;
import CapaDato.cPaciente;
import CapaDato.cRegistroSocial;
import CapaNegocio.NegocioQ;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ingreso_caso extends HttpServlet {
    public ingreso_caso() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.addHeader("Cache-Control", "no-store");
        response.addHeader("Cache-Control", "private");
        response.setDateHeader("Expires", -1L);
        PrintWriter out = response.getWriter();
        int opcion = Integer.parseInt(request.getParameter("txt_modo"));
        HttpSession session1 = request.getSession();
        String obtiene_usuario = "" + request.getParameter("txt_usuario");
        String ip = request.getRemoteAddr();
        NegocioQ neg = new NegocioQ();
        String mensaje = "";
        cRegistroSocial reg;
        cPaciente pac;
        int id_consultorio;
        String rut_paciente;
        switch(opcion) {
        case 1:
            cEvaRespiratoria res = new cEvaRespiratoria();
            res.setId_duo(Integer.parseInt(request.getParameter("txt_duo")));
            res.setRut_usuario(obtiene_usuario);
            res.setOxigeno(Integer.parseInt(request.getParameter("cbo_oxigeno")));
            res.setOxigeno_det(request.getParameter("txt_oxigeno"));
            request.getParameter("txt_oxigeno");
            res.setVia_venosa(Integer.parseInt(request.getParameter("cbo_via_venosa")));
            res.setVia_urinaria(Integer.parseInt(request.getParameter("cbo_via_urinaria")));
            res.setVia_urinaria_det(request.getParameter("txt_via_urinaria_especifique"));
            res.setVigil(Integer.parseInt(request.getParameter("cbo_vigil")));
            res.setAlteracion_lenguaje(Integer.parseInt(request.getParameter("cbo_alteracion_lenguaje")));
            if (request.getParameter("chk_estimulo_verbal") != null) {
                res.setEstimulo_verbal(1);
            } else {
                res.setEstimulo_verbal(2);
            }

            if (request.getParameter("chk_estimulo_visual") != null) {
                res.setEstimulo_visual(1);
            } else {
                res.setEstimulo_visual(2);
            }

            res.setEstado_conciencia(Integer.parseInt(request.getParameter("cbo_estado_no_vigil")));
            if (request.getParameter("chk_temporal") != null) {
                res.setUbicacion_temporal(1);
            } else {
                res.setUbicacion_temporal(2);
            }

            if (request.getParameter("chk_espacial") != null) {
                res.setUbicacion_espacial(1);
            } else {
                res.setUbicacion_espacial(2);
            }

            res.setPostura(Integer.parseInt(request.getParameter("cbo_postura")));
            res.setEESS(request.getParameter("txa_eess"));
            res.setEEII(request.getParameter("txa_eeii"));
            res.setDeformidad(request.getParameter("txt_deformidad"));
            res.setEstado_nutricional(Integer.parseInt(request.getParameter("cbo_estado_nutricional")));
            res.setColoracion_piel(Integer.parseInt(request.getParameter("cbo_coloracion")));
            res.setCianosis(Integer.parseInt(request.getParameter("cbo_cianosis")));
            if (request.getParameter("chk_normal") != null) {
                res.setEp_normal(1);
            } else {
                res.setEp_normal(2);
            }

            if (request.getParameter("chk_escaras") != null) {
                res.setEp_escara(1);
            } else {
                res.setEp_escara(2);
            }

            if (request.getParameter("chk_cicatrices") != null) {
                res.setEp_cicatriz(1);
            } else {
                res.setEp_cicatriz(2);
            }

            if (request.getParameter("chk_hematomas") != null) {
                res.setEp_hematoma(1);
            } else {
                res.setEp_hematoma(2);
            }

            if (request.getParameter("chk_petequias") != null) {
                res.setEp_petequia(1);
            } else {
                res.setEp_petequia(2);
            }

            if (request.getParameter("chk_edemas") != null) {
                res.setEp_edema(1);
            } else {
                res.setEp_edema(2);
            }

            res.setEp_normal_det(request.getParameter("txt_normal"));
            res.setEp_escara_det(request.getParameter("txt_escaras"));
            res.setEp_cicatriz_det(request.getParameter("txt_cicatrices"));
            res.setEp_hematoma_det(request.getParameter("txt_hematomas"));
            res.setEp_petequia_det(request.getParameter("txt_petequias"));
            res.setEp_edema_det(request.getParameter("txt_edemas"));
            res.setPatron_respiratorio(Integer.parseInt(request.getParameter("cbo_patron_respiratorio")));
            res.setRespirador(Integer.parseInt(request.getParameter("cbo_respirador")));
            res.setDificultad_respiratoria(Integer.parseInt(request.getParameter("cbo_dificultad_respiratoria")));
            res.setMusculatura_accesoria(Integer.parseInt(request.getParameter("cbo_musculatura_accesoria")));
            res.setMusculatura_accesoria_det(request.getParameter("txt_musculatura_accesoria_det"));
            res.setAleteo_nasal(Integer.parseInt(request.getParameter("cbo_aleteo_nasal")));
            res.setAleteo_costal(Integer.parseInt(request.getParameter("cbo_aleteo_costal")));
            res.setTemperatura(Integer.parseInt(request.getParameter("cbo_temperatura")));
            res.setElasticidad(Integer.parseInt(request.getParameter("cbo_elasticidad")));
            res.setElasticidad_det(request.getParameter("txt_elasticidad"));
            res.setRestriccion_miofascial(request.getParameter("txt_restriccion_miofascial"));
            res.setLlene_capilar(Integer.parseInt(request.getParameter("cbo_llene_capilar")));
            res.setEdema(Integer.parseInt(request.getParameter("cbo_edema")));
            res.setDolor(Integer.parseInt(request.getParameter("cbo_dolor")));
            res.setEVA(Integer.parseInt(request.getParameter("cbo_eva")));
            res.setDesalineacion(request.getParameter("txt_desalineacion_articular"));
            res.setFrecuencia_cardiaca(request.getParameter("txt_frecuencia_cardiaca"));
            res.setFrecuencia_respiratoria(request.getParameter("txt_frecuencia_respiratoria"));
            res.setSaturacion(request.getParameter("txt_saturacion"));
            res.setAus_pi_ls(Integer.parseInt(request.getParameter("cbo_aus_pi_ls")));
            res.setAus_pi_li(Integer.parseInt(request.getParameter("cbo_aus_pi_li")));
            res.setAus_pd_ls(Integer.parseInt(request.getParameter("cbo_aus_pd_ls")));
            res.setAus_pd_lm(Integer.parseInt(request.getParameter("cbo_aus_pd_lm")));
            res.setAus_pd_li(Integer.parseInt(request.getParameter("cbo_aus_pd_li")));
            if (request.getParameter("chk_sibilancia") != null) {
                res.setSibilancia(1);
            } else {
                res.setSibilancia(2);
            }

            if (request.getParameter("chk_roncus") != null) {
                res.setRoncus(1);
            } else {
                res.setRoncus(2);
            }

            if (request.getParameter("chk_estertor_traqueal") != null) {
                res.setEstertor_traqueal(1);
            } else {
                res.setEstertor_traqueal(2);
            }

            if (request.getParameter("chk_crepitacion_finas") != null) {
                res.setCrepitacion_fina(1);
            } else {
                res.setCrepitacion_fina(2);
            }

            if (request.getParameter("chk_crepitacion_gruesa") != null) {
                res.setCrepitacion_gruesa(1);
            } else {
                res.setCrepitacion_gruesa(2);
            }

            res.setRa_pi_ls(request.getParameter("txt_izquierdo_lobulo_superior"));
            res.setRa_pi_li(request.getParameter("txt_izquierdo_lobulo_inferior"));
            res.setRa_pd_ls(request.getParameter("txt_derecho_lobulo_superior"));
            res.setRa_pd_lm(request.getParameter("txt_derecho_lobulo_medio"));
            res.setRa_pd_li(request.getParameter("txt_derecho_lobulo_inferior"));
            res.setTos_presencia(Integer.parseInt(request.getParameter("cbo_tos_presencia")));
            res.setTos_produccion(Integer.parseInt(request.getParameter("cbo_tos_produccion")));
            res.setSecrecion_cantidad(Integer.parseInt(request.getParameter("cbo_secrecion_cantidad")));
            res.setSecrecion_coloracion(Integer.parseInt(request.getParameter("cbo_secrecion_color")));
            res.setSecrecion_tipo(Integer.parseInt(request.getParameter("cbo_secrecion_tipo")));
            if (neg.ingresa_evaluacion_respiratoria(res)) {
                out.write("se ingreso");
                response.sendRedirect(neg.getLocal() + "uh_visita.jsp");
            } else {
                out.write("No se ingreso");
            }
            break;
        case 2:
            cEvaNeurologia neu = new cEvaNeurologia();
            neu.setLesion_evaluada(Integer.parseInt(request.getParameter("cbo2_lesion_evaluada")));
            neu.setId_duo(Integer.parseInt(request.getParameter("txt_duo")));
            neu.setRut_usuario(obtiene_usuario);
            if (neu.getLesion_evaluada() == 1) {
                neu.setLesion(Integer.parseInt(request.getParameter("cbo2_lesion_medular")));
                neu.setAsia(request.getParameter("cbo2_asia"));
                neu.setLesion_tipo(Integer.parseInt(request.getParameter("cbo2_tipo_lesion")));
                neu.setAshworth(request.getParameter("cbo2_ashworth_medular"));
                neu.setReflejo_osteorendineo_desc(request.getParameter("txt2_reflejo_osteorendineo"));
                neu.setEvaluacion_sensitiva(request.getParameter("txt2_evaluacion_sensitiva"));
                neu.setSensibilidad(request.getParameter("txt2_sensibilidad"));
                neu.setMotor_index(request.getParameter("txt2_motor_index"));
                neu.setContraccion(request.getParameter("txt2_contraccion"));
                neu.setSilla_ruedas(request.getParameter("txt2_silla_rueda"));
                neu.setMarcha(request.getParameter("txt2_marcha_medular"));
                neu.setNivel_motor(request.getParameter("txt2_nivel_motor"));
                neu.setNivel_sentivo(request.getParameter("txt2_nivel_sentivo"));
                neu.setNivel_neurologico(request.getParameter("txt2_nivel_neurologico"));
                neu.setOp1(Integer.parseInt(request.getParameter("rbt2_giro")));
                neu.setOp1_desc(request.getParameter("txt2_giro"));
                neu.setOp2(Integer.parseInt(request.getParameter("rbt2_supino_decubito_lateral")));
                neu.setOp2_desc(request.getParameter("txt2_supino_decubito_lateral"));
                neu.setOp3(Integer.parseInt(request.getParameter("rbt2_supino_prono")));
                neu.setOp3_desc(request.getParameter("txt2_supino_prono"));
                neu.setOp4(Integer.parseInt(request.getParameter("rbt2_decubito_lateral_sedente")));
                neu.setOp4_desc(request.getParameter("txt2_decubito_lateral_sedente"));
                neu.setOp5(Integer.parseInt(request.getParameter("rbt2_supino_sedente")));
                neu.setOp5_desc(request.getParameter("txt2_supino_sedente"));
                neu.setOp6(Integer.parseInt(request.getParameter("rbt2_sedente_bipedo")));
                neu.setOp6_desc(request.getParameter("txt2_sedente_bipedo"));
            } else {
                if (neu.getLesion_evaluada() == 2) {
                    neu.setLesion(Integer.parseInt(request.getParameter("cbo2_lesion_otro")));
                } else {
                    neu.setLesion(0);
                }

                neu.setMotoneurona(request.getParameter("txt2_motoneurona"));
                neu.setExtrapiramiral(request.getParameter("txt2_extrapiramidal"));
                neu.setPostura(request.getParameter("txt2_postura"));
                neu.setAshworth(request.getParameter("cbo2_ashworth_otro"));
                neu.setFuerza(request.getParameter("txt2_fuerza"));
                neu.setTono_muscular(request.getParameter("txt2_tono_muscular"));
                neu.setTrofismo(Integer.parseInt(request.getParameter("cbo2_trofismo")));
                neu.setTrofismo_adicional(request.getParameter("txt2_trofismo_adicional"));
                neu.setEess(request.getParameter("txt2_eess"));
                neu.setEeii(request.getParameter("txt2_eeii"));
                neu.setReflejo_osteorendineo(Integer.parseInt(request.getParameter("cbo2_reflejo_osteotendineo")));
                neu.setPropiocepcion(Integer.parseInt(request.getParameter("cbo2_propiocepcion")));
                neu.setPropiocepcion_adicional(request.getParameter("txt2_propiocepcion_adicional"));
                neu.setTransicion(request.getParameter("txt2_transicion"));
                neu.setReaccion_equilibrio(Integer.parseInt(request.getParameter("cbo2_reaccion_equilibrio")));
                neu.setReaccion_enderezamiento(Integer.parseInt(request.getParameter("cbo2_reaccion_enderezamiento")));
                neu.setReaccion_apoyo(Integer.parseInt(request.getParameter("cbo2_reaccion_apoyo")));
                neu.setMarcha(request.getParameter("txt2_marcha_otro"));
                neu.setTest_especial(request.getParameter("txt2_test_especial"));
                neu.setOp1_desc(request.getParameter("txt2_hta"));
                neu.setOp2_desc(request.getParameter("txt2_dm"));
                neu.setOp3_desc(request.getParameter("txt2_dislipidemia"));
                neu.setOp4_desc(request.getParameter("txt2_tabaquismo"));
                if (request.getParameter("chk2_hta") != null) {
                    neu.setOp1(1);
                } else {
                    neu.setOp1(0);
                }

                if (request.getParameter("chk2_dm") != null) {
                    neu.setOp2(1);
                } else {
                    neu.setOp2(0);
                }

                if (request.getParameter("chk2_dislipidemia") != null) {
                    neu.setOp3(1);
                } else {
                    neu.setOp3(0);
                }

                if (request.getParameter("chk2_tabaquismo") != null) {
                    neu.setOp4(1);
                } else {
                    neu.setOp4(0);
                }
            }

            if (neg.ingresa_evaluacion_neurologica(neu)) {
                out.write("se ingreso");
                response.sendRedirect(neg.getLocal() + "uh_visita.jsp");
            } else {
                out.write("No se ingreso");
            }
            break;
        case 3:
            cEvaTraumatologia tra = new cEvaTraumatologia();
            tra.setObservacion_inicial(request.getParameter("txa3_observacion_inicial"));
            tra.setHistorial_usuario(request.getParameter("txa3_historial"));
            tra.setDolor(request.getParameter("txa3_dolor"));
            tra.setPrueba_especial(request.getParameter("txa3_prueba_especial"));
            tra.setPalpacion(request.getParameter("txa3_palpacion"));
            tra.setMarcha(request.getParameter("txt3_marcha"));
            tra.setPlano_frontal(request.getParameter("txa3_plano_frontal"));
            tra.setPlano_sagital(request.getParameter("txa3_plano_sagital"));
            tra.setPlano_posterior(request.getParameter("txa3_plano_posterior"));
            tra.setMovimiento_pasivo(request.getParameter("txa3_movimiento_pasivo"));
            tra.setMovimiento_activo(request.getParameter("txa3_movimiento_activo"));
            tra.setObservacion(request.getParameter("txa3_observacion"));
            tra.setDermatoma(request.getParameter("txa3_dermatoma"));
            tra.setMiotoma(request.getParameter("txa3_miotoma"));
            tra.setReflejo_osteotendineo(request.getParameter("txa3_reflejo_osteotendineo"));
            tra.setTest_neurodinamico(request.getParameter("txa3_test_neurodinamico"));
            tra.setDiagnostico_imagen(request.getParameter("txa3_diagnostico_imagen"));
            tra.setDiagnostico_kinesico(request.getParameter("txa3_diagnostico_kinesico"));
            tra.setId_duo(Integer.parseInt(request.getParameter("txt_duo")));
            tra.setRut_usuario(obtiene_usuario);
            if (neg.ingresa_evaluacion_traumatologica(tra)) {
                mensaje = "Se registraron los datos";
                response.sendRedirect(neg.getLocal() + "uh_visita.jsp");
            } else {
                mensaje = "Error. No se pudo ingresar elr egistro de traumatologia";
            }
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
        case 10:
        case 11:
        case 12:
        case 13:
        case 14:
        case 15:
        case 16:
        case 17:
        case 18:
        case 19:
        case 20:
        case 23:
        case 24:
        case 25:
        case 26:
        case 27:
        default:
            break;
        case 21:
            reg = new cRegistroSocial();
            reg.setId_duo(Integer.parseInt(request.getParameter("txt_duo")));
            reg.setInstitucionalizado(Integer.parseInt(request.getParameter("cbo_institucionalizado")));
            if (reg.getInstitucionalizado() == 1) {
                reg.setInstitucion_nombre(request.getParameter("txt_institucion"));
            }

            reg.setEstado_civil(Integer.parseInt(request.getParameter("cbo_estado_civil")));
            reg.setSituacion_laboral(Integer.parseInt(request.getParameter("cbo_situacion_laboral")));
            reg.setPlan(request.getParameter("txa_plan"));
            reg.setSituacion(request.getParameter("txa_situacion"));
            reg.setVive(Integer.parseInt(request.getParameter("cbo_vive")));
            reg.setHijos(Integer.parseInt(request.getParameter("cbo_hijo")));
            if (reg.getHijos() == 1) {
                reg.setHijos_cantidad(Integer.parseInt(request.getParameter("txt_cantidad")));
            }

            reg.setRut_usuario(obtiene_usuario);
            reg.setHospital_origen_desc(request.getParameter("txt_hospital_origen"));
            reg.setDestino(Integer.parseInt(request.getParameter("cbo_destino")));
            reg.setNombre_asistente(request.getParameter("txt_asistente_social"));
            if (request.getParameter("txt_fecha_egreso") != null && request.getParameter("txt_fecha_egreso").trim().length() == 10) {
                reg.setFecha_egreso(neg.MMDDYYY(request.getParameter("txt_fecha_egreso"), 1));
            }

            if (reg.getFecha_egreso().equals("")) {
                reg.setFecha_egreso("01-01-1900");
            }

            pac = new cPaciente();
            pac.setRut_paciente(request.getParameter("txt_rut"));
            pac.setTelefono1(request.getParameter("txt_telefono1"));
            pac.setTelefono2(request.getParameter("txt_telefono2"));
            pac.setMail(request.getParameter("txt_mail"));
            neg.modifica_paciente_fono_correo(pac);
            pac.setCodigo_fonasa(request.getParameter("cbo_prevision"));
            if (!pac.getCodigo_fonasa().equals("-1")) {
                String a_tramo = "";
                if (pac.getCodigo_fonasa().equals("00110A0")) {
                    a_tramo = "A";
                } else if (pac.getCodigo_fonasa().equals("00110B0")) {
                    a_tramo = "B";
                } else if (pac.getCodigo_fonasa().equals("00110C0")) {
                    a_tramo = "C";
                } else if (pac.getCodigo_fonasa().equals("00110D0")) {
                    a_tramo = "D";
                } else if (pac.getCodigo_fonasa().equals("0190100")) {
                    a_tramo = "0";
                } else if (pac.getCodigo_fonasa().equals("00110A1")) {
                    a_tramo = "PRAIS";
                }

                pac.setCodigo_fonasa(pac.getCodigo_fonasa().substring(0, pac.getCodigo_fonasa().length() - 2));
                cPaciente pre = neg.obtiene_paciente_prevision(pac.getRut_paciente());
                if (a_tramo.equals("PRAIS")) {
                    if (pre.getPrais() == 0) {
                        pac.setTramo_prevision("A");
                        pac.setPrais(1);
                        pac.setVerificado_fonasa(0);
                        neg.modifica_prevision_todas(pac.getRut_paciente());
                        neg.ingresa_prevision(pac);
                    }
                } else if (!a_tramo.equals(pre.getTramo_prevision())) {
                    pac.setTramo_prevision(a_tramo);
                    pac.setPrais(0);
                    pac.setVerificado_fonasa(0);
                    neg.modifica_prevision_todas(pac.getRut_paciente());
                    neg.ingresa_prevision(pac);
                }
            }

            reg.setDiagnostico(request.getParameter("txt_diagnostico"));
            neg.ingresa_registro_social(reg);
            id_consultorio = Integer.parseInt(request.getParameter("cbo_consultorio"));
            rut_paciente = request.getParameter("txt_paciente_rut");
            neg.modifica_paciente_datos(rut_paciente, id_consultorio);
            response.sendRedirect(neg.getLocal() + "asistencia_social/social_ingreso.jsp?txt_duo=" + reg.getId_duo());
            break;
        case 22:
            reg = new cRegistroSocial();
            reg.setId_registro(Integer.parseInt(request.getParameter("txt_id_registro")));
            reg.setId_duo(Integer.parseInt(request.getParameter("txt_duo")));
            reg.setInstitucionalizado(Integer.parseInt(request.getParameter("cbo_institucionalizado")));
            if (reg.getInstitucionalizado() == 1) {
                reg.setInstitucion_nombre(request.getParameter("txt_institucion"));
            }

            reg.setEstado_civil(Integer.parseInt(request.getParameter("cbo_estado_civil")));
            reg.setSituacion_laboral(Integer.parseInt(request.getParameter("cbo_situacion_laboral")));
            reg.setPlan(request.getParameter("txa_plan"));
            reg.setSituacion(request.getParameter("txa_situacion"));
            reg.setVive(Integer.parseInt(request.getParameter("cbo_vive")));
            reg.setHijos(Integer.parseInt(request.getParameter("cbo_hijo")));
            if (reg.getHijos() == 1) {
                reg.setHijos_cantidad(Integer.parseInt(request.getParameter("txt_cantidad")));
            }

            reg.setRut_usuario(obtiene_usuario);
            reg.setHospital_origen_desc(request.getParameter("txt_hospital_origen"));
            reg.setDestino(Integer.parseInt(request.getParameter("cbo_destino")));
            reg.setNombre_asistente(request.getParameter("txt_asistente_social"));
            if (request.getParameter("txt_fecha_egreso") != null && request.getParameter("txt_fecha_egreso").trim().length() == 10) {
                reg.setFecha_egreso(neg.MMDDYYY(request.getParameter("txt_fecha_egreso"), 1));
            }

            if (reg.getFecha_egreso().equals("")) {
                reg.setFecha_egreso("01-01-1900");
            }

            pac = new cPaciente();
            pac.setRut_paciente(request.getParameter("txt_rut"));
            pac.setTelefono1(request.getParameter("txt_telefono1"));
            pac.setTelefono2(request.getParameter("txt_telefono2"));
            pac.setMail(request.getParameter("txt_mail"));
            neg.modifica_paciente_fono_correo(pac);
            pac.setCodigo_fonasa(request.getParameter("cbo_prevision"));
            if (!pac.getCodigo_fonasa().equals("-1")) {
                String a_tramo = "";
                if (pac.getCodigo_fonasa().equals("00110A0")) {
                    a_tramo = "A";
                } else if (pac.getCodigo_fonasa().equals("00110B0")) {
                    a_tramo = "B";
                } else if (pac.getCodigo_fonasa().equals("00110C0")) {
                    a_tramo = "C";
                } else if (pac.getCodigo_fonasa().equals("00110D0")) {
                    a_tramo = "D";
                } else if (pac.getCodigo_fonasa().equals("0190100")) {
                    a_tramo = "0";
                } else if (pac.getCodigo_fonasa().equals("00110A1")) {
                    a_tramo = "PRAIS";
                }

                pac.setCodigo_fonasa(pac.getCodigo_fonasa().substring(0, pac.getCodigo_fonasa().length() - 2));
                cPaciente pre = neg.obtiene_paciente_prevision(pac.getRut_paciente());
                if (a_tramo.equals("PRAIS")) {
                    if (pre.getPrais() == 0) {
                        pac.setTramo_prevision("A");
                        pac.setPrais(1);
                        pac.setVerificado_fonasa(0);
                        neg.modifica_prevision_todas(pac.getRut_paciente());
                        neg.ingresa_prevision(pac);
                    }
                } else if (!a_tramo.equals(pre.getTramo_prevision())) {
                    pac.setTramo_prevision(a_tramo);
                    pac.setPrais(0);
                    pac.setVerificado_fonasa(0);
                    neg.modifica_prevision_todas(pac.getRut_paciente());
                    neg.ingresa_prevision(pac);
                }
            }

            reg.setDiagnostico(request.getParameter("txt_diagnostico"));
            neg.modifica_registro_social(reg);
            id_consultorio = Integer.parseInt(request.getParameter("cbo_consultorio"));
            rut_paciente = request.getParameter("txt_paciente_rut");
            neg.modifica_paciente_datos(rut_paciente, id_consultorio);
            response.sendRedirect(neg.getLocal() + "asistencia_social/social_ingreso.jsp?txt_duo=" + reg.getId_duo());
        }

        out.append(mensaje);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }
}
