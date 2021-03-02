/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaServlet;

import CapaDato.DetailIndexBarthel;
import CapaDato.DuoDetailBarthel;
import CapaDato.DuoIndexBarthel;
import CapaNegocio.Negocio;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kairin
 */
public class insertIndexBarthel extends HttpServlet {

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
        String UserRegisters = request.getParameter("txt_usuario");
        String typeRegisters = request.getParameter("type_attention");
        String totalPuntuction = request.getParameter("txt_score");
        String degreeOfDependency = request.getParameter("txt_outcome");
        int idDuo = Integer.parseInt(request.getParameter("txt_duo"));
        Negocio controller = new Negocio();
        DuoDetailBarthel duoDetail = new DuoDetailBarthel();
        DuoIndexBarthel duoIndex = new DuoIndexBarthel();
        duoIndex.setDegreeOfDependency(degreeOfDependency);
        duoIndex.setStatus(1);
        duoIndex.setTotalPuntuction(Integer.parseInt(totalPuntuction));
        duoIndex.setTypeRegisters(Integer.parseInt(typeRegisters));
        duoIndex.setIdDuo(idDuo);
        duoIndex.setUserRegisters(UserRegisters);
        controller.insertDuoIndexBarthel(duoIndex);
        int id = controller.getIdRegisterDuoIndexBarthel(duoIndex.getIdDuo(), duoIndex.getTypeRegisters());
        List<DetailIndexBarthel> listDetailIndexBarthel = controller.searchDetailIndexBarthel();
        for (DetailIndexBarthel detailIndexBarthel : listDetailIndexBarthel) {
            if (request.getParameter("detailBarthel" + detailIndexBarthel.getIdDetail()) != null) {
                if (request.getParameter("detailBarthel" + detailIndexBarthel.getIdDetail()).equals("checkbox")) {
                    duoDetail.setIdDetailBarthel(detailIndexBarthel.getIdDetail());
                    duoDetail.setIdRegisterDuo(id);
                    controller.insertDouDetailBarthel(duoDetail);
                }

            }

        }

        response.sendRedirect("index_barthel/registers_index_barthel.jsp?id=" + id);

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
