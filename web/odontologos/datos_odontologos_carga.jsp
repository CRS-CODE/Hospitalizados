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
    ArrayList lista_sesion_odontologos = neg.lista_sesion_odontologos(id_duo);

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
    see_pdf.add(18);
    see_pdf.add(20);

    ArrayList see_registrar = new ArrayList();

    see_registrar.add(20);


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
        <td rowspan="2" ><img src="../Imagenes/kinesiologia.png" width="48" height="48" alt="Odontologos"/></td>
        <td <%=titulo%> Datos del Pscologia</td>
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
                <legend>Sesi�n Odontologos</legend>
                <table>
                    <tr>
                        <%    //
                            Iterator it_lista_sesion_odontologos = lista_sesion_odontologos.iterator();
                            int contador = 0;
                            if (lista_sesion_odontologos.size() == 0) {
                                out.write("<h3>Sin registro</h3>");
                            } else if (it_lista_sesion_odontologos.hasNext()) {
                                cSesionKine ses = (cSesionKine) it_lista_sesion_odontologos.next();
                                contador++;

                                out.write("<tr><td>");
                                out.write("<form  action='../PDF_sesion_odontologos' method='POST' target='_blank' > "
                                        + "   <input type='hidden' value='" + id_duo + "' name='txt_duo'> "
                                        + "   <button name='boton' type='submit'><img src='../Imagenes/pdf.png'> " + ses.getFecha() + "</button>  "
                                        + " </form>  ");
                                out.write("</td>");
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
                    <form name="form_kin"  action="sesion_odontologos.jsp" method="POST"  >
                        <input type="hidden" name="txt_duo" value="<%=id_duo%>" > 
                        <input class="btn btn-primary" type="submit" value="Registrar Sesi�n de Odontologos" name="btn_ses" />
                    </form> 
                </TD>


            </TR>
        </TABLE>
    </center>
</fieldset>


<%
    }

%>




