//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package CapaServlet;

import CapaDato.cDas;
import CapaNegocio.Negocio;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ingreso_suam extends HttpServlet {
    public ingreso_suam() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Negocio neg = new Negocio();
        HttpSession session1 = request.getSession();
        String obtiene_usuario = session1.getAttribute("usuario_rut") + "";
        String ip = request.getRemoteAddr();
        int modo = Integer.parseInt(request.getParameter("txt_modo"));
        switch(modo) {
        case 1:
            int id_dau = Integer.parseInt(request.getParameter("txt_dau_id"));
            String paciente_rut = request.getParameter("txt_paciente");
            int derivador = Integer.parseInt(request.getParameter("cbo_derivador"));
            String medico = request.getParameter("cbo_medico");
            int camilla = Integer.parseInt(request.getParameter("cbo_camilla"));
            int tipo_dau = Integer.parseInt(request.getParameter("txt_dau_id_tipo"));
            cDas das = new cDas();
            if (tipo_dau == 1) {
                das.setDau_id(id_dau);
                das.setDau_nn_id(0);
                das.setRut_paciente(paciente_rut);
            } else {
                das.setDau_nn_id(id_dau);
                das.setDau_id(0);
                das.setRut_paciente("");
            }

            das.setDerivador(derivador);
            das.setCamilla(camilla);
            das.setMedico(medico);
            das.setIp(ip);
            das.setUsuario(obtiene_usuario);
            neg.ingresa_das(das);
            response.sendRedirect(neg.getLocal() + "uh_visita_suam.jsp");
        case 2:
        case 3:
        default:
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
