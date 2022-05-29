/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaServlet;

import CapaDato.cUsuario;
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
 * @author Dis
 */
public class valida_usuario extends HttpServlet {

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


        PrintWriter out = response.getWriter();

        Negocio neg = new Negocio();
        HttpSession session = request.getSession();
        String pass = request.getParameter("pass");
        String usuario = request.getParameter("user");

        cUsuario usu = neg.valida_Usuario(usuario, pass);
        if (usu.getRut_usuario().equalsIgnoreCase("")) {
            out.write("NO");
            response.sendRedirect("index.jsp?nn=1");
        } else {
            //out.write("SI");
            String nombre_completo = usu.getNombre_usuario() + " " + usu.getApellidop_usuario() + " " + usu.getApellidom_usuario();
            session.setAttribute("usuario_nombre_completo", nombre_completo);
            session.setAttribute("usuario_nombre", usu.getNombre_usuario());
            session.setAttribute("usuario_rut", usu.getRut_usuario());

            session.setAttribute("usuario_perfil", usu.getPerfil_usuario());
            session.setAttribute("usuario_perfil_descripcion", usu.getPerfil_descripcion_usuario());
            neg.modifica_ultima_sesion(usu.getRut_usuario());
            String rrr = usu.getRut_usuario().replace(".", "");
            rrr = rrr.substring(0, 4);
            if (usu.getPass_usuario().equalsIgnoreCase(rrr)) {
                response.sendRedirect("clave/pass.jsp?rut=" + usu.getRut_usuario());
            } else {
                response.sendRedirect("uh_visita.jsp");
            }

        }

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
