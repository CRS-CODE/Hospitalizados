<%-- 
    Document   : datos_paciente_carga
    Created on : 17-may-2012, 12:32:48
    Author     : EseGamboa
--%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page import="CapaNegocio.Negocio"%>
<%
    HttpSession session1 = request.getSession();
    String titulo = " style=' background-color: #4169E1 ; color: white '  ";
    String datos = " style=' background-color: #87CEFA ; color: black '  ";
    // String datos = "  style=' color: #000080 '  ";
    int obtiene_perfil = 0;
    String tipo_perfil = "" + session.getAttribute("usuario_perfil_descripcion");
    try {
        obtiene_perfil = Integer.parseInt("" + session.getAttribute("usuario_perfil"));
    } catch (NumberFormatException ex) {
        obtiene_perfil = -1;
    }

    int numero_duo = Integer.parseInt(request.getParameter("duo").toString().trim());

    NegocioQ neg = new NegocioQ();
    cDuo duo = neg.obtiene_duo(numero_duo);

    ArrayList see_visita = new ArrayList();
    see_visita.add(1);
    see_visita.add(2);
    see_visita.add(4);
    see_visita.add(8);
    see_visita.add(9);

    see_visita.add(10);
    see_visita.add(12);
    see_visita.add(13);
    see_visita.add(14);
    see_visita.add(15);
    see_visita.add(16);
    see_visita.add(17);
    see_visita.add(18);

    // see_visita.add(12);
    ArrayList see_alta_medica = new ArrayList();
    see_alta_medica.add(1);
    see_alta_medica.add(2);
    see_alta_medica.add(4);
    see_alta_medica.add(8);
    see_alta_medica.add(9);
    see_alta_medica.add(10);
    see_alta_medica.add(12);

    ArrayList see_alta_administrativa = new ArrayList();
    see_alta_administrativa.add(3);
    see_alta_administrativa.add(10);
    see_alta_administrativa.add(12);

    ArrayList see_kinesiologia = new ArrayList();

    see_kinesiologia.add(1);
    see_kinesiologia.add(2);
    see_kinesiologia.add(4);
    see_kinesiologia.add(8);
    see_kinesiologia.add(9);
    see_kinesiologia.add(10);
    see_kinesiologia.add(12);
    see_kinesiologia.add(13);

    ArrayList see_cambio_cama = new ArrayList();
    see_cambio_cama.add(1);
    see_cambio_cama.add(2);
    see_cambio_cama.add(4);
    see_cambio_cama.add(8);
    see_cambio_cama.add(9);
    see_cambio_cama.add(10);
    see_cambio_cama.add(12);


%>
<script>
    function valida_valida_epicrisis()
    {
        if (confirm('Esta a Punto de Validar que el Paciente Realmente dejo la Cama?'))
        {
            return true;
        } else {
            return false;
        }
    }
</script>

<div style=" vertical-align: top  " align="right" >
    <a href="../uh_visita.jsp">
        <img src="../Imagenes/fileclose.png" width="30" height="30" alt="Cerrar Ventana"/>
    </a>
</div>
<fieldset><legend>Datos del Paciente</legend>
    <table border="1" >
        <tbody>
            <tr>
                <td <%=titulo%> >Rut Paciente</td>
                <td colspan="2" <%=datos%> ><% out.write("" + duo.getRut_paciente());%></td>

                <td <%=titulo%> >Fecha Nacimiento</td>
                <td colspan="2" <%=datos%> >   <% out.write("" + duo.getFecha_nac());%></td>
            </tr>
            <tr>
                <td <%=titulo%> >Nombre Completo</td>
                <td colspan="3" <%=datos%> ><% out.write("" + duo.getNombres_paciente() + " " + duo.getApellidop_paciente() + " " + duo.getApellidom_paciente());%></td>

                <td <%=titulo%> >Edad</td>
                <td <%=datos%> ><% out.write("" + duo.getEdad());%></td>
            </tr>
            <tr>
                <td <%=titulo%> >Dirección</td>
                <td colspan="5" <%=datos%> ><% out.write("" + duo.getDireccion() + " ," + duo.getComuna_descri());%></td>
            </tr>
            <tr>
                <td <%=titulo%> >Teléfono</td>
                <td colspan="5" <%=datos%> ><% out.write("" + duo.getTelefono1() + "/" + duo.getTelefono2());%></td>
            </tr>
            <tr>
                <td <%=titulo%> >Previsión</td>
                <td colspan="3" <%=datos%> ><% out.write("" + duo.getCodigo_fonasa_descripcion());%></td>

                <td <%=titulo%> >Tramo</td>
                <td <%=datos%> ><% out.write("" + duo.getTramo_prevision());%></td>
            </tr>
            <tr>
                <td <%=titulo%> >Pueblo Ori.</td>
                <td colspan="3" <%=datos%> ><% out.write("" + duo.getPueblo_descri());%></td>

                <td <%=titulo%> >Consultorio Pert.</td>
                <td <%=datos%> ><% out.write("" + duo.getConsultorio_descri());%></td>
            </tr>
            <tr>
                <td <%=titulo%> >Nación</td>
                <td colspan="3" <%=datos%> ><% out.write("" + duo.getNacion_descripcion());%></td>

                <td <%=titulo%> >Comuna</td>
                <td <%=datos%> ><% out.write("" + duo.getComuna_descri());%></td>
            </tr>
        </tbody>
    </table>
