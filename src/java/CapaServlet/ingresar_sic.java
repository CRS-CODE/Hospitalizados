/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaServlet;

import CapaDato.cInterconsulta;
import CapaNegocio.Negocio;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kairin
 */
public class ingresar_sic extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session1 = request.getSession();
        Negocio negocio = new Negocio();
        String obtiene_usuario = session1.getAttribute("usuario_rut") + "";
        cInterconsulta sic = new cInterconsulta();
        sic.setDiagnostico(request.getParameter("diagnostico"));
        sic.setEspecialidad(request.getParameter("especialidad"));
        sic.setEspecificarProblema(request.getParameter("especificarProblema"));
        sic.setFundamentosDiagnostico(request.getParameter("fundaentosDiagnostico"));
        sic.setIdEstablecimiento(Integer.parseInt(request.getParameter("establecimiento")));
        sic.setIdRazon(Integer.parseInt(request.getParameter("razonConsulta")));
        sic.setIdSospechaProblema(Integer.parseInt(request.getParameter("sospechaProblema")));
        sic.setOtraRazon(request.getParameter("especificarOtro"));
        sic.setSubProblema(request.getParameter("subproblema"));
        sic.setIdDuo(Integer.parseInt(request.getParameter("duo")));
        sic.setExamenesRealizados(request.getParameter("examenRealizados"));
        sic.setUser(obtiene_usuario);
        Boolean ingreso = negocio.ingresa_sic(sic);
        int id = negocio.getIdSic(sic.getIdDuo());
        response.sendRedirect(negocio.getLocal() + "/GenerarInterconsulta.jsp?id=" + id);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
