//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package CapaServlet;

import CapaDato.cDiagnostico;
import CapaNegocio.NegocioQ;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ingreso_diagnostico extends HttpServlet {
    public ingreso_diagnostico() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String modo = request.getParameter("modo");
        String descripcion_diagnostico = request.getParameter("diagnostico");
        int id_duo = 0;
        int tipo_diagnostico = 0;
        int id_diagnostico = 0;

        try {
            tipo_diagnostico = Integer.parseInt(request.getParameter("tipo_diagnostico_duo"));
        } catch (Exception var17) {
        }

        try {
            id_duo = Integer.parseInt(request.getParameter("id_duo"));
        } catch (Exception var16) {
        }

        try {
            id_diagnostico = Integer.parseInt(request.getParameter("id_diagnostico_duo"));
        } catch (Exception var15) {
        }

        NegocioQ neg = new NegocioQ();
        cDiagnostico diag = new cDiagnostico();
        diag.setDescripcion_diagnostico(descripcion_diagnostico);
        diag.setId_diagnostico(id_diagnostico);
        diag.setId_duo(id_duo);
        diag.setTipo_diagnostico(tipo_diagnostico);
        if (modo.equals("1")) {
            neg.ingresa_diagnostico_duo(diag);
        } else if (modo.equals("2")) {
            neg.elimina_diagnostico_duo(diag.getId_diagnostico());
        }

        ArrayList lista_diagnostico = neg.lista_diagnostico(diag.getId_duo(), diag.getTipo_diagnostico() + "");
        Iterator it = lista_diagnostico.iterator();
        out.write(" <table width=\"700\"><tr> ");
        out.write(" <th class=\"DATOS\">N°</th>");
        out.write(" <th class=\"DATOS\">Descripción Diagnóstico</th>");
        out.write(" <th class=\"DATOS\">Eli</th> ");
        out.write(" </tr>");

        for(int contador = 1; it.hasNext(); ++contador) {
            cDiagnostico aux = (cDiagnostico)it.next();
            out.write("<tr>");
            out.write("<td class=\"DATOS\">" + contador + " </td> ");
            out.write("<td class=\"DATOS\">" + aux.getDescripcion_diagnostico() + "  </td>");
            out.write("<td class=\"DATOS\"> <img src=\"../Iconos/action_stop.gif\" onclick=\"EliminaDiag(" + aux.getId_diagnostico() + ")\" style=\"cursor:pointer\"> </td>");
            out.write(" </tr> ");
        }

        out.write("  </table>");
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