</fieldset>

<fieldset>
    <legend>Evoluciones</legend>
    <table border="0">
        <tbody>
            <tr valign="top" >
                <% if (see_visita.contains(obtiene_perfil)) {%>
                <td>
                    <table cellpadding='5px' border='1'  >
                        <tr>

                            <td align='right' >
                                <%   out.write("<a href='" + neg.getLocal() + "PDF_DUO?id_duo=" + duo.getId_duo() + "' target='_blank'><img src='../Imagenes/doctorImp.png' width='30' height='30' title='Ingreso Medico'/></a>");%>
                            </td>
                            <td valign="top"><<-Ver Ingreso Médico</td>

                            <% if (see_alta_medica.contains(obtiene_perfil)) {%>
                            <td style="  " align='right' > 


                                <%    // if (see_kinesiologia.contains(obtiene_perfil)) {
                                    out.write("<form name='form_medica" + duo.getId_duo() + "' id='form_medica" + duo.getId_duo() + "' action='" + neg.getLocal() + "visita_medica.jsp' method='POST' >");
                                    out.write("<input type='hidden' name='txt_duo' value='" + duo.getId_duo() + "' >");
                                    out.write(" <img title='Visita Médica' width='30' height='30'   src='../Imagenes/visita.png' onclick='document.forms[\"form_medica" + duo.getId_duo() + "\"].submit();' style='cursor:pointer'>");
                                    out.write("</form>");
                                    // }
                                %>


                            </td>
                            <td valign="top" ><<-Ir visita Médica</td>
                            <%}%>
                        </tr>
                        <tr>

                            <td align='right' >
                                <%
                                    out.write("<a href='" + neg.getLocal() + "PDF_ingreso_enfermeria?txt_duo=" + duo.getId_duo() + "' target='_blank'><img src='../Imagenes/enfermeraImp.png' width='30' height='30' title='Ingreso Enfermeria'/></a>");

                                    //    out.write("<a href='http://10.8.4.9:9090/modulo_uo/Ingreso/IngEnfPDF.jsp?id_duo=" + duo.getId_duo() + "' target='_blank'><img src='../Imagenes/enfermeraImp.png' width='25' height='26' alt='Ingreso Enfermeria'/></a>&nbsp;&nbsp;");
                                %>

                            </td>
                            <td valign="top"><<-Ver Ingreso Enfermería</td>


                            <td align='right' >
                                <%                                    out.write("<form name='form_sesion_terapeuta" + duo.getId_duo() + "' id='form_sesion_terapeuta" + duo.getId_duo() + "' action='" + neg.getLocal() + "terapeuta/datos_terapeuta.jsp' method='POST' >");
                                    out.write("<input type='hidden' name='txt_duo' value='" + duo.getId_duo() + "' >");
                                    out.write(" <img title='Visita Terapeuta' width='30' height='30'   src='../Imagenes/visita.png' onclick='document.forms[\"form_sesion_terapeuta" + duo.getId_duo() + "\"].submit();' style='cursor:pointer'>");
                                    out.write("</form>");

                                %>

                            </td>
                            <td valign="top"><<-Ir Terapeuta Ocupacional</td>
                        </tr>


                        <a  ></a>

                        <tr>
                            <td align='right' >
                                <%                        out.write("<a href='" + neg.getLocal() + "PDF_visita_medica?txt_duo=" + duo.getId_duo() + "' target='_blank'><img src='../Imagenes/pdf.png' width='30' height='30' title='Evolución Médica'/></a>");
                                    //    out.write("<a href='http://10.8.4.9:9090/modulo_uo/Ingreso/IngEnfPDF.jsp?id_duo=" + duo.getId_duo() + "' target='_blank'><img src='../Imagenes/enfermeraImp.png' width='25' height='26' alt='Ingreso Enfermeria'/></a>&nbsp;&nbsp;");
                                %>

                            </td>
                            <td valign="top"><<-Ver Evolución Médica</td>

                            <td align='right'>
                                <%    // if (see_kinesiologia.contains(obtiene_perfil)) {
                                    out.write("<form name='form_kine" + duo.getId_duo() + "' id='form_kine" + duo.getId_duo() + "' action='" + neg.getLocal() + "kinesiologia/datos_kinesiologia.jsp' method='POST' >");
                                    out.write("<input type='hidden' name='txt_manda_duo' value='" + duo.getId_duo() + "' >");
                                    out.write(" <img title='Kinesiologia' width='30' height='30'   src='../Imagenes/k.png' onclick='document.forms[\"form_kine" + duo.getId_duo() + "\"].submit();' style='cursor:pointer'>");
                                    out.write("</form>");
                                    // }
                                %>

                            </td>
                            <td valign="top" ><<-Ir kinesiología</td>
                        </tr>
                        <tr>
                            <td align='right' >
                                <%    // if (see_kinesiologia.contains(obtiene_perfil)) {
                                    out.write("<form name='form_nutri" + duo.getId_duo() + "' id='form_nutri" + duo.getId_duo() + "' action='" + neg.getLocal() + "nutricionista/datos_nutricionista.jsp' method='POST' >");
                                    out.write("<input type='hidden' name='txt_manda_duo' value='" + duo.getId_duo() + "' >");
                                    out.write(" <img title='Nutricionista' width='30' height='30'   src='../Imagenes/visita.png' onclick='document.forms[\"form_nutri" + duo.getId_duo() + "\"].submit();' style='cursor:pointer'>");
                                    out.write("</form>");
                                    // }
                                %>

                            </td>
                            <td valign="top"><<-Ir Evolución Nutricionista</td>

                            <td align='right'>
                                <%    // if (see_kinesiologia.contains(obtiene_perfil)) {
                                    out.write("<form name='form_fono" + duo.getId_duo() + "' id='form_fono" + duo.getId_duo() + "' action='" + neg.getLocal() + "fonoaudiologa/datos_fonoaudiologa.jsp' method='POST' >");
                                    out.write("<input type='hidden' name='txt_manda_duo' value='" + duo.getId_duo() + "' >");
                                    out.write(" <img title='Fonouriologo' width='30' height='30'   src='../Imagenes/visita.png' onclick='document.forms[\"form_fono" + duo.getId_duo() + "\"].submit();' style='cursor:pointer'>");
                                    out.write("</form>");
                                    // }
                                %>

                            </td>
                            <td valign="top" ><<-Ir Fonoaudiologa</td>
                        </tr>
                        <tr>
                            <td align='right' >
                                <%                                    out.write("<form name='form_sesion_psicologo" + duo.getId_duo() + "' id='form_sesion_psicologo" + duo.getId_duo() + "' action='" + neg.getLocal() + "psicologo/datos_psicologo.jsp' method='POST' >");
                                    out.write("<input type='hidden' name='txt_duo' value='" + duo.getId_duo() + "' >");
                                    out.write(" <img title='Visita Spicologo' width='30' height='30'   src='../Imagenes/visita.png' onclick='document.forms[\"form_sesion_psicologo" + duo.getId_duo() + "\"].submit();' style='cursor:pointer'>");
                                    out.write("</form>");

                                %>

                            </td>
                            <td valign="top"><<-Ir Psicologia</td>

                        </tr>

                    </table>
                </td>

                <td>
                    &nbsp;&nbsp;  &nbsp;&nbsp;  &nbsp;&nbsp; 
                </td>

                <% }%>

                <td>

                    <form action="../egreso/cambio_cama.jsp" name="form_cambiar_entre" method="POST" >
                        <input type="hidden" name="txt_manda_duo" value="<%=duo.getId_duo()%>"  >
                        <input type="hidden"name="txt_modo" value="1"  >
                        <input type="submit" name="btn_cambiar_disponible"  value="CAMBIAR A CAMA DISPONIBLE" style="width: 200px"   />
                        &nbsp;
                    </form>
                    <br>       
                    <form action="../egreso/cambio_cama.jsp" name="form_cambiar_entre" method="POST" >
                        <input type="hidden" name="txt_manda_duo" value="<%=duo.getId_duo()%>"  >
                        <input type="hidden" name="txt_modo" value="2"  >
                        <input type="submit" name="btn_cambiar_entre"  value="CAMBIAR ENTRE CAMAS" style=" width:  200px"   />
                        &nbsp;
                    </form>
                    <br>

                    <% if (duo.getEstado_duo() == 21) {
                            if (see_alta_medica.contains(obtiene_perfil)) {
                    %>
                    <form action="<%=neg.getLocal()%>egreso/alta_medica.jsp" name="form_alta_medica" method="POST"   >
                        <input type="hidden" name="txt_manda_duo" value="<%=duo.getId_duo()%>"  >
                        <input type="hidden" name="txt_modo" value="1"  >
                        <input type="submit" name="btn_alta_medica"  value="DAR ALTA MEDICA" style="width: 200px"   />
                        &nbsp;
                    </form>
                    <%     } else {
                            out.write("Debe tener Epicrisis en UO");
                        }

                    } else if (duo.getEstado_duo() == 3) {

                        if (duo.getFecha_hora_alta_med_duo().contains("---")) {

                            if (see_alta_medica.contains(obtiene_perfil)) {
                    %>
                    <form action="<%=neg.getLocal()%>ingresa_alta" name="form_alta_adm" method="POST" onsubmit="return  valida_valida_epicrisis()"  >
                        <input type="hidden" name="id_duo" value="<%=duo.getId_duo()%>"  >
                        <input type="hidden" name="txt_modo" value="2"  >
                        <input type="submit" name="btn_alta_adm"  value="VALIDAR ALTA MEDICA" style="width: 200px"   />
                        &nbsp;
                    </form>
                    <%
                        } else {
                            out.write("Debe validarse en UO");
                        }
                    } else if (see_alta_administrativa.contains(obtiene_perfil)) {
                    %>
                    <form action="<%=neg.getLocal()%>egreso/alta_administrativa.jsp" name="form_alta_adm" method="POST"  >
                        <input type="hidden" name="txt_manda_duo" value="<%=duo.getId_duo()%>"  >
                        <input type="hidden" name="txt_modo" value="3"  >
                        <input type="submit" name="btn_alta_adm"  value="DAR ALTA ADMINISTRATIVA"  style="width: 200px"  />
                        &nbsp;
                    </form>
                    <%  } else {
                                out.write("Debe ser Dado de Alta en Admision UGU");
                            }
                        } else {
                            out.write("Duo estado:" + duo.getEstado_duo_descripcion());
                        }
                    %>
                </td>
            </tr>
        </tbody>
    </table>

