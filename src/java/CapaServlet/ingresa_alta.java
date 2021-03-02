//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package CapaServlet;

import CapaDato.cDuo;
import CapaDato.cEpicrisis;
import CapaNegocio.NegocioQ;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ingresa_alta extends HttpServlet {
    public ingresa_alta() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int opcion = Integer.parseInt(request.getParameter("txt_modo"));
        HttpSession session1 = request.getSession();
        String obtiene_usuario = "" + request.getParameter("txt_rut_usuario");
        int id_duo = Integer.parseInt(String.valueOf(request.getParameter("id_duo")));
        String ip = request.getRemoteAddr();
        NegocioQ neg = new NegocioQ();
        new cDuo();
        String fecha_dma;
        String dia;
        String mes;
        String año;
        String fecha_mda;
        String hora;
        switch(opcion) {
        case 1:
            fecha_dma = request.getParameter("fecha_epi");
            dia = fecha_dma.substring(0, 2);
            mes = fecha_dma.substring(3, 5);
            año = fecha_dma.substring(6, 10);
            fecha_mda = mes + "-" + dia + "-" + año;
            out.write("" + fecha_mda + "<br>");
            hora = request.getParameter("fecha_epi");
            hora = hora.substring(hora.length() - 8, hora.length());
            out.write("" + hora);
            String resumen = request.getParameter("resumen").replace("'", "");
            String examenes = request.getParameter("examenes").replace("'", "");
            String diagnosticos = request.getParameter("diagnosticos").replace("'", "");
            String indicaciones = request.getParameter("indicaciones").replace("'", "");
            String medicamentos_prescritos = request.getParameter("medicamentos_prescritos").replace("'", "");
            
            
            cEpicrisis epi = new cEpicrisis();
            epi.setFecha_epicrisis(fecha_mda);
            epi.setHora_epicrisis(hora);
            epi.setResumen_epicrisis(resumen);
            epi.setExamen_epicrisis(examenes);
            epi.setDiagnostico_epicrisis(diagnosticos);
            epi.setIndicacion_epicrisis(indicaciones);
            epi.setRut_usuario(obtiene_usuario);
            epi.setIp_epicrisis(ip);
            epi.setId_duo(id_duo);
            
            epi.setMedicamentos_prescritos(medicamentos_prescritos);
            if (!obtiene_usuario.equalsIgnoreCase("null")) {
                neg.ingresa_epicrisis(epi);
                neg.modifica_estado_duo(id_duo, 3);
            } else {
                out.write("<script>alert('No se pudo completar esta operación porque su sesion ya habia caducado')</script>");
            }

            response.sendRedirect(neg.getLocal() + "uh_visita.jsp");
            break;
        case 2:
            neg.modifica_valida_epicrisis(id_duo);
            response.sendRedirect(neg.getLocal() + "uh_visita.jsp");
            break;
        case 3:
            int id_destino = Integer.parseInt(request.getParameter("id_destino"));
            fecha_dma = request.getParameter("fecha_epi");
            dia = fecha_dma.substring(0, 2);
            mes = fecha_dma.substring(3, 5);
            año = fecha_dma.substring(6, 10);
            fecha_mda = mes + "-" + dia + "-" + año;
            hora = request.getParameter("fecha_epi");
            hora = hora.substring(hora.length() - 8, hora.length());
            String obs = request.getParameter("obs");
            neg.ingresa_alta_adm(obs, fecha_mda, hora, obtiene_usuario, id_duo, id_destino, ip);
            neg.modifica_estado_duo_alta_administrativa(id_duo, 4);
            response.sendRedirect(neg.getLocal() + "uh_visita.jsp");
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
