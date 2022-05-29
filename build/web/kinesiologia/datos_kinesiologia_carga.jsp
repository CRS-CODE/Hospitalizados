<%-- 
    Document   : datos_kinesiologia_carga
    Created on : 27-ago-2014, 16:13:55
    Author     : Informatica
--%>

<%@page import="CapaDato.cEvaRespiratoria"%>
<%@page import="CapaDato.cEvaNeurologia"%>
<%@page import="CapaDato.cDuo"%>
<%@page import="CapaDato.cEvaTraumatologia"%>
<%@page import="CapaDato.cSesionKine"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CapaNegocio.NegocioQ"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>

<%

    NegocioQ neg = new NegocioQ();

    int id_duo = Integer.parseInt(request.getParameter("duo"));
    ArrayList lista_sesion_kine = neg.lista_sesion_kinesiologia(id_duo);
    ArrayList lista_eva_trauma = neg.lista_evaluacion_traumatologia(id_duo);
    ArrayList lista_eva_neuro = neg.lista_evaluacion_neurologia(id_duo);

    ArrayList lista_eva_respi = neg.lista_evaluacion_respiratoria(id_duo);

    String titulo = " style=' background-color: #f7903b ; color: white '  ";
    String datos = " style=' background-color: #fcd5b6 ; color: black '  ";

    int obtiene_perfil = 0;
    String tipo_perfil = "" + session.getAttribute("usuario_perfil_descripcion");
    try {
        obtiene_perfil = Integer.parseInt("" + session.getAttribute("usuario_perfil"));
    } catch (NumberFormatException ex) {
        obtiene_perfil = -1;
    }

    ArrayList see_pdf = new ArrayList();
    see_pdf.add(1);
    see_pdf.add(2);
    see_pdf.add(4);
    see_pdf.add(8);
    see_pdf.add(9);
    see_pdf.add(10);
    see_pdf.add(12);
    see_pdf.add(13);
    see_pdf.add(14);
    see_pdf.add(15);
    see_pdf.add(16);
    see_pdf.add(17);

    ArrayList see_registrar = new ArrayList();

  
    see_registrar.add(13);


%>
<jsp:include page="../css/boton_html.jsp" />
<div style=" vertical-align: top  " align="right" >
    <form name="form_back" id="form_back" action="../datos/datos_paciente.jsp" method="POST">
        <input type="hidden" name="txt_manda_duo" value="<%=id_duo%>" />
        &nbsp;&nbsp;
        <a href="#" onclick="document.getElementById('form_back').submit()" >
            <img src="../Imagenes/fileclose.png" width="30" height="30" alt="Cerrar Ventana"/>
        </a>
        <br>
    </form>
</div>

<table>
    <tr>
        <td rowspan="2" ><img src="../Imagenes/kinesiologia.png" width="48" height="48" alt="kinesiologia"/></td>
        <td <%=titulo%> >Datos de Kinesiología</td>
    </tr>
    <tr>
        <td <%=datos%>>Documentos</td>
    </tr>
</table>