</fieldset>


<fieldset  >
    <legend>Datos Clínicos</legend>
    <table border="1">
        <tbody>
            <tr>
                <td <%=titulo%> >ID</td>
                <td colspan="3" <%=datos%> ><% out.write("" + duo.getId_duo());%></td>
                <td <%=titulo%> >CAMA ACTUAL</td>
                <td <%=datos%> ><% out.write("" + duo.getCama_descripcion());%></td>
            </tr>
            <tr>
                <td <%=titulo%> >Derivador</td>
                <td colspan="3" <%=datos%> ><% out.write(duo.getDerivador_descripcion());%></td>
                <td <%=titulo%> >Programa</td>
                <td <%=datos%> ><% out.write("" + duo.getPrograma_descripcion());%></td>
            </tr>

            <tr>
                <td colspan="2" <%=titulo%> >Fecha Ingreso Admision</td>
                <td <%=datos%> ><% out.write("" + duo.getFecha_hora_ing_duo());%></td>
                <td <%=titulo%> >Admisor(a)</td>
                <td colspan="2" <%=datos%> ><% out.write("" + duo.getNombre_usuario_admision());%></td>
            </tr>
            <tr>
                <td colspan="2" <%=titulo%> >Fecha Ingreso Enfermeria</td>
                <td <%=datos%> ><% out.write("" + duo.getFecha_hora_ing_enf());%></td>
                <td <%=titulo%> >Enfermero(a)</td>
                <td colspan="2" <%=datos%> ><% out.write("" + duo.getNombre_usuario_ing_enf());%></td>
            </tr>
            <tr>
                <td colspan="2" <%=titulo%> >Fecha Ingreso Medico</td>
                <td <%=datos%> ><% out.write("" + duo.getFecha_hora_ing_med());%></td>
                <td <%=titulo%> >Médico(a)</td>
                <td colspan="2" <%=datos%> ><% out.write("" + duo.getNombre_usuario_ing_med());%></td>
            </tr>
        </tbody>
    </table>
</fieldset>

