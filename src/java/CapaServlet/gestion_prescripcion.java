package CapaServlet;

import CapaDato.cInfusion;
import CapaDato.cInfusionDetalle;
import CapaDato.cPrescripcion;
import CapaNegocio.Negocio;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class gestion_prescripcion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session1 = request.getSession();
        if (session1.getAttribute("usuario_rut") == null) {
            response.sendRedirect("index.jsp?timeout=1");
            return;
        }

        String usuario = session1.getAttribute("usuario_rut").toString();
        Negocio neg = new Negocio();

        String action = request.getParameter("action") != null ? request.getParameter("action") : "cargar";
        String strDuo = request.getParameter("id_duo") != null ? request.getParameter("id_duo") : "0";
        int id_duo = Integer.parseInt(strDuo);
        String tab = request.getParameter("tab") != null ? request.getParameter("tab") : "prescripciones";

        if ("agregar_pm".equals(action)) {
            cPrescripcion pm = new cPrescripcion();
            pm.setId_duo(id_duo);
            pm.setId_insumo(Integer.parseInt(request.getParameter("id_insumo")));
            pm.setMedicamento_desc(request.getParameter("medicamento_desc").replace("'", ""));
            pm.setDosis(request.getParameter("dosis").replace("'", ""));
            pm.setUnidad_desc(request.getParameter("unidad_desc").replace("'", ""));
            String strVia1 = request.getParameter("id_via");
            pm.setId_via(strVia1 != null && !strVia1.isEmpty() ? Integer.parseInt(strVia1) : 0);
            pm.setVia_desc(request.getParameter("via_desc").replace("'", ""));
            pm.setFrecuencia(request.getParameter("frecuencia").replace("'", ""));
            pm.setObservacion(request.getParameter("observacion").replace("'", ""));
            pm.setUsuario_registro(usuario);
            neg.ingresarPrescripcion(pm);

        } else if ("modificar_pm".equals(action)) {
            cPrescripcion pm = new cPrescripcion();
            pm.setId_pm(Integer.parseInt(request.getParameter("id_pm")));
            pm.setId_insumo(Integer.parseInt(request.getParameter("id_insumo")));
            pm.setMedicamento_desc(request.getParameter("medicamento_desc").replace("'", ""));
            pm.setDosis(request.getParameter("dosis").replace("'", ""));
            pm.setUnidad_desc(request.getParameter("unidad_desc").replace("'", ""));
            String strVia2 = request.getParameter("id_via");
            pm.setId_via(strVia2 != null && !strVia2.isEmpty() ? Integer.parseInt(strVia2) : 0);
            pm.setVia_desc(request.getParameter("via_desc").replace("'", ""));
            pm.setFrecuencia(request.getParameter("frecuencia").replace("'", ""));
            pm.setObservacion(request.getParameter("observacion").replace("'", ""));
            neg.modificarPrescripcion(pm);

        } else if ("eliminar_pm".equals(action)) {
            neg.eliminarPrescripcion(Integer.parseInt(request.getParameter("id_pm")));

        } else if ("agregar_inf".equals(action)) {
            cInfusion inf = new cInfusion();
            inf.setId_duo(id_duo);
            inf.setSuero_desc(request.getParameter("suero_desc").replace("'", ""));
            String strVel1 = request.getParameter("velocidad_inf");
            inf.setVelocidad_inf(strVel1 != null && !strVel1.isEmpty() ? Double.parseDouble(strVel1) : 0);
            inf.setUnidad_velocidad(request.getParameter("unidad_velocidad").replace("'", ""));
            inf.setObservacion(request.getParameter("obs_inf").replace("'", ""));
            inf.setUsuario_registro(usuario);
            int id_pi = neg.ingresarInfusion(inf);
            if (id_pi > 0) {
                String[] medIds    = request.getParameterValues("inf_med_id[]");
                String[] medDescs  = request.getParameterValues("inf_med_desc[]");
                String[] medDosis  = request.getParameterValues("inf_med_dosis[]");
                String[] medUnidad = request.getParameterValues("inf_med_unidad[]");
                if (medIds != null) {
                    for (int i = 0; i < medIds.length; i++) {
                        if (medIds[i] != null && !medIds[i].isEmpty()) {
                            cInfusionDetalle det = new cInfusionDetalle();
                            det.setId_pi(id_pi);
                            det.setOrden(i + 1);
                            det.setId_insumo(Integer.parseInt(medIds[i]));
                            det.setMedicamento_desc(medDescs != null && i < medDescs.length ? medDescs[i].replace("'", "") : "");
                            det.setDosis(medDosis != null && i < medDosis.length ? medDosis[i].replace("'", "") : "");
                            det.setUnidad_desc(medUnidad != null && i < medUnidad.length ? medUnidad[i].replace("'", "") : "");
                            neg.ingresarInfusionDetalle(det);
                        }
                    }
                }
            }

        } else if ("modificar_inf".equals(action)) {
            cInfusion inf = new cInfusion();
            inf.setId_pi(Integer.parseInt(request.getParameter("id_pi")));
            inf.setSuero_desc(request.getParameter("suero_desc").replace("'", ""));
            String strVel2 = request.getParameter("velocidad_inf");
            inf.setVelocidad_inf(strVel2 != null && !strVel2.isEmpty() ? Double.parseDouble(strVel2) : 0);
            inf.setUnidad_velocidad(request.getParameter("unidad_velocidad").replace("'", ""));
            inf.setObservacion(request.getParameter("obs_inf").replace("'", ""));
            neg.modificarInfusion(inf);
            String[] medIds    = request.getParameterValues("inf_med_id[]");
            String[] medDescs  = request.getParameterValues("inf_med_desc[]");
            String[] medDosis  = request.getParameterValues("inf_med_dosis[]");
            String[] medUnidad = request.getParameterValues("inf_med_unidad[]");
            if (medIds != null) {
                for (int i = 0; i < medIds.length; i++) {
                    if (medIds[i] != null && !medIds[i].isEmpty()) {
                        cInfusionDetalle det = new cInfusionDetalle();
                        det.setId_pi(inf.getId_pi());
                        det.setOrden(i + 1);
                        det.setId_insumo(Integer.parseInt(medIds[i]));
                        det.setMedicamento_desc(medDescs != null && i < medDescs.length ? medDescs[i].replace("'", "") : "");
                        det.setDosis(medDosis != null && i < medDosis.length ? medDosis[i].replace("'", "") : "");
                        det.setUnidad_desc(medUnidad != null && i < medUnidad.length ? medUnidad[i].replace("'", "") : "");
                        neg.ingresarInfusionDetalle(det);
                    }
                }
            }

        } else if ("eliminar_inf".equals(action)) {
            neg.eliminarInfusion(Integer.parseInt(request.getParameter("id_pi")));
        }

        String origen = request.getParameter("origen") != null ? request.getParameter("origen") : "prescripcion";
        if ("receta".equals(origen)) {
            response.sendRedirect("receta.jsp?txt_duo=" + id_duo);
        } else {
            response.sendRedirect("prescripcion.jsp?id_duo=" + id_duo + "&tab=" + tab);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Gestion de prescripciones medicas e infusiones";
    }
}
