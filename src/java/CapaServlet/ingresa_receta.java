/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaServlet;

import CapaDato.cInsumo;
import CapaDato.cReceta;
import CapaDato.cUnidadMedida;
import CapaNegocio.NegocioQ;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Informatica
 */
public class ingresa_receta extends HttpServlet {

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
       
        PrintWriter out = response.getWriter();
        NegocioQ neg = new NegocioQ();

        ArrayList lista_medicamento = neg.lista_medicamento();
        Iterator it_medicamento = lista_medicamento.iterator();

        ArrayList lista_examenes = neg.lista_examenes();
        Iterator it_examenes = lista_examenes.iterator();

        HttpSession session1 = request.getSession();
        String txt_duo_seleccionado = request.getParameter("txt_duo_seleccionado");
        String txt_paciente = request.getParameter("txt_paciente");
        String txt_usuario = request.getParameter("txt_usuario");
        String txt_reposo = request.getParameter("txt_reposo").replace("'", "");
        String txt_regimen = request.getParameter("txt_regimen").replace("'", "");
        String txt_otrasindicaciones = request.getParameter("txt_otrasindicaciones").replace("'", "");
        String txt_diagnostico = request.getParameter("txt_diagnostico").replace("'", "");
        String txt_control_signos = request.getParameter("txt_control_signos").replace("'", "");
        String txt_aislamiento = request.getParameter("txt_aislamiento").replace("'", "");
        String txt_alergias = request.getParameter("txt_alergias").replace("'", "");
        String txt_contencion = request.getParameter("txt_contencion").replace("'", "");
        String txt_imagenes = request.getParameter("txt_imagenes").replace("'", "");
        String txt_otros = request.getParameter("txt_otros").replace("'", "");
        String txt_indicaciones_enfermeria = request.getParameter("txt_indicaciones_enfermeria").replace("'", "");
        String txt_indicaciones_nutricionista = request.getParameter("txt_indicaciones_nutricionista").replace("'", "");
        String txt_indicaciones_kinesiologo = request.getParameter("txt_indicaciones_kinesiologo").replace("'", "");
        String txt_indicaciones_otros = request.getParameter("txt_indicaciones_otros").replace("'", "");

        cReceta r = new cReceta();
        r.setId_receta(neg.obtenerid());
        r.setReposo(txt_reposo);
        r.setRegimen(txt_regimen);
        r.setIndicacion(txt_otrasindicaciones);
        r.setId_duo(Integer.parseInt(txt_duo_seleccionado));
        r.setNombre_usuario(txt_usuario);
        r.setAlergias(txt_alergias);
        r.setDiagnostico(txt_diagnostico);
        r.setControl_signos(txt_control_signos);
        r.setAislamiento(txt_aislamiento);
        r.setOtros(txt_otros);
        r.setImagenes(txt_imagenes);
        r.setContencion(txt_contencion);
        r.setIndicaciones_enfermeria(txt_indicaciones_enfermeria);
        r.setIndicaciones_kinesiologo(txt_indicaciones_kinesiologo);
        r.setIndicaciones_otros(txt_indicaciones_otros);
        r.setIndicaciones_nutricionista(txt_indicaciones_nutricionista);
        neg.ingresarindicaones(r);

        /* crear el for*/
        while (it_medicamento.hasNext()) {
            cInsumo ins = (cInsumo) it_medicamento.next();
            if (request.getParameter("medicamento" + ins.getId_insumo()) != null) {
                cReceta rec = new cReceta();
                rec.setId_receta(r.getId_receta());

                rec.setId_receta_detalle(Integer.parseInt(request.getParameter("medicamento" + ins.getId_insumo())));
                rec.setCantidad(Double.parseDouble(request.getParameter("cantidad" + ins.getId_insumo())));
                rec.setMedida(Integer.parseInt(request.getParameter("medida" + ins.getId_insumo())));
                rec.setFrecuencia(request.getParameter("frecuencia" + ins.getId_insumo()));
                rec.setDuracion(Integer.parseInt(request.getParameter("duracion" + ins.getId_insumo())));
                rec.setIndicacion(request.getParameter("indicaciones" + ins.getId_insumo()).replace("'", ""));
                rec.setId_via(Integer.parseInt(request.getParameter("via"+ ins.getId_insumo())));

                neg.ingresarindicaonesReceta(rec);
            }
        }

        while (it_examenes.hasNext()) {
            cUnidadMedida uni = (cUnidadMedida) it_examenes.next();
            if (request.getParameter("examenen" + uni.getId_unidad_medida()) != null) {
                cReceta rec = new cReceta();
                rec.setId_duo(r.getId_receta());
                rec.setId_receta(Integer.parseInt(request.getParameter("examenen" + uni.getId_unidad_medida())));

                neg.ingresarSolicitudExamenes(rec);
            }
        }

        RequestDispatcher a = request.getRequestDispatcher("index.jsp");
        a = request.getRequestDispatcher("receta.jsp?txt_duo=" + txt_duo_seleccionado);
        a.forward(request, response);
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
