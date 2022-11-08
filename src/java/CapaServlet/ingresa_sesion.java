/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaServlet;

import CapaDato.cVisita;
import CapaNegocio.NegocioQ;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ingresa_sesion extends HttpServlet {
    public ingresa_sesion() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.addHeader("Cache-Control", "no-store");
        response.addHeader("Cache-Control", "private");
        response.setDateHeader("Expires", -1L);
        NegocioQ neg = new NegocioQ();
        HttpSession session1 = request.getSession();
        if (session1.getAttribute("usuario_rut") == null) {
            out.write("SIN SESION <script>alert('El tiempo de su sesión ha caducado; Ingrese Nuevamente'); window.location = '" + neg.getLocal() + "index.jsp?timeout=1' </script> ");
            response.sendRedirect("index.jsp?timeout=1");
        } else {
            int obtiene_modo = Integer.parseInt(request.getParameter("txt_modo"));
            int obtiene_duo = Integer.parseInt(request.getParameter("txt_duo"));
            String obtiene_usuario = session1.getAttribute("usuario_rut").toString();
            if (obtiene_modo == 1) {
                cVisita vis = new cVisita();
                vis.setHora_visita(request.getParameter("txt_hora") + ":" + request.getParameter("txt_minuto") + ":00");
                vis.setFecha_visita(neg.MMDDYYY(request.getParameter("txt_fecha"), 1) + " " + vis.getHora_visita());
                vis.setObs_visita(request.getParameter("txa_evolucion").replace("'", ""));
                vis.setRut_usuario(obtiene_usuario);
                vis.setId_duo(obtiene_duo);
                neg.ingresa_sesion(vis);
                RequestDispatcher a = request.getRequestDispatcher("index.jsp");
                a = request.getRequestDispatcher("sesion.jsp?txt_duo=" + obtiene_duo);
                a.forward(request, response);
            } else if (obtiene_modo == 2) {
                String motivo = request.getParameter("txt_motivo_anula");
                int var11 = Integer.parseInt(request.getParameter("txt_visita"));
            } else {
                out.write("Sin modo");
            }
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
