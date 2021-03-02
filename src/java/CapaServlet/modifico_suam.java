//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package CapaServlet;

import CapaDato.cAlta_Das;
import CapaDato.cDas;
import CapaNegocio.NegocioQ;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class modifico_suam extends HttpServlet {
    public modifico_suam() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        NegocioQ neg = new NegocioQ();
        int modo = 0;

        try {
            modo = Integer.parseInt(request.getParameter("txt_modo"));
        } catch (NumberFormatException var21) {
        }

        HttpSession session1 = request.getSession();
        String obtiene_usuario = session1.getAttribute("usuario_rut") + "";
        String ip = request.getRemoteAddr();
        cDas das;
        switch(modo) {
        case 1:
            cAlta_Das alta = new cAlta_Das();
            int destino = Integer.parseInt(request.getParameter("cbo_destino"));
            String observaciones = "" + request.getParameter("txa_observacion").trim();
            int id_das = Integer.parseInt(request.getParameter("txt_das"));
            String rut_medico = request.getParameter("cbo_medico");
            alta.setId_das(id_das);
            alta.setRut_usuario(obtiene_usuario);
            alta.setDetalle(observaciones);
            alta.setDestino(destino);
            alta.setIp(ip);
            alta.setMedico_rut(rut_medico);
            neg.ingresa_alta_das(alta);
            neg.modifica_estado_das(id_das, 4);
            response.sendRedirect("" + neg.getLocal() + "uh_visita_suam.jsp");
            break;
        case 2:
            int modo_cama = Integer.parseInt(request.getParameter("txt_modo_camilla"));
            int das_actual = Integer.parseInt(request.getParameter("txt_das_actual"));
            int cama_actual = Integer.parseInt(request.getParameter("txt_camilla_actual"));
            int cama_nueva = Integer.parseInt(request.getParameter("cbo_camilla_seleccionada"));
            if (modo_cama == 1) {
                neg.modifica_camilla(das_actual, cama_nueva);
            } else if (modo_cama == 2) {
                das = neg.obtiene_das(cama_nueva);
                neg.modifica_camilla(cama_nueva, cama_actual);
                neg.modifica_camilla(das_actual, das.getCamilla());
            }

            response.sendRedirect("" + neg.getLocal() + "uh_visita_suam.jsp");
            break;
        case 3:
            das = new cDas();
            int destino_indica = Integer.parseInt(request.getParameter("cbo_destino"));
            int id_das_indaca = Integer.parseInt(request.getParameter("txt_das"));
            das.setIndicacion_destino_id(destino_indica);
            das.setIndicacion_ip(ip);
            das.setIndicacion_usuario_rut(obtiene_usuario);
            das.setId_das(id_das_indaca);
            out.write(destino_indica + " destino");
            out.write("UPDATE  schema_suam.das  SET   das_estado = '2',  das_indicacion_destino = '" + das.getIndicacion_destino_id() + "',  das_indicacion_usuario = '" + das.getIndicacion_usuario_rut() + "',  das_indicacion_fecha_ingreso = CURRENT_TIMESTAMP,  das_indicacion_ip = '" + das.getIndicacion_ip() + "'  WHERE  das_id = '" + das.getId_das() + "' ");
            neg.modifica_das_x_indicacion_alta(das);
            response.sendRedirect("" + neg.getLocal() + "uh_visita_suam.jsp");
        case 97:
        case 98:
        case 99:
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
