//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package CapaServlet;

import CapaDato.cContacto;
import CapaDato.cDiagnostico;
import CapaDato.cExamen;
import CapaDato.cObservacion;
import CapaNegocio.NegocioQ;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ingreso_diagnostico_suam extends HttpServlet {
    public ingreso_diagnostico_suam() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session1 = request.getSession();
        String modo = request.getParameter("txt_modo");
        String obtiene_usuario = session1.getAttribute("usuario_rut") + "";
        String ip = request.getRemoteAddr();
        String descripcion_diagnostico = request.getParameter("diagnostico");
        int id_duo = 0;
        int tipo_diagnostico = 0;
        int id_diagnostico = 0;

     
        try {
            tipo_diagnostico = Integer.parseInt(request.getParameter("tipo_diagnostico"));
        } catch (Exception var26) {
            tipo_diagnostico = 1;
        }

        try {
            id_duo = Integer.parseInt(request.getParameter("id_das"));
        } catch (Exception var25) {
        }

        try {
            id_diagnostico = Integer.parseInt(request.getParameter("id_diagnostico_das"));
        } catch (Exception var24) {
        }

        NegocioQ neg = new NegocioQ();
        cDiagnostico diag = new cDiagnostico();
        diag.setDescripcion_diagnostico(descripcion_diagnostico);
        diag.setId_diagnostico(id_diagnostico);
        diag.setId_duo(id_duo);
        diag.setTipo_diagnostico(tipo_diagnostico);
        diag.setRut_usuario(obtiene_usuario);
        diag.setIp(ip);
        if (modo.equals("1")) {
            neg.ingresa_diagnostico_suam(diag);
        } else if (modo.equals("2")) {
            neg.elimina_diagnostico_suam(diag.getId_diagnostico());
        } else {
            int id_examen_das;
            if (modo.equals("11")) {
                id_examen_das = Integer.parseInt(request.getParameter("radio"));
                int laboratorio = Integer.parseInt(request.getParameter("laboratorio"));
                diag.setEspera_ex_laboratorio(laboratorio);
                diag.setEspera_radiografia(id_examen_das);
                neg.ingresa_observacion_suam(diag);
            } else if (modo.equals("12")) {
                neg.elimina_observacion_suam(diag.getId_diagnostico());
            } else if (modo.equals("21")) {
                cContacto con = new cContacto();
                con.setNombre(request.getParameter("nombre"));
                con.setDas_id(id_duo);
                con.setObservacion(request.getParameter("observaciones"));
                con.setFecha(request.getParameter("fecha"));
                String armador_hora = request.getParameter("hora") + ":" + request.getParameter("minuto") + ":00";
                con.setHora(armador_hora);
                con.setIp(ip);
                con.setRut_usuario(obtiene_usuario);
                neg.ingresa_contacto(con);
            } else if (modo.equals("31")) {
                cExamen exa = new cExamen();
                exa.setId_das(id_duo);
                exa.setId_examen(Integer.parseInt(request.getParameter("id_examen")));
                exa.setRut_usuario(obtiene_usuario);
                neg.ingresa_examen_radiografia(exa);
            } else if (modo.equals("32")) {
                id_examen_das = Integer.parseInt(request.getParameter("id_examen"));
                neg.elimina_examen_radiografia(id_examen_das);
            }
        }

        ArrayList lista_diagnostico = neg.lista_diagnostico_suam(diag.getId_duo(), diag.getTipo_diagnostico() + "");
        Iterator it = lista_diagnostico.iterator();
        int contador = 1;
        if (!lista_diagnostico.isEmpty()) {
            out.write(" <table width=\"740\"><tr> ");
            out.write(" <th class=\"DATOS\">N°</th>");
            out.write(" <th class=\"DATOS\">Hora</th>");
            out.write(" <th class=\"DATOS\">Descripción Diagnóstico</th>");
            out.write(" <th class=\"DATOS\">Eli</th> ");
            out.write(" </tr>");

            while(it.hasNext()) {
                cDiagnostico aux = (cDiagnostico)it.next();
                out.write("<tr>");
                out.write("<td class=\"DATOS\">" + contador + " </td> ");
                out.write("<td class=\"DATOS\">" + aux.getFecha_corta() + " </td> ");
                out.write("<td class=\"DATOS\">" + aux.getDescripcion_diagnostico() + "  </td>");
                out.write("<td class=\"DATOS\"> <img src=\"../Iconos/action_stop.gif\" onclick=\"EliminaDiag(" + aux.getId_diagnostico() + ")\" style=\"cursor:pointer\"> </td>");
                out.write(" </tr> ");
                ++contador;
            }

            out.write("  </table>");
        }

        ArrayList lista_observacion = neg.lista_observacion_suam(id_duo);
        Iterator it2 = lista_observacion.iterator();
        if (!lista_observacion.isEmpty()) {
            contador = 0;
            out.write(" <table width=\"740\"><tr> ");
            out.write(" <th class=\"DATOS\">N°</th>");
            out.write(" <th class=\"DATOS\">Hora</th>");
            out.write(" <th class=\"DATOS\">Descripción Observación</th>");
            out.write(" <th class=\"DATOS\">Espera Radiografía</th> ");
            out.write(" <th class=\"DATOS\">Espera Ex. Laboratorio</th> ");
            out.write(" </tr>");

            for(; it2.hasNext(); out.write(" </tr> ")) {
                ++contador;
                cObservacion aux = (cObservacion)it2.next();
                out.write("<tr>");
                out.write("<td class=\"DATOS\">" + contador + " </td> ");
                out.write("<td class=\"DATOS\">" + aux.getFecha_corta() + " </td> ");
                out.write("<td class=\"DATOS\">" + aux.getObservacion_detalle() + "  </td>");
                if (aux.getEspera_radiografia() == 1) {
                    out.write("<td class=\"DATOS\">SI </td>");
                } else {
                    out.write("<td class=\"DATOS\">NO </td>");
                }

                if (aux.getEspera_ex_laboratorio() == 1) {
                    out.write("<td class=\"DATOS\">SI </td>");
                } else {
                    out.write("<td class=\"DATOS\">NO </td>");
                }
            }

            out.write("  </table>");
        }

        ArrayList lista_examen = neg.lista_examen_x_das(id_duo);
        Iterator it4 = lista_examen.iterator();
        if (!lista_examen.isEmpty()) {
            contador = 0;
            out.write(" <table width=\"740\"><tr> ");
            out.write(" <th class=\"DATOS\">N°</th>");
            out.write(" <th class=\"DATOS\">Hora</th>");
            out.write(" <th class=\"DATOS\">Examen</th>");
            out.write(" <th class=\"DATOS\">Eli</th>");
            out.write(" </tr>");

            while(it4.hasNext()) {
                ++contador;
                cExamen aux = (cExamen)it4.next();
                out.write("<tr>");
                out.write("<td class=\"DATOS\">" + contador + " </td> ");
                out.write("<td class=\"DATOS\">" + aux.getFecha_corta() + " </td> ");
                out.write("<td class=\"DATOS\">" + aux.getDescripcion() + "  </td>");
                out.write("<td class=\"DATOS\"> <img src=\"../Iconos/action_stop.gif\" onclick=\"eliminaExamen(" + aux.getId_das_examen() + ",'" + aux.getDescripcion() + "')\" style=\"cursor:pointer\"> </td>");
                out.write(" </tr> ");
            }

            out.write("  </table>");
        }

        ArrayList lista_contacto = neg.lista_contacto(id_duo);
        Iterator it3 = lista_contacto.iterator();
        if (!lista_contacto.isEmpty()) {
            contador = 0;
            out.write(" <table width=\"740\"><tr> ");
            out.write(" <th class=\"DATOS\">N°</th>");
            out.write(" <th class=\"DATOS\">Hora</th>");
            out.write(" <th class=\"DATOS\">Familiar</th>");
            out.write(" <th class=\"DATOS\">Observación</th> ");
            out.write(" </tr>");

            while(it3.hasNext()) {
                ++contador;
                cContacto aux = (cContacto)it3.next();
                out.write("<tr>");
                out.write("<td class=\"DATOS\">" + contador + " </td> ");
                out.write("<td class=\"DATOS\">" + aux.getFecha_corta() + " </td> ");
                out.write("<td class=\"DATOS\">" + aux.getNombre() + "  </td>");
                out.write("<td class=\"DATOS\">" + aux.getObservacion() + "  </td>");
                out.write(" </tr> ");
            }

            out.write("  </table>");
        }

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
