//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package CapaServlet;

import CapaDato.cDuo;
import CapaNegocio.NegocioQ;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ingreso_dato extends HttpServlet {
    public ingreso_dato() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int opcion = Integer.parseInt(request.getParameter("txt_modo"));
        HttpSession session1 = request.getSession();
        String obtiene_usuario = "" + request.getParameter("txt_rut_usuario");
        int estado = Integer.parseInt(String.valueOf(request.getParameter("estado")));
        int id_duo = Integer.parseInt(String.valueOf(request.getParameter("id_duo")));
        String duo_ip = request.getRemoteAddr();
        NegocioQ neg = new NegocioQ();
        cDuo duo = new cDuo();
        String[] PreDuo;
        int i;
        if (request.getParameterValues("EnfCronH") != null) {
            PreDuo = request.getParameterValues("EnfCronH");

            for(i = 0; i < PreDuo.length; ++i) {
                neg.ingresa_EnfCronica(id_duo, Integer.parseInt(PreDuo[i]));
            }
        }

        try {
            if (request.getParameterValues("PreDuo") != null) {
                PreDuo = request.getParameterValues("PreDuo");

                for(i = 0; i < PreDuo.length; ++i) {
                    neg.ingresa_PrestacionesDuo(id_duo, Integer.parseInt(PreDuo[i]));
                }
            }
        } catch (Exception var40) {
            out.write("Try Prestación--linea 65 ingreso_dato");
        }

       
        switch(opcion) {
        case 1:
            String otro_ex_docto_ing_enfermeria = request.getParameter("otro_ex_docto_ing_enfermeria");
            String dorso_lumbar_ex_fisico = request.getParameter("dorso_lumbar_ex_fisico");
            String piel_ex_fisico = request.getParameter("piel_ex_fisico");
            String morbilidades = request.getParameter("morbilidades");
            String farmacos = request.getParameter("farmacos");
            String observacion = request.getParameter("observacion");
            String conciencia = request.getParameter("conciencia");
            String cabeza = request.getParameter("cabeza");
            String mucoza = request.getParameter("mucoza");
            String torax = request.getParameter("torax");
            String abdomen = request.getParameter("abdomen");
            String eess = request.getParameter("eess");
            String eeii = request.getParameter("eeii");
            String zona = request.getParameter("zona");
            String sng = request.getParameter("sng");
            String sfoley = request.getParameter("sfoley");
            String peso = request.getParameter("peso");
            String talla = request.getParameter("talla");
            String pulso = request.getParameter("pulso");
            String presion = request.getParameter("presion");
            String temp = request.getParameter("temp");
            String sat = request.getParameter("sat");
            String vvp1 = request.getParameter("vvp1");
            String vvp2 = request.getParameter("vvp2");
            String vvc = request.getParameter("vvc");
            if (!obtiene_usuario.equalsIgnoreCase("null")) {
                if (request.getParameterValues("doctosAdjuntos") != null) {
                    String[] doctosAdjuntos = request.getParameterValues("doctosAdjuntos");

                    for(i = 0; i < doctosAdjuntos.length; ++i) {
                        neg.ingresa_DoctoAdjunto(id_duo, Integer.parseInt(doctosAdjuntos[i]));
                    }
                }

                int id_ex_fisico = neg.ingresa_ExamenFisico(conciencia, cabeza, mucoza, torax, abdomen, eess, eeii, zona, sng, sfoley, peso, talla, pulso, presion, temp, sat, vvp1, vvp2, vvc, dorso_lumbar_ex_fisico, piel_ex_fisico);
                neg.ingresa_ingreso_enfermeria(morbilidades, farmacos, observacion, obtiene_usuario, id_ex_fisico, id_duo, otro_ex_docto_ing_enfermeria);
                duo.setIp_ing_enf(duo_ip);
                if (estado == 2) {
                    cDuo duox = neg.obtiene_duo_liviano(id_duo);
                    if (duox.getEstado_duo() == 21) {
                        duo.setEstado_duo(21);
                    } else {
                        duo.setEstado_duo(estado);
                    }
                } else {
                    duo.setEstado_duo(estado);
                }

                duo.setId_duo(id_duo);
                neg.modifica_duo_x_enfermeria(duo);
                response.sendRedirect(neg.getLocal() + "uh_visita.jsp");
            } else {
                out.write("<script> alert('Su sesion ha expirado'); ");
                out.write(" window.location = '" + neg.getLocal() + "cierra_sesion' ");
                out.write("</script>");
            }
            break;
        case 2:
            String anamnesis = request.getParameter("anamnesis").replace("'", "");
            i = Integer.parseInt(request.getParameter("cbo_consultorio_pertenencia"));
            duo.setConsultorio(i);
            String rut_paciente = request.getParameter("txt_rut").toUpperCase();
            neg.modifica_paciente_datos(rut_paciente, i);
            duo.setCategorizacion_id(0);
            duo.setAnamnesis_duo(anamnesis);
            duo.setIp_ing_med(duo_ip);
            duo.setEstado_duo(21);
            duo.setId_duo(id_duo);
            if (!obtiene_usuario.equalsIgnoreCase("null")) {
                duo.setRut_usuario_ing_med(obtiene_usuario);
                neg.modifica_duo_x_medico(duo);
            } else {
                out.write("<script> alert('Su sesion ha expirado'); ");
                out.write(" window.location = '" + neg.getLocal() + "cierra_sesion' ");
                out.write("</script>");
            }

            response.sendRedirect(neg.getLocal() + "uh_visita.jsp");
        case 3:
        }

        out.write("El sistema no pudo redireccionar a la pagina siguiente...<a href='uh_visita.jsp'>IR A VISITA...</a>");
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