<br><br>
<%    if (see_pdf.contains(obtiene_perfil)) {

%>
<table>
    <tr valign="top" >
        <td>
            <fieldset>
                <legend>Sesión kinésica</legend>
                <table>
                    <tr>
                        <%    //
                            Iterator it_lista_sesion_kine = lista_sesion_kine.iterator();
                            int contador = 0;
                            if (lista_sesion_kine.size() == 0) {
                                out.write("<h3>Sin registro</h3>");
                            } else {
                                if (it_lista_sesion_kine.hasNext()) {
                                    cSesionKine ses = (cSesionKine) it_lista_sesion_kine.next();
                                    contador++;

                                    out.write("<tr><td>");
                                    out.write("<form  action='../PDF_sesion_kinesiologia' method='POST' target='_blank' > "
                                            + "   <input type='hidden' value='" + id_duo + "' name='txt_duo'> "
                                            + "   <button name='boton' type='submit'><img src='../Imagenes/pdf.png'> " + ses.getFecha() + "</button>  "
                                            + " </form>  ");
                                    out.write("</td>");
                                }
                            }


                        %>
                </table>  
            </fieldset>

        </td>
        <td>

            <fieldset>
                <legend  >Evaluación Respiratoria</legend>
                <table>
                    <tr>
                        <%    //
                            Iterator it_eva_respi = lista_eva_respi.iterator();
                            contador = 0;
                            if (lista_eva_respi.size() == 0) {
                                out.write("<h3>Sin registro</h3>");
                            } else {

                                while (it_eva_respi.hasNext()) {
                                    cEvaRespiratoria res = (cEvaRespiratoria) it_eva_respi.next();
                                    contador++;

                                    out.write("<tr><td>");
                                    out.write("<form  action='../PDF_evaluacion_respiratoria' method='POST' target='_blank' > "
                                            + "   <input type='hidden' value='" + res.getId_respi() + "' name='txt_id'> "
                                            + "   <button name='boton' type='submit'><img src='../Imagenes/pdf.png'> " + res.getFecha_ingreso() + "</button>  "
                                            + " </form>  ");
                                    out.write("</td>");
                                }
                            }


                        %>
                </table>  
            </fieldset>

        </td>
        <td>

            <fieldset>
                <legend>Evaluación Traumatológica</legend>
                <table>
                    <tr>
                        <%  ////
                            Iterator it_eva_trauma = lista_eva_trauma.iterator();
                            if (lista_eva_trauma.size() == 0) {
                                out.write("<h3>Sin registro</h3>");
                            } else {
                                while (it_eva_trauma.hasNext()) {
                                    cEvaTraumatologia tra = (cEvaTraumatologia) it_eva_trauma.next();

                                    out.write("<tr><td>");
                                    out.write("<form  action='../PDF_evaluacion_traumatologica' method='POST' target='_blank' > "
                                            + "   <input type='hidden' value='" + tra.getId_eva_traumatologia() + "' name='txt_id'> "
                                            + "   <button name='boton' type='submit'><img src='../Imagenes/pdf.png'> " + tra.getFecha_ingreso_trauma() + "</button>  "
                                            + " </form>  ");
                                    out.write("</td>");
                                }
                            }

                        %>
                </table>  
            </fieldset>

        </td>
        <td>
            <fieldset>
                <legend>Evaluación Neurológica</legend>
                <table>
                    <tr>
                        <%  ////
                            Iterator it_eva_neuro = lista_eva_neuro.iterator();
                            if (lista_eva_neuro.size() == 0) {
                                out.write("<h3>Sin registro</h3>");
                            } else {
                                while (it_eva_neuro.hasNext()) {
                                    cEvaNeurologia neu = (cEvaNeurologia) it_eva_neuro.next();

                                    out.write("<tr><td>");
                                    out.write("<form  action='../PDF_evaluacion_neurologica' method='POST' target='_blank' > "
                                            + "   <input type='hidden' value='" + neu.getId_neuro() + "' name='txt_id'> "
                                            + "   <button name='boton' type='submit'><img src='../Imagenes/pdf.png'> " + neu.getFecha_ingreso_neuro() + "</button>  "
                                            + " </form>  ");
                                    out.write("</td>");
                                }
                            }

                        %>
                </table>  
            </fieldset>

        </td>

    </tr>
</table>

<% } %>





<br>
<br>

<%    if (see_registrar.contains(obtiene_perfil)) {
%>
<fieldset>
    <legend>Registros</legend>
    <center>
        <TABLE>
            <TR>
                <TD>
                    <form name="form_kin"  action="sesion_kinesica.jsp" method="POST"  >
                        <input type="hidden" name="txt_duo" value="<%=id_duo%>" > 
                        <input class="btn btn-primary" type="submit" value="Registrar Sesión Kinésica" name="btn_ses" />
                    </form> 
                </TD>
                <TD>
                    <form name="form_res"  action="evaluacion_respiratoria.jsp" method="POST"  >
                        <input type="hidden" name="txt_duo" value="<%=id_duo%>" > 
                        <input class="btn btn-primary" type="submit" value="Registrar Ev. Respiratoria" name="btn_respiratoria"  />
                    </form> 
                </TD>
                <TD>
                    <form name="form_neu"  action="evaluacion_neurologica.jsp" method="POST"  >
                        <input type="hidden" name="txt_duo" value="<%=id_duo%>" > 
                        <input class="btn btn-primary" type="submit" value="Registrar Ev. Neurológica" name="btn_neu"  />
                    </form>  
                </TD>
                <TD>
                    <form name="form_tra"  action="evaluacion_traumatologica.jsp" method="POST"  >
                        <input type="hidden" name="txt_duo" value="<%=id_duo%>" > 
                        <input class="btn btn-primary" type="submit" value="Registrar Ev. Traumatológica" name="btn_tra" />
                    </form> 
                </TD>

            </TR>
        </TABLE>
    </center>
</fieldset>


<%
    }

%>




