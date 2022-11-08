//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package CapaServlet;

import CapaDato.Ingreso;
import CapaDato.cDuo;
import CapaNegocio.NegocioQ;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ingreso_dato extends HttpServlet {

    public ingreso_dato() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int opcion = Integer.parseInt(request.getParameter("txt_modo"));
        HttpSession session1 = request.getSession();
        String obtiene_usuario = "" + request.getParameter("txt_rut_usuario");
        int estado = Integer.parseInt(String.valueOf(request.getParameter("estado")));
        int id_duo = Integer.parseInt(String.valueOf(request.getParameter("id_duo")));
        String duo_ip = request.getRemoteAddr();
        NegocioQ neg = new NegocioQ();
        cDuo duo = new cDuo();
        String[] PreDuo;
        int i;
        if (request.getParameterValues("EnfCronH") != null) {
            PreDuo = request.getParameterValues("EnfCronH");

            for (i = 0; i < PreDuo.length; ++i) {
                neg.ingresa_EnfCronica(id_duo, Integer.parseInt(PreDuo[i]));
            }
        }

        try {
            if (request.getParameterValues("PreDuo") != null) {
                PreDuo = request.getParameterValues("PreDuo");

                for (i = 0; i < PreDuo.length; ++i) {
                    neg.ingresa_PrestacionesDuo(id_duo, Integer.parseInt(PreDuo[i]));
                }
            }
        } catch (Exception var40) {
            out.write("Try Prestación--linea 65 ingreso_dato");
        }

        switch (opcion) {
            case 1:
                Ingreso ingreso = new Ingreso();
                ingreso.setMorbilidades(request.getParameter("morbilidades"));
                ingreso.setFarmacos(request.getParameter("farmacos"));
                ingreso.setAlergias(request.getParameter("alergias"));
                ingreso.setPlan(request.getParameter("plan"));
                ingreso.setPa(request.getParameter("pa"));
                ingreso.setFc(request.getParameter("fc"));
                ingreso.setT(request.getParameter("t"));
                ingreso.setSat(request.getParameter("sat"));
                ingreso.setFio2(request.getParameter("fio2"));
                ingreso.setHgt(request.getParameter("hgt"));
                ingreso.setEvolucion(request.getParameter("evolucion"));
                ingreso.setVvp(request.getParameter("vvp"));
                ingreso.setCup(request.getParameter("cup"));
                ingreso.setSng(request.getParameter("sng"));
                ingreso.setSny(request.getParameter("sny"));
                ingreso.setGtt(request.getParameter("gtt"));
                ingreso.setPicc(request.getParameter("picc"));
                ingreso.setCvc(request.getParameter("cvc"));
                ingreso.setTqt(request.getParameter("tqt"));
                ingreso.setLpp(request.getParameter("lpp"));
                ingreso.setPrestaciones(request.getParameter("prestaciones"));
                ingreso.setVenoso(request.getParameter("venoso"));
                ingreso.setArterial(request.getParameter("arterial"));
                ingreso.setOtro(request.getParameter("otro"));
                ingreso.setEducacion(request.getParameter("educacion"));
                ingreso.setDocumento(request.getParameter("documento"));
                ingreso.setPcr(request.getParameter("pcr"));
                ingreso.setEcg(request.getParameter("ecg"));
                ingreso.setFrecuenciaRespiratoria(request.getParameter("frecuenciaRespiratoria"));
                ingreso.setIdDuo(id_duo);
                ingreso.setUsuario(obtiene_usuario);

                if (!obtiene_usuario.equalsIgnoreCase("null")) {

                    neg.ingresa_ingreso_enfermeria(ingreso);
                    duo.setIp_ing_enf(duo_ip);
                    if (estado == 2) {
                        cDuo duox = neg.obtiene_duo_liviano(id_duo);
                        if (duox.getEstado_duo() == 21) {
                            duo.setEstado_duo(21);
                        } else {
                            duo.setEstado_duo(estado);
                        }
                    } else {
                        duo.setEstado_duo(estado);
                    }

                    duo.setId_duo(id_duo);
                    neg.modifica_duo_x_enfermeria(duo);
                    response.sendRedirect(neg.getLocal() + "uh_visita.jsp");
                } else {
                    out.write("<script> alert('Su sesion ha expirado'); ");
                    out.write(" window.location = '" + neg.getLocal() + "cierra_sesion' ");
                    out.write("</script>");
                }
                break;
            case 2:
                String anamnesis = request.getParameter("anamnesis").replace("'", "");
                i = Integer.parseInt(request.getParameter("cbo_consultorio_pertenencia"));
                String indicacionesMedicas = request.getParameter("indicacionesMedicas").replace("'", "");
                duo.setConsultorio(i);
                String rut_paciente = request.getParameter("txt_rut").toUpperCase();
                neg.modifica_paciente_datos(rut_paciente, i);
                duo.setCategorizacion_id(0);
                duo.setAnamnesis_duo(anamnesis);
                duo.setIp_ing_med(duo_ip);
                duo.setEstado_duo(21);
                duo.setIndicacionesMedicas(indicacionesMedicas);
                duo.setId_duo(id_duo);
                if (!obtiene_usuario.equalsIgnoreCase("null")) {
                    duo.setRut_usuario_ing_med(obtiene_usuario);
                    neg.modifica_duo_x_medico(duo);
                } else {
                    out.write("<script> alert('Su sesion ha expirado'); ");
                    out.write(" window.location = '" + neg.getLocal() + "cierra_sesion' ");
                    out.write("</script>");
                }

                response.sendRedirect(neg.getLocal() + "uh_visita.jsp");
            case 3:
        }

        out.write("El sistema no pudo redireccionar a la pagina siguiente...<a href='uh_visita.jsp'>IR A VISITA...</a>");
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
