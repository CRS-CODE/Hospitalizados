<%-- 
    Document   : uh_pacientes_documento
    Created on : 11-may-2012, 12:40:41
    Author     : EseGamboa
--%>

<%@page import="CapaDato.cVisita"%>
<%

    HttpSession session1 = request.getSession();
    NegocioQ neg = new NegocioQ();
    if (session1.getAttribute("usuario_rut") == null) {
        out.write("SIN SESION <script>alert('El tiempo de su sesión ha caducado; Ingrese Nuevamente');"
                + " window.location = '" + neg.getLocal() + "index.jsp?timeout=1' </script> ");
        response.sendRedirect("index.jsp?timeout=1");
    } else {
%>


<%@page import="CapaDato.cPaciente"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@page import="CapaDato.cEpicrisis"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="CapaNegocio.Negocio"%>
<%
    int obtiene_perfil = Integer.parseInt(session1.getAttribute("usuario_perfil").toString());

    String rut_paciente = request.getParameter("user");
    Negocio controller = new Negocio();
    //  out.write("Documentos del Paciente " + rut_paciente + "<br>");
    ArrayList DuoEpi = neg.lista_documentos_paciente(rut_paciente);
    Iterator it = DuoEpi.iterator();
    cPaciente pac = neg.obtiene_paciente(rut_paciente);

    String titulo = " style=' background-color: #f7903b ; color: white '  ";
    String titulo_menos = " style=' background-color: #f7903b ; color: white;FONT-SIZE:9px; '  ";
    String datos = " style=' background-color: #fcd5b6 ; color: black '  ";

    if (DuoEpi.size() == 0) {
        out.write("<b>No se encontraron Resultados para " + rut_paciente + "</b>");
    } else {
        out.write("<table cellpadding='8'><thead>"
                + "<tr " + titulo + " >"
                + "<td colspan='6' >Documentos del Paciente: " + pac.getNombres_paciente() + " " + pac.getApellidop_paciente() + " " + pac.getApellidom_paciente() + "</td>"
                + "</tr>"
                + "<tr " + titulo_menos + " >"
                + "<td>Rut</td><td colspan='2'>" + rut_paciente + "</td>"
                + "<td>Fecha Nac.</td><td colspan='2'>" + pac.getFecha_nac() + "</td>"
                + "</tr>"
                + "<tr " + titulo + " >"
                + "  <th>Duo</th>"
                + "  <th>Fecha Duo</th>"
                + "  <th>Epicrisis</th>"
                + "  <th>Fecha Epicrisis</th>"
                + " <th>Tramo</th>"
                + " <th>Prevision</th>"
                + " <th>Registros Profesional</th>  "
                + " <th>Visita Médica</th>  "
                + " <th>Visita Enfermería</th> "
                + " <th>Indice Barthel Ingreso</th> "
                + " <th>Indice Barthel Egreso</th> "
                + "<th>Historial Completo </th> "
                + " </tr>"
                + " </thead><tbody>");
        while (it.hasNext()) {
            cEpicrisis not = (cEpicrisis) it.next();
            int old = neg.IngresoOld(not.getId_duo());
            out.write("<tr valign='TOP' " + datos + " >");
            out.write("<td><center>" + not.getId_duo() + "</center><br>");
            out.write("<a href='" + neg.getLocal() + "PDF_DUO?id_duo=" + not.getId_duo() + "' target='_blank'><img src='Imagenes/doctorImp.png' width='35' height='36' alt='Ingreso Médico' title='Ingreso Médico' /></a>");
            if (old > 0) {
                out.write("<a href='" + neg.getLocal() + "PDF_ingreso_enfermeriaOLD?txt_duo=" + not.getId_duo() + "' target='_blank'><img src='Imagenes/enfermeraImp.png' width='35' height='36' alt='Ingreso Enfermería' title='Ingreso Enfermería' /></a>");

            } else {
                out.write("<a href='" + neg.getLocal() + "PDF_ingreso_enfermeria?txt_duo=" + not.getId_duo() + "' target='_blank'><img src='Imagenes/enfermeraImp.png' width='35' height='36' alt='Ingreso Enfermería' title='Ingreso Enfermería' /></a>");

            }
            out.write("</td>");
            out.write("<td>" + not.getFecha_duo() + " </td>");
            if (not.getId_epicrisis() != 0) {
                out.write("<td>" + not.getId_epicrisis() + "<br><br>");
                out.write("<a href='PDF_egresoMedico?txt_duo=" + not.getId_duo() + "' target='_blank'>");
                out.write("<img src='Imagenes/doctorImpEpi.png' width='35' height='36' alt='Epicrisis' title='Epicrisis' />");
                out.write("</a>");
                out.write("</td>");
                out.write("<td>" + not.getFecha_epicrisis() + "</td>");
            } else {
                out.write("<td> </td>");
                out.write("<td></td>");
            }
            out.write("<td>Tramo " + not.getTramo() + "&nbsp;</td>");
            out.write("<td>   <font style=' font-size:   xx-small ' >" + not.getPrevision_descri().replace(" ", "<br>") + " " + not.getPrais_descri().replace(" ", "<br>") + "  </font> </td>");

            out.write("<td>");

%>
<form  action='PDF_registro_social' id="form_documento_registro" method='POST' target='_blank' >
    <input type='hidden' value='<%=not.getId_duo()%>' name='txt_duo'> 
    <a style=' font-size:   xx-small '  href="#" onclick="document.getElementById('form_documento_registro').submit();
            return false"  > Registro social
        <img src='Imagenes/pdf.png' alt="Ver documento"  >
    </a>
    <p style=' font-size:   xx-small ' > Kinesiologia  <img src="Imagenes/pdf.png" alt="" onclick="javascript:  window.open('/modulo_uhce/PDF_sesion_kinesiologia?txt_duo=<%=not.getId_duo()%>', '', 'width=600,height=450')"/></p>
    <p  style=' font-size:   xx-small ' > Terapeuta  <img src="Imagenes/pdf.png" alt="" onclick="javascript:  window.open('/modulo_uhce/PDF_sesion_terapeuta?txt_duo=<%=not.getId_duo()%>', '', 'width=600,height=450')"/></p>
    <p  style=' font-size:   xx-small ' > Nutricionista  <img src="Imagenes/pdf.png" alt="" onclick="javascript:  window.open('/modulo_uhce/PDF_sesion_nutricionista?txt_duo=<%=not.getId_duo()%>', '', 'width=600,height=450')"/></p>
    <p  style=' font-size:   xx-small ' > Fonoaudiologa  <img src="Imagenes/pdf.png" alt="" onclick="javascript:  window.open('/modulo_uhce/PDF_sesion_fono?txt_duo=<%=not.getId_duo()%>', '', 'width=600,height=450')"/></p>     
    <p  style=' font-size:   xx-small ' > Psicòlogo/a  <img src="Imagenes/pdf.png" alt="" onclick="javascript:  window.open('/modulo_uhce/PDF_sesion_psicolo?txt_duo=<%=not.getId_duo()%>', '', 'width=600,height=450')"/></p>

</form>   
<%

    out.write("</td>");

    int idIngreso = controller.getIdRegisterDuoIndexBarthel(not.getId_duo(), 1);
    int idEgreso = controller.getIdRegisterDuoIndexBarthel(not.getId_duo(), 2);
    out.write("<td>");
    out.write("<form action=\"visita_enfermeria_historial.jsp\" method=\"post\" target='_blank' onsubmit=\"\"> "
            + " <input type='hidden' name='txt_duo' value='" + not.getId_duo() + "'  />"
            + " <input type=\"submit\" class=\"btn btn-primary\" value=\"Ver categorizaciones\" name=\"btn_Categorizacion\" />"
            + " </form>");
    out.write("</td>");

    //visita medica 14082015
    out.write("<td>");
    out.write("<form action=\"PDF_visita_medica\" method=\"post\" target='_blank' onsubmit=\"\"> "
            + " <input type='hidden' name='txt_duo' value='" + not.getId_duo() + "'  />"
            + " <input type=\"submit\" class=\"btn btn-primary\" value=\"Ver visita\" name=\"btn_visita_medica\" />"
            + " </form>");
    out.write("</td>");
    if (idIngreso != 0) {
        out.write("<td>");
        out.write("<form action=\"PDF_indexBarthel\" method=\"post\" target='_blank' onsubmit=\"\"> "
                + " <input type='hidden' name='id' value='" + idIngreso + "'  />"
                + " <input type=\"submit\" class=\"btn btn-primary\" value=\"Ver visita\" name=\"btn_visita_medica\" />"
                + " </form>");
        out.write("</td>");
    } else {
        out.write("<td>");
        out.write("<p> Sin Registro <p>");
        out.write("</td>");

    }
    if (idEgreso != 0) {
        out.write("<td>");
        out.write("<form action=\"PDF_indexBarthel\" method=\"post\" target='_blank' onsubmit=\"\"> "
                + " <input type='hidden' name='id' value='" + idEgreso + "'  />"
                + " <input type=\"submit\" class=\"btn btn-primary\" value=\"Ver visita\" name=\"btn_visita_medica\" />"
                + " </form>");
        out.write("</td>");
    } else {
        out.write("<td>");
        out.write("<p> Sin Registro <p>");
        out.write("</td>");

    }
    out.write("<td>");
    out.write("<form action=\"PDF_HISTORIAL\" method=\"post\" target='_blank' onsubmit=\"\"> "
            + " <input type='hidden' name='txt_duo' value='" + not.getId_duo() + "'  />"
            + " <input type=\"submit\" class=\"btn btn-primary\" value=\"Ver visita\" name=\"btn_visita_medica\" />"
            + " </form>");
    out.write("</td>");

    out.write("</tr>");
%>




<%      }
        out.write(" </tbody> "
                + " </table>");
    }


%>



<%    }


%>


