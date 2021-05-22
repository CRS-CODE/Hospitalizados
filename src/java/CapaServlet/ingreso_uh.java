/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaServlet;

import CapaDato.cDuo;
import CapaDato.cEpicrisis;
import CapaDato.cHito;
import CapaDato.cPaciente;
import CapaDato.cUsuario;
import CapaNegocio.NegocioQ;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author EseGamboa
 */
public class ingreso_uh extends HttpServlet {

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
        // opcion = 1;
        NegocioQ neg = new NegocioQ();
        int opcion = Integer.parseInt(request.getParameter("modo").toString().trim());
        out.write("" + request.getParameter("modo") + "<br>");
        HttpSession session1 = request.getSession();
        //       if ( == null)
        String obtiene_usuario = session1.getAttribute("usuario_rut") + "";
        String ip = request.getRemoteAddr(); //IP del cliente

        switch (opcion) {
            case 1:
                /**
                 * ********** CASO 1***********
                 */
                //ingreso a paciente desde UGU
                
                int verificado_fonasa = Integer.parseInt(request.getParameter("verificado_fonasa"));
                // int queAgo = Integer.parseInt(request.getParameter("queAgo"));
                String rut_paciente = request.getParameter("rut");
                String nombres = request.getParameter("nombres");
                String apellidop = request.getParameter("apellidop");
                String apellidom = request.getParameter("apellidom");
                String fecha_nac = request.getParameter("fecha_nac");
                Date fecha1 = new Date(Integer.parseInt(fecha_nac.substring(6, 10)) - 1900, Integer.parseInt(fecha_nac.substring(3, 5)) - 1, Integer.parseInt(fecha_nac.substring(0, 2)), 0, 0, 0);
                String direccion = request.getParameter("direccion");
                String mail = request.getParameter("txt_mail");
                String telefono1 = request.getParameter("telefono1");
                String telefono2 = request.getParameter("telefono2");
                int sexo = Integer.parseInt(request.getParameter("rbt_sexo"));
                int id_comuna = Integer.parseInt(request.getParameter("id_comuna")); 
                int prevision = Integer.parseInt(request.getParameter("prevision"));
                int tramo = Integer.parseInt(request.getParameter("tramo"));
                int consultorio_pertenencia = Integer.parseInt(request.getParameter("id_consultorio_pertenencia").toString());
                int nacion = Integer.parseInt(request.getParameter("paciente_nacion"));
                 int pueblo_originario = Integer.parseInt(request.getParameter("id_pueblo").toString());
                
                String dia1 = "";
                String mes1 = "";
                String año1 = "";
                try {
                    dia1 = fecha_nac.substring(0, 2);
                    mes1 = fecha_nac.substring(3, 5);
                    año1 = fecha_nac.substring(6, 10);
                } catch (Exception ex) {
                    dia1 = "01";
                    mes1 = "01";
                    año1 = "1900";
                }

                String fecha_nac_mda = mes1 + "-" + dia1 + "-" + año1;
                int programa = 0;// ya que es un campo medico
                
                cDuo ddd = new cDuo();

                String fecha_duo = request.getParameter("fecha_duo");
                String hora_duo = request.getParameter("fecha_duo");

                String dia = fecha_duo.substring(0, 2);
                String mes = fecha_duo.substring(3, 5);
                String año = fecha_duo.substring(6, 10);

                String fecha_mda = dia + "-" + mes + "-" + año;
                out.write("" + fecha_mda + "<br>");

                hora_duo = hora_duo.substring(hora_duo.length() - 8, hora_duo.length());

//String anamnesis=request.getParameter("anamnesis");
                String anamnesis = " ";
                int id_cama = Integer.parseInt(request.getParameter("id_cama"));
                int id_derivador = Integer.parseInt(request.getParameter("id_derivado"));
                int id_categorizacion = Integer.parseInt(request.getParameter("id_derivado"));

                ddd.setDerivador_id(id_derivador);
                ddd.setCama(id_cama);
                ddd.setFecha_duo(fecha_mda);
                ddd.setHora_duo(hora_duo);
                ddd.setRut_paciente(rut_paciente);
                ddd.setRut_usuario(obtiene_usuario);
                ddd.setPrograma(programa);
                cPaciente pac_prestacion = neg.obtiene_paciente(rut_paciente);
                ddd.setId_prevision(pac_prestacion.getId_prevision());

                out.write("DERIVADOR " + ddd.getDerivador_id() + "<br>");
                out.write("CAMA " + ddd.getCama() + "<br>");
                out.write("FECHA " + ddd.getFecha_duo() + "<br>");
                out.write("HORA " + ddd.getHora_duo() + "<br>");

                // out.write("<br><br>prevision_paciente" + paciente_prevision + "<br><br>");
                cPaciente pac = new cPaciente();
                pac.setRut_paciente(rut_paciente);
                pac.setNombres_paciente(nombres);
                pac.setApellidop_paciente(apellidop);
                pac.setApellidom_paciente(apellidom);
                pac.setFecha_nac(fecha_nac_mda);
                pac.setNombre_usuario(obtiene_usuario);
                pac.setIp_contacto(ip);

                pac.setSexo(sexo);
                pac.setDireccion(direccion);
                pac.setComuna_codigo(id_comuna);
                pac.setTelefono1(telefono1);
                pac.setTelefono2(telefono2);
                pac.setConsultorio(consultorio_pertenencia);
                pac.setPueblo(pueblo_originario);
                pac.setFechanacimiento(fecha1);
                pac.setId_prevision(prevision);
                pac.setTramo(tramo);
                
                //  pac.setCodigo_fonasa(codigo_fonasa);
                // pac.setTramo_prevision(tramo);
                // pac.setPrais(prais);
                pac.setNacion(nacion);
                pac.setMail(mail);

                out.write("RUT " + pac.getRut_paciente() + "<br>");
                out.write("NOMBRES " + pac.getNombres_paciente() + "<br>");
                out.write("APELLIDOP " + pac.getApellidop_paciente() + "<br>");
                out.write("APELLIDOM " + pac.getApellidom_paciente() + "<br>");
                out.write("FECHA NAC " + pac.getFecha_nac() + "<br>");

                out.write("SEXO " + pac.getSexo() + "<br>");
                out.write("DIRECCION " + pac.getDireccion() + "<br>");
                out.write("COMUNA " + pac.getComuna_codigo() + "<br>");
//                out.write("PREVISION " + paciente_prevision + "<br>");
                out.write("CODIGO FONASA " + pac.getCodigo_fonasa() + "<br>");
                out.write("TRAMO " + pac.getTramo_prevision() + "<br>");
                out.write("PRAIS " + pac.getPrais() + "<br>");
                out.write("TELEFONO1 " + pac.getTelefono1() + "<br>");
                out.write("TELEFONO2 " + pac.getTelefono2() + "<br>");

                out.write("CONSULTORIO PERTENENCIA " + pac.getConsultorio() + "<br>");
                out.write("PUEBLO " + pac.getPueblo() + "<br>");
                out.write("FECHA/HORA " + ddd.getFecha_duo() + " ||" + ddd.getHora_duo());
                boolean existe = neg.buscarpaciente(rut_paciente);
//sssssssssssssss
                /**/
                ArrayList lista_duo = neg.lista_documentos_paciente(pac.getRut_paciente());
                Iterator itt = lista_duo.iterator();
                boolean sw_esta = false;
                while (itt.hasNext()) {
                    cEpicrisis epi = (cEpicrisis) itt.next();
                    if (epi.getEstado_duo() == 1 || epi.getEstado_duo() == 2 || epi.getEstado_duo() == 3 || epi.getEstado_duo() == 21) {
                        sw_esta = true;
                    }
                }
                /* 13022013 */

                if (!sw_esta) {
                    if (existe == true) {
                        neg.modifica_paciente(pac);
                        neg.ingresa_duo(ddd);
                    } else {
                        pac.setVerificado_fonasa(1);
                        neg.guardarPaciente(pac);
                       // neg.modifica_prevision_todas(pac.getRut_paciente());
                      //  neg.ingresa_prevision(pac);
                        cPaciente pacx = neg.buscarpacienteporrut(pac.getRut_paciente());
                        ddd.setId_prevision(pacx.getId_prevision());
                        neg.ingresa_duo(ddd);
                    }
                }


                response.sendRedirect(neg.getLocal() + "uh_visita.jsp");

                break;
            case 2:
                /**
                 * ********** CASO 2***********
                 */
                /* INGRESO DUO */
 /* FIN INGRESO DUO */
                /**
                 * **********FIN CASO 2***********
                 */
                break;

            case 3:
                /**
                 * ********** CASO 3***********
                 */
                /**
                 * **********FIN CASO 3***********
                 */
                break;
            case 4:
                /**
                 * ********** CASO 4***********
                 */
                /**
                 * **********FIN CASO 4***********
                 */
                break;
            case 5:
                /**
                 * ********** CASO 5***********
                 */
                /* INGRESO EPICRISIS */
 /* FIN INGRESO EPICRISIS */
                /**
                 * **********FIN CASO 5***********
                 */
                break;

            case 96:
                /*RESETEAR usuario*/
                cUsuario usu2 = new cUsuario();
                usu2.setRut_usuario(request.getParameter("txt_rut"));
                String cuatro2 = usu2.getRut_usuario().replace(".", "");
                cuatro2 = cuatro2.substring(0, 4);
                usu2.setPass_usuario(cuatro2);
                neg.modifica_password_usuario(usu2);
                response.sendRedirect(neg.getLocal() + "/uh_registro.jsp?ok96=" + usu2.getRut_usuario());
                /*FIN RESETEAR usuario*/
                break;
            case 97:
                /* MODIFICAR usuario*/
                cUsuario usu1 = new cUsuario();
                usu1.setPerfil_usuario(Integer.parseInt(request.getParameter("cbo_perfil")));
                usu1.setNombre_usuario(request.getParameter("txt_nombres"));
                usu1.setApellidop_usuario(request.getParameter("txt_apellidop"));
                usu1.setApellidom_usuario(request.getParameter("txt_apellidom"));
                usu1.setRut_usuario(request.getParameter("txt_rut"));
                usu1.setEstado_usuario(Integer.parseInt(request.getParameter("cbo_estado")));

                neg.modifica_usuario(usu1);
                response.sendRedirect(neg.getLocal() + "/uh_registro.jsp?ok97=" + usu1.getRut_usuario());
                /*FIN MODIFICAR usuario*/
                break;
            case 98:
                /**
                 * ********** CASO 98***********
                 */
                /* INGRESO HITO */
                cHito hit = new cHito();
                int tipo = Integer.parseInt(request.getParameter("cbo_tipo"));

                hit.setDetalle(request.getParameter("txa_hito"));
                hit.setUsuario_rut(request.getParameter("txt_usuario"));
                hit.setEstado(1);
                hit.setIp(ip);
                hit.setTipo(tipo);
                hit.setRut_paciente(request.getParameter("txt_paciente"));
                neg.ingresa_hito_paciente(hit);
                response.sendRedirect(neg.getLocal() + "/hito_paciente.jsp?rut=" + hit.getRut_paciente());

                /* FIN INGRESO HITO */
                /**
                 * **********FIN CASO 98***********
                 */
                break;
            case 99:
                /**
                 * ********** CASO 5***********
                 */
                /* INGRESO USUARIO */
                cUsuario usu = new cUsuario();
                usu.setPerfil_usuario(Integer.parseInt(request.getParameter("cbo_perfil")));
                usu.setNombre_usuario(request.getParameter("txt_nombres"));
                usu.setApellidop_usuario(request.getParameter("txt_apellidop"));
                usu.setApellidom_usuario(request.getParameter("txt_apellidom"));
                usu.setRut_usuario(request.getParameter("txt_rut"));
                usu.setEstado_usuario(1);

                String cuatro = usu.getRut_usuario().replace(".", "");
                cuatro = cuatro.substring(0, 4);
                usu.setPass_usuario(cuatro);

                neg.ingresa_usuario(usu);

                response.sendRedirect(neg.getLocal() + "/uh_registro.jsp?ok=" + usu.getRut_usuario());
                /* FIN INGRESO USUARIO */
                /**
                 * **********FIN CASO 5***********
                 */
                break;

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
