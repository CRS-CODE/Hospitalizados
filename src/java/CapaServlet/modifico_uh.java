//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package CapaServlet;

import CapaDato.cDuo;
import CapaDato.cHistorial_Consultorio;
import CapaNegocio.NegocioQ;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class modifico_uh extends HttpServlet {
    public modifico_uh() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        NegocioQ neg = new NegocioQ();
        int modo = 0;

        try {
            modo = Integer.parseInt(request.getParameter("modo"));
        } catch (NumberFormatException var23) {
        }

        HttpSession session1 = request.getSession();
        String obtiene_usuario = session1.getAttribute("usuario_rut") + "";
        String ip = request.getRemoteAddr();
        switch(modo) {
        case 1:
            int modo_cama = Integer.parseInt(request.getParameter("txt_modo_cama"));
            int duo_actual = Integer.parseInt(request.getParameter("txt_duo_actual"));
            int cama_actual = Integer.parseInt(request.getParameter("txt_cama_actual"));
            int cama_nueva = Integer.parseInt(request.getParameter("cbo_cama_seleccionada"));
            if (modo_cama == 1) {
                neg.modifica_cama_duo(duo_actual, cama_nueva);
            } else if (modo_cama == 2) {
                cDuo duo = neg.obtiene_dato_segun_duo(cama_nueva);
                neg.modifica_cama_duo(duo.getId_duo(), cama_actual);
                neg.modifica_cama_duo(duo_actual, duo.getCama());
            }

            response.sendRedirect("" + neg.getLocal() + "uh_visita.jsp");
        case 2:
        default:
            break;
        case 97:
            String rut_paciente = request.getParameter("txt_rut");
            int consultorio_nuevo = Integer.parseInt(request.getParameter("cbo_consultorio"));
            int consultorio_anterior = Integer.parseInt(request.getParameter("txt_consultorio_anterior"));
            cHistorial_Consultorio his = new cHistorial_Consultorio();
            his.setHis_ip(ip);
            his.setHis_paciente_rut(rut_paciente);
            his.setHis_usuario(obtiene_usuario);
            his.setHis_consultorio_anterior(consultorio_anterior);
            his.setHis_consultorio_nuevo(consultorio_nuevo);
            neg.ingresa_historial_consultorio_pertenencia(his);
            neg.modifica_paciente_datos(rut_paciente, consultorio_nuevo);
            out.write("Se Modifico Consultorio Pertenencia");
            response.sendRedirect(neg.getLocal() + "uh_paciente.jsp?mod=1");
            break;
        case 98:
            String usuario_rut = request.getParameter("txt_usuario");
            String usuario_clave = request.getParameter("txt_pass_nueva");
            String misma_ventana = request.getParameter("misma_ventana");

            try {
                neg.modifica_clave(usuario_rut, usuario_clave);
                if (misma_ventana != null) {
                    out.write("Se Modifico su Contraseña. Vuelva a entrar para comprobar el cambio.");
                } else {
                    response.sendRedirect(neg.getLocal() + "index.jsp?cambio_pass=1");
                }
            } catch (Exception var22) {
                response.sendRedirect(neg.getLocal() + "cierra_sesion.jsp");
            }
            break;
        case 99:
            int duo = Integer.parseInt(request.getParameter("txt_manda_duo"));
            String motivo = request.getParameter("txa_motivo");
            neg.modifica_estado_duo(duo, 99);
            neg.ingresa_anula_duo(motivo, obtiene_usuario, duo);
            out.write("modifico");
            response.sendRedirect(neg.getLocal() + "admision_uhce.jsp");
        }

        out.write("" + modo);
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
