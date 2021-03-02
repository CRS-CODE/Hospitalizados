<%-- 
    Document   : mantencion_alta_medica_carga
    Created on : 12-feb-2013, 16:10:22
    Author     : Silvio
--%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="CapaNegocio.NegocioQ"%>


<%

    NegocioQ neg = new NegocioQ();
    int modo = Integer.parseInt(request.getParameter("modo"));
    int id_duo = Integer.parseInt(request.getParameter("txt_duo"));
    HttpSession session1 = request.getSession();
    String rut_usuario = "";
    try {
        rut_usuario = session1.getAttribute("usuario_rut").toString();
    } catch (Exception ex) {
        out.write("<h1>Se sesión ha finalizado</h1>");
    }

    if (modo == 1) {
        // out.write(modo + " " + id_duo);
        cDuo duo = neg.obtiene_duo(id_duo);
        // out.write("<br>" + duo.getEstado_duo() + " " + duo.getEstado_duo_descripcion());
        if (duo.getEstado_duo() == 3) {
            //  out.write("");
            out.write("<table>");
            out.write("<tr><th colspan='2'  ></th></tr>");
            out.write(" <tr>");
            out.write("   <td><b>Id Duo</b></td>");
            out.write("   <td>" + duo.getId_duo() + "</td>");
            out.write(" </tr>");
            out.write(" <tr>");
            out.write("    <td><b>Paciente</b></td>");
            out.write("   <td>" + duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente() + "</td>");
            out.write("</tr>");
            out.write(" <tr>");
            out.write("    <td><b>Rut</b></td>");
            out.write("   <td>" + duo.getRut_paciente() + "</td>");
            out.write("</tr>");
            out.write(" <tr>");
            out.write("      <td><b>Camilla</b></td>");
            out.write("     <td>" + duo.getCama_descripcion() + "</td>");
            out.write(" </tr>");
             out.write("<tr><th colspan='2' > <input type='button' name='btn_anular' id='btn_anular' onclick='Enviar2(" + duo.getId_duo() + ");' class='btn btn-primary' value='Anular Alta Médica' >  </th></tr>");
          //  out.write("<tr><th colspan='2' > <input type='SUBMIT' name='btn_anular' id='btn_anular' onclick='Enviar2(" + duo.getId_duo() + ",'" + rut_usuario + "');return false;' class='btn btn-primary' value='Anular Alta Médica' >  </th></tr>");
            out.write("<tr><td colspan='2'  ><br><br><i>*Este proceso anulará el alta médica y dejará al paciente en estado -En cama- </i></td></tr>");
            out.write("</table>");
        } else {

            out.write("<h2>El Duo " + id_duo + " no tiene un alta de médica en espera.<br>No se puede realizar esta acción</h2>");
        }

    } else if (modo == 2) {

        String obtiene_usuario = request.getParameter("txt_rut_responsable");

       // out.write("MODO 2 .....--");
        if (neg.elimina_epicrisis_segun_duo(id_duo, obtiene_usuario)) {
            out.write("Se anulo DUO n°" + id_duo);
        } else {
            out.write("No se pudo anular-Intentelo de nuevo más tarde");
        }

    }


%>


